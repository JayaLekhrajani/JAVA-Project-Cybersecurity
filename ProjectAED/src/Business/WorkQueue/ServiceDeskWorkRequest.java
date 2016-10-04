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
public class ServiceDeskWorkRequest extends WorkRequest {
    
    
    String criticallevel;
    String serviceDeskRequestresult;

    public String getServiceDeskRequestresult() {
        return serviceDeskRequestresult;
    }

    public void setServiceDeskRequestresult(String serviceDeskRequestresult) {
        this.serviceDeskRequestresult = serviceDeskRequestresult;
    }
    
     public String getCriticallevel() {
        return criticallevel;
    }

    public void setCriticallevel(String criticallevel) {
        this.criticallevel = criticallevel;
    }
    
}
