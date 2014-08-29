package org.processmining.tests.framework;

import java.io.File;
import java.net.URI;
import java.util.Enumeration;

import org.junit.Before;
import org.junit.Test;
import org.processmining.plugins.wpp.objects.Gsp;

import weka.associations.GeneralizedSequentialPatterns;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class WekaTest {

  public DataSource source;
  public GeneralizedSequentialPatterns seq;
  public Instances instances;
  
  @Before
  public void setUp() throws Exception {
    File appDir = new File(System.getProperty("user.dir"));
    URI uri = new URI(appDir.toURI() + "resources/running.arff");
    source = new DataSource(uri.getPath().toString());
    instances = source.getDataSet();
    seq = new GeneralizedSequentialPatterns();
  }

  @SuppressWarnings("unchecked")
  @Test
  public void instancesTest() {
    Enumeration e = instances.enumerateAttributes();
    int i = 0;
    try {
      while (i != -1) {
        System.out.println(e.nextElement().toString());
        i++;
        }
    } catch (Exception exec) {
      System.out.println("---------------");
      System.out.println(i);
    }
    
  }
  @Test
  public void GSPtest() throws Exception {
  
    seq.setDebug(true);
    seq.setMinSupport(0.8);
    //seq.setDataSeqID(0);
    //seq.setFilterAttributes("2");
    seq.buildAssociations(instances);
    System.out.println("1\n************************************");
    System.out.println("Atributos a Filtrar: " + seq.getFilterAttributes());
    System.out.println("Soporte Minimo: " + seq.getMinSupport());
    System.out.println("Seq Id: " + seq.getDataSeqID());
    System.out.println("************************************\n");
    
    System.out.println(seq.toString());
  }
  
  @Test
  public void GSPtest2() throws Exception {

    //seq.setDebug(true);
    String op[] = {"-D", "-S", "0.6", "-I", "0", "-F", "1"};
    seq.setOptions(op);
    seq.buildAssociations(instances);
    System.out.println("2\n************************************");
    System.out.println("Atributos a Filtrar: " + seq.getFilterAttributes());
    System.out.println("Soporte Minimo: " + seq.getMinSupport());
    System.out.println("Seq Id: " + seq.getDataSeqID());
    System.out.println("************************************");
    System.out.println(seq.getOptions()[0].toString());
    System.out.println(seq.getOptions()[1].toString());
    System.out.println(seq.getOptions()[2].toString());
    System.out.println(seq.getOptions()[3].toString());
    System.out.println("************************************\n");
    System.out.println(seq.toString());
  }
  
  @Test
  public void GSPtest3() throws Exception {

    //seq.setDebug(true);
    Gsp fsp = new Gsp(instances, false, 0.6, 0, "1");
    System.out.println("Num of cycles: " + fsp.getNumberOfCycles());
    System.out.println("Num of Seqs: " + fsp.getNumberOfFreqSeq());
    System.out.println("Seq last cycle: " + fsp.getSecondLastCycle().getSequenceAt(0).toString());
  }
  
  /*@Test
  public void CSV2ArffTest() throws Exception {
    String files[] = {"C:/Users/Mauro/Desktop/running-example.csv", 
        "C:/Users/Mauro/Desktop/running2.arff"};
    CSV2Arff.main(files);
  }*/
}
