package Database;


	import java.io.BufferedReader;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.time.temporal.ValueRange;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Collections;
	import java.util.HashMap;
	import java.util.List;
import java.util.Properties;
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
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport.Builder;
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
import com.google.gdata.data.analytics.CustomVariable.Scope;

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





	public class independentService extends gSheetColumns{


		HashMap<Integer,String[]> myJsonContent = new HashMap<Integer, String[]>();
		static String spreadsheetId = "15nHxfeM-6RzeBfbCbL2coDnQGgXShKOYo6N_OKJmA4M";
		public int  maxRow;
		public int Range;
		private static final String APPLICATION_NAME =
				"Google Sheets API Java Quickstart";
		private static final java.io.File DATA_STORE_DIR = new java.io.File("C:\\Users\\334750\\.credentials\\");

		private static final JsonFactory JSON_FACTORY =
				JacksonFactory.getDefaultInstance();
		private static HttpTransport HTTP_TRANSPORT;
		private static HttpTransport HTTP_TRANSPORT1;
		private static HttpTransport HTTP_TRANSPORT2;
		
		
		static Builder netHttpBuilder;

		
		



			static Credential credential;
			
			static Sheets service;


			
			

			
			static {
				try {
					HTTP_TRANSPORT1 = GoogleNetHttpTransport.newTrustedTransport();
				
				  netHttpBuilder = NetHttpTransport.Builder.class.newInstance();
				  HTTP_TRANSPORT2 =  netHttpBuilder.doNotValidateCertificate().build();
					
				} catch (Throwable t) {
					t.printStackTrace();
					System.exit(1);
				}
			}
			
			independentService(){
			
				
				
				try{
					
					
           			InetAddress Inet = InetAddress.getByName("10.237.69.139");
					int PROXY_PORT =6050;
					Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Inet, PROXY_PORT));
					HTTP_TRANSPORT = new NetHttpTransport.Builder().setProxy(proxy).build();
			
					
					final String authUser = "cts\334750";
					final String authPassword = "Aug@2017";
					Authenticator.setDefault(
					   new Authenticator() {
					      @Override
					      public PasswordAuthentication getPasswordAuthentication() {
					         return new PasswordAuthentication(
					               authUser, authPassword.toCharArray());
					      }
					   }
					);

					System.setProperty("http.proxyUser", authUser);
					System.setProperty("http.proxyPassword", authPassword);
					
			
					credential = generateCredentialWithUserApprovedToken();
					System.out.println(credential.getRefreshToken());
					credential.refreshToken();
				    System.out.println(credential.getAccessToken());
				  
				    
					service = buildService();
					
						
					
				} catch (IOException | GeneralSecurityException e) {
				
					e.printStackTrace();
		
				
				}
			}
			
			
			public static Sheets buildService() throws IOException {
			       
			    System.setProperty("http.proxyHost", "10.237.69.139");
				System.setProperty("http.proxyPort", "6050");
				System.setProperty("https.proxyHost", "10.237.69.139");
				System.setProperty("https.proxyPort", "6050");
				

			
							// Load client secrets.
							InputStream in =
									gSheetService.class.getResourceAsStream("/cred_auto.json");
							 
				
					
				   InputStreamReader inputStreamReader =
				       new InputStreamReader(in);
				   GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, inputStreamReader);
				   
					
					GoogleCredential cred = new GoogleCredential.Builder()
		                    .setClientSecrets(clientSecrets)
		                    .setTransport(HTTP_TRANSPORT)
		                    .setJsonFactory(JSON_FACTORY)
		                    .build()
		                    .setAccessToken(credential.getAccessToken())
		                    .setRefreshToken(credential.getRefreshToken());   
					
			
		
				
				return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, cred)
						.setApplicationName("Google Sheets REST Services")
						.build();
			}
			
		
	private static Credential generateCredentialWithUserApprovedToken() throws IOException,GeneralSecurityException {
				 
	    System.setProperty("http.proxyHost", "10.237.69.139");
		System.setProperty("http.proxyPort", "6050");
		System.setProperty("https.proxyHost", "10.237.69.139");
		System.setProperty("https.proxyPort", "6050");
		

	
					// Load client secrets.
					InputStream in =
							gSheetService.class.getResourceAsStream("/cred_auto.json");
					 
		
			
		   InputStreamReader inputStreamReader =
		       new InputStreamReader(in);
		   GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, inputStreamReader);
		   

		   return new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT).setTransport(HTTP_TRANSPORT1).setTransport(HTTP_TRANSPORT2).setJsonFactory(JSON_FACTORY)
		       .setClientSecrets(clientSecrets).build().setRefreshToken("1/ZwGgv7Pb6GlXEQxiEfpKqVVgEZOP0T0GER_81RdFjpY");
		   
		 }
		

		public String[] getRowData(int row)
		{

			return  myJsonContent.get(row);
		}
		

	}
		
		
		


	
	
	
