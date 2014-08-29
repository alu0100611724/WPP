package org.processmining.plugins.wpp.implementations;

import java.util.ArrayList;
import java.util.Enumeration;

import weka.core.Instances;


public class GspConfiguration {
  private ArrayList<String> attributes;
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

  public GspConfiguration(Instances instances) {
    findAttributes(instances);
    setDebug(false);
    setSupport(0.9);
    setIdData(0);
    setFilterAttribute("-1");
  }

  protected void findAttributes(Instances instances) {
    ArrayList<String> attr = new ArrayList<String>();
    Enumeration e = instances.enumerateAttributes();
    int i = 0;
    try {
      while (i != -1) {
        String cad = e.nextElement().toString();
        String cads[] = cad.split(" ");
        attr.add(cads[1]);
        i++;
        }
    } catch (Exception exec) {
      setAttributes(attr);
    }
  }
  public ArrayList<String> getAttributes() {
    return attributes;
  }

  protected void setAttributes(ArrayList<String> attributes) {
    this.attributes = attributes;
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
