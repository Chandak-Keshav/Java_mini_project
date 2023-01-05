package demo.Seller_Rishi;
import java.util.ArrayList;

import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

public class Rishi_Seller extends Seller{
    private ArrayList<ProductMain> productList=new ArrayList<ProductMain>();
    public Rishi_Seller(String id) {
        super(id);
        addBook();
        addMobile();
    }
    public void addPlatform(Platform thePlatform)
    {
        thePlatform.addSeller(this);//we add the seller to the platform
    }

	public void addBook()
    {
        Book b1=new Book(Globals.Category.Book,getID()+"-"+"Statistics",getID()+"1", (float) 1000.0, 360);
        Book b2=new Book(Globals.Category.Book,getID()+"-"+"Probability",getID()+"2", (float) 1250.0, 180);
        Book b3=new Book(Globals.Category.Book,getID()+"-"+"Number Theory",getID()+"3", (float) 1300.0, 120);
        Book b4=new Book(Globals.Category.Book,getID()+"-"+"Regression",getID()+"4", (float) 1880.0, 100);
        Book b5=new Book(Globals.Category.Book,getID()+"-"+"Topology",getID()+"5", (float) 590.0, 80);
        Book b6=new Book(Globals.Category.Book,getID()+"-"+"Graph Theory",getID()+"6", (float) 260.0,65);
        productList.add(b1);
        productList.add(b2);
        productList.add(b3);
        productList.add(b4);
        productList.add(b5);
        productList.add(b6);
    }
    
    public void addMobile()
    {
        Mobile b1=new Mobile(Globals.Category.Mobile,getID()+"-"+"HP",getID()+"500", (float) 3200.0, 110);
        Mobile b2=new Mobile(Globals.Category.Mobile,getID()+"-"+"Samsung",getID()+"501", (float) 2000.0, 80);
        Mobile b3=new Mobile(Globals.Category.Mobile,getID()+"-"+"Apple",getID()+"502", (float) 1590.0, 50);
        Mobile b4=new Mobile(Globals.Category.Mobile,getID()+"-"+"Blackberry",getID()+"503", (float) 64200.0, 30);
        Mobile b5=new Mobile(Globals.Category.Mobile,getID()+"-"+"Asus",getID()+"504", (float) 8420.0, 180);
        Mobile b6=new Mobile(Globals.Category.Mobile,getID()+"-"+"RealMe",getID()+"505", (float) 7740.0,185);
        productList.add(b1);
        productList.add(b2);
        productList.add(b3);
        productList.add(b4);
        productList.add(b5);
        productList.add(b6);
    }
    // Seller to return listing of Products of specified Category
	public ArrayList<Product> findProducts(Globals.Category whichOne)
    {
        ArrayList<Product> listing=new ArrayList<Product>();
        for(ProductMain i: productList)
        {
            if(i.getCategory().equals(whichOne))
            {
                listing.add(i);
                i.increasePrice();
            }
        }
        return listing;
    }
	// User wants to buy specified quantity of productID
	// Return true if transaction succeeds, false otherwise. 
	// Transaction fails if incorrect productID or quantity exceeds available inventory
	public boolean buyProduct(String productID, int quantity)
    {
        for(ProductMain i:productList)
        {
            if(i.getProductID().equals(productID))
            {
                if(i.getQuantity()>=quantity)
                {
                    i.reduceQuantity(quantity);
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }
}
