/*package com.jaxrs.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

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

import Database.gSheetService;

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

// Receive access token after refreshing it



// Setting access token
//	Credential credential = new GoogleCredential().setAccessToken("AIzaSyCHxuBS04U2fD1yBkc86IZAT2YHiaHrNAQ");

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
*/