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
import org.processmining.plugins.wpp.objects.FrecSeqPatterns;
 
@Plugin(name = "Show Frequent Sequential Patterns",
        parameterLabels = { "FrecSeqPatterns" },
        returnLabels = { "Frequent Sequential Patterns Viewer" },
        returnTypes = { JComponent.class },
        userAccessible = false)
@Visualizer
public class FrecSeqPatternsVisualizer {
  @PluginVariant(requiredParameterLabels = { 0 })
  public static JComponent visualize(final PluginContext context,
                                     final FrecSeqPatterns fsp) {
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
    return pMix;
  }
}