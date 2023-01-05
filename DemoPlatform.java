package demo;

import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DemoPlatform extends Platform {

	//A mapping from sellerId to the seller to easily identify the seller based on the sellerId.
	private HashMap<String,Seller> IdToSellerMap = new HashMap<String,Seller>();
	private HashSet<Seller> sellerList = new HashSet<Seller>();  // Also storing all the seller objects in a different hash set.


	// Declaring the file readers and writers.
	private FileReader fr;
	private FileWriter fw;
	private BufferedReader br;
	private BufferedWriter bw;


	//Initializing the file reader and writer in the constructor.
	public DemoPlatform() {
		try {
			fr = new FileReader(Globals.toPlatform);
			fw = new FileWriter(Globals.fromPlatform,true);
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			System.out.println("File was not found or other IOException");
		}
	}
	
	// addSeller methods adds the seller to the sellerList and adds a entry in IdToSellerMap withrespect to the given seller.
	@Override
	public boolean addSeller(Seller aSeller) {
		sellerList.add(aSeller);
		IdToSellerMap.put(aSeller.getID(), aSeller);
		return true;
	}


	@Override
	public void processRequests() {
		String request;
		try {
			while((request = br.readLine()) != null) {
				String[] requestComponents = request.split(" ");
				String portalId = requestComponents[0];
				String requestId = requestComponents[1];
				String requestType = requestComponents[2];
				System.out.println(requestType);

				// Checking the type of request and passing the control to the respective handler of that request.
				if (requestType.equals("Start")) {
					handleStartRequest(portalId, requestId);
				} 
				else if (requestType.equals("List")) {
					String category = requestComponents[3];
					handleListRequest(portalId, requestId,category);
				} 
				else {
					String productId = requestComponents[3];
					String numItems = requestComponents[4];
					handleBuyRequest(portalId, requestId,productId, numItems);
				}
			}
			bw.flush();
			fw.flush();
		} catch (IOException e) {
			System.out.println("IOException while reading the file. By Ricky");
		}
	}


	// This method writes the list of all the categories to the platformToPortal.txt
	private void handleStartRequest(String portalId, String requestID) {
		ArrayList<String> categoryList = new ArrayList<String>();
		for(Globals.Category category : Globals.Category.values()) {  //Getting all the categories from Globals.Category
			categoryList.add(Globals.getCategoryName(category));
		}
		
		try {
			bw.write(portalId + " " + requestID + " ");
			for(String s : categoryList) {
				bw.write(s + " ");
			}
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Problem while writing to the file");
		}
	}


	// This method handles the list request.
	private void handleListRequest(String portalId,String requestId,String requestCategory) {
		ArrayList<Product> productList = new ArrayList<Product>();  //Stores all the products of the given category.
		Globals.Category category;

		if (requestCategory.equals("Mobile")) {
			category = Globals.Category.Mobile;
		} else {
			category = Globals.Category.Book;
		}

		for(Seller s : sellerList) {
			productList.addAll(s.findProducts(category));  //Adding the list of products gained by the sellers to the productList.
		}
		try {
			for(Product p : productList) {
				bw.write(portalId + " "  + requestId + " " + p.getName() + " " + p.getProductID() + " " + p.getPrice() + " " + p.getQuantity());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error while writing to the file. For List request.");
		}
	}



	private void handleBuyRequest(String portalId, String requestId, String productId, String numItems) {
		int numberOfItems = Integer.parseInt(numItems);
		Boolean buyStatus = false;
		for(Seller s : sellerList) {
			if (s.buyProduct(productId,numberOfItems)) {
				if (buyStatus) { System.out.println("Two products with same productId are available"); }
				else {
					buyStatus = true;
				}
			}
		}
		
		String output;
		if (buyStatus) { output = "Success"; }
		else {output = "Failure";}
		try {
			bw.write(portalId + " " + requestId + " " + output);
		} catch (IOException e) {
			System.out.println("Error while writing to the file. In buy command");
		}

		// String[] productInfo = new String[2];
		// productInfo = productId.split("_");
		// String sellerName = productInfo[0];
		// int numberOfItems = Integer.parseInt(numItems);
		// Boolean buyStatus = IdToSellerMap.get(sellerName).buyProduct(productId, numberOfItems);
		// String output;
		// if (buyStatus) { output = "Success"; }
		// else {output = "Failure";}
		// try {
		// 	bw.write(portalId + " " + requestId + " " + output);
		// } catch (IOException e) {
		// 	System.out.println("Error while writing to the file. In buy command");
		// }
	}
}