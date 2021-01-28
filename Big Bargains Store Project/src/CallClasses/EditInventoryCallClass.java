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
public class EditInventoryCallClass
{
    private int invid;
    private String product;
    private String groupcode;
    private String groups;
    private double priceinksh ;
    private int quantity;
    
    /**CONSTRUCTOR DETAILS
     * 
     * This is a Constructor Method with initialized Variables. 
     * The object referring to this method is created in the class 'EditAllUsers'
     * This method is used to hold data from the database before posting to a table to view.
     * 
     * @param InvID
     * @param Product
     * @param GroupCode
     * @param Groups
     * @param PriceinKSH
     * @param Quantity
    */
    public EditInventoryCallClass (int InvID, String Product, String GroupCode, String Groups, double PriceinKSH, int Quantity)
    {
        this.invid = InvID;
        this.product = Product;
        this.groupcode = GroupCode;
        this.groups = Groups;
        this.priceinksh = PriceinKSH;
        this.quantity = Quantity;
    }
    
    public int getInvID()
    {
        return invid;
        
    }
    
    public String getProduct()
    {
        return product;
        
    }
    
    public String getGroupCode()
    {
        return groupcode;
    }
    
    public String getGroups()
    {
        return groups;
    }
    
    public double getPriceinKSH()
    {
        return priceinksh;
    }
    
    public int getQuantity()
    {
        return quantity;
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
public class EditInventoryCallClass
{
    private int invid;
    private String product;
    private String groupcode;
    private String groups;
    private double priceinksh ;
    private int quantity;
    
    /**CONSTRUCTOR DETAILS
     * 
     * This is a Constructor Method with initialized Variables. 
     * The object referring to this method is created in the class 'EditAllUsers'
     * This method is used to hold data from the database before posting to a table to view.
     * 
     * @param InvID
     * @param Product
     * @param GroupCode
     * @param Groups
     * @param PriceinKSH
     * @param Quantity
    */
    public EditInventoryCallClass (int InvID, String Product, String GroupCode, String Groups, double PriceinKSH, int Quantity)
    {
        this.invid = InvID;
        this.product = Product;
        this.groupcode = GroupCode;
        this.groups = Groups;
        this.priceinksh = PriceinKSH;
        this.quantity = Quantity;
    }
    
    public int getInvID()
    {
        return invid;
        
    }
    
    public String getProduct()
    {
        return product;
        
    }
    
    public String getGroupCode()
    {
        return groupcode;
    }
    
    public String getGroups()
    {
        return groups;
    }
    
    public double getPriceinKSH()
    {
        return priceinksh;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
}


>>>>>>> 428d86ff0a10b23329e084423b3d910ec968e65a
