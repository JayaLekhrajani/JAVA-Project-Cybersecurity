/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.CustomerRole;

import Business.BankAccount.BankAccount;
import Business.BankAccount.BankAccountActivity;
import Business.Customer.Customer;
import Business.Customer.RegUsers;
import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.BankEmployeeOrganization;
import Business.Organization.MonitoringTeamOrganization;
import Business.Organization.Organization;
import Business.Organization.ServiceDeskOrganization;
import Business.SessionHistory.SessionHistory;
import Business.SessionHistory.UserSession;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.RegisterReciepientRequest;
import Business.WorkQueue.SuspiciousActivityRequest;
import Business.WorkQueue.TransferFundWorkRequest;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import userInterface.MainJFrame;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;



/**
 *
 * @author jayal
 */
public class customerJFrame extends javax.swing.JFrame implements ActionListener {
 String userName;
 char[] passwordCharArray;
String password;
 Enterprise inEnterprise;
     EcoSystem system;
     DB4OUtil db4OUtil;
     Customer c;
     UserAccount ua;
      Random r=new Random();
      PrintWriter out;
        int number1;
      int transferAmount; 
     int otpRegisteruser;
     
           
    Calendar cal = Calendar.getInstance();
  UserSession s;  
  UserSession lastsession;
  UserSession startsession;
  SessionHistory sesh;
    FileWriter writer;
    
    Organization noc=null;
    long t1;
    //BufferedWriter bw;
    SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss"); 
//String start;
    /**
     * Creates new form customerJFrame
     */
    public customerJFrame(Customer c,String userName,String password, Enterprise inEnterprise, EcoSystem system, DB4OUtil db4OUtil,UserAccount ua) {
        initComponents();
        
        
       
        
        this.userName=userName;
        this.password=password;
        this.inEnterprise=inEnterprise;
        this.system=system;
        this.c=c;
        this.db4OUtil=db4OUtil;
        this.ua=ua;
        lastloggedinatlabel.setVisible(false);
        time.setVisible(false);
        strongpasswordlabel.setVisible(false);
        otpJTextField.setVisible(false);
        otpLabel.setVisible(false);
        regOTPnoJText.setVisible(false);
        Timer tp=new Timer(60000, this);
        tp.start();
         jLabel5.setText("Welcome"+ " "+ userName);
        tblAccount.getTableHeader().setVisible(false);
        number1=100000+(int)(r.nextFloat()+787854);
        populateTable();
        populateAaccountFromCombo();
        populateAccountToCombo();
        balanceJLabel.setVisible(false);
        performActivity();
        populateAccountActivityCombo();
        
        
        sesh=ua.getSh();
        s=sesh.createSession();
        
         try
       {
       
         writer=new FileWriter(sesh.getFile(),true);
          //bw=new BufferedWriter(writer);
          writer.write(System.lineSeparator());
          writer.write(System.lineSeparator());
          writer.write(" User" +ua.getUsername() + "session");
         // writer.flush();
          //writer.close();
        //out = new PrintWriter(bw);
        
       
       }
        catch(IOException e)
        {
        System.out.println("Unable to write");
        
        }
       
  


if(ua.getSh().getSessionHistoryList().size()!=1)
{
    lastsession=ua.getSh().getSessionHistoryList().get(ua.getSh().getSessionHistoryList().size()-2);
//startsession=ua.getSh().getSessionHistoryList().get(0);
lastloggedinatlabel.setVisible(true);
time.setVisible(true);
time.setText(lastsession.getEndtimeStamp());



}
 //start=sdf.format(cal.getTime());

  System.out.println(ua.getSh().getSessionHistoryList().size());
   t1=cal.getTimeInMillis();
   
  
  s.setTimestamp(sdf.format(cal.getTime()));
           
              
    
       
    }
    
    public void populateAccountActivityCombo()
    {
    accountActivityCombo.removeAllItems();
    
    for(BankAccount acc:c.getBankAccount())
    {
    accountActivityCombo.addItem(acc);
    
    
    
    }
    
    
    }

    
    public void actionPerformed(ActionEvent e)
    {
    JOptionPane.showMessageDialog(null, "Session will expire! Please refresh the page");
    //return;
    

    
    if(e.getSource()==refreshButton)
    {
   
    
    }
    if(e.getSource()!=refreshButton)
    {
    
    this.dispose();
System.exit(0);
          
    
    
    }
    }
    
    void populateAaccountFromCombo()
    {
    int p;
   accountFromComboBox.removeAllItems();
   for(BankAccount i:c.getBankAccount())
        
       
   
   
   {
  accountFromComboBox.addItem(i);
   
   }
    }
    
    void populateAccountToCombo()
    {
    int p;
    accountToJComboBox.removeAllItems();
   
   
    for(RegUsers name:c.getUsersreg())
    {
    accountToJComboBox.addItem(name.getName());
    
    
    }
    
    
    
    }
    void performActivity()
    {
        
        BankAccount selected=null;
    for(BankAccount bac:c.getBankAccount())
    {
    if(bac.getReq()!=null)
    {
    if(bac.getReq().getMessage().contains("Approved"))
    { for(BankAccountActivity bact:bac.getBach().getBankAcountHistory())
        {
            if(bact.isStat())
            {
                bac.setBalance(bac.getBalance()-bact.getWithdrawal());
                bact.setStat(false);
                populateTable();
               
                break;
            }
        
            else
            {
                

             }
        }
    
    }

    
    
    
    }
    }
    
    
    refreshTable();
    
    }
    
    void refreshTable()
    {
     int rowcount=tblAccount.getRowCount();
     for (int i = rowcount - 1; i >= 0; i--) {
            ((DefaultTableModel) tblAccount.getModel()).removeRow(i);
        }
    for(BankAccount ban:c.getBankAccount())
    {
    Object [] row= new Object[3];
    String p=String.valueOf(ban.getAccountno());
    
    row[0]=(Object)"**"+p.substring(3);
    row[1]=ban.getAccountType();
    
    row[2]=ban.getBalance();
     
    
    
    ((DefaultTableModel) tblAccount.getModel()).addRow(row);
    
    }
    
    
    }
void populateTable()
{
    DefaultTableModel model=(DefaultTableModel) tblAccount.getModel();
    model.setRowCount(0);
    
    
    for(BankAccount ban:c.getBankAccount())
    {
    Object [] row= new Object[3];
    String p=String.valueOf(ban.getAccountno());
    
    row[0]=(Object)"**"+p.substring(3);
    row[1]=ban.getAccountType();
    
    row[2]=ban.getBalance();
     
    
    
    model.addRow(row);
    
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        acountTypeJTextField = new javax.swing.JTextField();
        availableBalJTextField = new javax.swing.JTextField();
        currentBalanceJTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAccount = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        newPassword = new javax.swing.JTextField();
        securityQuestionJComboBox = new javax.swing.JComboBox<>();
        securityAnswer = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        strongpasswordlabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        otpLabel = new javax.swing.JLabel();
        regUserNameJText = new javax.swing.JTextField();
        regOTPnoJText = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        accountNoJTextField = new javax.swing.JFormattedTextField();
        accountNoErrorlabel = new javax.swing.JLabel();
        btnRegUserReq = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        accountFromComboBox = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        requestOTPbutton = new javax.swing.JButton();
        otpJTextField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        transferAmountJTextField = new javax.swing.JTextField();
        accountToJComboBox = new javax.swing.JComboBox();
        btnrequestTransfer = new javax.swing.JButton();
        balanceJLabel = new javax.swing.JLabel();
        accountBallabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bankAccountActivityJTable = new javax.swing.JTable();
        accountActivityCombo = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        emailAddressField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cvvValueField = new javax.swing.JFormattedTextField();
        AmountJTextField = new javax.swing.JFormattedTextField();
        creditCardNumberJTextField = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        lastloggedinatlabel = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logo.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("NU Bank");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 204));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Accounts");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(72, 50, 52, 14);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Available Balance");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(412, 50, 99, 14);

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(56, 82, 462, 2);

        acountTypeJTextField.setEditable(false);
        acountTypeJTextField.setBackground(new java.awt.Color(255, 255, 255));
        acountTypeJTextField.setForeground(new java.awt.Color(255, 0, 0));
        acountTypeJTextField.setBorder(null);
        jPanel2.add(acountTypeJTextField);
        acountTypeJTextField.setBounds(726, 102, 54, 14);

        availableBalJTextField.setEditable(false);
        availableBalJTextField.setBackground(new java.awt.Color(255, 255, 255));
        availableBalJTextField.setForeground(new java.awt.Color(255, 0, 0));
        availableBalJTextField.setBorder(null);
        jPanel2.add(availableBalJTextField);
        availableBalJTextField.setBounds(838, 102, 54, 14);

        currentBalanceJTextField.setEditable(false);
        currentBalanceJTextField.setBackground(new java.awt.Color(255, 255, 255));
        currentBalanceJTextField.setForeground(new java.awt.Color(255, 0, 0));
        currentBalanceJTextField.setBorder(null);
        currentBalanceJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentBalanceJTextFieldActionPerformed(evt);
            }
        });
        jPanel2.add(currentBalanceJTextField);
        currentBalanceJTextField.setBounds(1458, 102, 54, 14);

        tblAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account No", "Account Type", "Available Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAccount.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblAccount);
        if (tblAccount.getColumnModel().getColumnCount() > 0) {
            tblAccount.getColumnModel().getColumn(0).setResizable(false);
            tblAccount.getColumnModel().getColumn(1).setResizable(false);
            tblAccount.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(50, 90, 493, 166);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Account Type");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(254, 50, 77, 14);

        jTabbedPane1.addTab("Accounts", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1630, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 591, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Customer Service", jPanel5);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setText("New Password");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        progressBar.setBackground(new java.awt.Color(255, 0, 0));

        newPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                newPasswordFocusGained(evt);
            }
        });
        newPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPasswordActionPerformed(evt);
            }
        });
        newPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                newPasswordKeyTyped(evt);
            }
        });

        securityQuestionJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In which city were you born?", "What is your favourite movie?", "What is your favourite food?", " " }));
        securityQuestionJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                securityQuestionJComboBoxActionPerformed(evt);
            }
        });

        jLabel20.setText("Securtiy Question");

        jLabel21.setText("Answer");

        jLabel8.setText("Pasword Strength");

        strongpasswordlabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        strongpasswordlabel.setForeground(new java.awt.Color(51, 153, 0));
        strongpasswordlabel.setText("Strong Password");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(strongpasswordlabel)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel8))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)))
                            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(securityAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(securityQuestionJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSave))
                .addContainerGap(958, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(strongpasswordlabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(securityQuestionJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(securityAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addComponent(btnSave)
                .addContainerGap(379, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Change Password", jPanel8);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(null);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Name");
        jPanel10.add(jLabel29);
        jLabel29.setBounds(98, 85, 32, 14);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Account Number");
        jPanel10.add(jLabel30);
        jLabel30.setBounds(98, 146, 93, 14);

        otpLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        otpLabel.setText("Enter OTP");
        jPanel10.add(otpLabel);
        otpLabel.setBounds(98, 221, 55, 14);

        regUserNameJText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regUserNameJTextActionPerformed(evt);
            }
        });
        jPanel10.add(regUserNameJText);
        regUserNameJText.setBounds(277, 82, 90, 20);

        regOTPnoJText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regOTPnoJTextActionPerformed(evt);
            }
        });
        jPanel10.add(regOTPnoJText);
        regOTPnoJText.setBounds(280, 220, 90, 20);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel10.add(btnAdd);
        btnAdd.setBounds(260, 280, 51, 23);

        try {
            accountNoJTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        accountNoJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountNoJTextFieldActionPerformed(evt);
            }
        });
        accountNoJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                accountNoJTextFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                accountNoJTextFieldKeyTyped(evt);
            }
        });
        jPanel10.add(accountNoJTextField);
        accountNoJTextField.setBounds(280, 150, 90, 20);

        accountNoErrorlabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        accountNoErrorlabel.setForeground(new java.awt.Color(255, 0, 0));
        accountNoErrorlabel.setText("Not a valid Account number");
        jPanel10.add(accountNoErrorlabel);
        accountNoErrorlabel.setBounds(420, 150, 190, 15);

        btnRegUserReq.setText("Register");
        btnRegUserReq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegUserReqActionPerformed(evt);
            }
        });
        jPanel10.add(btnRegUserReq);
        btnRegUserReq.setBounds(380, 280, 73, 23);

        jTabbedPane1.addTab("Register Recepients", jPanel10);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setText("Account From");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 73, -1, -1));

        jLabel14.setText("Account To");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 125, -1, -1));

        accountFromComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        accountFromComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accountFromComboBoxMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accountFromComboBoxMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accountFromComboBoxMousePressed(evt);
            }
        });
        accountFromComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountFromComboBoxActionPerformed(evt);
            }
        });
        accountFromComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                accountFromComboBoxKeyReleased(evt);
            }
        });
        jPanel3.add(accountFromComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 70, 89, -1));

        jLabel15.setText("Transfer Amount");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 175, -1, -1));

        requestOTPbutton.setText("Request One-Time Password");
        requestOTPbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestOTPbuttonActionPerformed(evt);
            }
        });
        jPanel3.add(requestOTPbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 286, -1, -1));

        otpJTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                otpJTextFieldMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                otpJTextFieldMouseReleased(evt);
            }
        });
        otpJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otpJTextFieldActionPerformed(evt);
            }
        });
        otpJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                otpJTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                otpJTextFieldKeyReleased(evt);
            }
        });
        jPanel3.add(otpJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 228, 89, -1));

        jLabel16.setText("Please enter OTP");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 231, -1, -1));

        transferAmountJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferAmountJTextFieldActionPerformed(evt);
            }
        });
        transferAmountJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                transferAmountJTextFieldKeyTyped(evt);
            }
        });
        jPanel3.add(transferAmountJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 172, 89, -1));

        accountToJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", " " }));
        jPanel3.add(accountToJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 122, 89, -1));

        btnrequestTransfer.setText("Request for Transfer");
        btnrequestTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrequestTransferActionPerformed(evt);
            }
        });
        jPanel3.add(btnrequestTransfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 286, -1, -1));

        balanceJLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        balanceJLabel.setForeground(new java.awt.Color(102, 204, 0));
        balanceJLabel.setText("balancelabel");
        jPanel3.add(balanceJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 66, -1, -1));

        accountBallabel.setText("jLabel32");
        jPanel3.add(accountBallabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, -1, -1));

        jTabbedPane1.addTab("Transfer Funds", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(null);

        bankAccountActivityJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Activity Description", "Deposits", "Withdrawals"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(bankAccountActivityJTable);
        if (bankAccountActivityJTable.getColumnModel().getColumnCount() > 0) {
            bankAccountActivityJTable.getColumnModel().getColumn(0).setResizable(false);
            bankAccountActivityJTable.getColumnModel().getColumn(1).setResizable(false);
            bankAccountActivityJTable.getColumnModel().getColumn(2).setResizable(false);
            bankAccountActivityJTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel7.add(jScrollPane2);
        jScrollPane2.setBounds(136, 97, 414, 186);

        accountActivityCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        accountActivityCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountActivityComboActionPerformed(evt);
            }
        });
        jPanel7.add(accountActivityCombo);
        accountActivityCombo.setBounds(130, 30, 90, 20);

        jTabbedPane1.addTab("Account Details and Activities", jPanel7);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 276, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/securepaymentgateway.png"))); // NOI18N
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 356, -1));

        jLabel17.setText("Enter Credit Card Number");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 130, -1));

        jLabel18.setText("Enter CVV Value");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, -1, -1));

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, -1, -1));

        jLabel11.setText("Email Address");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 80, -1));
        jPanel4.add(emailAddressField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 140, -1));

        jLabel22.setText("Amount");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        try {
            cvvValueField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel4.add(cvvValueField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, 140, -1));

        AmountJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AmountJTextFieldKeyPressed(evt);
            }
        });
        jPanel4.add(AmountJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 140, -1));

        creditCardNumberJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                creditCardNumberJTextFieldKeyPressed(evt);
            }
        });
        jPanel4.add(creditCardNumberJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, 140, -1));

        jTabbedPane1.addTab("Bill Pay", jPanel4);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("Online Banking");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("jLabel5");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lastloggedinatlabel.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lastloggedinatlabel.setText("You last logged in at");

        time.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        time.setText("time");

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(btnLogout)
                        .addGap(29, 29, 29)
                        .addComponent(refreshButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lastloggedinatlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(btnLogout)
                            .addComponent(refreshButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastloggedinatlabel)
                    .addComponent(time))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
s.setEndtimeStamp(sdf.format(cal.getTimeInMillis()));
//ua.getSh().getSessionHistoryList().add(s);
        
        try{
    
    
    
    writer.write(System.lineSeparator() );
    writer.write("Logged out at" + "" + sdf.format(cal.getTimeInMillis()));
    
    writer.flush();
   writer.close();


}
catch(IOException e)
{}

db4OUtil.storeSystem(system);
this.dispose();
        MainJFrame mj=new MainJFrame();
        mj.setVisible(true);
        
        
       // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void currentBalanceJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentBalanceJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentBalanceJTextFieldActionPerformed

    private void requestOTPbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestOTPbuttonActionPerformed
        // TODO add your handling code here:
       try
       
       { 
           
           writer.write(System.lineSeparator());
           writer.write("Requested for OTP"+""+sdf.format(cal.getTimeInMillis()));
       }
       
       catch(IOException ex)
       
       {
       
       
       }
       
       
        if(transferAmountJTextField.getText().length()==0)
        {
        JOptionPane.showMessageDialog(null, "Please enter some amount");
        
        try
       
       { 
           
           writer.write(System.lineSeparator());
           writer.write("Did not enter any value in the amount to be transferred"+""+sdf.format(cal.getTimeInMillis()));
       }
       
       catch(IOException ex)
       
       {
       
       
       } 
        
    
        return;
   
            
            
        }
          JOptionPane.showMessageDialog(null, "OTP has ben sent to your registered number");
          
        requestOTPbutton.setEnabled(false);
        
              otpJTextField.setVisible(true);
        transferAmountJTextField.setEnabled(false);

        
        
      //  if(Integer.valueOf(transferAmountJTextField.getText())==Integer.valueOf(accountBallabel.getText())||Integer.valueOf(transferAmountJTextField.getText())>0.5*Integer.valueOf(accountBallabel.getText()))
    //out.println("Requested for a big amount"+sdf.format(cal.getTimeInMillis()));     
      otpJTextField.setVisible(true);
        transferAmountJTextField.setEnabled(false);
        transferAmount=Integer.valueOf(transferAmountJTextField.getText());

        final String username = "banknu0";
		final String password = "Lekhrajani90!";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
                
                
                try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("banknu0@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("7326667221@cingularme.com "));
			message.setSubject("A request for OTP has been generated.");
			message.setText("Dear ,"+"  " +c.getName()
				+ "\n OTP is"+""+number1);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
   
   
        
        
    }//GEN-LAST:event_requestOTPbuttonActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnrequestTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrequestTransferActionPerformed
int wrongotpentered=0;
        if(otpJTextField.getText().length()==0)
        {
            
            JOptionPane.showMessageDialog(null,"Please enter a valid value");
           return;
        
        }
        
            
            
    
        
        if(Integer.valueOf(otpJTextField.getText())!=number1)
        {
        try
        {
           writer.write(System.lineSeparator());
        writer.write("ENtered wrong OTP --Abnormal activity"+sdf.format(cal.getTimeInMillis()));
        wrongotpentered++;
        }
        
        catch(IOException e)
        {
        
        }
        }        if(wrongotpentered==3)

        {
        SuspiciousActivityRequest request1=new SuspiciousActivityRequest();
        request1.setMessage("Wrong OTP was entered more than twice");
       request1.setSender(ua);
           request1.setStatus("Sent");
           
           for (Network network3 : system.getNetworkList())
               {
                for (Enterprise enterprise3 : network3.getEnterpriseDirectory().getEnterpriseList())
                    
                {
                if(enterprise3.getEnterpriseType()==enterprise3.getEnterpriseType().SOCENTERPRISE)
                {
                
                if(enterprise3.getNetworkname().equalsIgnoreCase(inEnterprise.getNetworkname()))
                {
           
           
           
                for (Organization organization3 : enterprise3.getOrganizationDirectory().getOrganizationList()){
            if (organization3 instanceof ServiceDeskOrganization)
            
            {
                noc = organization3;
                break;
            }
            
            
                }
                
                
                if(noc!=null)
                    break;
                }
                
                
                
                
                }
                if(noc!=null)
                    break;
                
                }
               }
           
       
           
           if (noc!=null){
            noc.getWorkQueue().getWorkRequestList().add(request1);  
            }
       

        
        }
                
        
        
        
  if(Integer.valueOf(otpJTextField.getText())==number1)  {    
        BankAccountActivity bac=null;
       BankAccount selectedAcc=(BankAccount)accountFromComboBox.getSelectedItem();
      
       int bal=0;
       //BankAccount selectedAcc=null;
        
        
       bal=selectedAcc.getBalance();
       //selectedAcc=acc;
       
       String message="Req transfer for $" + String.valueOf(transferAmountJTextField.getText())+ "Current Balance is $" + String.valueOf(bal);
       
       if(Integer.valueOf(transferAmountJTextField.getText())<(0.5*bal))
       {  
           
           system.setNumberofsuccessfulevents(system.getClosedTickets()+1);
       TransferFundWorkRequest request=new TransferFundWorkRequest();
        request.setMessage(message);
        request.setSender(ua);
        request.setStatus("Sent");
        
        Organization org = null;
        for (Organization organization : inEnterprise.getOrganizationDirectory().getOrganizationList()){
            if (organization instanceof BankEmployeeOrganization){
                org = organization;
                break;
            }
        }
        
        
        if (org!=null){
            org.getWorkQueue().getWorkRequestList().add(request);
            ua.getWorkQueue().getWorkRequestList().add(request);
           // selectedAcc.setReq(request);
            
        }
        
       selectedAcc.setReq(request);
       BankAccountActivity bankAccountActivity=selectedAcc.getBach().createBankAccountActivity();
       bankAccountActivity.setStat(true);
       bankAccountActivity.setTimestamp(sdf.format(cal.getTime()));
       bankAccountActivity.setWithdrawal(transferAmount);
    } 
       
    else
       {
           
           JOptionPane.showMessageDialog(null, "You have requested for a large amount of transfer");
       system.numberofsuspiciousevents++;//suspicious activity if he tries to
       //transfer more money
        system.opentickets++;
       
       SuspiciousActivityRequest request=new SuspiciousActivityRequest();
           request.setMessage("Customer tried to transfer money more than the prtmissable limit");
           
           
 try
        {
           writer.write(System.lineSeparator());
        writer.write("Customer tried to transfer huge amount of money --Abnormal activity"+sdf.format(cal.getTimeInMillis()));
        wrongotpentered++;
        }
        
        catch(IOException e)
        {
        
        }          
           
           
           
           
           
           
           
           
           request.setSender(ua);
           request.setStatus("Sent");
           
           for (Network network2 : system.getNetworkList())
               {
                for (Enterprise enterprise1 : network2.getEnterpriseDirectory().getEnterpriseList())
                    
                {
                if(enterprise1.getEnterpriseType()==enterprise1.getEnterpriseType().SOCENTERPRISE)
                {
                
                if(enterprise1.getNetworkname().equalsIgnoreCase(inEnterprise.getNetworkname()))
                {
           
           
           
                for (Organization organization2 : enterprise1.getOrganizationDirectory().getOrganizationList()){
            if (organization2 instanceof ServiceDeskOrganization)
            
            {
                noc = organization2;
                break;
            }
            
            
                }
                
                
                if(noc!=null)
                    break;
                }
                
                
                
                
                }
                if(noc!=null)
                    break;
                
                }
               }
           
       
           
           if (noc!=null){
            noc.getWorkQueue().getWorkRequestList().add(request);  
            }
       
       
       }
       
       
       
       
    }     
    }//GEN-LAST:event_btnrequestTransferActionPerformed

    private void accountFromComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountFromComboBoxActionPerformed
        // TODO add your handling code here:
     
        
         
        
        
    }//GEN-LAST:event_accountFromComboBoxActionPerformed

    private void accountFromComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountFromComboBoxMouseClicked
      // TODO add your handling code here:
    }//GEN-LAST:event_accountFromComboBoxMouseClicked

    private void otpJTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_otpJTextFieldKeyPressed
        // TODO add your handling code here:
    
        
        
        
    }//GEN-LAST:event_otpJTextFieldKeyPressed

    private void accountFromComboBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountFromComboBoxKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_accountFromComboBoxKeyReleased

    private void otpJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otpJTextFieldActionPerformed
        // TODO add your handling code here:
        
           balanceJLabel.setVisible(true);
        
        int selectedNo=(int)accountFromComboBox.getSelectedItem();
        for (BankAccount acc:c.getBankAccount())
        {
        if(acc.getAccountno()==selectedNo)
        {
        
        balanceJLabel.setText("Account Balance is" + " " + String.valueOf(acc.getBalance()));
      
        }
        
        } 
    }//GEN-LAST:event_otpJTextFieldActionPerformed

    private void accountFromComboBoxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountFromComboBoxMousePressed
        // TODO add your handling code here:
        // balanceJLabel.setVisible(true);
BankAccount selectedNo=(BankAccount)accountFromComboBox.getSelectedItem();
        for (BankAccount acc:c.getBankAccount())
        {
        if(acc==selectedNo)
        {
        
        balanceJLabel.setText("Account Balance is" + " " + String.valueOf(acc.getBalance()));
      accountBallabel.setText(String.valueOf(acc.getBalance()));
        }
        
        }
    }//GEN-LAST:event_accountFromComboBoxMousePressed

    private void otpJTextFieldMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otpJTextFieldMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_otpJTextFieldMouseReleased

    private void otpJTextFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otpJTextFieldMouseExited
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_otpJTextFieldMouseExited

    private void accountFromComboBoxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountFromComboBoxMouseExited
        // TODO add your handling code here:
         balanceJLabel.setVisible(true);
BankAccount selectedNo=(BankAccount)accountFromComboBox.getSelectedItem();
        for (BankAccount acc:c.getBankAccount())
        {
        if(acc==selectedNo)
        {
        
        balanceJLabel.setText("Account Balance is" + " " + String.valueOf(acc.getBalance()));
      
        }
        
        }
    }//GEN-LAST:event_accountFromComboBoxMouseExited

    private void newPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newPasswordKeyTyped
        // TODO add your handling code here:
        progressBar.setValue(newPassword.getText().length());
        
    }//GEN-LAST:event_newPasswordKeyTyped

    private void newPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPasswordActionPerformed
   
                       // TODO add your handling code here:
    }//GEN-LAST:event_newPasswordActionPerformed

    private void newPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPasswordFocusGained
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_newPasswordFocusGained

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // String text=passwordTODO add your handling code here:
        String text=newPassword.getText();
        String patern="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        String securityQuestion=(String)securityQuestionJComboBox.getSelectedItem();
         progressBar.setBackground(Color.RED);
        
        //progressBar.setValue(0);
       
                    //return;
                
           
           if(text.matches(patern))
           {
               //JOptionPane.showMessageDialog(null, "Pasword is strong");
               progressBar.setValue(progressBar.getMaximum());
           progressBar.setBackground(Color.GREEN);
          strongpasswordlabel.setVisible(true);
          if(securityAnswer.getText().equals(""))
       {
       JOptionPane.showMessageDialog(null,"Please answer security question");
       
       return;
       }
       
       c.setSecurityanswer(securityAnswer.getText());
       ua.setPassword(text); 
           
       c.setSecurityQuestion(securityQuestion);
           
           }
       else
           {
           JOptionPane.showMessageDialog(null, "Weak Password, Enter a new one!");
           return;
           
           }
           
           
           
           
           
try
       {
       
         
          writer.write(System.lineSeparator());
          writer.write("Changed password --Normal Activity" +sdf.format(cal.getTimeInMillis()));
          writer.write(" User" +ua.getUsername() + "session");
          writer.flush();
          //writer.close();
        //out = new PrintWriter(bw);
        
       
       }
        catch(IOException e)
        {
        System.out.println("Unable to write");
        
        }
           
           
           
           
           
           
           
           
       
    }//GEN-LAST:event_btnSaveActionPerformed

    private void securityQuestionJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_securityQuestionJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_securityQuestionJComboBoxActionPerformed

    private void regUserNameJTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regUserNameJTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regUserNameJTextActionPerformed

    private void regOTPnoJTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regOTPnoJTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regOTPnoJTextActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
  if(regUserNameJText.getText().equals("")|| accountNoJTextField.getText().equals(""))  
      {
      JOptionPane.showMessageDialog(null, "Please enter the values in all the fields");
      try
       
       { 
           
           writer.write(System.lineSeparator());
           writer.write("Did not enter values in all the fields --AbNormal Activity"+""+sdf.format(cal.getTimeInMillis()));
       }   
        
     catch(IOException e)
     
     
       {

     }
      return;
      
     }
  
  if(!regUserNameJText.getText().equals("")&&! accountNoJTextField.getText().equals(""))
      
  {
  
  JOptionPane.showMessageDialog(null, "OTP has been sent to your registered number. Enter the value");
  
  
  
  }
        
        
        regOTPnoJText.setVisible(true);
      Random r= new Random();
      
      
  otpRegisteruser=100000+ (int)(r.nextFloat()*899900);   
        
        final String username = "banknu0";
		final String password = "Lekhrajani90!";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
                
                
                try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("banknu0@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("7326667221@cingularme.com "));
			message.setSubject("A request to register the recepient has been generated.");
			message.setText("Dear ,"+"  " +c.getName()
				+ "\n OTP is"+""+otpRegisteruser);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
        
       String regex="[a-zA-Z]+";
        try
       
       { 
           
           writer.write(System.lineSeparator());
           writer.write("Requested for Adding recepients --Normal Activity"+""+sdf.format(cal.getTimeInMillis()));
       }   
        
     catch(IOException e)
     
     
     {
     
     
     
     }
        
        String  patern=regUserNameJText.getText();
        
    
         if(!regOTPnoJText.getText().equals(otpRegisteruser))
   {
   
   JOptionPane.showMessageDialog(null, "Please enter correct OTP");
   
   
      try
       
       { 
           
           writer.write(System.lineSeparator());
           writer.write("Did not enter correct value"+""+sdf.format(cal.getTimeInMillis()));
       }   
        
     catch(IOException e)
     
     
     {
  
     }
      return;
      
   
   
   
   }
        
   if(!regUserNameJText.getText().equals("")&&!accountNoJTextField.getText().equals("")&&regOTPnoJText.getText().equals(otpRegisteruser))   
      
      
   {
   otpLabel.setVisible(true);
   regUserNameJText.setEditable(false);
   btnAdd.setEnabled(false);
   //btnAdd.setEnabled(false);
   //btnRegUserReq.setEnabled(true);
   //JOptionPane.showMessageDialog(null, "Please enter OTP value");
   
 
 
   
   }
      
  
      
      
      
      
      
      
      
      
      
      
      
      
      
      
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void otpJTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_otpJTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_otpJTextFieldKeyReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        //actionPerformed();
    }//GEN-LAST:event_formMouseMoved

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void accountNoJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountNoJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountNoJTextFieldActionPerformed

    private void accountNoJTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountNoJTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_accountNoJTextFieldKeyTyped

    private void accountNoJTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountNoJTextFieldKeyPressed
        // TODO add your handling code here:
        
        int key=evt.getKeyCode();
if((key>=evt.VK_0 && key<=evt.VK_9)||(key>=evt.VK_NUMPAD0&& key<=evt.VK_NUMPAD9)||(key==KeyEvent.VK_BACK_SPACE))
        {

            accountNoJTextField.setEditable(true);
            accountNoJTextField.setBackground(Color.WHITE);
accountNoErrorlabel.setVisible(false);

}

else
{
accountNoJTextField.setEditable(false);
accountNoErrorlabel.setVisible(true);
accountNoJTextField.setBackground(Color.red);
}
    }//GEN-LAST:event_accountNoJTextFieldKeyPressed

    private void btnRegUserReqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegUserReqActionPerformed
        // TODO add your handling code here:
        
       
    
  
        
        
     String message = "Requested to add Recepient";   
     RegisterReciepientRequest request=new RegisterReciepientRequest();
     request.setMessage(message);
     request.setReqaccountno(Integer.valueOf(accountNoJTextField.getText()));
     request.setName(regUserNameJText.getText());
        request.setSender(ua);
        request.setStatus("Sent");
        
        Organization org = null;
        for (Organization organization : inEnterprise.getOrganizationDirectory().getOrganizationList()){
            if (organization instanceof BankEmployeeOrganization){
                org = organization;
                break;
            }
        }
        if (org!=null){
            org.getWorkQueue().getWorkRequestList().add(request);
            ua.getWorkQueue().getWorkRequestList().add(request);
        }
        
        JOptionPane.showMessageDialog(null, "Request has been registered successfully");
        btnRegUserReq.setEnabled(false);
        
        
        
        
        
        
        
    }//GEN-LAST:event_btnRegUserReqActionPerformed

    private void transferAmountJTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_transferAmountJTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_transferAmountJTextFieldKeyTyped

    private void accountActivityComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountActivityComboActionPerformed
        // TODO add your handling code here:
     BankAccount selectedaccount=(BankAccount)accountActivityCombo.getSelectedItem();
     if(selectedaccount!=null){
     if(selectedaccount.getReq()!=null)
     {
         DefaultTableModel model = (DefaultTableModel)bankAccountActivityJTable.getModel();
model.setRowCount(0);

for(BankAccountActivity bac:selectedaccount.getBach().getBankAcountHistory())
{
    
    if(bac!=null){
 Object[] row = new Object[4];
 row[0]=bac.getTimestamp();
 row[1]=selectedaccount.getReq().getMessage();
 row[2]="";
 row[3]=bac.getWithdrawal();
  model.addRow(row);
 



}}
     
     
     }
        
     }     
        
        
    }//GEN-LAST:event_accountActivityComboActionPerformed

    private void transferAmountJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferAmountJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transferAmountJTextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    Organization servicedesk=null;
       int counterwrongattempts=0;
       if(creditCardNumberJTextField.getText().equals("")||cvvValueField.getText().equals("")||emailAddressField.equals("")||AmountJTextField.getText().equals(""))
       {
       JOptionPane.showMessageDialog(null, "Please enter the values in all the fields");
       return;
       
       }
        
        
if(Long.valueOf(creditCardNumberJTextField.getText())==c.getCreditcardaccountnumber() && Integer.valueOf(cvvValueField.getText())==c.getCvvno() &&
        emailAddressField.getText().equalsIgnoreCase(c.getEmailAddress()))
        {
        
            
            //JOptionPane.showMessageDialog(null, "Payment has been acknowledged");
            if(Integer.valueOf(AmountJTextField.getText())>c.getPrimaryaccount().getBalance())
            
            {
            JOptionPane.showMessageDialog(this, "Insufficient balance in your account");
            return;
            
            }
            
            else
            {
            JOptionPane.showMessageDialog(null, "Payment has been acknowledged");
            c.getPrimaryaccount().setBalance(c.getPrimaryaccount().getBalance()-Integer.valueOf(AmountJTextField.getText()));
            
            
            final String username = "banknu0";
		final String password = "Lekhrajani90!";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
                
                
                try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("banknu0@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("7326667221@cingularme.com "));
			message.setSubject("Credit Card Payment.");
			message.setText("Dear ,"+"  " +c.getName()
				+ "Payment has been done.");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
        
            
            
            
            
            
            
            
            
            
            
            
            }
            
            
            
            
        
        }


else
    JOptionPane.showMessageDialog(null, "Details entered are incorrect");
counterwrongattempts++;
system.numberofsuspiciousevents++;
if(counterwrongattempts==3)
{
    SuspiciousActivityRequest requestwrongattempt=new SuspiciousActivityRequest();
    requestwrongattempt.setMessage("Made 3 wrong attempts");
    requestwrongattempt.setCriticallevel("Medium");
    requestwrongattempt.setSender(ua);
    requestwrongattempt.setStatus("Sent");
    
           for (Network network1 : system.getNetworkList())
               {
                for (Enterprise enterprise1 : network1.getEnterpriseDirectory().getEnterpriseList())
                    
                {
                if(enterprise1.getEnterpriseType()==enterprise1.getEnterpriseType().SOCENTERPRISE)
                {
                
                if(enterprise1.getNetworkname().equalsIgnoreCase(inEnterprise.getNetworkname()))
                {
                
                
                for (Organization organization1 : enterprise1.getOrganizationDirectory().getOrganizationList()){
            if (organization1 instanceof ServiceDeskOrganization){
                servicedesk = organization1;
                break;
            }
        }
                
                
              if(servicedesk!=null)
                  break;
                }
                    
                    
                
                
                }
                
               if(servicedesk!=null)
                   break;
                
                }
          
              
              
              
               }              
              
              
            if (servicedesk!=null){
            servicedesk.getWorkQueue().getWorkRequestList().add(requestwrongattempt);  
            } 
    
    
    
    try
       
       { 
           
           writer.write(System.lineSeparator());
           writer.write("Requested for Adding recepients --Normal Activity"+""+sdf.format(cal.getTimeInMillis()));
       }   
        
     catch(IOException e)
     
     
     {
     
     
     
     }
       
    
    
    
    
    
    
    
    
    
    
    
    

           } 




return;


   
       
       
       
       
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void creditCardNumberJTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_creditCardNumberJTextFieldKeyPressed
int key=evt.getKeyCode();
if((key>=evt.VK_0 && key<=evt.VK_9)||(key>=evt.VK_NUMPAD0&& key<=evt.VK_NUMPAD9)||(key==KeyEvent.VK_BACK_SPACE))
        {

            creditCardNumberJTextField.setEditable(true);
            creditCardNumberJTextField.setBackground(Color.WHITE);


}

else
{
creditCardNumberJTextField.setEditable(false);
creditCardNumberJTextField.setBackground(Color.red);
}

        










        // TODO add your handling code here:
    }//GEN-LAST:event_creditCardNumberJTextFieldKeyPressed

    private void AmountJTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AmountJTextFieldKeyPressed
        // TODO add your handling code here:
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_AmountJTextFieldKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(customerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//newpassword.getDocument().addDocumentListener(new DocumentListener(){});
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
           
                //new customerJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField AmountJTextField;
    private javax.swing.JComboBox accountActivityCombo;
    private javax.swing.JLabel accountBallabel;
    private javax.swing.JComboBox accountFromComboBox;
    private javax.swing.JLabel accountNoErrorlabel;
    private javax.swing.JFormattedTextField accountNoJTextField;
    private javax.swing.JComboBox accountToJComboBox;
    private javax.swing.JTextField acountTypeJTextField;
    private javax.swing.JTextField availableBalJTextField;
    private javax.swing.JLabel balanceJLabel;
    private javax.swing.JTable bankAccountActivityJTable;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRegUserReq;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnrequestTransfer;
    private javax.swing.JFormattedTextField creditCardNumberJTextField;
    private javax.swing.JTextField currentBalanceJTextField;
    private javax.swing.JFormattedTextField cvvValueField;
    private javax.swing.JTextField emailAddressField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lastloggedinatlabel;
    private javax.swing.JTextField newPassword;
    private javax.swing.JTextField otpJTextField;
    private javax.swing.JLabel otpLabel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField regOTPnoJText;
    private javax.swing.JTextField regUserNameJText;
    private javax.swing.JButton requestOTPbutton;
    private javax.swing.JTextField securityAnswer;
    private javax.swing.JComboBox<String> securityQuestionJComboBox;
    private javax.swing.JLabel strongpasswordlabel;
    private javax.swing.JTable tblAccount;
    private javax.swing.JLabel time;
    private javax.swing.JTextField transferAmountJTextField;
    // End of variables declaration//GEN-END:variables
}
