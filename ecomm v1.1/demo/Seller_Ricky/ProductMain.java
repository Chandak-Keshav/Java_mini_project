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
    public ProductMain(Category p_category,String p_name,String p_product_id,float p_price,int p_quantity)
    {
        category=p_category;
        name=p_name;
        product_id=p_product_id;
        price=p_price;
        quantity=p_quantity;
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
        price=rd.nextFloat()*100000;
    }
}
