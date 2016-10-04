/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.UserAccount.UserAccount;

/**
 *
 * @author jayal
 */
public class LockAccountRequest extends WorkRequest {
    
    String result;
    UserAccount accounttobeLocked;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UserAccount getAccounttobeLocked() {
        return accounttobeLocked;
    }

    public void setAccounttobeLocked(UserAccount accounttobeLocked) {
        this.accounttobeLocked = accounttobeLocked;
    }
    
    
}
