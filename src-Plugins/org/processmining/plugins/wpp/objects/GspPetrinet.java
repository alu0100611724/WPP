package org.processmining.plugins.wpp.objects;

import org.processmining.framework.annotations.AuthoredType;
import org.processmining.framework.annotations.Icon;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;

@AuthoredType(typeName = "GSP Petri net",
    affiliation = "Universidad de la Laguna",
    author = "Maurizio Rendon",
    email = "mauriziorendon@gmail.com")
@Icon(icon = "./resources/resourcetype_gsp_petri_30x35.png")
public class GspPetrinet {

  private Gsp gsp;
  private Petrinet petri;
  
  public GspPetrinet(Gsp gsp, Petrinet petri) {
    this.gsp = gsp;
    this.petri = petri;
  }

  public Gsp getGsp() {
    return gsp;
  }

  public Petrinet getPetri() {
    return petri;
  }

}
