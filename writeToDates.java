package MorningUpdate;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;  
//////RUN THIS FIRST
public class writeToDates {
	//file and its location should never change ngl WHY WOULD IT CHANGE 
	static File dateFileHolder = new File("Dates.txt"); static String fileLocation = dateFileHolder.getAbsolutePath();	
	//this will be used to run everything!
	static Desktop desktop = Desktop.getDesktop(); //for basic file interactions
	public static void main(String[] args) {
		//First it will ask the users location
		weatherApiManager.getLocation();
		//starts the infinite loop
		runInfi();
	}
	//KEEP IT SEPERATED BECAUSE IT NEEDS TO BE ALONE DO NOT ALLOW IT TO MEET AND MINGLE 
	//this will run the forever while loop to keep this active don't PUT ANYTHING IN THIS EVERYTHING THAT NEEDS TO BE WRITTEN SHOULD BE WRITTEN IN writeToFile
	static void runInfi(){
		int runCount = 1;
		//This loop runs forever and will write the date and time in a file once a minute 
		while(true) {
			try{
				writeToFile();
				openFile();
				System.out.println("The Run Count is currently: "+runCount++);
				Thread.sleep((1000*60*60/*oneHour*/)*24);
				
			}catch(InterruptedException e){
					e.printStackTrace();
			}
		}		
	}
	//This writes to the dates txt file, add all changes here 
	//this method will be used to write FOR EVERYTHING
	static void writeToFile() {
		Date currentDate = new Date();		
		try {
			//if we JUST made the file it goes "YOU MADE THE.. " else it gives us the file locations 
			if(dateFileHolder.createNewFile()) {
				System.out.print("You have made the file" + dateFileHolder.getName());
			}else {
				System.out.println("The file "+ dateFileHolder.getName()+" exist in: "+ fileLocation);
			}																														           /*append*/
			//after we check the files existence we will now WRITE TO THE FILE by using FILEWIRTE with append set to true FileWriter(FileinHolder,True);
			FileWriter dataWriter = new FileWriter(dateFileHolder,true);
			//dataWriter.write("-----------------------------\nWelcome to your Morning Report: \n\n");
			//dataWriter.append("The current date is "+ currentDate+"\n");
			//call api info 
			//dataWriter.append("Weather Stats:\n");
			//dataWriter.append("\n\tThe Temperature is: "+weatherApiManager.TemperatureFer+"°F in " +weatherApiManager.Location+"\n\n");
			dataWriter.write(""" 
			-----------------------------
			Welcome to your Morning Report: 
				Date & Time:
					The current date is:"""+" "+currentDate+"""
								
				\tWeather Stats:
					\tThe Temperature is:"""+" "+weatherApiManager.TemperatureFer+"°F in " +weatherApiManager.Location+"""
					
					
			-----------------------------

					""");
			dataWriter.close();		
		} catch (IOException e) {
			System.out.println("you gotta error");
			e.printStackTrace();
		}
	}
	//open and close file 
	static void openFile() {
		try {
			desktop.open(dateFileHolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
