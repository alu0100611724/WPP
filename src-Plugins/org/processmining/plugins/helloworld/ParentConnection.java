package org.processmining.plugins.helloworld;

import org.processmining.framework.connections.impl.AbstractConnection;

/**
 * Connections declaratively specify relationships between provided objects. 
 * Plug-ins can get access to a connection manager, which manages all connections, 
 * using the PluginContext. 
 * @author Mauro
 *
 */
public class ParentConnection extends AbstractConnection {
  
  /*
   *  All members of a connection must have an explicit role. The role is a string 
   *  as constant to avoid forcing users to use unchecked string.
   */
  public static final String FATHER = "father";
  public static final String MOTHER = "mother";
  public static final String CHILD = "child";
  
  public ParentConnection(Person child, Person mother) {
    super("Mother of " + child.getName() + " is " + mother.getName());
    //Each constructor defines the relevant fields using put.
    put(CHILD, child);
    put(MOTHER, mother);
  }
  
  public ParentConnection(Person child, Person father, Person mother) {
    super("Parents of " + child.getName() + " are " + father.getName() +
          " and " + mother.getName());
    put(CHILD, child);
    put(FATHER, father);
    put(MOTHER, mother);
  }
}