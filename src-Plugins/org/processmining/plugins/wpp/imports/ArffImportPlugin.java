package org.processmining.plugins.wpp.imports;

import java.io.File;
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
      File appDir = new File(System.getProperty("user.dir"));
      URI uri = new URI(appDir.toURI() + "resources/" + filename);
      //System.out.println(uri.getPath().toString());
      DataSource source = new DataSource(uri.getPath().toString());
      Instances instances = source.getDataSet();
      return instances;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}
