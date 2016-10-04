/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.BankEmployeeRole;
import Business.Role.ReportRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author jayal
 */
public class ServiceDeskOrganization extends Organization{
    
    public ServiceDeskOrganization() {
        super(Type.ServiceDesk.getValue());
    }
   @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new ReportRole());
        return roles;
    } 
    
}
