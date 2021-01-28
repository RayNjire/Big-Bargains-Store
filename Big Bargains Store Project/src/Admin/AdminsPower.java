package Admin;

import BeforeLogin.*;
import MySQLConnections.*;
import java.awt.Color;
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
public class AdminsPower extends javax.swing.JFrame
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
        //Passing the CartNumber. Insert into Array slot 0
        cart[0] = ItemsInCart;
        
    }
    
    //Common Variables
    Connection conn;
    String SQLCode;
    PreparedStatement preparedStatement;
    int YesorNo;
    Statement NewStatement;
            
    /**
     * Creates new form AdminsPower
     * All Codes in here are run as the Form is being created
    */
    public AdminsPower()
    {
        //Personalized Default Frame Appearance
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
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
            //Execute the create table query
            conn = MySQLConnect.ConnectDB();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            
        }
        
        //Error Handling the SQL Statements
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage() + "\n\nThe SQL statement did not execute properly", "ERROR EXECUTING SQL QUERY", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    //This function runs as the user logs out or exits the system
    public void CloseShoppingSession()
    {
        try
        {
            //Check MySQL Connection
            conn = MySQLConnect.ConnectDB();
            
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
        LBLLogo = new javax.swing.JLabel();
        LBLAdminAcc = new javax.swing.JLabel();
        LBLCustAcc = new javax.swing.JLabel();
        LBLAllUsers = new javax.swing.JLabel();
        LBLInv = new javax.swing.JLabel();
        LBLBuy = new javax.swing.JLabel();
        BTNLogout = new javax.swing.JButton();
        BTNExit = new javax.swing.JButton();
        LBLBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ADMINISTRATOR'S POWER");
        setMinimumSize(new java.awt.Dimension(675, 400));
        setResizable(false);

        PNL1.setPreferredSize(new java.awt.Dimension(660, 360));
        PNL1.setLayout(null);

        LBLMSG.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLMSG.setForeground(new java.awt.Color(255, 255, 255));
        LBLMSG.setText("User Name:");
        LBLMSG.setText("" + LoggedInUser);
        PNL1.add(LBLMSG);
        LBLMSG.setBounds(30, 10, 140, 30);

        LBLLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Big Bargains Store Logo.jpg"))); // NOI18N
        PNL1.add(LBLLogo);
        LBLLogo.setBounds(20, 70, 300, 300);

        LBLAdminAcc.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        LBLAdminAcc.setForeground(new java.awt.Color(255, 255, 0));
        LBLAdminAcc.setText("ADD NEW ADMINISTRATOR ACCOUNT");
        LBLAdminAcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLAdminAccMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LBLAdminAccMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LBLAdminAccMouseExited(evt);
            }
        });
        PNL1.add(LBLAdminAcc);
        LBLAdminAcc.setBounds(400, 70, 290, 31);

        LBLCustAcc.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        LBLCustAcc.setForeground(new java.awt.Color(255, 255, 0));
        LBLCustAcc.setText("ADD NEW CUSTOMER ACCOUNT");
        LBLCustAcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLCustAccMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LBLCustAccMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LBLCustAccMouseExited(evt);
            }
        });
        PNL1.add(LBLCustAcc);
        LBLCustAcc.setBounds(400, 140, 240, 31);

        LBLAllUsers.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        LBLAllUsers.setForeground(new java.awt.Color(255, 255, 0));
        LBLAllUsers.setText("EDIT ALL USER ACCOUNTS");
        LBLAllUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLAllUsersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LBLAllUsersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LBLAllUsersMouseExited(evt);
            }
        });
        PNL1.add(LBLAllUsers);
        LBLAllUsers.setBounds(400, 210, 200, 31);

        LBLInv.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        LBLInv.setForeground(new java.awt.Color(255, 255, 0));
        LBLInv.setText("EDIT INVENTORY");
        LBLInv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLInvMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LBLInvMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LBLInvMouseExited(evt);
            }
        });
        PNL1.add(LBLInv);
        LBLInv.setBounds(400, 280, 130, 31);

        LBLBuy.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        LBLBuy.setForeground(new java.awt.Color(255, 255, 0));
        LBLBuy.setText("BUY GOODS");
        LBLBuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLBuyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LBLBuyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LBLBuyMouseExited(evt);
            }
        });
        PNL1.add(LBLBuy);
        LBLBuy.setBounds(400, 350, 90, 31);

        BTNLogout.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNLogout.setText("Log Out");
        BTNLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNLogoutActionPerformed(evt);
            }
        });
        PNL1.add(BTNLogout);
        BTNLogout.setBounds(20, 410, 90, 40);

        BTNExit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNExit.setText("Exit");
        BTNExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExitActionPerformed(evt);
            }
        });
        PNL1.add(BTNExit);
        BTNExit.setBounds(680, 410, 80, 40);

        LBLBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Background Photo.jpg"))); // NOI18N
        LBLBackground.setMaximumSize(new java.awt.Dimension(12000, 10000));
        PNL1.add(LBLBackground);
        LBLBackground.setBounds(-9, -8, 910, 580);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNLogoutActionPerformed
        try
        {
                YesorNo = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to Log Out?", "CONFIRM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, new Object[] {"Yes", "No"}, JOptionPane.YES_OPTION);

                if (YesorNo == JOptionPane.YES_OPTION)
            {
                CloseShoppingSession();

                new ChooseUserType().setVisible(true);
                this.setVisible(false);
                
            }
        }
        
        //Error Handling the Log Out Action Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nLogging Out Error", "LOG OUT ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNLogoutActionPerformed

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

    private void LBLAdminAccMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLAdminAccMouseEntered
        LBLAdminAcc.setForeground(Color.green);
        
    }//GEN-LAST:event_LBLAdminAccMouseEntered

    private void LBLCustAccMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCustAccMouseEntered
        LBLCustAcc.setForeground(Color.green);
        
    }//GEN-LAST:event_LBLCustAccMouseEntered

    private void LBLAllUsersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLAllUsersMouseEntered
        LBLAllUsers.setForeground(Color.green);
        
    }//GEN-LAST:event_LBLAllUsersMouseEntered

    private void LBLInvMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLInvMouseEntered
        LBLInv.setForeground(Color.green);
        
    }//GEN-LAST:event_LBLInvMouseEntered

    private void LBLBuyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLBuyMouseEntered
        LBLBuy.setForeground(Color.green);
        
    }//GEN-LAST:event_LBLBuyMouseEntered

    private void LBLAdminAccMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLAdminAccMouseExited
        LBLAdminAcc.setForeground(Color.yellow);
        
    }//GEN-LAST:event_LBLAdminAccMouseExited

    private void LBLCustAccMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCustAccMouseExited
        LBLCustAcc.setForeground(Color.yellow);
        
    }//GEN-LAST:event_LBLCustAccMouseExited

    private void LBLAllUsersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLAllUsersMouseExited
        LBLAllUsers.setForeground(Color.yellow);
        
    }//GEN-LAST:event_LBLAllUsersMouseExited

    private void LBLInvMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLInvMouseExited
        LBLInv.setForeground(Color.yellow);
        
    }//GEN-LAST:event_LBLInvMouseExited

    private void LBLBuyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLBuyMouseExited
        LBLBuy.setForeground(Color.yellow);
        
    }//GEN-LAST:event_LBLBuyMouseExited

    private void LBLAdminAccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLAdminAccMouseClicked
        try
        {
            //Pass UserName and CartNumber to the Next Page
            PassingUserName();
            PassingCartNumber();
            NewAdmin.main(loggedin, cart);

            //Open Next Page
            new NewAdmin().setVisible(true);
            this.setVisible(false);
            
        }
        
        //Error Handling Label Click Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError opening page to edit Administrator accounts", "ERROR OPENING PAGE", JOptionPane.ERROR_MESSAGE);
            
        }   
    }//GEN-LAST:event_LBLAdminAccMouseClicked

    private void LBLCustAccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCustAccMouseClicked
        try
        {
            //Pass UserName and CartNumber to the Next Page
            PassingUserName();
            PassingCartNumber();
            NewCustomer_Admin.main(loggedin, cart);

            //Open Next Page
            new NewCustomer_Admin().setVisible(true);
            this.setVisible(false);
            
        }
        
        //Error Handling Label Click Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError opening page to edit Customer accounts", "ERROR OPENING PAGE", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_LBLCustAccMouseClicked

    private void LBLAllUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLAllUsersMouseClicked
        try
        {
            //Pass UserName and CartNumber to the Next Page
            PassingUserName();
            PassingCartNumber();
            EditAllUsers.main(loggedin, cart);

            //Open Next Page
            new EditAllUsers().setVisible(true);
            this.setVisible(false);
            
        }
        
        //Error Handling Label Click Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError opening page to edit administrator accounts", "ERROR OPENING PAGE", JOptionPane.ERROR_MESSAGE);
        
        }
    }//GEN-LAST:event_LBLAllUsersMouseClicked

    private void LBLInvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLInvMouseClicked
        try
        {
            //Pass UserName and CartNumber to the Next Page
            PassingUserName();
            PassingCartNumber();
            EditInventory.main(loggedin, cart);

            //Open Next Page
            new EditInventory().setVisible(true);
            this.setVisible(false);
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError opening page to edit administrator accounts", "ERROR OPENING PAGE", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_LBLInvMouseClicked

    private void LBLBuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLBuyMouseClicked
        try
        {
            //Creates the table that holds the all User's shopping sessions. Permanent Record of bought items
            SQLCode = "create table if not exists `" + LBLMSG.getText() + "` (ProductBought varchar (20), DateBought DATETIME primary key, Price double (10,2), Quantity int (5), ProductPrice double(10,2), TotalPrice double(10,2));";
            createTable(SQLCode);

            //Creates the table that only holds the User's current shopping session. Temporary table.
            SQLCode = "create table if not exists `" + LBLMSG.getText().concat("_TEMP") +"` like `" + LBLMSG.getText() +"`;";
            createTable(SQLCode);

            //Pass the UserName and Cart Number
            PassingUserName();
            PassingCartNumber();
            AdminBuyGoods1.main(loggedin, cart);

            //Opens the first Shopping JFrame
            new AdminBuyGoods1().setVisible(true);
            this.setVisible(false);

        }
        
        //Error Handling the Label Click Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Opening the Shopping Page", "BUY GOODS CLICK ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        
    }//GEN-LAST:event_LBLBuyMouseClicked

    /**
     * @param args the command line arguments
     * @param args2
    */
    public static void main(String args[], int args2[])
    {
        // Set the Nimbus look and feel
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminsPower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminsPower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminsPower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminsPower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //The default method of running of this Page/File before logging in has been disabled.
        //<editor-fold defaultstate="collapsed" desc=" Code for Running this File (optional) ">
        try
        {
            java.awt.EventQueue.invokeLater(new Runnable() 
            {
                public void run() 
                {

                }
            });
        }
        
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
    //<editor-fold defaultstate="collapsed" desc=" Declared Variable of the Swing Objects (do not modify)">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNExit;
    private javax.swing.JButton BTNLogout;
    private javax.swing.JLabel LBLAdminAcc;
    private javax.swing.JLabel LBLAllUsers;
    private javax.swing.JLabel LBLBackground;
    private javax.swing.JLabel LBLBuy;
    private javax.swing.JLabel LBLCustAcc;
    private javax.swing.JLabel LBLInv;
    private javax.swing.JLabel LBLLogo;
    private javax.swing.JLabel LBLMSG;
    private javax.swing.JPanel PNL1;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
