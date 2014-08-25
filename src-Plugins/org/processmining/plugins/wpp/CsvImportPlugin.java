package org.processmining.plugins.wpp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

import weka.associations.GeneralizedSequentialPatterns;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
 
/**
 * Importar un obeto en prom significa cargar o leerlo.
 * @author Mauro
 *
 */
@Plugin(name = "CSV to Seq Freq",
        parameterLabels = { "Filename" },
        returnLabels = { "SeqFrec" },
        returnTypes = { SeqFrec.class })
//Primero indicamos una descripcion y luego su extension
@UIImportPlugin(description = "Seq Freq",
                extensions = { "arff" })
//La clase tiene que extender de AbstractImportPlugin
public class CsvImportPlugin extends AbstractImportPlugin {
  
  //Este es el metodo donde se hace la importacion
  @Override
  protected SeqFrec importFromStream(final PluginContext context,
                                    final InputStream input,
                                    final String filename,
                                    final long fileSizeInBytes) throws IOException {
    try {
      //Cambiamos el nombre del objeto por Person imported from <filename>
      context.getFutureResult(0).setLabel("CVS imported from " + filename);
      URI uCSV = new URI("C:/Users/Mauro/Desktop/" + filename);
      DataSource source = new DataSource(uCSV.toString());
      Instances instances = source.getDataSet();
      GeneralizedSequentialPatterns seq = new GeneralizedSequentialPatterns();
      //weka.filters.unsupervised.attribute.StringToNominal;
      //seq.setDataSeqID(0);
      seq.setMinSupport(0.6);
      seq.setFilterAttributes("1");
      //seq.setDebug(true);
      /*System.out.println("\n************************************");
      System.out.println("Atributos a Filtrar: " + seq.getFilterAttributes());
      System.out.println("Soporte Minimo: " + seq.getMinSupport());
      System.out.println("Seq Id: " + seq.getDataSeqID());
      System.out.println("************************************\n");*/
      seq.buildAssociations(instances);
      //System.out.println(seq.toString());

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    SeqFrec result = new SeqFrec();
   
    return result;
  }
}
