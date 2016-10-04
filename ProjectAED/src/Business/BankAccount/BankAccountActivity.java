/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.BankAccount;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.time.*;


/**
 *
 * @author Jaya_L
 */
public class BankAccountActivity {
    
  private String timestamp;

  
  private int deposit;
  private int withdrawal;
  private String ActivityDescription;
boolean stat=false;

    public boolean isStat() {
        return stat;
    }

    public void setStat(boolean stat) {
        this.stat = stat;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getActivityDescription() {
        return ActivityDescription;
    }

    public void setActivityDescription(String ActivityDescription) {
        this.ActivityDescription = ActivityDescription;
    }
  
    
}
