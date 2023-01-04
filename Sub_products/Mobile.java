package Sub_products;

import ecomm.Globals;
import ecomm.Product;

public class Mobile extends Product{
    private float price;
    private int quantity;
    private String product_id,name;
    private Globals.Category category;
    public Mobile(Globals.Category m_category,String mobile_name,String m_product_id,float m_price,int m_quantity)
    {
        category=m_category;
        name=mobile_name;
        product_id=m_product_id;
        price=m_price;
        quantity=m_quantity;
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
