/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

/**
 *
 * @author jayal
 */
public class SuspiciousActivityRequest extends WorkRequest{
    
    
     private String SuspiciousActivityRequest;
     String criticallevel;

    public String getCriticallevel() {
        return criticallevel;
    }

    public void setCriticallevel(String criticallevel) {
        this.criticallevel = criticallevel;
    }
     
     public String getSuspiciousActivityStatus() {
        return SuspiciousActivityRequest;
    }

    public void setSusupiciousActivityStatus(String SuspiciousActivityRequest) {
        this.SuspiciousActivityRequest = SuspiciousActivityRequest;
    }
    
}
