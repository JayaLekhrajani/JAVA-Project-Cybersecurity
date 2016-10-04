/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import java.util.ArrayList;

/**
 *
 * @author Jaya_L
 */
public class OrganizationDirectory {
    
    private ArrayList<Organization> organizationList;
    
    public OrganizationDirectory() {
        organizationList = new ArrayList<>();
    }
    
    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    public Organization createOrganization(Organization.Type type){
        Organization organization = null;
        if (type.getValue().equals(Organization.Type.BankEmployee.getValue())){
            organization = new BankEmployeeOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Organization.Type.MontoringTeam.getValue())){
            organization = new MonitoringTeamOrganization();
            organizationList.add(organization);
        }
        
        else if (type.getValue().equals(Organization.Type.IMTeam.getValue())){
            organization = new IMTeamOrganization();
            organizationList.add(organization);
        }
        
        else if (type.getValue().equals(Organization.Type.ReportingTeam.getValue())){
            organization = new ServiceDeskOrganization();
            organizationList.add(organization);
        }
        
        else if (type.getValue().equals(Organization.Type.ServiceDesk.getValue())){
            organization = new ServiceDeskOrganization();
            organizationList.add(organization);
        }
        
        
        return organization;
    }
    
}
