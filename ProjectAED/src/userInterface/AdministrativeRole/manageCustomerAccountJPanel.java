/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.AdministrativeRole;

import Business.BankAccount.BankAccount;
import Business.Customer.Customer;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Role.CustomerRole;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import static java.time.Clock.system;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jayal
 */
public class manageCustomerAccountJPanel extends javax.swing.JPanel {
JPanel userProcessContainer;
    //OrganizationDirectory organizationDirectory;
Customer c;
Enterprise e;
EcoSystem system;
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss"); 
//UserAccount uac;
    /**
     * Creates new form manageCustomerAccountJPanel
     */
    public manageCustomerAccountJPanel(JPanel userProcessContainer, Customer c, Enterprise e, EcoSystem system) {
        initComponents();
        this.userProcessContainer=userProcessContainer;
        this.c=c;
        this.e=e;
        this.system=system;
        customerNamelabel.setVisible(false);
        //this.organizationDirectory=organizationDirectory;
       // nameJText.setText(c.getName());
        for(UserAccount uac:e.getUserAccountDirectory().getUserAccountList())
        {
        if(uac.getPerson()==c)
        {
       passwordJText.setVisible(false);
       paswordLabel.setVisible(false);
       nameJText.setVisible(false);
       namelabel.setVisible(false);
        btnModify.setEnabled(true);
        customerNamelabel.setText("Customer Name: "+ c.getName());
        
        }
        }
        populateComboBox();
        
    }
      public static String md5(String input) {
         
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
void populateComboBox()
{

accountTypeJComboBox.removeAllItems();
for(BankAccount.AccountType type:BankAccount.AccountType.values())
{

accountTypeJComboBox.addItem(type);

}

}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        namelabel = new javax.swing.JLabel();
        paswordLabel = new javax.swing.JLabel();
        accountnoJText = new javax.swing.JTextField();
        passwordJText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameJText = new javax.swing.JTextField();
        balanceJText = new javax.swing.JTextField();
        accountTypeJComboBox = new javax.swing.JComboBox();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnModify = new javax.swing.JButton();
        customerNamelabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        openingDateJTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        namelabel.setText("Customer User Name");
        add(namelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        paswordLabel.setText("Password");
        add(paswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 102, -1, -1));

        accountnoJText.setEditable(false);
        add(accountnoJText, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 100, -1));
        add(passwordJText, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 99, 100, -1));

        jLabel3.setText("Account Number");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 92, -1));

        jLabel4.setText("Balance");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 77, -1));

        jLabel5.setText("Account Type");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 77, -1));
        add(nameJText, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 100, -1));

        balanceJText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                balanceJTextKeyPressed(evt);
            }
        });
        add(balanceJText, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 100, -1));

        accountTypeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        accountTypeJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountTypeJComboBoxActionPerformed(evt);
            }
        });
        add(accountTypeJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 100, -1));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 285, -1, -1));

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/home.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 60, -1));

        btnModify.setText("Modify Password");
        btnModify.setEnabled(false);
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });
        add(btnModify, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, -1, -1));

        customerNamelabel.setText("jLabel1");
        add(customerNamelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jLabel1.setText("Opening Date");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 80, -1));

        openingDateJTextField.setEnabled(false);
        add(openingDateJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 100, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String pas=passwordJText.getText();
        String username=nameJText.getText();
        
        System.out.print("Encrypted password is"+md5(pas));
        //String username=c.getName();
        
        Random r= new Random();
        //Random r1=new Random();
        Random r2=new Random();

        int number=100000+ (int)(r.nextFloat()*899900);
       
        
       
        //c.getBankAccount().setAccountno(number);
        if(balanceJText.getText().equals("") || passwordJText.getText().equals("")||nameJText.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Please enter all the fields");
            return;
        
        }
        
        
        
        
        
    if(nameJText.isEnabled() && passwordJText.isVisible())
             {
         if(!system.checkIfUsernameIsUnique(username))
   {
   JOptionPane.showMessageDialog(null, "User Name is not unique");
   return;
   
   }
         
             }
        int bal=Integer.valueOf(balanceJText.getText());
         accountnoJText.setText(String.valueOf(number));
        BankAccount bankac=new BankAccount();
        BankAccount.AccountType type=(BankAccount.AccountType)accountTypeJComboBox.getSelectedItem();
        bankac.setAccountType(String.valueOf(type));
        //bankac.setAccountType((String)accountTypeJComboBox.getSelectedItem());
        bankac.setAccountno(Integer.valueOf(accountnoJText.getText()));
        openingDateJTextField.setText(sdf.format(cal.getTimeInMillis()));
        bankac.setOpeningDate(openingDateJTextField.getText());
        bankac.setBalance(bal);
        c.getBankAccount().add(bankac);
        c.setPrimaryaccount(c.getBankAccount().get(0));
        //CustomerRole crole= new CustomerRole();
        
        
        e.getUserAccountDirectory().createUserAccount(nameJText.getText(), md5(pas), c, (Role)new CustomerRole());
     System.out.println("Credit card number is" +c.getCreditcardaccountnumber());
     System.out.println("CVV number is"+c.getCvvno());
       JOptionPane.showMessageDialog(null,"Details saved successfully");
       
       nameJText.setEnabled(false);
       passwordJText.setVisible(false);
       balanceJText.setText("");
       paswordLabel.setVisible(false);
       accountnoJText.setText("");
       openingDateJTextField.setText("");
       
        
        
        
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        // TODO add your handling code here:
        accountnoJText.setEnabled(false);
        balanceJText.setEnabled(false);
        passwordJText.setEnabled(true);
       // passwordJText.setText("");
       String p=passwordJText.getText();
        
        
        
        for(UserAccount uac:e.getUserAccountDirectory().getUserAccountList())
        {
        if(uac.getPerson()==c){
            if(uac.getPassword().equals(passwordJText.getText()))
            {
                JOptionPane.showMessageDialog(null, "New password should not be same as the last password");
                return;
            
            }
        
            if(p.length()==0)
        {
            JOptionPane.showMessageDialog(null, "Please enter the new password");
        
        return;
        
        
        }
        uac.setPassword(passwordJText.getText());
        JOptionPane.showMessageDialog(null,"Password changed sucessfully");
        btnModify.setEnabled(false);
        
        
        }
        
        }  
        
    
        
    }//GEN-LAST:event_btnModifyActionPerformed

    private void accountTypeJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountTypeJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountTypeJComboBoxActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        
        
        
        
       userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer); 
        
        
        
        
        
    }//GEN-LAST:event_btnBackActionPerformed

    private void balanceJTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_balanceJTextKeyPressed
        // TODO add your handling code here:
        
        
        int key=evt.getKeyCode();
if((key>=evt.VK_0 && key<=evt.VK_9)||(key>=evt.VK_NUMPAD0&& key<=evt.VK_NUMPAD9)||(key==KeyEvent.VK_BACK_SPACE))
        {

            balanceJText.setEditable(true);
            balanceJText.setBackground(Color.WHITE);


}

else
{
balanceJText.setEditable(false);
balanceJText.setBackground(Color.red);
}
    }//GEN-LAST:event_balanceJTextKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JComboBox accountTypeJComboBox;
    private javax.swing.JTextField accountnoJText;
    private javax.swing.JTextField balanceJText;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel customerNamelabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nameJText;
    private javax.swing.JLabel namelabel;
    private javax.swing.JTextField openingDateJTextField;
    private javax.swing.JTextField passwordJText;
    private javax.swing.JLabel paswordLabel;
    // End of variables declaration//GEN-END:variables
}