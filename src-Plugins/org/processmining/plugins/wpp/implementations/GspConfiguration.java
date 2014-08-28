package org.processmining.plugins.wpp.implementations;


public class GspConfiguration {
  private boolean debug;
  private double support;
  private int idData;
  private String filterAttribute;
  
  public GspConfiguration(double s, int i, String f) {
    setDebug(false);
    setSupport(s);
    setIdData(i);
    setFilterAttribute(f);
  }

  public GspConfiguration() {
    setDebug(false);
    setSupport(0.9);
    setIdData(0);
    setFilterAttribute("-1");
  }

  public double getSupport() {
    return support;
  }

  public void setSupport(double support) {
    this.support = support;
  }

  public int getIdData() {
    return idData;
  }

  public void setIdData(int idData) {
    this.idData = idData;
  }

  public String getFilterAttribute() {
    return filterAttribute;
  }

  public void setFilterAttribute(String filterAttribute) {
    this.filterAttribute = filterAttribute;
  }

  public boolean isDebug() {
    return debug;
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }
  
}
