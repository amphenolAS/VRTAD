/**
 * @author Vijay
 *
 */

package com.advrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.advrt.base.BaseClass;
import com.advrt.utility.TestUtilities;

public class FM_VRTConvertPage extends BaseClass{
	TestUtilities tu = new TestUtilities();
	//Page element variable declaration definition
	WebElement AVSConvertHeaderBtn = null;
	WebElement AVSConvertBrowseBtn = null;
	WebElement AVSConvertOKbtn = null;
	WebElement backBtn = null;
	WebElement filePathTxtField = null;
	WebElement commentField = null;
	WebElement okBtn = null;
		
	//Page element Initialize method
	private void initElements()
	{
		AVSConvertHeaderBtn = driver.findElementByAccessibilityId("VRTConvert");
		AVSConvertBrowseBtn = driver.findElementByAccessibilityId("ConvertRawFileBrowseButton");
		AVSConvertOKbtn = driver.findElementByAccessibilityId("ConvertRawFileOKButton");
		backBtn = driver.findElementByAccessibilityId("BackButton");
		filePathTxtField = driver.findElementByAccessibilityId("ConvertRawFileTextBox");
		commentField = driver.findElementByAccessibilityId("ConvertRawFileCommentsTextBox");
		okBtn = driver.findElementByAccessibilityId("ConvertRawFileOKButton");

	}
	
	//Constructor for initializing the page elements
	FM_VRTConvertPage() throws IOException
	{
		super();
		initElements();
	}
	
	//Release memory
	public void resetWebElements()
	{
		AVSConvertHeaderBtn = null;
		AVSConvertBrowseBtn = null;
		AVSConvertOKbtn = null;
		backBtn = null;
		filePathTxtField = null;
		commentField = null;
		okBtn = null;
	}
	
	/*----------------------
	Methods of VRTConvert Page
	------------------------*/
	//AVS Convert Header Button is visible or not
	public boolean is_AVSConvertHeaderVisible()
	{
		return IsElementVisibleStatus(AVSConvertHeaderBtn);
	}
	public boolean is_AVSConvertHeaderEnabled()
	{
		return IsElementEnabledStatus(AVSConvertHeaderBtn);
	}
	public boolean is_BrowseBtnVisible()
	{
		return IsElementVisibleStatus(AVSConvertBrowseBtn);
	}
	public boolean is_FilePthTxtFieldVisible()
	{
		return IsElementVisibleStatus(filePathTxtField);
	}
	public boolean is_CmntFieldVisible()
	{
		return IsElementVisibleStatus(commentField);
	}
	public void enter_TextIntoCommentField(String val)
	{
		clickOn(commentField);
		ClearText(commentField);
		enterText(commentField, val);
	}
	public String get_TextFromCmntField()
	{
		return FetchText(commentField);
	}
	public boolean is_OkBtnVIsible()
	{
		return IsElementVisibleStatus(okBtn);
	}
	 public void enterTxt_ConvertTextBox(String val)
     {
     	enterText(filePathTxtField, val);
     }   
	 public String  getText_ConvertFilePath()
     {
                    return FetchText(filePathTxtField);
     }
	
	//Click the folder browse button
	public void Click_BrowseBtn() throws InterruptedException {
	//	Actions act = new Actions(driver);
		
		try
		{
		Thread.sleep(2000);
	//	act.moveToElement(AVSConvertBrowseBtn).click(AVSConvertBrowseBtn).build().perform();
		clickOn(AVSConvertBrowseBtn);
		}
		catch (Exception e) {
			Thread.sleep(2000);
			AVSConvertBrowseBtn = driver.findElementByAccessibilityId("ConvertRawFileBrowseButton");
	//		act.moveToElement(AVSConvertBrowseBtn).click(AVSConvertBrowseBtn).build().perform();
			clickOn(AVSConvertBrowseBtn);
		}
		
	}
	public boolean is_ExplorerWinInvoked()
   	{
   		//Switch to file explorer window 
   		driver.switchTo().activeElement();
   		
   		WebElement win = driver.findElementByXPath("//*[normalize-space(.='Select Folder')]");
   		return IsElementVisibleStatus(win);
   	} 
	//click on ok button on AVS COnvrter Page
	public void click_OkOnVRTConverter() throws InterruptedException
	{
		Thread.sleep(2000);
		clickOn(AVSConvertOKbtn);
	}
	
	// Fetch the alert message data in the bottom app bar
		public String get_AlertMsg_text() throws InterruptedException {
			WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
			return FetchText(checkingVisibilityOfElement(Msg, 5));
		}
	
	public boolean is_VRTConvertSuccessMsgDisplayed()
	{
		WebElement msg = driver.findElementByAccessibilityId("Popup Window");
		return IsElementVisibleStatus(msg);
	}
	
	// Enter sync in folder path
/*	public void enter_Filepath(String pathname) throws AWTException, IOException, InterruptedException{
		Thread.sleep(5000);
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
	} */
	public void enter_Filepath(String pathname) throws AWTException, IOException, InterruptedException{
		clickOn(AVSConvertBrowseBtn);
		Thread.sleep(3000);
		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(2000);
		
		String fp2 = System.getProperty("user.dir") +  "\\src\\test\\resources\\TestData\\" + pathname;
		System.out.println(fp2);
		//"git\\AVS\\AVS\\src\\test\\resources\\TestData"
		Thread.sleep(2000);
		alert.sendKeys(fp2);
		Thread.sleep(500);
		System.out.println("PATH ENTERED");

		//Click Select Folder button

		WebElement SelectFolderBtn = driver.findElementByAccessibilityId("1");
		Thread.sleep(1000);

		clickOn(SelectFolderBtn);
		
		// switch back
		driver.switchTo().activeElement();
		Thread.sleep(500);
	}
	
	public List<String> getFleNameFromFolder(String fileName)
	{
		List<String> results = new ArrayList();
		File[] files = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TestData\\" + fileName).listFiles();
		
		for(File file: files)
		{
			if(file.isFile())
			{
			results.add(file.getName());
			}
		}
		return results;
	}
	//Click Back button to move to File Management Page
   	public MainHubPage click_BackBtn() throws IOException {
   		clickOn(backBtn);
   		return new MainHubPage();
   	}
 // Enter sync in folder path
   	public void enter_NewFilepath(String pathname) throws AWTException, IOException, InterruptedException{
   		Thread.sleep(2000);
  // 		Click_BrowseBtn();
   	//	WebElement browse = driver.findElementByName("Browse");
   	//	clickOn(browse);
		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(2000);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		String fp2 = pathname;
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
	


}
