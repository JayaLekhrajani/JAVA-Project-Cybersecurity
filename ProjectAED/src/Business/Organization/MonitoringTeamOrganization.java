/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.IncidentManagerRole;
import Business.Role.NOCMonitorRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Jaya_L
 */
public class MonitoringTeamOrganization extends Organization {
    
    
public MonitoringTeamOrganization()
    {
    
        super(Organization.Type.MontoringTeam.getValue());
    }

@Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new NOCMonitorRole());
        return roles;
    }
    
}
