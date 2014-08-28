package org.processmining.plugins.wpp.implementations;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextField;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.plugins.wpp.objects.Gsp;
import org.processmining.plugins.wpp.objects.GspPetrinet;

import weka.core.Instances;

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
    GspConfiguration config = new GspConfiguration();
    config = populate(context, config);
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
          									      final GspConfiguration config) {
    
	  ProMPropertiesPanel panel = new ProMPropertiesPanel("Configure GSP Algorith");
	  
	  ProMTextField minSupport = panel.addTextField("Min. Support: ", 
	      Double.toString(config.getSupport()));
	  ProMTextField idData = panel.addTextField("Sequence ID number: ",
	  		Integer.toString(config.getIdData()));
	  ProMTextField filter = panel.addTextField("Filtering Attributes: ", 
        config.getFilterAttribute());
	  
	  final InteractionResult interactionResult = context.showConfiguration("Setups", panel);
	  
	  if (interactionResult == InteractionResult.FINISHED ||
			  interactionResult == InteractionResult.CONTINUE ||
			  interactionResult == InteractionResult.NEXT) {
		  config.setSupport(Double.parseDouble(minSupport.getText()));
		  config.setIdData(Integer.parseInt(idData.getText()));
		  config.setFilterAttribute(filter.getText());
		  return config;
	  }
	  //Este metodo populate retorna null si l configuracion fue cancelada
	  return null;
  }
}
