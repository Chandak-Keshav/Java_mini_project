package demo.Seller_Ricky;
import java.util.ArrayList;

import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

public class Ricky_Seller extends Seller{
    private ArrayList<ProductMain> productList=new ArrayList<ProductMain>();
    public Ricky_Seller(String id) {
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
        Book b1=new Book(Globals.Category.Book,getID()+"-"+"Hamlet",getID()+"100", (float) 400.0, 90);
        Book b2=new Book(Globals.Category.Book,getID()+"-"+"The_Merchant_Of_Venice",getID()+"101", (float) 250.0, 220);
        Book b3=new Book(Globals.Category.Book,getID()+"-"+"The_Isles_of_Naath",getID()+"102", (float) 100.0, 10);
        Book b4=new Book(Globals.Category.Book,getID()+"-"+"The_Song_Of_Ice_And_Fire",getID()+"103", (float) 200.0, 140);
        Book b5=new Book(Globals.Category.Book,getID()+"-"+"Macbeth",getID()+"104", (float) 890.0, 20);
        Book b6=new Book(Globals.Category.Book,getID()+"-"+"Chernobyl",getID()+"105", (float) 360.0,85);
        productList.add(b1);
        productList.add(b2);
        productList.add(b3);
        productList.add(b4);
        productList.add(b5);
        productList.add(b6);
    }
    
    public void addMobile()
    {
        Mobile b1=new Mobile(Globals.Category.Mobile,getID()+"-"+"HP_Phone",getID()+"500", (float) 40000.0, 30);
        Mobile b2=new Mobile(Globals.Category.Mobile,getID()+"-"+"ASUS_Zen_Phone",getID()+"501", (float) 25000.0, 20);
        Mobile b3=new Mobile(Globals.Category.Mobile,getID()+"-"+"Apple_X",getID()+"502", (float) 15590.0, 10);
        Mobile b4=new Mobile(Globals.Category.Mobile,getID()+"-"+"Redmi_Note5",getID()+"503", (float) 34200.0, 40);
        Mobile b5=new Mobile(Globals.Category.Mobile,getID()+"-"+"Infinix_Pro",getID()+"504", (float) 94720.0, 20);
        Mobile b6=new Mobile(Globals.Category.Mobile,getID()+"-"+"RealMe_5",getID()+"505", (float) 17740.0,85);
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
                    i.changePriceRandomly();
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }
}
