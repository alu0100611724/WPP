package org.processmining.plugins.helloworld;

import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.plugin.events.Logger.MessageLevel;
import org.processmining.framework.providedobjects.ProvidedObjectDeletedException;
import org.processmining.framework.providedobjects.ProvidedObjectID;
import org.processmining.framework.util.ui.wizard.ListWizard;
import org.processmining.framework.util.ui.wizard.ProMWizardDisplay;
import org.processmining.framework.util.ui.wizard.TextStep;

/**
 * This plugin is like 31 but it use a wizard and step configuration 
 * @author Mauro
 *
 */
@Plugin(name = "Procreate32",
    parameterLabels = { "Father", "Mother", "Procreation Configuration" },
    returnLabels = { "Child" },
    returnTypes = { Person.class })
public class ProcreatePlugin32 {

  @UITopiaVariant(affiliation = "University of Life",
      author = "Britney J. Spears",
      email = "britney@westergaard.eu",
      uiLabel = UITopiaVariant.USEPLUGIN)
  @PluginVariant(requiredParameterLabels = { 0, 1, 2 })
  //<-------------------------------------------- Este metodo es del 31
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
  
  //<-------------------------------------------- Este metodo es del 31
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
  
  //<------------------------------------------------- Importante a partir de aqui
  @SuppressWarnings({ "unchecked", "cast" })
  public static ProcreationConfiguration populate(final UIPluginContext context,
                                                  final ProcreationConfiguration config) {
    /*
     * The ListWizard takes a list of wizard steps as parameter and configures itself 
     * accordingly. The first step is the TextStep, which takes a title and main content,
     * both as strings. It can display HTML. The second step is our previously created 
     * NameStep.
     */
    ListWizard<ProcreationConfiguration> wizard =
      new ListWizard(TextStep.create("Name that Bastard", 
                          "<html><p>In the following screen, name " +
          		            "the offspring<p>No silly names, please!"), new NameStep());
    
    /*
     * To show the wizard, we just call the static show method of the ProMWizardDisplay
     * class. It takes a context, a wizard and an initial configuration.
     */
    return (ProcreationConfiguration) ProMWizardDisplay.show(context, wizard, config);
    /*
     * The wizard is run, including back and forward steps, and when the user completes 
     * the last page or presses cancel, the method returns. If the user cancelled null is
     *  returned, otherwise an appropriate configuration is returned.
     */
  }
}