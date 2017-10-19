package com.jaxrs.api;
/*package Database;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.TokenErrorResponse;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.DeleteDimensionRequest;
import com.google.api.services.sheets.v4.model.DimensionRange;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import services.gSheetCredentials;




public class gSheetService_mod extends gSheetColumns {


	private static final File DATA_STORE_DIR =  new File("C:\\Users\\334750\\Documents\\workspace\\ServerProj\\com.jaxrs.api\\com.jaxrs.api\\src\\main\\resources");
	HashMap<Integer,String[]> myJsonContent = new HashMap<Integer, String[]>();
	String spreadsheetId = "15nHxfeM-6RzeBfbCbL2coDnQGgXShKOYo6N_OKJmA4M";

  

	public int  maxRow;
	public int Range;
	String cred1 = "?ke";
	String cred2 = "y=AIzaSyDaTnDlppwOaW";
	String cred3 = "I7lVz3x2QgOXPeXVKKBMA";
	public String CLIENT_ID ="490610843204-b487jp0o61k53ieurkleohmf0i8al54d.apps.googleusercontent.com";
	public String CLIENT_SECRET="Zr_BOvw0KAp0M7xC6nrWSqNX";

	

	
	public String[] getRowData(int row)
	{

		
		return myJsonContent.get(row);
	}
	
	public void WriteIntoCell() throws IOException {
		Sheets service = getSheetsService();
		List<Request> requests = new ArrayList<>();

		List<CellData> values = new ArrayList<>();


		values.add(new CellData()
				.setUserEnteredValue(new ExtendedValue()
						.setStringValue("Hello World!")));
		requests.add(new Request()
				.setUpdateCells(new UpdateCellsRequest()
						.setStart(new GridCoordinate()
								.setSheetId(0)
								.setRowIndex(0)
								.setColumnIndex(0))
						.setRows(Arrays.asList(
								new RowData().setValues(values)))
						.setFields("userEnteredValue,userEnteredFormat.backgroundColor")));

		BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
				.setRequests(requests);
		service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest)
		.execute();
	}



	public void  getAccessToken() throws IOException
	{
		
	     HttpTransport httpTransport = new NetHttpTransport();
	        JsonFactory jsonFactory = new JacksonFactory();

	        // If we do not already have a refresh token a flow is created to get a refresh token.
	        // To get the token the user has to visit a web site and enter the code afterwards
	        // The refresh token is saved and may be reused.
	        final GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	                httpTransport,
	                jsonFactory,
	                CLIENT_ID,
	                CLIENT_SECRET,
	                Arrays.asList(SheetsScopes.SPREADSHEETS))
	        .setAccessType("offline")
	        .setApprovalPrompt("auto").build();
	       
	        
	    
	 
	        // Setting access token
//        	Credential credential = new GoogleCredential().setAccessToken("AIzaSyCHxuBS04U2fD1yBkc86IZAT2YHiaHrNAQ");
	            AuthorizationCodeRequestUrl authorizationUrl =
	                        flow.newAuthorizationUrl().setRedirectUri("https://sheets.googleapis.com/v4/spreadsheets/15nHxfeM-6RzeBfbCbL2coDnQGgXShKOYo6N_OKJmA4M/values:batchUpdate")
	                                .setApprovalPrompt("force")
	                                .setAccessType("offline");
	        
	        

	        Credential credential = new AuthorizationCodeInstalledApp(
	       flow, new LocalServerReceiver()).authorize("user");
	        System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
	        System.out.println("My Access Token: "+credential.getAccessToken());
	        System.out.println("My Referesh Token: "+credential.getRefreshToken());
	            
	            
	        gSheetCredentials.getAccessTokenFromRefreshToken( "1/WVeSF6dK_CjKHpTQv01r19GISc0tjiq7TPMqgjgnzLA", httpTransport, jsonFactory, "490610843204-b487jp0o61k53ieurkleohmf0i8al54d.apps.googleusercontent.com", "Zr_BOvw0KAp0M7xC6nrWSqNX");
		
		
	}
	
	
	
	
public String getUniqueIDNumber() throws IOException
{
	
	int idNo = 69;
	
	retriveResponse();

	ArrayList<String> IdArrayDB = new ArrayList<String>();
	for(int counter=1;counter<=myJsonContent.size();counter++)
	{
      
		String[] completeRow = myJsonContent.get(counter);
		String DBids=removeWhiteSpaces(completeRow[0].replace("\"", "").replace("[", ""));
		IdArrayDB.add(DBids);

	}
	
	boolean idFound = true;
	
	while(idFound==true)
	{
		idNo++;
		idFound=false;
		for (int i=0; i<IdArrayDB.size(); i++)
		{
			
			if (Integer.parseInt(IdArrayDB.get(i))==(idNo))
			{  
				idFound=true;
				break;
			}	

		}
		
	}

	String id = idNo+"";
	return id;
	
}



	public String checkEntries(String IDvalue) throws IOException
	{


		retriveResponse();
		Range = 0;
		String records = "not found";
		for(int counter=1;counter<=myJsonContent.size();counter++)
		{

			String[] completeRow = myJsonContent.get(counter);
			String actual=completeRow[0].replace("\"", "").replaceAll(" ", "");

			if(actual.equals(IDvalue))
			{

				Range = counter;
				break;
			}


		}

		if(Range==0)
		{
			records= "not found";




		}else{
			records ="found";
		}

		return records;

	}

	public String deleteHotel(String IDvalue) throws IOException
	{


		String dataDeleted;
		if ((checkEntries(IDvalue).equalsIgnoreCase("not found")))
		{
			dataDeleted="not found";
			return dataDeleted;
		}else{
			
			
			//Deleting data

			Spreadsheet spreadsheet = null;
	      Sheets gservice = getSheetsService();

		try {
				spreadsheet = gservice.spreadsheets().get(spreadsheetId).execute();
			} catch (IOException e1) {
				dataDeleted= "false";
				e1.printStackTrace();
			}
		
		
		
			BatchUpdateSpreadsheetRequest content = new BatchUpdateSpreadsheetRequest();
			Request request = new Request();
			
			DeleteDimensionRequest deleteDimensionRequest = new DeleteDimensionRequest();
			DimensionRange dimensionRange = new DimensionRange();
			dimensionRange.setDimension("ROWS");
			dimensionRange.setStartIndex(Range);
			dimensionRange.setEndIndex(Range+1);

			dimensionRange.setSheetId(spreadsheet.getSheets().get(0).getProperties().getSheetId());
			deleteDimensionRequest.setRange(dimensionRange);

			request.setDeleteDimension(deleteDimensionRequest);

			List<Request> requests = new ArrayList<Request>();
			requests.add(request);
			content.setRequests(requests);

			try {
				dimensionRange.setSheetId(spreadsheet.getSheets().get(0).getProperties().getSheetId());

			//	gservice.spreadsheets().batchUpdate(spreadsheetId, content).execute();
				dataDeleted= "true";



			} catch (IOException e) {
				dataDeleted= "false";
				e.printStackTrace();

			} finally {
				dimensionRange = null;
				deleteDimensionRequest = null;
				request = null;
				requests = null;
				content = null;
			}

		}


		return dataDeleted;

	}


	//=========================WRITING DATA INTO SHEET======================================================


	public String addData(JSONObject json) throws IOException{

		
		String Response = null;

try{
		String myUniqueID = getUniqueIDNumber();
		String range = "A"+(maxRow+1)+":G"+(maxRow+1);
		System.out.println(range);

		List<List<Object>> arrData = getData(json,myUniqueID);
	
String bodyMsg=  "{\"valueInputOption\": \"RAW\",\"data\": [ { \"range\": \""+range+"\",\"values\" : [[ "+arrData+" ],] },]}";
		

System.out.println(bodyMsg);
		

		
		 //Hit service via URL for adding data in a batch
		  RestAssured.baseURI = "https://sheets.googleapis.com/v4/spreadsheets/15nHxfeM-6RzeBfbCbL2coDnQGgXShKOYo6N_OKJmA4M";
		  String ENDPOINT =  "/values:batchUpdate"+cred1+cred2+cred3;
		  Response restResponse = RestAssured.given().contentType(ContentType.JSON).body(bodyMsg).when().post(ENDPOINT);
		  Response = restResponse.asString();
		  
		
}catch(Exception e)
{
	
	System.out.println(e);
Response = "There was no enough data for the API to process the request";
}

		
		return Response;
	}
	

	public static List<List<Object>> getData (JSONObject json,String uniqueID)  {

		List<Object> obj = new ArrayList<Object>();
	
		  String id = uniqueID;
		  String hotelName = json.getString("hotelName");
		  String location = json.getString("location");
		  String ratings = json.get("ratings").toString();
		  String famousDish = json.getString("famousDish");
		  String startedOn = json.getString("startedOn");
		  String landmark = json.getString("landmark");
		  
		  obj.add(id);
		  obj.add(hotelName);
		  obj.add(location);
		  obj.add(ratings);
		  obj.add(famousDish);
		  obj.add(startedOn);
		  obj.add(landmark);
				
		List<List<Object>> lstObj = new ArrayList<List<Object>>();
		lstObj.add (obj);

		return lstObj;
	}

   //======================================================================================

	public String retriveResponse() throws IOException
	{


		getAccessToken();
		
    //Hit service via URL
		
	   RestAssured.baseURI = "https://sheets.googleapis.com/v4/spreadsheets/15nHxfeM-6RzeBfbCbL2coDnQGgXShKOYo6N_OKJmA4M";
		
		//Takes data from the below defined range
				String range =  "A1:Z100";
		
				
		
				
				
	String ENDPOINT = "/values/"+range+cred1+cred2+cred3;
		

	
	Response restResponse = RestAssured.given().contentType(ContentType.JSON).get(ENDPOINT);
   
		



		String [] rowValues = new String[100];
		rowValues = restResponse.asString().toString().split("]");

		int validRows = rowValues.length-3; // Have used 3 in order t neglect extra index values that were created while converting the response into pretty string
		System.out.println("validRows: "+ validRows);

		maxRow = validRows+1; // +1 since I included header row 

		String result = null;
		for(int rowNo=1;rowNo<=validRows;rowNo++)
		{
			result =  rowValues[rowNo].replace("\n", "");
			result = result.substring(3);
		
			myJsonContent.put(rowNo, result.split(","));

		}


		return result;
	}

	
	
	public String removeWhiteSpaces(String result)
	{
		
		
	

		boolean startIndexFound=false;
		boolean reversedStartIndexFound = false;
		int startIndex=0;
		int reversedStartIndex=0;

		while(startIndexFound==false)
		{

			if (!Character.isWhitespace(result.charAt(startIndex)))
			{
				startIndexFound=true;
				break;
			} else{

				startIndex++;
			}

		}


result = result.substring(startIndex);
String reversedString = new StringBuffer(result).reverse().toString();

		
		while (reversedStartIndexFound==false)
		{
			if (!Character.isWhitespace(reversedString.charAt(reversedStartIndex)))
			{
				reversedStartIndexFound=true;
				break;
			} else{

				reversedStartIndex++;
			}
		
		}
		
		reversedString = reversedString.substring(reversedStartIndex);
		
		result = new StringBuffer(reversedString).reverse().toString();
		
		return result;
		
	}
	
	
	

}


*/