/**
 * @author ruchika

 *
 */

package com.advrt.testcases;


import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.text.ParseException;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
//import com.vrt.Listners.AllureReportListner;
import com.advrt.base.BaseClass;
import com.advrt.pages.LoginPage;
import com.advrt.pages.MainHubPage;
import com.advrt.pages.UserManagementPage;//AD_UMpage
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.AD_UMPage;
import com.advrt.utility.TestUtilities;


public class AD_PolicyTest extends BaseClass{
	
	public AD_PolicyTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();
	
	//Initialization of pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	PoliciesPage PoliciesPage;
	AD_UMPage AD_UMPage;
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_ADpolicyTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("ADpolicy Test in Progress..");
		

		// Rename the VRT Data Files folder if exists in order to make the system default
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service", "DataFiles");
		//Copy the Default DataFIles folder from Test Data to the App service location.
		String SrcLocation  = System.getProperty("user.dir") +  "\\src\\test\\resources\\TestData\\DataFiles"; 
		String DestLocation = "C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles";	
		tu.Copy_FolderFromOneDirectoryToANother(SrcLocation, DestLocation);
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		extent.addSystemInfo("VRT Version", LoginPage.get_SWVersion_About_Text());
		LoginPage.clickOn_AppName();
		PoliciesPage = LoginPage.DefaultLogin();
		UserManagementPage = PoliciesPage.click_UMHeader();
		
		
		UserManagementPage.ClickNewUser();	
		// Create the default Admin USer
		LoginPage = UserManagementPage.FirstUserCreation(AdmnUN, getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");	
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("User1");
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();	
		PoliciesPage = UserManagementPage.Click_Policy();
		
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		AppClose();
		Thread.sleep(2000);
		
	}
	
	//After All the tests are conducted
	//@AfterTest
	@AfterClass
		public void endReport() throws InterruptedException {
			extent.flush();
			extent.close();
			System.out.println("AD policy test completed");
			Thread.sleep(500);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage=LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		PoliciesPage = UserManagementPage.Click_Policy();
		
		
	}

	@AfterMethod(alwaysRun=true)
	public void Teardown(ITestResult result) throws IOException, Exception {
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FailED IS # "+result.getName()+" #"); //to add name in extent report
			// TearDown of the App
			extentTest.log(LogStatus.FAIL, "TEST CASE FailED IS # "+result.getThrowable()+" #"); //to add error/exception in extent report
			
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); //to add screenshot in extent report
			//extentTest.log(LogStatus.Fail, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName()+" #");
			//String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			//extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2)); //to add screenshot in extent report
		}		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		//MainLoginPage.resetWebElements();
		Thread.sleep(5000);
		driver.quit();
	}
	
	
	/********
	Test Cases
	 * @throws IOException 
	*********/
	
	
	
	
	 //Verify if Active Directory User button is available in the Policies screen
	
	//Policies01-Verify if Active Directory is activated all policies should be in disable mode

	
	@Test(priority=1,groups = { "Sanity",
			"Regression" }, description = "Policies01-Verify if Active Directory is activated all policies should be in disable mode")

	public void AD_Policies01() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Policies01-Verify if Active Directory is activated all policies should be in disable mode");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		PoliciesPage.ClickSaveButton();
		PoliciesPage.clickOn_AcceptBtn();
		//UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserLoginPopup_UserCommentTextBox(getUID("adminFull"), getPW("adminFull"),"commit");
		tu.click_OK_popup();
		AD_UMPage=PoliciesPage.click_AD_UMHeader();
		PoliciesPage=AD_UMPage.AD_Click_Policy();
		
		sa.assertEquals(PoliciesPage.IspwdlengthcheckboxEnabled(), false,
				"Fail: password length check box is not enabled");
		
		sa.assertEquals(PoliciesPage.IsExpirepwdcheckboxEnabled(),false,
				"Fail: Expire password length check box is not enabled");
		
		
		sa.assertEquals(PoliciesPage.IsDisableUserafterAttemptsCheckBoxEnabled(),false,
				"Fail:Disable user account for consecutive login failures length check box is not enabled");
		
		sa.assertEquals(PoliciesPage.IsUserIdEntryCheckBox_Enabled1(),false,
				"Fail: UserId Entry check box is not enabled");
	
		sa.assertEquals(PoliciesPage.IsAutoSyncOutCheckBoxVisible(),false,
				"Fail: Auto Sync Out check box is not enabled");
		
		
		sa.assertEquals(PoliciesPage.IsSpecialCharCheckBoxVisible(),false,
				"Fail: Special Char CheckBox  is not enabled");
		
		sa.assertAll();
}

	
	//Policies02-Verify if Active Directory is deactivated all policies should be in enable mode
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "Policies02-Verify if Active Directory is deactivated all policies should be in enable mode")

public void AD_Policies02() throws InterruptedException {
extentTest = extent
		.startTest("Policies02-Verify if Active Directory is deactivated all policies should be in enable mode");
SoftAssert sa = new SoftAssert();

//PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

sa.assertEquals(PoliciesPage.IspwdlengthcheckboxEnabled(), true,
		"Fail: password length check box is not enabled");

sa.assertEquals(PoliciesPage.IsExpirepwdcheckboxEnabled(),true,
		"Fail: Expire password length check box is not enabled");
//

sa.assertEquals(PoliciesPage.IsDisableUserafterAttemptsCheckBoxEnabled(),true,
		"Fail:Disable user account for consecutive login failures length check box is not enabled");

sa.assertEquals(PoliciesPage.IsUserIdEntryCheckBox_Enabled(),true,
		"Fail: UserId Entry check box is not enabled");

sa.assertEquals(PoliciesPage.IsAutoSyncOutCheckBoxVisible(),true,
		"Fail: Auto Sync Out check box is not enabled");

//
sa.assertEquals(PoliciesPage.IsSpecialCharCheckBoxVisible(),true,
		"Fail: Special Char CheckBox  is not enabled");

sa.assertAll();
}
	
	// AD_Policies03-Verify if Required minimum password length should not
	// applicable when Active Directory Enabled  covered in PreferenceTest
	
	//Policies08-Verify if Require special characters should not applicable when Active Directory enabled covered in PreferenceTest
	
	

	@Test(groups = { "Sanity",
			"Regression" }, description = "AD_Policies03-Verify if Required minimum password length should not applicable when Active Directory Enabled")

	public void AD_Policies03() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"AD_Policies03-Verify if Required minimum password length should not applicable when Active Directory Enabled");
		SoftAssert sa = new SoftAssert();
		
		System.out.println("covered in preferencetest");
		
		sa.assertAll();

	}
	
	
	
	
	
	/*
	//NA-Policies12-Verify if "Disable Password System" functionality should not be available in the Policies.
	
	
	@Test(groups = "Sanity", description = "Policies05-Verify if Disable user account for consecutive login failures should not applicable when Active Directory Enabled")

	public void AD_Policies05() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Policies05-Verify if Disable user account for consecutive login failures should not applicable when Active Directory Enabled");

		SoftAssert sa = new SoftAssert();
       // MainHubPage=AD_UMPage.click_BackBtn();
        LoginPage=MainHubPage.UserSignOut();
		
		 for(int i=0; i<=2;i++) 
		 { 
		 LoginPage.EnterUserID("123");
		 LoginPage.EnterUserPW("123"); 
		 LoginPage.ClickLoginBtn();
		 tu.click_OK_popup(); 
		 Thread.sleep(2000); 
		 }
		 
		 
//		 -----first--------
/*		 
		// first attempt
		LoginPage.EnterUserID("123");
		Thread.sleep(5000);
		LoginPage.EnterUserPW("123456");
		LoginPage.ClickLoginBtn();
		tu.click_OK_popup();
		Thread.sleep(5000);

		// second attempt
		LoginPage.EnterUserID("1234");
		Thread.sleep(5000);
		LoginPage.EnterUserPW("123456");
		LoginPage.ClickLoginBtn();
		tu.click_OK_popup();
		Thread.sleep(5000);

		// third attempt
		LoginPage.EnterUserID("12345");
		Thread.sleep(5000);
		LoginPage.EnterUserPW("123456");
		LoginPage.ClickLoginBtn();  */
	/*
	
	//-----first----
	
		 String err_Msg = "Software Application Shutdown because of \"3\" invalid Login entries"; 
		 
		 sa.assertEquals(tu.get_popup_text(), err_Msg,
				 "Fail: Error Message is not displayed");
		 tu.click_OK_popup();
		 
		 try
		 {
			 sa.assertEquals(LoginPage.Is_AVSAppLoginScreen_Displayed(), false,
						"FAIL: App does not SHUTDOWN " + "on entering 3 times INVALID User Credentials");
				sa.assertAll();
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		

	}
*/
	
	}
