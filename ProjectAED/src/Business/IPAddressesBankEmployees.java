/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jayal
 */
public class IPAddressesBankEmployees {
    
    
    
    
     ArrayList<String> valListEmployees=generateRandomAdd();
      
       // System.out.println(valListEmployees);
          
          
          public boolean authenticate(String address)
          {
          
          for(String s:valListEmployees)
          {
          
          if(s.equals(address))
              return true;
          
          
          }
          
          return false;
          
          }
        
    



    
    

    



  
    
    
  public static  ArrayList<String> generateRandomAdd()
{
ArrayList<String> validList=new ArrayList<>();
for(int counter=0;counter<10;counter++)
        {
    Random rand = new Random();
    byte[] macAddr = new byte[6];
    rand.nextBytes(macAddr);
 

    macAddr[0] = (byte)(macAddr[0] & (byte)254);  //zeroing last 2 bytes to make it unicast and locally adminstrated

    StringBuilder sb = new StringBuilder(18);
    for(byte b : macAddr){

        if(sb.length() > 0)
            sb.append("-");

        sb.append(String.format("%02X", b));
        
        System.out.println( sb.toString());
    }
    validList.add(sb.toString());
    }
// validList.add("00-DB-DF-62-02-C3"); 
return validList;

}
    
    
    
    
    
    
    
    
}
