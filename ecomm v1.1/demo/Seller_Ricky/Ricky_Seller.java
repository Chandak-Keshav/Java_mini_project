package demo.Seller_Ricky;
import java.util.ArrayList;
import java.util.Random;

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

    public void addPlatform(Platform thePlatform) {
        thePlatform.addSeller(this);//we add the seller to the platform
    }


    // The below two methods are just for the sake of ease in creating the objects
    private int generatePrice() {
        Random rand = new Random();
        return rand.nextInt(1000);  //Returning a random integer in 1 to 1000 which will be set as price of mobile and book.
    }

    private int generateQuantity() {
        Random rand = new Random();
        return rand.nextInt(500);  //Returning a random integer in 1 to 500 which will be set as quantity of mobile and book.
    }

	public void addBook() {
        Book b1 = new Book(Globals.Category.Book,getID()+"-"+"Hamlet",getID()+"-100", (float) generatePrice(), generateQuantity());
        Book b2 = new Book(Globals.Category.Book,getID()+"-"+"The_Merchant_Of_Venice",getID()+"-101", (float) generatePrice(), generateQuantity());
        Book b3 = new Book(Globals.Category.Book,getID()+"-"+"Dune",getID()+"-102", (float) generatePrice(), generateQuantity());
        Book b4 = new Book(Globals.Category.Book,getID()+"-"+"Odyssey",getID()+"-103", (float) generatePrice(), generateQuantity());
        Book b5 = new Book(Globals.Category.Book,getID()+"-"+"Macbeth",getID()+"-104", (float) generatePrice(), generateQuantity());
        Book b6 = new Book(Globals.Category.Book,getID()+"-"+"Chernobyl",getID()+"-105", (float) generatePrice(),generateQuantity());
        productList.add(b1);
        productList.add(b2);
        productList.add(b3);
        productList.add(b4);
        productList.add(b5);
        productList.add(b6);
    }
    
    public void addMobile() {
        Mobile m1 = new Mobile(Globals.Category.Mobile,getID()+"-"+"HP_Phone",getID()+"-500", (float) generatePrice(), generateQuantity());
        Mobile m2 = new Mobile(Globals.Category.Mobile,getID()+"-"+"ASUS_Zen_Phone",getID()+"-501", (float) generatePrice(), generateQuantity());
        Mobile m3 = new Mobile(Globals.Category.Mobile,getID()+"-"+"Apple_X",getID()+"-502", (float) generatePrice(), generateQuantity());
        Mobile m4 = new Mobile(Globals.Category.Mobile,getID()+"-"+"Redmi_Note5",getID()+"-503", (float) generatePrice(), generateQuantity());
        Mobile m5 = new Mobile(Globals.Category.Mobile,getID()+"-"+"Infinix_Pro",getID()+"-504", (float) generatePrice(), generateQuantity());
        Mobile m6 = new Mobile(Globals.Category.Mobile,getID()+"-"+"RealMe_5",getID()+"-505", (float) generatePrice(),generateQuantity());
        productList.add(m1);
        productList.add(m2);
        productList.add(m3);
        productList.add(m4);
        productList.add(m5);
        productList.add(m6);
    }

    // Seller to return listing of Products of specified Category
	public ArrayList<Product> findProducts(Globals.Category whichOne)
    {
        ArrayList<Product> listOfProducts = new ArrayList<Product>();
        for(ProductMain prod : productList) {
            if(prod.getCategory().equals(whichOne)) {
                listOfProducts.add(prod);
            }
        }
        return listOfProducts;
    }

	// User wants to buy specified quantity of productID
	// Return true if transaction succeeds, false otherwise. 
	// Transaction fails if incorrect productID or quantity exceeds available inventory
	public boolean buyProduct(String productID, int quantity)
    {
        for(ProductMain i : productList)
        {
            if(i.getProductID().equals(productID))
            {
                if(i.getQuantity()>=quantity) {
                    i.reduceQuantity(quantity);
                    i.changePriceRandomly();
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
}