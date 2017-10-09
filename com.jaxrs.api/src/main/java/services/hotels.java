package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class hotels {

	private String id;
	private String hotelName;
	private String location;
	private String ratings;
	private String landmark;
	private String startedOn;
	private String famousDish;

	private String info;



	
	public String getId() {
		return id;
	}


	public hotels()
	{
		
	}
	

	
	
	public hotels(String comments)
	{
      this.info = comments;
	}

	



	

	public  hotels(String id, String hotelName,String location, String ratings, String famousDish,String startedOn, String landmark)
	{
 
		this.id = id;
		this.hotelName = hotelName;
		this.location=location;
		this.ratings = ratings;
		this.landmark = landmark;
		this.startedOn=startedOn;
		this.famousDish=famousDish;


	}
	
	
	
	
	

	public String getStartedOn() {
		return startedOn;
	}


	public void setStartedOn(String startedOn) {
		this.startedOn = startedOn;
	}


	public String getFamousDish() {
		return famousDish;
	}


	public void setFamousDish(String famousDish) {
		this.famousDish = famousDish;
	}


	public void setId(String id) {
		this.id = id;
	}


public String getHotelName() {
		return hotelName;
	}


	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}




	public String getLocation() {
		return location;
	}




	public void setLocation(String location) {
		this.location = location;
	}




	public String getRatings() {
		return ratings;
	}




	public void setRatings(String ratings) {
		this.ratings = ratings;
	}




	public String getLandmark() {
		return landmark;
	}




	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	

	@Override
	public String toString() {
		return "{\"hotelName\":\""+hotelName+"\","
				+ "\"location\":\""+location+"\","
						+ "\"ratings\":"+ratings+","
								+ "\"famousDish\":\""+famousDish+"\","
										+ "\"startedOn\":\""+startedOn+"\","
												+ "\"id\":"+id+","
														+ "\"landmark\":\""+landmark+"\"}";
	}









}
