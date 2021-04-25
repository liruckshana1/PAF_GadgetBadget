package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

import model.Customer;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/Customers")
public class CustomerService {
Customer customerObj = new Customer(); 
	
	@GET
	@Path("/view") 
	@Produces(MediaType.TEXT_HTML) 
	public String readCustomers() 
	 { 
	 return  customerObj.readCustomer();
	 } 
	
	@POST
	@Path("/add") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertCustomer(@FormParam("customerName") String customerName, 
	 @FormParam("customerAge") int customerAge, 
	 @FormParam("customerAddress") String customerAddress,
	 @FormParam("customerEmail") String customerEmail) 
	{
		 String output = customerObj.insertCustomer(customerName, customerAge, customerAddress, customerEmail); 
			return output; 
			}
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustomer(String customerData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String customerID = customerObject.get("customerID").getAsString();  
	 String customerName = customerObject.get("customerName").getAsString(); 
	 String customerAge = customerObject.get("customerAge").getAsString(); 
	 String customerAddress = customerObject.get("customerAddress").getAsString(); 
	 String customerEmail = customerObject.get("customerEmail").getAsString(); 
	 
	 String output = customerObj.updateCustomer(customerID,customerName, customerAge, customerAddress, customerEmail); 
		return output; 
		}
		
	@DELETE
	@Path("/delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteCustomer(String customerData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <customerID>
	 String customerID = doc.select("customerID").text(); 
	 String output = customerObj.deleteCustomer(customerID); 
	return output; 
	}
}
