package com.olx.techathon.suite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.naukri.datatable.Xls_Reader;
import com.olx.techathon.Config.ConfigDetails;
import com.olx.techathon.Generic.MobileGeneric;
import com.olx.techathon.pom.OLX_HomePage;

public class TestCases_Posting extends ConfigDetails {

	@BeforeMethod
	public void Before()
	{
		generic = new MobileGeneric(driver);
		driver = generic.StartDriver(Driver_Type,MobileEmulatorType);
		driver.manage().window().maximize();
	}
	Xls_Reader datatable;
	@Test
	public void AddPosting_Mobile(){
		datatable = new Xls_Reader(TestDataSheetPath_Olx_Posting);
		driver.get("http://techathon:8hP8AHsyU2TJ@qa04.olx.in/");
		generic.Click(OLX_HomePage.SubmitFreeADD_Lnk);
		
		
		


	}

	@AfterMethod()
	public void CloseDriver()
	{
		driver.quit();
	}	
}
