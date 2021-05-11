package main;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.json.JSONArray;

public class OHLC {
	
	
	public String timestamp(String timeInMS) {
		BigInteger bigTime = new BigInteger(timeInMS);
		Calendar cal = Calendar.getInstance();
		
		cal.setTimeInMillis(bigTime.longValue());
		
		
		
		// Convert Calender into the parts of the date
		int mYear = cal.get(Calendar.YEAR);
		int mMonth = cal.get(Calendar.MONTH)+1;
		int mDay = cal.get(Calendar.DAY_OF_MONTH);
		int mHour = cal.get(Calendar.HOUR_OF_DAY);
		int mMinute = cal.get(Calendar.MINUTE);
		
		
		
		return mHour+" "+mMinute+" - "+mDay+" "+mMonth+" "+mYear;
		
	}
	
	public void makeChart(OHLCDataset dataset, String cur) {
		JFreeChart chart = ChartFactory.createHighLowChart("Crypto", "date", "price", dataset, true);
		try {
			LocalTime now = LocalTime.now();
			File chartFile = (new File("chart_"+cur+"_"+now.getHour()+"_"+now.getMinute()+".png"));
			System.out.println("Dateipfad: "+chartFile.getAbsolutePath());
			ChartUtils.saveChartAsPNG(chartFile, chart, 500, 500);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void getOHLCData(String cur, String fiat, String days) {
		try {
			// Creates an HTTPClient (Java 11+ required) and builds an HTTP Request to the Coingecko API
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.coingecko.com/api/v3/coins/"+cur+"/ohlc?vs_currency="+fiat+"&days="+days))
					.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			//The response is always a JSON String --> Converts response String into JSON Object with org.json Class
			JSONArray obj = new JSONArray(response.body());
			//Go through the Array
			List <JSONArray> jsonArrayArray = new ArrayList<JSONArray>();
			for (int i = 0; i < obj.length(); i++) {
				
				jsonArrayArray.add(obj.getJSONArray(i));
			}
			
			DateFormat format = new SimpleDateFormat("HH mm - dd MM yyyy");
			
			OHLCDataItem[] dataItem = new OHLCDataItem[jsonArrayArray.size()];
			
			
			
			try {
				for (int j = 0; j < jsonArrayArray.size(); j++) {
					Date d = format.parse(timestamp(jsonArrayArray.get(j).get(0).toString()));
					OHLCDataItem tempItem = new OHLCDataItem(d, 
							jsonArrayArray.get(j).getDouble(1), jsonArrayArray.get(j).getDouble(2),jsonArrayArray.get(j).getDouble(3), jsonArrayArray.get(j).getDouble(4), 0);
					
					dataItem[j] = tempItem;
					
				}
			} catch (Exception err) {
				System.out.println(err.toString());
				
			}
			
			
		    OHLCDataset dataset = new DefaultOHLCDataset(cur+"price", dataItem);
			
		    
		    makeChart(dataset, cur);

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
