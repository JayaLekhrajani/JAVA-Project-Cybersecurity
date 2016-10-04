/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.Role.SystemAdminRole;
import java.util.ArrayList;

/**
 *
 * @author Jaya_L
 */
public class EcoSystem extends Organization{
    private static EcoSystem business;
    private ArrayList<Network> networkList;
    public int opentickets=0;
    public int closedTickets=0;
    public int criticaltickets=0;
    public int hightickets=0;
    public int mediumtickets=0;
    public int networksuspiciousevents=0;
    public  int assignedtickets=0;

    public int getAssignedtickets() {
        return assignedtickets;
    }

    public void setAssignedtickets(int assignedtickets) {
        this.assignedtickets = assignedtickets;
    }
    

    public int getNetworksuspiciousevents() {
        return networksuspiciousevents;
    }

    public void setNetworksuspiciousevents(int networksuspiciousevents) {
        this.networksuspiciousevents = networksuspiciousevents;
    }

    public int getCriticaltickets() {
        return criticaltickets;
    }

    public void setCriticaltickets(int criticaltickets) {
        this.criticaltickets = criticaltickets;
    }

    public int getHightickets() {
        return hightickets;
    }

    public void setHightickets(int hightickets) {
        this.hightickets = hightickets;
    }

    public int getMediumtickets() {
        return mediumtickets;
    }

    public void setMediumtickets(int mediumtickets) {
        this.mediumtickets = mediumtickets;
    }
    
    

    public int getOpentickets() {
        return opentickets;
    }

    public void setOpentickets(int opentickets) {
        this.opentickets = opentickets;
    }

    public int getClosedTickets() {
        return closedTickets;
    }

    public void setClosedTickets(int closedTickets) {
        this.closedTickets = closedTickets;
    }
    
    
  
  public IPAddresses validIPlist=new IPAddresses();
  public IPAddressesBankEmployees validIPlistEmployees=new IPAddressesBankEmployees();
  public int numberofsuspiciousevents=0;
  public int alarms=0;

    public int getAlarms() {
        return alarms;
    }

    public IPAddressesBankEmployees getValidIPlistEmployees() {
        return validIPlistEmployees;
    }

    public void setValidIPlistEmployees(IPAddressesBankEmployees validIPlistEmployees) {
        this.validIPlistEmployees = validIPlistEmployees;
    }

    public void setAlarms(int alarms) {
        this.alarms = alarms;
    }
  
  public int numberofsuccessfulevents=0;

    public  IPAddresses getValidIPlist() {
        return validIPlist;
    }

    public  void setValidIPlist(IPAddresses validIPlist) {
        this.validIPlist = validIPlist;
    }

    public int getNumberofsuspiciousevents() {
        return numberofsuspiciousevents;
    }

    public void setNumberofsuspiciousevents(int numberofsuspiciousevents) {
        this.numberofsuspiciousevents = numberofsuspiciousevents;
    }
  public int numberofunsuccessfulevents=0;

    public int getNumberofsuccessfulevents() {
        return numberofsuccessfulevents;
    }

    public void setNumberofsuccessfulevents(int numberofsuccessfulevents) {
        this.numberofsuccessfulevents = numberofsuccessfulevents;
    }

    public int getNumberofunsuccessfulevents() {
        return numberofunsuccessfulevents;
    }

    public void setNumberofunsuccessfulevents(int numberofunsuccessfulevents) {
        this.numberofunsuccessfulevents = numberofunsuccessfulevents;
    }
  
  
  
   
    
   public static EcoSystem getInstance() { 
// singleton pattern emerges when the singleton-ness of the object is baked into its definition,
        //typically by providing a function to retrieve the sole instance  
        //and making the constructor accessible to only this function
        if (business == null) {
            business = new EcoSystem();
        }
        return business;
    } 
   private EcoSystem() {
        super(null);
        networkList = new ArrayList<>();
    } 
   
   public ArrayList<Network> getNetworkList() {
        return networkList;
    }
  
   public Network createAndAddNetwork() {
        Network network = new Network();
        networkList.add(network);
        return network;
    }
   
   @Override
    public ArrayList<Role> getSupportedRole() 
    {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new SystemAdminRole());
        return roleList;
    }
   
    
   public boolean checkIfUsernameIsUnique(String username) {

        if (!this.getUserAccountDirectory().checkIfUsernameIsUnique(username)) {
            return false;
        }

        for (Network network : networkList) {
            
            
            
            for(Enterprise e:network.getEnterpriseDirectory().getEnterpriseList())
            {
            
            
            if(!e.getUserAccountDirectory().checkIfUsernameIsUnique(username))
            {
            
            return false;
            }
            
            }
            
            
            
        }
        
        
    for (Network network : networkList) {
            
            
            
            for(Enterprise e:network.getEnterpriseDirectory().getEnterpriseList())
            {
            
            for(Organization org:e.getOrganizationDirectory().getOrganizationList())
            {
            if(!org.getUserAccountDirectory().checkIfUsernameIsUnique(username))
            {
            
            return false;
            }
            
            }
            
            }
            
        }    
        
        
        
        

        return true;
    } 
    
    
    
    
}
