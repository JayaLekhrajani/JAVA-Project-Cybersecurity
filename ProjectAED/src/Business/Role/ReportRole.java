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
//import Business.Organization.ReportingTeamOrganization;
import Business.Organization.ServiceDeskOrganization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.BankEmployeeRole.BankStaffWorkArea;
import userInterface.ReportingRole.ReportingWorkArea;

/**
 *
 * @author Jaya_L
 */
public class ReportRole extends Role {
    
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new ReportingWorkArea(userProcessContainer, account, (ServiceDeskOrganization)organization, enterprise,business);
    
    
    
}
    @Override
    public String toString() {
        
        return "Reporting Role";
    }  
    
}
