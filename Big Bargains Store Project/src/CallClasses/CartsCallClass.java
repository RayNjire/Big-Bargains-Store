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
public class CartsCallClass 
{
    private String productbought;
    private Object datebought;
    private double price;
    private int quantity;
    private double productprice;
    private double totalprice;
    
    /**CONSTRUCTOR DETAILS
     * 
     * This is a Constructor Method with initialized Variables. 
     * The objects referring to this method are created in the classes 'AdminCart' and 'CustomerCart'
     * This method is used to hold data from the database before posting to a table to view.
     * 
     * @param ProductBought
     * @param DateBought
     * @param Price
     * @param Quantity
     * @param ProductPrice
     * @param TotalPrice
    */
    
    //There are 2 Tables in those classes. This prints on the main table
    public CartsCallClass(String ProductBought, Object DateBought, double Price, int Quantity, double ProductPrice, double TotalPrice)
    {
        this.productbought = ProductBought;
        this.datebought = DateBought;
        this.price = Price;
        this.quantity = Quantity;
        this.productprice = ProductPrice;
        this.totalprice = TotalPrice;
        
    }

    //This one prints on the CustomerReceipt table
    public CartsCallClass(String ProductBought, Object DateBought, double Price, int Quantity, double ProductPrice) {
        this.productbought = ProductBought;
        this.datebought = DateBought;
        this.price = Price;
        this.quantity = Quantity;
        this.productprice = ProductPrice;
        
    }
  
    public String getProductBought()
    {
        return productbought;
        
    }
    
    public Object getDateBought()
    {
        return datebought;
        
    }
   
    public double getPrice()
    {
        return price;
        
    }
    
    public int getQuantity()
    {
        return quantity;
        
    }
    
    public double getProductPrice()
    {
        return productprice;
        
    }
    
    public double getTotalPrice()
    {
        return totalprice;
        
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
public class CartsCallClass 
{
    private String productbought;
    private Object datebought;
    private double price;
    private int quantity;
    private double productprice;
    private double totalprice;
    
    /**CONSTRUCTOR DETAILS
     * 
     * This is a Constructor Method with initialized Variables. 
     * The objects referring to this method are created in the classes 'AdminCart' and 'CustomerCart'
     * This method is used to hold data from the database before posting to a table to view.
     * 
     * @param ProductBought
     * @param DateBought
     * @param Price
     * @param Quantity
     * @param ProductPrice
     * @param TotalPrice
    */
    
    //There are 2 Tables in those classes. This prints on the main table
    public CartsCallClass(String ProductBought, Object DateBought, double Price, int Quantity, double ProductPrice, double TotalPrice)
    {
        this.productbought = ProductBought;
        this.datebought = DateBought;
        this.price = Price;
        this.quantity = Quantity;
        this.productprice = ProductPrice;
        this.totalprice = TotalPrice;
        
    }

    //This one prints on the CustomerReceipt table
    public CartsCallClass(String ProductBought, Object DateBought, double Price, int Quantity, double ProductPrice) {
        this.productbought = ProductBought;
        this.datebought = DateBought;
        this.price = Price;
        this.quantity = Quantity;
        this.productprice = ProductPrice;
        
    }
  
    public String getProductBought()
    {
        return productbought;
        
    }
    
    public Object getDateBought()
    {
        return datebought;
        
    }
   
    public double getPrice()
    {
        return price;
        
    }
    
    public int getQuantity()
    {
        return quantity;
        
    }
    
    public double getProductPrice()
    {
        return productprice;
        
    }
    
    public double getTotalPrice()
    {
        return totalprice;
        
    }
}


>>>>>>> 428d86ff0a10b23329e084423b3d910ec968e65a
