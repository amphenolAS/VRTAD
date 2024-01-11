
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
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.UserManagementPage;
import com.advrt.pages.ADUM_page;
import com.advrt.utility.TestUtilities;


public class AD_UMTest extends BaseClass{
	
	public AD_UMTest() throws IOException {
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
	UserManagementPage UserManagementPage;
	ADUM_page ADUM_page;
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_ADUMTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("ADUMTest Test in Progress..");
		

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
		Thread.sleep(1000);
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
		
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		PoliciesPage.ClickSaveButton();
		PoliciesPage.clickOn_AcceptBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();
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
			System.out.println("ADUM test completed");
			Thread.sleep(500);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		PoliciesPage = LoginPage.ADLogin_Policypage(getUID("adminFull"), getPW("adminFull"));
		//UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		//PoliciesPage = MainHubPage.ClickAdminTile_Polpage();
		ADUM_page =	PoliciesPage.ClickUM_Tab_AD();
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
	 * @throws AWTException 
	*********/
	
	//UM37-Verify if User should not be able to create new user type while creating the first user
	//UM52-Verify if Validation message should be displayed when there are no users in the userlist
	
	
 //UM20 - Verify if User Management screen displays New User creation when Active Directory is not activated	

	@Test(priority=0,groups = { "Sanity",
			"Regression" }, description = "UM20 - Verify if User Management screen displays New User creation when Active Directory is not activated")

	public void UM20() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"UM20 - Verify if User Management screen displays New User creation when Active Directory is not activated");

		SoftAssert sa = new SoftAssert();
		 
System.out.println("THIS TC has been covered in NON-AD VRT cases");
		
		sa.assertAll();

	}
	
	// UM21-Verify if System able to create the first manual user as System
	// Administrator
	// if Active Directory user as System Administrator exists in the application

	@Test(priority=1,groups = { "Sanity",
			"Regression" }, description = "UM21-Verify if System able to create the first manual user as System Administrator if Active Directory user as System Administrator exists in the application")

	public void UM21() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"UM21-Verify if System able to create the first manual user as System Administrator if Active Directory user as System Administrator exists in the application");

		SoftAssert sa = new SoftAssert();
		
		/*
		PoliciesPage = ADUM_page.ClickOn_PoliciesHeaderText();
		UserManagementPage_Manual = PoliciesPage.ClickUserManagement_TAB1();
		UserManagementPage_Manual.UMCreation_MandatoryFields("UserA", "11", "Abcde@123 ", "Manager", "SystemAdministrator");
		UserManagementPage_Manual.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		String alert = tu.get_popup_text();
		tu.click_OK_popup();
		sa.assertEquals(alert, "Data Saved Sussessfully", "Fail : MESSAGE IS NOT DISPLAYING");*/
		System.out.println("This TC has been covered in NON-AD VRT ");
		sa.assertAll();
		
	}
	
	// UM22-Verify if user able to create the user with the Operator Privileges

	@Test(priority=2,groups = { "Sanity",
	"Regression" }, description = "UM22-Verify if user able to create the user with the Operator Privileges ")

	public void UM22() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest("UM22-Verify if user able to create the user with the Operator Privileges ");

		SoftAssert sa = new SoftAssert();
/*
		PoliciesPage = ADUM_page.ClickOn_PoliciesHeaderText();
		UserManagementPage_Manual = PoliciesPage.ClickUserManagement_TAB1();
		UserManagementPage_Manual.UMCreation_MandatoryFields("Userope", "12", "Abcde@123 ", "Manager", "Operator");
		
		UserManagementPage_Manual.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		String alert = tu.get_popup_text();
		tu.click_OK_popup();
		sa.assertEquals(alert, "Data Saved Sussessfully", "Fail : MESSAGE IS NOT DISPLAYING");*/
		
		System.out.println("This TC has been covered in NON-AD VRT ");
		sa.assertAll();
		
	
	}
	
	
	
	//UM23-Verify if user able to create the user with the Supervisor Privileges 
	
	@Test(priority=3,groups = { "Sanity",
			"Regression" }, description = "UM23-Verify if user able to create the user with the Supervisor Privileges")

	public void UM23() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest("UM23-Verify if user able to create the user with the Supervisor Privileges");

		SoftAssert sa = new SoftAssert();

		/*
		PoliciesPage = ADUM_page.ClickOn_PoliciesHeaderText();
		UserManagementPage_Manual = PoliciesPage.ClickUserManagement_TAB1();
		UserManagementPage_Manual.UMCreation_MandatoryFields("Usersup", "13", "Abcde@123 ", "Manager", "Supervisor");
		UserManagementPage_Manual.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		String alert = tu.get_popup_text();
		tu.click_OK_popup();
		sa.assertEquals(alert, "Data Saved Sussessfully", "Fail : MESSAGE IS NOT DISPLAYING");*/
		System.out.println("This TC has been covered in NON-AD VRT ");
		sa.assertAll();
		
	}
	
	//UM24-Verify if user able to create the user with the System Administrator Privileges 
		
		@Test(priority=4,groups = { "Sanity",
				"Regression" }, description = "UM24-Verify if user able to create the user with the System Administrator Privileges")

		public void UM24() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest("UM24-Verify if user able to create the user with the System Administrator Privileges");

			SoftAssert sa = new SoftAssert();

			System.out.println(" UM24 is similar to the UM21");
			sa.assertAll();
		}
	
		
		
	//UM25-Verify if user able to create the user with the Create New User type Privileges 
		
		
	@Test(priority=5,groups = { "Sanity",
			"Regression" }, description = "UM25-Verify if user able to create the user with the Create New User type Privileges")

	public void UM25() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("UM25-Verify if user able to create the user with the Create New User type Privileges");

		SoftAssert sa = new SoftAssert();

		System.out.println("UM25 is similar to the UM21");
		sa.assertAll();
	}
		 
	
	
	




//UM01-Verify if User Management screen displays the Select Group when the Active Directory is activated

@Test(priority=6,groups = { "Sanity","Regression" }, description = "UM01-Verify if User Management screen displays the Select Group when the Active Directory is activated")

public void UM01() throws InterruptedException, AWTException {
	extentTest = extent.startTest("UM01-Verify if User Management screen displays the Select Group when the Active Directory is activated");
	SoftAssert sa = new SoftAssert();

	System.out.println("The Test Case covered in Before class");

}




//UM02-Verify if the different groups are displayed in the Select group dropdown

@Test(priority=7,groups = { "Sanity","Regression" }, description = "UM02-Verify if the different groups are displayed in the Select group dropdown")

public void UM02() throws InterruptedException, AWTException {
extentTest = extent.startTest("UM02-Verify if the different groups are displayed in the Select group dropdown");
SoftAssert sa = new SoftAssert();

ADUM_page.select_grp("QA Testers");
String Gname = ADUM_page.Fetch_Groupname();
sa.assertEquals(Gname, "QA Testers", "FAIL: Group Name is not available");

sa.assertAll();

}

//AD03-Verify if the users are displayed in the dropdown when the groups is selected from the user group

@Test(priority=8,groups = { "Sanity",
	"Regression" }, description = "UM03-Verify if the users are displayed in the dropdown when the groups is selected from the user group")

public void UM03() throws InterruptedException, AWTException {
extentTest = extent.startTest("UM03-Verify if the users are displayed in the dropdown when the groups is selected from the user group");
SoftAssert sa = new SoftAssert();

ADUM_page.select_grp("QA Testers");
ADUM_page.select_user(1);
sa.assertEquals(ADUM_page.Is_SelectUser_available(), true, "FAIL: select user option is not available");

sa.assertAll();
}

//UM04-Verify if System able to configure the first group as System Administrator if manual user as
//System Administrator exists in the application

@Test(groups = { "Sanity",
		"Regression" }, description = "UM04-Verify if System able to configure the first group as System Administrator if manual user as System Administrator exists in the application")

public void UM04() throws InterruptedException, AWTException, IOException {
	extentTest = extent.startTest(
			"UM04-Verify if System able to configure the first group as System Administrator if manual user as System Administrator exists in the application");

	SoftAssert sa = new SoftAssert();
	ADUM_page.select_grp("QA Testers");
	ADUM_page.select_user(1);
	ADUM_page.SelectUType("SystemAdministrator");
	ADUM_page.enterNewUserTitle("Manager");
	ADUM_page.ClickNewUserSaveButton();

	UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	tu.click_OK_popup();
	tu.click_OK_popup();
}


}