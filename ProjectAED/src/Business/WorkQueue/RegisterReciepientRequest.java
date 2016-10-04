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
public class RegisterReciepientRequest extends WorkRequest{
    
    
    private  String registerResult;
    private int reqaccountno;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReqaccountno() {
        return reqaccountno;
    }

    public void setReqaccountno(int reqaccountno) {
        this.reqaccountno = reqaccountno;
    }
    

    public String getRegisterResult() {
        return registerResult;
    }

    public void setRegisterResult(String registerResult) {
        this.registerResult = registerResult;
    }
    
    
    
}
