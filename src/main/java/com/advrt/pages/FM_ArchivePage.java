/**
 * @author manoj ghadei
 *
 */

package com.advrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.advrt.pages.FM_ArchiveSelectionPage;
import com.advrt.base.BaseClass;
import com.advrt.utility.TestUtilities;

public class FM_ArchivePage extends BaseClass{
	TestUtilities tu = new TestUtilities();
	//Page element variable declaration definition
	WebElement ArchiveHeaderBtn = null;
	WebElement ArchiveBrowseBtn = null;
	WebElement ArchiveByDtBtn = null;
	WebElement ArchiveTextBox = null;
	WebElement ArchiveOKbtn = null;
		
	//Page element Initialize method
	private void initElements()
	{
		ArchiveHeaderBtn = driver.findElementByAccessibilityId("Archive");
		ArchiveBrowseBtn = driver.findElementByAccessibilityId("ArchiveFolderBrowseButton");
		ArchiveTextBox = driver.findElementByAccessibilityId("ArchiveFolderTextBox");
		ArchiveByDtBtn = driver.findElementByAccessibilityId("PART_PickerButton");
		ArchiveOKbtn = driver.findElementByAccessibilityId("ArchiveFolderOKButton");
		

	}
	
	//Constructor for initializing the page elements
	FM_ArchivePage() throws IOException
	{
		super();
		initElements();
	}
	
	//Release memory
	public void resetWebElements()
	{
		ArchiveHeaderBtn = null;
		ArchiveBrowseBtn = null;
		ArchiveByDtBtn = null;		
		ArchiveTextBox = null;
		ArchiveOKbtn = null;
		
	}
	
	/*----------------------
	Methods of Archive Page
	------------------------*/
	
	// Verify if Archive browse text box visible or not	
	public boolean ArchiveTextBoxVisible() throws InterruptedException {
		return IsElementVisibleStatus(ArchiveTextBox);
	}
	
	//Click the folder browse button
	public void Click_BrowseBtn() throws InterruptedException {
		clickOn(ArchiveBrowseBtn);
		Thread.sleep(2000);
	}
	public boolean Is_ArchiveBrowseBtnVisible() {
        return IsElementVisibleStatus(ArchiveBrowseBtn);
    }
	 public boolean is_ArchivePopupDateVisible()
	 {
		 boolean status = false;
		try
		{
			 WebElement ArchivePopupDate = driver.findElementByAccessibilityId("AssestPopupDateHeaderLabels");
			 IsElementVisibleStatus(ArchivePopupDate);
			 status = true;
		}
		catch (Exception e) {
			e.getMessage();
		}
		
	    	return status;
	 }
	 public boolean is_ArchiveDateBtnVisible()
     {
     	return IsElementVisibleStatus(ArchiveByDtBtn);
     }
	 public boolean is_ArchiveOkBtnVisible()
     {
     	return IsElementVisibleStatus(ArchiveOKbtn);
     }
	 public void enterTxt_ArchiveTextBox(String val)
    	{
     	enterText(ArchiveTextBox, val);
     }  
	 public String  getText_ArchiveFilePath()
     {
                    return FetchText(ArchiveTextBox);
     } 
	 public void click_ArchiveDate()
     {
     	clickOn(ArchiveByDtBtn);
     }
	 public void click_Ok_DateSelectionPopUp() throws InterruptedException
 	{
 		WebElement okSymbol = driver.findElementByAccessibilityId("PART_SelectorOKButton");
 		Thread.sleep(1000);
 		clickOn(okSymbol);
 	}
	 public FM_ArchiveSelectionPage click_OkBtn() throws IOException, InterruptedException
     {
     	clickOn(ArchiveOKbtn);
     	Thread.sleep(2000);
     	return new FM_ArchiveSelectionPage();
     }
	 public boolean is_MsgPopUpDisplayed()
	 {
		 boolean status = false;
		 try
		 {
			WebElement popupMsg = driver.findElementByAccessibilityId("Popup Window");
			IsElementVisibleStatus(popupMsg);
			status = true;
		 }
		 catch (Exception e) {
			e.getMessage();
		}
		 return status;
		
		 
	 }
	 public boolean is_DatePopupVisible()
	 {
		 WebElement datePopup = driver.findElementByAccessibilityId("PART_Popup");
		 return IsElementVisibleStatus(datePopup);
	 }
	 public boolean is_LocDatePopUpDisplayed()
	 {
		 WebElement popup = driver.findElementByAccessibilityId("AssestPopupDateHeaderLabels");
		 return IsElementVisibleStatus(popup);
	 }
	 public String getTxt_ArchiveDate()
     {
     	return FetchText(ArchiveByDtBtn);
     } 
  
		public boolean is_ExplorerWinInvoked()
       	{
       		//Switch to file explorer window 
       		driver.switchTo().activeElement();
       		
       		WebElement win = driver.findElementByXPath("//*[normalize-space(.='Select Folder')]");
       		return IsElementVisibleStatus(win);
       	} 
	// Enter sync in folder path
	public void enter_Filepath(String pathname) throws AWTException, IOException, InterruptedException{
		Click_BrowseBtn();
		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(2000);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		String fp2 = System.getProperty("user.dir") +  "\\src\\test\\resources\\TestData\\" + pathname;
		//System.out.println(fp2);
		alert.sendKeys(fp2);
		Thread.sleep(500);
		//Robot r = new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		// switch back
		driver.switchTo().activeElement();
		Thread.sleep(500);
	}
	 public boolean is_ArchiveHeaderBtnVisible() 
     {
     	return IsElementVisibleStatus(ArchiveHeaderBtn);
     }


}
