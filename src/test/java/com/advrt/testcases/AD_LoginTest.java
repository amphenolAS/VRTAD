/*                    

		Description              :This Test Suite TC's related to Login operations
		Script Writer            :Ruchika 
		Last Modified/ Updated by: Deepika Arjala								 
*/
package com.advrt.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//import com.vrt.Listners.AllureReportListner;
import com.advrt.base.BaseClass;
import com.advrt.pages.ADUM_page;
import com.advrt.pages.AD_UMPage;
import com.advrt.pages.AuditPage;
import com.advrt.pages.Database_configPage;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.pages.LoginPage;
import com.advrt.pages.MainHubPage;
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.UserManagementPage_Manual;
import com.advrt.utility.TestUtilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AD_LoginTest extends BaseClass {

	public AD_LoginTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

//com.advrt.testcases.AD_LoginTest#Login01
	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();

	// Initialization of pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage_Manual UserManagementPage_Manual;
	PoliciesPage PoliciesPage;
	ADUM_page ADUM_page;
	DefaultUserPrivilages_page DefaultUserPrivilages_page;
	AuditPage AuditPage;
	AD_UMPage AD_UMPage;
	Database_configPage Database_configPage;
	static String AdmnUN = "User1";

	// Before All the tests are conducted
	@BeforeClass
	// @BeforeTest
	private void PreSetUp() throws Exception {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER" + "_ADLoginTestReg(1.6.14)" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		// extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		// extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		// extent.addSystemInfo("ScriptVersion-Git",
		// prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD-Login Test in Progress..");


		// stop service
				Process stopService = Runtime.getRuntime().exec("cmd /c net stop VRT.DataAccessService.Host");
				stopService.waitFor();
				System.out.println("VRT Services stopped");
				Thread.sleep(5000);
				// Rename the VRT Data Files folder if exists in order to make the system
				// default
				renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service", "DataFiles");
				// Copy the Default DataFIles folder from Test Data to the App service location.
				String SrcLocation = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\DataFiles";
				String DestLocation = "C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles";
				tu.Copy_FolderFromOneDirectoryToANother(SrcLocation, DestLocation);
				System.out.println("Application is Launching");

		//Start the services
				Runtime.getRuntime().exec("cmd /c net start VRT.DataAccessService.Host").waitFor();
				System.out.println("VRT Services started");

				tu.waitForServiceRunning("VRT.DataAccessService.Host", 60);


		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		Database_configPage = LoginPage.DefaultLogin1();
		UserManagementPage_Manual = Database_configPage.click_UMHeaderMnl();
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

		PoliciesPage = UserManagementPage_Manual.Click_Policy();

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc1@VRTHYD.LOCAL", "Amphenol@123", "10.17.17.55", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		PoliciesPage.ClickSaveButton();
		//PoliciesPage.clickonOkBtn();
		PoliciesPage.clickOn_AcceptBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();
		ADUM_page =	PoliciesPage.ClickUM_Tab_AD();
		Thread.sleep(1000);
		ADUM_page.select_grp(prop.getProperty("Group1"));
		ADUM_page.enterNewUserTitle("Manager");
		ADUM_page.SelectUType("SystemAdministrator");
		Thread.sleep(1000);
		ADUM_page.ClickNewUserSaveButton();
		
		UserLoginPopup_UserCommentTextBox("1", "111111", "Admin");
		
		tu.click_OK_popup();
	
		tu.click_OK_popup();
		Thread.sleep(2000);
	}

	// After All the tests are conducted
	// @AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("AD-LOGIN  test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
	}



	 @AfterMethod(alwaysRun = true)
	    public void tearDown(ITestResult result) throws NoSuchSessionException, InterruptedException, IOException {
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
	 * Test Cases
	 * 
	 * @throws AWTException
	 * @throws IOException
	 *********/

	// Login01-Verify if System able to connect to the Domain with Active Directory
	// Credentials
	// Login02-Verify if Kaye 411 User able to login to the system for the first
	// time
	@Test(priority = 0, groups = { "Sanity",
			"Regression" }, description = "Login01-Verify if System able to connect to the Domain with Active Directory Credentials + Login02-Verify if Kaye 411 User able to login to the system for the first time")

	public void Login01() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login01-Verify if System able to connect to the Domain with Active Directory Credentials");

		SoftAssert sa = new SoftAssert();
		extent.addSystemInfo("VRT Version", LoginPage.get_SWVersion_About_Text());
		
		sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.55");

		MainHubPage = LoginPage.Login(prop.getProperty("Group1UserId"),prop.getProperty("Group1Pwd"));
		Thread.sleep(500);
		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true);
		sa.assertAll();

	}

	@Test(priority = 2, groups = { "Sanity",
			"Regression" }, description = "Login02-Verify if Kaye 411 User able to login to the system for the first time ")

	public void Login02() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login02-Verify if Kaye 411 User able to login to the system for the first time ");

		SoftAssert sa = new SoftAssert();
		System.out.println("We have covered this tc in the before method of the class");

		sa.assertAll();

	}
	// Login03-Verify if Kaye 411 user able to login to the application when the
	// Active Directory is activated and Manual Users and Active Directory users are
	// not available in the application

	@Test(priority = 3, groups = { "Sanity",
			"Regression" }, description = "Login02-Verify if Kaye 411 User able to login to the system for the first time ")

	public void Login03() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login03-Verify if Kaye 411 user able to login to the application when the Active Directory is activated and Manual Users and Active Directory users are not available in the application ");

		SoftAssert sa = new SoftAssert();
		System.out.println("We have covered this tc in the before method of the class");

		sa.assertAll();

	}

	// Login04-Verify if Active Directory user able to login to the application when
	// the Active Directory is activated and Manual Users and Active Directory
	// usersare available in the application

	@Test(priority = 3, groups = { "Sanity",
			"Regression" }, description = "Login04-Verify if Active Directory user able to login to the application when the Active Directory is activated and Manual Users and Active Directory users are available in the application")

	public void Login04() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login04-Verify if Active Directory user able to login to the application when the Active Directory is activated and Manual Users and Active Directory users are available in the application");

		SoftAssert sa = new SoftAssert();
		
		LoginPage = new LoginPage();
		sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.55");
		MainHubPage = LoginPage.Login(prop.getProperty("Group1UserId"),prop.getProperty("Group1Pwd"));
		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(), true,
				"Fail:Not able to login with Active Directory Credentials");
		sa.assertAll();
	}

	// Login05-Verify if Manual user able to login to the application and Policies
	// and User Management screen displays to create the Active Directory users when
	// the Active Directory is activated and Manual Users available and Active
	// Directory users are not available in the application

	@Test(priority = 4, groups = { "Sanity",
			"Regression" }, description = "Login05-Verify if Manual user able to login to the application and Policies and User Management screen displays to create the Active Directory users when the Active Directory is activated and Manual Users available and Active Directory users are not available in the application	\r\n"
					+ "")

	public void Login05() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login05-Verify if Manual user able to login to the application and Policies and User Management screen displays to create the Active Directory users when the Active Directory is activated and Manual Users available and Active Directory users are not available in the application");

		SoftAssert sa = new SoftAssert();
		LoginPage = new LoginPage();
		System.out.println("We have covered this tc in the before method of the class");

		sa.assertAll();

	}

// Login06-Verify if Active Directory user able to login to the application when
// the Active Directory is activated and Manual Users not available and Active
// Directory users are available in the application

	@Test(priority = 5, groups = { "Sanity",
			"Regression" }, description = "Login06-Verify if Active Directory user able to login to the application when the Active Directory is activated and Manual Users not available and Active Directory users are available in the application")

	public void Login06() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login06-Verify if Active Directory user able to login to the application when the Active Directory is activated and Manual Users not available and Active Directory users are available in the application");

		SoftAssert sa = new SoftAssert();
		System.out.println("Covered in Login04");
		sa.assertAll();

	}

	@Test(priority = 5, groups = { "Sanity",
			"Regression" }, description = "Login07-Verify if Manual user able to login to the application when the Active Directory is not activated and Manual Users available and Active Directory users are available in the application")

	public void Login07() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login07-Verify if Manual user able to login to the application when the Active Directory is not activated and Manual Users available and Active Directory users are available in the application");
		SoftAssert sa = new SoftAssert();
		
		System.out.println("This tc already handeled in Before test class");
		sa.assertAll();

	}

//Login08-Verify if Manual user able to login to the application when the Active Directory is not activated and Manual Users available and Active Directory users are not available in the application
	@Test(priority = 6, groups = { "Sanity",
			"Regression" }, description = "Login08-Verify if Manual user able to login to the application when the Active Directory is not activated and Manual Users available and Active Directory users are not available in the application")
	public void Login08() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login08-Verify if Manual user able to login to the application when the Active Directory is not activated and Manual Users available and Active Directory users are not available in the application");

		SoftAssert sa = new SoftAssert();
		System.out.println("This tc already handeled in Before test class");

		sa.assertAll();
	}

//Login10-Verify if Username displayed in the Login Screen when Active Directory Connected
	@Test(priority = 7, groups = { "Sanity",
			"Regression" }, description = "Login09-Verify if Active Directory user able to login to the application and Active Directory"
					+ " is disabled and user Active Directory able to login to the application and Policies and User Management screen displays to create the Manual users when Manual Users are not available and Active Directory users are available in the application")

	public void Login09() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login09-Verify if Active Directory user able to login to the application and Active Directory is disabled and user Active Directory able to login to the application and Policies and User Management screen displays to create the Manual users when Manual Users are not available and Active Directory users"
						+ " are available in the application");

		SoftAssert sa = new SoftAssert();
		
		sa.assertEquals(PoliciesPage.IsADConnected(), true, "Fail: LDAP is not activated");
		AD_UMPage = PoliciesPage.click_AD_UMHeader();

		AD_UMPage.select_grp(prop.getProperty("Group1"));
		//AD_UMPage.select_user(1);
		AD_UMPage.SelectUType("SystemAdministrator");
		AD_UMPage.enterNewUserTitle("Manager");
		AD_UMPage.ClickNewUserSaveButton();

		UserLoginPopup_UserCommentTextBox(getUID("adminFull"), getPW("adminFull"), "NA");
		tu.click_OK_popup();
		tu.click_OK_popup();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.55");
		MainHubPage = LoginPage.Login(prop.getProperty("Group1UserId"),prop.getProperty("Group1Pwd"));
		AD_UMPage = MainHubPage.AD_ClickAdminTile_UMpage();
		PoliciesPage = AD_UMPage.Click_Policy();
		// Deactivating the AD
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ClickSaveButton();
		UserLoginPopup(prop.getProperty("Group1UserId"),prop.getProperty("Group1Pwd"));
		tu.click_OK_popup();
		

		sa.assertAll();

	}

//Login10-Verify if Username displayed in the Login Screen when Active Directory Connected
//Login11-Verify if Password displayed in the Login Screen when Active Directory Connected
//Login12-Verify if Domain Name displayed in the Login Screen when Active Directory Connected
//Login13-Verify if Domain Name should not be in editable mode in the Login Screen
//Login14-Verify if Domain Name value should be displayed by default where the domain previously connected
	@Test(priority = 7, groups = { "Sanity",
			"Regression" }, description = "Login10-Verify if Username displayed in the Login Screen when Active Directory Connected + "
					+ "Login11-Verify if Password displayed in the Login Screen when Active Directory Connected"
					+ "Login12-Verify if Domain Name displayed in the Login Screen when Active Directory Connected"
					+ "Login13-Verify if Domain Name should not be in editable mode in the Login Screen"
					+ "Login14-Verify if Domain Name value should be displayed by default where the domain previously connected")

	public void Login10_Login11_Login12_Login13_Login14() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("Login10-Verify if Username displayed in the Login Screen when Active Directory Connected + "
						+ "+Login11-Verify if Password displayed in the Login Screen when Active Directory Connected+"
						+ "Login12-Verify if Domain Name displayed in the Login Screen when Active Directory Connected"
						+ "Login13-Verify if Domain Name should not be in editable mode in the Login Screen"
						+ "Login14-Verify if Domain Name value should be displayed by default where the domain previously connected");

		SoftAssert sa = new SoftAssert();

		// Verifying the domain name
		sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.55");
		LoginPage.enterTxt_DomainTextBox("123");
		// Domain name should not be in editable mode
		sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.55",
				"Fail:Domain name should be 10.17.17.55 but it is accepting some random values");
		// Validating the User id field
		sa.assertEquals(LoginPage.UserIDFieldPresence(), true);
		LoginPage.UserIdPwdPresence(prop.getProperty("Group1UserId"),prop.getProperty("Group1Pwd"));
		sa.assertEquals(LoginPage.GetTextUserIDField(), "ajay2");
		// Validating the password field
		sa.assertEquals(LoginPage.UserPWFieldPresence(), true);

		sa.assertAll();

	}

//Login15-Verify if System not allowed to logout from the application without Configuring the Active Directory user when Active Directory is activated

	@Test(priority = 12, groups = { "Sanity",
			"Regression" }, description = "Login15-Verify if System not allowed to logout from the application without Configuring the Active Directory user when Active Directory is activated")

	public void Login15() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login15-Verify if System not allowed to logout from the application without Configuring the Active Directory user when Active Directory is activated");

		SoftAssert sa = new SoftAssert();

		
		MainHubPage = LoginPage.Login(prop.getProperty("Group1UserId"),prop.getProperty("Group1Pwd"));

		ADUM_page = MainHubPage.ClickAdminTile_ADUM();
		sa.assertEquals(ADUM_page.UMtabPresence(), true, "Fail: Not landed to User Management page");
		PoliciesPage = ADUM_page.ClickOn_PoliciesHeaderText();
		sa.assertEquals(PoliciesPage.IsADConnected(), true, "Fail: LDAP is not activated");
		System.out.println(PoliciesPage.get_connectionStatus());
		sa.assertEquals(PoliciesPage.is_UpdateBtnvisible(), true);
		//sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.54");

		sa.assertAll();

		
	}

//Login16-Verify if System not allowed to logout from the application without creating the user when Active Directory is not activated

	@Test(priority = 13, groups = { "Sanity",
			"Regression" }, description = "Login16-Verify if System not allowed to logout from the application without creating the user when Active Directory is not activated")

	public void Login16() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login16-Verify if System not allowed to logout from the application without creating the user when Active Directory is not activated");

		SoftAssert sa = new SoftAssert();

		System.out.println("Local user is already created in Before class and login to the application");
		sa.assertAll();
	}

//Login17- Verify if Kaye-411 user not able to login to the application once Active Directory users/groups synced to the HMI
//Login18-Verify if Kaye-411 user not able to login to the application once Local user created in the HMI
	@Test(priority = 14, groups = { "Sanity",
			"Regression" }, description = "Login17- Verify if Kaye-411 user not able to login to the application once Active Directory users/groups synced to the HMI  + "
					+ "Login18-Verify if Kaye-411 user not able to login to the application once Local user created in the HMI")

	public void Login17_Login18() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login17- Verify if Kaye-411 user not able to login to the application once Active Directory users/groups synced to the HMI + "
						+ "Login18-Verify if Kaye-411 user not able to login to the application once Local user created in the HMI");

		SoftAssert sa = new SoftAssert();

		

		LoginPage.InvalidLogin("Kaye", "411");
		sa.assertEquals(tu.get_popup_text(), "Invalid Credential, Please try again",
				"FAIL: Application allowed the dafult user when AD is connected");

		sa.assertAll();

	}

//Login19-Verify if the Unassigned users able to login to the application as per the Guest login as supervisor

	@Test(priority = 23, groups = { "Sanity",
			"Regression" }, description = "Login19-Verify if the Unassigned users able to login to the application as per the Guest login as supervisor")
	public void Login19() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login19-Verify if the Unassigned users able to login to the application as per the Guest login as supervisor");

		SoftAssert sa = new SoftAssert();

		System.out.println("This Tc has been Covered in AllowGuestloginTest1 TC-AD_GL05");

		sa.assertAll();

	}

//Login20-Verify if the Unassigned users able to login to the application as per the Guest login as Operator

	@Test(priority = 24, groups = { "Sanity",
			"Regression" }, description = "Login20-Verify if the Unassigned users able to login to the application as per the Guest login as Operator")
	public void Login20() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login20-Verify if the Unassigned users able to login to the application as per the Guest login as Operator");

		SoftAssert sa = new SoftAssert();

		System.out.println("This Tc has been Covered in AllowGuestloginTest1 TC-AD_GL06");

		sa.assertAll();

	}

//Login21-Verify if the Unassigned users able to login to the application as per the Guest login as Administrator

	@Test(priority = 25, groups = { "Sanity",
			"Regression" }, description = "Login21-Verify if the Unassigned users able to login to the application as per the Guest login as Administrator")
	public void Login21() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login21-Verify if the Unassigned users able to login to the application as per the Guest login as Administrator");

		SoftAssert sa = new SoftAssert();

		System.out.println("This Tc has been Covered in AllowGuestloginTest1 TC-AD_GL07");

		sa.assertAll();

	}

//Login22-Verify if the Unassigned users able to login to the application as per the Guest login as created New user type
	@Test(priority = 26, groups = { "Sanity",
			"Regression" }, description = "Login22-Verify if the Unassigned users able to login to the application as per the Guest login as created New user type")
	public void Login22() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login22-Verify if the Unassigned users able to login to the application as per the Guest login as created New user type");

		SoftAssert sa = new SoftAssert();

		System.out.println("This Tc has been Covered in AllowGuestloginTest1 TC-AD_GL08");

		sa.assertAll();

	}

//Login23-Verify if validation message displayed when user trying to login to the application where the Domain user not available in Active Directory
	@Test(priority = 27, groups = { "Sanity",
			"Regression" }, description = "Login23-Verify if validation message displayed when user trying to login to the application where the Domain user not available in Active Directory")
	public void Login23() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login23-Verify if validation message displayed when user trying to login to the application where the Domain user not available in Active Directory");

		SoftAssert sa = new SoftAssert();

		

		LoginPage.Login1("Ruchika", "Amphenol@123");

		sa.assertEquals(tu.get_popup_text(), "Invalid Credential, Please try again",
				"FAIL: Application allowed the dafult user when AD is connected");

		sa.assertAll();

	}
//Login24-Verify if validation message is displayed when user login to the application with invalid username Active Directory credentials

	@Test(priority = 16, groups = { "Sanity",
			"Regression" }, description = "Login24-Verify if validation message is displayed when user login to the application with invalid username Active Directory credentials")

	public void Login24() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login24-Verify if validation message is displayed when user login to the application with invalid username Active Directory credentials");

		SoftAssert sa = new SoftAssert();


		LoginPage.InvalidLogin("ABC", "Amphenol@123");
		sa.assertEquals(tu.get_popup_text(), "Invalid Credential, Please try again",
				"FAIL: Application allowed the dafult user when AD is connected");
		sa.assertAll();

	}

//Login25-Verify if validation message is displayed when user login to the application with invalid password Active Directory credentials

	@Test(priority = 17, groups = { "Sanity",
			"Regression" }, description = "Login25-Verify if validation message is displayed when user login to the application with invalid password Active Directory credentials")

	public void Login25() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login25-Verify if validation message is displayed when user login to the application with invalid password Active Directory credentials");

		SoftAssert sa = new SoftAssert();


		LoginPage.InvalidLogin("ajay2", "abc@123");

		sa.assertEquals(tu.get_popup_text(), "Invalid Credential, Please try again",
				"FAIL: Application allowed the dafult user when AD is connected");

		sa.assertAll();

	}

//Login26-Verify if validation message is displayed when user login to the application with locked Active Directory credentials

	@Test(priority = 18, groups = { "Sanity",
			"Regression" }, description = "Login26-Verify if validation message is displayed when user login to the application with locked Active Directory credentials")
	public void Login26() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"LLogin26-Verify if validation message is displayed when user login to the application with locked Active Directory credentials");

		SoftAssert sa = new SoftAssert();


		LoginPage.LockedLogin("kiranc", "Amphenol@123");

		sa.assertEquals(tu.get_popup_text(), "Invalid Credential, Please try again",
				"FAIL: Application allowed the dafult user when AD is connected");

		sa.assertAll();

	}

//Login27-Verify if validation message is displayed when deleted Active Directory user login to the application

	@Test(priority = 19, groups = { "Sanity",
			"Regression" }, description = "Login27-Verify if validation message is displayed when deleted Active Directory user login to the application")
	public void Login27() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login27-Verify if validation message is displayed when deleted Active Directory user login to the application");

		SoftAssert sa = new SoftAssert();


	
		LoginPage.DeletedLogin("ec", "Amphenol@123");

		sa.assertEquals(tu.get_popup_text(), "User must reset password.",
				"FAIL: Application allowed the dafult user when AD is connected");

		sa.assertAll();

	}

// Login30-Verify if validation message is displayed when Active Directory user login to the application where the Active Directory user available in the multiple groups
	@Test(priority = 20, groups = { "Sanity",
			"Regression" }, description = "Login30-Verify if validation message is displayed when Active Directory user login to the application where the Active Directory user available in the multiple groups")
	public void Login30() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login30-Verify if validation message is displayed when Active Directory user login to the application where the Active Directory user available in the multiple groups");

		SoftAssert sa = new SoftAssert();

		LoginPage.MultipleuserLogin("Deepika", "Amphenol@123");

		sa.assertEquals(tu.get_popup_text(), "Password expired, please contact administrator.",
				"FAIL: Application allowed the dafult user when AD is connected");

		sa.assertAll();

	}

// Login31-Verify if validation message is displayed when password expired when the user login to the application
	@Test(priority = 20, groups = { "Sanity",
			"Regression" }, description = "Login31-Verify if validation message is displayed when password expired when the user login to the application")
	public void Login31() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login31-Verify if validation message is displayed when password expired when the user login to the application");

		SoftAssert sa = new SoftAssert();
		System.out.println("Login30&Login31 throws the same error message. And both are similar");
		sa.assertAll();

	}
// Login33-Verify if application should automatically logoff after creating the first user with default SystemAdministrator Privileges

	@Test(priority = 21, groups = { "Sanity",
			"Regression" }, description = "Login33-Verify if application should automatically logoff after creating the first user with default SystemAdministrator Privileges")
	public void Login33() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login33-Verify if application should automatically logoff after creating the first user with default SystemAdministrator Privileges\r\n"
						+ "");

		SoftAssert sa = new SoftAssert();
		System.out.println("This Tc has been Covered in Before class");
		sa.assertAll();


	}

// Login34-Verify if application should automatically logoff after creating the first user with Modified SystemAdministrator Privileges

	@Test(priority = 22, groups = { "Sanity",
			"Regression" }, description = "Login34-Verify if application should automatically logoff after creating the first user with Modified SystemAdministrator Privileges")
	public void Login34() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Login34-Verify if application should automatically logoff after creating the first user with Modified SystemAdministrator Privileges"
						+ "");
        System.out.println("Initially we cant modify the system admin privilages. So this TC is not valid");
		

	}

}