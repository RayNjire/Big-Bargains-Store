package Admin;

import BeforeLogin.*;
import MySQLConnections.MySQLConnect;
import java.sql.*;
import javax.swing.*;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
 */
public class NewAdmin extends javax.swing.JFrame
{
    //This variables ACCEPT passed data for UserName. 
    public static String LoggedInUser;
    public static int ItemsInCart;
    
    //These Arrays hold the UserName and Cart Number before passing it to the next page
    String loggedin[] = new String [1];
    int cart[] = new int [1];
    
    //Function that holds the Array Input for Passing UserName
    public void PassingUserName()
    {
        //Passing the Username. Insert into Array slot 0 the name of the user
        loggedin[0] = LBLMSG.getText();
        
    }
    
    //Function that holds the Array Input for Passing Cart Number
    public void PassingCartNumber()
    {
        //Passing the CartNumber. Insert into Array slot 0 the name of the user
        cart[0] = ItemsInCart;
        
    }
    
    //Common Variables
    Connection conn;
    String SQLCode;
    PreparedStatement preparedStatement;
    int YesorNo;
    Statement NewStatement;
            
    /**
     * Creates new form NewAdmin
     * All Codes in here are run as the Form is being created
    */
    public NewAdmin()
    {
        //Personalized Default Frame Appearance
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Hidden Components
        CHKBXPassword.hide();
        
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
    
    //This function runs as the user logs out or exits the system
    public void CloseShoppingSession()
    {
        try
        {
            //In the database, show that the user logged out
            SQLCode = "insert into LoginAttempt (UserName, Date, Attempt) values ('" + LBLMSG.getText() + "', Now(), 'Logged Out');";

            //Call the executeSQL query method. Execute the SQL Query
            executeSQLQuery(SQLCode);

            //Drop the User's temp table from the database. (This table holds the current shopping session)
            SQLCode = "drop table if exists `" + LBLMSG.getText() + "_temp`;";

            //Call the executeSQL query method. Execute the SQL Query.
            executeSQLQuery(SQLCode);
            
            //Set Cart Number to 0
            cart[0] = 0;
            
        }
        
        //Error Handling the Closing of the Current Shopping Session
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nAn Error occured while closing your current shopping session", "CRITICAL ERROR!", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    //This function accepts JOptionPane Results from the Back and Exit Buttons
    public void ExitLogout_Code()
    {
        try
        {
            //If Yes was chosen (renamed as Exit)
            if (YesorNo == JOptionPane.YES_OPTION)
            {
                //Calls the Function Holding SQL Queries for disconnecting the current shopping session
                CloseShoppingSession();
                
                //Exit the system
                System.exit(0);

            }
            
            //If No was chosen (renamed as Logout)
            else if (YesorNo == JOptionPane.NO_OPTION)
            {
                //Calls the Function holding SQL Queries for disconnecting the current shopping session
                CloseShoppingSession();

                //Opens Page ChooseUserType
                new ChooseUserType().setVisible(true);
                this.setVisible(false);

            }
        }
        
        //Error Handling the User Interaction with the JOptionDialog
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Logging out or Exiting the System", "EXIT/LOGOUT ERROR", JOptionPane.ERROR_MESSAGE);
            
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
        LBLMSG = new javax.swing.JLabel();
        LBLWarning = new javax.swing.JLabel();
        LBLLogo = new javax.swing.JLabel();
        LBL3 = new javax.swing.JLabel();
        LBLUserName = new javax.swing.JLabel();
        TXTUserName = new javax.swing.JTextField();
        LBLCase1 = new javax.swing.JLabel();
        LBL4 = new javax.swing.JLabel();
        LBLPassword = new javax.swing.JLabel();
        TXTPassword = new javax.swing.JPasswordField();
        CHKBXPassword = new javax.swing.JCheckBox();
        BTNSubmit = new javax.swing.JButton();
        BTNClearAll = new javax.swing.JButton();
        BTNBack = new javax.swing.JButton();
        BTNExit = new javax.swing.JButton();
        LBLBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CREATE NEW ADMINISTRATOR ACCOUNTS");
        setResizable(false);

        PNL1.setLayout(null);

        LBLMSG.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLMSG.setForeground(new java.awt.Color(255, 255, 255));
        LBLMSG.setText("User Name:");
        LBLMSG.setText("" + LoggedInUser);
        PNL1.add(LBLMSG);
        LBLMSG.setBounds(20, 30, 140, 30);

        LBLWarning.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        LBLWarning.setForeground(new java.awt.Color(153, 0, 0));
        LBLWarning.setText("BE SURE TO REMEMBER YOUR USERNAME AND PASSWORD");
        PNL1.add(LBLWarning);
        LBLWarning.setBounds(160, 10, 460, 20);

        LBLLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Big Bargains Store Logo.jpg"))); // NOI18N
        PNL1.add(LBLLogo);
        LBLLogo.setBounds(20, 90, 300, 300);

        LBL3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL3.setForeground(new java.awt.Color(255, 51, 51));
        LBL3.setText("*");
        PNL1.add(LBL3);
        LBL3.setBounds(350, 100, 8, 17);

        LBLUserName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLUserName.setForeground(new java.awt.Color(255, 255, 255));
        LBLUserName.setText("UserName:");
        PNL1.add(LBLUserName);
        LBLUserName.setBounds(370, 100, 70, 18);

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
        TXTUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTUserNameKeyTyped(evt);
            }
        });
        PNL1.add(TXTUserName);
        TXTUserName.setBounds(460, 100, 170, 30);

        LBLCase1.setForeground(new java.awt.Color(204, 0, 0));
        LBLCase1.setText("Case Sensitive");
        PNL1.add(LBLCase1);
        LBLCase1.setBounds(650, 110, 110, 16);
        LBLCase1.setVisible(false);

        LBL4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL4.setForeground(new java.awt.Color(255, 51, 51));
        LBL4.setText("*");
        PNL1.add(LBL4);
        LBL4.setBounds(350, 150, 8, 17);

        LBLPassword.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPassword.setForeground(new java.awt.Color(255, 255, 255));
        LBLPassword.setText("Password:");
        PNL1.add(LBLPassword);
        LBLPassword.setBounds(370, 150, 80, 18);

        TXTPassword.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTPasswordFocusLost(evt);
            }
        });
        TXTPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTPasswordActionPerformed(evt);
            }
        });
        TXTPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPasswordKeyTyped(evt);
            }
        });
        PNL1.add(TXTPassword);
        TXTPassword.setBounds(460, 150, 170, 30);

        CHKBXPassword.setForeground(new java.awt.Color(255, 255, 255));
        CHKBXPassword.setText("Show Password");
        CHKBXPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHKBXPasswordActionPerformed(evt);
            }
        });
        PNL1.add(CHKBXPassword);
        CHKBXPassword.setBounds(650, 160, 130, 18);

        BTNSubmit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNSubmit.setText("Submit");
        BTNSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSubmitActionPerformed(evt);
            }
        });
        PNL1.add(BTNSubmit);
        BTNSubmit.setBounds(460, 270, 170, 40);

        BTNClearAll.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNClearAll.setText("Clear All");
        BTNClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNClearAllActionPerformed(evt);
            }
        });
        PNL1.add(BTNClearAll);
        BTNClearAll.setBounds(460, 340, 170, 40);

        BTNBack.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBack.setText("Back");
        BTNBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBackActionPerformed(evt);
            }
        });
        PNL1.add(BTNBack);
        BTNBack.setBounds(20, 430, 90, 40);

        BTNExit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNExit.setText("Exit");
        BTNExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExitActionPerformed(evt);
            }
        });
        PNL1.add(BTNExit);
        BTNExit.setBounds(670, 420, 90, 40);

        LBLBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Background Photo.jpg"))); // NOI18N
        PNL1.add(LBLBackground);
        LBLBackground.setBounds(0, 0, 830, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNExitActionPerformed
        try
        {
            /**
                Creates JOptionDialog with Yes/No Options renamed as Exit and Logout
                When an option is chosen, it is saved in the YesorNo variable 
            */
            YesorNo = JOptionPane.showOptionDialog(new JFrame(), "Exit or Log Out", "CONFIRM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, new Object[] {"EXIT", "LOGOUT"}, JOptionPane.YES_OPTION);

            ExitLogout_Code();
        
        }
        
        //Error Handling the Option Dialog Frame
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Executing the JFrame Components Properly", "JFRAME ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNExitActionPerformed

    private void TXTUserNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTUserNameFocusGained
        LBLCase1.setVisible(true);
        
    }//GEN-LAST:event_TXTUserNameFocusGained

    private void TXTUserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTUserNameFocusLost
        LBLCase1.setVisible(false);

    }//GEN-LAST:event_TXTUserNameFocusLost

    private void TXTUserNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTUserNameKeyTyped
        //Allow a maxium of 30 Characters.
        if(TXTUserName.getText().length()>=30)
        {
            evt.consume();
            java.awt.Toolkit.getDefaultToolkit().beep();
            
        }
    }//GEN-LAST:event_TXTUserNameKeyTyped

    private void TXTPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTPasswordFocusGained
        CHKBXPassword.show();
        
    }//GEN-LAST:event_TXTPasswordFocusGained

    private void TXTPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTPasswordFocusLost
        
    }//GEN-LAST:event_TXTPasswordFocusLost

    private void TXTPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPasswordKeyTyped
        //Allow a maxium of 30 Characters.
        if(TXTPassword.getText().length()>=30)
        {
            evt.consume();
            java.awt.Toolkit.getDefaultToolkit().beep();
            
        }
    }//GEN-LAST:event_TXTPasswordKeyTyped

    private void BTNSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSubmitActionPerformed
        try
        {
            //Creates ConfirmDialog
            YesorNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to create this user?", "CREATE USER?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            if (YesorNo == JOptionPane.YES_OPTION)
            {
                //Submits New Admin Details into the Database
                SQLCode = "insert into adminlogin (UserName, Password) values ('" + TXTUserName.getText() + "', '" + TXTPassword.getText() + "');";
                executeSQLQuery(SQLCode);

                //Confirm Data Insertion
                JOptionPane.showMessageDialog(null, "User Details Successfully Inserted", "NEW ADMIN CREATED!", JOptionPane.INFORMATION_MESSAGE);

                //Create Another User?
                YesorNo = JOptionPane.showConfirmDialog(null, "Do you want to create another user?", "CREATE ANOTHER USER?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (YesorNo == JOptionPane.YES_OPTION)
                {
                    BTNClearAll.doClick();
                    
                }

                else
                {
                    PassingUserName();
                    PassingCartNumber();
                    AdminsPower.main(loggedin, cart);

                    new AdminsPower().setVisible(true);
                    this.setVisible(false);
                    
                }
            }
        }
        
        //Error Handling the Customer Data Insertion Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR CREATING USER", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNSubmitActionPerformed

    private void BTNClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNClearAllActionPerformed
        TXTUserName.setText("");
        TXTPassword.setText("");
        CHKBXPassword.hide();
        CHKBXPassword.setSelected(false);
        
    }//GEN-LAST:event_BTNClearAllActionPerformed

    private void BTNBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBackActionPerformed
        try
        {
            PassingUserName();
            PassingCartNumber();
            AdminsPower.main(loggedin, cart);

            new AdminsPower().setVisible(true);
            this.setVisible(false);
            
        }
        
        //Error Handling the Back Button Action Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Going Back", "ERROR GOING BACK", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNBackActionPerformed

    private void CHKBXPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHKBXPasswordActionPerformed
        if (CHKBXPassword.isSelected())
        {
            TXTPassword.setEchoChar((char)0);
        }

        else
        {
            TXTPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_CHKBXPasswordActionPerformed

    private void TXTUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTUserNameActionPerformed

    private void TXTPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTPasswordActionPerformed

    /**
     * @param args the command line arguments
     * @param args2
     */
    public static void main(String args[], int args2[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //The default method of running of this Page/File before logging in has been disabled.
        //<editor-fold defaultstate="collapsed" desc=" Code for Running this File (optional) ">
        try
        {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {

                }
            });
        }
        
        //Error Handling Running of this page/file before logging in
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please Login as Admin to Access this Page");
            
        }
        //</editor-fold>
        
        /*
            This is the User Name and Cart Number being passed to the int array variable called args and args2 as declared in the main method.
            This variable is passed to the next page to display the current number of items in cart but it can
            also accept updated data from a page passing this array data
        */
        try
        {
            LoggedInUser = args[0];
            ItemsInCart = args2[0];
            
        }
        
        //Error Handling the Data Passing Process
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Passing UserName and Cart Information to the next page", "ERROR PASSING USERNAME AND CART INFORMATION", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    //Swing Objects Declared Here
    //<editor-fold defaultstate="collapsed" desc=" Declared Variable of the Swing Objects (do not modify) ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNBack;
    private javax.swing.JButton BTNClearAll;
    private javax.swing.JButton BTNExit;
    private javax.swing.JButton BTNSubmit;
    private javax.swing.JCheckBox CHKBXPassword;
    private javax.swing.JLabel LBL3;
    private javax.swing.JLabel LBL4;
    private javax.swing.JLabel LBLBackground;
    private javax.swing.JLabel LBLCase1;
    private javax.swing.JLabel LBLLogo;
    private javax.swing.JLabel LBLMSG;
    private javax.swing.JLabel LBLPassword;
    private javax.swing.JLabel LBLUserName;
    private javax.swing.JLabel LBLWarning;
    private javax.swing.JPanel PNL1;
    private javax.swing.JPasswordField TXTPassword;
    private javax.swing.JTextField TXTUserName;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
