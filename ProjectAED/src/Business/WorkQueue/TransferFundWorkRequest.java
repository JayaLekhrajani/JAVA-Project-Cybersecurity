/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

//import org.junit.runner.Request;

/**
 *
 * @author jayal
 */
public class TransferFundWorkRequest extends WorkRequest {
    
    
    
    private String transferResult;
     public String getTestResult() {
        return transferResult;
    }

    public void setTestResult(String tranferResult) {
        this.transferResult = transferResult;
    }
    
}
