package Admin;

import CallClasses.*;
import BeforeLogin.*;
import MySQLConnections.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
import java.text.MessageFormat; 
import java.util.ArrayList;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
 */
public class EditInventory extends javax.swing.JFrame
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

    //Common Variables
    String SQLCode;
    int YesorNo;
    Connection conn;
    Statement NewStatement;
    ResultSet myResultSet;
    PreparedStatement preparedStatement;
    
    /**
     * Creates new form EditInventory
     * All Codes in here are run as the Form is being created
    */
    public EditInventory()
    {
        //Personalized Default Frame Appearance
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Methods That Run as the Form is Created
        show_products_in_table();
        HideObjects();
        
    }
    
    //Sets Components Hidden
    public void HideObjects()
    {
        LBLInvID.hide();
        LBLInvID2.hide();
        LBLProdName.hide();
        LBLProdName2.hide();
        
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
     * This is a Function. Return Type ArrayList. ArrayList Inheriting from EditInventoryCallClass Function Name getProductsList.
     * @return 
    */
    public ArrayList<EditInventoryCallClass> getProductsList()
    {
        ArrayList<EditInventoryCallClass> productsList = new ArrayList<>();
        
        try
        {
            //Connect to Database and Run SQL Statement to show all Inventory Items
            conn = MySQLConnect.ConnectDB();
            SQLCode = "select * from inventory;";
            NewStatement = conn.createStatement();
            myResultSet = NewStatement.executeQuery(SQLCode);
            
            //Declaring Variable products as the inherited class
            EditInventoryCallClass products;
            
            while (myResultSet.next())
            {
                //Set Users' data into the Inherited Class
                products = new EditInventoryCallClass(myResultSet.getInt("InvID"), myResultSet.getString("Product"), myResultSet.getString("GroupCode"), myResultSet.getString("Groups"), myResultSet.getDouble("PriceinKSH"), myResultSet.getInt("Quantity"));
                productsList.add(products);
               
            }
        }
        
        //Error Handling the Inventory ArrayList
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Unable to Get Data from Database", "DATA EXTRACTION ERROR", JOptionPane.ERROR_MESSAGE);
            new AdminsPower().setVisible(true);
            this.setVisible(false);
            
        }
        
        return productsList;
    }
    
    //Displaying Data in Table
    public void show_products_in_table()
    {
        //Run the Function getProductsList and Assign it to an ArrayList Variable named productsList
        ArrayList<EditInventoryCallClass> productsList = getProductsList();
        
        //Reset the Current View of the Table and let the database choose how it should look
        DefaultTableModel model = (DefaultTableModel)TBLProducts.getModel();
        
        //This Array holds the columns and all their objects
        Object[] row = new Object[6];
        
        //Assign data to each row of the Table
        for(int i = 0; i < productsList.size(); i++)
        {
            row[0] = productsList.get(i).getInvID();
            row[1] = productsList.get(i).getProduct();
            row[2] = productsList.get(i).getGroupCode();
            row[3] = productsList.get(i).getGroups();
            row[4] = productsList.get(i).getPriceinKSH();
            row[5] = productsList.get(i).getQuantity();
            
            //Next Row
            model.addRow(row);
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
        LBLInvID = new javax.swing.JLabel();
        LBLInvID2 = new javax.swing.JLabel();
        LBLProdName = new javax.swing.JLabel();
        LBLProdName2 = new javax.swing.JLabel();
        LBLGroupCode = new javax.swing.JLabel();
        LBLGroupCode2 = new javax.swing.JLabel();
        LBLGroups = new javax.swing.JLabel();
        TXTGroups = new javax.swing.JTextField();
        LBLPrice = new javax.swing.JLabel();
        TXTPrice = new javax.swing.JTextField();
        LBLQuantity = new javax.swing.JLabel();
        TXTQuantity = new javax.swing.JTextField();
        BTNUpdate = new javax.swing.JButton();
        BTNClearAll = new javax.swing.JButton();
        BTNRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBLProducts = new javax.swing.JTable();
        BTNPrint = new javax.swing.JButton();
        BTNBack = new javax.swing.JButton();
        BTNExit = new javax.swing.JButton();
        LBLBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("EDIT INVENTORY");
        setResizable(false);

        PNL1.setLayout(null);

        LBLMSG.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLMSG.setForeground(new java.awt.Color(255, 255, 255));
        LBLMSG.setText("User Name:");
        LBLMSG.setText("" + LoggedInUser);
        PNL1.add(LBLMSG);
        LBLMSG.setBounds(30, 10, 140, 30);

        LBLInvID.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLInvID.setForeground(new java.awt.Color(255, 255, 255));
        LBLInvID.setText("Inventory ID:");
        PNL1.add(LBLInvID);
        LBLInvID.setBounds(30, 80, 90, 20);

        LBLInvID2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLInvID2.setForeground(new java.awt.Color(255, 255, 255));
        LBLInvID2.setText("InvID");
        PNL1.add(LBLInvID2);
        LBLInvID2.setBounds(130, 80, 90, 20);

        LBLProdName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLProdName.setForeground(new java.awt.Color(255, 255, 255));
        LBLProdName.setText("Product Name:");
        PNL1.add(LBLProdName);
        LBLProdName.setBounds(20, 130, 100, 20);

        LBLProdName2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLProdName2.setForeground(new java.awt.Color(255, 255, 255));
        LBLProdName2.setText("ProdName");
        PNL1.add(LBLProdName2);
        LBLProdName2.setBounds(130, 130, 100, 20);

        LBLGroupCode.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLGroupCode.setForeground(new java.awt.Color(255, 255, 255));
        LBLGroupCode.setText("Group Code:");
        PNL1.add(LBLGroupCode);
        LBLGroupCode.setBounds(30, 180, 100, 20);

        LBLGroupCode2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLGroupCode2.setForeground(new java.awt.Color(255, 255, 255));
        LBLGroupCode2.setText("GroupCode");
        PNL1.add(LBLGroupCode2);
        LBLGroupCode2.setBounds(130, 180, 100, 20);

        LBLGroups.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLGroups.setForeground(new java.awt.Color(255, 255, 255));
        LBLGroups.setText("Groups:");
        PNL1.add(LBLGroups);
        LBLGroups.setBounds(60, 230, 70, 20);

        TXTGroups.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTGroupsActionPerformed(evt);
            }
        });
        PNL1.add(TXTGroups);
        TXTGroups.setBounds(130, 220, 160, 30);

        LBLPrice.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPrice.setForeground(new java.awt.Color(255, 255, 255));
        LBLPrice.setText("Price (KSH):");
        PNL1.add(LBLPrice);
        LBLPrice.setBounds(30, 280, 100, 20);

        TXTPrice.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        PNL1.add(TXTPrice);
        TXTPrice.setBounds(130, 270, 160, 30);

        LBLQuantity.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLQuantity.setForeground(new java.awt.Color(255, 255, 255));
        LBLQuantity.setText("Quantity:");
        PNL1.add(LBLQuantity);
        LBLQuantity.setBounds(50, 330, 70, 20);

        TXTQuantity.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        PNL1.add(TXTQuantity);
        TXTQuantity.setBounds(130, 320, 160, 30);

        BTNUpdate.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNUpdate.setText("Update");
        BTNUpdate.setToolTipText("Updates User Data");
        BTNUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNUpdateActionPerformed(evt);
            }
        });
        PNL1.add(BTNUpdate);
        BTNUpdate.setBounds(130, 390, 160, 42);

        BTNClearAll.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNClearAll.setText("Clear Textfields");
        BTNClearAll.setToolTipText("Clears Textfields without Removing Data from Database");
        BTNClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNClearAllActionPerformed(evt);
            }
        });
        PNL1.add(BTNClearAll);
        BTNClearAll.setBounds(130, 450, 160, 38);

        BTNRefresh.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNRefresh.setText("Refresh Table");
        BTNRefresh.setToolTipText("Manually Refreshe the Table");
        BTNRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRefreshActionPerformed(evt);
            }
        });
        PNL1.add(BTNRefresh);
        BTNRefresh.setBounds(710, 20, 130, 30);

        TBLProducts.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TBLProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Inventory ID", "Product", "Group Code", "Groups", "Price (KSH)", "Quantity"
            }
        ));
        TBLProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBLProducts);

        PNL1.add(jScrollPane1);
        jScrollPane1.setBounds(350, 70, 500, 280);

        BTNPrint.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNPrint.setText("Print Table");
        BTNPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNPrintActionPerformed(evt);
            }
        });
        PNL1.add(BTNPrint);
        BTNPrint.setBounds(560, 390, 133, 38);

        BTNBack.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBack.setText("Back");
        BTNBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBackActionPerformed(evt);
            }
        });
        PNL1.add(BTNBack);
        BTNBack.setBounds(30, 520, 84, 38);

        BTNExit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNExit.setText("Exit");
        BTNExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExitActionPerformed(evt);
            }
        });
        PNL1.add(BTNExit);
        BTNExit.setBounds(770, 510, 84, 38);

        LBLBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Background Photo.jpg"))); // NOI18N
        PNL1.add(LBLBackground);
        LBLBackground.setBounds(-1, -6, 880, 610);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTGroupsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTGroupsActionPerformed

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

    private void TBLProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLProductsMouseClicked
        try
        {
            LBLInvID.show();
            LBLInvID2.show();
            LBLProdName.show();
            LBLProdName2.show();

            //Get the Selected Row, Assign the value to integer variable i
            int i = TBLProducts.getSelectedRow();
            
            //Get the Table Model, assign it to the TableModel variable model
            TableModel model = TBLProducts.getModel();
            
            //Set Each Row to its respective Label or Textbox
            LBLInvID2.setText(model.getValueAt (i, 0).toString());
            LBLProdName2.setText(model.getValueAt (i, 1).toString());
            LBLGroupCode2.setText(model.getValueAt (i, 2).toString());
            TXTGroups.setText(model.getValueAt (i, 3).toString());
            TXTPrice.setText(model.getValueAt (i, 4).toString());
            TXTQuantity.setText(model.getValueAt (i, 5).toString());
        }
        
        //Error Handling Table Click Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Showing the data to the Labels and Textboxes", "ERROR CREATING DATA", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_TBLProductsMouseClicked

    private void BTNUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNUpdateActionPerformed
        try
        {
            YesorNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to update user data?", "UPDATE USER DATA?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            if (YesorNo == JOptionPane.YES_OPTION)
            {
                SQLCode = "update inventory set GroupCode = '" + LBLGroupCode2.getText() + "', Groups = '" + TXTGroups.getText() + "', PriceinKSH = " + TXTPrice.getText() + ", Quantity = " + TXTQuantity.getText() + " where InvID = " + LBLInvID2.getText() + ";";
                executeSQLQuery(SQLCode);
                
                JOptionPane.showMessageDialog(null, "Product information has been updated sucessfully!", "UPDATE SUCESSFUL", JOptionPane.INFORMATION_MESSAGE);
                BTNClearAll.doClick();
                BTNRefresh.doClick();
                
            }
        }
        
        //Error Handling the Update Inventory Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR UPDATING USER DATA", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNUpdateActionPerformed

    private void BTNClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNClearAllActionPerformed
        try
        {
            HideObjects();
            LBLGroupCode2.setText("");
            TXTGroups.setText("");
            TXTQuantity.setText("");
            TXTPrice.setText("");

        }
        
        //Error Handling the Update Inventory Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR UPDATING USER DATA", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNClearAllActionPerformed

    private void BTNRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRefreshActionPerformed
        try
        {
            //Sets the current Table Model to Default
            DefaultTableModel model = (DefaultTableModel)TBLProducts.getModel();
            
            //Empties the Rows of the Table
            model.setRowCount(0);
            
            //Re-inserts the Data as it is in the Database
            show_products_in_table();
            
        }
        
        //Error Handling the BTNRefresh Action Event
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Refreshing the Customer Table", "ERROR REFRESHING THE TABLE", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNRefreshActionPerformed

    private void BTNPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNPrintActionPerformed
        try
        {
            MessageFormat header = new MessageFormat("Report Data");
            MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        
            TBLProducts.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        
        }
        
        catch(java.awt.print.PrinterException e)
        {
            System.err.format("Cannot print the %s%n", e.getMessage());
            
        }
    }//GEN-LAST:event_BTNPrintActionPerformed

    private void BTNBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBackActionPerformed
        try
        {
            //Pass UserName and CartNumber to the Next Page
            PassingUserName();
            PassingCartNumber();
            AdminsPower.main(loggedin, cart);

            //Open Next Page
            new AdminsPower().setVisible(true);
            this.setVisible(false);
        
        }
            
        //Error Handling Opening of the AdminsPower Page
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nAn error occured while going back", "ERROR GOING BACK", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNBackActionPerformed

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
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton BTNBack;
    private javax.swing.JButton BTNClearAll;
    private javax.swing.JButton BTNExit;
    private javax.swing.JButton BTNPrint;
    private javax.swing.JButton BTNRefresh;
    private javax.swing.JButton BTNUpdate;
    private javax.swing.JLabel LBLBackground;
    private javax.swing.JLabel LBLGroupCode;
    private javax.swing.JLabel LBLGroupCode2;
    private javax.swing.JLabel LBLGroups;
    private javax.swing.JLabel LBLInvID;
    private javax.swing.JLabel LBLInvID2;
    private javax.swing.JLabel LBLMSG;
    private javax.swing.JLabel LBLPrice;
    private javax.swing.JLabel LBLProdName;
    private javax.swing.JLabel LBLProdName2;
    private javax.swing.JLabel LBLQuantity;
    private javax.swing.JPanel PNL1;
    private javax.swing.JTable TBLProducts;
    private javax.swing.JTextField TXTGroups;
    private javax.swing.JTextField TXTPrice;
    private javax.swing.JTextField TXTQuantity;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
