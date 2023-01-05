import java.util.Scanner;

import demo.DemoPlatform;
import ecomm.Platform;
import ecomm.Seller;
import ecomm.Seller1;

public class PlatformMain {

	public static void main(String[] args) {

		Platform pf = new DemoPlatform();
		
		Seller1 s1 = new Seller1("Seller1"); 
		s1.addPlatform(pf);

		Seller1 s2 = new Seller1("Seller2");
		s2.addPlatform(pf);
		
		Seller1 s3 = new Seller1("Seller3");
		s3.addPlatform(pf);

		Scanner sc = new Scanner(System.in);
		String command;
		while(true) {
			command = sc.next();
			if (command.equals("check")) {
				pf.processRequests();
			} else {
				sc.close();
				break;
			}
		}
		
		// replace with appropriate derived class
		
		// create a number of sellers (of different sub-types of Seller)
		// Assign a name (sellerID) to each of them.
		
		// replace each of the XYZ below with the derived class name of one of the
		// team members.
		
		/*
		Seller s1 = new DemoSeller1("Seller1"); 
		s1.addPlatform(pf);

		Seller s2 = new SellerXYZ("Seller2");
		s1.addPlatform(pf);
		
		Seller s3 = new SellerXYZ2("Seller3");
		s1.addPlatform(pf);
 		*/
		
		/*
		// keep reading from System.in
		// If "Check" typed in
		// invoke 
			pf.processRequests();
		*/
			
	}

}
