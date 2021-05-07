package main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetOtherInformation {
	
	public String getDescription(String crypto) throws IOException, InterruptedException {
		try {
			// Creates an HTTPClient (Java 11+ required) and builds an HTTP Request to the Coingecko API
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.coingecko.com/api/v3/coins/"
					+ crypto.toLowerCase()
					+ "?localization=false&tickers=false&market_data=false&community_data=true&developer_data=false&sparkline=false"))
					.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			//The response is always a JSON String --> Converts response String into JSON Object with org.json Class
			JSONObject obj = new JSONObject(response.body());
			//Fetch the Value out of the JSON String
			String value = obj.getJSONObject("description").getString("en");

			return value;
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

	public String getGenesisDate(String crypto) throws IOException, InterruptedException {
		try {
			// Creates an HTTPClient (Java 11+ required) and builds an HTTP Request to the Coingecko API
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.coingecko.com/api/v3/coins/"
					+ crypto.toLowerCase()
					+ "?localization=false&tickers=false&market_data=false&community_data=true&developer_data=false&sparkline=false"))
					.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			//The response is always a JSON String --> Converts response String into JSON Object with org.json Class
			JSONObject obj = new JSONObject(response.body());
			//Fetch the Value out of the JSON String
			String value = obj.getString("genesis_date");

			return value;
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
	
	public void getSupportedCoins() throws IOException, InterruptedException {
		try {
			// Creates an HTTPClient (Java 11+ required) and builds an HTTP Request to the Coingecko API
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.coingecko.com/api/v3/coins/markets?vs_currency=eur&order=market_cap_desc&per_page=100&page=1&sparkline=false"))
					.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			//Return value is JSON Array --> Convert Response String into JSON Array
			JSONArray coinArray = new JSONArray(response.body());
			//Go through the Array
			for (Object coinInArr : coinArray) {
				//Convert current Object into JSON Object
				JSONObject coin = (JSONObject) coinInArr;
				//Fetch information from current object
				System.out.println("ID: "+coin.get("id")+"  -  "+"Name: "+coin.get("name")+"  -  "+"Market Cap: "+coin.get("market_cap")+"  -  "+"Aktueller Preis: "+coin.get("current_price"));
			}
			

		} catch (org.json.JSONException e) {
			System.out.println("Upsala. Da stimmt was mit deiner Eingabe nicht! Überprüfe diese!");
			e.printStackTrace();
		} catch (Exception err) {
			System.out.println("Ups.. Da stimmt etwas nicht!");
			err.printStackTrace();
		}

	}


}
