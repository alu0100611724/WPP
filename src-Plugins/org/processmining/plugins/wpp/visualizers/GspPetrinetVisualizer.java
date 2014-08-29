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
import org.processmining.plugins.petrinet.PetriNetVisualization;
import org.processmining.plugins.wpp.objects.GspPetrinet;
 
@Plugin(name = "Show GSP Petri net",
        parameterLabels = { "Gsp Petri net" },
        returnLabels = { "GSP Petri net viewer" },
        returnTypes = { JComponent.class },
        userAccessible = false)
@Visualizer
public class GspPetrinetVisualizer {

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
                                     final GspPetrinet fsp) {
    PetriNetVisualization pnv = new PetriNetVisualization();
    JPanel pBotones = new JPanel();
    JComponent pPetri = pnv.visualize(context, fsp.getPetri());
    JPanel pMix = new JPanel();
    
    pBotones.setLayout(new GridLayout(fsp.getGsp().getSecondLastCycle().size(), 1));
    for (int i = 0; i<fsp.getGsp().getSecondLastCycle().size(); i++) {
      JButton bSequence = new JButton(fsp.getGsp().getSecondLastCycle().getSequenceAt(i).toString());
      bSequence.setBackground(java.awt.Color.darkGray);
      bSequence.setForeground(java.awt.Color.white);
      pBotones.add(bSequence);
    }
    pMix.setLayout(new BorderLayout());
    pMix.add(pPetri, BorderLayout.CENTER);
    pMix.add(pBotones, BorderLayout.EAST);
    
 // Always return a single parameter of type JComponent
    return pMix;
  }
}