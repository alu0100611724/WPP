package org.processmining.plugins.wpp.visualizers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.processmining.contexts.uitopia.annotations.Visualizer;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.util.ui.widgets.ProMScrollablePanel;
import org.processmining.framework.util.ui.widgets.WidgetColors;
import org.processmining.plugins.petrinet.PetriNetVisualization;
import org.processmining.plugins.wpp.objects.GspPetrinet;

import com.fluxicon.slickerbox.ui.SlickerScrollBarUI;
 
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
    ProMScrollablePanel pBotones = new ProMScrollablePanel();
    Container cBotones = new Container(); 
    cBotones.setLayout(new BoxLayout(cBotones, BoxLayout.Y_AXIS));
    //pBotones.setLayout(new GridLayout(fsp.getGsp().getNumberOfFreqSeq(), 1));
    
    for (int i = 0; i < fsp.getGsp().getNumberOfCycles(); i++) {
      for (int j = 0; j<fsp.getGsp().getCycleAt(i).size(); j++) {
        JButton bSequence = new JButton(fsp.getGsp().getCycleAt(i).getSequenceAt(j).toString());
        bSequence.setBackground(java.awt.Color.darkGray);
        bSequence.setForeground(java.awt.Color.white);
        bSequence.setAlignmentX(Component.LEFT_ALIGNMENT);
        //bSequence.setMinimumSize(new Dimension(100, 30));
        //bSequence.setMaximumSize(new Dimension(5000, 40));
        //bSequence.setPreferredSize(new Dimension(500, 30));
        cBotones.add(bSequence);
      }
    }
    
    final JScrollPane logarea = new JScrollPane(cBotones);
    logarea.setOpaque(true);
    logarea.setBackground(WidgetColors.COLOR_ENCLOSURE_BG);
    logarea.setBorder(BorderFactory.createEmptyBorder());
    logarea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    logarea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JScrollBar vBar = logarea.getVerticalScrollBar();
    
    vBar.setUI(new SlickerScrollBarUI(vBar, new Color(0, 0, 0, 0), new Color(160, 160, 160),
        WidgetColors.COLOR_NON_FOCUS, 4, 12));
    vBar.setOpaque(true);
    vBar.setBackground(WidgetColors.COLOR_ENCLOSURE_BG);
    
    vBar = logarea.getHorizontalScrollBar();
    vBar.setUI(new SlickerScrollBarUI(vBar, new Color(0, 0, 0, 0), new Color(160, 160, 160),
        WidgetColors.COLOR_NON_FOCUS, 4, 12));
    vBar.setOpaque(true);
    vBar.setBackground(WidgetColors.COLOR_ENCLOSURE_BG);
    
    pBotones.setBackground(WidgetColors.COLOR_ENCLOSURE_BG);
    pBotones.setLayout(new BorderLayout());
    pBotones.setMinimumSize(new Dimension(200, 100));
    pBotones.setMaximumSize(new Dimension(400, 5000));
    pBotones.setPreferredSize(new Dimension(350, 100));

    pBotones.add(Box.createHorizontalStrut(5), BorderLayout.WEST);
    pBotones.add(logarea, BorderLayout.CENTER);
    pBotones.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
    
    
    //pBotones.add(cBotones);
    pBotones.setAutoscrolls(true);//.setMaximumSize(d = new Dimension(1,2));
    //fsp.getPetri().getArc(new PetrinetNode, target);
    //fsp.getPetri().getNodes();
    //fsp.getPetri().getPlaces();
    fsp.getPetri().getTransitions();
    
    //ColouredPetriNet cpn = new ColouredPetriNet(fsp.getPetri()); <----------------
    
    JComponent pPetri = pnv.visualize(context, fsp.getPetri());
    JPanel pMix = new JPanel();
    pMix.setLayout(new BorderLayout());
    pMix.add(pPetri, BorderLayout.CENTER);
    pMix.add(pBotones, BorderLayout.EAST);
    
 // Always return a single parameter of type JComponent
    return pMix;
  }
}