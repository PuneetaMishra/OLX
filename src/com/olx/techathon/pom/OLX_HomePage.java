package com.olx.techathon.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.naukri.GenericFunctionsPack.GenericFunctions;

public class OLX_HomePage {
	public WebDriver driver;
	GenericFunctions generic;
	public OLX_HomePage(WebDriver driver ,GenericFunctions generic)
	{
		this.driver=driver;
		this.generic = generic;
	}
	public static final String SubmitFreeADD_Lnk = "//a[@id='postNewAdLink']";
	//	Posting page
	public static final String Ad_title_Txt =".//*[@id='add-title']";
	public static final String Category_DD = "(//dl[@class='dropdown fleft']/dt/a)[1]";
	public static final String Description_Txt = "add-description";
	public static final String UploadPhoto = "(//a[@class='block'])[1]";
	public static final String Name_Txt = "//input[@id='add-person']";
	public static final String Email_Txt = "//input[@id='add-email']";
	public static final String Phone_Txt = "//input[@id='add-phone']";
	public static final String City_txt = "//input[@id='mapAddress']";
	public static final String Submit_btn = "//input[@id='save']";
	public static final String ErrorMessages_WE = "//label[@class='error']";

	// Category Section
	public static final String Mobile_btn = "(//span[@class='block']/strong)[1]";
	public static final String Electronics_btn = "(//span[@class='block']/strong)[1]";
	public static final String Vehicles_btn = "(//span[@class='block']/strong)[1]";
	public static final String HomeFurniture_btn = "(//span[@class='block']/strong)[1]";
	public static final String Animals_btn = "(//span[@class='block']/strong)[1]";
	public static final String BooksSportsHobbies_btn = "(//span[@class='block']/strong)[1]";
	public static final String FashionBeauty_btn = "(//span[@class='block']/strong)[1]";
	public static final String KidsBaby_btn = "(//span[@class='block']/strong)[1]";
	public static final String Services_btn = "(//span[@class='block']/strong)[1]";
	public static final String Jobs_btn = "(//span[@class='block']/strong)[1]";
	public static final String RealEstates_btn = "(//span[@class='block']/strong)[1]";





			public String CaptureErrors(){
				List<WebElement> WE = driver.findElements(By.xpath(ErrorMessages_WE));
				List<String> ErrorMessages = new ArrayList<String>();
				for (WebElement webElement : WE) {
					if(webElement.isDisplayed()){
						ErrorMessages.add(webElement.getText());
					}
				}

				String Errors = ErrorMessages.toString();
				Errors = Errors.replace("[", "");
				Errors = Errors.replace("]", "");

				System.out.println(Errors);
				return Errors;
			}

			public void Fillform_PostAd(String Name,String Email,String Mobile,String Query){
				generic.Fill_Txt(Ad_title_Txt, Name);
				generic.Fill_Txt(Category_DD, Email);
				generic.Fill_Txt(Description_Txt, Mobile);
				generic.Fill_Txt(Name_Txt, Name);
				generic.Fill_Txt(Email_Txt, Email);
				generic.Fill_Txt(Phone_Txt, Mobile);
				generic.Fill_Txt(City_txt, Query);
				generic.Click_Btn(Submit_btn);
			}




}
