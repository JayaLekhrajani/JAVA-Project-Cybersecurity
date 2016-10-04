/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

import Business.Role.Role;
import Business.WorkQueue.WorkQueue;

/**
 *
 * @author jayal
 */
public class Person {
    
    
  private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
    
  @Override
    public String toString() {
        return this.getClass().getName();
    }
  
  
  
}
