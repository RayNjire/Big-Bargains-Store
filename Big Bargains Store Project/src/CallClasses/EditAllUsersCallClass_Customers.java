<<<<<<< HEAD
package CallClasses;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
 */
public class EditAllUsersCallClass_Customers
{
    private int customerid;
    private String firstname;
    private String lastname;
    private String custusername;
    private String custpassword;
    private int phonenumber;
    private String email;
    
    /**CONSTRUCTOR DETAILS
     * 
     * This is a Constructor Method with initialized Variables. 
     * The objects referring to this method are created in the classes 'AdminCart' and 'CustomerCart'
     * This method is used to hold data from the database before posting to a table to view.
     * 
     * 
     * @param CustomerID
     * @param FirstName
     * @param LastName
     * @param CustUserName
     * @param CustPassword
     * @param PhoneNumber
     * @param Email
    */
    public EditAllUsersCallClass_Customers (int CustomerID, String FirstName, String LastName, String CustUserName, String CustPassword, int PhoneNumber, String Email)
    {
        this.customerid = CustomerID;
        this.firstname = FirstName;
        this.lastname = LastName;
        this.custusername = CustUserName;
        this.custpassword = CustPassword;
        this.phonenumber = PhoneNumber;
        this.email = Email;
        
    }
    
    public int getCustomerID()
    {
        return customerid;
        
    }
    
    public String getFirstName()
    {
        return firstname;
        
    }
    
    public String getLastName()
    {
        return lastname;
        
    }
    
    public String getCustUserName()
    {
        return custusername;
        
    }
    
    public String getCustPassword()
    {
        return custpassword;
        
    }
    
    public int getPhoneNumber()
    {
        return phonenumber;
        
    }
    
    public String getEmail()
    {
        return email;
        
    }
}


=======
package CallClasses;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
 */
public class EditAllUsersCallClass_Customers
{
    private int customerid;
    private String firstname;
    private String lastname;
    private String custusername;
    private String custpassword;
    private int phonenumber;
    private String email;
    
    /**CONSTRUCTOR DETAILS
     * 
     * This is a Constructor Method with initialized Variables. 
     * The objects referring to this method are created in the classes 'AdminCart' and 'CustomerCart'
     * This method is used to hold data from the database before posting to a table to view.
     * 
     * 
     * @param CustomerID
     * @param FirstName
     * @param LastName
     * @param CustUserName
     * @param CustPassword
     * @param PhoneNumber
     * @param Email
    */
    public EditAllUsersCallClass_Customers (int CustomerID, String FirstName, String LastName, String CustUserName, String CustPassword, int PhoneNumber, String Email)
    {
        this.customerid = CustomerID;
        this.firstname = FirstName;
        this.lastname = LastName;
        this.custusername = CustUserName;
        this.custpassword = CustPassword;
        this.phonenumber = PhoneNumber;
        this.email = Email;
        
    }
    
    public int getCustomerID()
    {
        return customerid;
        
    }
    
    public String getFirstName()
    {
        return firstname;
        
    }
    
    public String getLastName()
    {
        return lastname;
        
    }
    
    public String getCustUserName()
    {
        return custusername;
        
    }
    
    public String getCustPassword()
    {
        return custpassword;
        
    }
    
    public int getPhoneNumber()
    {
        return phonenumber;
        
    }
    
    public String getEmail()
    {
        return email;
        
    }
}


>>>>>>> 428d86ff0a10b23329e084423b3d910ec968e65a
