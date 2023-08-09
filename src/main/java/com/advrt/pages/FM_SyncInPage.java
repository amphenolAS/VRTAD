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

public class FM_SyncInPage extends BaseClass{
	TestUtilities tu = new TestUtilities();
	//Page element variable declaration definition
	WebElement SyncInHeaderBtn = null;
	WebElement SyncInBrowseBtn = null;
	WebElement FltrByDtBtn = null;
	WebElement SyncInTextBox = null;
	WebElement SyncInOKbtn = null;
	WebElement SyncInBackbtn = null;
		
	//Page element Initialize method
	private void initElements()
	{
		SyncInHeaderBtn = driver.findElementByAccessibilityId("SyncIn");
		SyncInBrowseBtn = driver.findElementByAccessibilityId("SyncInFolderBrowseButton");
		SyncInTextBox = driver.findElementByAccessibilityId("SyncInFolderTextBox");
		FltrByDtBtn = driver.findElementByAccessibilityId("SyncInDateFilterCheckBox");
		SyncInOKbtn = driver.findElementByAccessibilityId("SyncInFolderOKButton");
		SyncInBackbtn = driver.findElementByAccessibilityId("BackButton");

	}
	
	//Constructor for initializing the page elements
	FM_SyncInPage() throws IOException
	{
		super();
		initElements();
	}
	
	//Release memory
	public void resetWebElements()
	{
		SyncInHeaderBtn = null;
		SyncInBrowseBtn = null;
		FltrByDtBtn = null;		
		SyncInTextBox = null;
		SyncInOKbtn = null;
		SyncInBackbtn = null;
		
	}
	
	/*----------------------
	Methods of Sync In Page
	------------------------*/

	// SyncInTextBox is visible
	public boolean SyncInTextBoxVisible() throws InterruptedException {
		return IsElementVisibleStatus(SyncInTextBox);
	}
	
	// Enter sync in folder path
	public void enter_Filepath(String pathname) throws AWTException, IOException, InterruptedException{
		clickOn(SyncInBrowseBtn);
		Thread.sleep(2000);
		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(2000);
		
		String fp2 = System.getProperty("user.dir") +  "\\src\\test\\resources\\TestData\\" + pathname;
		//System.out.println(fp2);
		alert.sendKeys(fp2);
		Thread.sleep(1000);

		//Click Select Folder button

		WebElement SelectFolderBtn = driver.findElementByAccessibilityId("1");
		clickOn(SelectFolderBtn);
		
		// switch back
		driver.switchTo().activeElement();
		Thread.sleep(1000);
	}
	
	//Click the Filter button
	public void click_FltrBtn() {
		clickOn(FltrByDtBtn);
	}
	
	// Click the Filter button
	public SyncInAssetListPage click_SyncInOK_btn() throws IOException, InterruptedException {
		//Thread.sleep(1000);
		clickOn(SyncInOKbtn);
		clickOn(SyncInOKbtn);
		return new SyncInAssetListPage();
	}
	
	
	//Click the SycnIn Alert confirmation message
	public void click_SyncIn_AlrtMag() {
		clickOn(FltrByDtBtn);
	}

	//Click Back button to move to File Management Page
	public MainHubPage click_BackBtn() throws IOException {
		clickOn(SyncInBackbtn);
		return new MainHubPage();
	}
	
}
