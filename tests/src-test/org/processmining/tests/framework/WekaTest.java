package org.processmining.tests.framework;


import java.net.URI;

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
    source = new DataSource(
        new URI("C:/Users/Mauro/Desktop/running.arff").toString());
    instances = source.getDataSet();
    seq = new GeneralizedSequentialPatterns();
  }

  @Test
  public void GSPtest() throws Exception {
  
    seq.setDebug(true);
    seq.setMinSupport(0.7);
    seq.setDataSeqID(0);
    seq.setFilterAttributes("1");
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
    String op[] = {"-D", "-S", "0.5", "-I", "0", "-F", "2"};
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
    Gsp fsp = new Gsp(instances);
    System.out.println("Num of cycles: " + fsp.getNumberOfCycles());
    System.out.println("Num of Seqs: " + fsp.getNumberOfFreqSeq());
    System.out.println("Seq last cycle: " + fsp.getLastCycle().getSequenceAt(0).toString());
  }
  
  /*@Test
  public void CSV2ArffTest() throws Exception {
    String files[] = {"C:/Users/Mauro/Desktop/running-example.csv", 
        "C:/Users/Mauro/Desktop/running2.arff"};
    CSV2Arff.main(files);
  }*/
}
