/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Customer.CustomerDirectory;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Jaya_L
 */
public class BankEnterprise extends Enterprise {
    public CustomerDirectory custDirectory;
   
    public BankEnterprise(String name) {
        super(name, EnterpriseType.BANK);
        custDirectory=new CustomerDirectory();
        
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }

    public CustomerDirectory getCustDirectory() {
        return custDirectory;
    }

    public void setCustDirectory(CustomerDirectory custDirectory) {
        this.custDirectory = custDirectory;
    }
    
}
