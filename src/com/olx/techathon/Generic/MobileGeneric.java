package com.olx.techathon.Generic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.naukri.GenericFunctionsPack.GenericFunctions;

public class MobileGeneric extends GenericFunctions{

	public MobileGeneric(WebDriver driver) {
		super(driver);
	}

	public WebDriver StartDriver(String browserType,String DeviceType){
		String Common_Resources_Directory = System.getenv("Common_Resources");

		if(browserType.trim().equalsIgnoreCase("")){
			System.out.println("Kindly set the 'browserType' variable before calling this function");
			return driver;
		}

		if(browserType.equalsIgnoreCase("FF")){
			driver = new FirefoxDriver();
		}

		else if(browserType.startsWith("FF")){
			String BrowserVersion =browserType.split("FF")[1].trim();
			FirefoxProfile profile = new FirefoxProfile();
			driver = new FirefoxDriver(new FirefoxBinary(new File(Common_Resources_Directory + "\\MozillaVersions\\Mozilla Firefox"+BrowserVersion+"\\firefox.exe")), profile);
		}

		if(browserType.equalsIgnoreCase("Android")){
			FirefoxProfile profile = new FirefoxProfile(); 
			try {
				String directory = System.getProperty("user.dir");
				directory = directory.replace("\\", "\\\\");
				profile.addExtension(new File(directory+"\\user_agent_switcher-0.7.3-fx+sm.xpi"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			profile.setPreference("general.useragent.override", "Mozilla/5.0 (Linux; Android 4.4; Nexus 4 Build/KRT16E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.105 Mobile Safari"); 
			driver = new FirefoxDriver(new FirefoxBinary(new File(Common_Resources_Directory + "\\MozillaVersions\\Mozilla Firefox18\\firefox.exe")), profile);
		}

		if(browserType.equalsIgnoreCase("Iphone")){
			FirefoxProfile profile = new FirefoxProfile(); 
			try {
				String directory = System.getProperty("user.dir");
				directory = directory.replace("\\", "\\\\");
				profile.addExtension(new File(directory+"\\user_agent_switcher-0.7.3-fx+sm.xpi"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			profile.setPreference("general.useragent.override", "Mozilla/5.0 (iPhone; CPU iPhone OS 8_0_2 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12A405 Safari/600.1.4"); 
			driver = new FirefoxDriver(new FirefoxBinary(new File(Common_Resources_Directory + "\\MozillaVersions\\Mozilla Firefox18\\firefox.exe")), profile);
		}

		if(browserType.equalsIgnoreCase("Windows")){
			FirefoxProfile profile = new FirefoxProfile(); 
			try {
				String directory = System.getProperty("user.dir");
				directory = directory.replace("\\", "\\\\");
				profile.addExtension(new File(directory+"\\user_agent_switcher-0.7.3-fx+sm.xpi"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			profile.setPreference("general.useragent.override", "Mozilla/5.0 (Windows Phone 8.1; ARM; Trident/7.0; Touch IEMobile/11.0; NOKIA; 909) like Gecko"); 
			driver = new FirefoxDriver(new FirefoxBinary(new File(Common_Resources_Directory + "\\MozillaVersions\\Mozilla Firefox18\\firefox.exe")), profile);
		}


		if(browserType.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", Common_Resources_Directory + "\\jars\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if(browserType.startsWith("Chrome")){
			String BrowserVersion =browserType.split("Chrome")[1].trim();
			System.setProperty("webdriver.chrome.driver", Common_Resources_Directory + "\\jars\\chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.binary", Common_Resources_Directory + "\\jars\\chromedriver.exe\\Chrome"+BrowserVersion+"\\chrome.exe");
			driver = new ChromeDriver(capabilities);
		}

		if(browserType.equalsIgnoreCase("IE32")){
			System.setProperty("webdriver.ie.driver", Common_Resources_Directory + "\\jars\\IEDriverServer32.exe");
			driver = new InternetExplorerDriver();
		}

		if(browserType.equalsIgnoreCase("IE64")){
			System.setProperty("webdriver.ie.driver", Common_Resources_Directory + "\\jars\\IEDriverServer64.exe");
			driver = new InternetExplorerDriver();
		}

		if(browserType.equalsIgnoreCase("MobileChrome")){
			System.setProperty("webdriver.chrome.driver", Common_Resources_Directory + "\\jars\\chromedriver.exe"); //path of chromedriver
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", DeviceType); // select the device to emulate

			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capabilities);
		}


		SetImplicitWaitInSeconds(15);

		return driver;

	}

	/************************************************************** 
	 * Function Description: Enters a provided text into a text box
	 * Function parameters: String xpath, String data
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String Fill_Txt(By by, String data){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(data);
		return "Element entered in text box successfully";
	}
	/************************************************************** 
	 * Function Description: Clicks in the text box field
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String Click_Txt(By by){
		driver.findElement(by).click();
		return "Clicked on Text field";
	}
	/************************************************************** 
	 * Function Description: Fetches the text from the Text box field
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String GetText_Txt(By by){
		return driver.findElement(by).getAttribute("value");
	}

	/************************************************************** 
	 * Function Description: Fetches the Grey Text from the Text box field
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String GetPlaceholder_Txt(By by){
		return driver.findElement(by).getAttribute("placeholder");
	}

	/************************************************************** 
	 * Function Description: Clears the Text box field
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String Clear_Txt(By by){
		driver.findElement(by).clear();
		return "Text field cleared Successfully";
	}

	/************************************************************** 
	 * Function Description: Selects the value from Drop down
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/	
	public String Select_DD(By by, String Value){
		Select s1= new Select(driver.findElement(by));
		s1.selectByVisibleText(Value);
		return Value+ "is selected in drop down";
	}
	/************************************************************** 
	 * Function Description: DeSelects the value from Drop down
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/	
	public String DeSelect_DD(By by){
		Select s1= new Select(driver.findElement(by));
		s1.deselectAll();
		return "Values deselected";
	}
	/************************************************************** 
	 * Function Description:Fetch the selected value from Drop down
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String GetSelectedValue_DD(By by){
		Select s1= new Select(driver.findElement(by));
		s1.getFirstSelectedOption();
		return "Element is displayed";
	}

	/************************************************************** 
	 * Function Description:Selects the Checkbox
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String Check_Chk(By by){
		if(!driver.findElement(by).isSelected())
			driver.findElement(by).click();
		return "Checkbox selected";
	}
	/************************************************************** 
	 * Function Description:DeSelects the Checkbox
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String UnCheck_Chk(By by){
		if(driver.findElement(by).isSelected())
			driver.findElement(by).click();
		return "Checkbox deslected";
	}
	/************************************************************** 
	 * Function Description:It Checks whether the Checkbox is selected or not
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String IsChecked_Chk(By by){
		if(driver.findElement(by).isSelected());
		return "Checkbox is Selected";
	}
	/************************************************************** 
	 * Function Description:To select a Radio button
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String Select_Rd(By by){
		if(!driver.findElement(by).isSelected())
			driver.findElement(by).click();
		return "Radio button selected";	
	}
	/************************************************************** 
	 * Function Description:It returns the selected Radio button value
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	//	public String GetSelectedValue_Rd(String xpath, String Value){
	//		if(driver.findElement(By.xpath(xpath)).isSelected())
	//					return driver.findElement(By.xpath(Value)).getText()+  
	//	}
	/************************************************************** 
	 * Function Description:To click a Link
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String Click_Lnk(By by){
		driver.findElement(by).click();
		return "Link is clicked";
	}
	/************************************************************** 
	 * Function Description:To get text of a Link
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String GetLinkText_Lnk(By by){
		return driver.findElement(by).getText();
	}
	/************************************************************** 
	 * Function Description:To click a Button
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String Click_Btn(By by){
		driver.findElement(by).click();
		return "Button is clicked";
	}
	/************************************************************** 
	 * Function Description:To get text of a Button
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String GetElementText_Btn(By by){
		return driver.findElement(by).getText();

	}
	/************************************************************** 
	 * Function Description:To Click a Web Element
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String Click_WE(String xpath){
		driver.findElement(By.xpath(xpath)).click();
		return "Web Element Clicked";
	}
	/************************************************************** 
	 * Function Description:To get text of a Web Element
	 * Function parameters: String xpath
	 * Function Return Type: String
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public String GetElementText_WE(String xpath){
		return driver.findElement(By.xpath(xpath)).getText();
	}

	/***********************************************************************************************
	 * Function Description : Selects the provided value in a customized drop down
	 * Function Parameters : String xpath, String UlidXpath, String selectText
	 * Function Return Type: None
	 * Author: Mustufa Gunderwala, Date: 14-Nov-2014
	 ***********************************************************************************************/
	//    void DropDownCustom_Select(String xpath, String UlidXpath, String selectText){
	//          List<WebElement> DropDownValueContainer;
	//          driver.findElement(By.xpath(xpath)).click();
	//          DropDownValueContainer=driver.findElements(By.xpath(UlidXpath));
	//          for(WebElement option : DropDownValueContainer){
	//                if(option.findElement(By.tagName("a")).getText().equals(selectText)){
	//                      option.findElement(By.tagName("a")).click();
	//                      break;
	//                }
	//          }
	//    }
	public void Select_Experience_DD(String xpath, String UlidXpath, String selectText)
	{
		if (driver.findElements(By.xpath(xpath)).size()>0) 
		{

			List<WebElement> DropDownValueContainer;
			driver.findElement(By.xpath(xpath)).click();	
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DropDownValueContainer=driver.findElements(By.xpath(UlidXpath));
			for(WebElement option : DropDownValueContainer){

				if(option.getText().equals(selectText)){
					System.out.println(option.getText());
					option.click();					
					break;}
			}}


	}

	/************************************************************** 
	 * Function Description:To mouse hover on a WebElement
	 * Function parameters: String xpath 
	 * Function Return Type: void
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public void MouseHover(String xpath)
	{
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(xpath));
		action.moveToElement(we).build().perform();	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/************************************************************** 
	 * Function Description:To scroll the page
	 * Function parameters: String xpath   
	 * Function Return Type: void
	 * Author: Puneeta Mishra, Date: 03-March-2015
	 ***************************************************************/
	public void Scroll_The_Page(String xpath)
	{
		Point loc = driver.findElement(By.xpath(xpath)).getLocation();
		//		System.out.println(loc);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("javascript:window.scrollBy(0,"+loc.y+")");	
	}

	//	/************************************************************** 
	//	 * Function Description:To Check element is visible 
	//	 * Function parameters: String xpath 
	//	 * Function Return Type: void
	//	 * Author: Puneeta Mishra, Date: 03-March-2015
	//	 ***************************************************************/
	//	public boolean isVisible(By by)
	//	{
	//		driver.findElement(by.)
	//	}
	
	/************************************************************** 
		 * Function Description:To Check attribute of the element 
		 * Function parameters: String xpath , String Attribute Name
		 * Function Return Type: void
		 * Author: Puneeta Mishra, Date: 30-July-2015
		 ***************************************************************/
	
	public String GetAttribute(String xpath, String Attribute)
	{
		return driver.findElement(By.xpath(xpath)).getAttribute(Attribute);
	}
	/************************************************************** 
	 * Function Description:To Check Tag of the element 
	 * Function parameters: String xpath ,String Tag name
	 * Function Return Type: void
	 * Author: Puneeta Mishra, Date: 30-July-2015
	 ***************************************************************/
	public String GetTagName(String xpath)
		{
			return driver.findElement(By.xpath(xpath)).getTagName();
		}
}
