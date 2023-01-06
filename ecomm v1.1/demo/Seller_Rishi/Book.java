package demo.Seller_Rishi;

import ecomm.Globals;

public class Book extends ProductMain {
    
    // Constructor for book product with its data members.
    Book(Globals.Category b_category,String b_name,String b_product_id,float b_price,int b_quantity)
    {
        // Inheriting constructor code from parent class that is the 
        // ProductMain class.
        super(b_category,b_name,b_product_id,b_price,b_quantity);
    }
}
