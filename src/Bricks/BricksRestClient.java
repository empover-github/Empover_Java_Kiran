package Bricks;


import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;


/*
 * Rest Client to send request
 */
public class BricksRestClient {
	
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in); 
			System.out.println("Create Order as c | Get Order as g | Update Order as u | Fulfil Order as f | Get all Order as a | Exit from operations as e");
			System.out.println("Select from above operations......");
			selectionControls(scanner.nextLine());
		}catch(Exception ex) {
		System.out.println(ex.getMessage());
		}
	}
	
	public static String initializingMainMethod() {
		main(null);
		return "";
	}
	
	/*
	 * Method which hits the rest service based on selection option..
	 */
	public static void selectionControls(String order) {
		
		try {
		String qty = "";
		Response resp =null;
		/*
		 * Url targets the service 
		 */
		String webServiceURI = "http://localhost:8080/BricksRestService/rest/bricks";
		Client client = ClientBuilder.newClient();
		Scanner scanner = new Scanner(System.in); 
		
			if("c".equalsIgnoreCase(order)) {
			  System.out.println("Please Enter the Order Quantity...");
			  qty = scanner.nextLine();
			  if(!orderQtyValidation(qty)) {
				  System.out.println("Please Enter valid Order Quantity...");  
			  }else {
				  resp = client.target(webServiceURI+"/"+order+"/"+qty).request("text/plain").get();
				  System.out.println("Order taken sucessfully, Your order ref no is : "+ resp.readEntity(String.class) );  
			  }
			}else if("g".equalsIgnoreCase(order)) {
			  System.out.println("PLease Enter the Order ref No...");
			  qty = scanner.nextLine();
			  resp = client.target(webServiceURI+"/"+order+"/"+qty).request("text/plain").get();
			  System.out.println(resp.readEntity(String.class) );
			}else if("u".equalsIgnoreCase(order)) {
			  System.out.println("PLease Enter the Order ref No...");
			  String upOrd = scanner.nextLine();
			  System.out.println("Please Enter the quantity...");
			  qty = scanner.nextLine();
			  resp = client.target(webServiceURI+"/"+order+"/"+upOrd+":"+qty).request("text/plain").get();
			  System.out.println(resp.readEntity(String.class) );
			}else if("f".equalsIgnoreCase(order)) {
			  System.out.println("PLease Enter the Order ref to Fulfil...");
			  String upOrd = scanner.nextLine();
			  resp = client.target(webServiceURI+"/"+order+"/"+upOrd).request("text/plain").get();
			  System.out.println(resp.readEntity(String.class) );
			}else if("a".equalsIgnoreCase(order)) {
			  resp = client.target(webServiceURI+"/"+order).request("text/plain").get();
			  System.out.println(resp.readEntity(String.class) );
			}else if("e".equalsIgnoreCase(order)) {
				  System.out.println("Sucessfully Exit......");
			}else {
				 System.out.println("Invalid Entry...");
			}
		
		if(!"e".equalsIgnoreCase(order)) {
			System.out.println("-------------------------------------");
			System.out.println("Create Order as c | Get Order as g | Update Order as u | Fulfil Order as f | Get all Order as a | Exit from operations as e");
			System.out.println("Select from above operations......");
			selectionControls(scanner.nextLine());
		}
	}catch(Exception ex) {
		System.out.println(ex.getMessage());
	}
  }
	/*
	 * Method which validates the qty entered in number
	 */
	public static boolean orderQtyValidation(String orderQty) {
		try {
			Integer.parseInt(orderQty);
			return true;
		}catch(NumberFormatException ex) {
			return false;
		}
		
	}
}
