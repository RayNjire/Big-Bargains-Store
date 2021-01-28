package Admin;

import BeforeLogin.*;
import MySQLConnections.*;
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
public class NewCustomer_Admin extends javax.swing.JFrame
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
     * Creates new form NewCustomer_Admin
     * All Codes in here are run as the Form is being created
    */
    public NewCustomer_Admin()
    {
        //Personalized Default Frame Appearance
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        
        //Set Componentss Hidden
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
            //Calls the Method for checking MySQL Connection in the Class MySQLConnect. (Inheritance)
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
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Closing your current shopping session", "CRITICAL ERROR!", JOptionPane.ERROR_MESSAGE);
            
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

                //Set the Number of Items in Cart to 0
                ItemsInCart = 0;

                //Opens Page ChooseUserType
                new ChooseUserType().setVisible(true);
                
                //Set this to invisible
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
        LBL1 = new javax.swing.JLabel();
        LBLFirstName = new javax.swing.JLabel();
        TXTFirstName = new javax.swing.JTextField();
        LBL2 = new javax.swing.JLabel();
        LBLLastName = new javax.swing.JLabel();
        TXTLastName = new javax.swing.JTextField();
        LBL3 = new javax.swing.JLabel();
        LBLUserName = new javax.swing.JLabel();
        TXTUserName = new javax.swing.JTextField();
        LBLCase1 = new javax.swing.JLabel();
        LBL4 = new javax.swing.JLabel();
        LBLPassword = new javax.swing.JLabel();
        TXTPassword = new javax.swing.JPasswordField();
        LBLCase2 = new javax.swing.JLabel();
        CHKBXPassword = new javax.swing.JCheckBox();
        LBLPhoneNumber = new javax.swing.JLabel();
        TXTPhoneNumber = new javax.swing.JTextField();
        LBLEmail = new javax.swing.JLabel();
        TXTEmail = new javax.swing.JTextField();
        BTNSubmit = new javax.swing.JButton();
        BTNClearAll = new javax.swing.JButton();
        BTNBack = new javax.swing.JButton();
        BTNExit = new javax.swing.JButton();
        LBLBackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CREATE NEW CUSTOMER ACCOUNT");
        setResizable(false);

        PNL1.setLayout(null);

        LBLMSG.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLMSG.setForeground(new java.awt.Color(255, 255, 255));
        LBLMSG.setText("User Name:");
        LBLMSG.setText("" + LoggedInUser);
        PNL1.add(LBLMSG);
        LBLMSG.setBounds(30, 10, 140, 30);

        LBLWarning.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        LBLWarning.setForeground(new java.awt.Color(153, 0, 0));
        LBLWarning.setText("BE SURE TO REMEMBER YOUR USERNAME AND PASSWORD");
        PNL1.add(LBLWarning);
        LBLWarning.setBounds(220, 10, 460, 20);

        LBLLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Big Bargains Store Logo.jpg"))); // NOI18N
        PNL1.add(LBLLogo);
        LBLLogo.setBounds(30, 80, 300, 300);

        LBL1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL1.setForeground(new java.awt.Color(255, 51, 51));
        LBL1.setText("*");
        PNL1.add(LBL1);
        LBL1.setBounds(350, 90, 8, 17);

        LBLFirstName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLFirstName.setForeground(new java.awt.Color(255, 255, 255));
        LBLFirstName.setText("First Name:");
        PNL1.add(LBLFirstName);
        LBLFirstName.setBounds(370, 90, 90, 18);

        TXTFirstName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTFirstName.setMinimumSize(new java.awt.Dimension(6, 20));
        TXTFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTFirstNameActionPerformed(evt);
            }
        });
        TXTFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTFirstNameKeyTyped(evt);
            }
        });
        PNL1.add(TXTFirstName);
        TXTFirstName.setBounds(460, 80, 160, 30);

        LBL2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL2.setForeground(new java.awt.Color(255, 51, 51));
        LBL2.setText("*");
        PNL1.add(LBL2);
        LBL2.setBounds(350, 130, 8, 17);

        LBLLastName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLLastName.setForeground(new java.awt.Color(255, 255, 255));
        LBLLastName.setText("Last Name:");
        PNL1.add(LBLLastName);
        LBLLastName.setBounds(370, 130, 90, 18);

        TXTLastName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTLastNameKeyTyped(evt);
            }
        });
        PNL1.add(TXTLastName);
        TXTLastName.setBounds(460, 120, 160, 30);

        LBL3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL3.setForeground(new java.awt.Color(255, 51, 51));
        LBL3.setText("*");
        PNL1.add(LBL3);
        LBL3.setBounds(350, 170, 8, 17);

        LBLUserName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLUserName.setForeground(new java.awt.Color(255, 255, 255));
        LBLUserName.setText("UserName:");
        PNL1.add(LBLUserName);
        LBLUserName.setBounds(370, 170, 90, 18);

        TXTUserName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTUserNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTUserNameFocusLost(evt);
            }
        });
        TXTUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTUserNameKeyTyped(evt);
            }
        });
        PNL1.add(TXTUserName);
        TXTUserName.setBounds(460, 160, 160, 30);

        LBLCase1.setForeground(new java.awt.Color(204, 0, 0));
        LBLCase1.setText("Case Sensitive");
        PNL1.add(LBLCase1);
        LBLCase1.setBounds(630, 170, 90, 16);
        LBLCase1.setVisible(false);

        LBL4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL4.setForeground(new java.awt.Color(255, 51, 51));
        LBL4.setText("*");
        PNL1.add(LBL4);
        LBL4.setBounds(350, 210, 8, 17);

        LBLPassword.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPassword.setForeground(new java.awt.Color(255, 255, 255));
        LBLPassword.setText("Password:");
        PNL1.add(LBLPassword);
        LBLPassword.setBounds(370, 210, 80, 18);

        TXTPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TXTPassword.setPreferredSize(new java.awt.Dimension(6, 24));
        TXTPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTPasswordFocusLost(evt);
            }
        });
        TXTPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPasswordKeyTyped(evt);
            }
        });
        PNL1.add(TXTPassword);
        TXTPassword.setBounds(460, 200, 160, 30);

        LBLCase2.setForeground(new java.awt.Color(204, 0, 0));
        LBLCase2.setText("Case Sensitive");
        PNL1.add(LBLCase2);
        LBLCase2.setBounds(630, 210, 100, 16);
        LBLCase2.setVisible(false);

        CHKBXPassword.setForeground(new java.awt.Color(255, 255, 255));
        CHKBXPassword.setText("Show Password");
        PNL1.add(CHKBXPassword);
        CHKBXPassword.setBounds(730, 210, 130, 18);

        LBLPhoneNumber.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPhoneNumber.setForeground(new java.awt.Color(255, 255, 255));
        LBLPhoneNumber.setText("Phone Number:");
        PNL1.add(LBLPhoneNumber);
        LBLPhoneNumber.setBounds(340, 250, 120, 18);

        TXTPhoneNumber.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTPhoneNumber.setText("07");
        TXTPhoneNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPhoneNumberKeyTyped(evt);
            }
        });
        PNL1.add(TXTPhoneNumber);
        TXTPhoneNumber.setBounds(460, 240, 160, 30);

        LBLEmail.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLEmail.setForeground(new java.awt.Color(255, 255, 255));
        LBLEmail.setText("Email:");
        PNL1.add(LBLEmail);
        LBLEmail.setBounds(400, 290, 60, 18);

        TXTEmail.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTEmailKeyTyped(evt);
            }
        });
        PNL1.add(TXTEmail);
        TXTEmail.setBounds(460, 280, 160, 30);

        BTNSubmit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNSubmit.setText("Submit");
        BTNSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSubmitActionPerformed(evt);
            }
        });
        PNL1.add(BTNSubmit);
        BTNSubmit.setBounds(460, 350, 160, 40);

        BTNClearAll.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNClearAll.setText("Clear All");
        BTNClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNClearAllActionPerformed(evt);
            }
        });
        PNL1.add(BTNClearAll);
        BTNClearAll.setBounds(460, 400, 160, 40);

        BTNBack.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBack.setText("Back");
        BTNBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBackActionPerformed(evt);
            }
        });
        PNL1.add(BTNBack);
        BTNBack.setBounds(40, 460, 80, 40);

        BTNExit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNExit.setText("Exit");
        BTNExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExitActionPerformed(evt);
            }
        });
        PNL1.add(BTNExit);
        BTNExit.setBounds(750, 460, 80, 40);

        LBLBackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Background Photo.jpg"))); // NOI18N
        PNL1.add(LBLBackGround);
        LBLBackGround.setBounds(0, 0, 880, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
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

    private void TXTFirstNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTFirstNameKeyTyped
        //Allow a maxium of 20 Characters.
        if(TXTFirstName.getText().length()>=20)
        {
            evt.consume();
            java.awt.Toolkit.getDefaultToolkit().beep();
            
        }
        
    }//GEN-LAST:event_TXTFirstNameKeyTyped

    private void TXTLastNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTLastNameKeyTyped
        //Allow a maxium of 20 Characters.
        if(TXTLastName.getText().length()>=20)
        {
            evt.consume();
            java.awt.Toolkit.getDefaultToolkit().beep();
            
        }
        
    }//GEN-LAST:event_TXTLastNameKeyTyped

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

    private void TXTEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTEmailKeyTyped
        //Allow a maxium of 30 Characters.
        if(TXTEmail.getText().length()>=50)
        {
            evt.consume();
            java.awt.Toolkit.getDefaultToolkit().beep();
            
        }
    }//GEN-LAST:event_TXTEmailKeyTyped

    private void TXTPhoneNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPhoneNumberKeyTyped
        //Allow a maxium of 10 Characters.
        if(TXTPhoneNumber.getText().length()>=10)
        {
            evt.consume();
            java.awt.Toolkit.getDefaultToolkit().beep();
            
        }
    }//GEN-LAST:event_TXTPhoneNumberKeyTyped

    private void TXTPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTPasswordFocusGained
        LBLCase2.setVisible(true);
        CHKBXPassword.show();
        
    }//GEN-LAST:event_TXTPasswordFocusGained

    private void TXTPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTPasswordFocusLost
        LBLCase2.setVisible(false);
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
        //Check MySQL Connection
        conn = MySQLConnect.ConnectDB();
        
        if (TXTFirstName.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "First Name TextField Cannot be Blank");
            
        }
        
        else if (TXTLastName.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "Last Name TextField Cannot be Blank");
            
        }
        
        else if (TXTUserName.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "User Name TextField Cannot be Blank");
            
        }
        
        else if (TXTPassword.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "Password TextField Cannot be Blank");
            
        }
        
        else if (TXTPhoneNumber.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "Phone Number TextField Cannot be Blank");
            
        }
        
        else
        {
            try
            {
                //Creates ConfirmDialog
                YesorNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to create this user?", "CREATE USER?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (YesorNo == JOptionPane.YES_OPTION)
                {
                    //Submits New Admin Details into the Database
                    SQLCode = "insert into customers (FirstName, LastName, UserName, Password, PhoneNumber, Email) values ('" + TXTFirstName.getText() + "', '" + TXTLastName.getText() + "', '" + TXTUserName.getText() + "', '" + TXTPassword.getText() + "', " + TXTPhoneNumber.getText() + ", '" + TXTEmail.getText() + "');";
                    executeSQLQuery(SQLCode);

                    //Confirm Data Insertion
                    JOptionPane.showMessageDialog(null, "User Details Successfully Inserted", "NEW CUSTOMER CREATED!", JOptionPane.INFORMATION_MESSAGE);

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
        }
    }//GEN-LAST:event_BTNSubmitActionPerformed

    private void BTNClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNClearAllActionPerformed
        TXTFirstName.setText("");
        TXTLastName.setText("");
        TXTUserName.setText("");
        TXTPassword.setText("");
        TXTEmail.setText("");
        TXTPhoneNumber.setText("07");
        CHKBXPassword.setSelected(false);
        CHKBXPassword.hide();
        
    }//GEN-LAST:event_BTNClearAllActionPerformed

    private void BTNBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBackActionPerformed
        PassingUserName();
        PassingCartNumber();
        AdminsPower.main(loggedin, cart);
        
        new AdminsPower().setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_BTNBackActionPerformed

    private void TXTFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTFirstNameActionPerformed

    /**
     * @param args the command line arguments
     * @param args2
    */
    public static void main(String args[], int args2[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
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
    private javax.swing.JLabel LBL1;
    private javax.swing.JLabel LBL2;
    private javax.swing.JLabel LBL3;
    private javax.swing.JLabel LBL4;
    private javax.swing.JLabel LBLBackGround;
    private javax.swing.JLabel LBLCase1;
    private javax.swing.JLabel LBLCase2;
    private javax.swing.JLabel LBLEmail;
    private javax.swing.JLabel LBLFirstName;
    private javax.swing.JLabel LBLLastName;
    private javax.swing.JLabel LBLLogo;
    private javax.swing.JLabel LBLMSG;
    private javax.swing.JLabel LBLPassword;
    private javax.swing.JLabel LBLPhoneNumber;
    private javax.swing.JLabel LBLUserName;
    private javax.swing.JLabel LBLWarning;
    private javax.swing.JPanel PNL1;
    private javax.swing.JTextField TXTEmail;
    private javax.swing.JTextField TXTFirstName;
    private javax.swing.JTextField TXTLastName;
    private javax.swing.JPasswordField TXTPassword;
    private javax.swing.JTextField TXTPhoneNumber;
    private javax.swing.JTextField TXTUserName;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
