/**
 * Tutorial from https://westergaard.eu/category/tutorials/prom-tutorial/
 */
package org.processmining.plugins.helloworld;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextField;

//Esta anotacion indica que esta clase es un plugin
@Plugin(name = "Procreate",
        parameterLabels = { "Father", "Mother", "Procreation Configuration" },
        returnLabels = { "Child" },
        returnTypes = { Person.class })
/**
 * @author Mauro
 * Simple plug-in allowing two persons to have a child
 */
public class ProcreatePlugin {
  //Esta anotacion indica que el plugin debera ser expuesto a la interfaz grafica
  @UITopiaVariant(affiliation = "University of Life",
                  author = "Britney J. Spears",
                  email = "britney@westergaard.eu",
                  uiLabel = UITopiaVariant.USEPLUGIN)
  //Indica que este metodo es una variante del plugin
  @PluginVariant(requiredParameterLabels = { 0, 1, 2 })
  public static Person procreate(final PluginContext context,
                                 final Person father,
                                 final Person mother,
                                 final ProcreationConfiguration config) {
    Person child = new Person();
    child.setAge(0);
    child.setName(new Name(config.getName(), father.getName().getLast()));
    return child;
  }
 
  @UITopiaVariant(affiliation = "University of Life",
                  author = "Britney J. Spears",
                  email = "britney@westergaard.eu",
                  uiLabel = UITopiaVariant.USEPLUGIN)
  @PluginVariant(requiredParameterLabels = { 0, 1 })
  public static Person procreate(final UIPluginContext context,
                                 final Person father,
                                 final Person mother) {
    ProcreationConfiguration config = new ProcreationConfiguration("");
    populate(context, config);
    return procreate(context, father, mother, config);
  }
  
  public static ProcreationConfiguration populate(final UIPluginContext context,
          									      final ProcreationConfiguration config) {
	  ProMPropertiesPanel panel = new ProMPropertiesPanel("Configure Procreation");
	  ProMTextField name = panel.addTextField("Name", config.getName());
	  final InteractionResult interactionResult = context.showConfiguration("Setup Procreation", panel);
	  if (interactionResult == InteractionResult.FINISHED ||
			  interactionResult == InteractionResult.CONTINUE ||
			  interactionResult == InteractionResult.NEXT) {
		  config.setName(name.getText());
		  return config;
	  }
	  //Este metodo populate retorna null si l configuracion fue cancelada
	  return null;
  }
}
