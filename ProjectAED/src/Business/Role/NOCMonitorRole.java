/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;
import userInterface.MainJFrame;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;

import Business.Organization.MonitoringTeamOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.IncidentManagerRole.IncidentManagerWorkArea;
import userInterface.MonitoringTeamRole.MonitoringTeamWorkArea;

/**
 *
 * @author Jaya_L
 */
public class NOCMonitorRole extends Role {
  
    
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new MonitoringTeamWorkArea(userProcessContainer, account, (MonitoringTeamOrganization)organization, enterprise, business);
    }
     @Override
    public String toString() {
        
        return "NOC Monitor";
    } 
    
    
    
    
}
