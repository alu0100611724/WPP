package org.processmining.plugins.wpp.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;

import org.processmining.framework.annotations.AuthoredType;
import org.processmining.framework.annotations.Icon;

import weka.associations.GeneralizedSequentialPatterns;
import weka.core.Instances;


/**
 * @author Mauro
 * This class have the frequent sequential patterns
 */
@AuthoredType(typeName = "General Sequential Patterns",
    affiliation = "Universidad de la Laguna",
    author = "Maurizio Rendon",
    email = "mauriziorendon@gmail.com")
@Icon(icon = "./resources/resourcetype_gsp_30x35.png")
public class Gsp {
  
  private ArrayList<Cycle> cycles;
  private int numberOfCycles;
  private int numberOfFreqSeq;
  private boolean debug;
  private double support;
  private int idData;
  private String filterAttribute;
  private GeneralizedSequentialPatterns gsp;

  public Gsp(Instances instances, boolean d, double s, int i, String f) {
    setDebug(d);
    setSupport(s);
    setIdData(i);
    setFilterAttribute(f);
    setCycles(new ArrayList<Cycle>());
    setGsp(new GeneralizedSequentialPatterns());
    runAlgorith(instances);
  }

  public Gsp(Instances instances) {
    setDebug(false);
    setSupport(0.9);
    setIdData(0);
    setFilterAttribute("-1");
    setCycles(new ArrayList<Cycle>());
    setGsp(new GeneralizedSequentialPatterns());
    runAlgorith(instances);
  }
  
  protected void runAlgorith(Instances instances) {
    getGsp().setDebug(isDebug());
    getGsp().setMinSupport(getSupport());
    getGsp().setDataSeqID(getIdData());
    getGsp().setFilterAttributes(getFilterAttribute());
    try {
      getGsp().buildAssociations(instances);
    } catch (Exception e) {
      e.printStackTrace();
    }
    writeFile();
    readFile();
  }

  protected boolean isDebug() {
    return debug;
  }

  protected void setDebug(boolean debug) {
    this.debug = debug;
  }

  protected double getSupport() {
    return support;
  }

  protected void setSupport(double support) {
    this.support = support;
  }

  protected int getIdData() {
    return idData;
  }

  protected void setIdData(int idData) {
    this.idData = idData;
  }

  protected String getFilterAttribute() {
    return filterAttribute;
  }

  protected void setFilterAttribute(String filterAttribute) {
    this.filterAttribute = filterAttribute;
  }

  public void setCycles(ArrayList<Cycle> cycles) {
    this.cycles = cycles;
  }
  
  public ArrayList<Cycle> getCycles() {
    return cycles;
  }

  public Cycle getLastCycle() {
    return getCycles().get(getCycles().size() - 1);
  }
  
  public Cycle getSecondLastCycle() {
    return getCycles().get(getCycles().size() - 2);
  }
  
  public Cycle getCycleAt(int i) {
    return getCycles().get(i);
  }
  
  public int getNumberOfCycles() {
    return numberOfCycles;
  }

  public void setNumberOfCycles(int numberOfCycles) {
    this.numberOfCycles = numberOfCycles;
  }

  public int getNumberOfFreqSeq() {
    return numberOfFreqSeq;
  }

  public void setNumberOfFreqSeq(int numberOfFreqSeq) {
    this.numberOfFreqSeq = numberOfFreqSeq;
  }
  
  protected GeneralizedSequentialPatterns getGsp() {
    return gsp;
  }

  protected void setGsp(GeneralizedSequentialPatterns gsp) {
    this.gsp = gsp;
  }

  protected void writeFile(){
    FileWriter fichero = null;
    PrintWriter pw = null;
    try
    {
      File appDir = new File(System.getProperty("user.dir"));
      URI uri = new URI(appDir.toURI() + "resources/gsp.txt");  
      fichero = new FileWriter(uri.getPath().toString());
      pw = new PrintWriter(fichero);
      pw.println(getGsp().toString());

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
       try {
       // Nuevamente aprovechamos el finally para 
       // asegurarnos que se cierra el fichero.
       if (null != fichero)
          fichero.close();
       } catch (Exception e2) {
          e2.printStackTrace();
       }
    }
  }
  
  protected void readFile() {
    
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    Cycle c = null;
    
    try {
      File appDir = new File(System.getProperty("user.dir"));
      URI uri = new URI(appDir.toURI() + "resources/gsp.txt"); 
      archivo = new File (uri.getPath().toString());
      fr = new FileReader (archivo);
      br = new BufferedReader(fr);

      String linea;
      while((linea=br.readLine())!=null) {
         
         if (linea.contains("-sequences")) {
           c = new Cycle();
           getCycles().add(c);
         } else if (linea.contains("<{")) {
           Sequence s = new Sequence(linea);
           getLastCycle().getSequences().add(s);
         } else if (linea.contains("Number of cycles performed")) {
           String nCycles[] = linea.split(": ");
           setNumberOfCycles(Integer.parseInt(nCycles[1]));
         } else if (linea.contains("Total number of frequent")) {
           String nCycles[] = linea.split(": ");
           setNumberOfFreqSeq((Integer.parseInt(nCycles[1])));
         }
       }
    }
    catch(Exception e){
       e.printStackTrace();
    }finally{
       // En el finally cerramos el fichero, para asegurarnos
       // que se cierra tanto si todo va bien como si salta 
       // una excepcion.
       try{                    
          if( null != fr ){   
             fr.close();     
          }                  
       }catch (Exception e2){ 
          e2.printStackTrace();
       }
    }
  }

}
