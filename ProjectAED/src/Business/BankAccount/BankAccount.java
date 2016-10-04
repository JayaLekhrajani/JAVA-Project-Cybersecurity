/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.BankAccount;

import Business.WorkQueue.TransferFundWorkRequest;
import java.util.ArrayList;

/**
 *
 * @author Jaya_L
 */
public class BankAccount {
    
    private int accountno;
    private String accountType;
    private int balance;
    private TransferFundWorkRequest req;
    private String openingDate;

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }
    
    
    BankAccountActivityHistory bach;
    
    
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public TransferFundWorkRequest getReq() {
        return req;
    }

    public void setReq(TransferFundWorkRequest req) {
        this.req = req;
    }
    
    
     
    public enum AccountType{
    
    SAVING("Savings"), CHECKING("Checking");
    private String value;
    private AccountType(String value)
    {
    this.value=value;
    
    }
    public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    
    }
    public BankAccount()
    {
bach=new BankAccountActivityHistory();
    
    
    }

    
    
    
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BankAccountActivityHistory getBach() {
        return bach;
    }

    public void setBach(BankAccountActivityHistory bach) {
        this.bach = bach;
    }

    
    
 
   
    
   

    public int getAccountno() {
        return accountno;
    }

    public void setAccountno(int accountno) {
        this.accountno = accountno;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
   @Override
    public String toString() {
        return "***"+String.valueOf(accountno).substring(3);
    } 
    
}
