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
public class EditAllUsersCallClass_Admin
{
    private int adminid;
    private String adminusername;
    private String adminpassword;
    
    /**CONSTRUCTOR DETAILS
     * 
     * This is a Constructor Method with initialized Variables. 
     * The objects referring to this method are created in the classes 'AdminCart' and 'CustomerCart'
     * This method is used to hold data from the database before posting to a table to view.
     * 
     * @param AdminID
     * @param AdminUserName
     * @param AdminPassword
    */
    public EditAllUsersCallClass_Admin (int AdminID, String AdminUserName, String AdminPassword)
    {
        this.adminid = AdminID;
        this.adminusername = AdminUserName;
        this.adminpassword = AdminPassword;
        
    }
    
    public int getAdminID()
    {
        return adminid;
        
    }
    
    public String getAdminUserName()
    {
        return adminusername;
        
    }
    
    public String getAdminPassword()
    {
        return adminpassword;
        
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
public class EditAllUsersCallClass_Admin
{
    private int adminid;
    private String adminusername;
    private String adminpassword;
    
    /**CONSTRUCTOR DETAILS
     * 
     * This is a Constructor Method with initialized Variables. 
     * The objects referring to this method are created in the classes 'AdminCart' and 'CustomerCart'
     * This method is used to hold data from the database before posting to a table to view.
     * 
     * @param AdminID
     * @param AdminUserName
     * @param AdminPassword
    */
    public EditAllUsersCallClass_Admin (int AdminID, String AdminUserName, String AdminPassword)
    {
        this.adminid = AdminID;
        this.adminusername = AdminUserName;
        this.adminpassword = AdminPassword;
        
    }
    
    public int getAdminID()
    {
        return adminid;
        
    }
    
    public String getAdminUserName()
    {
        return adminusername;
        
    }
    
    public String getAdminPassword()
    {
        return adminpassword;
        
    }
}


>>>>>>> 428d86ff0a10b23329e084423b3d910ec968e65a
