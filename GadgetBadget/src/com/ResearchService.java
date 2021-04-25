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

import model.Research;


	@Path("/Research")
	public class ResearchService
	{
	 Research resObj = new Research();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return resObj.readResearch();
	 }
	
	
	
	
	
	
	@POST
	@Path("/")
	//to specify the input type as form data.
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// produce a status message to specify
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch( @FormParam("ProjectCode") String ProjectCode,
	@FormParam("ProjectName") String ProjectName,
	 @FormParam("ProjectType") String ProjectType,
	 @FormParam("Price") String Price)
	{
	 String output = resObj.insertResearch(ProjectCode, ProjectName, ProjectType, Price);
	return output;
	}
	
	
	
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearch(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String ProjectID = itemObject.get("ProjectID").getAsString();
	 String ProjectCode = itemObject.get("ProjectCode").getAsString();
	 String ProjectName = itemObject.get("ProjectName").getAsString();
	 String ProjectType = itemObject.get("ProjectType").getAsString();
	 String Price = itemObject.get("Price").getAsString();
	
	 String output = resObj.updateResearch(ProjectID, ProjectCode, ProjectName, ProjectType, Price);
	return output;
	}

	
	
	
	

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearch(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	
	//Read the value from the element <itemID>
	 String ProjectID = doc.select("ProjectID").text();
	 String output = resObj.deleteResearch(ProjectID);
	return output;
	}


	}

