package BeforeLogin;

import Customers.*;
import MySQLConnections.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
 */
public class CustomerLogin extends javax.swing.JFrame 
{
    //These Arrays hold the UserName and Cart Number before passing it to the next page
    String loggedin[] = new String [1];
    
    //Function that holds the Array Input for Passing UserName
    public void PassingUserName()
    {
        try
        {
            //Passing the Username. Insert into Array slot 0 the name of the user
            loggedin[0] = TXTUserName.getText().toUpperCase();
        
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Initializing Array to Pass UserName", "", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    //Common Variables
    String SQLCode;
    int YesorNo;
    Connection conn;
    Statement NewStatement;
    ResultSet myResultSet;
    PreparedStatement preparedStatement;
    
    /**
     * Creates new form CustomerLogin
     * All Codes in here are run as the Form is being created
    */
    public CustomerLogin() 
    {
        //Personalized Default Frame Appearance
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Set Components Invisible
        CHKBXPassword.hide();
        LBLCase1.setVisible(false);
        
    }
    
    //Method to execute Any SQL Statements that run in this Class
    public void executeSQLQuery (String query)
    {
        try
        {
            //Check MySQL Connection
            conn = MySQLConnect.ConnectDB();
            
            //Holds the SQL Statements that are run
            NewStatement = conn.createStatement();
            
            //Executes the Query
            NewStatement.executeUpdate(query);
            
        }
        
        //Error Handling the SQL Statements
        catch (SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage() + "\n\nThe SQL statement did not execute properly", "ERROR EXECUTING SQL QUERY", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    //Method to execute create table SQL Statements that run in this Class
    public void createTable(String query)
    {
        try
        {
            //Check MySQL Connection
            conn = MySQLConnect.ConnectDB();
            
            //Execute the create table query
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            
        }
        
        //Error Handling the SQL Statements
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage() + "\n\nThe SQL statement did not execute properly", "ERROR EXECUTING SQL QUERY", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    /**Default Instantiation Function
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PNL1 = new javax.swing.JPanel();
        LBLLogo = new javax.swing.JLabel();
        LBLUserName2 = new javax.swing.JLabel();
        TXTUserName = new javax.swing.JTextField();
        LBLCase1 = new javax.swing.JLabel();
        LBLPassword2 = new javax.swing.JLabel();
        TXTPassword = new javax.swing.JPasswordField();
        CHKBXPassword = new javax.swing.JCheckBox();
        BTNOK = new javax.swing.JButton();
        BTNClearAll = new javax.swing.JButton();
        BTNBack = new javax.swing.JButton();
        BTNExit = new javax.swing.JButton();
        LBLBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CUSTOMER LOGIN FORM");
        setResizable(false);

        PNL1.setLayout(null);

        LBLLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Big Bargains Store Logo.jpg"))); // NOI18N
        PNL1.add(LBLLogo);
        LBLLogo.setBounds(30, 30, 300, 300);

        LBLUserName2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLUserName2.setForeground(new java.awt.Color(255, 255, 255));
        LBLUserName2.setText("UserName: ");
        PNL1.add(LBLUserName2);
        LBLUserName2.setBounds(370, 50, 74, 18);

        TXTUserName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTUserNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTUserNameFocusLost(evt);
            }
        });
        TXTUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTUserNameActionPerformed(evt);
            }
        });
        PNL1.add(TXTUserName);
        TXTUserName.setBounds(460, 40, 200, 40);

        LBLCase1.setForeground(new java.awt.Color(204, 0, 0));
        LBLCase1.setText("Case Sensitive");
        PNL1.add(LBLCase1);
        LBLCase1.setBounds(670, 50, 110, 16);
        LBLCase1.setVisible(false);

        LBLPassword2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPassword2.setForeground(new java.awt.Color(255, 255, 255));
        LBLPassword2.setText("Password: ");
        PNL1.add(LBLPassword2);
        LBLPassword2.setBounds(370, 120, 80, 18);

        TXTPassword.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTPasswordFocusGained(evt);
            }
        });
        PNL1.add(TXTPassword);
        TXTPassword.setBounds(460, 110, 200, 40);

        CHKBXPassword.setBackground(new java.awt.Color(0, 0, 0));
        CHKBXPassword.setForeground(new java.awt.Color(255, 255, 255));
        CHKBXPassword.setText("Show Password");
        CHKBXPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHKBXPasswordActionPerformed(evt);
            }
        });
        PNL1.add(CHKBXPassword);
        CHKBXPassword.setBounds(670, 110, 120, 18);

        BTNOK.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNOK.setText("OK");
        BTNOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNOKActionPerformed(evt);
            }
        });
        PNL1.add(BTNOK);
        BTNOK.setBounds(460, 220, 200, 40);

        BTNClearAll.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNClearAll.setText("Clear All");
        BTNClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNClearAllActionPerformed(evt);
            }
        });
        PNL1.add(BTNClearAll);
        BTNClearAll.setBounds(460, 280, 200, 40);

        BTNBack.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBack.setText("Back");
        BTNBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBackActionPerformed(evt);
            }
        });
        PNL1.add(BTNBack);
        BTNBack.setBounds(30, 350, 100, 40);

        BTNExit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNExit.setText("Exit");
        BTNExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExitActionPerformed(evt);
            }
        });
        PNL1.add(BTNExit);
        BTNExit.setBounds(660, 350, 100, 40);

        LBLBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Background Photo.jpg"))); // NOI18N
        PNL1.add(LBLBackground);
        LBLBackground.setBounds(-6, -6, 930, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTUserNameActionPerformed

    private void BTNOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNOKActionPerformed
        try
        {
            //Calls the Method for checking MySQL Connection in the Class MySQLConnect. (Inheritance)
            conn = MySQLConnect.ConnectDB();

            //Holds the SQL Statement you write and checks whether MySQL is connected
            NewStatement = conn.createStatement();
            
            //Retrieve data for the user
            myResultSet = NewStatement.executeQuery("Select * from Customers where UserName = '" + TXTUserName.getText() + "' and Password = '" + TXTPassword.getText() + "';");
            
            //If Result Set returns results without error
            if (myResultSet.next())
            {
                //Show that login was confirmed
                JOptionPane.showMessageDialog(null, "\t\tLogin Successful! \nWelcome to the Big Bargains Online Store!");
                
                //In the database, show that the user logged in
                SQLCode = "insert into LoginAttempt (UserName, PasswordUsed, Date, Attempt) values ('" + TXTUserName.getText()+"', '" + TXTPassword.getText() + "', Now(), 'Successful Login');";
                executeSQLQuery(SQLCode);
                
                //Creates the table that holds the all User's shopping sessions. Permanent Record of bought items
                SQLCode = "create table if not exists `" + TXTUserName.getText() + "` (ProductBought varchar (20), DateBought DATETIME primary key, Price double (10,2), Quantity int (5), ProductPrice double(10,2), TotalPrice double(10,2));";
                createTable(SQLCode);
                
                //Creates the table that only holds the User's current shopping session. Temporary table.
                SQLCode = "create table if not exists `" + TXTUserName.getText() + "_temp` like `"+TXTUserName.getText()+"`;";
                createTable(SQLCode);
                
                //Pass the UserName and the Default CartNumber to the next page
                PassingUserName();
                int cart[] = {0};
                CustomerBuyGoods1.main(loggedin, cart);
                
                //Open the Next Page
                new CustomerBuyGoods1().setVisible(true);
                this.setVisible(false);
                
            }
            
            else
            {
                //Calls the Method for checking MySQL Connection in the Class MySQLConnect. (Inheritance)
                conn = MySQLConnect.ConnectDB();

                //Holds the SQL Statement you write and checks whether MySQL is connected
                NewStatement = conn.createStatement();

                myResultSet = NewStatement.executeQuery("Select * from AdminLogin where UserName = '" + TXTUserName.getText() + "' and Password = '" + TXTPassword.getText() + "';");
            
                //If Result Set returns results without error
                if (myResultSet.next())
                {
                    //Show that login was confirmed
                    JOptionPane.showMessageDialog(null, "   Administrator Recognized! \n Welcome to the Customer's UI");

                    //Send to the database accepted login details
                    SQLCode = "insert into LoginAttempt (UserName, PasswordUsed, Date, Attempt) values ('" + TXTUserName.getText()+"', '" + TXTPassword.getText() + "', Now(), 'Successful Login')";
                    executeSQLQuery(SQLCode);
                    
                    //Creates the table that holds the all User's shopping sessions. Permanent Record of bought items
                    SQLCode = "create table if not exists `" + TXTUserName.getText() + "` (ProductBought varchar (20), DateBought DATETIME primary key, Price double (6,2), Quantity int (5), ProductPrice double(6,2), TotalPrice double (6,2))";
                    createTable(SQLCode);

                    //Creates the table that only holds the User's current shopping session. Temporary table.
                    SQLCode = "create table if not exists `" + TXTUserName.getText() + "_temp` like `" + TXTUserName.getText() + "`";
                    createTable(SQLCode);

                    //Pass the UserName and the Default CartNumber to the next page
                    PassingUserName();
                    int cart[] = {0};
                    CustomerBuyGoods1.main(loggedin, cart);

                    //Open the Next Page
                    new CustomerBuyGoods1().setVisible(true);
                    this.setVisible(false);
                    
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Login Credentials Provided", "Access Denied", JOptionPane.ERROR_MESSAGE);
                    String SQL = "insert into LoginAttempt (UserName, PasswordUsed, Date, Attempt) values ('" + TXTUserName.getText() + "', '" + TXTPassword.getText() + "', Now(), 'Failed Login')";
                    executeSQLQuery(SQL);
                    
                }
            }
        }

        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nRestart Database and Resubmit your Credentials", "CRITICAL ERROR!", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNOKActionPerformed

    private void BTNClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNClearAllActionPerformed
        TXTUserName.setText("");
        TXTPassword.setText("");
        CHKBXPassword.setSelected(false);
        CHKBXPassword.hide();
        
    }//GEN-LAST:event_BTNClearAllActionPerformed

    private void CHKBXPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHKBXPasswordActionPerformed
        //Does not allow users to select words in the password field
        if (CHKBXPassword.isSelected())
        {
            TXTPassword.setEchoChar((char)0);
            
        }

        else
        {
            TXTPassword.setEchoChar('*');
            
        }
    }//GEN-LAST:event_CHKBXPasswordActionPerformed

    private void BTNExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNExitActionPerformed
        System.exit(0);
        
    }//GEN-LAST:event_BTNExitActionPerformed

    private void BTNBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBackActionPerformed
        new ChooseUserType().setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_BTNBackActionPerformed

    private void TXTUserNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTUserNameFocusGained
        LBLCase1.setVisible(true);
        
    }//GEN-LAST:event_TXTUserNameFocusGained

    private void TXTUserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTUserNameFocusLost
        LBLCase1.setVisible(false);
        
    }//GEN-LAST:event_TXTUserNameFocusLost

    private void TXTPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTPasswordFocusGained
        CHKBXPassword.show();
        CHKBXPassword.setOpaque(false);
        
    }//GEN-LAST:event_TXTPasswordFocusGained

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
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new CustomerLogin().setVisible(true);
                
            }
        });
    }

    //Swing Objects Declared Here
    //<editor-fold defaultstate="collapsed" desc=" Declared Variable of the Swing Objects (do not modify) ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNBack;
    private javax.swing.JButton BTNClearAll;
    private javax.swing.JButton BTNExit;
    private javax.swing.JButton BTNOK;
    private javax.swing.JCheckBox CHKBXPassword;
    private javax.swing.JLabel LBLBackground;
    private javax.swing.JLabel LBLCase1;
    private javax.swing.JLabel LBLLogo;
    private javax.swing.JLabel LBLPassword2;
    private javax.swing.JLabel LBLUserName2;
    private javax.swing.JPanel PNL1;
    private javax.swing.JPasswordField TXTPassword;
    private javax.swing.JTextField TXTUserName;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
