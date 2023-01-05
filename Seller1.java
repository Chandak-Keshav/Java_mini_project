package ecomm;
import java.util.ArrayList;
import java.util.Random;

import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

public class Seller1 extends Seller{
    private ArrayList<ProductMain> productList=new ArrayList<ProductMain>();
    private static int p_id=0;
    public Seller1(String id) {
        super(id);
        addBook();
        addMobile();
    }

    public void addPlatform(Platform thePlatform) {
        thePlatform.addSeller(this);//we add the seller to the platform
    }

    
    public String generateBookName() {
        String[] book_names={"Introduction_To-Algorithm","Digital_Design","Machine_Learning","Maths_For_Machine_Learning","Topology","Number_Theory","Statistical_Physics","Astronomy","Computer_Architecture","Computer_Networks"};
        Random random = new Random();
        int index = random.nextInt(book_names.length);
        return book_names[index];
    }

    public String generateProductID() {
        return Integer.toString(100+(p_id++));
    }

    public float generatePrice() {
        Random rd = new Random(); // creating Random object
        return rd.nextFloat()*1000; // displaying a random float value between 0.0 and 1.0
    }

    public int generateQuantity() {
        Random rd = new Random(); // creating Random object
        return rd.nextInt(200)*100;
    }

	public void addBook()
    {
        int no_of_books=10;
        while(no_of_books-->0)
        {
            String book_name=generateBookName();
            String product_id=generateProductID();
            float book_price=generatePrice();
            int quantity=generateQuantity(); 
            Book b = new Book(Globals.Category.Book, book_name, product_id, book_price, quantity);
            productList.add(b);
        }
    }
    
    public void addMobile()
    {
        int no_of_mobile=10;
        while(no_of_mobile-->0)
        {
            String mobile_name=generateMobileName();
            String product_id=generateProductID();
            float mobile_price=generatePrice();
            int quantity=generateQuantity(); 
            Mobile m=new Mobile(Globals.Category.Mobile, mobile_name, product_id, mobile_price, quantity);
            productList.add(m);
        }
    }

	public String generateMobileName() {
        String[] mobile_names={"Samsung","Apple","Blackberry","Nokia","RedMi","RealMe","ASUS"};
        Random random = new Random();
        int index = random.nextInt(mobile_names.length);
        return mobile_names[index];
    }

    // Seller to return listing of Products of specified Category
	public ArrayList<Product> findProducts(Globals.Category whichOne)
    {
        ArrayList<Product> listing = new ArrayList<Product>();
        for(Product i: productList)
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
                    i.increasePrice();
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }
}
