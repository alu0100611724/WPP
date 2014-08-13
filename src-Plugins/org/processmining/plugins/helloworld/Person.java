/**
 * Tutorial from https://westergaard.eu/category/tutorials/prom-tutorial/
 */
package org.processmining.plugins.helloworld;

/**
 * @author Mauro
 * Example of a simple object model
 * Person is compose of a class Name 
 */
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

