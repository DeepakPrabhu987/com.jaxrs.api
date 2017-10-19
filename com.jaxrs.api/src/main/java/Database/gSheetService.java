package Database;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.ws.rs.core.Response;
import javax.xml.ws.soap.AddressingFeature.Responses;

import org.json.JSONObject;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.DeleteDimensionRequest;
import com.google.api.services.sheets.v4.model.DimensionRange;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.GridCoordinate;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.UpdateCellsRequest;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;





public class gSheetService extends independentService{

	
	Sheets service;
	
	public gSheetService()
	{
		this.service = independentService.service;
		
	}
	

	public String[] getRowData(int row)
	{

		return  myJsonContent.get(row);
	}
	
	public void WriteIntoCell() throws IOException {
		
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



public String getUniqueIDNumber() throws IOException
{
	
	int idNo = 69;
	
	retriveResponse();

	ArrayList<String> IdArrayDB = new ArrayList<String>();
	for(int counter=1;counter<=myJsonContent.size();counter++)
	{
      
		String[] completeRow = myJsonContent.get(counter);
		String DBids=completeRow[0].replace("\"", "").replaceAll(" ", "");
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
		

			try {
				spreadsheet = service.spreadsheets().get(spreadsheetId).execute();
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

				service.spreadsheets().batchUpdate(spreadsheetId, content).execute();
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


	public  String addData(JSONObject json) throws IOException{

		BatchUpdateValuesResponse response = null;
		String Response = null;

try{
		String myUniqueID = getUniqueIDNumber();
		String range = "A"+(maxRow+1)+":G"+(maxRow+1);
		System.out.println(range);

		List<List<Object>> arrData = getData(json,myUniqueID);

		com.google.api.services.sheets.v4.model.ValueRange oRange = new com.google.api.services.sheets.v4.model.ValueRange();
		oRange.setRange(range);
		oRange.setValues(arrData);

		List<com.google.api.services.sheets.v4.model.ValueRange> oList = new ArrayList<>();
		oList.add(oRange);

		BatchUpdateValuesRequest oRequest = new BatchUpdateValuesRequest();
		oRequest.setValueInputOption("RAW");
		oRequest.setData(oList);

		
		
		
		
		
	
		response = service.spreadsheets().values().batchUpdate(spreadsheetId,oRequest).execute();
        Response = response.toString();
		// service.spreadsheets().values().update (spreadsheetId, range,) ;     
		//return request;
}catch(Exception e)
{
	
	 Response = response.toString();
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

	



		//Takes data from the below defined range
		String range =  "A1:Z100";

		com.google.api.services.sheets.v4.model.ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range)
				.execute();

		String [] rowValues = new String[100];
		rowValues = response.toString().split("]");

		int validRows = rowValues.length-3; // Have used 3 in order t neglect extra index values that were created while converting the response into pretty string
		System.out.println("validRows: "+ validRows);

		maxRow = validRows+1; // +1 since I included header row 

		String result = null;
		for(int rowNo=1;rowNo<=validRows;rowNo++)
		{
			result = rowValues[rowNo].substring(3);
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
	
	
	

