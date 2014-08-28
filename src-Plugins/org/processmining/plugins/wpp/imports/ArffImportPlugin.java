package org.processmining.plugins.wpp.imports;

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
@Plugin(name = "Import Arff",
        parameterLabels = { "Filename" },
        returnLabels = { "Arff" },
        returnTypes = { Instances.class })

@UIImportPlugin(description = "Extract Instances",
                extensions = { "arff" })

public class ArffImportPlugin extends AbstractImportPlugin {
  
  @Override
  protected Instances importFromStream(final PluginContext context,
                                    final InputStream input,
                                    final String filename,
                                    final long fileSizeInBytes) throws IOException {
    try {
      context.getFutureResult(0).setLabel("Arff Instances from " + filename);
      URI ArffUri = new URI("C:/Users/Mauro/Desktop/" + filename);
      DataSource source = new DataSource(ArffUri.toString());
      Instances instances = source.getDataSet();
      //FrecSeqPatterns result = new FrecSeqPatterns(instances);
      return instances;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}
