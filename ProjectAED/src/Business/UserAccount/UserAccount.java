/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccount;

import Business.Employee.Employee;
import Business.Person.Person;
import Business.Role.Role;
import Business.SessionHistory.SessionHistory;
import Business.WorkQueue.WorkQueue;



/**
 *
 * @author Jaya_L
 */
public class UserAccount {
    
    
    private String username;
    private String password;
    private Person person;
   // private Employee employee;
    private Role role;
    private WorkQueue workQueue;
    private boolean flag=false;
    SessionHistory sh;

    public SessionHistory getSh() {
        return sh;
    }

    public void setSh(SessionHistory sh) {
        this.sh = sh;
    }
    
    
    public UserAccount() {
        workQueue = new WorkQueue();
        sh=new SessionHistory();
        
    }
    
     public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }
    
    
    @Override
    public String toString() {
        return username;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
}
