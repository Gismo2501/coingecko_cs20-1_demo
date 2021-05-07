package main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;

public class GetCurrency {

	
	
	public double getCurrency(String crypto, String fiat) throws IOException, InterruptedException {
		try {
			// Creates an HTTPClient (Java 11+ required) and builds an HTTP Request to the Coingecko API
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.coingecko.com/api/v3/simple/price?ids=" + crypto.toLowerCase()
							+ "&vs_currencies=" + fiat.toLowerCase()))
					.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			//The response is always a JSON String --> Converts response String into JSON Object with org.json Class
			JSONObject obj = new JSONObject(response.body());
			//Fetch the Value out of the JSON String
			double value = obj.getJSONObject(crypto.toLowerCase()).getDouble(fiat.toLowerCase());

			return value;
			//Catch JSON Error for Example: wrong currency given or not supported currency
		} catch (org.json.JSONException e) {
			System.out.println("Upsala. Da stimmt was mit deiner Eingabe nicht! Überprüfe diese!");
			e.printStackTrace();
			return 0.0;
		} catch (Exception err) {
			System.out.println("Ups.. Da stimmt etwas nicht!");
			err.printStackTrace();
			return 0.0;
		}

	}

	public double getCurrencyOnGivenDate(String crypto, String date, String fiat)
			throws IOException, InterruptedException {
		try {
			// Creates an HTTPClient (Java 11+ required) and builds an HTTP Request to the Coingecko API
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.coingecko.com/api/v3/coins/"
					+ crypto.toLowerCase() + "/history?date=" + date + "&localization=false")).build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			//The response is always a JSON String --> Converts response String into JSON Object with org.json Class
			JSONObject obj = new JSONObject(response.body());
			//Fetch the Value out of the JSON String
			double value = obj.getJSONObject("market_data").getJSONObject("current_price")
					.getDouble(fiat.toLowerCase());

			return value;
			//Catch JSON Error for Example: wrong currency given or not supported currency
		} catch (org.json.JSONException e) {
			System.out.println("Upsala. Da stimmt was mit deiner Eingabe nicht! Überprüfe diese!");
			e.printStackTrace();
			return 0.0;
		} catch (Exception err) {
			System.out.println("Ups.. Da stimmt etwas nicht!");
			err.printStackTrace();
			return 0.0;
		}

	}
}
