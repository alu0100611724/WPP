package org.processmining.plugins.wpp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
 
/**
 * Importar un obeto en prom significa cargar o leerlo.
 * @author Mauro
 *
 */
@Plugin(name = "Arff to Frecuent Sequences",
        parameterLabels = { "Filename" },
        returnLabels = { "FrecSeqPatterns" },
        returnTypes = { FrecSeqPatterns.class })

@UIImportPlugin(description = "Frequent Sequential Patterns",
                extensions = { "arff" })

public class ArffImportPlugin extends AbstractImportPlugin {
  
  @Override
  protected FrecSeqPatterns importFromStream(final PluginContext context,
                                    final InputStream input,
                                    final String filename,
                                    final long fileSizeInBytes) throws IOException {
    try {
      context.getFutureResult(0).setLabel("Frecuent Sequences from " + filename);
      URI ArffUri = new URI("C:/Users/Mauro/Desktop/" + filename);
      DataSource source = new DataSource(ArffUri.toString());
      Instances instances = source.getDataSet();
      FrecSeqPatterns result = new FrecSeqPatterns(instances);
      return result;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}
