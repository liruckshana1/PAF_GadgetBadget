package com;



//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


import model.Funders;
@Path("/Funders")
public class FunderService{

	Funders funderObj = new Funders();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunder()
	{
		return funderObj.readFunder();
	}



@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertFunder(@FormParam("funderID") String funderID,
 @FormParam("funderName") String funderName,
 @FormParam("funderAddress") String funderAddress,
 @FormParam("funderPhno") String funderPhno,
 @FormParam("funderEmail") String funderEmail,
 @FormParam("amount") String amount,
 @FormParam("date") String date)
{
 String output = funderObj.insertFunder(funderID, funderName, funderAddress, funderPhno, funderEmail, amount, date);
 return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateFunder(String funderData)
{
//Convert the input string to a JSON object
 JsonObject funderObject = new JsonParser().parse(funderData).getAsJsonObject();
//Read the values from the JSON object
 String funderID = funderObject.get("funderID").getAsString();
 String funderName = funderObject.get("funderName").getAsString();
 String funderAddress = funderObject.get("funderAddress").getAsString();
 String funderPhno = funderObject.get("funderPhno").getAsString();
 String funderEmail = funderObject.get("funderEmail").getAsString();
 String amount = funderObject.get("amount").getAsString();
 String date = funderObject.get("date").getAsString();
 String output = funderObj.updateFunder(funderID, funderName, funderAddress, funderPhno, funderEmail, amount, date);
return output;
}


@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteFunder(String funderData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(funderData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String funderID = doc.select("funderID").text();
 String output = funderObj.deleteFunder(funderID);
return output;
}
}



