package com.advrt.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.advrt.base.BaseClass;
import com.advrt.pages.ADUM_page;
import com.advrt.pages.AuditPage;
import com.advrt.pages.Copyassetpage;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.pages.EquipmentHubPage;
import com.advrt.pages.Equipment_IRTDDetailspage;
import com.advrt.pages.Equipment_IRTDHubPage;
import com.advrt.pages.FM_SyncInPage;
import com.advrt.pages.FM_SyncOutAssetListPage;
import com.advrt.pages.FM_SyncOutPage;
import com.advrt.pages.FileManagementPage;
import com.advrt.pages.LoginPage;
import com.advrt.pages.MainHubPage;
import com.advrt.pages.NewEquipmentCreation_Page;
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.SyncInAssetListPage;
import com.advrt.pages.UserManagementPage_Manual;
import com.advrt.pages.assetDetailsPage;
import com.advrt.pages.assetHubPage;
import com.advrt.pages.AD_UMPage;
import com.advrt.utility.TestUtilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AD_FM_Syncout_Audit_OperatorTest extends BaseClass{

	public AD_FM_Syncout_Audit_OperatorTest() throws IOException
	{
		super();
	}

	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();

	//Initialization of pages
	FM_SyncOutPage FM_SyncOutPage;
	Copyassetpage Copyassetpage;
	assetDetailsPage assetDetailsPage;
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage_Manual UserManagementPage_Manual;
	PoliciesPage PoliciesPage;
	ADUM_page ADUM_page;
	DefaultUserPrivilages_page DefaultUserPrivilages_page;
	AuditPage AuditPage;
	assetHubPage assetHubPage;
	FileManagementPage FileManagementPage;
	SyncInAssetListPage SyncInAssetListPage;
	FM_SyncInPage SyncInPage;
	EquipmentHubPage EquipmentHubPage;
	NewEquipmentCreation_Page NewEquipmentCreation_Page;
	Equipment_IRTDHubPage Equipment_IRTDHubPage;
	Equipment_IRTDDetailspage Equipment_IRTDDetailspage;
	FM_SyncOutAssetListPage FM_SyncOutAssetListPage;
	AD_UMPage AD_UMPage;

	static String AdmnUN = "User1";


	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"AD_FM_Syncout_Audit_OperatorTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "AD_FM_Syncout_Audit_OperatorTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD_FM_Syncout_Audit_OperatorTest in Progress..");


		// Rename the VRT Data Files folder if exists in order to make the system default
		/*	 renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service", "DataFiles");
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
			PoliciesPage.ActiveDirectoryUserLoginPopup("kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
			PoliciesPage.clickOn_ConnectBtn();
			PoliciesPage.ClickSaveButton();
			PoliciesPage.clickOn_AcceptBtn();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			tu.click_OK_popup();
			Thread.sleep(1000);
			ADUM_page =	PoliciesPage.ClickUM_Tab_AD();
			ADUM_page.select_grp("QA Testers");
			ADUM_page.enterNewUserTitle("Manager");
			ADUM_page.SelectUType("SystemAdministrator");
			Thread.sleep(1000);
			ADUM_page.ClickNewUserSaveButton();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			tu.click_OK_popup();
			tu.click_OK_popup();
			Thread.sleep(2000);
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
			AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();
			Thread.sleep(500);
			AD_UMPage.select_grp("Automation");
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("NewUserType");
			DefaultUserPrivilages_page=AD_UMPage.newUserType("Operator");
			DefaultUserPrivilages_page.Click_ManualSync();
			DefaultUserPrivilages_page.NewSaveButton();
			Thread.sleep(500);
			UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
			Thread.sleep(1000);
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("Operator");
			AD_UMPage.clickSavebtn();
			UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
			tu.click_OK_popup();

			 MainHubPage=AD_UMPage.click_BackBtn();
			LoginPage = MainHubPage.UserSignOut();
			tu.AppClose();
			Thread.sleep(2000); */

	}

	//After All the tests are conducted
	//@AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("AD_FM_Syncout_Audit_Operator test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kaverib","Amphenol@123");

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

	@Test

	public void AD_SYNCOUT_023() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest("AD_SYNCOUT_023 Verify the Audit trail entry  while login with the  Active Directory Operator  User");
		SoftAssert sa = new SoftAssert();

		AuditPage = MainHubPage.ClickAuditTitle();

		sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\",User Name : \"Kaveri Bedar\" Logged in to System.");

		sa.assertAll();

	}

	@Test

	public void AD_SYNCOUT_024() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest("AD_SYNCOUT_024 Verify the Audit trail entry while peforming SYNCOUT operation with the  Active Directory"
				+ " Operator  User");
		SoftAssert sa = new SoftAssert();

		//Conduct a Syncout operation

		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FM_SyncOutPage = FileManagementPage.ClickSyncOutBtn_SyncOutPage("kaverib","Amphenol@123","comm");
		FM_SyncOutPage.create_Foler(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Syncout");
		FM_SyncOutPage.enter_Filepath("Syncout");
		FM_SyncOutAssetListPage = FM_SyncOutPage.ClickSyncOutOkBtn1();
		FM_SyncOutAssetListPage.select_EquipmentChkBx();
		FM_SyncOutAssetListPage.Select_Users();
		FM_SyncOutAssetListPage.clickOk_OnSyncOutSelections();

		tu.click_YesBtn_popup();
		Thread.sleep(5000);
		tu.click_Close_alertmsg();
		MainHubPage = FileManagementPage.click_Backbtn();
		MainHubPage.ClickAuditTitle();
		MainHubPage.ClickAuditTitle();	
		AuditPage = MainHubPage.ClickAuditTitle();
		AuditPage.Click_ActionFilter_Icon();
		AuditPage.EnterTxt_ActionFilter("\"Audit\" is Synced Out.");
		AuditPage.click_Action_FilterBtn();
		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"\"Audit\" is Synced Out.");

		sa.assertAll();

	}
}
