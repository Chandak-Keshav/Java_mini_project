package demo.Seller_Keshav;
import java.util.ArrayList;
import java.util.Random;

import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

public class Keshav_Seller extends Seller{
    private ArrayList<ProductMain> productList=new ArrayList<ProductMain>();
    private static int p_id=0;
    public Keshav_Seller(String id) {
        super(id); ///denotes the id passed by the platform which is attached in front of name and product id
        addBook(); //This adds the book objects in productList arraylist
        addMobile(); //This adds the mobile objects in productList arraylist
    }

    public void addPlatform(Platform thePlatform) {
        thePlatform.addSeller(this);//we add the seller to the platform
    }

    
    public String generateBookName() {
        //all course related books are given below
        String[] book_names={"Introduction_To_Algorithm","Digital_Design","Machine_Learning","Maths_For_Machine_Learning","Topology","Number_Theory","Statistical_Physics","Astronomy","Computer_Architecture","Computer_Networks"};
        Random random = new Random();
        int index = random.nextInt(book_names.length);//we pick any random index of string array
        return getID()+"-"+book_names[index];//this will give SellerId-book_name
    }

    public String generateProductID() {
        return getID()+"-"+Integer.toString(100+(p_id++));//this will give ellerId-ProductId
    }

    public float generatePrice() {
        Random rd = new Random(); // creating Random object
        return rd.nextFloat((float) 1000); // returning a random float value between 0.0 and 1000.0
    }

    public int generateQuantity() {
        Random rd = new Random(); // creating Random object
        return rd.nextInt(200); //gives a quantity which is between 0 and 200
    }

	public void addBook()
    {
        int no_of_books=10;
        while(no_of_books-->0)
        {
            String book_name=generateBookName();//generates book name
            String product_id=generateProductID();//generates productId for the above book
            float book_price=generatePrice();//generates price for the above book
            int quantity=generateQuantity(); //generates quantity for the above book
            Book b = new Book(Globals.Category.Book, book_name, product_id, book_price, quantity); //Book object is created
            productList.add(b);//object added to arrayList
        }
    }
    
    public void addMobile()
    {
        int no_of_mobile=10;
        while(no_of_mobile-->0)
        {
            String mobile_name=generateMobileName();//generates mobile name
            String product_id=generateProductID();//generates productId for the above mobile
            float mobile_price=generatePrice();//generates price for the above mobile
            int quantity=generateQuantity(); //generates quantity for the above mobile
            Mobile m=new Mobile(Globals.Category.Mobile, mobile_name, product_id, mobile_price, quantity);
            productList.add(m); //Mobile object is added to the product List
        }
    }

	public String generateMobileName() {
        String[] mobile_names={"Samsung","Apple","Blackberry","Nokia","RedMi","RealMe","ASUS"};
        Random random = new Random();
        int index = random.nextInt(mobile_names.length);
        return mobile_names[index]; //any random mobile name is returned among the String[] array
    }

    // Seller to return listing of Products of specified Category
	public ArrayList<Product> findProducts(Globals.Category whichOne)
    {
        ArrayList<Product> listing = new ArrayList<Product>();
        for(ProductMain i: productList)
        {
            if(i.getCategory().equals(whichOne))
            {//if the category of product matches the category in array list, we add it into the ArrayList listing
                listing.add(i);
            }
        }
        return listing;//listing of a specific category product is returned
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
                    i.increasePrice();//this is implemented for dynamic price,we increase the price whenever quantity decreases 
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }
}
