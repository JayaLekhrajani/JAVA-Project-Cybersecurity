/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Customer;
import Business.BankAccount.*;
import Business.Person.Person;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jaya_L
 */
public class Customer extends Person{
   
    private int id;
    private static int count=1;
   private BankAccount primaryaccount;
    private long creditcardaccountnumber;
     int cvvno;
     

    public long getCreditcardaccountnumber() {
        return creditcardaccountnumber;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public BankAccount getPrimaryaccount() {
        return primaryaccount;
    }

    public void setPrimaryaccount(BankAccount primaryaccount) {
        this.primaryaccount = primaryaccount;
    }

    
    
    
    private String emailAddress;
   // private BankAccount bankAccount;
    // public ArrayList<Customer> custList;
     public ArrayList<BankAccount> bankAccount;
    private String contactNum;
    //private String emailid;

    public ArrayList<RegUsers> getUsersreg() {
        return usersreg;
    }

    public void setUsersreg(ArrayList<RegUsers> usersreg) {
        this.usersreg = usersreg;
    }

   
   
   public ArrayList<RegUsers> usersreg;
    
  private String securityanswer;
  private String SecurityQuestion;
 

    public String getSecurityQuestion() {
        return SecurityQuestion;
    }

    public void setSecurityQuestion(String SecurityQuestion) {
        this.SecurityQuestion = SecurityQuestion;
    }
          
  
    
    
    
public Customer()
{

id=count;
count++;
       

bankAccount=new ArrayList<>();
usersreg=new ArrayList<>();
creditcardaccountnumber=generateRandom(52);
cvvno=generateCVVRandom();


}

    public int getCvvno() {
        return cvvno;
    }
   

public int getId() {
        return id;
    }


/*public void setName(String name) {
        this.name = name;
    }

 public String getName() {
        return name;
    }*/

  

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNum() {
        return contactNum;
    }

    
@Override

public String toString() {
        return getName();
    }

   

    public ArrayList<BankAccount> getBankAccount() {
        return bankAccount;
    }

    public BankAccount addBankAccount()
            
    {
    BankAccount baccount = new BankAccount();
        bankAccount.add(baccount);
        return baccount;
    }

  

   

    public String getSecurityanswer() {
        return securityanswer;
    }

    public void setSecurityanswer(String securityanswer) {
        this.securityanswer = securityanswer;
    }

 public static long generateRandom(int prefix) {
        Random rand = new Random();

        long x = (long)(rand.nextDouble()*100000000000000L);

        String s = String.valueOf(prefix) + String.format("%014d", x);
        return Long.valueOf(s);
    }
 
 public static int generateCVVRandom()
 {
 
 Random r= new Random();
      
      
  int cvv1=r.nextInt(900) + 100;
  //String s=String.valueOf(prefix)+String.valueOf(cvv1);
  return cvv1;
 
 
 }
 
 public RegUsers createRegisteredUser()
 {
 RegUsers reg=new RegUsers();
 usersreg.add(reg); 
 return reg;
 
 
 }
}
