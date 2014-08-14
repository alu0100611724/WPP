package org.processmining.plugins.helloworld;

import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.connections.ConnectionCannotBeObtained;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;

/**
 * Consider we want to scold a person. If said person is younger than 18, we want this to 
 * fall back on the father if he is known, and otherwise on the mother (if known).
 * @author Mauro
 *
 */
@Plugin(name = "Scold",
        parameterLabels = { "Vicim" },
        returnLabels = { "Scolding" },
        returnTypes = { Scolding.class })
public class ScoldPlugin {
    
  @UITopiaVariant(affiliation = "University of Life",
                  author = "Britney J. Spears",
                  email = "britney@westergaard.eu",
                  uiLabel = UITopiaVariant.USEPLUGIN)
  @PluginVariant(requiredParameterLabels = { 0 })
  public static Scolding scold(final PluginContext context,
                               final Person victim) {
    if (victim.getAge() < 18) {
      try {
        for (ParentConnection c : context.getConnectionManager().getConnections(
             ParentConnection.class, context, victim)) {
          if (c.getObjectWithRole(ParentConnection.CHILD) == victim) {
            Person toScold = c.getObjectWithRole(ParentConnection.MOTHER);
            if (toScold == null)
              toScold = c.getObjectWithRole(ParentConnection.FATHER);
            if (toScold != null)
              context.getProvidedObjectManager().createProvidedObject(
                  "Bad Parent", new Scolding(toScold), Scolding.class, context);
          }
        }
      } catch (ConnectionCannotBeObtained _) {
        // Ignore; nobody cares about orphans anyway
      }
    }
    return new Scolding(victim);
  }
  // More stuff from the previous parts
}
