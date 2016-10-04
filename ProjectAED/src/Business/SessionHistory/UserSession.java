/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.SessionHistory;

import Business.UserAccount.UserAccount;
import static com.db4o.foundation.StopWatch.time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author jayal
 */
public class UserSession {
private String timestamp;
private String endtimeStamp;
private String usersessionid;
private static int counter;

    public String getEndtimeStamp() {
        return endtimeStamp;
    }

    public String getUsersessionid() {
        return usersessionid;
    }

    public void setUsersessionid(String usersessionid) {
        this.usersessionid = usersessionid;
    }

    public void setEndtimeStamp(String endtimeStamp) {
        this.endtimeStamp = endtimeStamp;
    }

   // private UserAccount uac;
   



public UserSession()
{ 
//file=new File("Session"+""+counter);
//uac=new UserAccount();
usersessionid=md5(String.valueOf(counter));
counter++;


}

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

  
  public static String md5(String input) {
         
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
    

   
    
}
