package org.processmining.plugins.helloworld;

public class Scolding {

  private Person victim;
  
  public Scolding(Person toScold) {
    victim = toScold;
  }
  
  public String shout() {
    return (victim.getName().getFirst() + " " + victim.getName().getLast() + 
            " go to your room");
  }
}
