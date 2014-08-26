package org.processmining.plugins.wpp;

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
@AuthoredType(typeName = "Frequent Sequential Patterns",
    affiliation = "Universidad de la Laguna",
    author = "Maurizio Rendon",
    email = "mauriziorendon@gmail.com")
@Icon(icon = "./resources/resourcetype_freq_sec_30x35.png")
public class FrecSeqPatterns {
  private ArrayList<Cycles> fsp;
  private int NumberOfCycles;
  private int NumberOfFreqSeq;

  public FrecSeqPatterns(Instances instances) throws Exception {
    GeneralizedSequentialPatterns seq = new GeneralizedSequentialPatterns();
    seq.buildAssociations(instances);
    fsp = new ArrayList<Cycles>();
    writeFile(seq);
    readFile();
  }

  public ArrayList<Cycles> getFsp() {
    return fsp;
  }

  public void setFsp(ArrayList<Cycles> fsp) {
    this.fsp = fsp;
  }

  public int getNumberOfCycles() {
    return NumberOfCycles;
  }

  public void setNumberOfCycles(int numberOfCycles) {
    NumberOfCycles = numberOfCycles;
  }

  public int getNumberOfFreqSeq() {
    return NumberOfFreqSeq;
  }

  public void setNumberOfFreqSeq(int numberOfFreqSeq) {
    NumberOfFreqSeq = numberOfFreqSeq;
  }
  
  public Cycles getLast() {
    return fsp.get(NumberOfCycles-1);
  }
  
  public Cycles get(int index) {
    return fsp.get(index);
  }
  
  protected void writeFile(GeneralizedSequentialPatterns seq){
    FileWriter fichero = null;
    PrintWriter pw = null;
    try
    {
        fichero = new FileWriter(new URI("C:/Users/Mauro/Desktop/p.txt").toString());
        pw = new PrintWriter(fichero);
        pw.println(seq.toString());

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

    try {
       // Apertura del fichero y creacion de BufferedReader para poder
       // hacer una lectura comoda (disponer del metodo readLine()).
       archivo = new File (new URI("C:/Users/Mauro/Desktop/p.txt").toString());
       fr = new FileReader (archivo);
       br = new BufferedReader(fr);

       // Lectura del fichero
       String linea;
       while((linea=br.readLine())!=null) {
         int contSeq = 0;
         if (linea.contains("-sequences")) {
           fsp.add(new Cycles());
           contSeq++;
         } else if (linea.contains("<{")) {
           fsp.get(contSeq).addCycle(linea);
         }
         if (linea.contains("Number of cycles performed")) {
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
