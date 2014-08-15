/**
 * Tutorial from https://westergaard.eu/category/tutorials/prom-tutorial/
 */
package org.processmining.plugins.helloworld;

import org.processmining.framework.annotations.AuthoredType;
import org.processmining.framework.annotations.Icon;

/**
 * @author Mauro
 * Example of a simple object model that is only meant to be used in ProM 
 * Person is compose of a class Name 
 */
@AuthoredType(typeName = "Person object",
    affiliation = "University of Life",
    author = "Britney J. Spears",
    email = "britney@westergaard.eu")
@Icon(icon = "resources/resourcetype_person_30x35.png")
public class Person {
	  private Name name;
	  private int age;
	 
	  // Constructor
	  public Person() {
	    age = 0;
	  }
	 
	  // Getters and Setters
	  public Name getName() {
	    return name;
	  }
	 
	  void setName(Name name) {
	    this.name = name;
	  }
	 
	  public int getAge() {
	    return age;
	  }
	 
	  void setAge(int age) {
	    this.age = age;
	  }
}

