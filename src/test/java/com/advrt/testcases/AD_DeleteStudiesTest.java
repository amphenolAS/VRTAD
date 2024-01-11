/**
 * @author KaveriB

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
import com.advrt.pages.assetHubPage;
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.FileManagementPage;
import com.advrt.pages.FM_SyncInPage;
import com.advrt.pages.SyncInAssetListPage;
import com.advrt.pages.AD_UMPage;
import com.advrt.pages.AuditPage;
import com.advrt.pages.assetHubPage;
import com.advrt.pages.assetDetailsPage;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.utility.TestUtilities;


public class AD_DeleteStudiesTest extends BaseClass{
	
	public AD_DeleteStudiesTest() throws IOException {
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
	FileManagementPage FileManagementPage;
	FM_SyncInPage SyncInPage;
	SyncInAssetListPage SyncInAssetListPage;
	AuditPage AuditPage;
	AD_UMPage AD_UMPage;
	assetHubPage assetHubPage;
	assetDetailsPage assetDetailsPage;
	DefaultUserPrivilages_page DefaultUserPrivilages_page;
	
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_ADDeleteStudiesTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("ADDeleteStudies Test in Progress..");
		

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
		//MainHubPage = UserManagementPage.ClickBackButn();
		
		PoliciesPage=UserManagementPage.Click_Policy();
		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		PoliciesPage.ClickSaveButton();
		PoliciesPage.clickOn_AcceptBtn();
		//tu.click_OK_popup();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();
        Thread.sleep(5000);
		AD_UMPage=PoliciesPage.click_AD_UMHeader();
		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("SystemAdministrator");
		AD_UMPage.clickSavebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();
		tu.click_OK_popup();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		
		//MainHubPage=AD_UMPage.click_BackBtn();
		/*
		//SyncIn
				FileManagementPage = MainHubPage.ClickFileManagementTitle();
				SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kiranc","Amphenol@123","usercommitted");
				Thread.sleep(500);
				SyncInPage.enter_Filepath("syncin");
				Thread.sleep(500);
				SyncInPage.click_FltrBtn();
				SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
				SyncInAssetListPage.click_EquipmentCheckBox();
				SyncInAssetListPage.click_SelectAllBtn();
				SyncInAssetListPage.click_OkBtn();
				SyncInAssetListPage.click_AlrtYesBtn();
				Thread.sleep(7000);
				SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
				Thread.sleep(2000);
				LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
				LoginPage = new LoginPage();
				MainHubPage = LoginPage.Login("kiranc","Amphenol@123");*/
				
				
		
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
			System.out.println("AD DeleteStudies test completed");
			Thread.sleep(500);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException, AWTException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		//SyncIn
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kiranc","Amphenol@123","usercommitted");
		Thread.sleep(500);
		SyncInPage.enter_Filepath("syncin");
		Thread.sleep(500);
		SyncInPage.click_FltrBtn();
		SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
		SyncInAssetListPage.click_EquipmentCheckBox();
		SyncInAssetListPage.click_SelectAllBtn();
		SyncInAssetListPage.click_OkBtn();
		SyncInAssetListPage.click_AlrtYesBtn();
		Thread.sleep(9000);
		SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
		Thread.sleep(5000);
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();
		Thread.sleep(500);
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
	
	//AD_Delete study_001-Verify the Audit trail entry  while login with the  Active Directory Admin  User

	
	@Test(priority=0,groups = { "Sanity",
			"Regression" }, description = "AD_Delete study_001-Verify the Audit trail entry  while login with the  Active Directory Admin  User")

	public void AD_Deletestudy_001() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("AD_Delete study_001-Verify the Audit trail entry  while login with the  Active Directory Admin  User");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=AD_UMPage.click_BackBtn();
		AuditPage=MainHubPage.ClickAuditTitle();
		
		
		String ActualMsg=AuditPage.get_auditEvent_text();
		String ExpectedMsg="User ID : \"kiranc\",User Name : \"kiran c\" Logged in to System.";
		
		sa.assertEquals(ActualMsg,ExpectedMsg,
				"Fail: There is NO Audit trail  entry  while login with the  Active Directory Admin  User");
		sa.assertAll();
}
	
	//AD_Delete study_002-Verify the Audit trail entry  when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Admin  User

	
		@Test(priority=1,groups = { "Sanity",
				"Regression" }, description = "AD_Delete study_002-Verify the Audit trail entry  when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Admin  User")

		public void AD_Deletestudy_002() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("AD_Delete study_002-Verify the Audit trail entry  when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Admin  User");
			SoftAssert sa = new SoftAssert();
			
			Thread.sleep(500);
			MainHubPage=AD_UMPage.click_BackBtn();
			assetHubPage=MainHubPage.Click_AssetTile2();
			assetDetailsPage	=assetHubPage.click_assetTile2("SyncInAsset");
			assetDetailsPage.click_QualTile();
			assetDetailsPage.Select_QualFile("manual 1 min sampling");
			Thread.sleep(1000);
			assetDetailsPage.click_DeleteQualificationButton();
			UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted");
			tu.click_YesBtn_popup();
			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();
			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);
			String ActualMsg = AuditPage.get_auditEvent_text();
			String ExpectedMsg ="Qualification Study : \"manual 1 min sampling\"  deleted by User ID : \"kiranc\", User Name : \"kiran c\"";
			
			sa.assertEquals(ActualMsg,ExpectedMsg,
					"Fail: No audit recorded in Audit trail when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Admin  User ");
			sa.assertAll();
	}
		
		
		
		//AD_Delete study_003-Verify the Audit trail entry  when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Supervisor  User
	
		@Test(priority=5,groups = { "Sanity",
				"Regression" }, description = "AD_Delete study_003-Verify the Audit trail entry  when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Supervisor  User")

		public void AD_Deletestudy_003() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("AD_Delete study_003-Verify the Audit trail entry  when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Supervisor  User");
			SoftAssert sa = new SoftAssert();
			
			AD_UMPage.select_grp("Automation");
			//AD_UMPage.Select_user();
			AD_UMPage.select_user(3);
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("NewUserType");
			DefaultUserPrivilages_page=AD_UMPage.newUserType("Supervisor");
			DefaultUserPrivilages_page.Click_DeleteStudyFiles_Reports();
			DefaultUserPrivilages_page.NewSaveButton();
			Thread.sleep(500);
			UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
			Thread.sleep(1000);
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("Supervisor");
			AD_UMPage.clickSavebtn();
			UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
			tu.click_OK_popup();
			MainHubPage=AD_UMPage.click_BackBtn();
			MainHubPage.UserSignOut();
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			assetHubPage=MainHubPage.Click_AssetTile2();
			assetDetailsPage	=assetHubPage.click_assetTile2("SyncInAsset");
			assetDetailsPage.click_QualTile();
			assetDetailsPage.Select_QualFile("manual 1 min sampling");
			Thread.sleep(1000);
			assetDetailsPage.click_DeleteQualificationButton();
			UserLoginPopup_UserCommentTextBox("kaverib","Amphenol@123","usercommitted");
			tu.click_YesBtn_popup();
			Thread.sleep(500);
			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();
			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);
			String ActualMsg = AuditPage.get_auditEvent_text();
			String ExpectedMsg ="Qualification Study : \"manual 1 min sampling\"  deleted by User ID : \"Kaverib\", User Name : \"Kaveri Bedar\"";
			
			sa.assertEquals(ActualMsg,ExpectedMsg,
					"Fail: No audit recorded in Audit trail when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Supervisor  User ");
			
			
			
			sa.assertAll();
	}

		
		
		//AD_Delete study_004-Verify the Audit trail entry  when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Operatore   User


		
		@Test(priority=6,groups = { "Sanity",
				"Regression" }, description = "AD_Delete study_004-Verify the Audit trail entry  when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Operatore   User")

		public void AD_Deletestudy_004() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("AD_Delete study_004-Verify the Audit trail entry  when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Operatore   User");
			SoftAssert sa = new SoftAssert();
			
			AD_UMPage.select_grp("Automation");
			//AD_UMPage.Select_user();
			AD_UMPage.select_user(3);
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("NewUserType");
			DefaultUserPrivilages_page=AD_UMPage.newUserType("Operator");
			DefaultUserPrivilages_page.Click_DeleteStudyFiles_Reports();
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
			MainHubPage.UserSignOut();
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			assetHubPage=MainHubPage.Click_AssetTile2();
			assetDetailsPage	=assetHubPage.click_assetTile2("SyncInAsset");
			assetDetailsPage.click_QualTile();
			assetDetailsPage.Select_QualFile("manual 1 min sampling");
			Thread.sleep(1000);
			assetDetailsPage.click_DeleteQualificationButton();
			UserLoginPopup_UserCommentTextBox("kaverib","Amphenol@123","usercommitted");
			tu.click_YesBtn_popup();
			Thread.sleep(500);
			assetHubPage=assetDetailsPage.ClickBackBtn();
			MainHubPage=assetHubPage.click_BackBtn();
			AuditPage=MainHubPage.ClickAuditTitle();
				
				
			String ActualMsg = AuditPage.get_auditEvent_text();
			String ExpectedMsg ="Qualification Study : \"manual 1 min sampling\"  deleted by User ID : \"Kaverib\", User Name : \"Kaveri Bedar\"";
			
			
			sa.assertEquals(ActualMsg,ExpectedMsg,
					"Fail: No audit recorded in Audit trail when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Admin  User ");
			sa.assertAll();	}
			
			
		
	
		
		
		//AD_Delete study_005-Verify if no Audit trail entry  is recorded when user is not having delete study privilegs   with the  Active Directory Admin  User
		
		@Test(priority=4,groups = { "Sanity",
				"Regression" }, description = "AD_Delete study_005-Verify if no Audit trail entry  is recorded when user is not having delete study privilegs   with the  Active Directory Admin  User")

		public void AD_Deletestudy_005() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("AD_Delete study_005-Verify if no Audit trail entry  is recorded when user is not having delete study privilegs   with the  Active Directory Admin  User");
			SoftAssert sa = new SoftAssert();
			
			
			
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("SystemAdministrator");
		DefaultUserPrivilages_page.Click_DeleteStudyFiles_Reports();
		DefaultUserPrivilages_page.NewSaveButton();
		Thread.sleep(500);
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("SystemAdministrator");
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		assetHubPage=MainHubPage.Click_AssetTile2();
		assetDetailsPage	=assetHubPage.click_assetTile2("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		Thread.sleep(1000);
		assetDetailsPage.click_DeleteQualificationButton();
		//UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted");
		
		assetHubPage=assetDetailsPage.ClickBackBtn();
		MainHubPage=assetHubPage.click_BackBtn();
		AuditPage=MainHubPage.ClickAuditTitle();
			
			
		String ActualMsg = AuditPage.get_auditEvent_text();
		String ExpectedMsg ="User ID : \"kiranc\", User Name : \"kiran c\"  has insufficient Privileges to perform Qualification study deletion  ";
		
		
		sa.assertEquals(ActualMsg,ExpectedMsg,
				"Fail: No audit recorded in Audit trail when Qual study is deleted from Qualification tile form asset screen  with the  Active Directory Admin  User ");
		sa.assertAll();	}
		
		
		//AD_Delete study_006-Verify if no Audit trail entry  is recorded when user is not having delete study privilegs   with the  Active Directory Supervisor  User
		//by defalut  supervisor is not having deletestudy privilages
		
		@Test(priority=2,groups = { "Sanity",
				"Regression" }, description = "AD_Delete study_006-Verify if no Audit trail entry  is recorded when user is not having delete study privilegs   with the  Active Directory Supervisor  User")

		public void AD_Deletestudy_006() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("AD_Delete study_006-Verify if no Audit trail entry  is recorded when user is not having delete study privilegs   with the  Active Directory Supervisor  User");
			SoftAssert sa = new SoftAssert();
			
			
			AD_UMPage.select_grp("Automation");
			AD_UMPage.select_user(3);
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("Supervisor");
			AD_UMPage.clickSavebtn();
			UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
			tu.click_OK_popup();
			Thread.sleep(1000);
			MainHubPage=AD_UMPage.click_BackBtn();
			MainHubPage.UserSignOut();
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			assetHubPage=MainHubPage.Click_AssetTile2();
			assetDetailsPage	=assetHubPage.click_assetTile2("SyncInAsset");
			assetDetailsPage.click_QualTile();
			assetDetailsPage.Select_QualFile("manual 1 min sampling");
			Thread.sleep(1000);
			assetDetailsPage.click_DeleteQualificationButton();
			
			assetHubPage=assetDetailsPage.ClickBackBtn();
			MainHubPage=assetHubPage.click_BackBtn();
			AuditPage=MainHubPage.ClickAuditTitle();
				
				
			String ActualMsg = AuditPage.get_auditEvent_text();
			String ExpectedMsg ="User ID : \"kaverib\", User Name : \"kaveri Bedar\"  has insufficient Privileges to perform Qualification study deletion  ";
			
			sa.assertAll();
	}
		
		
		//AD_Delete study_007-Verify if no Audit trail entry  is recorded when user is not having delete study privilegs   with the  Active Directory operatore  User
		
		@Test(priority=3,groups = { "Sanity",
				"Regression" }, description = "AD_Delete study_007-Verify if no Audit trail entry  is recorded when user is not having delete study privilegs   with the  Active Directory operatore  User")

		public void AD_Deletestudy_007() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("AD_Delete study_007-Verify if no Audit trail entry  is recorded when user is not having delete study privilegs   with the  Active Directory operatore  User");
			SoftAssert sa = new SoftAssert();
			
			AD_UMPage.select_grp("Automation");
			AD_UMPage.select_user(3);
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("Operator");
			AD_UMPage.clickSavebtn();
			UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
			tu.click_OK_popup();
			Thread.sleep(1000);
			MainHubPage=AD_UMPage.click_BackBtn();
			MainHubPage.UserSignOut();
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			assetHubPage=MainHubPage.Click_AssetTile2();
			assetDetailsPage	=assetHubPage.click_assetTile2("SyncInAsset");
			assetDetailsPage.click_QualTile();
			assetDetailsPage.Select_QualFile("manual 1 min sampling");
			Thread.sleep(1000);
			assetDetailsPage.click_DeleteQualificationButton();
			
			assetHubPage=assetDetailsPage.ClickBackBtn();
			MainHubPage=assetHubPage.click_BackBtn();
			AuditPage=MainHubPage.ClickAuditTitle();
				
				
			String ActualMsg = AuditPage.get_auditEvent_text();
			String ExpectedMsg ="User ID : \"kaverib\", User Name : \"kaveri Bedar\"  has insufficient Privileges to perform Qualification study deletion  ";
			
			sa.assertAll();
			
	}
 
}
