package org.processmining.plugins.wpp.visualizers;

import javax.swing.JComponent;

import org.processmining.contexts.uitopia.annotations.Visualizer;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.util.ui.widgets.ProMTextArea;

import weka.core.Instances;
 
@Plugin(name = "Show Arff Instances",
        parameterLabels = { "Arff" },
        returnLabels = { "Arff Instances viewer" },
        returnTypes = { JComponent.class },
        userAccessible = false)
@Visualizer
public class ArffVisualizer {

  @PluginVariant(requiredParameterLabels = { 0 })
  public static JComponent visualize(final PluginContext context,
                                     final Instances arff) {
    ProMTextArea data = new ProMTextArea(false);
    data.setText(arff.toString());
    
 // Always return a single parameter of type JComponent
    return data;
  }
}