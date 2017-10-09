package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;

import Database.gSheetService;
import Database.gSheetColumns;



public class hotelServices {

	
	

	gSheetColumns col = new gSheetColumns();
	gSheetService utils = new gSheetService();

	
	

	
	
	
	public List<hotels> getAllHotels() throws IOException
	
	{
		
		
		List<hotels>  list = new ArrayList<hotels>();	
	
	 
		try {
			utils.retriveResponse();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int currRow=1;currRow<=(utils.maxRow-1);currRow++)
		{		
			String ID =  (utils.getRowData(currRow))[0].replaceAll("\"", "");
			String HoteName = (utils.getRowData(currRow))[1].replaceAll("\"", "");
			String Location = (utils.getRowData(currRow))[2].replaceAll("\"", "");
			String Ratings = (utils.getRowData(currRow))[3].replaceAll("\"", "");
			String FamousDish = (utils.getRowData(currRow))[4].replaceAll("\"", "");
			String StartedOn = (utils.getRowData(currRow))[5].replaceAll("\"", "");
			String Landmark = (utils.getRowData(currRow))[6].replaceAll("\"", "");
            hotels thisHotel = new hotels(ID,HoteName,Location,Ratings,FamousDish,StartedOn,Landmark);
		    list.add(thisHotel);	
		}
			
		
		
		return list;
		
		
		
	}




	
	public List<hotels> lookupBy(Map<String, String> hm) 
	{
		
		
		List<hotels>  list = new ArrayList<hotels>();	
		
		 
		try {
			utils.retriveResponse();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int currRow=1;currRow<=(utils.maxRow-1);currRow++)
		{		
			String ID =  (utils.getRowData(currRow))[0].replaceAll("\"", "").replaceAll(" ", "");
			String HotelName = (utils.getRowData(currRow))[1].replaceAll("\"", "").replaceAll(" ", "");
			String Location = (utils.getRowData(currRow))[2].replaceAll("\"", "").replaceAll(" ", "");
			String Ratings = (utils.getRowData(currRow))[3].replaceAll("\"", "").replaceAll(" ", "");
			String FamousDish = (utils.getRowData(currRow))[4].replaceAll("\"", "").replaceAll(" ", "");
			String StartedOn = (utils.getRowData(currRow))[5].replaceAll("\"", "").replaceAll(" ", "");
			String Landmark = (utils.getRowData(currRow))[6].replaceAll("\"", "").replaceAll(" ", "");
			
			
			
			if(Ratings.equalsIgnoreCase(hm.get("ratings")))
			{
				
				if(Location.equalsIgnoreCase(hm.get("location")))
				{
					 hotels thisHotel = new hotels(ID,HotelName,Location,Ratings,FamousDish,StartedOn,Landmark);     
					    list.add(thisHotel);	
				}
			}
	       
			
		}
		
	
		
		
		if(list.size()==0)
		{

	    	hotels comments = new hotels("No hotels found for the requested search parameters");
            list.add(comments);
		}
		
			
		return list;
		 
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public List<hotels> lookupBy(String searchParam, String value) 
	{
		
		
		List<hotels>  list = new ArrayList<hotels>();	
		
		 
		try {
			utils.retriveResponse();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int currRow=1;currRow<=(utils.maxRow-1);currRow++)
		{		
			String ID =  (utils.getRowData(currRow))[0].replaceAll("\"", "").replaceAll(" ", "");
			String HotelName = (utils.getRowData(currRow))[1].replaceAll("\"", "").replaceAll(" ", "");
			String Location = (utils.getRowData(currRow))[2].replaceAll("\"", "").replaceAll(" ", "");
			String Ratings = (utils.getRowData(currRow))[3].replaceAll("\"", "").replaceAll(" ", "");
			String FamousDish = (utils.getRowData(currRow))[4].replaceAll("\"", "").replaceAll(" ", "");
			String StartedOn = (utils.getRowData(currRow))[5].replaceAll("\"", "").replaceAll(" ", "");
			String Landmark = (utils.getRowData(currRow))[6].replaceAll("\"", "").replaceAll(" ", "");
			
			
			String expected = null;
			switch(searchParam)
			{
			case "ratings": expected = Ratings;
			break;
			case "hotelName": expected= HotelName;
			break;
			case "ID": expected = ID;
			break;
			case "location": expected = Location;
			break;
			case "startedOn": expected=StartedOn;
			break;
			case "landmark": expected= Landmark;
			break;
			case "famousDish": expected=FamousDish;
			break;


			}
			
			
			if(expected.equalsIgnoreCase(value))
			{
            hotels thisHotel = new hotels(ID,HotelName,Location,Ratings,FamousDish,StartedOn,Landmark);     
		    list.add(thisHotel);	
			}
		}
		
		
		if(list.size()==0)
		{

	    	hotels comments = new hotels("No hotels found for the requested " +searchParam.toLowerCase() + ":" + value+  " in our records");
            list.add(comments);
		}
		
			
		return list;
		 
	}
	
	
}
	
