package org.processmining.plugins.helloworld;

import org.processmining.framework.connections.impl.AbstractStrongReferencingConnection;

/**
 * Using the AbstractStrongReferencingConnection class as base class instead of the 
 * AbstractConnection.
 * @author Mauro
 *
 */
public class GenderConnection extends AbstractStrongReferencingConnection {
  public static final String PERSON = "person";
  public static final String GENDER = "gender";
  public enum Gender { MALE, FEMALE };
  
  public GenderConnection(Person person, Gender gender) {
    super("Gender of " + person.getName().getFirst() + " is " + gender);
    put(PERSON, person);
    
    /* Using the putStrong method ensures that the object is not garbage collected and
     * makes the connection ignore it when cleaning up.
     */
    putStrong(GENDER, gender);
  }
}
