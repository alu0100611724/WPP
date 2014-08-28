/**
 * Tutorial from https://westergaard.eu/category/tutorials/prom-tutorial/
 */
package org.processmining.plugins.wpp.implementations;

import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.plugins.helloworld.Person;

//Esta anotacion indica que esta clase es un plugin
@Plugin(name = "Analyze Frequent Sequential Patterns",
        parameterLabels = { "Petri net", "Frequent Sequences", "Analyze Configuration" },
        returnLabels = { "Petri net " },
        returnTypes = { Person.class })
/**
 * @author Mauro
 * Simple plug-in allowing two persons to have a child
 */
public class AnalizeFSPPlugin {
  
  /*
   * The main idea of the plug-in is to expose two variants: one does the actual work and 
   * one populates the configurations.  
   * The 1st variant do the actual work, takes all parameters and the configuration,
   * and the other takes all parameters except the configuration. 
   * 
   * If a plug-in returns multiple parameters, they should be returned in an 
   * Object[14] array
   */
  
  
  /*Esta anotacion indica que el plugin debera ser expuesto a la interfaz grafica
  @UITopiaVariant(affiliation = "Universidad de la Laguna",
                  author = "Maurizio Rendon",
                  email = "mauriziorendon@gmail.com",
                  uiLabel = UITopiaVariant.USEPLUGIN)
  //Indica que este metodo es una variante del plugin
  @PluginVariant(requiredParameterLabels = { 0, 1, 2 })
  public static Person procreate(final PluginContext context,
                                 final Petrinet petri,
                                 final FrecSeqPatterns fsp,
                                 final AnalizeFSPConfiguration config) {
    Person child = new Person();
    child.setAge(0);
    child.setName(new Name(config.getName(), father.getName().getLast()));
    return child;
  }
 
  @UITopiaVariant(affiliation = "Universidad de la Laguna",
      author = "Maurizio Rendon",
      email = "mauriziorendon@gmail.com",
      uiLabel = UITopiaVariant.USEPLUGIN)
  @PluginVariant(requiredParameterLabels = { 0, 1 })
  public static Person procreate(final UIPluginContext context,
                                 final Petrinet petri,
                                 final FrecSeqPatterns fsp) {
    AnalizeFSPConfiguration config = new AnalizeFSPConfiguration();
    populate(context, config);
    return procreate(context, father, mother, config);
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
   *
  public static ProcreationConfiguration populate(final UIPluginContext context,
          									      final AnalizeFSPConfiguration config) {
	  ProMPropertiesPanel panel = new ProMPropertiesPanel("Configure AnalizeFSP");
	  ProMTextField name = panel.addTextField("Name", config.getName());
	  final InteractionResult interactionResult = context.showConfiguration("Setup AnalizeFSP", panel);
	  if (interactionResult == InteractionResult.FINISHED ||
			  interactionResult == InteractionResult.CONTINUE ||
			  interactionResult == InteractionResult.NEXT) {
		  config.setName(name.getText());
		  return config;
	  }
	  //Este metodo populate retorna null si l configuracion fue cancelada
	  return null;
  }*/
}
