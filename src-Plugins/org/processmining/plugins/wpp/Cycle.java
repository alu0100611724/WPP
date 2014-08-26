package org.processmining.plugins.wpp;

import java.util.ArrayList;

public class Cycle {
  
  private ArrayList<Sequence> sequences;

  public Cycle() {
    super();
    sequences = new ArrayList<Sequence>();
  }

  public ArrayList<Sequence> getSequences() {
    return sequences;
  }

  public void setSequences(ArrayList<Sequence> sequences) {
    this.sequences = sequences;
  }

  public int size() {
    return sequences.size();
  }
  
}
