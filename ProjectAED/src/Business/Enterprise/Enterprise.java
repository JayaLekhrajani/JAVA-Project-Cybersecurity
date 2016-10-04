/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;

/**
 *
 * @author Jaya_L
 */
public abstract class Enterprise extends Organization {
    
    private EnterpriseType enterpriseType;
    //private String location;
    private String networkname=null;

    public String getNetworkname() {
        return networkname;
    }

    public void setNetworkname(String networkname) {
        this.networkname = networkname;
    }
    private OrganizationDirectory organizationDirectory;
   
    
    public Enterprise(String name, EnterpriseType type) {
        super(name);
        this.enterpriseType = type;
        organizationDirectory = new OrganizationDirectory();
       
    }

  /*  public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }*/
    
    public enum EnterpriseType{
        BANK("Bank"), SOCENTERPRISE("SOCEnterprise");
        
        private String value;

        private EnterpriseType(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    
    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }
    
    
    
}
