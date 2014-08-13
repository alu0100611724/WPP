package org.processmining.plugins.helloworld;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.framework.plugin.annotations.*;
import org.processmining.framework.plugin.events.Logger.MessageLevel;
import org.processmining.framework.plugin.*;
import org.processmining.contexts.uitopia.*;
import org.processmining.contexts.uitopia.annotations.*;
import org.processmining.framework.providedobjects.*;
import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextField;

@Plugin(name = "Procreate 31",
  parameterLabels = { "Father", "Mother", "Procreation Configuration" },
  returnLabels = { "Child" },
  returnTypes = { Person.class })
public class ProcreatePlugin31 {
  
  @UITopiaVariant(affiliation = "University of Life",
      author = "Britney J. Spears",
      email = "britney@westergaard.eu",
      uiLabel = UITopiaVariant.USEPLUGIN)
  @PluginVariant(requiredParameterLabels = { 0, 1, 2 })
  //<-------------------------------------------- Este metodo es del antiguo combineAll24
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
  
  // <------------------------------------------------- Importante a partir de aqui
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
    for (ProvidedObjectID id : context.getProvidedObjectManager().getProvidedObjects()) {
      try {
        //For each object, we check that the type of the object is a ProcreationConfiguration
        Class<?> clazz = context.getProvidedObjectManager().getProvidedObjectType(id);
        //If the object is the correct type, we fetch it and test the type.
        if (ProcreationConfiguration.class.isAssignableFrom(clazz)) {
          /* When fetching the object, we provide false as a second parameter. 
           * This means that if the object is not yet fully computed, we get a Future
           * instead of the actual object.
           */
          Object o = context.getProvidedObjectManager().getProvidedObjectObject(id, false);
          /* we check that the object is actually of the correct type and not a Future 
           * or null
           */
          if (o instanceof ProcreationConfiguration) {
            // If the object has the correct type, we update the existing configuration 
            ProcreationConfiguration c = (ProcreationConfiguration) o;
            config.setName(c.getName());
            break;
          }
        }
      } catch (ProvidedObjectDeletedException _) {
        /* We wrap any handling in a try-catch statement as a provided object may be 
         * deleted while processing
         */
        
        //Do nothing
      }
    }
    config = populate(context, config);
    if (config == null) {
      //Usamos el getFutureResult(0) para cancelar el proceso 
        context.getFutureResult(0).cancel(true);
        return null;
    }
    context.getProvidedObjectManager().createProvidedObject("Procreation Configuration",
        config, ProcreationConfiguration.class, context);
    return procreate(context, father, mother, config);
  }

  //<-------------------------------------------- Este metodo es del antiguo combineAll24
  public static ProcreationConfiguration populate(final UIPluginContext context,
      final ProcreationConfiguration config) {
    ProMPropertiesPanel panel = new ProMPropertiesPanel("Configure Procreation");
    ProMTextField name = panel.addTextField("Name", config.getName());
    final InteractionResult interactionResult = context.showConfiguration(
        "Setup Procreation", panel);
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
