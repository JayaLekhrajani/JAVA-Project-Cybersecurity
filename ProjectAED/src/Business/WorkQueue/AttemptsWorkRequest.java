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
public class AttemptsWorkRequest extends WorkRequest {
    
    
    private  int atemptsResult;

    public int getAtemptsRequest() {
        return atemptsResult;
    }

    public void setRegisterResult(int atemptsResult) {
        this.atemptsResult = atemptsResult;
    }
    
    
    
}
