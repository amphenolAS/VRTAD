
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
import com.advrt.base.BaseClass;
import com.advrt.pages.LoginPage;
import com.advrt.pages.MainHubPage;
import com.advrt.pages.UserManagementPage_Manual;
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.AuditPage;
import com.advrt.pages.UserManagementPage;
import com.advrt.pages.ADUM_page;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.utility.ADUserManagementUtility;
import com.advrt.utility.TestUtilities;


public class AD_UMtest2 extends BaseClass{
	
	public AD_UMtest2() throws IOException {
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
	UserManagementPage_Manual UserManagementPage_Manual;
	PoliciesPage PoliciesPage;
	ADUM_page ADUM_page;
	DefaultUserPrivilages_page DefaultUserPrivilages_page;
	AuditPage AuditPage;
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_ADUMTest2"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD-UM Test in Progress..");
		

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
		UserManagementPage_Manual = PoliciesPage.click_UmHeader();
		Thread.sleep(1000);
		UserManagementPage_Manual.ClickNewUser();	
		// Create the default Admin USer
		LoginPage = UserManagementPage_Manual.FirstUserCreation(AdmnUN, getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");
				
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage_Manual = MainHubPage.ClickAdminTile_manualUM();
		UserManagementPage_Manual.clickAnyUserinUserList("User1");
	
		UserManagementPage_Manual.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();
		
		PoliciesPage = UserManagementPage_Manual.Click_Policy();
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		PoliciesPage.ClickSaveButton();
		PoliciesPage.clickOn_AcceptBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();
		Thread.sleep(2000);
		ADUM_page =	PoliciesPage.ClickUM_Tab_AD();
	
		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(0);
		ADUM_page.enterNewUserTitle("Manager");
		ADUM_page.SelectUType("SystemAdministrator");
		ADUM_page.ClickNewUserSaveButton();
		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();
		tu.click_OK_popup();
		Thread.sleep(2000);
	
	}
	
	//After All the tests are conducted
	//@AfterTest
	@AfterClass
		public void endReport() throws InterruptedException {
			extent.flush();
			extent.close();
			System.out.println("AD-UM  test completed");
			Thread.sleep(500);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		ADUM_page = MainHubPage.ClickAdminTile_ADUM();
		//PoliciesPage = MainHubPage.ClickAdminTile_Polpage();
		//ADUM_page =	PoliciesPage.ClickUM_Tab_AD();
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
	 * @throws AWTException 
	 * @throws IOException 
	*********/
	
	
	// UM05-Verify if Admin user able to assign the Supervisor privileges to
	// selected group

	@Test(groups = { "Sanity",
			"Regression" }, description = "UM05-Verify if Admin user able to assign the Supervisor privileges to selected group")

	public void UM05() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("UM05-Verify if Admin user able to assign the Supervisor privileges to selected group");

		SoftAssert sa = new SoftAssert();
		ADUM_page.select_grp("QA Testers2");
		ADUM_page.select_user(0);
		ADUM_page.SelectUType("Supervisor");
		ADUM_page.enterNewUserTitle("Manager");
		ADUM_page.ClickNewUserSaveButton();

		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","comment");
		sa.assertEquals(tu.get_popup_text(), "Group: \" QA Testers2 \" saved...",
				"Fail: Incorrect alert message shown");
		tu.click_OK_popup();
		sa.assertAll();

	}

	
	// UM06-Verify if Admin user able to assign the SystemAdministrator privileges
	// to selected group

	@Test(groups = { "Sanity",
			"Regression" }, description = "UM06-Verify if Admin user able to assign the SystemAdministrator privileges to selected group")

	public void UM06() throws InterruptedException, AWTException, IOException {

		extentTest = extent.startTest(
				"UM06-Verify if Admin user able to assign the SystemAdministrator privileges to selected group");

		SoftAssert sa = new SoftAssert();
		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(0);
		ADUM_page.SelectUType("SystemAdministrator");
		ADUM_page.enterNewUserTitle("Manager");
		ADUM_page.ClickNewUserSaveButton();

		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","comment");
		sa.assertEquals(tu.get_popup_text(), "Group: \" QA Testers \" saved...", "Fail: Incorrect alert message shown");
		tu.click_OK_popup();
		sa.assertAll();

	}

	// UM07-Verify if Admin user able to assign the Operator privileges to selected
	// group

	@Test(groups = { "Sanity",
			"Regression" }, description = "UM07-Verify if Admin user able to assign the Operator privileges to selected group")

	public void UM07() throws InterruptedException, AWTException, IOException {

		extentTest = extent
				.startTest("UM07-Verify if Admin user able to assign the Operator privileges to selected group");

		SoftAssert sa = new SoftAssert();
		ADUM_page.select_grp("QA2");
		ADUM_page.select_user(0);
		ADUM_page.SelectUType("Operator");
		ADUM_page.enterNewUserTitle("Manager");
		ADUM_page.ClickNewUserSaveButton();

		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","comment");
		sa.assertEquals(tu.get_popup_text(), "Group: \" QA2 \" saved...", "Fail: Incorrect alert message shown");
		tu.click_OK_popup();
		sa.assertAll();

	}

	
	//UM08-Verify if Admin user able to assign the Created New User type privileges to selected group
	
	
	@Test(groups = { "Sanity",
			"Regression" }, description = "UM08-Verify if Admin user able to assign the Created New User type privileges to selected group")

	public void UM08() throws InterruptedException, AWTException, IOException {

		extentTest = extent
				.startTest("UM08-Verify if Admin user able to assign the Created New User type privileges to selected group");

		SoftAssert sa = new SoftAssert();
		
		ADUM_page.select_grp("QA3");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);
		DefaultUserPrivilages_page.Enter_NewUserType("res");
		DefaultUserPrivilages_page.click_UPUserManagementCheckBox();
		DefaultUserPrivilages_page.click_UPAssetsPrivlegesCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		Thread.sleep(1000);
		
		ADUM_page.SelectUType("res");
		
		sa.assertEquals(ADUM_page.Fetch_UserType(),"res","FAIL: User Type has not selected as per the users selection");
		sa.assertAll();

	}
	
	
	 //UM8 TO UM17 will be handled manually
	
	
	 //UM18-Verify if unmaps button displayed when the group selected where the group is already configured
	 
	 
		@Test(groups = { "Sanity","Regression" }, description = "UM18-Verify if unmap button displayed when the group selected where the group is already configured")

		public void UM18() throws InterruptedException, AWTException, IOException {

			extentTest = extent.startTest("UM18-Verify if unmap button displayed when the group selected where the group is already configured");

			SoftAssert sa = new SoftAssert();

			ADUM_page.select_grp("QA Testers");
			ADUM_page.select_user(1);
			ADUM_page.SelectUType("SystemAdministrator");
			ADUM_page.enterNewUserTitle("Manager");
			sa.assertEquals(ADUM_page.IsUnmapDisplayed(),true,"Fail : Unmap button is not displaying");
			sa.assertAll();
		}

  //UM19-Verify if user able to unmap the users from the selected usertype once clicking on the Unmap button

		@Test(groups = { "Sanity",
				"Regression" }, description = "UM19-Verify if user able to unmap the users from the selected usertype once clicking on the Unmap button")

		public void UM19() throws InterruptedException, AWTException, IOException {

			extentTest = extent.startTest(
					"UM19-Verify if user able to unmap the users from the selected usertype once clicking on the Unmap button");

			SoftAssert sa = new SoftAssert();

			ADUM_page.select_grp("Automation");
			ADUM_page.select_user(1);
			ADUM_page.SelectUType("SystemAdministrator");//
			ADUM_page.enterNewUserTitle("Manager");
			ADUM_page.ClickNewUserSaveButton();
			UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","comment");
			tu.click_OK_popup();
			MainHubPage=ADUM_page.ClickBackButn();
			MainHubPage.UserSignOut();
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
			ADUM_page = MainHubPage.ClickAdminTile_ADUM();
			ADUM_page.select_grp("Automation");
			ADUM_page.select_user(1);
			ADUM_page.SelectUType("SystemAdministrator");
			ADUM_page.enterNewUserTitle("Manager");
			ADUM_page.click_UnmapBtn();

			sa.assertEquals(tu.UserLoginPopupVisible(), true, "Fail: User Login Popup is not visible");

			sa.assertAll();
		}
  
       //UM20 - Verify if User Management screen displays New User creation when Active Directory is not activated
      // UM20 automated in AD_UMTest script	
	
	//UM20 TO UM25 handled in AD_UMTest class
		
	//UM26 TO UM28 handled Manually
		
     //UM29-Verify if user able to assign the selected privileges to the created New user type
	
		
		@Test(groups = { "Sanity",
				"Regression" }, description = "UM29-Verify if user able to assign the selected privileges to the created New user type")

		public void UM29() throws InterruptedException, AWTException, IOException {

			extentTest = extent.startTest(
					"UM29-Verify if user able to assign the selected privileges to the created New user type");

			SoftAssert sa = new SoftAssert();

			ADUM_page.select_grp("QA3");
			ADUM_page.select_user(2);
			DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
			Thread.sleep(1000);
			DefaultUserPrivilages_page.Enter_NewUserType("NEWTYPE");

			DefaultUserPrivilages_page.click_UPUserManagementCheckBox();
			DefaultUserPrivilages_page.click_UPAssetsPrivlegesCheckBox();

			sa.assertEquals(DefaultUserPrivilages_page.Is_UPAdminCheckBox_checkedin(), true,
					"Fail: Admin checkbox is not checked in");

			sa.assertEquals(DefaultUserPrivilages_page.Is_UPAssetsPrivlegesCheckBox_checkedin(), true,
					"Fail: Asset Privillages checkbox is not checked in");

			sa.assertAll();

		}		
	
		
	// UM30-Verify if User able to modify the privileges for the existing user type using New user type
		
		
		@Test(groups = { "Sanity",
				"Regression" }, description = "UM30-Verify if User able to modify the privileges for the existing user type using New user type")

		public void UM30() throws InterruptedException, AWTException, IOException {

			extentTest = extent
					.startTest("UM30-Verify if User able to modify the privileges for the existing user type using New user type");

			SoftAssert sa = new SoftAssert();
			
			ADUM_page.select_grp("QA3");
			ADUM_page.select_user(2);
			DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
			Thread.sleep(1000);
			DefaultUserPrivilages_page.Enter_NewUserType("UM30");
			Thread.sleep(1000);
			DefaultUserPrivilages_page.click_UPUserManagementCheckBox();
			DefaultUserPrivilages_page.click_UPAssetsPrivlegesCheckBox();
			ADUM_page = DefaultUserPrivilages_page.click_save_btn();
			
			Thread.sleep(1000);
			
			DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
			
			DefaultUserPrivilages_page.Enter_NewUserType("UM30");
			
			DefaultUserPrivilages_page.click_ModifyAssetsPrivlegesCheckBox();
			
			sa.assertEquals(DefaultUserPrivilages_page.Is_UpdateBtn_Enabled(), true,"Fail : Update button is not displayed " );
			ADUM_page = DefaultUserPrivilages_page.clickOn_UpdateBtn();
			
			//Move to Audit Trial and verify if its audited
			MainHubPage = ADUM_page.ClickBackButn();
			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);
			
			String ActualAuditmsg = AuditPage.get_auditEvent_text();
			String Expectedmsg= "\"Modify Assets\" Privileges  is Enabled  for  User Type: \"UM30\" by User ID : \"kiranc\" , User Name : \"kiran c\"";
		    
			sa.assertEquals(ActualAuditmsg, Expectedmsg,"FAIL : ");
			sa.assertAll();

		}
		
	   //UM31-Verify if Newly added user type should display in the user type dropdown in the User management screen
			
	   // UM31-Similar to UM08 the above tc

		@Test(groups = { "Sanity",
				"Regression" }, description = "UM31-Verify if Newly added user type should display in the user type dropdown in the User management screen")

		public void UM31() throws InterruptedException, AWTException, IOException {

			extentTest = extent.startTest(
					"UM31-Verify if Newly added user type should display in the user type dropdown in the User management screen");

			System.out.println("UM31-Similar to UM08 the above tc");
		}
		
		// UM32-Verify if user should not be able to create the new user type with the
		// existing name

		@Test(groups = { "Sanity",
				"Regression" }, description = "UM32-Verify if user should not be able to create the new user type with the existing name")

		public void UM32() throws InterruptedException, AWTException, IOException {

			extentTest = extent.startTest(
					"UM32-Verify if user should not be able to create the new user type with the existing name");

			SoftAssert sa = new SoftAssert();

			ADUM_page.select_grp("QA3");
			ADUM_page.select_user(2);
			DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
			Thread.sleep(1000);
			DefaultUserPrivilages_page.Enter_NewUserType("UM30");

			DefaultUserPrivilages_page.click_ModifyEquipmentPrivlegesCheckBox();

			sa.assertEquals(DefaultUserPrivilages_page.Is_UpdateBtn_Enabled(), true,
					"Fail : Update button is not displayed ");

			sa.assertAll();

        }

		//UM33-Verify if User able to create the new user type with maximum of 50 characters
		
		@Test(groups = { "Sanity",
				"Regression" }, description = "UM33-Verify if User able to create the new user type with maximum of 50 characters")

		public void UM33() throws InterruptedException, AWTException, IOException {

			extentTest = extent
					.startTest("UM33-Verify if User able to create the new user type with maximum of 50 characters");

			SoftAssert sa = new SoftAssert();

			ADUM_page.select_grp("QA Testers");
			ADUM_page.select_user(2);
			DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");

			Thread.sleep(1000);

			// Enter data which is more than 50 characters

			DefaultUserPrivilages_page.Enter_NewUserType("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPHSVGSSCDF");
			String fetchtext = DefaultUserPrivilages_page.Fetchtxt_NewUserType();
			sa.assertEquals(fetchtext.length(),6,
					"FAIL: the new user type charecter length count is not maximum of 50 characters");
			sa.assertAll();

		}
		
		
		// UM34-Verify if System should not allow user to create the new user type using
	   //  special characters

		@Test(groups = {
				"Regression" }, dataProvider = "UM34", dataProviderClass = ADUserManagementUtility.class, description = "UM34-Verify if System should not allow user to create the new user type using special characters")
		public void UM34(String AName) throws InterruptedException, IOException, AWTException, ParseException {
			extentTest = extent.startTest(
					"UM34-Verify if System should not allow user to create the new user type using special characters");
			SoftAssert sa = new SoftAssert();
			
			ADUM_page.select_grp("QA Testers");
			ADUM_page.select_user(2);
			DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");

			Thread.sleep(1000);

			DefaultUserPrivilages_page.Enter_NewUserType(AName);
			DefaultUserPrivilages_page.Click_Create_UserManagement();
			DefaultUserPrivilages_page.click_savebtn_alert();

			sa.assertEquals(tu.get_popup_text(), "Please enter valid text for  New User Type.",
					"Fail : Alert message is not same");
			
			sa.assertAll();

  }
		
 //UM35-Verify if System should not allow user to create the new user type using numerical	
		
	@Test(groups = {
			"Regression" }, description = "UM35-Verify if System should not allow user to create the new user type using numerical")
	
	public void UM35() throws InterruptedException, IOException, AWTException, ParseException {
		
		extentTest = extent.startTest("UM35-Verify if System should not allow user to create the new user type using numerical");
		SoftAssert sa = new SoftAssert();
		
		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");

		Thread.sleep(1000);

		DefaultUserPrivilages_page.Enter_NewUserType("1234576");
		DefaultUserPrivilages_page.click_savebtn_alert();

		sa.assertEquals(tu.get_popup_text(), "Please enter valid text for  New User Type.",
				"Fail : Alert message is not same");
		
		sa.assertAll();
		
}
	
	//UM36-Verify if System should not allow user to create the new user type without selecting the privileges
	
	
	@Test(groups = {
			"Regression" }, description = "UM36-Verify if System should not allow user to create the new user type without selecting the privileges")

	public void UM36() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM36-Verify if System should not allow user to create the new user type without selecting the privileges");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);
		DefaultUserPrivilages_page.Enter_NewUserType("Demo");  //Select_UT_NewUtypecombobox("ATEST"
		DefaultUserPrivilages_page.click_savebtn_alert();

		sa.assertEquals(tu.get_popup_text(), "Please select any privilege", "Fail : Alert message is not same");
		sa.assertAll();

	}
	
	//UM37
	
	//UM38-Manually
	
	//UM39-Verify if Create Assets privileges should be available in the User Management Screen
 
	@Test(groups = {
			"Regression" }, description = "UM39-Verify if Create Assets privileges should be available in the User Management Screen")

	public void UM39() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent
				.startTest("UM39-Verify if Create Assets privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);
		DefaultUserPrivilages_page.Enter_NewUserType("UM39");
		DefaultUserPrivilages_page.click_UPAssetsPrivlegesCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM39");
		
		sa.assertEquals(ADUM_page.Is_AssetsPrivlegesCheckBox_checkedin(), true, "FAIL: Assets Privleges CheckBox is not checked in...  ");
		
		sa.assertAll();

	}
	
	
	//UM40-Verify if Modify Assets privileges should be available in the User Management Screen
	
	
	@Test(groups = {
			"Regression" }, description = "UM40-Verify if Modify Assets privileges should be available in the User Management Screen")

	public void UM40() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM40-Verify if Modify Assets privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);
		
		DefaultUserPrivilages_page.Enter_NewUserType("UM40");
		DefaultUserPrivilages_page.click_ModifyAssetsPrivlegesCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM40");

		sa.assertEquals(ADUM_page.Is_ModifyPrivlegesCheckBox_checkedin(), true,
				"FAIL: Modify Assets Privleges CheckBox is not checked in...  ");
		
		sa.assertAll();

	}
	
	//UM41-Verify if Delete Assets privileges should be available in the User Management Screen
	
	
	@Test(groups = {"Regression" }, description = "UM41-Verify if Delete Assets privileges should be available in the User Management Screen")

	public void UM41() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM41-Verify if Delete Assets privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);

		DefaultUserPrivilages_page.Enter_NewUserType("UM41");
		DefaultUserPrivilages_page.click_UPAssetDeleteCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM41");

		sa.assertEquals(ADUM_page.Is_AssetDeleteCheckBox_checkedin(), true, "FAIL: Modify Assets Privleges CheckBox is not checked in...");

		sa.assertAll();

	}
  
	//UM42-Verify if Create Setup privileges should be available in the User Management Screen
	
	@Test(groups = {"Regression" }, description = "UM41-Verify if Delete Assets privileges should be available in the User Management Screen")

	public void UM42() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM41-Verify if Delete Assets privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);

		DefaultUserPrivilages_page.Enter_NewUserType("UM42");
		DefaultUserPrivilages_page.click_UPSetupCreationCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM42");

		sa.assertEquals(ADUM_page.Is_SetupCreationCheckBox_checkedin(), true, "FAIL:setup creation Privleges CheckBox is not checked in...");

		sa.assertAll();

	}
	
	//UM43-Verify if Modify Setup privileges should be available in the User Management Screen
	
	
	@Test(groups = {"Regression" }, description = "UM43-Verify if Modify Setup privileges should be available in the User Management Screen")

	public void UM43() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM43-Verify if Modify Setup privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);

		DefaultUserPrivilages_page.Enter_NewUserType("UM43");
		DefaultUserPrivilages_page.click_UPModifySetupCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM43");

		sa.assertEquals(ADUM_page.Is_ModifySetupCheckBox_checkedin(), true, "FAIL:Modify Setup creation Privleges CheckBox is not checked in...");

		sa.assertAll();

	}
	
	
	
	//UM44-Verify if Delete Setup privileges should be available in the User Management Screen
	
	@Test(groups = {"Regression" }, description = "UM44-Verify if Delete Setup privileges should be available in the User Management Screen")

	public void UM44() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM44-Verify if Delete Setup privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);
		DefaultUserPrivilages_page.Enter_NewUserType("UM44");
		DefaultUserPrivilages_page.click_UPSetupdeleteCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM44");

		sa.assertEquals(ADUM_page.Is_DeleteSetupCheckBox_checkedin(), true, "FAIL: Delete Setup creation Privleges CheckBox is not checked in...");

		sa.assertAll();

	}
	
	
	
	//UM45-Verify if Create Equipment privileges should be available in the User Management Screen
	
	@Test(groups = {"Regression" }, description = "UM45-Verify if Create Equipment privileges should be available in the User Management Screen")

	public void UM45() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM45-Verify if Create Equipment privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);

		DefaultUserPrivilages_page.Enter_NewUserType("UM45");
		DefaultUserPrivilages_page.click_UPEquipmentPrivlegesCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM45");

		sa.assertEquals(ADUM_page.Is_EquipmentPrivlegesCheckBox_checkedin(), true, "FAIL:Equipment Privleges CheckBox is not checked in...");
		sa.assertAll();

	}
	
	//UM46-Verify if Modify Equipment privileges should be available in the User Management Screen
	
	@Test(groups = {"Regression" }, description = "UM46-Verify if Modify Equipment privileges should be available in the User Management Screen")

	public void UM46() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM46-Verify if Modify Equipment privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);

		DefaultUserPrivilages_page.Enter_NewUserType("UM46");
		DefaultUserPrivilages_page.click_ModifyEquipmentPrivlegesCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM46");

		sa.assertEquals(ADUM_page.Is_ModifyEquipmentPrivlegesCheckBox_checkedin(), true, "FAIL: Modify Equipment Privleges CheckBox is not checked in...");

		sa.assertAll();

	}
	
 //UM47-Verify if Delete Equipment privileges should be available in the User Management Screen
	
	@Test(groups = {"Regression" }, description = "UM47-Verify if Delete Equipment privileges should be available in the User Management Screen")

	public void UM47() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM47-Verify if Delete Equipment privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);

		DefaultUserPrivilages_page.Enter_NewUserType("UM47");
		DefaultUserPrivilages_page.click_UPEquipmentDeleteCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM47");

		sa.assertEquals(ADUM_page.Is_DeleteEquipmentPrivlegesCheckBox_checkedin(), true, "FAIL:Delete Equipment Privleges CheckBox is not checked in...");

		sa.assertAll();

	}
	
	
	//UM48-Verify if Policies privileges should be available in the User Management Screen
	
	@Test(groups = {"Regression" }, description = "UM48-Verify if Policies privileges should be available in the User Management Screen")

	public void UM48() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM48-Verify if Policies privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);

		DefaultUserPrivilages_page.Enter_NewUserType("UM48");
		DefaultUserPrivilages_page.click_UPPoliciesCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM48");

		sa.assertEquals(ADUM_page.Is_PoliciesCheckBox_checkedin(), true, "FAIL:policy Privleges CheckBox is not checked in...");

		sa.assertAll();

	}
	
	
 //UM49-Verify if Preferences privileges should be available in the User Management Screen
	
	@Test(groups = {"Regression" }, description = "UM49-Verify if Preferences privileges should be available in the User Management Screen")

	
	public void UM49() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM49-Verify if Preferences privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);

		DefaultUserPrivilages_page.Enter_NewUserType("UM48");
		DefaultUserPrivilages_page.click_UPPreferencesCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM48");

		sa.assertEquals(ADUM_page.Is_PreferencesCheckBox_checkedin(), true, "FAIL: preference  Privleges CheckBox is not checked in...");

		sa.assertAll();

	}
	
	//UM50-Verify if HardwareMaintenance privileges should be available in the User Management Screen
	
    @Test(groups = {"Regression" }, description = "UM50-Verify if HardwareMaintenance privileges should be available in the User Management Screen")

	
	public void UM50() throws InterruptedException, IOException, AWTException, ParseException {

		extentTest = extent.startTest("UM50-Verify if HardwareMaintenance privileges should be available in the User Management Screen");

		SoftAssert sa = new SoftAssert();

		ADUM_page.select_grp("QA Testers");
		ADUM_page.select_user(2);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		Thread.sleep(1000);
		
		DefaultUserPrivilages_page.Enter_NewUserType("UM50");
		DefaultUserPrivilages_page.click_HardwareMaintenanceCheckBox();
		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
		ADUM_page.SelectUType("UM50");

		sa.assertEquals(ADUM_page.Is_HardwareMaintenanceCheckBox_checkedin(), true, "FAIL: Hardware Maintenance CheckBox Privleges CheckBox is not checked in...");
		sa.assertAll();

	}
    
    //UM51-Verify if User Management privileges should be available in the User Management Screen
    
    
    @Test(groups = {"Regression" }, description = "UM51-Verify if User Management privileges should be available in the User Management Screen")

	
   	public void UM51() throws InterruptedException, IOException, AWTException, ParseException {

   		extentTest = extent.startTest("UM51-Verify if User Management privileges should be available in the User Management Screen");

   		SoftAssert sa = new SoftAssert();

   		ADUM_page.select_grp("QA Testers");
   		ADUM_page.select_user(2);
   		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
   		Thread.sleep(1000);
   		
   		DefaultUserPrivilages_page.Enter_NewUserType("UM51");
   		DefaultUserPrivilages_page.click_UPUserManagementCheckBox();
   		ADUM_page = DefaultUserPrivilages_page.click_save_btn();
   		ADUM_page.SelectUType("UM51");

   		sa.assertEquals(ADUM_page.Is_UserManagementCheckBox_checkedin(), true, "FAIL: User Management  Privleges CheckBox is not checked in...");
   		sa.assertAll();

   	}
     
 
     //UM60-Verify if Validation message should be displayed when user trying to unmap the group where the application is having one group
     
     @Test(groups = {"Regression" }, description = "UM60-Verify if Validation message should be displayed when user trying to unmap the group where the application is having one group")

     public void UM60() throws InterruptedException, IOException, AWTException, ParseException {

    		extentTest = extent.startTest("UM60-Verify if Validation message should be displayed when user trying to unmap the group where the application is having one group");

    		SoftAssert sa = new SoftAssert();

    		ADUM_page.select_grp("QA Testers");
    		ADUM_page.select_user(2);
    		ADUM_page.enterNewUserTitle("Manager");
 		ADUM_page.SelectUType("SystemAdministrator");
 	
 		ADUM_page.click_UnmapBtn();
 		String alertMSG = tu.get_popup_text();
 		
 		sa.assertEquals(alertMSG, "Unmap group has denied, due to system need to have at least one Administrator user.", "FAIL:alert message not displaying");
    		sa.assertAll();

 		
 		
 }
     
  // UM37- Verify if User should not be able to create new user type while
 	// creating the first user
  
 	@Test(groups = {
 			"Regression" }, description = "UM37- Verify if User should not be able to create new user type while creating the first user")
  
 	public void UM37() throws InterruptedException, IOException, AWTException, ParseException {
  
 		extentTest = extent.startTest(
 				"UM37- Verify if User should not be able to create new user type while creating the first user");
  
 		System.out.println("This has covered in before class");
 	}	
    
	
}
