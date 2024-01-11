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
import com.advrt.pages.UserManagementPage_Manual;
import com.advrt.pages.UserManagementPage_Manual;
import com.advrt.pages.PoliciesPage;
import com.advrt.utility.TestUtilities;
import com.advrt.pages.AuditPage;
import com.advrt.pages.ADUM_page;
import com.advrt.pages.DefaultUserPrivilages_page;

public class AD_LDAP_Audit_Test extends BaseClass{
	
	public AD_LDAP_Audit_Test() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();
	
	//Initialization of pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage_Manual UserManagementPage_Manual;
	PoliciesPage PoliciesPage;
	AuditPage AuditPage ;
	ADUM_page ADUM_page ;
	DefaultUserPrivilages_page DefaultUserPrivilages_page ;
	
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_AD_LDAP_Audit_Test"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD LDAP Audit Test in Progress..");
		

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
		UserManagementPage_Manual = PoliciesPage.click_UMHeader1();
		Thread.sleep(1000);
		UserManagementPage_Manual.ClickNewUser();	
		// Create the default supervisor USer
		LoginPage = UserManagementPage_Manual.FirstUserCreation(AdmnUN, getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");
				
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage_Manual = MainHubPage.ClickAdminTile_manualUM();
		UserManagementPage_Manual.clickAnyUserinUserList("User1");
	
		UserManagementPage_Manual.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();	
		MainHubPage = UserManagementPage_Manual.ClickBackButn();
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
			System.out.println("AD LDAP Audit Test is completed");
			Thread.sleep(500);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		
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

	//Audit01-Verify if audit record displayed for the successful login

	@Test(priority = 0, description = "Audit01-Verify if audit record displayed for the successful login")

	public void Audit01() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Audit01-Verify if audit record displayed for the successful login");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage_Manual = MainHubPage.ClickAdminTile_manualUM();
		PoliciesPage = UserManagementPage_Manual.Click_Policy();
		MainHubPage = PoliciesPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		
		sa.assertEquals(AuditPage.get_auditEvent_text(), "User ID : \"1\",User Name : \"User1\" Logged in to System.");
		sa.assertAll();	}


//Audit02-Verify if audit record displayed for the invalid username login
	
	@Test(priority = 1, description = "AD01-Verify if Active Directory User button is available in the Policies screen")

	public void Audit02() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("AD01-Verify if Active Directory User button is available in the Policies screen");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage_Manual = MainHubPage.ClickAdminTile_manualUM();
		PoliciesPage = UserManagementPage_Manual.Click_Policy();

		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.InvalidLogin("2", "111111");
		tu.click_OK_popup();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		AuditPage = MainHubPage.ClickAuditTitle();
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter("User does not exist for User ID:\"2\", User Name: \"Unknown user\"");
		AuditPage.click_Action_FilterBtn();
		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"User does not exist for User ID:\"2\", User Name: \"Unknown user\"");
		sa.assertAll();
	}
	
	// Audit03-Verify if audit record displayed for the invalid password

	@Test(priority = 2, description = "AD01-Verify if Active Directory User button is available in the Policies screen")

	public void Audit03() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("AD01-Verify if Active Directory User button is available in the Policies screen");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage_Manual = MainHubPage.ClickAdminTile_manualUM();
		PoliciesPage = UserManagementPage_Manual.Click_Policy();
		MainHubPage = PoliciesPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.InvalidLogin("1", "123456");
		tu.click_OK_popup();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		AuditPage = MainHubPage.ClickAuditTitle();
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter("Login attempt failed for User ID:\"1\", User Name: \"User1\"");
		AuditPage.click_Action_FilterBtn();
		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Login attempt failed for User ID:\"1\", User Name: \"User1\"");
		sa.assertAll();
	}

	
	// Audit04-Verify if audit record displayed for the locked Active Directory credentials

		@Test(priority = 3, description = "Audit04-Verify if audit record displayed for the locked Active Directory credentials")

		public void Audit04() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("Audit04-Verify if audit record displayed for the locked Active Directory credentials");
			System.out.println("covered in VRT");
		}
	
	
	
	
	//Audit05-Verify if audit should be recorded for Active Directory Activation

	@Test( priority = 4 ,description = "Audit05-Verify if audit should be recorded for Active Directory Activation")

	public void Audit05() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("Audit05-Verify if audit should be recorded for Active Directory Activation");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage_Manual = MainHubPage.ClickAdminTile_manualUM();
		PoliciesPage = UserManagementPage_Manual.Click_Policy();
		
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		PoliciesPage.ClickSaveButton();

		PoliciesPage.clickOn_AcceptBtn();
	
		tu.UserLoginPopup_UserCommentTextBox("1", "111111", "comment");
		tu.click_OK_popup();
		Thread.sleep(1000);
		ADUM_page =	PoliciesPage.ClickUM_Tab_AD();
		ADUM_page.select_grp("Automation");
		ADUM_page.enterNewUserTitle("Manager");
		ADUM_page.SelectUType("SystemAdministrator");
		Thread.sleep(1000);
		ADUM_page.ClickNewUserSaveButton();
	
		tu.UserLoginPopup_UserCommentTextBox("1", "111111", "created");
		tu.click_OK_popup();
		tu.click_OK_popup();
		Thread.sleep(2000);
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("ruchika1","Amphenol@123");
		
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter("\"Active Directory User\" field modified and accepted from \"False\" to \"True \"  by User ID : \"1\", User Name : \"User1\"");
		AuditPage.click_Action_FilterBtn();
		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"\"Active Directory User\" field modified and accepted from \"False\" to \"True \"  by User ID : \"1\", User Name : \"User1\"");
		
		MainHubPage = AuditPage.Click_BackBtn();
		
		//UserManagementPage_Manual = MainHubPage.ClickAdminTile_manualUM();
		ADUM_page=MainHubPage.ClickAdminTile_ADUM();
		PoliciesPage = ADUM_page.ClickOn_PoliciesHeaderText();
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ClickSaveButton();
		
		
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "comment");
		tu.click_OK_popup();
		tu.click_OK_popup();
		Thread.sleep(2000);
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("1","111111");
		
		
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter("\"Active Directory User\" field modified from \"True\" to \"False \"  by User ID : \"Ruchika1\", User Name : \"Ruchika1\"");
		AuditPage.click_Action_FilterBtn();
		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"\"Active Directory User\" field modified from \"True\" to \"False \"  by User ID : \"Ruchika1\", User Name : \"Ruchika1\"");
		
		sa.assertAll();
	}
	
	//Audit06-Verify if audit should be recorded for Active Directory De-Activation
	
	@Test(priority = 5,description = "Audit06-Verify if audit should be recorded for Active Directory De-Activation")

	public void Audit06() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("Audit06-Verify if audit should be recorded for Active Directory De-Activation");
		System.out.println("This Tc has covered in Audit05");
	
}

	//individually getting passed
	//Audit07-Verify if audit should be recorded for the user group configuration
	
	@Test(priority = 6, description = "Audit07-Verify if audit should be recorded for the user group configuration")

	public void Audit07() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("Audit07-Verify if audit should be recorded for the user group configuration");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage_Manual = MainHubPage.ClickAdminTile_manualUM();
		PoliciesPage = UserManagementPage_Manual.Click_Policy();

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		PoliciesPage.ClickSaveButton();

		PoliciesPage.clickOn_AcceptBtn();

		tu.UserLoginPopup_UserCommentTextBox("1", "111111", "comment");
		tu.click_OK_popup();
		//tu.click_OK_popup();
		//Thread.sleep(1000);
		//LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		//LoginPage = new LoginPage();
		//ADUM_page=MainHubPage.ClickAdminTile_ADUM();
		ADUM_page = PoliciesPage.ClickUM_Tab_AD();
		ADUM_page.select_grp("Automation");
		ADUM_page.enterNewUserTitle("Manager");
		ADUM_page.SelectUType("SystemAdministrator");
		Thread.sleep(1000);
		ADUM_page.ClickNewUserSaveButton();

		tu.UserLoginPopup_UserCommentTextBox("1", "111111", "created");
		tu.click_OK_popup();
		tu.click_OK_popup();
		Thread.sleep(2000);

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("ruchika1", "Amphenol@123");

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter(
				"User Group : \"Automation\"  , User Type : \"SystemAdministrator\" , User Privileges : \"Delete Setup & Create Reports & Delete StudyFiles/Reports & Audit Trail & User Management & Delete Assets & Create Equipment & Delete Equipment & Manual Sync & Archive Data & Copy Files/Reports & Camera Access & Create Pass Fail Template & Edit Pass Fail Template & Delete Pass Fail Template & Modify Equipment & Preferences & Policies & HardwareMaintenance\" , created by User ID : \"1\" , User Name : \"User1\"");
		AuditPage.click_Action_FilterBtn();
		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"User Group : \"Automation\"  , User Type : \"SystemAdministrator\" , User Privileges : \"Delete Setup & Create Reports & Delete StudyFiles/Reports & Audit Trail & User Management & Delete Assets & Create Equipment & Delete Equipment & Manual Sync & Archive Data & Copy Files/Reports & Camera Access & Create Pass Fail Template & Edit Pass Fail Template & Delete Pass Fail Template & Modify Equipment & Preferences & Policies & HardwareMaintenance\" , created by User ID : \"1\" , User Name : \"User1\"");

		sa.assertAll();

	}
	
	
	//individually getting passed
	//Audit08-Verify if audit should be recorded for the New User Type Creation
	
	@Test(priority = 7, description = "Audit08-Verify if audit should be recorded for the New User Type Creation")

	public void Audit08() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("Audit08-Verify if audit should be recorded for the New User Type Creation");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login("ruchika1","Amphenol@123");
		ADUM_page = MainHubPage.ClickAdminTile_ADUM();
		ADUM_page.select_grp("Automation");
		
		ADUM_page.enterNewUserTitle("Manager");
		DefaultUserPrivilages_page=ADUM_page.SelectUType1("NewUserType");
		DefaultUserPrivilages_page.Enter_NewUserType("Newuser");
		DefaultUserPrivilages_page.click_UPAssetsPrivlegesCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		
		ADUM_page.select_grp("Automation");
		//ADUM_page.select_user(3);
		ADUM_page.enterNewUserTitle("Manager");
		
		ADUM_page.SelectUType("Newuser");
		ADUM_page.ClickNewUserSaveButton();
		
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "updated");
		tu.click_OK_popup();
		
		MainHubPage =	ADUM_page.ClickBackButn();
		
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter(
				"User Group : \"Automation\"  , User Type : \"SystemAdministrator\" to \"newuser\" , User Privileges : \"Create Assets\" , Modified by User ID : \"Ruchika1\" , User Name : \"Ruchika1\"");
		AuditPage.click_Action_FilterBtn();
		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"User Group : \"Automation\"  , User Type : \"SystemAdministrator\" to \"newuser\" , User Privileges : \"Create Assets\" , Modified by User ID : \"Ruchika1\" , User Name : \"Ruchika1\"");

		sa.assertAll();

		
	}
	
	
	//Audit09-Verify if audit should be recorded for the User Type Modification
	
	@Test(priority = 8, description = "Audit09-Verify if audit should be recorded for the User Type Modification")

	public void Audit09() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest("Audit09-Verify if audit should be recorded for the User Type Modification");
		SoftAssert sa = new SoftAssert();
		
		System.out.println("This Tc is done in  Audit08");
		
	}
	
	// Audit10-Verify if audit should be recorded for the Allow Guest Login
	// activation

	@Test(priority = 9, description = "Audit10-Verify if audit should be recorded for the Allow Guest Login activation")

	public void Audit10() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("Audit10-Verify if audit should be recorded for the Allow Guest Login activation");
		SoftAssert sa = new SoftAssert();

		System.out.println("This Tc is covered in AD Allow Guest Login  class file ");

	}

	/// Audit11-Verify if audit should be recorded for the Allow Guest Login
	/// deactivation

	@Test(priority = 10, description = "Audit11-Verify if audit should be recorded for the Allow Guest Login deactivation")

	public void Audit11() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("Audit11-Verify if audit should be recorded for the Allow Guest Login deactivation");
		SoftAssert sa = new SoftAssert();

		System.out.println("This Tc is covered in AD Allow Guest Login  class file ");

	}
	
	
	
	//Audit13-Verify if audit should be recorded when the group is unmapped
		@Test(priority = 11, description = "Audit13-Verify if audit should be recorded when the group is unmapped")
	 
		public void Audit13() throws InterruptedException, IOException, AWTException {
			extentTest = extent.startTest("Audit13-Verify if audit should be recorded when the group is unmapped");

	 
			System.out.println("This Tc is covered in AD_UMtest2  class file ");
	 
		}

	 
		//Audit14-Verify if audit should be recorded when the user login to the application as Allow Guest Login user
		@Test(priority = 12, description = "Audit14-Verify if audit should be recorded when the user login to the application as Allow Guest Login user")
	 
		public void Audit14() throws InterruptedException, IOException, AWTException {
			extentTest = extent.startTest("Audit14-Verify if audit should be recorded when the user login to the application as Allow Guest Login user");

	 
			System.out.println("This Tc is covered in AD Allow Guest Login  class file ");
	 
		}	
	
	
}