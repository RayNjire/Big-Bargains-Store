package Admin;

import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
import CallClasses.*;
import BeforeLogin.*;
import MySQLConnections.*;
import java.util.ArrayList;
import java.text.MessageFormat; 

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
*/
public class AdminCart extends javax.swing.JFrame
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
        cart[0] = ItemsInCart;
        
    }

    //Common Variables/
    String SQLCode;
    double ProductPrice1, totalPrice;
    int YesorNo;
    Connection conn;
    Statement NewStatement;
    ResultSet myResultSet;
    PreparedStatement preparedStatement;

    /**
     * Creates new form AdminCart
     * All Codes in here are run as the Form is being created
    */
    public AdminCart()
    {
        //Personalized Default Frame Appearance
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        
        //Hidden Components
        TBLCart2.hide();
        BTNDelete.hide();
        BTNUpdate.hide();
        CBXQuantity.hide();
        
        //Calling Functions
        show_shopping_in_table();
        CalculateTotalPrice();
        
    }
    
    public void CalculateTotalPrice()
    {
        try
        {
            conn = MySQLConnect.ConnectDB();
            NewStatement = conn.createStatement();
            myResultSet = NewStatement.executeQuery("select sum(ProductPrice) as COST from `" + LBLMSG.getText() + "_temp`");
            
            while (myResultSet.next())
            {   
                LBLTotalPrice1.setText(myResultSet.getString("COST"));
                
            }
            
            myResultSet = NewStatement.executeQuery("select sum(Quantity) as NewItemsInCart from `" + LBLMSG.getText() + "_temp`");
            
            while (myResultSet.next())
            {   
                ItemsInCart = myResultSet.getInt("NewItemsInCart");
                
            }
        }
        
        catch (SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage() + "\n\nError Calculating Total Price of Products", "CALCULATION ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
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
    
    /**
     * Calling Data from Database and setting it to an ArrayList
     * This is a Function. Return Type ArrayList. ArrayList Inheriting from CartsCallClass Function Name getShoppingList.
     * @return 
    */
    public ArrayList<CartsCallClass> getShoppingList()
    {
        ArrayList<CartsCallClass> shoppingList = new ArrayList<>();
        
        try
        {
            //Connect to Database and Run SQL Statement to show all Inventory Items
            conn = MySQLConnect.ConnectDB();
            SQLCode = "select * from `" + LBLMSG.getText() + "_temp`;";
            NewStatement = conn.createStatement();
            myResultSet = NewStatement.executeQuery(SQLCode);
            
            //Declaring Variable products as the inherited class
            CartsCallClass shopping;
            
            while (myResultSet.next())
            {
                //Set Users' data into the Inherited Class
                shopping = new CartsCallClass(myResultSet.getString("ProductBought"), myResultSet.getString("DateBought"), myResultSet.getDouble("Price"), myResultSet.getInt("Quantity"), myResultSet.getDouble("ProductPrice"));
                shoppingList.add(shopping);
               
            }
        }
        
        //Error Handling the Shopping ArrayList
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Unable to Get Data from Database", "DATA EXTRACTION ERROR", JOptionPane.ERROR_MESSAGE);
            new AdminBuyGoods1().setVisible(true);
            this.setVisible(false);
            
        }
        
        return shoppingList;
    }
    
    //Displaying Data in Table
    public void show_shopping_in_table()
    {
        //Run the Function getProductsList and Assign it to an ArrayList Variable named productsList
        ArrayList<CartsCallClass> shoppingList = getShoppingList();
        
        //Reset the Current View of the Table and let the database choose how it should look
        DefaultTableModel model = (DefaultTableModel)TBLCart.getModel();
        
        //This Array holds the columns and all their objects
        Object[] row = new Object[5];
        
        //Assign data to each row of the Table
        for(int i = 0; i < shoppingList.size(); i++)
        {
            row[0] = shoppingList.get(i).getProductBought();
            row[1] = shoppingList.get(i).getDateBought();
            row[2] = shoppingList.get(i).getPrice();
            row[3] = shoppingList.get(i).getQuantity();
            row[4] = shoppingList.get(i).getProductPrice();
            
            //Next Row
            model.addRow(row);
        }
        
        //FOR THE SECOND TABLE. This table is for printing purposes only. Please see BTNPay Action Event
        //Run the Function getProductsList and Assign it to an ArrayList Variable named productsList
        ArrayList<CartsCallClass> shoppingList2 = getShoppingList();
        
        //Reset the Current View of the Table and let the database choose how it should look
        DefaultTableModel model2 = (DefaultTableModel)TBLCart2.getModel();
        
        //This Array holds the columns and all their objects
        Object[] row2 = new Object[5];
        
        //Assign data to each row of the Table
        for(int j = 0; j < shoppingList2.size(); j++)
        {
            row2[0] = shoppingList2.get(j).getProductBought();
            row2[1] = shoppingList2.get(j).getDateBought();
            row2[2] = shoppingList2.get(j).getPrice();
            row2[3] = shoppingList2.get(j).getQuantity();
            row2[4] = shoppingList2.get(j).getProductPrice();
            
            //Next Row
            model2.addRow(row2);
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
        LBLProductName = new javax.swing.JLabel();
        LBLProductName1 = new javax.swing.JLabel();
        LBLDate = new javax.swing.JLabel();
        LBLDate1 = new javax.swing.JLabel();
        LBLPrice = new javax.swing.JLabel();
        LBLPrice1 = new javax.swing.JLabel();
        LBLQuantity = new javax.swing.JLabel();
        CBXQuantity = new javax.swing.JComboBox<>();
        LBLProductPrice = new javax.swing.JLabel();
        LBLPrice11 = new javax.swing.JLabel();
        BTNUpdate = new javax.swing.JToggleButton();
        BTNDelete = new javax.swing.JToggleButton();
        LBLTotalPrice = new javax.swing.JLabel();
        LBLTotalPrice1 = new javax.swing.JLabel();
        BTNClearAll = new javax.swing.JButton();
        BTNRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBLCart = new javax.swing.JTable();
        BTNPay = new javax.swing.JButton();
        BTNBack = new javax.swing.JButton();
        BTNExit = new javax.swing.JButton();
        LBLBackground = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TBLCart2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ADMIN'S CART");
        setResizable(false);

        PNL1.setLayout(null);

        LBLMSG.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLMSG.setForeground(new java.awt.Color(255, 255, 255));
        LBLMSG.setText("User Name:");
        LBLMSG.setText("" + LoggedInUser);
        PNL1.add(LBLMSG);
        LBLMSG.setBounds(40, 10, 140, 30);

        LBLProductName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLProductName.setForeground(new java.awt.Color(255, 255, 255));
        LBLProductName.setText("Product Name:");
        PNL1.add(LBLProductName);
        LBLProductName.setBounds(10, 80, 120, 30);

        LBLProductName1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLProductName1.setForeground(new java.awt.Color(255, 255, 255));
        LBLProductName1.setText("QWERTY");
        PNL1.add(LBLProductName1);
        LBLProductName1.setBounds(120, 80, 160, 30);

        LBLDate.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLDate.setForeground(new java.awt.Color(255, 255, 255));
        LBLDate.setText("Date:");
        PNL1.add(LBLDate);
        LBLDate.setBounds(70, 120, 90, 30);

        LBLDate1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLDate1.setForeground(new java.awt.Color(255, 255, 255));
        LBLDate1.setText("YYYY-MM-DD");
        PNL1.add(LBLDate1);
        LBLDate1.setBounds(120, 120, 200, 30);

        LBLPrice.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPrice.setForeground(new java.awt.Color(255, 255, 255));
        LBLPrice.setText("Price (x1):");
        PNL1.add(LBLPrice);
        LBLPrice.setBounds(30, 160, 90, 30);

        LBLPrice1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPrice1.setForeground(new java.awt.Color(255, 255, 255));
        LBLPrice1.setText("0.00");
        PNL1.add(LBLPrice1);
        LBLPrice1.setBounds(120, 160, 90, 30);

        LBLQuantity.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLQuantity.setForeground(new java.awt.Color(255, 255, 255));
        LBLQuantity.setText("Quantity:");
        PNL1.add(LBLQuantity);
        LBLQuantity.setBounds(40, 210, 100, 30);

        CBXQuantity.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        CBXQuantity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        CBXQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CBXQuantityFocusLost(evt);
            }
        });
        PNL1.add(CBXQuantity);
        CBXQuantity.setBounds(120, 210, 80, 30);

        LBLProductPrice.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLProductPrice.setForeground(new java.awt.Color(255, 255, 255));
        LBLProductPrice.setText("Product Price:");
        PNL1.add(LBLProductPrice);
        LBLProductPrice.setBounds(10, 260, 110, 30);

        LBLPrice11.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPrice11.setForeground(new java.awt.Color(255, 255, 255));
        LBLPrice11.setText("0.00");
        PNL1.add(LBLPrice11);
        LBLPrice11.setBounds(120, 260, 190, 30);

        BTNUpdate.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNUpdate.setText("Update");
        BTNUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNUpdateActionPerformed(evt);
            }
        });
        PNL1.add(BTNUpdate);
        BTNUpdate.setBounds(20, 310, 110, 40);

        BTNDelete.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNDelete.setText("Delete");
        BTNDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNDeleteActionPerformed(evt);
            }
        });
        PNL1.add(BTNDelete);
        BTNDelete.setBounds(160, 310, 120, 40);

        LBLTotalPrice.setFont(new java.awt.Font("Rockwell", 3, 20)); // NOI18N
        LBLTotalPrice.setForeground(new java.awt.Color(255, 102, 102));
        LBLTotalPrice.setText("Total Price:");
        PNL1.add(LBLTotalPrice);
        LBLTotalPrice.setBounds(40, 390, 120, 30);

        LBLTotalPrice1.setFont(new java.awt.Font("Rockwell", 3, 20)); // NOI18N
        LBLTotalPrice1.setForeground(new java.awt.Color(255, 102, 102));
        LBLTotalPrice1.setText("0.00");
        PNL1.add(LBLTotalPrice1);
        LBLTotalPrice1.setBounds(180, 390, 130, 30);

        BTNClearAll.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNClearAll.setText("Clear All");
        BTNClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNClearAllActionPerformed(evt);
            }
        });
        PNL1.add(BTNClearAll);
        BTNClearAll.setBounds(50, 450, 170, 40);

        BTNRefresh.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNRefresh.setText("Refresh Table");
        BTNRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRefreshActionPerformed(evt);
            }
        });
        PNL1.add(BTNRefresh);
        BTNRefresh.setBounds(720, 50, 130, 30);

        TBLCart.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TBLCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Date", "Price (x1)", "Quantity", "Product Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TBLCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLCartMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBLCart);

        PNL1.add(jScrollPane1);
        jScrollPane1.setBounds(320, 90, 540, 261);

        BTNPay.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNPay.setText("Pay for Goods");
        BTNPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNPayActionPerformed(evt);
            }
        });
        PNL1.add(BTNPay);
        BTNPay.setBounds(500, 390, 210, 60);

        BTNBack.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBack.setText("Back");
        BTNBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBackActionPerformed(evt);
            }
        });
        PNL1.add(BTNBack);
        BTNBack.setBounds(30, 530, 90, 40);

        BTNExit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNExit.setText("Exit");
        BTNExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExitActionPerformed(evt);
            }
        });
        PNL1.add(BTNExit);
        BTNExit.setBounds(760, 530, 90, 40);

        LBLBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Background Photo.jpg"))); // NOI18N
        LBLBackground.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLBackgroundMouseClicked(evt);
            }
        });
        PNL1.add(LBLBackground);
        LBLBackground.setBounds(0, 0, 880, 600);

        TBLCart2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TBLCart2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Date", "Price (x1)", "Quantity", "Product Price", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TBLCart2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLCart2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TBLCart2);

        PNL1.add(jScrollPane2);
        jScrollPane2.setBounds(-20, 40, 950, 540);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            YesorNo = JOptionPane.showOptionDialog(new JFrame(), "Exit or Log Out", "CONFIRM", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, new Object[] {"EXIT", "LOGOUT"}, JOptionPane.YES_OPTION);

            ExitLogout_Code();
            
        }
        
        //Error Handling the Option Dialog Frame
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "DATABASE ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNExitActionPerformed

    private void BTNPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNPayActionPerformed
        try
        {
            BTNClearAll.doClick();
            BTNRefresh.doClick();
            CalculateTotalPrice();
            
            /**
             * Creates JOptionDialog with Yes/No Options
             * When an option is chosen, it is saved in the YesorNo variable 
            */
            YesorNo = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to pay \nKSH " + LBLTotalPrice1.getText() + " \n to the Big Bargains Store?", "CONFIRM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
            null, new Object[] {"YES", "NO"}, JOptionPane.YES_OPTION);

            if (YesorNo == JOptionPane.YES_OPTION)
            {
                JOptionPane.showMessageDialog(null, "Please Print your receipt.");

                try
                {
                    MessageFormat header = new MessageFormat("Big Bargains Store Receipt.  \n\n\t\t" + LBLTotalPrice.getText() + " " + LBLTotalPrice1.getText());
                    MessageFormat footer = new MessageFormat("Page{0,number,integer}");
                    
                    TBLCart2.print(JTable.PrintMode.FIT_WIDTH,header,footer);
                    
                    YesorNo = JOptionPane.showOptionDialog(new JFrame(), "Thank you for shopping at the Big Bargains Store. \nWould you like to go back to shop or exit?", "CONFIRM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                    null, new Object[] {"Go Back to Shop", "Exit"}, JOptionPane.YES_OPTION);

                    SQLCode = "insert `" + LBLMSG.getText() + "` select * from `" + LBLMSG.getText() + "_temp`;";
                    executeSQLQuery(SQLCode);
                    
                    totalPrice = Double.parseDouble(LBLTotalPrice1.getText());
                    
                    SQLCode = "insert into `" + LBLMSG.getText() + "` (DateBought, TotalPrice) values (now(), " + totalPrice + ");";
                    executeSQLQuery(SQLCode);
                    
                    if (YesorNo == JOptionPane.YES_OPTION)
                    {
                        SQLCode = "truncate table `" + LBLMSG.getText() + "_temp`;";
                        executeSQLQuery(SQLCode);

                        ItemsInCart = 0;

                        PassingUserName();
                        PassingCartNumber();
                        AdminBuyGoods1.main(loggedin, cart);

                        new AdminBuyGoods1().setVisible(true);
                        this.setVisible(false);
                        
                    }

                    else if (YesorNo == JOptionPane.NO_OPTION)
                    {
                        SQLCode = "insert into LoginAttempt (UserName, Date, Attempt) values ('" + LBLMSG.getText() + "', Now(), 'Logged Out');";
                        executeSQLQuery(SQLCode);

                        SQLCode = "drop table if exists `" + LBLMSG.getText() + "_temp`;";
                        executeSQLQuery(SQLCode);

                        System.exit(0);
                        
                    }
                }

                catch (java.awt.print.PrinterException e)
                {
                    System.err.format("Cannot print the %s%n", e.getMessage());
                    
                }
            }
        }
        
        //Error Handling the Print Receipt Process
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Requesting for Payment from system", "PAYMENT ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNPayActionPerformed

    private void BTNClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNClearAllActionPerformed
        BTNDelete.hide();
        BTNUpdate.hide();
        CBXQuantity.hide();
        
        LBLProductName1.setText("QWERTY");
        LBLDate1.setText("YYYY-MM-DD");
        LBLPrice1.setText("0.00");
        CBXQuantity.setSelectedIndex(0);
        LBLPrice11.setText("0.00");
        
    }//GEN-LAST:event_BTNClearAllActionPerformed

    private void BTNBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBackActionPerformed
        PassingUserName();
        PassingCartNumber();
        AdminBuyGoods1.main(loggedin, cart);
        
        new AdminBuyGoods1().setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_BTNBackActionPerformed

    private void TBLCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLCartMouseClicked
        BTNDelete.show();
        BTNUpdate.show();
        CBXQuantity.show();
        
        int i = TBLCart.getSelectedRow();
        TableModel model = TBLCart.getModel();
        LBLProductName1.setText(model.getValueAt (i, 0).toString());
        LBLDate1.setText(model.getValueAt (i, 1).toString());
        LBLPrice1.setText(model.getValueAt (i, 2).toString());
        CBXQuantity.setSelectedIndex(model.getValueAt (i, 3).hashCode());
        LBLPrice11.setText(model.getValueAt (i, 4).toString());
        
    }//GEN-LAST:event_TBLCartMouseClicked

    private void LBLBackgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLBackgroundMouseClicked
        LBLBackground.transferFocus();
        
    }//GEN-LAST:event_LBLBackgroundMouseClicked

    private void BTNRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRefreshActionPerformed
        DefaultTableModel model = (DefaultTableModel)TBLCart.getModel();
        model.setRowCount(0);
                
        DefaultTableModel model2 = (DefaultTableModel)TBLCart2.getModel();
        model2.setRowCount(0);
        show_shopping_in_table();
        
    }//GEN-LAST:event_BTNRefreshActionPerformed

    private void BTNDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDeleteActionPerformed
        try
        {
            YesorNo = JOptionPane.showConfirmDialog(this, "  Are you sure you want to \n delete this item from your cart?", "DELETE DATA?", JOptionPane.YES_NO_OPTION);
        
            if (YesorNo == JOptionPane.YES_OPTION)
            {
                SQLCode = "delete from `" + LBLMSG.getText() + "_temp` where DateBought = '" + LBLDate1.getText() + "';";
                executeSQLQuery(SQLCode);
                
                BTNClearAll.doClick();
                BTNRefresh.doClick();
                CalculateTotalPrice();
                BTNDelete.hide();
                BTNUpdate.hide();
                CBXQuantity.hide();
                
            }
        }
        
        //Error Handling the Data Deletion Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR DELETING USER", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNDeleteActionPerformed

    private void BTNUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNUpdateActionPerformed
        try
        {
            //Convert the String value of the Selected Index to Integer
            int Quant1 = Integer.parseInt(CBXQuantity.getItemAt(CBXQuantity.getSelectedIndex()));
            YesorNo = JOptionPane.showConfirmDialog(this, "Update Quantity?", "UPDATE DATA?", JOptionPane.YES_NO_OPTION);
            
            if (YesorNo == JOptionPane.YES_OPTION)
            {
               SQLCode = "update `" + LBLMSG.getText() + "_temp` set Quantity = '" + Quant1 + "', ProductPrice = " + LBLPrice11.getText() + " where DateBought = '" + LBLDate1.getText() + "';";
               executeSQLQuery(SQLCode);
               
               BTNClearAll.doClick();
               BTNRefresh.doClick();
               CalculateTotalPrice();
               BTNDelete.hide();
               BTNUpdate.hide();
               CBXQuantity.hide();
               
            }
        }
        
        //Error Handling the Update Data Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR UPDATING USER DATA", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNUpdateActionPerformed

    private void TBLCart2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLCart2MouseClicked
        
    }//GEN-LAST:event_TBLCart2MouseClicked

    private void CBXQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CBXQuantityFocusLost
        //Convert the String value of the Selected Index to Integer
        int Quant1 = Integer.parseInt(CBXQuantity.getItemAt(CBXQuantity.getSelectedIndex()));

        double Priceof1 = Double.parseDouble(LBLPrice1.getText());

        totalPrice = Quant1 * Priceof1;

        LBLPrice11.setText(String.valueOf(totalPrice));

    }//GEN-LAST:event_CBXQuantityFocusLost

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
    //<editor-fold defaultstate="collapsed" desc=" Declared Variable of the Swing Objects (do not modify) ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNBack;
    private javax.swing.JButton BTNClearAll;
    private javax.swing.JToggleButton BTNDelete;
    private javax.swing.JButton BTNExit;
    private javax.swing.JButton BTNPay;
    private javax.swing.JButton BTNRefresh;
    private javax.swing.JToggleButton BTNUpdate;
    private javax.swing.JComboBox<String> CBXQuantity;
    private javax.swing.JLabel LBLBackground;
    private javax.swing.JLabel LBLDate;
    private javax.swing.JLabel LBLDate1;
    private javax.swing.JLabel LBLMSG;
    private javax.swing.JLabel LBLPrice;
    private javax.swing.JLabel LBLPrice1;
    private javax.swing.JLabel LBLPrice11;
    private javax.swing.JLabel LBLProductName;
    private javax.swing.JLabel LBLProductName1;
    private javax.swing.JLabel LBLProductPrice;
    private javax.swing.JLabel LBLQuantity;
    private javax.swing.JLabel LBLTotalPrice;
    private javax.swing.JLabel LBLTotalPrice1;
    private javax.swing.JPanel PNL1;
    public javax.swing.JTable TBLCart;
    public javax.swing.JTable TBLCart2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
