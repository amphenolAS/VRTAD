/**
* @author ruchika.behura
*
*/

package com.advrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.advrt.pages.Equipment_IOBoxPage;
import com.advrt.base.BaseClass;

public class EquipmentHubPage extends BaseClass {
	// IRTDHubPage IRTDHubPage;
	// EquipmentHubPage Element definition
	WebElement AddButton = null;
	WebElement VRTLogger = null;
	WebElement EquipmentHeaderTextBlock = null;
	WebElement Back_btn = null;
	WebElement IntiQual_btn=null;

	private void initElements() {
		AddButton = driver.findElementByAccessibilityId("AddEquipmentsButton");
		VRTLogger = driver.findElementByAccessibilityId("TitleTextBlock1");
		EquipmentHeaderTextBlock = driver.findElementByAccessibilityId("EquipmentHeaderTextBlock");
		Back_btn = driver.findElementByAccessibilityId("ArrowGlyph");
		IntiQual_btn = driver.findElementByAccessibilityId("SaveButton1");
	}

	EquipmentHubPage() throws IOException {
		super();
		initElements();

	}

	// Release memory
	public void resetWebElements() {
		AddButton = null;
		VRTLogger = null;
		EquipmentHeaderTextBlock = null;
		IntiQual_btn=null;
		Back_btn = null;

	}

	// Click AddButton
	public NewEquipmentCreation_Page ClickAddButton() throws InterruptedException, IOException {
		//WebElement AddButton = driver.findElementByAccessibilityId("AddEquipmentsButton");
		clickOn(AddButton);
		// Thread.sleep(1000);
		return new NewEquipmentCreation_Page();
	}

	// IRTD
	// Click on IRTD List box of Equipment page
	public Equipment_IRTDHubPage click_IRTDTile() throws IOException {
		WebElement irtdbox = driver.findElementByName("IRTD");
		clickOn(irtdbox);
		return new Equipment_IRTDHubPage();
	}

	// Is IRTD Tile visible

	public boolean Is_IRTDTileVisible() throws IOException {
		WebElement irtdbox = driver.findElementByName("IRTD");
		return IsElementVisibleStatus(irtdbox);
	}

	// Is VRT Tile visible

	public boolean Is_VRTLoggerVisible() throws IOException {

		return IsElementVisibleStatus(VRTLogger);
	}
	
	
	// Click AddButton
    public void ClickAddButton_alrt() throws InterruptedException, IOException {
    	 WebElement AddButton = driver.findElementByAccessibilityId("AddEquipmentsButton");
                    clickOn(AddButton);
                   
    }


	// Click on VRT List box of Equipment page
	public Equipment_VRTLoggerHubPage Click_VRTLogger_listbox() throws IOException {
		clickOn(VRTLogger);
		return new Equipment_VRTLoggerHubPage();
	}

	// Click AddButton to get Alert message when supervisor does not have default
	// privilege
	public void Alert_ClickAddBtn() throws InterruptedException {
		clickOn(AddButton);
		Thread.sleep(1000);
	}

	
	//click intiqual
		public void IntiQual_Btn() throws InterruptedException {
			clickOn(IntiQual_btn);
			UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "commited");
			Thread.sleep(1000);
		}
		
		public void IntiQual_Btn1() throws InterruptedException {
			clickOn(IntiQual_btn);
			Thread.sleep(1000);
		}

		public void IntiQual_Btn_Alert() throws InterruptedException {
			clickOn(IntiQual_btn);
			//UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "commited");
			//Thread.sleep(1000);
		}

	
	// Fetch the Save Alert message
		public String AlertMsg() {
			WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
			return FetchText(Msg);
		}
	// Click on back button
	public MainHubPage ClickBackBtn() throws IOException {
		clickOn(Back_btn);
		return new MainHubPage();
	}

	// click Initiate Verification Tab
	public void ClickInitiateVerification() throws IOException {
		WebElement InitiateVerificationTab = driver.findElementByAccessibilityId("SaveButton1");
		clickOn(InitiateVerificationTab);
	}

	// Is EquipmentHeaderTextBlock visible
	public boolean IsEquipmentHeader_Visible() {
		return IsElementVisibleStatus(EquipmentHeaderTextBlock);
	}
	
	//
	public void loggerpopup() throws IOException {
		List<WebElement> loggerpopup = driver.findElementByAccessibilityId("CalTypePopup").findElements(By.className("RadioButton"));
	}
	
	//click on loggerokbutton
	//
	public setup_verificationpage loggerokbutton() throws IOException {
		WebElement okbtn = driver.findElementByAccessibilityId("OKButton1");
		clickOn(okbtn);
		return new setup_verificationpage();
	}
	

	// is InitiateVerification button visible

	// Fetch EquipmentDueCalibration_Count and IRTD Equipment type text

	public String FetchTxt_DueCalibrationCount_IRTDtype(int j) throws InterruptedException, IOException {
		List<WebElement> Listcounts = driver.findElementByName("IRTD").findElements(By.className("TextBlock"));
		return Listcounts.get(j).getText();

	}

	// Fetch EquipmentDueCalibration_Count and IRTD Equipment type text

	public String FetchTxt_DueCalibrationCount_VRTLoggertype(int j) throws InterruptedException, IOException {
		List<WebElement> Listcounts = driver.findElementByName("VRTLogger").findElements(By.className("TextBlock"));
		// System.out.println(Listcounts.size());
		return Listcounts.get(j).getText();

	}
	
	
	//Equipement items
    public Equipment_IOBoxPage select_EqupmentItem(String name) throws IOException
     {
    	List<WebElement> eqpItem = driver.findElementsByClassName("GridViewItem");
    			
    	
    	for(WebElement item: eqpItem)
    	{
    		if(FetchText(item).equalsIgnoreCase(name))
    		{
    			clickOn(item);
    			break;
    		}
    	}
    	return new Equipment_IOBoxPage();
    }
    
  //check visiblity of Equipements
    public boolean is_ReqEpuipmentVisible(String name)
    {
    	List<WebElement> eqpItem = driver.findElementsByClassName("GridViewItem");
    			
    	boolean flag = false;
    	for(WebElement item: eqpItem)
    	{
    		if(FetchText(item).equalsIgnoreCase(name))
    		{
    			IsElementVisibleStatus(item);
    			flag = true;
    			break;
    		}
    }
    	return flag;
    	
    }
    
    
    public int EpuipmentCountOnEquipmentHubPage()
    {
    	List<WebElement> eqpItem = driver.findElementsByClassName("GridViewItem");
    			
    	int count = 0;
    	for(WebElement item: eqpItem)
    	{
    		if(IsElementVisibleStatus(item))
    		{
    			count++;
    		}
    }
    	return count;
    	
    }
    
    
    //CalTypePopup
    
    public void select_loggerradiobtn(int n) {
		List<WebElement> radios=driver.findElementByAccessibilityId("CalTypePopup").findElements(By.className("TextBlock"));
		 clickOn(radios.get(n));
	}
    
    public void clickon_Humidityradiobtn() {
    	WebElement Humiditybtn=driver.findElementByAccessibilityId("CalTypePopup").findElement(By.name("Humidity"));
    	clickOn(Humiditybtn);

	}
    
    
    public void clickon_HumidityTempbtn() {
    	WebElement tempbtn=driver.findElementByAccessibilityId("CalTypePopup").findElement(By.name("Only the Temperature Sensors (0-70 °C)"));
    	clickOn(tempbtn);

	}
    
    
    
}
