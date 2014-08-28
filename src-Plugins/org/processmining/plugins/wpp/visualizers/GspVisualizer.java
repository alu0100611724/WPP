/**
 * 
 */
package org.processmining.plugins.wpp.visualizers;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.processmining.contexts.uitopia.annotations.Visualizer;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.plugins.wpp.objects.Gsp;
 
@Plugin(name = "Show Frequent Sequential Patterns",
        parameterLabels = { "FrecSeqPatterns" },
        returnLabels = { "Frequent Sequential Patterns Viewer" },
        returnTypes = { JComponent.class },
        userAccessible = false)
@Visualizer
public class GspVisualizer {

  /*
   * Here, we just return a passive JComponent object, but it is perfectly ok for a 
   * visualizer to include active elements including event handlers. Visualizers can
   * present different views of the same object (e.g., zooming in or making various 
   * projections) and are allowed to contain a reference to the original object. 
   * Visualizers should not, however, ever change the provided object.Visualizers are 
   * allowed to create new objects based on the input.
   * 
   * Again, we have made the visualizer static as a single visualizer may display multiple 
   * objects,and may even be instantiated multiple times for the same object. 
   */
  @PluginVariant(requiredParameterLabels = { 0 })
  public static JComponent visualize(final PluginContext context,
                                     final Gsp fsp) {
    JPanel pBotones = new JPanel();
    JPanel pPetri = new JPanel();
    JPanel pMix = new JPanel();
    
    pBotones.setLayout(new GridLayout(fsp.getLastCycle().size(), 1));
    for (int i = 0; i<fsp.getLastCycle().size(); i++) {
      JButton bSequence = new JButton(fsp.getLastCycle().getSequenceAt(i).toString());
      pBotones.add(bSequence);
    }
    pMix.setLayout(new BorderLayout());
    pMix.add(pPetri, BorderLayout.CENTER);
    pMix.add(pBotones, BorderLayout.WEST);
    
 // Always return a single parameter of type JComponent
    return pMix;
  }
}