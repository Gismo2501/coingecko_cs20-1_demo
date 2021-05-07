package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

public class CheckCurrencyInInterval {
	//Creates new File
	LocalDate date = LocalDate.now();
	File curFile;
	
	
	public void startCheck(String cur, String fiat, int time, int durchlauf) throws IOException {
		// Get local time
		LocalTime now = LocalTime.now();
		// create new File to write in
		curFile = new File(cur+"-"+date.toString()+"--"+now.getHour()+"-"+now.getMinute()+".txt");
		System.out.println(curFile.getAbsolutePath());
		// Write Top lines
		BufferedWriter firstWrite = new BufferedWriter(new FileWriter(curFile.getPath(), true));
		firstWrite.write("Preis("+cur+")"+"  "+"Währung"+"  "+"Zeitstempel\n========================================\n");
		firstWrite.close();
		
		
		
		
		// Create Timer for schedule
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			// Timer will cancel after 10 runs
			int timerLauf = 0;
			  @Override
			  public void run() {
				  if (timerLauf < durchlauf) {
					  writeInFile(cur, fiat, timerLauf);
					  timerLauf++;
					  
				  } else {
					  timer.cancel();
				  }
				  
				  
				  
				  
			  }
			  // delay, period
			}, time*1000, time*1000);
		
		
	}
	
	
	public String getInformation(String cur, String fiat) {
		try {
			// Creates an HTTPClient (Java 11+ required) and builds an HTTP Request to the Coingecko API
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.coingecko.com/api/v3/simple/price?ids=" + cur.toLowerCase()
							+ "&vs_currencies=" + fiat.toLowerCase()))
					.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			//The response is always a JSON String --> Converts response String into JSON Object with org.json Class
			JSONObject obj = new JSONObject(response.body());
			//Fetch the Value out of the JSON String
			Double value = obj.getJSONObject(cur.toLowerCase()).getDouble(fiat.toLowerCase());

			return value.toString();
			//Catch JSON Error for Example: wrong currency given or not supported currency
		} catch (org.json.JSONException e) {
			System.out.println("Upsala. Da stimmt was mit deiner Eingabe nicht! Überprüfe diese!");
			e.printStackTrace();
			return "";
		} catch (Exception err) {
			System.out.println("Ups.. Da stimmt etwas nicht!");
			err.printStackTrace();
			return "";
		}
	}
	
	public void writeInFile(String cur, String fiat, int durchlauf) {
		// Used to write the information in the file
		
		// Get local time
		LocalTime time = LocalTime.now();
		
		try {
			 // Write the Information in the file
			 BufferedWriter curWriter = new BufferedWriter(new FileWriter(curFile.getPath(), true));
			 curWriter.append(durchlauf+1+". "+getInformation(cur, fiat)+"        "+fiat+"        "+time.getHour()+":"+time.getMinute()+":"+time.getSecond()+"\n");
			 curWriter.close();
			
		} catch (Exception err) {
			System.out.println(err.toString());
			
		}
	}

}


