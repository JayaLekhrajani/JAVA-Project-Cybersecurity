/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import Business.Person.Person;
import java.util.ArrayList;

/**
 *
 * @author Jaya_L
 */
public class Employee extends Person{
    
    //private String name;
    private int id;
    private static int count=1;
    private ArrayList<String> allowedList= new ArrayList<>();
    
   
    
  
     
        
    String AccountType;

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }

   
    
    
    public Employee()
    {
    id=count;
    count++;
   
    
    
    }
   
      public int getId() {
        return id;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    
   /* public String getName() {
        return name;
    }*/

    @Override
    public String toString() {
        return getName();
    }

    public ArrayList<String> getAllowedList() {
        return allowedList;
    }

    public void setAllowedList(ArrayList<String> allowedList) {
        this.allowedList = allowedList;
    }
    
   
    
}
