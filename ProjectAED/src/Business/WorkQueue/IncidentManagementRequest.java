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
public class IncidentManagementRequest extends WorkRequest{
    
    int severity;
    String result;

    public int getSeverity() {
        return severity;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
    
   
    
    
}
