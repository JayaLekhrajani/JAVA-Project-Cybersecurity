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
public class SOCEnterprise extends Enterprise
{
    public SOCEnterprise(String name)
    {
    
    super(name, EnterpriseType.SOCENTERPRISE);
    
    }
        
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
}
