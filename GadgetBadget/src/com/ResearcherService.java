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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Researcher;
import javax.ws.rs.core.Response;

@Path("/Researchers")
public class ResearcherService {
Researcher researcherObj = new Researcher(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON) 
	public String readResearchers() {
	
	  return researcherObj.readJsonResearcher().toString();
	 
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertResearcher(@FormParam("researcherName") String researcherName, 
	 @FormParam("researcherPhone") int researcherPhone, 
	 @FormParam("researcherUniversity") String researcherUniversity) 
	{ 
	 String output = researcherObj.insertResearcher(researcherName, researcherPhone, researcherUniversity); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateResearcher(String researcherData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject researcherObject = new JsonParser().parse(researcherData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String researcherID = researcherObject.get("researcherID").getAsString();  
	 String researcherName = researcherObject.get("researcherName").getAsString(); 
	 String researcherPhone = researcherObject.get("researcherPhone").getAsString(); 
	 String researcherUniversity = researcherObject.get("researcherUniversity").getAsString(); 
	 String output = researcherObj.updateCustomer(researcherID,researcherName, researcherPhone, researcherUniversity); 
		return output; 
		}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteResearcher(String researcherData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(researcherData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String researcherID = doc.select("researcherID").text(); 
	 String output = researcherObj.deleteResearcher(researcherID); 
	return output; 
	}

}
