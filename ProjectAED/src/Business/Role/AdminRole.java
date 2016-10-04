/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.BankEnterprise;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.AdministrativeRole.AdminWorkAreaJPanel;

/**
 *
 * @author Jaya_L
 */
public class AdminRole extends Role {
    
    
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new AdminWorkAreaJPanel(userProcessContainer, (BankEnterprise)enterprise, account, business);
    }
public String toString() {
        
        return "Admin";
    } 
    
}
