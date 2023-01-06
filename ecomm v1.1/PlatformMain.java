import java.util.Scanner;

import ecomm.Platform;
import demo.DemoPlatform;
import demo.Seller_Ricky.*;
import demo.Seller_Keshav.*;
import demo.Seller_Rishi.*;

public class PlatformMain {
	public static void main(String[] args) {

		Platform pf = new DemoPlatform(); // Object of DemoPlatform for testing.
		
		// Adding the sellers made by the tree team members.
		Ricky_Seller s1 = new Ricky_Seller("Seller1");
		s1.addPlatform(pf);

		Keshav_Seller s2 = new Keshav_Seller("Seller2");
		s2.addPlatform(pf);

		Rishi_Seller s3= new Rishi_Seller("Seller3");
		s3.addPlatform(pf);


		Scanner sc = new Scanner(System.in);
		String command;
		// Always waiting for input from user. If check is provided then processRequests or else close the program.
		while(true) {
			command = sc.next();
			if (command.equals("Check")) {
				pf.processRequests();
			} else {
				sc.close();
				break;
			}
		}
	}
}