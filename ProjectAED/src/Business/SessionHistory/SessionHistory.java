/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.SessionHistory;

import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author jayal
 */
public class SessionHistory {
    
    
    ArrayList<UserSession> sessionHistoryList;
    File file;
    static int count;
    int countSh;
    
    
    
    
    public SessionHistory()
    {
    sessionHistoryList=new ArrayList<>();
    countSh=count;
    count++;
   file=new File("Session Monitoring.txt");
   
    
    
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ArrayList<UserSession> getSessionHistoryList() {
        return sessionHistoryList;
    }

    public void setSessionHistory(ArrayList<UserSession> sessionHistory) {
        this.sessionHistoryList = sessionHistory;
    }
    
    
    public UserSession createSession()
    {
    UserSession s=new UserSession();
    sessionHistoryList.add(s);
    return s;
    
    
    
    
    }
    
    
    
    
    
}
