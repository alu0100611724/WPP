package org.processmining.plugins.wpp.exports;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.processmining.contexts.uitopia.annotations.UIExportPlugin;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.plugins.wpp.objects.Gsp;

@Plugin(name = "Export Frequent Secuences",
    parameterLabels = { "Frequent Secuences", "File" },
    returnLabels = {},
    returnTypes = {})
@UIExportPlugin(description = "Frequent Secuences file",
        extension = "fqp")
public class ExportFrequentSequences {
  @UITopiaVariant(affiliation = "Universidad de la Laguna",
      author = "Maurizio Rendon",
      email = "mauriziorendon@gmail.com")
  @PluginVariant(requiredParameterLabels = { 0, 1 })
  public void export(PluginContext context,
          Gsp fsp,
          File file) throws IOException {
      FileWriter writer = new FileWriter(file);
      PrintWriter pwriter = new PrintWriter(writer);
     /* pwriter.print(person.getFirst());
      pwriter.print(' ');
      pwriter.println(person.getLast());
      */
      pwriter.close();
  }
}
