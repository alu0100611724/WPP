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
