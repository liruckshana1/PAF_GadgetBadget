package com;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Payment;


@Path("/Payment")
public class PaymentService {
	Payment payObj = new Payment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return payObj.readPayment();
	 }
	

	
	
	@POST
	@Path("/")
	//to specify the input type as form data.
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// produce a status message to specify
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment( @FormParam("Name") String Name,
	@FormParam("CardType") String CardType,
	 @FormParam("CardNo") String CardNo,
	 @FormParam("cvv") String cvv,
	 @FormParam("EXP_Month") String EXP_Month,
	 @FormParam("EXP_Year") String EXP_Year,
	 @FormParam("Amount") String Amount,
	 @FormParam("PaymentDate") String PaymentDate)
	{
	 String output = payObj.insertPayment(Name, CardType, CardNo, cvv, EXP_Month, EXP_Year, Amount, PaymentDate);
	return output;
	}
	



	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String PaymentId = itemObject.get("PaymentId").getAsString();
	 String Name = itemObject.get("Name").getAsString();
	 String CardType = itemObject.get("CardType").getAsString();
	 String CardNo = itemObject.get("CardNo").getAsString();
	 String cvv = itemObject.get("cvv").getAsString();
	 String EXP_Month = itemObject.get("EXP_Month").getAsString();
	 String EXP_Year = itemObject.get("EXP_Year").getAsString();
	 String Amount = itemObject.get("Amount").getAsString();
	 String PaymentDate = itemObject.get("PaymentDate").getAsString();
	 

	 String output = payObj.updatePayment(PaymentId, Name, CardType, CardNo, cvv, EXP_Month, EXP_Year, Amount, PaymentDate);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	
	//Read the value from the element <itemID>
	 String PaymentID = doc.select("PaymentId").text();
	 String output = payObj.deletePayment(PaymentID);
	return output;
	}

}