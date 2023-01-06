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
    public ProductMain(Category category,String name,String product_id,float price,int quantity) {
        this.category = category;
        this.name = name;
        this.product_id = product_id;
        this.price = price;
        this.quantity = quantity;
    }

    // Implementing the getter and setters for the category,Name,productID
    public Globals.Category getCategory() {
        return category;
    }

	public String getName() {
        return name;
    }

    public String getProductID() {
        return product_id;
    }

    // Methiod to reduce the amount of the product quantity
    public void reduceQuantity(int reduced_amount) {
        quantity = quantity - reduced_amount;
    }

	public float getPrice() {
        return price;
    }

	public int getQuantity() {
        return quantity;
    }

    // Chnaging the price randomly to mimic the functionality of seller being able to change the price dynamically.
    public void changePriceRandomly() {
        Random rd = new Random(); // creating Random object
        price=rd.nextFloat((float) 10000.0);
    }
}
