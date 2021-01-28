package Admin;

import CallClasses.*;
import BeforeLogin.*;
import MySQLConnections.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/** Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
 */
public class EditAllUsers extends javax.swing.JFrame 
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
    Statement NewStatement;
    int YesorNo;
    ResultSet myResultSet;
            
    /**
     * Creates new form EditAllUsers
     * All Codes in here are run as the Form is being created
    */
    public EditAllUsers() 
    {
        //Personalized Default Frame Appearance
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        LBLMSG.hide();
        
        //Methods That Run as the Form is Created
        HideObjects();
        show_customers_in_table();
        show_administrators_in_table();
        
    }
    
    //Sets Components Hidden
    public void HideObjects()
    {
        LBLCustID.hide();
        LBLCustID2.hide();
        LBLAdminID.hide();
        LBLAdminID2.hide();
        BTNDeleteAdmin.hide();
        BTNUpdateAdmin.hide();
        BTNDeleteCust.hide();
        BTNUpdateCust.hide();
        
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
     * This is a Function. Return Type ArrayList. Function Name getCustomersList.
     * @return 
    */
    public ArrayList<EditAllUsersCallClass_Customers> getCustomersList()
    {
        ArrayList<EditAllUsersCallClass_Customers> customersList = new ArrayList<>();
        
        try
        {
            //Connect to Database and Run SQL Statement to show all Administrator Users
            conn = MySQLConnect.ConnectDB();
            SQLCode = "select * from customers;";
            NewStatement = conn.createStatement();
            myResultSet = NewStatement.executeQuery(SQLCode);
            
            //Declaring Variable customers as the inherited class
            EditAllUsersCallClass_Customers customers;
            
            while (myResultSet.next())
            {
                //Set Users' data into the Inherited Class 
                customers = new EditAllUsersCallClass_Customers(myResultSet.getInt("CustID"), myResultSet.getString("FirstName"), myResultSet.getString("LastName"), myResultSet.getString("UserName"), myResultSet.getString("Password"), myResultSet.getInt("PhoneNumber"), myResultSet.getString("Email"));
                customersList.add(customers);
                
            }
        }
        
        //Error Handling the Customer ArrayList
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Unable to Get Data from Database", "DATA EXTRACTION ERROR", JOptionPane.ERROR_MESSAGE);
            new AdminsPower().setVisible(true);
            this.setVisible(false);
            
        }
        
        return customersList;
    }
    
    /**
     * Calling Data from Database and setting it to an ArrayList
     * This is a Function. Return Type ArrayList. Function Name getAdministratorsList.
     * @return 
    */
    public ArrayList<EditAllUsersCallClass_Admin> getAdministratorsList()
    {
        ArrayList<EditAllUsersCallClass_Admin> administratorsList = new ArrayList<>();
        
        try
        {
            //Connect to Database and Run SQL Statement to show all Administrator Users
            conn = MySQLConnect.ConnectDB();
            SQLCode = "select * from adminlogin;";
            NewStatement = conn.createStatement();
            myResultSet = NewStatement.executeQuery(SQLCode);
            
            //Declaring Variable administrators as the inherited class
            EditAllUsersCallClass_Admin administrators;
           
            while (myResultSet.next())
            {
                //Set Users' data into the Inherited Class 
                administrators = new EditAllUsersCallClass_Admin(myResultSet.getInt("AdminID"), myResultSet.getString("UserName"), myResultSet.getString("Password")); 
                administratorsList.add(administrators);
                
            }
        }
        
        //Error Handling the Admin ArrayList
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Unable to Get Data from Database", "DATA EXTRACTION ERROR", JOptionPane.ERROR_MESSAGE);
            new AdminsPower().setVisible(true);
            this.setVisible(false);
            
        }
        
        return administratorsList;
    }
    
    
    //Displaying Data in Admin Table
    public void show_administrators_in_table()
    {
        try
        {
            //Run the Function getAdministratorsList and Assign it to an ArrayList Variable named adminList
            ArrayList<EditAllUsersCallClass_Admin> adminList = getAdministratorsList();

            //Reset the Current View of the Table and let the database choose how it should look
            DefaultTableModel model = (DefaultTableModel)TBLAdministrators.getModel();

            //This Array holds the columns and all their objects
            Object[] row = new Object[3];

            //Assign data to each row of the Table
            for(int i = 0; i < adminList.size(); i++)
            {
                row[0] = adminList.get(i).getAdminID();
                row[1] = adminList.get(i).getAdminUserName();
                row[2] = adminList.get(i).getAdminPassword();

                //Next Row
                model.addRow(row);
            }
        }
        
        //Error Handling Displaying data in Admin Table
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Putting Administrator Details to the ArrayList", "ADMIN ARRAYLIST ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    
    //Displaying Data in Customer Table
    public void show_customers_in_table()
    {
        try
        {
            //Run the Function getCustomersList and Assign it to an ArrayList Variable named customerList
            ArrayList<EditAllUsersCallClass_Customers> customerList = getCustomersList();

            //Reset the Current View of the Table and let the database choose how it should look
            DefaultTableModel model = (DefaultTableModel)TBLCustomers.getModel();

            //This Array holds the columns and all their objects
            Object[] row = new Object[7];

            //Assign data to each row of the Table
            for(int i = 0; i < customerList.size(); i++)
            {
                row[0] = customerList.get(i).getCustomerID();
                row[1] = customerList.get(i).getFirstName();
                row[2] = customerList.get(i).getLastName();
                row[3] = customerList.get(i).getCustUserName();
                row[4] = customerList.get(i).getCustPassword();
                row[5] = customerList.get(i).getPhoneNumber();
                row[6] = customerList.get(i).getEmail();

                //Next Row
                model.addRow(row);
            }
        }
        
        //Error Handling Displaying Data in Customer Table
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Putting Administrator Details to the ArrayList", "ADMIN ARRAYLIST ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    
    //Method to execute create table SQL Statements that run in this Class
    public void createTable(String query)
    {
        try
        {
            //Execute the create table query
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            
        }
        
        //Error Handling the SQL Statements
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage() + "\n\nThe create table statement did not execute properly", "ERROR EXECUTING CREATE TABLE QUERY", JOptionPane.ERROR_MESSAGE);
            
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
    
    public void ClearAdmin()
    {
        LBLAdminID.hide();
        LBLAdminID2.hide();
        TXTAdminUserName.setText("");
        TXTAdminPassword.setText("");
        BTNDeleteAdmin.hide();
        BTNUpdateAdmin.hide();
        
    }
    
    public void ClearCustomer()
    {
        LBLCustID.hide();
        LBLCustID2.hide();
        TXTFirstName.setText("");
        TXTLastName.setText("");
        TXTCustUserName.setText("");
        TXTCustPassword.setText("");
        TXTPhoneNumber.setText("07");
        TXTEmail.setText("");
        BTNDeleteCust.hide();
        BTNUpdateCust.hide();
        
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
        LBLCustID = new javax.swing.JLabel();
        LBLCustID2 = new javax.swing.JLabel();
        LBL1 = new javax.swing.JLabel();
        LBLFirstName = new javax.swing.JLabel();
        TXTFirstName = new javax.swing.JTextField();
        LBL2 = new javax.swing.JLabel();
        LBLLastName = new javax.swing.JLabel();
        TXTLastName = new javax.swing.JTextField();
        LBL3 = new javax.swing.JLabel();
        LBLCustUserName = new javax.swing.JLabel();
        TXTCustUserName = new javax.swing.JTextField();
        LBL4 = new javax.swing.JLabel();
        LBLCustPassword = new javax.swing.JLabel();
        TXTCustPassword = new javax.swing.JTextField();
        LBLPhoneNumber = new javax.swing.JLabel();
        TXTPhoneNumber = new javax.swing.JTextField();
        LBLEmail = new javax.swing.JLabel();
        TXTEmail = new javax.swing.JTextField();
        BTNInsertCust = new javax.swing.JButton();
        BTNDeleteCust = new javax.swing.JButton();
        BTNUpdateCust = new javax.swing.JButton();
        LBLCustTable = new javax.swing.JLabel();
        BTNRefreshCust = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TBLCustomers = new javax.swing.JTable();
        LBLAdminID = new javax.swing.JLabel();
        LBLAdminID2 = new javax.swing.JLabel();
        LBL5 = new javax.swing.JLabel();
        LBLAdminUserName = new javax.swing.JLabel();
        TXTAdminUserName = new javax.swing.JTextField();
        LBL6 = new javax.swing.JLabel();
        LBLAdminPassword = new javax.swing.JLabel();
        TXTAdminPassword = new javax.swing.JTextField();
        BTNInsertAdmin = new javax.swing.JButton();
        BTNUpdateAdmin = new javax.swing.JButton();
        BTNDeleteAdmin = new javax.swing.JButton();
        BTNClearAll = new javax.swing.JButton();
        LBLAdminTable = new javax.swing.JLabel();
        BTNRefreshAdmin = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBLAdministrators = new javax.swing.JTable();
        BTNBack = new javax.swing.JButton();
        BTNExit = new javax.swing.JButton();
        LBLBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("EDIT ALL USERS");
        setName("JFrame1"); // NOI18N
        setResizable(false);

        PNL1.setLayout(null);

        LBLMSG.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLMSG.setForeground(new java.awt.Color(255, 255, 255));
        LBLMSG.setText("User Name:");
        LBLMSG.setText("" + LoggedInUser);
        PNL1.add(LBLMSG);
        LBLMSG.setBounds(320, 20, 140, 30);

        LBLCustID.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLCustID.setForeground(new java.awt.Color(255, 255, 255));
        LBLCustID.setText("Customer ID:");
        PNL1.add(LBLCustID);
        LBLCustID.setBounds(30, 40, 90, 20);

        LBLCustID2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLCustID2.setForeground(new java.awt.Color(255, 255, 255));
        LBLCustID2.setText("CustID");
        PNL1.add(LBLCustID2);
        LBLCustID2.setBounds(140, 40, 90, 20);

        LBL1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL1.setForeground(new java.awt.Color(255, 51, 51));
        LBL1.setText("*");
        PNL1.add(LBL1);
        LBL1.setBounds(20, 80, 8, 17);

        LBLFirstName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLFirstName.setForeground(new java.awt.Color(255, 255, 255));
        LBLFirstName.setText("First Name:");
        PNL1.add(LBLFirstName);
        LBLFirstName.setBounds(40, 80, 80, 20);

        TXTFirstName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTFirstNameActionPerformed(evt);
            }
        });
        PNL1.add(TXTFirstName);
        TXTFirstName.setBounds(130, 70, 160, 30);

        LBL2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL2.setForeground(new java.awt.Color(255, 51, 51));
        LBL2.setText("*");
        PNL1.add(LBL2);
        LBL2.setBounds(20, 120, 8, 17);

        LBLLastName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLLastName.setForeground(new java.awt.Color(255, 255, 255));
        LBLLastName.setText("Last Name:");
        PNL1.add(LBLLastName);
        LBLLastName.setBounds(40, 120, 80, 20);

        TXTLastName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        PNL1.add(TXTLastName);
        TXTLastName.setBounds(130, 110, 160, 30);

        LBL3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL3.setForeground(new java.awt.Color(255, 51, 51));
        LBL3.setText("*");
        PNL1.add(LBL3);
        LBL3.setBounds(20, 160, 8, 17);

        LBLCustUserName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLCustUserName.setForeground(new java.awt.Color(255, 255, 255));
        LBLCustUserName.setText("UserName:");
        PNL1.add(LBLCustUserName);
        LBLCustUserName.setBounds(40, 160, 80, 20);

        TXTCustUserName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTCustUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTCustUserNameActionPerformed(evt);
            }
        });
        PNL1.add(TXTCustUserName);
        TXTCustUserName.setBounds(130, 150, 160, 30);

        LBL4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL4.setForeground(new java.awt.Color(255, 51, 51));
        LBL4.setText("*");
        PNL1.add(LBL4);
        LBL4.setBounds(20, 200, 8, 17);

        LBLCustPassword.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLCustPassword.setForeground(new java.awt.Color(255, 255, 255));
        LBLCustPassword.setText("Password:");
        PNL1.add(LBLCustPassword);
        LBLCustPassword.setBounds(40, 200, 80, 20);

        TXTCustPassword.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        PNL1.add(TXTCustPassword);
        TXTCustPassword.setBounds(130, 190, 160, 30);

        LBLPhoneNumber.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLPhoneNumber.setForeground(new java.awt.Color(255, 255, 255));
        LBLPhoneNumber.setText("Phone Number:");
        PNL1.add(LBLPhoneNumber);
        LBLPhoneNumber.setBounds(10, 240, 110, 20);

        TXTPhoneNumber.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TXTPhoneNumber.setText("07");
        PNL1.add(TXTPhoneNumber);
        TXTPhoneNumber.setBounds(130, 230, 160, 30);

        LBLEmail.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLEmail.setForeground(new java.awt.Color(255, 255, 255));
        LBLEmail.setText("Email:");
        PNL1.add(LBLEmail);
        LBLEmail.setBounds(70, 280, 60, 18);

        TXTEmail.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        PNL1.add(TXTEmail);
        TXTEmail.setBounds(130, 270, 160, 30);

        BTNInsertCust.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNInsertCust.setText("Insert");
        BTNInsertCust.setToolTipText("Creates a New Customer.");
        BTNInsertCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNInsertCustActionPerformed(evt);
            }
        });
        PNL1.add(BTNInsertCust);
        BTNInsertCust.setBounds(50, 320, 76, 42);

        BTNDeleteCust.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNDeleteCust.setText("Delete");
        BTNDeleteCust.setToolTipText("Deletes the Selected Customer");
        BTNDeleteCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNDeleteCustActionPerformed(evt);
            }
        });
        PNL1.add(BTNDeleteCust);
        BTNDeleteCust.setBounds(280, 320, 79, 42);

        BTNUpdateCust.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNUpdateCust.setText("Update");
        BTNUpdateCust.setToolTipText("Commits Changes Made to a Customer's Information to the Database.");
        BTNUpdateCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNUpdateCustActionPerformed(evt);
            }
        });
        PNL1.add(BTNUpdateCust);
        BTNUpdateCust.setBounds(170, 320, 79, 42);

        LBLCustTable.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLCustTable.setForeground(new java.awt.Color(255, 255, 255));
        LBLCustTable.setText("CUSTOMER TABLE");
        PNL1.add(LBLCustTable);
        LBLCustTable.setBounds(500, 20, 140, 20);

        BTNRefreshCust.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNRefreshCust.setText("Refresh Table");
        BTNRefreshCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRefreshCustActionPerformed(evt);
            }
        });
        PNL1.add(BTNRefreshCust);
        BTNRefreshCust.setBounds(740, 20, 130, 30);

        TBLCustomers.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TBLCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "First Name", "Last Name", "UserName", "Password", "Phone Number", "Email"
            }
        ));
        TBLCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLCustomersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TBLCustomers);

        PNL1.add(jScrollPane2);
        jScrollPane2.setBounds(300, 60, 570, 210);

        LBLAdminID.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLAdminID.setForeground(new java.awt.Color(255, 255, 255));
        LBLAdminID.setText("Admin ID:");
        PNL1.add(LBLAdminID);
        LBLAdminID.setBounds(30, 390, 90, 20);

        LBLAdminID2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLAdminID2.setForeground(new java.awt.Color(255, 255, 255));
        LBLAdminID2.setText("AdminID");
        PNL1.add(LBLAdminID2);
        LBLAdminID2.setBounds(130, 390, 90, 20);

        LBL5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL5.setForeground(new java.awt.Color(255, 51, 51));
        LBL5.setText("*");
        PNL1.add(LBL5);
        LBL5.setBounds(10, 440, 8, 17);

        LBLAdminUserName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLAdminUserName.setForeground(new java.awt.Color(255, 255, 255));
        LBLAdminUserName.setText("UserName:");
        PNL1.add(LBLAdminUserName);
        LBLAdminUserName.setBounds(30, 440, 80, 20);

        TXTAdminUserName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        PNL1.add(TXTAdminUserName);
        TXTAdminUserName.setBounds(130, 430, 160, 30);

        LBL6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBL6.setForeground(new java.awt.Color(255, 51, 51));
        LBL6.setText("*");
        PNL1.add(LBL6);
        LBL6.setBounds(10, 490, 8, 17);

        LBLAdminPassword.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLAdminPassword.setForeground(new java.awt.Color(255, 255, 255));
        LBLAdminPassword.setText("Password:");
        PNL1.add(LBLAdminPassword);
        LBLAdminPassword.setBounds(30, 490, 80, 20);

        TXTAdminPassword.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        PNL1.add(TXTAdminPassword);
        TXTAdminPassword.setBounds(130, 480, 160, 30);

        BTNInsertAdmin.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNInsertAdmin.setText("Insert");
        BTNInsertAdmin.setToolTipText("Creates a New Administrator.");
        BTNInsertAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNInsertAdminActionPerformed(evt);
            }
        });
        PNL1.add(BTNInsertAdmin);
        BTNInsertAdmin.setBounds(60, 530, 76, 42);

        BTNUpdateAdmin.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNUpdateAdmin.setText("Update");
        BTNUpdateAdmin.setToolTipText("Commits Changes Made to an Administrator's Information to the Database.");
        BTNUpdateAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNUpdateAdminActionPerformed(evt);
            }
        });
        PNL1.add(BTNUpdateAdmin);
        BTNUpdateAdmin.setBounds(170, 530, 79, 42);

        BTNDeleteAdmin.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNDeleteAdmin.setText("Delete");
        BTNDeleteAdmin.setToolTipText("Deletes the Selected Administrator");
        BTNDeleteAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNDeleteAdminActionPerformed(evt);
            }
        });
        PNL1.add(BTNDeleteAdmin);
        BTNDeleteAdmin.setBounds(280, 530, 79, 42);

        BTNClearAll.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNClearAll.setText("Clear Textfields");
        BTNClearAll.setToolTipText("");
        BTNClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNClearAllActionPerformed(evt);
            }
        });
        PNL1.add(BTNClearAll);
        BTNClearAll.setBounds(60, 590, 300, 38);

        LBLAdminTable.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LBLAdminTable.setForeground(new java.awt.Color(255, 255, 255));
        LBLAdminTable.setText("ADMIN TABLE");
        PNL1.add(LBLAdminTable);
        LBLAdminTable.setBounds(550, 330, 110, 20);

        BTNRefreshAdmin.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNRefreshAdmin.setText("Refresh Table");
        BTNRefreshAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRefreshAdminActionPerformed(evt);
            }
        });
        PNL1.add(BTNRefreshAdmin);
        BTNRefreshAdmin.setBounds(740, 330, 130, 30);

        TBLAdministrators.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        TBLAdministrators.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Admin ID", "UserName", "Password"
            }
        ));
        TBLAdministrators.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLAdministratorsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBLAdministrators);

        PNL1.add(jScrollPane1);
        jScrollPane1.setBounds(390, 370, 480, 200);

        BTNBack.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBack.setText("Back");
        BTNBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBackActionPerformed(evt);
            }
        });
        PNL1.add(BTNBack);
        BTNBack.setBounds(390, 590, 84, 38);

        BTNExit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNExit.setText("Exit");
        BTNExit.setToolTipText("Close the System");
        BTNExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExitActionPerformed(evt);
            }
        });
        PNL1.add(BTNExit);
        BTNExit.setBounds(770, 590, 84, 38);

        LBLBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Background Photo.jpg"))); // NOI18N
        PNL1.add(LBLBackground);
        LBLBackground.setBounds(0, 0, 900, 650);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTCustUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTCustUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTCustUserNameActionPerformed

    private void TXTFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTFirstNameActionPerformed

    private void BTNExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNExitActionPerformed
        try
        {
            /*
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

    private void TBLAdministratorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLAdministratorsMouseClicked
        try
        {
            LBLAdminID.show();
            LBLAdminID2.show();
            BTNDeleteAdmin.show();
            BTNUpdateAdmin.show();
        
            //Get the Selected Row, Assign the value to integer variable i
            int i = TBLAdministrators.getSelectedRow();
            
            //Get the Table Model, assign it to the TableModel variable model
            TableModel model = TBLAdministrators.getModel();
            
            //Set Each Row to its respective Label or Textbox
            LBLAdminID2.setText(model.getValueAt (i, 0).toString());
            TXTAdminUserName.setText(model.getValueAt (i, 1).toString());
            TXTAdminPassword.setText(model.getValueAt (i, 2).toString());
            
        }
        
        //Error Handling the Admin Table Click Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nTable Click Error", "CLICK ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        
        
    }//GEN-LAST:event_TBLAdministratorsMouseClicked

    private void BTNUpdateCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNUpdateCustActionPerformed
        if (TXTFirstName.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "First Name TextField Cannot be Blank");
            
        }
        
        else if (TXTLastName.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "Last Name TextField Cannot be Blank");
            
        }
        
        else if (TXTCustUserName.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "User Name TextField Cannot be Blank");
            
        }
        
        else if (TXTCustPassword.getText() == null)
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
                YesorNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to update user data?", "UPDATE USER DATA?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (YesorNo == JOptionPane.YES_OPTION)
                {
                    SQLCode = "update customers set FirstName = '" + TXTFirstName.getText() + "', LastName = '" + TXTLastName.getText() + "', UserName = '" + TXTCustUserName.getText() + "', Password = '" + TXTCustPassword.getText() + "', PhoneNumber = " + TXTPhoneNumber.getText() + ", Email = '" + TXTEmail.getText() + "' where CustID = " + LBLCustID2.getText() + ";";
                    executeSQLQuery(SQLCode);

                    ClearCustomer();
                    BTNRefreshCust.doClick();

                }
            }

            //Error Handling the Update Customer Event
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR UPDATING USER DATA", JOptionPane.ERROR_MESSAGE);

            }
        }
    }//GEN-LAST:event_BTNUpdateCustActionPerformed

    private void BTNInsertCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNInsertCustActionPerformed
        if (TXTFirstName.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "First Name TextField Cannot be Blank");
            
        }
        
        else if (TXTLastName.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "Last Name TextField Cannot be Blank");
            
        }
        
        else if (TXTCustUserName.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "User Name TextField Cannot be Blank");
            
        }
        
        else if (TXTCustPassword.getText() == null)
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
                YesorNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to create this user?", "CREATE USER?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (YesorNo == JOptionPane.YES_OPTION)
                {
                    SQLCode = "insert into customers (FirstName, LastName, UserName, Password, PhoneNumber, Email) values ('" + TXTFirstName.getText() + "', '" + TXTLastName.getText() + "', '" + TXTCustUserName.getText() + "', '" + TXTCustPassword.getText() + "', " + TXTPhoneNumber.getText() + ", '" + TXTEmail.getText() + "');";
                    executeSQLQuery(SQLCode);

                    ClearCustomer();
                    BTNRefreshCust.doClick();

                }
            }

            //Error Handling the Customer Data Insertion Event
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR CREATING USER", JOptionPane.ERROR_MESSAGE);

            }
        }
    }//GEN-LAST:event_BTNInsertCustActionPerformed

    private void BTNDeleteCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDeleteCustActionPerformed
        try
        {
            YesorNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "DELETE USER?", JOptionPane.YES_NO_OPTION);

            if (YesorNo == JOptionPane.YES_OPTION)
            {
                SQLCode = "delete from customers where CustID = " + LBLCustID2.getText() + ";";
                executeSQLQuery(SQLCode);

                ClearCustomer();
                BTNRefreshCust.doClick();
                
            }
        }
        
        //Error Handling the Customer Deletion Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR DELETING USER", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNDeleteCustActionPerformed

    private void BTNClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNClearAllActionPerformed
        ClearAdmin();
        ClearCustomer();
        
    }//GEN-LAST:event_BTNClearAllActionPerformed

    private void BTNRefreshAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRefreshAdminActionPerformed
        try
        {
            //Sets the current Table Model to Default
            DefaultTableModel model = (DefaultTableModel)TBLAdministrators.getModel();

            //Empties the Rows of the Table
            model.setRowCount(0);

            //Re-inserts the Data as it is in the Database
            show_administrators_in_table();
            
        }
        
        //Error Handling the BTNRefresh Action Event
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Refreshing the Admin Table", "ERROR REFRESHING THE TABLE", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNRefreshAdminActionPerformed

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

    private void TBLCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLCustomersMouseClicked
        try
        {
            LBLCustID.show();
            LBLCustID2.show();
            BTNDeleteCust.show();
            BTNUpdateCust.show();

            //Get the Selected Row, Assign the value to integer variable i
            int i = TBLCustomers.getSelectedRow();
            
            //Get the Table Model, assign it to the TableModel variable model
            TableModel model = TBLCustomers.getModel();
            
            //Set Each Row to its respective Label or Textbox
            LBLCustID2.setText(model.getValueAt (i, 0).toString());
            TXTFirstName.setText(model.getValueAt (i, 1).toString());
            TXTLastName.setText(model.getValueAt (i, 2).toString());
            TXTCustUserName.setText(model.getValueAt (i, 3).toString());
            TXTCustPassword.setText(model.getValueAt (i, 4).toString());
            TXTPhoneNumber.setText(model.getValueAt (i, 5).toString());
            TXTEmail.setText(model.getValueAt (i, 6).toString());
            
        }
        
        //Error Handling Table Click Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Showing the data to the Labels and Textboxes", "ERROR CREATING DATA", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_TBLCustomersMouseClicked

    private void BTNRefreshCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRefreshCustActionPerformed
        try
        {
            //Sets the current Table Model to Default
            DefaultTableModel model = (DefaultTableModel)TBLCustomers.getModel();

            //Empties the Rows of the Table
            model.setRowCount(0);

            //Re-inserts the Data as it is in the Database
            show_customers_in_table();
            
        }
        
        //Error Handling the BTNRefresh Action Event
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nError Refreshing the Customer Table", "ERROR REFRESHING THE TABLE", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNRefreshCustActionPerformed

    private void BTNInsertAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNInsertAdminActionPerformed
        try
        {
            YesorNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to create this user?", "CREATE USER?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            if (YesorNo == JOptionPane.YES_OPTION)
            {
                SQLCode = "insert into adminlogin (UserName, Password) values ('" + TXTAdminUserName.getText() + "', '" + TXTAdminPassword.getText() + "');";
                executeSQLQuery(SQLCode);
                
                ClearAdmin();
                BTNRefreshAdmin.doClick();
                
            }
        }
        
        //Error Handling the User Creaation Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR CREATING DATA", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNInsertAdminActionPerformed

    private void BTNUpdateAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNUpdateAdminActionPerformed
        YesorNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to update user data?", "UPDATE USER DATA?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        try
        {
            if (YesorNo == JOptionPane.YES_OPTION)
            {
                SQLCode = "update adminlogin set UserName = '" + TXTAdminUserName.getText() + "', Password = '" + TXTAdminPassword.getText() + "' where AdminID = " + LBLAdminID2.getText() + ";";
                executeSQLQuery(SQLCode);
                
                ClearAdmin();
                BTNRefreshAdmin.doClick();
                
            }
        }
        
        //Error Handling the User Update Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR UPDATING USER DATA", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNUpdateAdminActionPerformed

    private void BTNDeleteAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDeleteAdminActionPerformed
        try
        {
            YesorNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "DELETE USER?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            if (YesorNo == JOptionPane.YES_OPTION)
            {
                SQLCode = "delete from adminlogin where AdminID = " + LBLAdminID2.getText() + ";";
                executeSQLQuery(SQLCode);

                ClearAdmin();
                BTNRefreshAdmin.doClick();
                
            }
        }
        
        //Error Handling the User Deletion Event
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nInvalid Data Format Found", "ERROR DELETING USER", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_BTNDeleteAdminActionPerformed

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
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
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
    private javax.swing.JButton BTNDeleteAdmin;
    private javax.swing.JButton BTNDeleteCust;
    private javax.swing.JButton BTNExit;
    private javax.swing.JButton BTNInsertAdmin;
    private javax.swing.JButton BTNInsertCust;
    private javax.swing.JButton BTNRefreshAdmin;
    private javax.swing.JButton BTNRefreshCust;
    private javax.swing.JButton BTNUpdateAdmin;
    private javax.swing.JButton BTNUpdateCust;
    private javax.swing.JLabel LBL1;
    private javax.swing.JLabel LBL2;
    private javax.swing.JLabel LBL3;
    private javax.swing.JLabel LBL4;
    private javax.swing.JLabel LBL5;
    private javax.swing.JLabel LBL6;
    private javax.swing.JLabel LBLAdminID;
    private javax.swing.JLabel LBLAdminID2;
    private javax.swing.JLabel LBLAdminPassword;
    private javax.swing.JLabel LBLAdminTable;
    private javax.swing.JLabel LBLAdminUserName;
    private javax.swing.JLabel LBLBackground;
    private javax.swing.JLabel LBLCustID;
    private javax.swing.JLabel LBLCustID2;
    private javax.swing.JLabel LBLCustPassword;
    private javax.swing.JLabel LBLCustTable;
    private javax.swing.JLabel LBLCustUserName;
    private javax.swing.JLabel LBLEmail;
    private javax.swing.JLabel LBLFirstName;
    private javax.swing.JLabel LBLLastName;
    private javax.swing.JLabel LBLMSG;
    private javax.swing.JLabel LBLPhoneNumber;
    private javax.swing.JPanel PNL1;
    private javax.swing.JTable TBLAdministrators;
    private javax.swing.JTable TBLCustomers;
    private javax.swing.JTextField TXTAdminPassword;
    private javax.swing.JTextField TXTAdminUserName;
    private javax.swing.JTextField TXTCustPassword;
    private javax.swing.JTextField TXTCustUserName;
    private javax.swing.JTextField TXTEmail;
    private javax.swing.JTextField TXTFirstName;
    private javax.swing.JTextField TXTLastName;
    private javax.swing.JTextField TXTPhoneNumber;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
