package Customers;

import BeforeLogin.*;
import MySQLConnections.*;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
 */
public class CustomerBuyGoods1 extends javax.swing.JFrame
{
    //These variables ACCEPT passed data for Cart Number and UserName
    public static String LoggedInUser;
    public static int ItemsInCart;
    
    //These Arrays hold the UserName and Cart Number before passing it to the next page
    String loggedin[] = new String [1];
    int cart[] = new int [1];
    
    //Function that holds the Array Input for Passing UserName
    public void PassingUserName()
    {
        //Passing the Username. Insert into Array slot 0 the name of the user
        loggedin[0] = LBLMSG.getText().toUpperCase();
        
    }
    
    //Function that holds the Array Input for Passing Cart Number
    public void PassingCartNumber()
    {
        //Passing the Username. Insert into Array slot 0 the name of the user
        cart[0] = Integer.parseInt(LBLCartNo.getText());
        
    }

    //Common Variables
    String ProductQuantity, ProductName,  SQLCode;
    double ProductPrice1;
    int YesorNo;
    Connection conn;
    Statement NewStatement;
    ResultSet myResultSet;
    
    /**
     * Creates new form CustomerBuyGoods1
     * All Codes in here are run as the Form is being created
    */
    public CustomerBuyGoods1()
    {
        //Personalized Default Frame Appearance
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        
        //Set Components Invisible
        LBLQuantity1.setVisible(false);
        CBXItem1.setVisible(false);
        LBLQuantity2.setVisible(false);
        CBXItem2.setVisible(false);
        LBLQuantity3.setVisible(false);
        CBXItem3.setVisible(false);
        
        try
        {
            //Calls the Method for checking MySQL Connection in the Class MySQLConnect. (Inheritance)
            conn = MySQLConnect.ConnectDB();
            
            //Holds the SQL Statement you write and checks whether MySQL is connected
            NewStatement = conn.createStatement();
            
            //FIRST PRODUCT
            //Retrieve all data for the product at CO001 from the database
            myResultSet = NewStatement.executeQuery ("select product, invid, priceinksh from inventory where groupcode = 'CO001';");
            
            //Sets the Data to their respective Labels
            while (myResultSet.next())
            {
                LBLName1.setText(myResultSet.getString("product"));
                BTNBuy1.setText("Buy " + myResultSet.getString("product"));
                LBLID1.setText(myResultSet.getString("invid"));
                LBLPrice1.setText(myResultSet.getString("priceinksh"));
                
            }
 
            //SECOND PRODUCT
            myResultSet = NewStatement.executeQuery ("select product, invid, priceinksh from inventory where groupcode = 'CO008';");
            
            while (myResultSet.next())
            {
                LBLName2.setText(myResultSet.getString("product"));
                BTNBuy2.setText("Buy " + myResultSet.getString("product"));
                LBLID2.setText(myResultSet.getString("invid"));
                LBLPrice2.setText(myResultSet.getString("priceinksh"));
                
            }
        
            //THIRD PRODUCT
            myResultSet = NewStatement.executeQuery("select product, invid, priceinksh from inventory where groupcode = 'FA006';");
            
            while (myResultSet.next())
            {
                LBLName3.setText(myResultSet.getString("product"));
                BTNBuy3.setText("Buy " + myResultSet.getString("product"));
                LBLID3.setText(myResultSet.getString("invid"));
                LBLPrice3.setText(myResultSet.getString("priceinksh"));
                
            }
        }
        
        //Error Handling for Retrieving Data
        catch (SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage() + "\n\nError Retrieving Data from Database.", "DATA RETRIEVAL ERROR", JOptionPane.ERROR_MESSAGE);
            new AdminLogin().setVisible(true);
            this.setVisible(false);
            
        }
    }
    
    //Method to execute Any SQL Statements that run in this class
    public void executeSQLQuery (String query)
    {
        try
        {
            //Calls the Method for checking MySQL Connection in the Class MySQLConnect. (Inheritance)
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
    
    /**
        Function that holds critical values of the current shopping session
        It holds: The creation of the temp table that updates the shopping session to the database,
                  The current amount to be spent 
                  The number of items bought
    */
    public void CalculateShoppingAmount()
    {
        try
        {
            //Calls the Method for checking MySQL Connection in the Class MySQLConnect. (Inheritance)
            conn = MySQLConnect.ConnectDB();
            
            //Converts the String to int
            int ProductQuantity1 = Integer.parseInt(ProductQuantity);

            //Multiplies Quantity by the Price
            double TotalPrice1 = ProductQuantity1 * ProductPrice1;

            //Create temp mysql table to hold current shopping details
            SQLCode = "insert into `" + LBLMSG.getText() + "_temp` (ProductBought, DateBought, Price, Quantity, ProductPrice) VALUES ('" + ProductName + "', Now(), '" + ProductPrice1 + "', '" + ProductQuantity1 + "', '" + TotalPrice1 + "');";

            //Send the SQL Query to execute in the method 'executeSQLQuery'
            executeSQLQuery(SQLCode);

            //Converts the Current number of Items in the cart to Integer
            int inCart = Integer.parseInt(LBLCartNo.getText());

            //Adds the New Items' Quantity into the Number of Items in the Cart that is being displayed
            int newinCart = inCart + ProductQuantity1;

            //Converts the integer of the 'New Number of Items In Cart' to String, so that it can post it to the label
            String StringnewinCart = String.valueOf(newinCart);

            //Puts up the new 'Number of Items in Cart'
            LBLCartNo.setText(StringnewinCart);
        
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nCritical error occured", "CRITICAL CALCULATION ERROR", JOptionPane.ERROR_MESSAGE);
            
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
        LBLCart = new javax.swing.JLabel();
        LBLCartNo = new javax.swing.JLabel();
        LBLFaceGel = new javax.swing.JLabel();
        LBLID1 = new javax.swing.JLabel();
        LBLName1 = new javax.swing.JLabel();
        LBLKSH1 = new javax.swing.JLabel();
        LBLPrice1 = new javax.swing.JLabel();
        BTNBuy1 = new javax.swing.JButton();
        LBLQuantity1 = new javax.swing.JLabel();
        CBXItem1 = new javax.swing.JComboBox<>();
        LBLShampoo = new javax.swing.JLabel();
        LBLID2 = new javax.swing.JLabel();
        LBLName2 = new javax.swing.JLabel();
        KSH2 = new javax.swing.JLabel();
        BTNBuy2 = new javax.swing.JButton();
        LBLQuantity2 = new javax.swing.JLabel();
        CBXItem2 = new javax.swing.JComboBox<>();
        LBLPrice2 = new javax.swing.JLabel();
        LBLPolo = new javax.swing.JLabel();
        LBLID3 = new javax.swing.JLabel();
        LBLName3 = new javax.swing.JLabel();
        KSH3 = new javax.swing.JLabel();
        LBLPrice3 = new javax.swing.JLabel();
        BTNBuy3 = new javax.swing.JButton();
        LBLQuantity3 = new javax.swing.JLabel();
        CBXItem3 = new javax.swing.JComboBox<>();
        BTNBack = new javax.swing.JButton();
        BTNInCart = new javax.swing.JToggleButton();
        BTNNext = new javax.swing.JButton();
        BTNExit = new javax.swing.JButton();
        LBLBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CUSTOMER SHOPPING PAGE 1");
        setResizable(false);

        PNL1.setLayout(null);

        LBLMSG.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLMSG.setForeground(new java.awt.Color(255, 255, 255));
        LBLMSG.setText("User Name:");
        LBLMSG.setText("" + LoggedInUser);
        PNL1.add(LBLMSG);
        LBLMSG.setBounds(30, 10, 140, 30);

        LBLCart.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLCart.setForeground(new java.awt.Color(255, 255, 0));
        LBLCart.setText("IN CART: ");
        LBLCart.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                LBLCartFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                LBLCartFocusLost(evt);
            }
        });
        LBLCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLCartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LBLCartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LBLCartMouseExited(evt);
            }
        });
        PNL1.add(LBLCart);
        LBLCart.setBounds(710, 10, 120, 30);

        LBLCartNo.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLCartNo.setForeground(new java.awt.Color(255, 255, 0));
        LBLCartNo.setText("00");
        LBLCartNo.setText("" + ItemsInCart);
        PNL1.add(LBLCartNo);
        LBLCartNo.setBounds(780, 8, 30, 30);

        LBLFaceGel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Face Gel.jpeg"))); // NOI18N
        PNL1.add(LBLFaceGel);
        LBLFaceGel.setBounds(50, 50, 200, 200);

        LBLID1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLID1.setForeground(new java.awt.Color(255, 255, 255));
        LBLID1.setText("ID");
        PNL1.add(LBLID1);
        LBLID1.setBounds(70, 270, 100, 30);

        LBLName1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLName1.setForeground(new java.awt.Color(255, 255, 255));
        LBLName1.setText("Name");
        PNL1.add(LBLName1);
        LBLName1.setBounds(70, 310, 130, 30);

        LBLKSH1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLKSH1.setForeground(new java.awt.Color(255, 255, 255));
        LBLKSH1.setText("KSH");
        PNL1.add(LBLKSH1);
        LBLKSH1.setBounds(70, 350, 50, 30);

        LBLPrice1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPrice1.setForeground(new java.awt.Color(255, 255, 255));
        LBLPrice1.setText("Price");
        PNL1.add(LBLPrice1);
        LBLPrice1.setBounds(120, 350, 130, 30);

        BTNBuy1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBuy1.setText("Buy");
        BTNBuy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBuy1ActionPerformed(evt);
            }
        });
        PNL1.add(BTNBuy1);
        BTNBuy1.setBounds(50, 400, 190, 30);

        LBLQuantity1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLQuantity1.setForeground(new java.awt.Color(255, 255, 255));
        LBLQuantity1.setText("Quantity:");
        PNL1.add(LBLQuantity1);
        LBLQuantity1.setBounds(70, 450, 70, 30);

        CBXItem1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        CBXItem1.setMaximumRowCount(5);
        CBXItem1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" }));
        CBXItem1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CBXItem1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CBXItem1FocusLost(evt);
            }
        });
        CBXItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBXItem1ActionPerformed(evt);
            }
        });
        PNL1.add(CBXItem1);
        CBXItem1.setBounds(140, 450, 90, 30);

        LBLShampoo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Shampoo.jpg"))); // NOI18N
        PNL1.add(LBLShampoo);
        LBLShampoo.setBounds(340, 50, 200, 200);

        LBLID2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLID2.setForeground(new java.awt.Color(255, 255, 255));
        LBLID2.setText("ID");
        PNL1.add(LBLID2);
        LBLID2.setBounds(370, 270, 50, 20);

        LBLName2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLName2.setForeground(new java.awt.Color(255, 255, 255));
        LBLName2.setText("Name");
        PNL1.add(LBLName2);
        LBLName2.setBounds(370, 310, 120, 30);

        KSH2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        KSH2.setForeground(new java.awt.Color(255, 255, 255));
        KSH2.setText("KSH");
        PNL1.add(KSH2);
        KSH2.setBounds(370, 350, 40, 30);

        BTNBuy2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBuy2.setText("Buy");
        BTNBuy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBuy2ActionPerformed(evt);
            }
        });
        PNL1.add(BTNBuy2);
        BTNBuy2.setBounds(350, 400, 190, 30);

        LBLQuantity2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLQuantity2.setForeground(new java.awt.Color(255, 255, 255));
        LBLQuantity2.setText("Quantity:");
        PNL1.add(LBLQuantity2);
        LBLQuantity2.setBounds(370, 450, 70, 30);

        CBXItem2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        CBXItem2.setMaximumRowCount(5);
        CBXItem2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" }));
        CBXItem2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CBXItem2FocusLost(evt);
            }
        });
        PNL1.add(CBXItem2);
        CBXItem2.setBounds(440, 450, 100, 30);

        LBLPrice2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPrice2.setForeground(new java.awt.Color(255, 255, 255));
        LBLPrice2.setText("Price");
        PNL1.add(LBLPrice2);
        LBLPrice2.setBounds(420, 350, 110, 30);

        LBLPolo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Polo Shirts.jpg"))); // NOI18N
        PNL1.add(LBLPolo);
        LBLPolo.setBounds(630, 50, 200, 200);

        LBLID3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLID3.setForeground(new java.awt.Color(255, 255, 255));
        LBLID3.setText("ID");
        PNL1.add(LBLID3);
        LBLID3.setBounds(670, 270, 50, 20);

        LBLName3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLName3.setForeground(new java.awt.Color(255, 255, 255));
        LBLName3.setText("Name");
        PNL1.add(LBLName3);
        LBLName3.setBounds(670, 310, 140, 30);

        KSH3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        KSH3.setForeground(new java.awt.Color(255, 255, 255));
        KSH3.setText("KSH");
        PNL1.add(KSH3);
        KSH3.setBounds(670, 358, 40, 30);

        LBLPrice3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPrice3.setForeground(new java.awt.Color(255, 255, 255));
        LBLPrice3.setText("Price");
        PNL1.add(LBLPrice3);
        LBLPrice3.setBounds(720, 360, 160, 30);

        BTNBuy3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBuy3.setText("Buy");
        BTNBuy3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBuy3ActionPerformed(evt);
            }
        });
        PNL1.add(BTNBuy3);
        BTNBuy3.setBounds(650, 400, 190, 30);

        LBLQuantity3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLQuantity3.setForeground(new java.awt.Color(255, 255, 255));
        LBLQuantity3.setText("Quantity:");
        PNL1.add(LBLQuantity3);
        LBLQuantity3.setBounds(670, 450, 70, 30);

        CBXItem3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        CBXItem3.setMaximumRowCount(5);
        CBXItem3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" }));
        CBXItem3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CBXItem3FocusLost(evt);
            }
        });
        PNL1.add(CBXItem3);
        CBXItem3.setBounds(750, 450, 90, 30);

        BTNBack.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBack.setText("Log Out");
        BTNBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBackActionPerformed(evt);
            }
        });
        PNL1.add(BTNBack);
        BTNBack.setBounds(30, 520, 100, 40);

        BTNInCart.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNInCart.setText("In Cart");
        BTNInCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNInCartActionPerformed(evt);
            }
        });
        PNL1.add(BTNInCart);
        BTNInCart.setBounds(247, 523, 180, 40);

        BTNNext.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNNext.setText("Next Page");
        BTNNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNNextActionPerformed(evt);
            }
        });
        PNL1.add(BTNNext);
        BTNNext.setBounds(480, 520, 180, 40);

        BTNExit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNExit.setText("Exit");
        BTNExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExitActionPerformed(evt);
            }
        });
        PNL1.add(BTNExit);
        BTNExit.setBounds(754, 520, 100, 38);

        LBLBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Background Photo.jpg"))); // NOI18N
        LBLBackground.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLBackgroundMouseClicked(evt);
            }
        });
        PNL1.add(LBLBackground);
        LBLBackground.setBounds(-6, -6, 890, 580);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNExitActionPerformed
        try
        {
            /**
             * Creates JOptionDialog with Yes/No Options renamed as Exit and Logout
             * When an option is chosen, it is saved in the YesorNo variable 
            */
            YesorNo = JOptionPane.showOptionDialog(new JFrame(), "Exit or Log Out", "CONFIRM", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, new Object[] {"EXIT", "LOGOUT"}, JOptionPane.YES_OPTION);

            ExitLogout_Code();
            
        }
        
        //Error Handling the Option Dialog Frame
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Executing the JFrame Components Properly", "JFRAME ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNExitActionPerformed

    private void BTNNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNNextActionPerformed
        try
        {
            //Passes the UserName and cart number to CustomerBuyGoods2 main method.
            PassingUserName();
            PassingCartNumber();
            CustomerBuyGoods2.main(loggedin, cart);

            //Opens the Next Page CustomerBuyGoods2
            new CustomerBuyGoods2().setVisible(true);
            this.setVisible(false);

        }
        
        //Error Handling BTNNext Action Event
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Going to the Next Page", "NEXT PAGE ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNNextActionPerformed

    private void BTNBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBackActionPerformed
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
    }//GEN-LAST:event_BTNBackActionPerformed

    private void BTNBuy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBuy1ActionPerformed
        LBLQuantity1.setVisible(true);
        CBXItem1.setVisible(true);
        
    }//GEN-LAST:event_BTNBuy1ActionPerformed

    private void BTNBuy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBuy2ActionPerformed
        LBLQuantity2.setVisible(true);
        CBXItem2.setVisible(true);
        
    }//GEN-LAST:event_BTNBuy2ActionPerformed

    private void BTNBuy3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBuy3ActionPerformed
        LBLQuantity3.setVisible(true);
        CBXItem3.setVisible(true);
        
    }//GEN-LAST:event_BTNBuy3ActionPerformed

    private void CBXItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXItem1ActionPerformed
        
    }//GEN-LAST:event_CBXItem1ActionPerformed

    private void CBXItem1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CBXItem1FocusLost
        try
        {
            //Sets Product Name 
            ProductName = LBLName1.getText();
            
            //Gets the Item at the Selected Index. Assigns it to String Variable.
            ProductQuantity = CBXItem1.getItemAt(CBXItem1.getSelectedIndex());

            //Gets the Product Price as set in the LBLPrice1.
            ProductPrice1 = Double.parseDouble(LBLPrice1.getText());

            //Calling Function
            CalculateShoppingAmount();
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "CBXITEM1 FOCUS EVENT ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_CBXItem1FocusLost

    private void CBXItem2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CBXItem2FocusLost
        try
        {
            //Sets Product Name 
            ProductName = LBLName2.getText();
            
            //Gets the Item at the Selected Index. Assigns it to String Variable.
            ProductQuantity = CBXItem2.getItemAt(CBXItem2.getSelectedIndex());

            //Gets the Product Price as set in the LBLPrice2.
            ProductPrice1 = Double.parseDouble(LBLPrice2.getText());

            //Calling Function
            CalculateShoppingAmount();
            
        }
        
        //Error Handling the Focus Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "CBXITEM2 FOCUS EVENT ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_CBXItem2FocusLost

    private void CBXItem3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CBXItem3FocusLost
        try
        {
            //Sets Product Name 
            ProductName = LBLName3.getText();
            
            //Gets the Item at the Selected Index. Assigns it to String Variable.
            ProductQuantity = CBXItem3.getItemAt(CBXItem3.getSelectedIndex());

            //Gets the Product Price as set in the LBLPrice3.
            ProductPrice1 = Double.parseDouble(LBLPrice3.getText());

            //Calling Function
            CalculateShoppingAmount();
            
        }
        
        //Error Handling the Focus Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "CBXITEM3 FOCUS EVENT ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_CBXItem3FocusLost

    private void LBLBackgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLBackgroundMouseClicked
        LBLBackground.transferFocus();
        
    }//GEN-LAST:event_LBLBackgroundMouseClicked

    private void LBLCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCartMouseClicked
        BTNInCart.doClick();
        
    }//GEN-LAST:event_LBLCartMouseClicked

    private void LBLCartFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LBLCartFocusGained
        
    }//GEN-LAST:event_LBLCartFocusGained

    private void LBLCartFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LBLCartFocusLost
        
    }//GEN-LAST:event_LBLCartFocusLost

    private void CBXItem1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CBXItem1FocusGained
        
    }//GEN-LAST:event_CBXItem1FocusGained

    private void LBLCartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCartMouseEntered
        LBLCart.setForeground(Color.green);
        LBLCartNo.setForeground(Color.green);
        
    }//GEN-LAST:event_LBLCartMouseEntered

    private void LBLCartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCartMouseExited
        LBLCart.setForeground(Color.yellow);
        LBLCartNo.setForeground(Color.yellow);
        
    }//GEN-LAST:event_LBLCartMouseExited

    private void BTNInCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNInCartActionPerformed
        try
        {
            int cartNo = Integer.parseInt(LBLCartNo.getText());
            if (cartNo == 0)
            {
                JOptionPane.showMessageDialog(null, "You Cannot Open an Empty Cart.");
                
            }
            
            else
            {
                //Passing UserName and CartNumber to the Next page
                PassingUserName();
                PassingCartNumber();
                CustomerCart.main(loggedin, cart);

                //Opening the AdminCart Page
                new CustomerCart().setVisible(true);
                this.setVisible(false);
                
            }
        }
        
        //Error Handling the Click Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Opening Cart", "ERROR OPENING CART", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNInCartActionPerformed

    /**
     * @param args the command line arguments
     * @param args2
     */
    public static void main(String args[], int args2[]) 
    {
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
            java.util.logging.Logger.getLogger(CustomerBuyGoods1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerBuyGoods1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerBuyGoods1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerBuyGoods1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton BTNBuy1;
    private javax.swing.JButton BTNBuy2;
    private javax.swing.JButton BTNBuy3;
    private javax.swing.JButton BTNExit;
    private javax.swing.JToggleButton BTNInCart;
    private javax.swing.JButton BTNNext;
    private javax.swing.JComboBox<String> CBXItem1;
    private javax.swing.JComboBox<String> CBXItem2;
    private javax.swing.JComboBox<String> CBXItem3;
    private javax.swing.JLabel KSH2;
    private javax.swing.JLabel KSH3;
    private javax.swing.JLabel LBLBackground;
    private javax.swing.JLabel LBLCart;
    private javax.swing.JLabel LBLCartNo;
    private javax.swing.JLabel LBLFaceGel;
    private javax.swing.JLabel LBLID1;
    private javax.swing.JLabel LBLID2;
    private javax.swing.JLabel LBLID3;
    private javax.swing.JLabel LBLKSH1;
    private javax.swing.JLabel LBLMSG;
    private javax.swing.JLabel LBLName1;
    private javax.swing.JLabel LBLName2;
    private javax.swing.JLabel LBLName3;
    private javax.swing.JLabel LBLPolo;
    private javax.swing.JLabel LBLPrice1;
    private javax.swing.JLabel LBLPrice2;
    private javax.swing.JLabel LBLPrice3;
    private javax.swing.JLabel LBLQuantity1;
    private javax.swing.JLabel LBLQuantity2;
    private javax.swing.JLabel LBLQuantity3;
    private javax.swing.JLabel LBLShampoo;
    private javax.swing.JPanel PNL1;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
