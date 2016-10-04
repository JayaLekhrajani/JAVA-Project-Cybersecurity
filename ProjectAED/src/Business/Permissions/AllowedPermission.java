/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Permissions;

/**
 *
 * @author jayal
 */
public class AllowedPermission {
    public enum PermissionType{
    READ("Read"), WRITE("Write"), SENDFILEGREATER("MORE THAN 1 GB"),SENDFILELESER("LESS THAN 1 GB");
    
    private String value;
    private PermissionType(String value)
    {
    this.value=value;
    }   
    
     public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
        
        
        
        
            
    }
    
    
    
    
    
    
}

