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
import com.advrt.pages.UserManagementPage;
import com.advrt.pages.PoliciesPage;
import com.advrt.utility.TestUtilities;
import com.advrt.utility.setupCreationUtility;
import com.advrt.utility.userManagementUtility;


public class ADpopupTest extends BaseClass{
	
	public ADpopupTest() throws IOException {
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
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_ADpopupTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("ADpopup Test in Progress..");
		

		// Rename the VRT Data Files folder if exists in order to make the system default
	/*	renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service", "DataFiles");
		//Copy the Default DataFIles folder from Test Data to the App service location.
		//String SrcLocation  = System.getProperty("user.dir") +  "\\src\\test\\resources\\TestData\\DataFiles"; 
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
		UserManagementPage.clickPrivRunQual();
		UserManagementPage.clickPrivRunCal();
		UserManagementPage.click_PrivRunVerf();
		UserManagementPage.Click_Create_AssetCheckBox();
		UserManagementPage.Click_EditAssetCheckBox();
		UserManagementPage.click_CreatSetupPriv();
		UserManagementPage.click_EditSetupPriv();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();	
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		AppClose();
		Thread.sleep(2000);
		*/
	}
	
	//After All the tests are conducted
	//@AfterTest
	@AfterClass
		public void endReport() throws InterruptedException {
			extent.flush();
			extent.close();
			System.out.println("AD popup test completed");
			Thread.sleep(500);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		//PoliciesPage = MainHubPage.ClickAdminTile_Polpage();
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
	*********/
	
	//Verify if Active Directory User button is available in the Policies screen
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "AD01-Verify if Active Directory User button is available in the Policies screen")

	public void AD01() throws InterruptedException {
		extentTest = extent
				.startTest("AD01-Verify if Active Directory User button is available in the Policies screen");
		SoftAssert sa = new SoftAssert();
		
		sa.assertEquals(PoliciesPage.Is_ActiveDirectoryUserbutton_Displayed(), true,
				"Fail: ActiveDirectory User button is not Displayed");
		sa.assertAll();
}

 //AD02-Verify if clicking on the Active Directory User button Enter Active Directory User credentials popup displayed

	@Test(groups = { "Sanity",
			"Regression" }, description = "AD02-Verify if clicking on the Active Directory User button Enter Active Directory User credentials popup displayed")

	public void AD02() throws InterruptedException {
		extentTest = extent.startTest(
				"AD02-Verify if clicking on the Active Directory User button Enter Active Directory User credentials popup displayed");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

		sa.assertEquals(PoliciesPage.Is_LDAPLoginPopup_Displayed(), true,
				"Fail: Enter Active Directory User credentials popup is not Displayed");

		sa.assertAll();
	}
	 
 //AD03-Verify if user able to enter the username in the username field in the Enter Active Directory User credentials popup

	@Test(groups = { "Sanity",
			"Regression" }, description = "AD03-Verify if user able to enter the username in the username field in the Enter Active Directory User credentials popup")

	public void AD03() throws InterruptedException {
		extentTest = extent.startTest(
				"AD03-Verify if user able to enter the username in the username field in the Enter Active Directory User credentials popup");
		
		SoftAssert sa = new SoftAssert();
		
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		
		PoliciesPage.Enter_AD_uid("Kiranc@VRT.LOCAL");
		sa.assertEquals(PoliciesPage.get_ADuserID_text(),"Kiranc@VRT.LOCAL","Fail: Acctual  User Id  is not matching with the expected user id in AD text box ");
		sa.assertAll();
		
	}
	
	
	//AD04- Verify if username field accepts 75 characters in the Enter Active Directory User credentials popup
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "AD04- Verify if username field accepts 75 characters in the Enter Active Directory User credentials popup")

	public void AD04() throws InterruptedException {
		extentTest = extent.startTest(
				"AD04- Verify if username field accepts 75 characters in the Enter Active Directory User credentials popup");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

		// Entered more that 75 chars
		PoliciesPage.Enter_AD_uid(
				"Kiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCALKiranc@VRT.LOCAL");

		sa.assertEquals(PoliciesPage.get_ADuserID_text().length(), 75,
				"Fail: User Id Text length is not correct in AD popup ");
		sa.assertAll();

	}

	// Verify if user able to enter the password in the password field in the Enter
	// Active Directory User credentials popup

	@Test(groups = { "Sanity",
			"Regression" }, description = "Verify if user able to enter the password in the password field in the Enter Active Directory User credentials popup")

	public void AD05() throws InterruptedException {
		extentTest = extent.startTest(
				"AD05-Verify if user able to enter the password in the password field in the Enter Active Directory User credentials popup");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

		PoliciesPage.Enter_AD_pwd("Amp@123");
		sa.assertEquals(PoliciesPage.get_ADpwd_text(), "●●●●●●●",
				"Fail: Acctual  User password  is not matching with the expected user id in AD text box ");
		sa.assertAll();

	}

	// AD06-Verify if password field accepts 32 characters in the Enter Active
	// Directory User credentials popup

	@Test(groups = { "Sanity",
			"Regression" }, description = "AD06-Verify if password field accepts 32 characters in the Enter Active Directory User credentials popup")

	public void AD06() throws InterruptedException {
		extentTest = extent.startTest(
				"AD06-Verify if password field accepts 32 characters in the Enter Active Directory User credentials popup");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

        //More than 32 chars entered

		PoliciesPage.Enter_AD_pwd("Amp@123Amp@123Amp@123Amp@123Amp@123Amp@123Amp@123Amp@123Amp@123");
		
		sa.assertEquals(PoliciesPage.get_ADpwd_text().length(), 32,
				"Fail: Password Text length is not correct in AD popup ");
		sa.assertAll();

	}
	
	//AD07-Verify if user able to enter the Domain Name in the Domain Name field in the Enter Active Directory User credentials popup
	
	//Enter_AD_DomainName
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "AD07-Verify if user able to enter the Domain Name in the Domain Name field in the Enter Active Directory User credentials popup")

	public void AD07() throws InterruptedException {
		extentTest = extent.startTest(
				"AD07-Verify if user able to enter the Domain Name in the Domain Name field in the Enter Active Directory User credentials popup");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

		PoliciesPage.Enter_AD_DomainName("10.17.17.54");
		sa.assertEquals(PoliciesPage.get_AD_DomainName_text(), "10.17.17.54",
				"Fail: Accutal domain name is not matching with the expected domain name  in AD domain name text box ");
		sa.assertAll();

}

	//AD08-Verify if Domain Name field accepts 75 characters in the Enter Active Directory User credentials popup
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "AD08-Verify if Domain Name field accepts 75 characters in the Enter Active Directory User credentials popup")

	public void AD08() throws InterruptedException {
		extentTest = extent.startTest(
				"AD08-Verify if Domain Name field accepts 75 characters in the Enter Active Directory User credentials popup");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

		PoliciesPage.Enter_AD_DomainName("10.17.17.54abcdefghijklmnopqrstuvwxyz12abcdefghijklmnopqrstuvwxyz45abcdefghijklmnopqrstuvwxyz45abcdefghijklmnopqrstuvwxyz");
		
		sa.assertEquals(PoliciesPage.get_AD_DomainName_text().length(), 75,
				"Fail: Domain Name Text length count in Ad Popup  is not correct");
		
		sa.assertAll();

}
	
	//AD09-Verify if user able to select the Authentication type from the dropdown
	
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "AD09-Verify if user able to select the Authentication type from the dropdown")

	public void AD09() throws InterruptedException {
		extentTest = extent.startTest("AD09-Verify if user able to select the Authentication type from the dropdown");

		SoftAssert sa = new SoftAssert();
		
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		Thread.sleep(1000);
		PoliciesPage.clickOn_ATypeComboBox();

		PoliciesPage.select_Authentication_Type("Secure");
		
		sa.assertEquals(PoliciesPage.getText_ATypeComboBox(), "Secure","Fail: Incorrect Authentication Type ");
		
		
		sa.assertAll();
	}

	// AD10-Verify if port number 389 is displayed based on the selection of Authentication Type as Secure
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "AD10-Verify if port number 389 is displayed based on the selection of Authentication Type as Secur")

	public void AD10() throws InterruptedException {
		extentTest = extent.startTest("AD10-Verify if port number 389 is displayed based on the selection of Authentication Type as Secur");
		SoftAssert sa = new SoftAssert();
		
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		Thread.sleep(1000);
		PoliciesPage.clickOn_ATypeComboBox();

		PoliciesPage.select_Authentication_Type("Secure");
		
		sa.assertEquals(PoliciesPage.getText_ATypeComboBox(), "Secure","Fail:  Authentication Type secure is not selected ");
		
		sa.assertEquals(PoliciesPage.getText_PortNoTextBox(), "389", "Fail: port number 389 is not displayed based on the selection of Authentication Type as Secure");
		sa.assertAll();
	}

	
	// AD11-Verify if port number 636 is displayed based on the selection of Authentication Type as Secure Socket Layer

	
		@Test(groups = { "Sanity", "Regression" }, description = "AD11-Verify if port number 636 is displayed based on the selection of Authentication Type as Secure Socket Layer")

		public void AD11() throws InterruptedException {
			extentTest = extent.startTest("AD11-Verify if port number 636 is displayed based on the selection of Authentication Type as Secure Socket Layer");

			SoftAssert sa = new SoftAssert();

			PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
			Thread.sleep(1000);
			PoliciesPage.clickOn_ATypeComboBox();
			PoliciesPage.select_Authentication_Type("SecureSocketsLayer");

			sa.assertEquals(PoliciesPage.getText_ATypeComboBox(), "SecureSocketsLayer","Fail:  Authentication Type secure is not selected ");
			
			sa.assertEquals(PoliciesPage.getText_PortNoTextBox(), "636", "Fail: port number 636 is not displayed based on the selection of Authentication Type as Secure");
			
			sa.assertAll();
		}
			
			
		
    //AD12- Verify if user able to enter the Port number in the Port field in the Enter Active Directory User credentials popup
		
	//Enter_PortNo
			
	@Test(groups = { "Sanity", "Regression" }, description = "AD11-Verify if port number 636 is displayed based on the selection of Authentication Type as Secure Socket Layer")

	public void AD12() throws InterruptedException {
		
		extentTest = extent.startTest("AD11-Verify if port number 636 is displayed based on the selection of Authentication Type as Secure Socket Layer");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		
		PoliciesPage.Enter_PortNo("12345");
		
		sa.assertEquals(PoliciesPage.getText_PortNoTextBox(), "12345", "Fail: port number 636 is not displayed based on the selection of Authentication Type as Secure");
		
		sa.assertAll();
 }
		
	//AD13-Verify if port field accepts 50 values in the Enter Active Directory User credentials popup	
	
	@Test(groups = { "Sanity", "Regression" }, description = "AD13-Verify if port field accepts 50 values in the Enter Active Directory User credentials popup")

	public void AD13() throws InterruptedException {
		
		extentTest = extent.startTest("AD13-Verify if port field accepts 50 values in the Enter Active Directory User credentials popup");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
	
	//Enter more than 50 numbers
		
		PoliciesPage.Enter_PortNo("123456789012345678901234567890123456789012345678901234567890");
		
		sa.assertEquals(PoliciesPage.getText_PortNoTextBox().length(), 50, "Fail: port number 636 is not displayed based on the selection of Authentication Type as Secure");
		
		sa.assertAll();
 }
		
	//AD14-Verify if Active Directory should be activated with valid Active Directory credentials after clicking on Connect button
	
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "AD14-Verify if Active Directory should be activated with valid Active Directory credentials after clicking on Connect button")

	public void AD14() throws InterruptedException {

		extentTest = extent.startTest(
				"AD14-Verify if Active Directory should be activated with valid Active Directory credentials after clicking on Connect button");

		SoftAssert sa = new SoftAssert();
		
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		
		sa.assertEquals(PoliciesPage.get_connectionStatus(), "Yes",
				"Fail: port number 636 is not displayed based on the selection of Authentication Type as Secure");

		sa.assertAll();
	}
	
	
	
	//AD15-Verify if User able to reject the permission and Active Directory should not be activated after clicking on the Save button

	//Need clarification from team
	
	//AD16 - Verify if User able to accept the permission while activating the Active Directory after clicking on the Save button
	
	@Test(groups = { "Sanity", "Regression" }, description = "AD16 - Verify if User able to accept the permission while activating the Active Directory after clicking on the Save button")

	public void AD16() throws InterruptedException {

		extentTest = extent.startTest(
				" AD16 - Verify if User able to accept the permission while activating the Active Directory after clicking on the Save button");

		SoftAssert sa = new SoftAssert();

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		PoliciesPage.ClickSaveButton();
		PoliciesPage.clickOn_AcceptBtn();
		
		sa.assertEquals(PoliciesPage.UserLoginPopupVisible(), true ,
				"Fail: User login pop up is not displaying when user clicked on Accept btn");

		sa.assertAll();
	}
	
	
	//AD17-Verify if Active Directory should not be activated with invalid User Id credentials after clicking on the Connect button
	
	@Test(groups = { "Sanity", "Regression" }, description = "AD17-Verify if Active Directory should not be activated with invalid User Id credentials after clicking on the Connect button")

	public void AD17() throws InterruptedException {

		extentTest = extent.startTest(
				"AD17-Verify if Active Directory should not be activated with invalid User Id credentials after clicking on the Connect button");

		SoftAssert sa = new SoftAssert();
		
	//Here we are entering the wrong UID as per the TC  specification 
		
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

		PoliciesPage.ActiveDirectoryUserLoginPopup("abc@abc.com", "Amphenol@123", "10.17.17.54", "Secure");	
		PoliciesPage.clickOn_ConnectBtn();
		sa.assertEquals(tu.get_popup_text(), "Invalid Credential, Please try again" ,
				"Fail: User login pop up is not displaying when user clicked on Accept btn");

		sa.assertAll();
	}
	
//AD18-Verify if Active Directory should not be activated with invalid Password credentials after clicking on the Connect button
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "AD18-Verify if Active Directory should not be activated with invalid Password credentials after clicking on the Connect button")

	public void AD18() throws InterruptedException {

		extentTest = extent.startTest(
				"AD18-Verify if Active Directory should not be activated with invalid Password credentials after clicking on the Connect button");

		SoftAssert sa = new SoftAssert();

		// Here we are entering the wrong UID as per the TC specification
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Abc@123", "10.17.17.54", "Secure");

		sa.assertEquals(tu.get_popup_text(), "Invalid Credential, Please try again",
				"Fail: User login pop up is not displaying when user clicked on Accept btn");

		sa.assertAll();
	}
	
	
	
	//AD19-Verify if Active Directory should not be activated with invalid Domain Name credentials after clicking on the Connect button
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "AD19-Verify if Active Directory should not be activated with invalid Domain Name credentials after clicking on the Connect button")

	public void AD19() throws InterruptedException {

		extentTest = extent.startTest("AD19-Verify if Active Directory should not be activated with invalid Domain Name credentials after clicking on the Connect button");

		SoftAssert sa = new SoftAssert();

// Here we are entering the wrong Domain Name as per the TC specification
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		sa.assertEquals(tu.get_popup_text(), "Invalid Credential, Please try again",
				"Fail: User login pop up is not displaying when user clicked on Accept btn");

		sa.assertAll();
	}

//AD20-Verify if Active Directory should not be activated if entered port and Domain port is different

	@Test(groups = { "Sanity",
			"Regression" }, description = "AD20-Verify if Active Directory should not be activated if entered port and Domain port is different")

	public void AD20() throws InterruptedException {

		extentTest = extent.startTest(
				"AD20-Verify if Active Directory should not be activated if entered port and Domain port is different");
		SoftAssert sa = new SoftAssert();

     //Here we are entering the wrong Domain as per the TC specification
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();

		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.ab.oo.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		sa.assertEquals(tu.get_popup_text(), "Invalid Credential, Please try again",
				"Fail: User login pop up is not displaying when user clicked on Accept btn");

		sa.assertAll();
	}
	
	
	//Verify if Active Directory should not be activated if Authentication type is selected differently for the Domain
	
	//AD22-Verify if Validation message should be displayed if any of the field is blank in the Enter Active Directory User Credentials popup

	@Test(groups = { "Sanity",
			"Regression" }, description = "AD20-Verify if Active Directory should not be activated if entered port and Domain port is different")

	public void AD22() throws InterruptedException {

		extentTest = extent.startTest(
				"AD20-Verify if Active Directory should not be activated if entered port and Domain port is different");
		SoftAssert sa = new SoftAssert();

//Here we are entering the wrong Domain as per the TC specification
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		
		sa.assertEquals(tu.get_popup_text(), "Invalid Credential, Please try again",
				"Fail: User login pop up is not displaying when user clicked on Accept btn");

		sa.assertAll();
	}
	
	//AD23-Verify if entered data in the Enter Active Directory User Credentials popup should be cleared on clicking the cancel button at popup

	@Test(groups = { "Sanity",
			"Regression" }, description = "AD20-Verify if Active Directory should not be activated if entered port and Domain port is different")

	public void AD23() throws InterruptedException {

		extentTest = extent.startTest(
				"AD20-Verify if Active Directory should not be activated if entered port and Domain port is different");
		SoftAssert sa = new SoftAssert();

		// Here we are entering the wrong Domain as per the TC specification
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.Enter_PortNo("12qwe");
		PoliciesPage.clickOn_ConnectBtn();

		sa.assertEquals(tu.get_popup_text(),
				"Offline Mode: Your credentials cannot get validated,please connect to your Domain to update the Active Directory information!",
				"Fail: alert not occured ");

		sa.assertAll();
	}

	//AD24-Verify if click on X button Enter Active Directory User Credentials popup should be closed
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "AD24-Verify if click on X button Enter Active Directory User Credentials popup should be closed")

	public void AD24() throws InterruptedException {

		extentTest = extent.startTest(
				"AD24-Verify if click on X button Enter Active Directory User Credentials popup should be closed");
		SoftAssert sa = new SoftAssert();
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
        // Here we are entering the wrong Domain as per the TC specification
		PoliciesPage.Click_LDAPCloseButton_Btn();
		
		sa.assertEquals(PoliciesPage.UserLoginPopupVisible(), false ,
				"Fail: User login pop up is not displaying when user clicked on Accept btn");

		sa.assertAll();
		
		sa.assertAll();
	}
	
}
