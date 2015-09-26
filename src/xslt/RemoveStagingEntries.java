package xslt;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class RemoveStagingEntries {

	public static void main(String args[]){
		String Environment = "Live";

		if(!args[0].equalsIgnoreCase("${environment}"))
			Environment = args[0];
		
		if(Environment.equalsIgnoreCase("Staging")){
			System.out.println("Making Hosts file back to Original!!");

			File Destination = new File(System.getenv("SystemRoot") + "\\System32\\Drivers\\etc\\hosts");		
			File temp = new File("test-output\\temp");

			try {
				FileUtils.copyFile(temp,Destination) ;
			} catch (IOException e) {
				System.out.println("Failed to Make Hosts file back to Original!!");
				e.printStackTrace();
			}
		}

		if(Environment.equalsIgnoreCase("live")){
			System.out.println("Making Hosts file back to Original!!");

			File Destination = new File(System.getenv("SystemRoot") + "\\System32\\Drivers\\etc\\hosts");		
			File temp = new File("test-output\\temp");

			try {
				FileUtils.copyFile(temp,Destination) ;
			} catch (IOException e) {
				System.out.println("Failed to Make Hosts file back to Original!!");
				e.printStackTrace();
			}
		}
	}
}
