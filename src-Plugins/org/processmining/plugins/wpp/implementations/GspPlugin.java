package org.processmining.plugins.wpp.implementations;

import java.util.ArrayList;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.util.ui.widgets.ProMComboBox;
import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextArea;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.plugins.wpp.objects.Gsp;
import org.processmining.plugins.wpp.objects.GspPetrinet;

import weka.core.Instances;

import com.fluxicon.slickerbox.components.NiceIntegerSlider;
import com.fluxicon.slickerbox.components.NiceSlider;
import com.fluxicon.slickerbox.factory.SlickerFactory;

//Esta anotacion indica que esta clase es un plugin
@Plugin(name = "Discover General Sequential Patterns",
        parameterLabels = { "Petri net", "Arff Instances", "GPS Configuration" },
        returnLabels = { "GSP Petri net" },
        returnTypes = { GspPetrinet.class })
/**
 * @author Mauro
 * Simple plug-in allowing two persons to have a child
 */
public class GspPlugin {
  
  /*
   * The main idea of the plug-in is to expose two variants: one does the actual work and 
   * one populates the configurations.  
   * The 1st variant do the actual work, takes all parameters and the configuration,
   * and the other takes all parameters except the configuration. 
   * 
   * If a plug-in returns multiple parameters, they should be returned in an 
   * Object[14] array
   */
  @UITopiaVariant(affiliation = "Universidad de la Laguna",
                  author = "Maurizio Rendon",
                  email = "mauriziorendon@gmail.com",
                  uiLabel = UITopiaVariant.USEPLUGIN)
  //Indica que este metodo es una variante del plugin
  @PluginVariant(requiredParameterLabels = { 0, 1, 2 })
  public static GspPetrinet build(final UIPluginContext context,
                                 final Petrinet petri,
                                 final Instances arff,
                                 final GspConfiguration config) {
    
    Gsp gsp = new Gsp(arff, config.isDebug(), config.getSupport(), 
        config.getIdData(), config.getFilterAttribute());
    
    return new GspPetrinet(gsp, petri);
  }
 
  @UITopiaVariant(affiliation = "Universidad de la Laguna",
      author = "Maurizio Rendon",
      email = "mauriziorendon@gmail.com",
      uiLabel = UITopiaVariant.USEPLUGIN)
  @PluginVariant(requiredParameterLabels = { 0, 1 })
  public static GspPetrinet build(final UIPluginContext context,
                                 final Petrinet petri,
                                 final Instances arff) {
    
    GspConfiguration config = new GspConfiguration(arff);
    config = populate(context, arff, config);
    return build(context, petri, arff, config);
  }
  
  /* Populate es el que crea y lanza las ventanas de prom para pedir una configuracion
   * al usuario.
   * You always want to only use widgets from either of these sources.  
   * You never want to use a JPanel and manually set the colors or any other old-fashioned 
   * hacks performed in ProM.  If a particular widget you need is not available, add it to 
   * the Widgets package and use it, never create your own local widget.
   * 
   * If a plug-in has more settings than sensible fit on a single page, the Widgets packet 
   * also provides a ProMWizard which should be used. (This is not the case)
   */
  public static GspConfiguration populate(final UIPluginContext context, 
                                  final Instances arff,
          									      final GspConfiguration config) {
    
	  ProMPropertiesPanel panel = new ProMPropertiesPanel("Configure GSP Algorith");
	  
    //JButton b = SlickerFactory.instance().createButton("prueba");
    //panel.add(b);
	  
    ProMComboBox idSeq = new ProMComboBox(config.getAttributes());
    idSeq.setToolTipText("The attribute representing the data sequence ID");
    panel.addProperty("Sequence ID", idSeq);
    
    ArrayList<String> attrExtra = config.getAttributes();
    /*
    attrExtra.remove(0);
    attrExtra.add(0, "All");
    */
    ProMComboBox filterAttr = new ProMComboBox(attrExtra);
    filterAttr.setToolTipText("The attribute used for result filtering");
    panel.addProperty("Filtering Attribute", filterAttr);
    /*
    JLabel space = SlickerFactory.instance().createLabel(" ");
    panel.add(space);
    */
    NiceIntegerSlider support = SlickerFactory.instance().createNiceIntegerSlider(
        "Min. Support (%)", 10, 100, 90, NiceSlider.Orientation.HORIZONTAL);
    support.setToolTipText("The miminum support threshold");
    panel.add(support);
    
	  ProMTextArea data = new ProMTextArea(false);
    data.setText(arff.toString());
    //SlickerTabbedPane tabP = SlickerFactory.instance().createTabbedPane("Dataset");
    //tabP.add(data);
    panel.addProperty("Dataset", data);
    /*
	  ProMTextField minSupport = panel.addTextField("Min. Support (0.5-0.9): ", 
	      Double.toString(config.getSupport()));
	  ProMTextField idData = panel.addTextField("Sequence ID number: ",
	  		Integer.toString(config.getIdData()));
	  ProMTextField filter = panel.addTextField("Filtering Attribute: ", 
        config.getFilterAttribute());
	  */
	  final InteractionResult interactionResult = context.showConfiguration("Setups", panel);
	  
	  if (interactionResult == InteractionResult.FINISHED ||
			  interactionResult == InteractionResult.CONTINUE ||
			  interactionResult == InteractionResult.NEXT) {
		  config.setSupport((support.getValue())/100.00);
		  config.setIdData(idSeq.getSelectedIndex());
		  config.setFilterAttribute(Integer.toString(filterAttr.getSelectedIndex()-1));
		  return config;
	  }
	  //Este metodo populate retorna null si l configuracion fue cancelada
	  return null;
  }
}
