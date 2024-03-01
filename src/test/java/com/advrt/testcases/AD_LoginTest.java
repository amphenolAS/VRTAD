/**
 * @author ruchika

 *
 */

package com.advrt.testcases;


import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
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
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.AuditPage;
import com.advrt.pages.AD_UMPage;
import com.advrt.pages.ADUM_page;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.utility.ADUserManagementUtility;
import com.advrt.utility.TestUtilities;


public class AD_LoginTest extends BaseClass{
	
	public AD_LoginTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

//com.advrt.testcases.AD_LoginTest#Login01
	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();
	
	//Initialization of pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage_Manual UserManagementPage_Manual;
	PoliciesPage PoliciesPage;
	ADUM_page ADUM_page;
	DefaultUserPrivilages_page DefaultUserPrivilages_page;
	AuditPage AuditPage;
	AD_UMPage AD_UMPage;
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_ADLoginTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD-Login Test in Progress..");
		

		/*
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
			
			PoliciesPage = UserManagementPage_Manual.Click_Policy();
			
			PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
			PoliciesPage.ActiveDirectoryUserLoginPopup("kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
			PoliciesPage.clickOn_ConnectBtn();
			PoliciesPage.ClickSaveButton();
			PoliciesPage.clickOn_AcceptBtn();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			tu.click_OK_popup();
		
			ADUM_page =	PoliciesPage.ClickUM_Tab_AD();
			Thread.sleep(1000);
			ADUM_page.select_grp("Automation");
			ADUM_page.enterNewUserTitle("Manager");
			ADUM_page.SelectUType("SystemAdministrator");
			Thread.sleep(1000);
			ADUM_page.ClickNewUserSaveButton();
			
			UserLoginPopup_UserCommentTextBox("1", "111111", "Admin");
			
			tu.click_OK_popup();
		
			tu.click_OK_popup();
			Thread.sleep(2000);*/
		
		}
	
	//After All the tests are conducted
	//@AfterTest
	@AfterClass
		public void endReport() throws InterruptedException {
			extent.flush();
			extent.close();
			System.out.println("AD-LOGIN  test completed");
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
	 * @throws AWTException 
	 * @throws IOException 
	*********/
	
	
	// Login01-Verify if System able to connect to the Domain with Active Directory Credentials

	@Test(priority=0,groups = { "Sanity",
			"Regression" }, description = "Login01-Verify if System able to connect to the Domain with Active Directory Credentials")

	public void Login01() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("Login01-Verify if System able to connect to the Domain with Active Directory Credentials");

		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.54");
		
		MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
		Thread.sleep(500);
		sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(),true);
		sa.assertAll();
		
		

	}

	
	// Login02-Verify if Kaye 411 User able to login to the system for the first
	// time

	@Test(priority=1,groups = { "Sanity",
			"Regression" }, description = "Login02-Verify if Kaye 411 User able to login to the system for the first time")

	public void Login02() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest("Login02-Verify if Kaye 411 User able to login to the system for the first time");

		SoftAssert sa = new SoftAssert();
		System.out.println("We have covered this tc in the before method of the class");

	}
	
	//Login03-Verify if Kaye 411 user able to login to the application when the Active Directory is activated and Manual Users and Active Directory users are not available in the application

		@Test(priority=2,groups = { "Sanity",
				"Regression" }, description = "Login03-Verify if Kaye 411 user able to login to the application when the Active Directory is activated and Manual Users and Active Directory users are not available in the application")

		public void Login03() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest("Login03-Verify if Kaye 411 user able to login to the application when the Active Directory is activated and Manual Users and Active Directory users are not available in the application");

			SoftAssert sa = new SoftAssert();
			System.out.println("We have covered this tc in the before method of the class");

		}
	
		
		// Login04-Verify if Active Directory user able to login to the application when
		// the Active Directory is activated and Manual Users and Active Directory users
		// are available in the application

		@Test(priority=3,groups = { "Sanity",
		"Regression" }, description = "Login04-Verify if Active Directory user able to login to the application when the Active Directory is activated and Manual Users and Active Directory users are available in the application")

public void Login04() throws InterruptedException, AWTException, IOException {
	extentTest = extent
			.startTest("Login04-Verify if Active Directory user able to login to the application when the Active Directory is activated and Manual Users and Active Directory users are available in the application");

	
	SoftAssert sa = new SoftAssert();
	sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.54");
	
	MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
	
	sa.assertEquals(MainHubPage.Is_mainHubPageTitle_Visible(),true);
	sa.assertAll();
	}
		
	//Login05-Verify if Manual user able to login to the application and Policies and User Management screen displays to create the Active Directory users when the Active Directory is activated and Manual Users available and Active Directory users are not available in the application	

		@Test(priority=4,groups = { "Sanity",
		"Regression" }, description = "Login05-Verify if Manual user able to login to the application and Policies and User Management screen displays to create the Active Directory users when the Active Directory is activated and Manual Users available and Active Directory users are not available in the application	\r\n"
				+ "")

public void Login05() throws InterruptedException, AWTException, IOException {
	extentTest = extent.startTest("Login05-Verify if Manual user able to login to the application and Policies and User Management screen displays to create the Active Directory users when the Active Directory is activated and Manual Users available and Active Directory users are not available in the application	\r\n"
			+ "");

	SoftAssert sa = new SoftAssert();
	System.out.println("We have covered this tc in the before method of the class");

}

// Login06-Verify if Active Directory user able to login to the application when
// the Active Directory is activated and Manual Users not available and Active
// Directory users are available in the application

@Test(priority=5,groups = { "Sanity",
		"Regression" }, description = "Login06-Verify if Active Directory user able to login to the application when the Active Directory is activated and Manual Users not available and Active Directory users are available in the application")

public void Login06() throws InterruptedException, AWTException, IOException {
	extentTest = extent.startTest(
			"Login06-Verify if Active Directory user able to login to the application when the Active Directory is activated and Manual Users not available and Active Directory users are available in the application");

	System.out.println("This tc is smiliar to Lgin04");

}



//Login08-Verify if Manual user able to login to the application when the Active Directory is not activated and Manual Users available and Active Directory users are not available in the application



@Test(priority=6,groups = { "Sanity",
		"Regression" }, description = "Login08-Verify if Manual user able to login to the application when the Active Directory is not activated and Manual Users available and Active Directory users are not available in the application")

public void Login08() throws InterruptedException, AWTException, IOException {
	extentTest = extent.startTest(
			"Login08-Verify if Manual user able to login to the application when the Active Directory is not activated and Manual Users available and Active Directory users are not available in the application");

	System.out.println("This tc already handeled in Before test class");

}

//Login10-Verify if Username displayed in the Login Screen when Active Directory Connected

@Test(priority=7,groups = { "Sanity",
		"Regression" }, description = "Login10-Verify if Username displayed in the Login Screen when Active Directory Connected")

public void Login10() throws InterruptedException, AWTException, IOException {
	extentTest = extent
			.startTest("Login10-Verify if Username displayed in the Login Screen when Active Directory Connected");

	SoftAssert sa = new SoftAssert();

	sa.assertEquals(LoginPage.UserIDFieldPresence(), true);
	LoginPage.EnterUserID("XXX");
	sa.assertEquals(LoginPage.GetTextUserIDField(), "XXX");
	sa.assertAll();

}

//Login11-Verify if Password displayed in the Login Screen when Active Directory Connected

@Test(priority=8,groups = { "Sanity",
		"Regression" }, description = "Login11-Verify if Password displayed in the Login Screen when Active Directory Connected")

public void Login11() throws InterruptedException, AWTException, IOException {
	extentTest = extent
			.startTest("Login11-Verify if Password displayed in the Login Screen when Active Directory Connected");

	SoftAssert sa = new SoftAssert();

	sa.assertEquals(LoginPage.UserPWFieldPresence(), true);
	
	sa.assertAll();

}

//Login12-Verify if Domain Name displayed in the Login Screen when Active Directory Connected

@Test(priority=9,groups = { "Sanity",
		"Regression" }, description = "Login12-Verify if Domain Name displayed in the Login Screen when Active Directory Connected")

public void Login12() throws InterruptedException, AWTException, IOException {
	extentTest = extent
			.startTest("Login12-Verify if Domain Name displayed in the Login Screen when Active Directory Connected");

	SoftAssert sa = new SoftAssert();

	sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.54");
	sa.assertAll();
}


//Login13-Verify if Domain Name should not be in editable mode in the Login Screen


@Test(priority=10,groups = { "Sanity",
		"Regression" }, description = "Login13-Verify if Domain Name should not be in editable mode in the Login Screen")

public void Login13() throws InterruptedException, AWTException, IOException {
	extentTest = extent.startTest("Login13-Verify if Domain Name should not be in editable mode in the Login Screen");

	SoftAssert sa = new SoftAssert();
	LoginPage.Clear_DomainName();
	
//Application will not allow to clear the text , hence it is non editable field 
//And after fetching it will give the same domain connected value
	
	sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.54");
	sa.assertAll();

}

//Login14-Verify if Domain Name value should be displayed by default where the domain previously connected


@Test(priority=11,groups = { "Sanity",
"Regression" }, description = "Login14-Verify if Domain Name value should be displayed by default where the domain previously connected")

public void Login14() throws InterruptedException, AWTException, IOException {
extentTest = extent.startTest("Login14-Verify if Domain Name value should be displayed by default where the domain previously connected");

SoftAssert sa = new SoftAssert();


sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.54");
sa.assertAll();

}


//Login15-Verify if System not allowed to logout from the application without Configuring the Active Directory user when Active Directory is activated


@Test(priority=12,groups = { "Sanity",
"Regression" }, description = "Login15-Verify if System not allowed to logout from the application without Configuring the Active Directory user when Active Directory is activated")

public void Login15() throws InterruptedException, AWTException, IOException {
extentTest = extent.startTest("Login15-Verify if System not allowed to logout from the application without Configuring the Active Directory user when Active Directory is activated");

SoftAssert sa = new SoftAssert();
MainHubPage = LoginPage.Login("kaverib","Amphenol@123");

ADUM_page = MainHubPage.ClickAdminTile_ADUM();
PoliciesPage = ADUM_page.ClickOn_PoliciesHeaderText();

System.out.println(PoliciesPage.get_connectionStatus());
sa.assertEquals(PoliciesPage.is_UpdateBtnvisible(), true);
//sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.54");

sa.assertAll();

}

//Login16-Verify if System not allowed to logout from the application without creating the user when Active Directory is not activated



@Test(priority=13,groups = { "Sanity",
		"Regression" }, description = "Login16-Verify if System not allowed to logout from the application without creating the user when Active Directory is not activated")

public void Login16() throws InterruptedException, AWTException, IOException {
	extentTest = extent.startTest("Login16-Verify if System not allowed to logout from the application without creating the user when Active Directory is not activated");


	System.out.println("Local user is already created in Before class and login to the application");
}


//Login17- Verify if Kaye-411 user not able to login to the application once Active Directory users/groups synced to the HMI

@Test(priority=14,groups = { "Sanity",
"Regression" }, description = "Login17- Verify if Kaye-411 user not able to login to the application once Active Directory users/groups synced to the HMI")

public void Login17() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login17- Verify if Kaye-411 user not able to login to the application once Active Directory users/groups synced to the HMI");


SoftAssert sa = new SoftAssert();

sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.54");

 LoginPage.InvalidLogin("Kaye","411");

sa.assertEquals(tu.get_popup_text(),"Invalid Credential, Please try again","FAIL: Application allowed the dafult user when AD is connected");


sa.assertAll();

}



//Login18-Verify if Kaye-411 user not able to login to the application once Local user created in the HMI


@Test(priority=15,groups = { "Sanity",
"Regression" }, description = "Login18-Verify if Kaye-411 user not able to login to the application once Local user created in the HMI")

public void Login18() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login18-Verify if Kaye-411 user not able to login to the application once Local user created in the HMI");

System.out.println("This test case is to Login17 ");
}



//Login24-Verify if validation message is displayed when user login to the application with invalid username Active Directory credentials



@Test(priority=16,groups = { "Sanity",
"Regression" }, description = "Login24-Verify if validation message is displayed when user login to the application with invalid username Active Directory credentials")

public void Login24() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login24-Verify if validation message is displayed when user login to the application with invalid username Active Directory credentials");


SoftAssert sa = new SoftAssert();

LoginPage.InvalidLogin("ABC","Amphenol@123");

sa.assertEquals(tu.get_popup_text(),"Invalid Credential, Please try again","FAIL: Application allowed the dafult user when AD is connected");


sa.assertAll();

}

//Login25-Verify if validation message is displayed when user login to the application with invalid password Active Directory credentials


@Test(priority=17,groups = { "Sanity",
"Regression" }, description = "Login25-Verify if validation message is displayed when user login to the application with invalid password Active Directory credentials")

public void Login25() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login25-Verify if validation message is displayed when user login to the application with invalid password Active Directory credentials");


SoftAssert sa = new SoftAssert();

 LoginPage.InvalidLogin("kaverib","abc@123");

sa.assertEquals(tu.get_popup_text(),"Invalid Credential, Please try again","FAIL: Application allowed the dafult user when AD is connected");


sa.assertAll();

}


//Login26-Verify if validation message is displayed when user login to the application with locked Active Directory credentials

@Test(priority=18,groups = { "Sanity",
"Regression" }, description = "Login26-Verify if validation message is displayed when user login to the application with locked Active Directory credentials")
public void Login26() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("LLogin26-Verify if validation message is displayed when user login to the application with locked Active Directory credentials");


SoftAssert sa = new SoftAssert();

 LoginPage.LockedLogin("Disabled","Amphenol@123");

sa.assertEquals(tu.get_popup_text(),"Invalid Credential, Please try again","FAIL: Application allowed the dafult user when AD is connected");

sa.assertAll();

}

//Login27-Verify if validation message is displayed when deleted Active Directory user login to the application

@Test(priority=19,groups = { "Sanity",
"Regression" }, description = "Login27-Verify if validation message is displayed when deleted Active Directory user login to the application")
public void Login27() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login27-Verify if validation message is displayed when deleted Active Directory user login to the application");

SoftAssert sa = new SoftAssert();

 LoginPage.DeletedLogin("ec","Amphenol@123");

sa.assertEquals(tu.get_popup_text(),"User must reset password.","FAIL: Application allowed the dafult user when AD is connected");

sa.assertAll();

}

// Login30-Verify if validation message is displayed when Active Directory user login to the application where the Active Directory user available in the multiple groups


@Test(priority=20,groups = { "Sanity",
"Regression" }, description = "Login30-Verify if validation message is displayed when Active Directory user login to the application where the Active Directory user available in the multiple groups")
public void Login30() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login30-Verify if validation message is displayed when Active Directory user login to the application where the Active Directory user available in the multiple groups");

SoftAssert sa = new SoftAssert();

 LoginPage.MultipleuserLogin("Ruchika1","Amphenol@123");

sa.assertEquals(tu.get_popup_text(),"Password expired, please contact administrator.","FAIL: Application allowed the dafult user when AD is connected");

sa.assertAll();

}

// Login33-Verify if application should automatically logoff after creating the first user with default SystemAdministrator Privileges

@Test(priority=21,groups = { "Sanity",
"Regression" }, description = "Login33-Verify if application should automatically logoff after creating the first user with default SystemAdministrator Privileges")
public void Login33() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login33-Verify if application should automatically logoff after creating the first user with default SystemAdministrator Privileges\r\n"
			+ "");

SoftAssert sa = new SoftAssert();

/*
MainHubPage =LoginPage.Login("Ruchika1","Amphenol@123");
AD_UMPage = MainHubPage.AD_ClickAdminTile_UMpage();
PoliciesPage=AD_UMPage.AD_Click_Policy();
PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
PoliciesPage.ClickSaveButton();
UserLoginPopup_UserCommentTextBox("Ruchika1","Amphenol@123","commit1");
tu.click_OK_popup();
try {
	tu.click_OK_popup();
} catch (Exception e) {
	e.printStackTrace();
}

Thread.sleep(1000);
LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
Thread.sleep(500);
LoginPage = new LoginPage();
MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
UserManagementPage_Manual = MainHubPage.ClickAdminTile_manualUM();

Thread.sleep(1000);

PoliciesPage = UserManagementPage_Manual.Click_Policy();

PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
PoliciesPage.ActiveDirectoryUserLoginPopup("kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
PoliciesPage.clickOn_ConnectBtn();
PoliciesPage.ClickSaveButton();
PoliciesPage.clickOn_AcceptBtn();
UserLoginPopup_UserCommentTextBox(getUID("adminFull"), getPW("adminFull"),"commit2");
tu.click_OK_popup();
try {
	tu.click_OK_popup();
} catch (Exception e) {
	e.printStackTrace();
}

LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
Thread.sleep(500);
LoginPage = new LoginPage();
MainHubPage =LoginPage.Login("Ruchika1","Amphenol@123");
ADUM_page=MainHubPage.ClickAdminTile_ADUM();
Thread.sleep(1000);
ADUM_page.select_grp("Automation");
ADUM_page.enterNewUserTitle("Manager");
//ADUM_page.SelectUType("SystemAdministrator");
AD_UMPage.select_UserType1("NewUserType");
DefaultUserPrivilages_page=AD_UMPage.newUserType("SystemAdministrator");
DefaultUserPrivilages_page.AllPrivilages();
Thread.sleep(1000);
DefaultUserPrivilages_page.NewSaveButton();
UserLoginPopup_UserCommentTextBox("Ruchika1","Amphenol@123","commit1");
ADUM_page.enterNewUserTitle("Manager");
ADUM_page.SelectUType("SystemAdministrator");
ADUM_page.ClickNewUserSaveButton();

UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "Admin");

tu.click_OK_popup();

sa.assertEquals(tu.get_popup_text(),"User Type Changed","FAIL: Application allowed the dafult user when AD is connected");
sa.assertEquals(LoginPage.Fetch_DomainNameTextbox(), "10.17.17.54");*/

System.out.println("This Tc has been Covered in Before class");

sa.assertAll();

}

// Login34-Verify if application should automatically logoff after creating the first user with Modified SystemAdministrator Privileges


@Test(priority=22,groups = { "Sanity",
"Regression" }, description = "Login33-Verify if application should automatically logoff after creating the first user with default SystemAdministrator Privileges")
public void Login34() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login33-Verify if application should automatically logoff after creating the first user with default SystemAdministrator Privileges\r\n"
			+ "");

SoftAssert sa = new SoftAssert();


System.out.println("This Tc has been Covered in Before class");


sa.assertAll();

}


//Login19-Verify if the Unassigned users able to login to the application as per the Guest login as supervisor

@Test(priority=23,groups = { "Sanity",
"Regression" }, description = "Login19-Verify if the Unassigned users able to login to the application as per the Guest login as supervisor")
public void Login19() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login19-Verify if the Unassigned users able to login to the application as per the Guest login as supervisor");

SoftAssert sa = new SoftAssert();


System.out.println("This Tc has been Covered in AllowGuestlogin TC-AD_GL05");


sa.assertAll();

}

//Login20-Verify if the Unassigned users able to login to the application as per the Guest login as Operator

@Test(priority=24,groups = { "Sanity",
"Regression" }, description = "Login20-Verify if the Unassigned users able to login to the application as per the Guest login as Operator")
public void Login20() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login20-Verify if the Unassigned users able to login to the application as per the Guest login as Operator");

SoftAssert sa = new SoftAssert();


System.out.println("This Tc has been Covered in AllowGuestlogin TC-AD_GL06");


sa.assertAll();

}

//Login21-Verify if the Unassigned users able to login to the application as per the Guest login as Administrator

@Test(priority=25,groups = { "Sanity",
"Regression" }, description = "Login21-Verify if the Unassigned users able to login to the application as per the Guest login as Administrator")
public void Login21() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login21-Verify if the Unassigned users able to login to the application as per the Guest login as Administrator");

SoftAssert sa = new SoftAssert();


System.out.println("This Tc has been Covered in AllowGuestlogin TC-AD_GL07");


sa.assertAll();

}


//Login22-Verify if the Unassigned users able to login to the application as per the Guest login as created New user type
@Test(priority=26,groups = { "Sanity",
"Regression" }, description = "Login22-Verify if the Unassigned users able to login to the application as per the Guest login as created New user type")
public void Login22() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login22-Verify if the Unassigned users able to login to the application as per the Guest login as created New user type");

SoftAssert sa = new SoftAssert();


System.out.println("This Tc has been Covered in AllowGuestlogin TC-AD_GL08");


sa.assertAll();

}

//Login23-Verify if validation message displayed when user trying to login to the application where the Domain user not available in Active Directory
@Test(priority=27,groups = { "Sanity",
"Regression" }, description = "Login23-Verify if validation message displayed when user trying to login to the application where the Domain user not available in Active Directory")
public void Login23() throws InterruptedException, AWTException, IOException {
extentTest = extent
	.startTest("Login23-Verify if validation message displayed when user trying to login to the application where the Domain user not available in Active Directory");

SoftAssert sa = new SoftAssert();


LoginPage.Login1("Kaveri","Amphenol@123");

sa.assertEquals(tu.get_popup_text(),"Invalid Credential, Please try again","FAIL: Application allowed the dafult user when AD is connected");

sa.assertAll();

}


}