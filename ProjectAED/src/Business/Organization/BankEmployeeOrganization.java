/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.BankEmployeeRole;
import Business.Role.Role;
import java.util.ArrayList;
import Business.Customer.CustomerDirectory;
import Business.Role.BankManagerRole;

/**
 *
 * @author Jaya_L
 */
public class BankEmployeeOrganization extends Organization {
    
     public BankEmployeeOrganization() {
        super(Type.BankEmployee.getValue());
        
        
    }
   @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new BankEmployeeRole());
        roles.add(new BankManagerRole());
        return roles;
    } 
}
