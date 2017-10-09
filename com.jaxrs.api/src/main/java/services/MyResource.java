package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.api.client.util.Key;
import com.google.api.services.sheets.v4.model.Response;
import com.google.gdata.util.parser.Parser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import Database.gSheetService;


@Path("/hotels")
public class MyResource {

   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    public String getResources() throws IOException {
    	
    	hotelServices services = new hotelServices();

    
           String jsonItems = new Gson().toJson(services.getAllHotels());
        
          
    	jsonItems =  "{\"hotels\":"+jsonItems+"}";
    	return jsonItems;
    	
    }
    

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{id}")

	
    public String getResourcesByID(@PathParam("id")String IDvalue) throws IOException {
	
    	
    	hotelServices services = new hotelServices();
   
        String jsonItems = new Gson().toJson(services.lookupBy("ID",IDvalue));
   
        jsonItems =  "{\"hotels\":"+jsonItems+"}";
    	return jsonItems;
   
    }
   
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")

	
    public String listBy(@Context UriInfo info) throws IOException {
	
		
		Map<String,String> hm = new LinkedHashMap<String,String>();
		
		
    	
		String value1 = info.getQueryParameters().getFirst("ratings");
		hm.put("ratings", value1);
		String value2= info.getQueryParameters().getFirst("location");
		hm.put("location", value2);
		
	try{
		value1.toString();
	}catch(Exception ee)
	{
		value1="empty";
	}
		
	try{
		value2.toString();
	}catch(Exception ee)
	{
		value2="empty";
	}	
		
    	hotelServices services = new hotelServices();
    	
    	String jsonItems = null;
    	if(value2.equals("empty")&&(!(value1.equals("empty"))))
    	{
    		jsonItems = new Gson().toJson(services.lookupBy("ratings",value1));
    	} else if(value1.equals("empty")&&(!(value2.equals("empty")))){
    		jsonItems = new Gson().toJson(services.lookupBy("location",value2));
    	} else if((!value1.equals(""))&&(!value2.equals(""))){
    		jsonItems = new Gson().toJson(services.lookupBy(hm));
    	}

    	
        jsonItems =  "{\"hotels\":"+jsonItems+"}";
    	return jsonItems;
   
    }
	
	
	
   
	@DELETE
    @Path("/delete/{id}")

	
    public String deleteHotelsByID(@PathParam("id")String IDvalue) throws IOException {
	
    	List<hotels> list = new ArrayList<hotels>();
    	gSheetService services = new gSheetService();
    	hotels comments = new hotels();
    	String message;
   
   if (services.deleteHotel(IDvalue).toLowerCase().equals("true"))
   {
	   
	   
	comments = new hotels("Records deleted as expected");
      
      
   }else if  (services.deleteHotel(IDvalue).toLowerCase().equals("false")) {
     comments = new hotels("Unable to delete the record");
     
	   
   }
   else if  (services.deleteHotel(IDvalue).toLowerCase().equals("not found")) {
	   comments = new hotels("No such records found");
       
   }
   
   list.add(comments);
   message = new Gson().toJson(list);
   message =  "{\"Response\":"+message+"}";
        
    	return message;
   
    }
   
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    
    public String addHotel(hotels hotel) throws IOException 
    {
    	
    	
    JSONObject json = new JSONObject(hotel.toString());
    List<hotels> list = new ArrayList<hotels>();
	gSheetService qs = new gSheetService();
	String message = qs.addData(json); // calling the main addData logic
	
	

	hotels comments = new hotels();
	comments = new hotels(message.replace("\"", ""));
	 list.add(comments);
	   message = new Gson().toJson(list);
	
	   return message;
    
    }	

    
    
}