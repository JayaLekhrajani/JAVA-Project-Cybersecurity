/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Customer.Customer;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.BankEmployeeOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.CustomerRole.customerJFrame;

/**
 *
 * @author jayal
 */
public class CustomerRole extends Role {
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
     //  return new customerJFrame((Customer)account.getPerson(), account.getUsername(),account.getPassword(), enterprise, business,business);
    return null;
    }
    
 @Override
    public String toString() {
        
        return "Customer";
    } 
    
    
    
    
}
