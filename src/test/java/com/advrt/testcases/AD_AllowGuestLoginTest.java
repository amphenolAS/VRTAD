/**
 * @author kaveriB

 *
 */

package com.advrt.testcases;


import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
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
import com.advrt.pages.UserManagementPage;//
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.PreferencesPage;
import com.advrt.pages.AD_UMPage;
import com.advrt.pages.AuditPage;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.utility.TestUtilities;


public class AD_AllowGuestLoginTest extends BaseClass{

	public AD_AllowGuestLoginTest() throws IOException {
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
	PreferencesPage PreferencesPage;
	DefaultUserPrivilages_page DefaultUserPrivilages_page;
	AuditPage AuditPage;
	
	static String AdmnUN = "User1";


	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_AD_AllowGuestLoginTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("ADGuestLogin Test in Progress..");

		

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
		//UserManagementPage.ClickNewUser();	
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
		System.out.println("ADGuestLogin test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		//MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		//AD_UMPage = LoginPage.ADLogin_UMpage(getUID("adminFull"), getPW("adminFull"));
		PoliciesPage	 = LoginPage.ADLogin_PPpage(getUID("adminFull"), getPW("adminFull"));
		//AD_UMPage = LoginPage.ADLogin_UMpage("kiranc", "Amphenol@123");
		//UserManagementPage = LoginPage.ADLogin_UMpage1(getUID("adminFull"), getPW("adminFull"));
		//AD_UMPage = MainHubPage.AD_ClickAdminTile_UMpage();
		Thread.sleep(1000);
		//PoliciesPage = AD_UMPage.Click_Policy();
	
		
		
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



	//ALG01-Verify if Allow Guset login should be disabled when there are no users in the application


	@Test(groups = { "Sanity",
	"Regression" }, description = "ALG01-Verify if Allow Guset login should be disabled when there are no users in the application")

	public void AD_GL01() throws InterruptedException {
		extentTest = extent
				.startTest("ALG01-Verify if Allow Guset login should be disabled when there are no users in the application");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(PoliciesPage.IsAllowGuestEnabled(), false,
				"Fail:Allowcheck check box is not enabled");

		sa.assertAll();
	}


	//ALG02-Verify if User type should be disabled when there are no users in the application

	@Test(groups = { "Sanity",
	"Regression" }, description = "ALG02-Verify if User type should be disabled when there are no users in the application")

	public void AD_GL02() throws InterruptedException {
		extentTest = extent
				.startTest("ALG02-Verify if User type should be disabled when there are no users in the application");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(PoliciesPage.IsGuestUsertypeEnabled(), false,
				"Fail:Allowcheck check box is not enabled");

		sa.assertAll();
	}

	//NA-ALG03-Verify if Allow Guest Login is enabled once user is configured into the HMI

	
	//ALG08-Verify if System Administrator able to activate the Allow Guest login as created New user type
		@Test(groups = { "Sanity",
		"Regression" }, description = "ALG08-Verify if System Administrator able to activate the Allow Guest login as created New user type")

		public void AD_GL08() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("ALG08-Verify if System Administrator able to activate the Allow Guest login as created New user type");
			SoftAssert sa = new SoftAssert();
			
			AD_UMPage=PoliciesPage.click_AD_UMHeader();
			//AD_UMPage.Select_grp();
			AD_UMPage.select_grp("QA Testers");
			//AD_UMPage.Select_user();
			AD_UMPage.select_user(1);
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("SystemAdministrator");
			AD_UMPage.clickSavebtn();
			UserLoginPopup_UserCommentTextBox(getUID("adminFull"), getPW("adminFull"),"NA");
			tu.click_OK_popup();
			tu.click_OK_popup();
			
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			Thread.sleep(500);
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
			AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();
			
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("NewUserType");
			DefaultUserPrivilages_page=AD_UMPage.newUserType("NUserType");
			DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
			DefaultUserPrivilages_page.Click_AuditCheckBox();
			DefaultUserPrivilages_page.NewSaveButton();
			Thread.sleep(500);
			UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
			//AD_UMPage.select_UserType1("NewUserType");//test
			//tu.click_OK_popup();
			
			Thread.sleep(1000);
			PoliciesPage=AD_UMPage.Click_Policy();
			Thread.sleep(1000);
			AD_UMPage=PoliciesPage.click_AD_UMHeader();
			Thread.sleep(1000);
			PoliciesPage=AD_UMPage.Click_Policy();
			PoliciesPage.click_on_AllowGuest();
			Thread.sleep(1000);
			PoliciesPage.selectGuestuser(4);
			PoliciesPage.ClickSaveButton();
			PoliciesPage.clickOn_AcceptBtn();
			Thread.sleep(1000);
			UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
			//AD_UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
			tu.click_OK_popup();
			MainHubPage=AD_UMPage.click_BackBtn();
			LoginPage=MainHubPage.UserSignOut();
			MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
			Thread.sleep(1000);
			AuditPage=MainHubPage.ClickAuditTitle();
			Thread.sleep(1000);
			String ActualMsg1=AuditPage.get_auditEvent_text();
			String ExpectedMsg1="User ID : \"kiranc1\",User Name : \"Guest\" Logged in to System.";

			sa.assertEquals(ActualMsg1,ExpectedMsg1,
					"Fail:Audit trial record does not exists change of user");
			sa.assertEquals(AuditPage.get_userName_text(),"Guest",
					"Fail:System Administrator NOT able to activate the Allow Guest login as Operator");


			sa.assertAll();
		}




	
		//ALG04-Verify if User type is enabled once Allow Guest Login Check box is checked
	//ALG05-Verify if System Administrator able to activate the Allow Guest login as supervisor
    //ALG06-Verify if System Administrator able to activate the Allow Guest login as Operator
    //ALG07-Verify if System Administrator able to activate the Allow Guest login as System Administrator
   //ALG09-Verify if Validation message should be displayed once clicking on the save button without selecting the User type value from the drop down"
//cases covered in AD_AllowGuestLoginTest1	
}