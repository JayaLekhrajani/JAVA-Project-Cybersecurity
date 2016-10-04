/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.AdministrativeRole;

import Business.Customer.Customer;
import Business.Customer.CustomerDirectory;
import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Enterprise.BankEnterprise;
import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import Business.Organization.Organization.Type;
import Business.Permissions.AllowedPermission;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.SuspiciousActivityRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jaya_L
 */
public class AdminWorkAreaJPanel extends javax.swing.JPanel {
JPanel userProcessContainer;
    BankEnterprise enterprise;
    BankEnterprise benEnterprise;
   CustomerDirectory custDir;
   UserAccount account;
   EcoSystem system;
    //benEnterprise=(BankEnterprise) enterprise;
    private OrganizationDirectory organizationDirectory;
  public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);  
    
    
    /**
     * Creates new form AdminWorkAreaJPanel
     */
    public AdminWorkAreaJPanel(JPanel userProcessContainer, BankEnterprise enterprise, UserAccount userAccount, EcoSystem system) {
        initComponents();
        
        this.userProcessContainer = userProcessContainer;
        this.enterprise = (BankEnterprise)enterprise;
        this.account=userAccount;
        this.system=system;
        
        //enterprise=(BankEnterprise) enterprise;
        organizationDirectory=enterprise.getOrganizationDirectory();
        custDir=enterprise.getCustDirectory();
        
        
        
        populateTable();
        populateCombo();
        populateOrganizationComboBox();
        populateOrganizationEmpComboBox();
        popOrganizationComboBox();
        popData();
        populateCustTable();
        populatePendingRequestTable();
       // populateEmployeeAccounttypeCombo();
     
    } 
    
    void populatePendingRequestTable()
    {
    
    DefaultTableModel model = (DefaultTableModel)anomalousJTable.getModel();
model.setRowCount(0);
for(WorkRequest request : enterprise.getWorkQueue().getWorkRequestList())
{

if(request instanceof SuspiciousActivityRequest)
{

            Object[] row = new Object[4];
            row[0] = request;
            row[1] = request.getSender().getPerson().getName();
            //=(Customer)request.getSender().getPerson();
            
            row[2] = request.getReceiver() == null ? null : request.getReceiver().getPerson().getName();
            row[3] = request.getStatus();
            
            model.addRow(row);
            
            
            
        



}

}
    
    
    }

 //void populateEmployeeAccounttypeCombo()
 //{
 



 
 
 
 //}

void populateCombo()
{
organizationTypeJCombobox.removeAllItems();
        for (Type type : Organization.Type.values()){
            if (type.getValue().equals(Type.BankEmployee.getValue()))
                organizationTypeJCombobox.addItem(type);
        }    

}
boolean validate1(String s)
{

for(Organization org:organizationDirectory.getOrganizationList())
{

if(s.equals(org.getName()))
{
return true;

}

}
return false;

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


void populateTable()
{
DefaultTableModel model = (DefaultTableModel) organizationJTable.getModel();
        
        model.setRowCount(0);
        
        for (Organization organization : organizationDirectory.getOrganizationList()){
            Object[] row = new Object[2];
            row[0] = organization.getOrganizationID();
            row[1] = organization.getName();
            
            model.addRow(row);
        }
}
void populateOrganizationComboBox()
{
organizationCombo.removeAllItems();
        
        for (Organization organization : organizationDirectory.getOrganizationList()){
            organizationCombo.addItem(organization);
        }

}
void populateOrganizationEmpComboBox()
{
organizationEmployeeCombo.removeAllItems();
        
        for (Organization organization : organizationDirectory.getOrganizationList()){
            organizationEmployeeCombo.addItem(organization);
        }
}

void populateEmployeeTable(Organization organization)
{

  DefaultTableModel model = (DefaultTableModel) empJTable.getModel();
        
        model.setRowCount(0);
        
        for (Employee employee : organization.getEmployeeDirectory().getEmployeeList()){
            Object[] row = new Object[2];
            row[0] = employee.getId();
            row[1] = employee.getName();
            model.addRow(row);
        }  
    
    
}
void popOrganizationComboBox()
{
manageUserOrganizationJCombo.removeAllItems();
for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            manageUserOrganizationJCombo.addItem(organization);
        }

}

void popData()
{

DefaultTableModel model = (DefaultTableModel) manageUserJTable.getModel();

        model.setRowCount(0);

        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            for (UserAccount ua : organization.getUserAccountDirectory().getUserAccountList()) {
                Object row[] = new Object[2];
                row[0] = ua;
                row[1] = ua.getRole();
                ((DefaultTableModel) manageUserJTable.getModel()).addRow(row);
            }
        }


}

void populateCustTable()
{
    
    DefaultTableModel model = (DefaultTableModel) customerJTable.getModel();
for(Customer c: enterprise.getCustDirectory().getCustList())
{
Object row[]=new Object[3];
row[0]=c;
row[1]=c.getContactNum();
row[2]=c.getEmailAddress();
model.addRow(row);

}


}
void refreshcustTable()
{
int rowCount = customerJTable.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            ((DefaultTableModel) customerJTable.getModel()).removeRow(i);
        }
for(Customer c: enterprise.getCustDirectory().getCustList())
{
Object row[]=new Object[3];
row[0]=c;
row[1]=c.getContactNum();
row[2]=c.getEmailAddress();
((DefaultTableModel) customerJTable.getModel()).addRow(row);

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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        addCustomerbtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        customerJTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        emailAddJText = new javax.swing.JTextField();
        custAccountbutton = new javax.swing.JButton();
        contactNoJTextField = new javax.swing.JFormattedTextField();
        manageOrganizationJPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        organizationJTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        organizationTypeJCombobox = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        manageUserJPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        manageUserJTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        manageUserOrganizationJCombo = new javax.swing.JComboBox();
        manageUserEmplyeeJCombo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        manageUserRoleJCombo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        manageUserUsernameJText = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        manageUserPasswordJText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        accounttypeJCombo = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        anomalousJTable = new javax.swing.JTable();
        asigntomebtn = new javax.swing.JButton();
        workOnitbtn = new javax.swing.JButton();
        manageEmplyeeJPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        empJTable = new javax.swing.JTable();
        btnCreateEmployee = new javax.swing.JButton();
        organizationCombo = new javax.swing.JComboBox();
        organizationEmployeeCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        labelOrganization = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nameJText = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 51, 255));
        jTabbedPane1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        addCustomerbtn.setText("Add Customer");
        addCustomerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerbtnActionPerformed(evt);
            }
        });
        jPanel1.add(addCustomerbtn);
        addCustomerbtn.setBounds(170, 450, 120, 23);

        customerJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Contact Number", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(customerJTable);
        if (customerJTable.getColumnModel().getColumnCount() > 0) {
            customerJTable.getColumnModel().getColumn(0).setResizable(false);
            customerJTable.getColumnModel().getColumn(1).setResizable(false);
            customerJTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(140, 106, 330, 180);

        jLabel9.setText("Customer Name");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(157, 315, 76, 14);

        jLabel10.setText("Contact Number");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(157, 356, 78, 14);

        jLabel11.setText("Email Address");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(157, 396, 66, 14);
        jPanel1.add(nameJTextField);
        nameJTextField.setBounds(343, 312, 80, 20);
        jPanel1.add(emailAddJText);
        emailAddJText.setBounds(343, 393, 80, 20);

        custAccountbutton.setText("Manage Customer Account");
        custAccountbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custAccountbuttonActionPerformed(evt);
            }
        });
        jPanel1.add(custAccountbutton);
        custAccountbutton.setBounds(320, 450, 172, 23);

        try {
            contactNoJTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        contactNoJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contactNoJTextFieldKeyPressed(evt);
            }
        });
        jPanel1.add(contactNoJTextField);
        contactNoJTextField.setBounds(343, 350, 80, 20);

        jTabbedPane1.addTab("Manage Customers", jPanel1);

        manageOrganizationJPanel.setBackground(new java.awt.Color(255, 255, 255));
        manageOrganizationJPanel.setLayout(null);

        organizationJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Organization ID", "Organization Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(organizationJTable);
        if (organizationJTable.getColumnModel().getColumnCount() > 0) {
            organizationJTable.getColumnModel().getColumn(1).setResizable(false);
        }

        manageOrganizationJPanel.add(jScrollPane1);
        jScrollPane1.setBounds(131, 28, 352, 195);

        jLabel1.setText("Organization Type");
        manageOrganizationJPanel.add(jLabel1);
        jLabel1.setBounds(201, 285, 88, 14);

        organizationTypeJCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        organizationTypeJCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                organizationTypeJComboboxActionPerformed(evt);
            }
        });
        manageOrganizationJPanel.add(organizationTypeJCombobox);
        organizationTypeJCombobox.setBounds(317, 282, 90, 20);

        jButton1.setText("Add Organization");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        manageOrganizationJPanel.add(jButton1);
        jButton1.setBounds(250, 360, 130, 23);

        jTabbedPane1.addTab("Manage Organization", manageOrganizationJPanel);

        manageUserJPanel.setBackground(new java.awt.Color(255, 255, 255));
        manageUserJPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageUserJPanelMouseClicked(evt);
            }
        });
        manageUserJPanel.setLayout(null);

        manageUserJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Name", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(manageUserJTable);
        if (manageUserJTable.getColumnModel().getColumnCount() > 0) {
            manageUserJTable.getColumnModel().getColumn(0).setResizable(false);
            manageUserJTable.getColumnModel().getColumn(1).setResizable(false);
        }

        manageUserJPanel.add(jScrollPane3);
        jScrollPane3.setBounds(40, 50, 440, 220);

        jLabel4.setText("Organization");
        manageUserJPanel.add(jLabel4);
        jLabel4.setBounds(60, 297, 70, 14);

        manageUserOrganizationJCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        manageUserOrganizationJCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUserOrganizationJComboActionPerformed(evt);
            }
        });
        manageUserJPanel.add(manageUserOrganizationJCombo);
        manageUserOrganizationJCombo.setBounds(130, 297, 80, 20);

        manageUserEmplyeeJCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        manageUserJPanel.add(manageUserEmplyeeJCombo);
        manageUserEmplyeeJCombo.setBounds(300, 297, 100, 20);

        jLabel5.setText("Employee");
        manageUserJPanel.add(jLabel5);
        jLabel5.setBounds(240, 297, 46, 14);

        jLabel6.setText("Role");
        manageUserJPanel.add(jLabel6);
        jLabel6.setBounds(420, 300, 21, 14);

        manageUserRoleJCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        manageUserJPanel.add(manageUserRoleJCombo);
        manageUserRoleJCombo.setBounds(464, 297, 106, 20);

        jLabel7.setText("Password");
        manageUserJPanel.add(jLabel7);
        jLabel7.setBounds(240, 344, 60, 14);

        jLabel8.setText("Username");
        manageUserJPanel.add(jLabel8);
        jLabel8.setBounds(60, 344, 60, 14);
        manageUserJPanel.add(manageUserUsernameJText);
        manageUserUsernameJText.setBounds(130, 344, 80, 20);

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        manageUserJPanel.add(btnCreate);
        btnCreate.setBounds(300, 404, 73, 23);

        manageUserPasswordJText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUserPasswordJTextActionPerformed(evt);
            }
        });
        manageUserJPanel.add(manageUserPasswordJText);
        manageUserPasswordJText.setBounds(300, 344, 100, 20);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/user icon.png"))); // NOI18N
        manageUserJPanel.add(jLabel12);
        jLabel12.setBounds(540, 0, 140, 90);

        jLabel13.setText("Account");
        manageUserJPanel.add(jLabel13);
        jLabel13.setBounds(420, 347, 40, 14);

        accounttypeJCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Business", "Regular" }));
        accounttypeJCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accounttypeJComboActionPerformed(evt);
            }
        });
        manageUserJPanel.add(accounttypeJCombo);
        accounttypeJCombo.setBounds(464, 344, 106, 20);

        jTabbedPane1.addTab("Manage User", manageUserJPanel);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        anomalousJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Suspicious Account", "Sender", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(anomalousJTable);
        if (anomalousJTable.getColumnModel().getColumnCount() > 0) {
            anomalousJTable.getColumnModel().getColumn(0).setResizable(false);
            anomalousJTable.getColumnModel().getColumn(1).setResizable(false);
            anomalousJTable.getColumnModel().getColumn(2).setResizable(false);
            anomalousJTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel2.add(jScrollPane5);
        jScrollPane5.setBounds(119, 53, 410, 281);

        asigntomebtn.setText("Assign to Me");
        asigntomebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asigntomebtnActionPerformed(evt);
            }
        });
        jPanel2.add(asigntomebtn);
        asigntomebtn.setBounds(235, 379, 93, 23);

        workOnitbtn.setText("Work on it");
        workOnitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workOnitbtnActionPerformed(evt);
            }
        });
        jPanel2.add(workOnitbtn);
        workOnitbtn.setBounds(375, 379, 90, 23);

        jTabbedPane1.addTab("Pending Requests", jPanel2);

        manageEmplyeeJPanel.setBackground(new java.awt.Color(255, 255, 255));
        manageEmplyeeJPanel.setLayout(null);

        empJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(empJTable);
        if (empJTable.getColumnModel().getColumnCount() > 0) {
            empJTable.getColumnModel().getColumn(0).setResizable(false);
            empJTable.getColumnModel().getColumn(1).setResizable(false);
        }

        manageEmplyeeJPanel.add(jScrollPane2);
        jScrollPane2.setBounds(100, 130, 440, 110);

        btnCreateEmployee.setText("Create Employee");
        btnCreateEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateEmployeeActionPerformed(evt);
            }
        });
        manageEmplyeeJPanel.add(btnCreateEmployee);
        btnCreateEmployee.setBounds(230, 390, 130, 20);

        organizationCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        organizationCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                organizationComboActionPerformed(evt);
            }
        });
        manageEmplyeeJPanel.add(organizationCombo);
        organizationCombo.setBounds(250, 60, 90, 20);

        organizationEmployeeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        manageEmplyeeJPanel.add(organizationEmployeeCombo);
        organizationEmployeeCombo.setBounds(250, 280, 100, 20);

        jLabel2.setText("Organization");
        manageEmplyeeJPanel.add(jLabel2);
        jLabel2.setBounds(100, 280, 70, 14);

        labelOrganization.setText("Organization");
        manageEmplyeeJPanel.add(labelOrganization);
        labelOrganization.setBounds(100, 60, 70, 14);

        jLabel3.setText("Name");
        manageEmplyeeJPanel.add(jLabel3);
        jLabel3.setBounds(100, 330, 27, 14);

        nameJText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 3, true));
        manageEmplyeeJPanel.add(nameJText);
        nameJText.setBounds(250, 340, 100, 20);

        jTabbedPane1.addTab("Manage Employee", manageEmplyeeJPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void organizationTypeJComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_organizationTypeJComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_organizationTypeJComboboxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        Type type = (Type) organizationTypeJCombobox.getSelectedItem();
        //organizationDirectory.createOrganization(type);
        
        if( !validate1(type.getValue()))        
      {organizationDirectory.createOrganization(type);
        populateTable();
      }
      
      else
      {
      JOptionPane.showMessageDialog(null, "Organization has already been added");
      return;
      
      }
        
        
        
       // populateTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        populateOrganizationComboBox();
        populateOrganizationEmpComboBox();
        popOrganizationComboBox();
        popData();
       
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void organizationComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_organizationComboActionPerformed
        // TODO add your handling code here:
        
     Organization organization = (Organization) organizationCombo.getSelectedItem();
        if (organization != null){
            populateEmployeeTable(organization);
        }   
        
        
    }//GEN-LAST:event_organizationComboActionPerformed

    private void btnCreateEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateEmployeeActionPerformed
        // TODO add your handling code here:
        
        Organization organization = (Organization) organizationEmployeeCombo.getSelectedItem();
        String name = nameJText.getText();
        if(nameJText.getText().length()==0)
        {
            JOptionPane.showMessageDialog(null, "Please enter the name");
            return;
        }
        
        organization.getEmployeeDirectory().createEmployee(name);
        JOptionPane.showMessageDialog(null, "Employee has been created successfully");
        nameJText.setText("");
        populateEmployeeTable(organization);
    }//GEN-LAST:event_btnCreateEmployeeActionPerformed

    private void manageUserOrganizationJComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUserOrganizationJComboActionPerformed
        // TODO add your handling code here:
        
      Organization organization = (Organization) manageUserOrganizationJCombo.getSelectedItem();
        if (organization != null){
            populateEmployeeComboBox(organization);
            populateRoleComboBox(organization);
        }  
        
    }//GEN-LAST:event_manageUserOrganizationJComboActionPerformed

    private void manageUserJPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageUserJPanelMouseClicked
        // TODO add your handling code here:
        popOrganizationComboBox();
        popData();
    }//GEN-LAST:event_manageUserJPanelMouseClicked

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        ArrayList<String> sent=new ArrayList<>();
        String userName = manageUserUsernameJText.getText();
        String password = manageUserPasswordJText.getText();
        
   if(userName.equals("")||password.equals(""))
       
   {
   
   JOptionPane.showMessageDialog(null, "Please enter username and password");
   return;
   
   
   }
   
   
   if(!system.checkIfUsernameIsUnique(userName))
   {
   JOptionPane.showMessageDialog(null, "User Name is not unique");
   return;
   
   }
   //if(!system.checkIfUsernameIsUnique(userName))
   
   
   
        
        String typeofAccount=(String)accounttypeJCombo.getSelectedItem();
        String password1=md5(password);
        System.out.println("encrypted password is"+ password1);
        Organization organization = (Organization) manageUserOrganizationJCombo.getSelectedItem();
        Employee employee = (Employee) manageUserEmplyeeJCombo.getSelectedItem();
        employee.setAccountType(typeofAccount);
    if(typeofAccount.equalsIgnoreCase("Business"))
    {
    
  sent.add(AllowedPermission.PermissionType.READ.getValue());
  sent.add(AllowedPermission.PermissionType.WRITE.getValue());
  sent.add(AllowedPermission.PermissionType.SENDFILEGREATER.getValue());
    employee.setAllowedList(sent);
    }
    if(typeofAccount.equalsIgnoreCase("Regular"))
    {
    
    sent.add(AllowedPermission.PermissionType.SENDFILELESER.getValue());
   
    employee.setAllowedList(sent);
    
    }
    
        Role role = (Role) manageUserRoleJCombo.getSelectedItem();
        
        JOptionPane.showMessageDialog(null, "Details have been added");
        organization.getUserAccountDirectory().createUserAccount(userName, password, employee, role);
       // organization.getUserAccountDirectory().getUserAccountList().add(ac); 
       
       manageUserUsernameJText.setText("");
       manageUserPasswordJText.setText("");
       
        popData();
        
        
    }//GEN-LAST:event_btnCreateActionPerformed

    private void addCustomerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerbtnActionPerformed
        // TODO add your handling code here:
        
        Customer c=new Customer();
        String a=nameJTextField.getText();
//        int b=Integer.valueOf(contactNoJTextField.getText());
        String e=emailAddJText.getText();
        
        
        
       
        if(nameJTextField.getText().equals("")||contactNoJTextField.getText().equals("")||emailAddJText.getText().equals(""))
        
        {
        
        JOptionPane.showMessageDialog(null, "Please enter the values in all the fields");
        return;
        
        }
        
        if(!validate(emailAddJText.getText()))
        {
        JOptionPane.showMessageDialog(null, "Please enter a valid emailaddress");
        return;
        
        }
        c.setContactNum(contactNoJTextField.getText());
        c.setName(a);
        c.setEmailAddress(e);
      
        custDir.getCustList().add(c);
          JOptionPane.showMessageDialog(null, "Details have been added successfully");
          nameJTextField.setText("");
          emailAddJText.setText("");
          contactNoJTextField.setText("");
          
        refreshcustTable();
        
        
        
    }//GEN-LAST:event_addCustomerbtnActionPerformed

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
}
    
    
    
    
    private void custAccountbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custAccountbuttonActionPerformed
        // TODO add your handling code here:
        int selectedRow = customerJTable.getSelectedRow();
        Customer selectedCustomer;
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Select a row", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
          selectedCustomer=(Customer) customerJTable.getValueAt(selectedRow, 0);
        
        }
        
        manageCustomerAccountJPanel jpanel= new manageCustomerAccountJPanel(userProcessContainer,selectedCustomer,enterprise, system);
        userProcessContainer.add("CustAccount",jpanel);
        CardLayout layout= (CardLayout) userProcessContainer.getLayout();
     layout.next(userProcessContainer);
    }//GEN-LAST:event_custAccountbuttonActionPerformed

    private void contactNoJTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactNoJTextFieldKeyPressed
        // TODO add your handling code here:
        int key=evt.getKeyCode();
if((key>=evt.VK_0 && key<=evt.VK_9)||(key>=evt.VK_NUMPAD0&& key<=evt.VK_NUMPAD9)||(key==KeyEvent.VK_BACK_SPACE))
        {

            contactNoJTextField.setEditable(true);
            contactNoJTextField.setBackground(Color.WHITE);


}

else
{
contactNoJTextField.setEditable(false);
contactNoJTextField.setBackground(Color.red);
}

        
        
        
    }//GEN-LAST:event_contactNoJTextFieldKeyPressed

    private void accounttypeJComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accounttypeJComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accounttypeJComboActionPerformed

    private void asigntomebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asigntomebtnActionPerformed
        // TODO add your handling code here:
        
         int selectedRow = anomalousJTable.getSelectedRow();
       if (selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a request");
            return;
        }
       
          WorkRequest request1 = (WorkRequest)anomalousJTable.getValueAt(selectedRow, 0);
          
          if(request1.getStatus().equalsIgnoreCase("Completed"))
          {
          JOptionPane.showMessageDialog(null, "Work Completed. Select some other request");
          return;
          
          
          
          }
          
          if(request1.getStatus().equalsIgnoreCase("Pending"))
          {
          JOptionPane.showMessageDialog(null, "Work is Pending. can start working on it");
          return;
          
          
          
          }
          
          
          
          
          
          
          
          
          
          
        request1.setReceiver(account);
        
        request1.setStatus("Pending");
        populatePendingRequestTable();;
        
        
        
        
    }//GEN-LAST:event_asigntomebtnActionPerformed

    private void workOnitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workOnitbtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = anomalousJTable.getSelectedRow();
       if (selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a request");
            return;
        }
       
     SuspiciousActivityRequest request1 = (SuspiciousActivityRequest)anomalousJTable.getValueAt(selectedRow, 0);  

  if(request1.getStatus().equalsIgnoreCase("Completed"))
          {
          JOptionPane.showMessageDialog(null, "Work Completed. Select some other request");
          return;
          
          
          
          }
             
      if(!request1.getStatus().equalsIgnoreCase("Pending"))
          {
          JOptionPane.showMessageDialog(null, "Asign the request to yourself");
          return;
          
          
          
          }
     
     
             
     
     
      UserAccount uatobeLocked=request1.getSender();
       request1.setStatus("Completed");
       populatePendingRequestTable();
       system.opentickets--;
       system.closedTickets++;
       
       
       request1.setMessage("Account locked");
       uatobeLocked.setFlag(true);
       
    }//GEN-LAST:event_workOnitbtnActionPerformed

    private void manageUserPasswordJTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUserPasswordJTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manageUserPasswordJTextActionPerformed
void populateEmployeeComboBox(Organization organization)
{
  manageUserEmplyeeJCombo.removeAllItems();
        
        for (Employee employee : organization.getEmployeeDirectory().getEmployeeList()){
            manageUserEmplyeeJCombo.addItem(employee);
        }  
    
    
}

void populateRoleComboBox(Organization organization)
{
manageUserRoleJCombo.removeAllItems();
        for (Role role : organization.getSupportedRole()){
           
            
            
            
                       manageUserRoleJCombo.addItem(role);
        }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox accounttypeJCombo;
    private javax.swing.JButton addCustomerbtn;
    private javax.swing.JTable anomalousJTable;
    private javax.swing.JButton asigntomebtn;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnCreateEmployee;
    private javax.swing.JFormattedTextField contactNoJTextField;
    private javax.swing.JButton custAccountbutton;
    private javax.swing.JTable customerJTable;
    private javax.swing.JTextField emailAddJText;
    private javax.swing.JTable empJTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelOrganization;
    private javax.swing.JPanel manageEmplyeeJPanel;
    private javax.swing.JPanel manageOrganizationJPanel;
    private javax.swing.JComboBox manageUserEmplyeeJCombo;
    private javax.swing.JPanel manageUserJPanel;
    private javax.swing.JTable manageUserJTable;
    private javax.swing.JComboBox manageUserOrganizationJCombo;
    private javax.swing.JTextField manageUserPasswordJText;
    private javax.swing.JComboBox manageUserRoleJCombo;
    private javax.swing.JTextField manageUserUsernameJText;
    private javax.swing.JTextField nameJText;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JComboBox organizationCombo;
    private javax.swing.JComboBox organizationEmployeeCombo;
    private javax.swing.JTable organizationJTable;
    private javax.swing.JComboBox organizationTypeJCombobox;
    private javax.swing.JButton workOnitbtn;
    // End of variables declaration//GEN-END:variables
}
