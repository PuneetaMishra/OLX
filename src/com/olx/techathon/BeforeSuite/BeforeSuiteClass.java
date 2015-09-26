package com.olx.techathon.BeforeSuite;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

import com.olx.techathon.Config.ConfigDetails;


public class BeforeSuiteClass extends ConfigDetails{

	@BeforeSuite
	public void Initialize_ConfigVariables(){
		try {
			FileInputStream input = new FileInputStream("Config.properties");
			Properties	prop = new Properties();
			prop.load(input);

			TestorLive = prop.getProperty("TestorLive");
			System.out.println(TestorLive);

			Driver_Type = prop.getProperty("Driver_Type");				
			System.out.println(Driver_Type);

			Execution_Type = prop.getProperty("Execution_Type");				
			System.out.println(Execution_Type);
			
			/*Email = prop.getProperty("Email");				
			System.out.println(Email);

			Password = prop.getProperty("Password");				
			System.out.println(Password);*/

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
