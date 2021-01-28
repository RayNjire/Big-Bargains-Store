<<<<<<< HEAD
package MySQLConnections;


import java.sql.*;
import javax.swing.*;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author rnjire
 */
public class MySQLConnect 
{
    //Sets any incoming connection variables to null
    Connection conn = null;
    public static Connection ConnectDB()
    {
        try
        {
            //Calling the MySQLConnector
            Class.forName("com.mysql.jdbc.Driver");
            
            //Sets the value of the new MySQL connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Java_BigBargainsStore", "root", "");
            return conn;
            
        }
        catch (Exception e)
        {
            //Error Message
            JOptionPane.showMessageDialog(null, "Database Connection Error. Please Restart the Database.");
            return null;
            
        }
    }
=======
package MySQLConnections;


import java.sql.*;
import javax.swing.*;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author rnjire
 */
public class MySQLConnect 
{
    //Sets any incoming connection variables to null
    Connection conn = null;
    public static Connection ConnectDB()
    {
        try
        {
            //Calling the MySQLConnector
            Class.forName("com.mysql.jdbc.Driver");
            
            //Sets the value of the new MySQL connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Java_BigBargainsStore", "root", "");
            return conn;
            
        }
        catch (Exception e)
        {
            //Error Message
            JOptionPane.showMessageDialog(null, "Database Connection Error. Please Restart the Database.");
            return null;
            
        }
    }
>>>>>>> 428d86ff0a10b23329e084423b3d910ec968e65a
}