package demo.Seller_Rishi;

import ecomm.Globals;

public class Mobile extends ProductMain{
    
    // Constructor for Mobile product with its appropriate data members.
    public Mobile(Globals.Category m_category,String mobile_name,String m_product_id,float m_price,int m_quantity)
    {
        // Inheriting constuctor code from the parent class that is
        // ProductMain class.
        super(m_category,mobile_name,m_product_id,m_price,m_quantity);
    }
}
