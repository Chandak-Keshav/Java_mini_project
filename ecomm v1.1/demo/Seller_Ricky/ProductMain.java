package demo.Seller_Ricky;

import java.util.Random;

import ecomm.Globals;
import ecomm.Product;
import ecomm.Globals.Category;

public class ProductMain extends Product {
    private float price;
    private int quantity;
    private String product_id,name;
    Globals.Category category;
    public ProductMain(Category category,String name,String product_id,float price,int quantity)
    {
        this.category=category;
        this.name=name;
        this.product_id=product_id;
        this.price=price;
        this.quantity=quantity;
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
    public void reduceQuantity(int reduced_amount)
    {
        quantity=quantity-reduced_amount;
    }
	public float getPrice()
    {
        return price;
    }
	public int getQuantity()
    {
        return quantity;
    }
    public void changePriceRandomly() {
        Random rd = new Random(); // creating Random object
        price=rd.nextFloat((float) 10000.0);
    }
}
