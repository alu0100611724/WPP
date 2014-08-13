package org.processmining.plugins.helloworld;

import java.io.*;
import org.processmining.framework.plugin.*;
import org.processmining.framework.plugin.annotations.*;
import org.processmining.contexts.uitopia.annotations.*;

/**
 * Exportar significa guardar en Prom
 * @author Mauro
 *
 */
@Plugin(name = "Export Person",
        parameterLabels = { "Person", "File" },
        returnLabels = {},
        returnTypes = {})
@UIExportPlugin(description = "Person file",
                extension = "person")
public class ExportPersonPlugin {
  @UITopiaVariant(affiliation = "University of Life",
                  author = "Britney J. Spears",
                  email = "britney@westergaard.eu")
  @PluginVariant(requiredParameterLabels = { 0, 1 })
  public void export(PluginContext context,
                     Person person,
                     File file) throws IOException {
    FileWriter writer = new FileWriter(file);
    PrintWriter pwriter = new PrintWriter(writer);
    pwriter.print(person.getName().getFirst());
    pwriter.print(' ');
    pwriter.println(person.getName().getLast());
    pwriter.close();
  }
}