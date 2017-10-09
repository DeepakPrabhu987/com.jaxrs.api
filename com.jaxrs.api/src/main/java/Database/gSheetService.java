package Database;


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
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
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




public class gSheetService extends gSheetColumns{


	HashMap<Integer,String[]> myJsonContent = new HashMap<Integer, String[]>();
	String spreadsheetId = "15nHxfeM-6RzeBfbCbL2coDnQGgXShKOYo6N_OKJmA4M";
	public int  maxRow;
	public int Range;
	private static final String APPLICATION_NAME =
			"Google Sheets API Java Quickstart";
	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart");
	private static FileDataStoreFactory DATA_STORE_FACTORY;
	private static final JsonFactory JSON_FACTORY =
			JacksonFactory.getDefaultInstance();
	private static HttpTransport HTTP_TRANSPORT;
	private static final List<String> SCOPES =
			Arrays.asList(SheetsScopes.SPREADSHEETS);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}
	public static Credential authorize() throws IOException {


		//String API_KEY ="AIzaSyCHxuBS04U2fD1yBkc86IZAT2YHiaHrNAQ";

		// Load client secrets.
		InputStream in =
				gSheetService.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets =
				GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow =
				new GoogleAuthorizationCodeFlow.Builder(
						HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline").setApprovalPrompt("force") .build(); 

		Credential credential = new AuthorizationCodeInstalledApp(
				flow, new LocalServerReceiver()).authorize("user");
		System.out.println(
				"Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	} 

	public static Sheets getSheetsService() throws IOException {
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}


	public String[] getRowData(int row)
	{

		return  myJsonContent.get(row);
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

				gservice.spreadsheets().batchUpdate(spreadsheetId, content).execute();
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

		
		
		Sheets service = getSheetsService();
		
		
	
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

		Sheets gservice = getSheetsService();




		//Takes data from the below defined range
		String range =  "A1:Z100";

		com.google.api.services.sheets.v4.model.ValueRange response = gservice.spreadsheets().values()
				.get(spreadsheetId, range)
				.execute();

		String [] rowValues = new String[100];
		rowValues = response.toPrettyString().split("]");

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


}


