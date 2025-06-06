/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.AdministrativeRole;

import Business.Employee.Employee;
import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jayal
 */
public class ManageSOCEmployeeJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageSOCEmployeeJPanel
     */
    JPanel userProcessContainer;
    OrganizationDirectory orgDir;
    public ManageSOCEmployeeJPanel(JPanel userProcessContainer, OrganizationDirectory orgDir) {
        initComponents();
        this.userProcessContainer=userProcessContainer;
        this.orgDir=orgDir;
        populateOrganizationComboBox();
        populateOrganizationEmpComboBox();
    }
    void populateOrganizationComboBox()
{
organizationCombo.removeAllItems();
        
        for (Organization organization : orgDir.getOrganizationList()){
            organizationCombo.addItem(organization);
        }

}
void populateOrganizationEmpComboBox()
{
organizationEmployeeCombo.removeAllItems();
        
        for (Organization organization : orgDir.getOrganizationList()){
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        empJTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        organizationCombo = new javax.swing.JComboBox();
        organizationEmployeeCombo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        nameJText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        empJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(empJTable);
        if (empJTable.getColumnModel().getColumnCount() > 0) {
            empJTable.getColumnModel().getColumn(0).setResizable(false);
            empJTable.getColumnModel().getColumn(1).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 246, 194));

        jLabel1.setText("Select Organization");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        jLabel2.setText("Organization");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

        organizationCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        organizationCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                organizationComboActionPerformed(evt);
            }
        });
        add(organizationCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 70, -1));

        organizationEmployeeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(organizationEmployeeCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 70, -1));

        jLabel3.setText("Name");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, -1));
        add(nameJText, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 70, -1));

        jButton1.setText("Add Employee");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/home.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 60, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        Organization organization = (Organization) organizationEmployeeCombo.getSelectedItem();
        String name = nameJText.getText();
        if(nameJText.getText().length()==0)
        {
            JOptionPane.showMessageDialog(null, "Please enter the name");
            return;
        }
        
       
        
        
        
        
        
        
        
        
        organization.getEmployeeDirectory().createEmployee(name);
        populateEmployeeTable(organization);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void organizationComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_organizationComboActionPerformed
Organization organization = (Organization) organizationCombo.getSelectedItem();
        if (organization != null){
            populateEmployeeTable(organization);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_organizationComboActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable empJTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameJText;
    private javax.swing.JComboBox organizationCombo;
    private javax.swing.JComboBox organizationEmployeeCombo;
    // End of variables declaration//GEN-END:variables
}
