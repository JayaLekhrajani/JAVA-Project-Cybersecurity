/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.BankAccount;

import java.util.ArrayList;

/**
 *
 * @author jayal
 */
public class BankAccountActivityHistory {
    
    private ArrayList<BankAccountActivity> bankAccountHistory;

   
    
    
    public BankAccountActivityHistory()
    {
        bankAccountHistory=new ArrayList<>();
    }
    
    
     public ArrayList<BankAccountActivity> getBankAcountHistory() {
        return bankAccountHistory;
    }
     
     public BankAccountActivity createBankAccountActivity() {
        BankAccountActivity bac = new BankAccountActivity();
        bankAccountHistory.add(bac);
        return bac;
}
    
    
}
