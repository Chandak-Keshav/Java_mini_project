package Sub_products;

import ecomm.Globals;
import ecomm.Product;

public class Book extends Product {
    private float price;
    private int quantity;
    private String product_id,name;
    private Globals.Category category;
    public Book(Globals.Category b_category,String book_name,String b_product_id,float b_price,int b_quantity)
    {
        category=b_category;
        name=book_name;
        product_id=b_product_id;
        price=b_price;
        quantity=b_quantity;
    }
    public Globals.Category getCategory()
    {
        return category;
    }
	public String getName()
    {
        return name;
    }
	public String getProductID()
    {
        return product_id;
    }
	public float getPrice()
    {
        return price;
    }
	public int getQuantity()
    {
        return quantity;
    }
}
