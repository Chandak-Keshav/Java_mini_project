package demo.Seller_Rishi;

import ecomm.Globals;
import ecomm.Product;
import ecomm.Globals.Category;

public class ProductMain extends Product {
    
    // Data members of class ProductMain
    private float price;
    private int quantity;
    private String product_id, name;
	
    Globals.Category category;
	
    public ProductMain(Category _category, String _name, String _product_id, float _price, int _quantity)
    {
        category = _category;
        name = _name;
        product_id = _product_id;
        price = _price;
        quantity = _quantity;
    }
	
    public Globals.Category getCategory() { return category; }
    
    // Getters and Setters for appropriate data members of this class.
    public String getName() { return name; }
    public String getProductID() { return product_id; }
    public void reduceQuantity(int reduced_amount) { quantity = quantity-reduced_amount; }
    public float getPrice() { return price; }	
    public int getQuantity() { return quantity; }
    public void increasePrice() {
	    
	// Increase the price of product by 10 % if quantity decreases.
        price *= (float) 1.1;
    }
}
