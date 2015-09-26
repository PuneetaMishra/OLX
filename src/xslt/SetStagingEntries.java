package xslt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;

public class SetStagingEntries {

	public static void main(String args[]){
		String Environment = "Live";
		
		if(!args[0].equalsIgnoreCase("${environment}"))
			Environment = args[0];
			
		if(Environment.equalsIgnoreCase("Staging")){
			System.out.println("Doing Staging Entries as Environment Provided is Staging!!");

			File Source = new File("StagingEntries");
			File Destination = new File(System.getenv("SystemRoot") + "\\System32\\Drivers\\etc\\hosts");		
			File temp = new File("test-output\\temp");

			try {
				FileUtils.copyFile(Destination,temp) ;
				FileUtils.copyFile(Source,Destination) ;
			} catch (IOException e) {
				System.out.println("Failed to Do Staging Entries!!");
				e.printStackTrace();
			}
		}

		if(Environment.equalsIgnoreCase("Live")){
			System.out.println("Removing all Staging Entries (Make the Hosts file Blank) as Environment Provided is Live!!");

			File Destination = new File(System.getenv("SystemRoot") + "\\System32\\Drivers\\etc\\hosts");		
			File temp = new File("test-output\\temp");

			try{
				FileUtils.copyFile(Destination,temp) ;
				new PrintWriter(Destination).close();
			}
			catch (Exception e){
				System.out.println("Failed to Make the Hosts file Blank!!");
				e.printStackTrace();
			}
		}
	}
}
