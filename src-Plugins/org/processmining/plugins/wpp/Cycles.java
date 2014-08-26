package org.processmining.plugins.wpp;

import java.util.ArrayList;

public class Cycles {
  
  private ArrayList<String> cycles;

  public ArrayList<String> getCycles() {
    return cycles;
  }

  public Cycles() {
    super();
    cycles =  new ArrayList<String>();
  }

  public String getCycleAt(int index) {
    return cycles.get(index);
  }
  
  public void setCycles(ArrayList<String> cycles) {
    this.cycles = cycles;
  }

  public void addCycle(String cycle) {
    cycles.add(cycle);
  }
}
