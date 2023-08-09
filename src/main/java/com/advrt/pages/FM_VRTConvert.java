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

import com.advrt.base.BaseClass;
import com.advrt.utility.TestUtilities;

public class FM_VRTConvert extends BaseClass{
	TestUtilities tu = new TestUtilities();
	//Page element variable declaration definition
	WebElement VRTConvertHeaderBtn = null;
	WebElement VRTConvertBrowseBtn = null;
	WebElement VRTConvertOKbtn = null;
		
	//Page element Initialize method
	private void initElements()
	{
		VRTConvertHeaderBtn = driver.findElementByAccessibilityId("VRTConvert");
		VRTConvertBrowseBtn = driver.findElementByAccessibilityId("ConvertRawFileBrowseButton");
		VRTConvertOKbtn = driver.findElementByAccessibilityId("ConvertRawFileOKButton");		

	}
	
	//Constructor for initializing the page elements
	FM_VRTConvert() throws IOException
	{
		super();
		initElements();
	}
	
	//Release memory
	public void resetWebElements()
	{
		VRTConvertHeaderBtn = null;
		VRTConvertBrowseBtn = null;
		VRTConvertOKbtn = null;
		
	}
	
	/*----------------------
	Methods of VRTConvert Page
	------------------------*/
	//Click the folder browse button
	public void Click_BrowseBtn() throws InterruptedException {
		clickOn(VRTConvertBrowseBtn);
		Thread.sleep(2000);
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
	


}
