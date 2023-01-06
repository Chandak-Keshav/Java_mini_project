package demo.Seller_Rishi;
import java.util.ArrayList;
import java.util.Random;

import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

public class Rishi_Seller extends Seller{
    private ArrayList<ProductMain> productList=new ArrayList<ProductMain>();
    public Rishi_Seller(String id) {
        super(id);//on calling the constructor, we make the productList arraylist with book and mobile objects
        addBook();
        addMobile();
    }
    public void addPlatform(Platform thePlatform)
    {
        thePlatform.addSeller(this);//we add the seller to the platform
    }

	public void addBook()
    {
        Random rd = new Random(); // creating Random object
        /*We are creating six book objects with price ranging between 1000 and 2000
         * and quantity between 100 and 600
         */
        Book b1=new Book(Globals.Category.Book,getID()+"-"+"Statistics",getID()+"-"+"1", rd.nextFloat(1000)+(float) 1000.0, 100+rd.nextInt(500));
        Book b2=new Book(Globals.Category.Book,getID()+"-"+"Probability",getID()+"-"+"2", rd.nextFloat(1000)+(float) 1000.0, 100+rd.nextInt(500));
        Book b3=new Book(Globals.Category.Book,getID()+"-"+"Number_Theory",getID()+"-"+"3", rd.nextFloat(1000)+(float) 1000.0, 100+rd.nextInt(500));
        Book b4=new Book(Globals.Category.Book,getID()+"-"+"Regression",getID()+"-"+"4", rd.nextFloat(1000)+(float) 1000.0, 100+rd.nextInt(500));
        Book b5=new Book(Globals.Category.Book,getID()+"-"+"Topology",getID()+"-"+"5", rd.nextFloat(1000)+(float) 1000.0, 100+rd.nextInt(500));
        Book b6=new Book(Globals.Category.Book,getID()+"-"+"Graph_Theory",getID()+"-"+"6", rd.nextFloat(1000)+(float) 1000.0,100+rd.nextInt(500));
        productList.add(b1);
        productList.add(b2);
        productList.add(b3);
        productList.add(b4);
        productList.add(b5);
        productList.add(b6);
        //all Book objects are added in the productList arrayList
    }
    
    public void addMobile()
    {
        Random rd = new Random(); // creating Random object
        /*We are creating six Mobile objects with price ranging between 10000 and 20000
         * and quantity between 100 and 200
         */
        Mobile b1=new Mobile(Globals.Category.Mobile,getID()+"-"+"HP",getID()+"-"+"500", rd.nextFloat(10000)+(float) 10000.0, 100+rd.nextInt(100));
        Mobile b2=new Mobile(Globals.Category.Mobile,getID()+"-"+"Samsung",getID()+"-"+"501", rd.nextFloat(10000)+(float) 10000.0, 100+rd.nextInt(100));
        Mobile b3=new Mobile(Globals.Category.Mobile,getID()+"-"+"Apple",getID()+"-"+"502", rd.nextFloat(10000)+(float) 10000.0, 100+rd.nextInt(100));
        Mobile b4=new Mobile(Globals.Category.Mobile,getID()+"-"+"Blackberry",getID()+"-"+"503", rd.nextFloat(10000)+(float) 10000.0, 100+rd.nextInt(100));
        Mobile b5=new Mobile(Globals.Category.Mobile,getID()+"-"+"Asus",getID()+"-"+"504", rd.nextFloat(10000)+(float) 10000.0, 100+rd.nextInt(100));
        Mobile b6=new Mobile(Globals.Category.Mobile,getID()+"-"+"RealMe",getID()+"-"+"505", rd.nextFloat(10000)+(float) 10000.0,100+rd.nextInt(100));
        productList.add(b1);
        productList.add(b2);
        productList.add(b3);
        productList.add(b4);
        productList.add(b5);
        productList.add(b6);
        //all Mobile objects are added in the productList arrayList
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
                //this increasePrice() is used to increase the price of a particular category after this call 
                //this is done to introduce price dynamism
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
                    i.reduceQuantity(quantity);//quantity of Book is reduced by the value present in quantity variable
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }
}
