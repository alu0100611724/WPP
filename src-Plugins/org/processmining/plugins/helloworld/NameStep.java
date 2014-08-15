package org.processmining.plugins.helloworld;

import javax.swing.JComponent;

import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextField;
import org.processmining.framework.util.ui.wizard.ProMWizardStep;

/**
 * This is an explicit class for the component.
 * This should always be done as we have no guarantee that a step may be used 
 * for multiple configurations, so saving state information inside the step is bad form. 
 * @author Mauro
 *
 * @param <M>
 */
public class NameStep<M> implements ProMWizardStep<ProcreationConfiguration> {
  
  /**
   * This class is a composite class
   * @author Mauro
   *
   */
  @SuppressWarnings("serial")
  private class ProcreationPanel extends ProMPropertiesPanel {
    
    private final ProMTextField name;
    
    public ProcreationPanel(ProcreationConfiguration config) {
      super("Configure Procreation");
      name = addTextField("Name", config.getName());
    }
    public String getName() {
      return name.getText();
    }
  }
  
  public String getTitle() {
    return "Name the Mistake";
  }

  public JComponent getComponent(final ProcreationConfiguration config) {
    return new ProcreationPanel(config);
  }
  
  // If there is no name then return false
  public boolean canApply(final ProcreationConfiguration model,
                          final JComponent component) {
    ProcreationPanel panel = (ProcreationPanel) component;
    return !"".equals(panel.getName());
  }
  
  // Get the name from the component and apply it to the model.
  public ProcreationConfiguration apply(final ProcreationConfiguration model,
                                        final JComponent component) {
    ProcreationPanel panel = (ProcreationPanel) component;
    model.setName(panel.getName());
    return model;
  }
}
