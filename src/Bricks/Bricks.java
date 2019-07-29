package Bricks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


// annotations rest service
@Path("/bricks")
public class Bricks {
	
	private static Map<String,BricksEntity> map  =new HashMap<String,BricksEntity>();
	private static int i =0;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		String response = "Hi Iam Kiran";
		return response;
	}
	
	/*
	 * Method is to create orders
	 */
	@GET
	@Path("/c/{order}")
	@Produces(MediaType.TEXT_PLAIN)
	public String createOrder(@PathParam("order") String order) {
				i = i+1;
				BricksEntity be = new BricksEntity();
				String ordRef= i+"c";//Generates Unique ref oredr number
				be.setQuantity(order);
				be.setStatus("Pending");
				map.put(ordRef, be);
		return ordRef;
	}
	
	
	/*
	 * Method is to get orders
	 */
	@GET
	@Path("/g/{order}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getOrder(@PathParam("order") String order) {
		String response = "";
		
		if(!map.containsKey(order)) {
			response = "Invalid Order No..";	
		}else {
			BricksEntity be = map.get(order);
			response = "Order ref no is "+order+" and Ordered Qty is "+be.getQuantity();
		}
		return response;
	}
	
	/*
	 * Method is to update orders
	 */
	@GET
	@Path("/u/{order}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOrder(@PathParam("order") String order) {
		String response = "";
		String[] str = order.split(":");
		BricksEntity be = null;
		
		if(!map.containsKey(str[0])) {
			response = "Invalid Order No.....";
		}else {
			be = map.get(str[0]);
			if("Dispatched".equals(be.getStatus())){
				response = "400 bad request..";	
			}else {
				if(!map.containsKey(str[0])){
					response = "Invalid Order No..";	
				}else {
					be = new BricksEntity();
					be.setQuantity(str[1]);
					be.setStatus("Pending");
					map.put(str[0],be);
					response = "Order ref no is "+str[0]+" and Updated Ordered Qty is "+be.getQuantity()+" and Status is  "+be.getStatus()+"";
				}
			}
		}
		return response;
	}
	
	/*
	 * Method is to fulfil orders
	 */
	@GET
	@Path("/f/{order}")
	@Produces(MediaType.TEXT_PLAIN)
	public String fulfilOrder(@PathParam("order") String order) {
		String response = "";
		
		if(!map.containsKey(order)) {
			response = "400 bad request..";	
		}else {
			BricksEntity be = new BricksEntity();
			be = map.get(order);
			be.setStatus("Dispatched");
			map.put(order,be);
			response = "Order ref no "+order+" is Dispatched..";
			
			
		}
		return response;
	}
	
	/*
	 * Method is to get all orders
	 */
	@GET
	@Path("/a")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllOrders() {
		return map.toString();
	}
}
