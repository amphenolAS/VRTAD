/**
 * @author kaveriB

 *
 */

package com.advrt.testcases;


import java.awt.AWTException;
import java.io.IOException;

import org.apache.tools.ant.Main;
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
import com.advrt.pages.UserManagementPage;
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.PreferencesPage;
import com.advrt.pages.AD_UMPage;
import com.advrt.pages.AuditPage;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.utility.TestUtilities;


public class AD_AllowGuestLoginTest1 extends BaseClass{

	public AD_AllowGuestLoginTest1() throws IOException {
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
	AuditPage AuditPage;
	DefaultUserPrivilages_page AD_NewUserPopup;

	static String AdmnUN = "User1";


	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_AD_AllowGuestLoginTest1"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("ADGuestLogin1 Test in Progress..");



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

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		PoliciesPage.ClickSaveButton();
		PoliciesPage.clickOn_AcceptBtn();
		tu.click_OK_popup();
		
		AD_UMPage=PoliciesPage.click_AD_UMHeader();
		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("SystemAdministrator");
		AD_UMPage.clickSavebtn();
		tu.click_OK_popup();

		AppClose();
		Thread.sleep(2000);

	}

	//After All the tests are conducted
	//@AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("ADGuestLogin1 test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		//--MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();
		Thread.sleep(1000);
		PoliciesPage = AD_UMPage.Click_Policy();
		PoliciesPage.click_on_AllowGuest();


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



	//ALG05-Verify if System Administrator able to activate the Allow Guest login as supervisor
	@Test(groups = { "Sanity",
	"Regression" }, description = "ALG05-Verify if System Administrator able to activate the Allow Guest login as supervisor")

	public void AD_GL05() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ALG05-Verify if System Administrator able to activate the Allow Guest login as supervisor");
		SoftAssert sa = new SoftAssert();

		//PoliciesPage.click_on_AllowGuest();
		PoliciesPage.selectGuestuser(2);
		PoliciesPage.ClickSaveButton();
		PoliciesPage.clickOn_AcceptBtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=PoliciesPage.click_BackBtn();
		//AuditPage=MainHubPage.ClickAuditTitle();
		//Thread.sleep(2000);
		//String ActualMsg=AuditPage.get_auditEvent_text();
		//MainHubPage= AuditPage.Click_BackBtn();
		LoginPage=MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		Thread.sleep(1000);
		AuditPage=MainHubPage.ClickAuditTitle();
		Thread.sleep(1000);
		String ActualMsg1=AuditPage.get_auditEvent_text(); 
		String ExpectedMsg1="User ID : \"kiranc1\",User Name : \"Guest\" Logged in to System.";

		//String ExpectedMsg="\"Active Directory Allow Guest Login UserType\" field modified and accepted from \"AllowGuestLoginisDisabled\" to \"Supervisor\"  by User ID : \"kiranc\", User Name : \"kiran c\"";

		//sa.assertEquals(ActualMsg,ExpectedMsg,
		//	"Fail:Audit trial record does not exists change of user");

		//sa.assertEquals(MainHubPage.UserNameText(),"Guest",
		//	"Fail:System Administrator NOT able to activate the Allow Guest login as supervisor");

		sa.assertEquals(ActualMsg1,ExpectedMsg1,
				"Fail:Audit trial record does not exists change of user");

		sa.assertEquals(AuditPage.get_userName_text(),"Guest",
				"Fail:System Administrator NOT able to activate the Allow Guest login as Operator");


		sa.assertAll();
	}






	//ALG06-Verify if System Administrator able to activate the Allow Guest login as Operator

	@Test(groups = { "Sanity",
	"Regression" }, description = "ALG06-Verify if System Administrator able to activate the Allow Guest login as Operator")

	public void AD_GL06() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ALG06-Verify if System Administrator able to activate the Allow Guest login as Operator");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.click_on_AllowGuest();
		//PoliciesPage.click_on_AllowGuest2();
		PoliciesPage.selectGuestuser(3);
		PoliciesPage.ClickSaveButton();
		PoliciesPage.clickOn_AcceptBtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=PoliciesPage.click_BackBtn();
		//-AuditPage=MainHubPage.ClickAuditTitle();
		//-Thread.sleep(2000);
		//String ActualMsg=AuditPage.get_auditEvent_text();
		//MainHubPage= AuditPage.Click_BackBtn();
		LoginPage=MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		Thread.sleep(1000);
		AuditPage=MainHubPage.ClickAuditTitle();
		Thread.sleep(1000);
		String ActualMsg1=AuditPage.get_auditEvent_text();

		//String ExpectedMsg="\"Active Directory Allow Guest Login UserType\" field modified and accepted from \"AllowGuestLoginisDisabled\" to \"Operator\"  by User ID : \"kiranc\", User Name : \"kiran c\"";

		String ExpectedMsg1="User ID : \"kiranc1\",User Name : \"Guest\" Logged in to System.";

		//sa.assertEquals(ActualMsg,ExpectedMsg,
		//	"Fail:Audit trial record does not exists change of user");

		sa.assertEquals(ActualMsg1,ExpectedMsg1,
				"Fail:Audit trial record does not exists change of user");

		//sa.assertEquals(MainHubPage.UserNameText(),"Guest",
		//	"Fail:System Administrator NOT able to activate the Allow Guest login as Operator");


		sa.assertEquals(AuditPage.get_userName_text(),"Guest",
				"Fail:System Administrator NOT able to activate the Allow Guest login as Operator");

		sa.assertAll();
	}




	//ALG07-Verify if System Administrator able to activate the Allow Guest login as System Administrator
	@Test(groups = { "Sanity",
	"Regression" }, description = "ALG07-Verify if System Administrator able to activate the Allow Guest login as System Administrator")

	public void AD_GL07() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ALG07-Verify if System Administrator able to activate the Allow Guest login as System Administrator");
		SoftAssert sa = new SoftAssert();

		PoliciesPage.click_on_AllowGuest();
		//PoliciesPage.click_on_AllowGuest2();
		PoliciesPage.selectGuestuser(1);
		PoliciesPage.ClickSaveButton();
		PoliciesPage.clickOn_AcceptBtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=PoliciesPage.click_BackBtn();
		//AuditPage=MainHubPage.ClickAuditTitle();
		//Thread.sleep(2000);
		//String ActualMsg=AuditPage.get_auditEvent_text();
		//MainHubPage= AuditPage.Click_BackBtn();
		LoginPage=MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		Thread.sleep(1000);
		AuditPage=MainHubPage.ClickAuditTitle();
		Thread.sleep(1000);
		String ActualMsg1=AuditPage.get_auditEvent_text();
		String ExpectedMsg1="User ID : \"kiranc1\",User Name : \"Guest\" Logged in to System.";


		// String ExpectedMsg="\"Active Directory Allow Guest Login UserType\" field modified and accepted from \"AllowGuestLoginisDisabled\" to \"System Administrator\"  by User ID : \"kiranc\", User Name : \"kiran c\"";

		//sa.assertEquals(ActualMsg,ExpectedMsg,
		//	"Fail:Audit trial record does not exists change of user");


		//sa.assertEquals(MainHubPage.UserNameText(),"Guest",
		//"Fail:System Administrator NOT able to activate the Allow Guest login as System Administrator");

		sa.assertEquals(ActualMsg1,ExpectedMsg1,
				"Fail:Audit trial record does not exists change of user");
		sa.assertEquals(AuditPage.get_userName_text(),"Guest",
				"Fail:System Administrator NOT able to activate the Allow Guest login as Operator");


		sa.assertAll();
	}



	
	@Test(groups = { "Sanity",
	"Regression" }, description = "ALG09-Verify if Validation message should be displayed once clicking on the save button without selecting the User type value from the drop down")

	public void AD_GL09() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("ALG09-Verify if Validation message should be displayed once clicking on the save button without selecting the User type value from the drop down");
		SoftAssert sa = new SoftAssert();

		System.out.println("Covered in ALG04");


		PoliciesPage.click_on_AllowGuest();
		PoliciesPage.ClickSaveButton();

		String ExpMsg="User Type Is Mandatory. So Please Select any User Type";
		String ActMsg=tu.get_popup_text();

		sa.assertEquals(ActMsg, ExpMsg,
				"Fail:Validation message is NOT displayed once clicking on the save button without selecting the User type value from the drop down");

		sa.assertAll();
	}
	
	
	
	//ALG04-Verify if User type is enabled once Allow Guest Login Check box is checked 
	
		@Test(groups = { "Sanity",
		"Regression" }, description = "ALG04-Verify if User type is enabled once Allow Guest Login Check box is checked")

		public void AD_GL04() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("ALG04-Verify if User type is enabled once Allow Guest Login Check box is checked");
			SoftAssert sa = new SoftAssert();

		
			sa.assertEquals(PoliciesPage.IsGuestUsertypeEnabled(), true,
					"Fail:Allowcheck check box is not enabled");

			

			sa.assertAll();
		}





}