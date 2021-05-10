package main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;

public class Trending {
	
	
	public void getTrendingCoin() {
		try {
			// Creates an HTTPClient (Java 11+ required) and builds an HTTP Request to the Coingecko API
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.coingecko.com/api/v3/search/trending"))
					.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			//The response is always a JSON String --> Converts response String into JSON Object with org.json Class
			JSONArray coinArray = new JSONArray(response.body());
			//Go through the Array
			for (Object coinInArr : coinArray) {
				//Convert current Object into JSON Object
				JSONObject coin = (JSONObject) coinInArr;
				//Fetch information from current object
				System.out.println("ID: "+coin.get("id")+"  -  "+"Name: "+coin.get("name")+"  -  "+coin.get("symbol"));
			}

			//Catch JSON Error for Example: wrong currency given or not supported currency
		} catch (org.json.JSONException e) {
			System.out.println("Upsala. Da stimmt was mit deiner Eingabe nicht! Überprüfe diese!");
			e.printStackTrace();
		} catch (Exception err) {
			System.out.println("Ups.. Da stimmt etwas nicht!");
			err.printStackTrace();
		}

	}
}
