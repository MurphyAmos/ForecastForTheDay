package MorningUpdate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Scanner;



public class weatherApiManager {
	//location data 
	public static String Location =null;
	static String LatNLong = "";
	static String gridPointX = "";
	static String gridPointY = "";
	
	//this is the temperature variab
	static String TemperatureFer = "";
	//this will be called in the main part of Write to dates, this is mostly done because all it will do it gather the location data
	
	public static String getLocation() {
		new String(LatNLong);
		Scanner LocationInput = new Scanner(System.in);	
		//we are gonna ask the location if youd like to add your own make sure to add it to one of the CASEs in the switch statement 
		System.out.print("What is your Location?: \n"
				+ "Houston (1)\n"
				+ "Beaumont(2)\nChoice:");
		int loca= LocationInput.nextInt();
		LocationInput.close();
		//after we ask the location we will then get the location data 
		//soon this will not be hard coded but eh 
		
		switch(loca) {
			case 1:
				Location = "Houston";
				LatNLong = "29.749907,-95.358421";
				break;
			case 2:
				Location = "Beaumont";
				LatNLong = "30.081987,-94.118637";
				break;
			default:
				System.out.print("THAT IS NOT VALID");;
		}
		//these will start the other parts of calling the api 
		try {
			gridFinder();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//try double catch for weather pull 
		//it just seems logical to place the start for weatherPull at the end of your info grabber so it runs and everything stays in the script 
		try {
			weatherPull();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return Location;
	}
	//this will pull data from the weather api and returns the temperature
	private static String weatherPull()  throws UnsupportedEncodingException, IOException{
		new String(TemperatureFer);
		//this is hard-coded for the houston office will auto call points 
		URL forecastApiUrl = new URL("https://api.weather.gov/gridpoints/HGX/"+gridPointX+gridPointY.replace(",", "")+"/forecast/hourly");
		//we will then try tp open the api link 		
		try (BufferedReader weatherReader = new BufferedReader(new InputStreamReader(forecastApiUrl.openStream()))) {
			//we will then read and write each line of the data to the console
			for (String line; (line = weatherReader.readLine()) != null;) {
				//soo we will go to the very first temperature which is the one we want and its in f
				if(line.contains("temperature")) {
		        	TemperatureFer = line.strip().replaceAll(",", "").replace("\"temperature\":","").strip();
		        	break;					
		        }
		    }
		}
		return TemperatureFer;
	}
	//this does the same thing as the prev method! but it finds the grid points and puts them into the forcastApiUrl
	static void gridFinder()throws UnsupportedEncodingException, IOException {
		new String(gridPointX);
		//the URL for grid points with  hardcoded LAT n LONG
		URL	gridPointFinder= new URL("https://api.weather.gov/points/"+LatNLong);
		
		try (BufferedReader pointFinderReader = new BufferedReader(new InputStreamReader(gridPointFinder.openStream()))) {
			for (String line; (line = pointFinderReader.readLine()) != null;) {
				if(line.contains("gridX")) {
					gridPointX = line.replaceAll("\"gridX\":", "").strip();
				}else if(line.contains("gridY")) {
					gridPointY = line.replaceAll("\"gridY\":", "").strip();
				}
			}
		}		
	}
}
