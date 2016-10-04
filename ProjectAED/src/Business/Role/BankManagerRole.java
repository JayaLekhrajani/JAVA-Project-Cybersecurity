/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.BankEmployeeOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
//import userInterface.BankEmployeeRole.BankStaffWorkArea;
import userInterface.BankEmployeeRole.BankManagerWorkArea;

/**
 *
 * @author jayal
 */
public class BankManagerRole extends Role{
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new BankManagerWorkArea(userProcessContainer, account, (BankEmployeeOrganization)organization, enterprise);
    }
    
    @Override
    public String toString() {
        
        return "Bank Manager";
    }  
    
}
