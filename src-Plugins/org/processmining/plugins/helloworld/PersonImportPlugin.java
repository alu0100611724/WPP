/**
 * 
 */
package org.processmining.plugins.helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
 
/**
 * Importar un obeto en prom significa cargar o leerlo.
 * @author Mauro
 *
 */
@Plugin(name = "Import Person",
        parameterLabels = { "Filename" },
        returnLabels = { "Person" },
        returnTypes = { Person.class })
//Primero indicamos una descripcion y luego su extension
@UIImportPlugin(description = "Person",
                extensions = { "person" })
//La clase tiene que extender de AbstractImportPlugin
public class PersonImportPlugin extends AbstractImportPlugin {
  
  //Este es el metodo donde se hace la importacion
  @Override
  protected Person importFromStream(final PluginContext context,
                                    final InputStream input,
                                    final String filename,
                                    final long fileSizeInBytes) throws IOException {
    try {
      //Cambiamos el nombre del objeto por Person imported from <filename>
      context.getFutureResult(0).setLabel("Person imported from " + filename);
    } catch (final Throwable _) {
      // Don't care if this fails
    }
    Person result = new Person();
    // Fill in object from input
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    StringBuilder out = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        out.append(line);
        out.append(' ');
    }
    reader.close();
    //System.out.println(out.toString());
    String cad[] = out.toString().split(" ");
    Name n = new Name(cad[0], cad[1]);
    result.setName(n);
    result.setAge(Integer.parseInt(cad[2]));
    return result;
  }
}
