/**
 * Tutorial from https://westergaard.eu/category/tutorials/prom-tutorial/
 */
package org.processmining.plugins.helloworld;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.framework.plugin.annotations.*;
import org.processmining.framework.plugin.events.Logger.MessageLevel;
import org.processmining.framework.plugin.*;
import org.processmining.contexts.uitopia.*;
import org.processmining.contexts.uitopia.annotations.*;
import org.processmining.framework.util.ui.widgets.*;

//Esta anotacion indica que esta clase es un plugin
@Plugin(name = "Procreate 24",
        parameterLabels = { "Father", "Mother", "Procreation Configuration" },
        returnLabels = { "Child" },
        returnTypes = { Person.class })
/**
 * @author Mauro
 * Simple plug-in allowing two persons to have a child
 */
public class ProcreatePluginCombineAll24 {
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
	  context.getProgress().setMaximum(5);
	  context.getProgress().setValue(1);
	  context.log("Creating new Person", MessageLevel.NORMAL);  
	  Person child = new Person();
	  context.getProgress().setValue(2);
	  child.setAge(0);
	  if (config == null) {
	      context.log("No configuration given!", MessageLevel.ERROR);
	      return null;
	  }
	  if ("Xtina".equalsIgnoreCase(config.getName())) {
	      context.log("Person has stupid name!", MessageLevel.WARNING);
	  }
	  try {
		  context.getProgress().setValue(3);
		  child.setName(new Name(config.getName(), father.getName().getLast()));
	      context.log("New person set up!", MessageLevel.DEBUG);
	  } catch (Exception e) {
	      context.log(e);
	      return null;
	  }
	  context.log("About to successfully return.", MessageLevel.TEST);
	  context.getProgress().setValue(5);
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
	    context.getProgress().setIndeterminate(false);
		ProcreationConfiguration config = new ProcreationConfiguration("");
		config = populate(context, config);
		if (config == null) {
			  //Usamos el getFutureResult(0) para cancelar el proceso 
		      context.getFutureResult(0).cancel(true);
		      return null;
		}
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
