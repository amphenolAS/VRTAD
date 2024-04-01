/**
 * @author kaveriB

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
import java.util.concurrent.TimeUnit;

import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import com.vrt.Listners.AllureReportListner;
import com.advrt.base.BaseClass;
import com.advrt.pages.LoginPage;
import com.advrt.pages.MainHubPage;
import com.advrt.pages.UserManagementPage_Manual;
import com.advrt.pages.VerificationPage;
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.SelectBaseStationPage;
import com.advrt.pages.SyncInAssetListPage;
import com.advrt.pages.AuditPage;
import com.advrt.pages.assetHubPage;
import com.advrt.pages.ADUM_page;
import com.advrt.pages.ADUM_page;
import com.advrt.pages.assetDetailsPage;
import com.advrt.pages.EquipmentHubPage;
import com.advrt.pages.NewEquipmentCreation_Page;
import com.advrt.pages.Copyassetpage;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.pages.Equipment_IRTDHubPage;
import com.advrt.pages.FM_SyncInPage;
import com.advrt.pages.Equipment_IRTDDetailspage;
import com.advrt.utility.ADUserManagementUtility;
import com.advrt.utility.TestUtilities;
import com.advrt.pages.FileManagementPage;
import com.advrt.pages.SelectBaseStationPage;
import com.advrt.pages.FM_SyncInPage;
import com.advrt.pages.SyncInAssetListPage;
import com.advrt.pages.AD_UMPage;
import com.advrt.pages.setup_verificationpage;
import com.advrt.pages.SelectLoggersPage;
import com.advrt.pages.SensorsInformationPage;
import com.advrt.pages.ProgramLoggersPage;
import com.advrt.pages.VerificationPage;



public class AD_Audit_TemperatureVerificationTest extends BaseClass{
	
	public AD_Audit_TemperatureVerificationTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	//com.advrt.testcases.AD_Audit_TemperatureVerificationTest#AD_Ver_Audit_001
	
	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();
	
	//Initialization of pages
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
	EquipmentHubPage EquipmentHubPage;
	NewEquipmentCreation_Page NewEquipmentCreation_Page;
	Equipment_IRTDHubPage Equipment_IRTDHubPage;
	Equipment_IRTDDetailspage Equipment_IRTDDetailspage;
	FM_SyncInPage SyncInPage;
	SyncInAssetListPage SyncInAssetListPage;
	SelectBaseStationPage SelectBaseStationPage;
	AD_UMPage AD_UMPage;
	setup_verificationpage setup_verificationpage;
	SelectLoggersPage SelectLoggersPage;
	SensorsInformationPage SensorsInformationPage;
	ProgramLoggersPage ProgramLoggersPage;
	VerificationPage VerificationPage;
	
	
	
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"AD_AuditVerificationTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD_AuditVerificationTest Test in Progress..");
		


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
			Thread.sleep(1000);
			PoliciesPage.clickOn_AcceptBtn();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
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
			Thread.sleep(500);
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();
			AD_UMPage.select_grp("Automation");
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("NewUserType");
			DefaultUserPrivilages_page=AD_UMPage.newUserType("SystemAdministrator");
			DefaultUserPrivilages_page.Click_RunVerification();
			DefaultUserPrivilages_page.Click_Create_SetUp();
			DefaultUserPrivilages_page.Click_RunQualification();
			DefaultUserPrivilages_page.NewSaveButton();
			Thread.sleep(500);
			UserLoginPopup_UserCommentTextBox("kaverib","Amphenol@123","usercommitted.");
			Thread.sleep(1000);
			AD_UMPage.select_UserTitle("Manager");
			ADUM_page.SelectUType("SystemAdministrator");
			Thread.sleep(1000);
			ADUM_page.ClickNewUserSaveButton();
			Thread.sleep(500);
			UserLoginPopup_UserCommentTextBox("kaverib","Amphenol@123","usercommitted.");
			Thread.sleep(1000);
			tu.click_OK_popup();
			MainHubPage=AD_UMPage.click_BackBtn();
			
			//SyncIn
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kaverib","Amphenol@123","usercommitted");
			Thread.sleep(500);
			SyncInPage.enter_Filepath("AD_FM");
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
			AppClose();
			Thread.sleep(2000);
			
		
		}
	
	//After All the tests are conducted
	//@AfterTest
	@AfterClass
		public void endReport() throws InterruptedException {
			extent.flush();
			extent.close();
			System.out.println("AD_AuditVerificationTest  test completed");
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
	 * @throws AWTException 
	 * @throws IOException 
	*********/
	
	
	//AD_Ver_Audit _001 Verify the Audit trail entry  when initated verification for Temperature Loggers from Equipment screen

	@Test(priority=0,description = "AD_Ver_Audit _001 Verify the Audit trail entry  when initated verification for Temperature Loggers from Equipment screen")

	public void AD_Ver_Audit_001() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("AD_Ver_Audit _001 Verify the Audit trail entry  when initated verification for Temperature Loggers from Equipment screen");
		
		SoftAssert sa = new SoftAssert();
		
		EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
		EquipmentHubPage.IntiQual_Btn();
		setup_verificationpage=EquipmentHubPage.loggerokbutton();
		Thread.sleep(500);
		EquipmentHubPage=setup_verificationpage.ClickBackBtn();
		MainHubPage=EquipmentHubPage.ClickBackBtn();
		AuditPage=MainHubPage.ClickAuditTitle();
		
		sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Initiate Verification\" operation in \"Equipment Details\" screen");
		sa.assertAll();
		
	}

	
	//AD_Ver_Audit _002 Verify the Audit trail entry  when saved the verification setup for Temperature Loggers
	
	@Test(priority=1,description = "AD_Ver_Audit _002 Verify the Audit trail entry  when saved the verification setup for Temperature Loggers")

	public void AD_Ver_Audit_002() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("AD_Ver_Audit _002 Verify the Audit trail entry  when saved the verification setup for Temperature Loggers");
		
		SoftAssert sa = new SoftAssert();
		
		EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
		EquipmentHubPage.IntiQual_Btn();
		setup_verificationpage=EquipmentHubPage.loggerokbutton();
		Thread.sleep(500);
		setup_verificationpage.CreateSetup();
		setup_verificationpage.ClicksaveBtn();
		setup_verificationpage.EntersetupName("T_Setup");
		EquipmentHubPage=setup_verificationpage.ClickBackBtn();
		MainHubPage=EquipmentHubPage.ClickBackBtn();
		AuditPage=MainHubPage.ClickAuditTitle();
		
		sa.assertEquals(AuditPage.get_auditEvent_text(),"Verification Setup : \"T_Setup\" is created by  User Id : \"Kaverib\" , User Name : \"Kaveri Bedar\"");
		sa.assertAll();
		
	}
	
	//AD_Ver_Audit _003 Verify the Audit trail entry  when deleted the verification setup for Temperature Loggers
	
	@Test(priority=2,description = "AD_Ver_Audit _003 Verify the Audit trail entry  when deleted the verification setup for Temperature Loggers")

	public void AD_Ver_Audit_003() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("AD_Ver_Audit _003 Verify the Audit trail entry  when deleted the verification setup for Temperature Loggers");
		
		SoftAssert sa = new SoftAssert();
		
		EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
		EquipmentHubPage.IntiQual_Btn();
		setup_verificationpage=EquipmentHubPage.loggerokbutton();
		Thread.sleep(500);
		setup_verificationpage.CreateSetup();
		setup_verificationpage.ClicksaveBtn();
		setup_verificationpage.EntersetupName("T_Setup1");
		setup_verificationpage.Click_SetupName("T_Setup1");
		setup_verificationpage.DeleteSetup();
		UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
		tu.click_YesBtn_popup();
		EquipmentHubPage=setup_verificationpage.ClickBackBtn();
		MainHubPage=EquipmentHubPage.ClickBackBtn();
		AuditPage=MainHubPage.ClickAuditTitle();
		
		sa.assertEquals(AuditPage.get_auditEvent_text(),"Verification Setup : \"T_Setup\" is deleted by  User Id : \"Kaverib\" , User Name : \"Kaveri Bedar\"");
		sa.assertAll();
		
	}
	
	
	//AD_Ver_Audit _004 Verify the Audit trail entry  when printed the verification setup report for Temperature Loggers
	
		@Test(priority=3,description = "AD_Ver_Audit _004 Verify the Audit trail entry  when printed the verification setup report for Temperature Loggers")

		public void AD_Ver_Audit_004() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _004 Verify the Audit trail entry  when printed the verification setup report for Temperature Loggers");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			setup_verificationpage.CreateSetup();
			setup_verificationpage.ClicksaveBtn();
			setup_verificationpage.EntersetupName("T_Setup2");
			setup_verificationpage.PrintSetup();
			UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
			Thread.sleep(4000);
			setup_verificationpage.click_PDFpopup_OkBtn();
			Thread.sleep(3000);
			tu.click_ALTf4_KeyStroke_ToCloseApp();
			Thread.sleep(2000);
			EquipmentHubPage=setup_verificationpage.ClickBackBtn();
			MainHubPage=EquipmentHubPage.ClickBackBtn();
			AuditPage=MainHubPage.ClickAuditTitle();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"verification setup print\" operation in \"verification setup\" screen");
			
			
			sa.assertAll();
			
		}
		
	
		//AD_Ver_Audit _005 Verify the Audit trail entry  when aborted the programming for Temperature Loggers
		
		@Test(priority=4,description = "AD_Ver_Audit _005 Verify the Audit trail entry  when aborted the programming for Temperature Loggers")

		public void AD_Ver_Audit_005() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _005 Verify the Audit trail entry  when aborted the programming for Temperature Loggers");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			setup_verificationpage.CreateSetup();
			setup_verificationpage.ClicksaveBtn();
			setup_verificationpage.EntersetupName("T_Setup3");
			SelectBaseStationPage=setup_verificationpage.ClicknextBtn();
			TimeUnit.SECONDS.sleep(30);
			SelectBaseStationPage.Click_DiscoverBS();
			TimeUnit.SECONDS.sleep(30);
			String BSIP="10.17.18.112";
			SelectBaseStationPage.Select_BSListbox("Ethernet IP-- " + BSIP);	
			SelectLoggersPage=SelectBaseStationPage.Click_ConnectBtn();
			TimeUnit. MINUTES. sleep(2);//need to wakeup/connect logger to bsestation
			SelectLoggersPage.Select_LogListbox("ZD82");
			Thread.sleep(2000);
			SensorsInformationPage=SelectLoggersPage.clickNext_SensorinfoBtn();
			//TimeUnit.SECONDS.sleep(30);
			ProgramLoggersPage=SensorsInformationPage.click_NextButton_withUnmappedSensors();
			TimeUnit.SECONDS.sleep(50);
			ProgramLoggersPage.clickAbortbtn();
			tu.click_YesBtn_popup();
			MainHubPage	=UserLoginPopup_UserCommentTextBox1("kaverib", "Amphenol@123", "committed");
			Thread.sleep(1000);
			AuditPage=MainHubPage.ClickAuditTitle();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(),"Logger Programming Aborted by  User ID : \"Kaverib\" , User Name : \"Kaveri Bedar\"");
			
			
			sa.assertAll();
			
		}
	
		
		
		//AD_Ver_Audit _006 Verify the Audit trail entry  when started the verification study for Temperature Loggers
		
		@Test(priority=5,description = "AD_Ver_Audit _006 Verify the Audit trail entry  when started the verification study for Temperature Loggers")

		public void AD_Ver_Audit_006() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _006 Verify the Audit trail entry  when started the verification study for Temperature Loggers");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			setup_verificationpage.CreateSetup();
			setup_verificationpage.ClicksaveBtn();
			setup_verificationpage.EntersetupName("T_Setup6");
			SelectBaseStationPage=setup_verificationpage.ClicknextBtn();
			TimeUnit.SECONDS.sleep(30);
			SelectBaseStationPage.Click_DiscoverBS();
			TimeUnit.SECONDS.sleep(30);
			String BSIP="10.17.18.112";
			SelectBaseStationPage.Select_BSListbox("Ethernet IP-- " + BSIP);	
			SelectLoggersPage=SelectBaseStationPage.Click_ConnectBtn();
			//TimeUnit. MINUTES. sleep(2);//need to wakeup/connect logger to bsestation
			SelectLoggersPage.Select_LogListbox("ZD82");
			Thread.sleep(2000);
			SensorsInformationPage=SelectLoggersPage.clickNext_SensorinfoBtn();
			//TimeUnit.SECONDS.sleep(30);
			ProgramLoggersPage=SensorsInformationPage.click_NextButton_withUnmappedSensors();
			TimeUnit.SECONDS.sleep(50);
			VerificationPage=ProgramLoggersPage.click_nextbtnV();
			VerificationPage.click_Start_verificationbtn();
			UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
			Thread.sleep(500);
			MainHubPage=VerificationPage.click_backbtn();
			Thread.sleep(500);
			AuditPage=MainHubPage.ClickAuditTitle();
			
sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Start Verification\" operation in \"Verification\" screen");



MainHubPage=AuditPage.Click_BackBtn();
SelectBaseStationPage=MainHubPage.Click_Discover();
TimeUnit.SECONDS.sleep(30);
SelectBaseStationPage.Click_DiscoverBS();
TimeUnit.SECONDS.sleep(30);
SelectBaseStationPage.Select_BSListbox("Ethernet IP-- " + BSIP);
SelectBaseStationPage.Click_BSsettingsBtn();
SelectBaseStationPage.Click_SetBSideal();
UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
TimeUnit.SECONDS.sleep(10);
MainHubPage=	SelectBaseStationPage.clickBackBtn();
MainHubPage.UserSignOut();

sa.assertAll();
		}
		
		
		//AD_Ver_Audit _007 Verify the Audit trail entry  when aborted the verification study for Temperature Loggers
		
		@Test(priority=6,description = "AD_Ver_Audit _007 Verify the Audit trail entry  when aborted the verification study for Temperature Loggers")

		public void AD_Ver_Audit_007() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _007 Verify the Audit trail entry  when aborted the verification study for Temperature Loggers");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			setup_verificationpage.CreateSetup();
			setup_verificationpage.ClicksaveBtn();
			setup_verificationpage.EntersetupName("T_Setup8");
			SelectBaseStationPage=setup_verificationpage.ClicknextBtn();
			TimeUnit.SECONDS.sleep(30);
			SelectBaseStationPage.Click_DiscoverBS();
			TimeUnit.SECONDS.sleep(30);
			String BSIP="10.17.18.112";
			SelectBaseStationPage.Select_BSListbox("Ethernet IP-- " + BSIP);	
			SelectLoggersPage=SelectBaseStationPage.Click_ConnectBtn();
			//TimeUnit. MINUTES. sleep(2);//need to wakeup/connect logger to bsestation
			SelectLoggersPage.Select_LogListbox("ZD82");
			Thread.sleep(2000);
			SensorsInformationPage=SelectLoggersPage.clickNext_SensorinfoBtn();
			//TimeUnit.SECONDS.sleep(30);
			ProgramLoggersPage=SensorsInformationPage.click_NextButton_withUnmappedSensors();
			TimeUnit.SECONDS.sleep(50);
			VerificationPage=ProgramLoggersPage.click_nextbtnV();
			VerificationPage.click_Abort_verificationbtn();
			  //String date1=tu.CurrentDatenTime_certainformat(); System.out.println(date1);		
			UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
			MainHubPage	=VerificationPage.click_YesBtn_popup();
			//Thread.sleep(500);
			 String date2=tu.CurrentDatenTime_certainformat();
			 //System.out.println(date2);
			Thread.sleep(1000);
			AuditPage=MainHubPage.ClickAuditTitle();
		   
	      	//System.out.println(date);
			sa.assertTrue(AuditPage.get_auditEvent_text().contains("Verification aborted by User: Kaveri Bedar, ID: Kaverib at "+ date2));
			
			
			sa.assertAll();
		}
		
		
		
		/*
		
		//AD_Ver_Audit _009 Verify the Audit trail entry  when initated verification for Humidity Loggers-Temperature Sensors from Equipment screen
		
		@Test(priority=7,description = "AD_Ver_Audit _009 Verify the Audit trail entry  when initated verification for Humidity Loggers-Temperature Sensors from Equipment screen")

		public void AD_Ver_Audit_009() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _009 Verify the Audit trail entry  when initated verification for Humidity Loggers-Temperature Sensors from Equipment screen");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			EquipmentHubPage.clickon_Humidityradiobtn();
			EquipmentHubPage.clickon_HumidityTempbtn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			EquipmentHubPage=setup_verificationpage.ClickBackBtn();
			MainHubPage=EquipmentHubPage.ClickBackBtn();
			AuditPage=MainHubPage.ClickAuditTitle();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Initiate Verification\" operation in \"Equipment Details\" screen");
			sa.assertAll();
			
		}
		
		
		//AD_Ver_Audit _010 Verify the Audit trail entry  when saved the verification setup for Humidity Loggers-Temperature Sensors
		
		@Test(priority=8,description = "AD_Ver_Audit _010 Verify the Audit trail entry  when started the verification setup for Humidity Loggers-Temperature Sensors")

		public void AD_Ver_Audit_010() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _010 Verify the Audit trail entry  when started the verification setup for Humidity Loggers-Temperature Sensors");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			EquipmentHubPage.clickon_Humidityradiobtn();
			EquipmentHubPage.clickon_HumidityTempbtn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			setup_verificationpage.CreateSetup();
			setup_verificationpage.ClicksaveBtn();
			setup_verificationpage.EntersetupName("H_Setup");
			EquipmentHubPage=setup_verificationpage.ClickBackBtn();
			MainHubPage=EquipmentHubPage.ClickBackBtn();
			AuditPage=MainHubPage.ClickAuditTitle();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(),"Verification Setup : \"H_Setup\" is created by  User Id : \"Kaverib\" , User Name : \"Kaveri Bedar\"");
			sa.assertAll();
			
		}
		
		
		
	//	AD_Ver_Audit _011 Verify the Audit trail entry  when deleted the verification setup for Humidity Loggers-Temperature Sensors

		@Test(priority=9,description = "AD_Ver_Audit _011 Verify the Audit trail entry  when deleted the verification setup for Humidity Loggers-Temperature Sensors")

		public void AD_Ver_Audit_011() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _011 Verify the Audit trail entry  when deleted the verification setup for Humidity Loggers-Temperature Sensors");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			EquipmentHubPage.clickon_Humidityradiobtn();
			EquipmentHubPage.clickon_HumidityTempbtn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			setup_verificationpage.CreateSetup();
			setup_verificationpage.ClicksaveBtn();
			setup_verificationpage.EntersetupName("H_Setup1");
			setup_verificationpage.Click_SetupName("H_Setup1");
			setup_verificationpage.DeleteSetup();
			UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
			tu.click_YesBtn_popup();
			EquipmentHubPage=setup_verificationpage.ClickBackBtn();
			MainHubPage=EquipmentHubPage.ClickBackBtn();
			AuditPage=MainHubPage.ClickAuditTitle();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(),"Verification Setup : \"H_Setup\" is deleted by  User Id : \"Kaverib\" , User Name : \"Kaveri Bedar\"");
			sa.assertAll();
			
		}
		
		
		//AD_Ver_Audit _012 Verify the Audit trail entry  when printed the verification setup report for Humidity Loggers-Temperature Sensors
		
		@Test(priority=10,description = "AD_Ver_Audit _012 Verify the Audit trail entry  when printed the verification setup report for Humidity Loggers-Temperature Sensors")

		public void AD_Ver_Audit_012() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _012 Verify the Audit trail entry  when printed the verification setup report for Humidity Loggers-Temperature Sensors");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			EquipmentHubPage.clickon_Humidityradiobtn();
			EquipmentHubPage.clickon_HumidityTempbtn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			setup_verificationpage.CreateSetup();
			setup_verificationpage.ClicksaveBtn();
			setup_verificationpage.EntersetupName("H_Setup2");
			setup_verificationpage.PrintSetup();
			UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
			Thread.sleep(4000);
			setup_verificationpage.click_PDFpopup_OkBtn();
			Thread.sleep(3000);
			tu.click_ALTf4_KeyStroke_ToCloseApp();
			Thread.sleep(2000);
			EquipmentHubPage=setup_verificationpage.ClickBackBtn();
			MainHubPage=EquipmentHubPage.ClickBackBtn();
			AuditPage=MainHubPage.ClickAuditTitle();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"verification setup print\" operation in \"verification setup\" screen");
			sa.assertAll();	
		}
		
		
		
		//AD_Ver_Audit _013 Verify the Audit trail entry  when started the verification study for Humidity Loggers-Temperature Sensors
		
		 @Test(priority=11,description = "AD_Ver_Audit _013 Verify the Audit trail entry  when started the verification study for Humidity Loggers-Temperature Sensor")

		public void AD_Ver_Audit_013() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _013 Verify the Audit trail entry  when started the verification study for Humidity Loggers-Temperature Sensor");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			EquipmentHubPage.clickon_Humidityradiobtn();
			EquipmentHubPage.clickon_HumidityTempbtn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			setup_verificationpage.CreateSetup();
			setup_verificationpage.ClicksaveBtn();
			setup_verificationpage.EntersetupName("T_Setup6");
			SelectBaseStationPage=setup_verificationpage.ClicknextBtn();
			TimeUnit.SECONDS.sleep(30);
			SelectBaseStationPage.Click_DiscoverBS();
			TimeUnit.SECONDS.sleep(30);
			String BSIP="10.17.18.112";
			SelectBaseStationPage.Select_BSListbox("Ethernet IP-- " + BSIP);	
			SelectLoggersPage=SelectBaseStationPage.Click_ConnectBtn();
			//TimeUnit. MINUTES. sleep(2);//need to wakeup/connect logger to bsestation
			SelectLoggersPage.Select_LogListbox("TW74");
			Thread.sleep(2000);
			SensorsInformationPage=SelectLoggersPage.clickNext_SensorinfoBtn();
			TimeUnit.SECONDS.sleep(30);
			ProgramLoggersPage=SensorsInformationPage.click_NextButton_withUnmappedSensors();
			TimeUnit.SECONDS.sleep(50);
			VerificationPage=ProgramLoggersPage.click_nextbtnV();
			VerificationPage.click_Start_verificationbtn();
			UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
			Thread.sleep(500);
			MainHubPage=VerificationPage.click_backbtn();
			Thread.sleep(500);
			AuditPage=MainHubPage.ClickAuditTitle();
			
sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Start Verification\" operation in \"Verification\" screen");



MainHubPage=AuditPage.Click_BackBtn();
SelectBaseStationPage=MainHubPage.Click_Discover();
TimeUnit.SECONDS.sleep(30);
SelectBaseStationPage.Click_DiscoverBS();
TimeUnit.SECONDS.sleep(30);
SelectBaseStationPage.Select_BSListbox("Ethernet IP-- " + BSIP);
SelectBaseStationPage.Click_BSsettingsBtn();
SelectBaseStationPage.Click_SetBSideal();
UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
TimeUnit.SECONDS.sleep(10);
MainHubPage=	SelectBaseStationPage.clickBackBtn();
MainHubPage.UserSignOut();


sa.assertAll();
		}
		
		//AD_Ver_Audit _014 Verify the Audit trail entry  when aborted the verification study for Humidity Loggers-Temperature Sensors
		
		@Test(priority=12,description = "AD_Ver_Audit _014 Verify the Audit trail entry  when aborted the verification study for Humidity Loggers-Temperature Sensors")
		public void AD_Ver_Audit_014() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _014 Verify the Audit trail entry  when aborted the verification study for Humidity Loggers-Temperature Sensors");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			EquipmentHubPage.clickon_Humidityradiobtn();
			EquipmentHubPage.clickon_HumidityTempbtn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			setup_verificationpage.CreateSetup();
			setup_verificationpage.ClicksaveBtn();
			setup_verificationpage.EntersetupName("H_Setup7");
			SelectBaseStationPage=setup_verificationpage.ClicknextBtn();
			TimeUnit.SECONDS.sleep(30);
			SelectBaseStationPage.Click_DiscoverBS();
			TimeUnit.SECONDS.sleep(30);
			String BSIP="10.17.18.112";
			SelectBaseStationPage.Select_BSListbox("Ethernet IP-- " + BSIP);	
			SelectLoggersPage=SelectBaseStationPage.Click_ConnectBtn();
			//TimeUnit. MINUTES. sleep(2);//need to wakeup/connect logger to bsestation
			SelectLoggersPage.Select_LogListbox("TW74");
			Thread.sleep(2000);
			SensorsInformationPage=SelectLoggersPage.clickNext_SensorinfoBtn();
			TimeUnit.SECONDS.sleep(30);
			ProgramLoggersPage=SensorsInformationPage.click_NextButton_withUnmappedSensors();
			TimeUnit.SECONDS.sleep(50);
			VerificationPage=ProgramLoggersPage.click_nextbtnV();
			VerificationPage.click_Abort_verificationbtn();
			
			  String date1=tu.CurrentDatenTime_certainformat(); System.out.println(date1);
			 			
			UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
			MainHubPage	=VerificationPage.click_YesBtn_popup();
			Thread.sleep(500);
			 String date2=tu.CurrentDatenTime_certainformat();
			 System.out.println(date2);
			Thread.sleep(1000);
			AuditPage=MainHubPage.ClickAuditTitle();
		   
	      	//System.out.println(date);
			sa.assertEquals(AuditPage.get_auditEvent_text(),"Verification aborted by User: Kaveri Bedar, ID: Kaverib at "+ date2);
			
			
			sa.assertAll();
		}

		
		/*
		//AD_Ver_Audit _016 Verify the Audit trail entry  when initated verification for Humidity Loggers-Humidity Sensors from Equipment screen

		@Test(priority=13,description = "AD_Ver_Audit _016 Verify the Audit trail entry  when initated verification for Humidity Loggers-Humidity Sensors from Equipment screen")

		public void AD_Ver_Audit_016() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Ver_Audit _016 Verify the Audit trail entry  when initated verification for Humidity Loggers-Humidity Sensors from Equipment screen");
			
			SoftAssert sa = new SoftAssert();
			
			EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.IntiQual_Btn();
			EquipmentHubPage.clickon_Humidityradiobtn();
			setup_verificationpage=EquipmentHubPage.loggerokbutton();
			Thread.sleep(500);
			EquipmentHubPage=setup_verificationpage.ClickBackBtn();
			MainHubPage=EquipmentHubPage.ClickBackBtn();
			AuditPage=MainHubPage.ClickAuditTitle();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Initiate Verification\" operation in \"Equipment Details\" screen");
			sa.assertAll();
			
		}
		
			
		//AD_Ver_Audit _017 Verify the Audit trail entry  when started the verification setup for Humidity Loggers-Humidity Sensors
		
		@Test(priority=14,description = "AD_Ver_Audit _017 Verify the Audit trail entry  when started the verification setup for Humidity Loggers-Humidity Sensors")

				public void AD_Ver_Audit_017() throws InterruptedException, AWTException, IOException {
					extentTest = extent
							.startTest("AD_Ver_Audit _017 Verify the Audit trail entry  when started the verification setup for Humidity Loggers-Humidity Sensors");
					
					SoftAssert sa = new SoftAssert();
					
					EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
					EquipmentHubPage.IntiQual_Btn();
					EquipmentHubPage.clickon_Humidityradiobtn();
					setup_verificationpage=EquipmentHubPage.loggerokbutton();
					Thread.sleep(500);
					setup_verificationpage.CreateSetup();
					setup_verificationpage.ClicksaveBtn();
					setup_verificationpage.EntersetupName("H_Setup");
					EquipmentHubPage=setup_verificationpage.ClickBackBtn();
					MainHubPage=EquipmentHubPage.ClickBackBtn();
					AuditPage=MainHubPage.ClickAuditTitle();
					
					sa.assertEquals(AuditPage.get_auditEvent_text(),"Verification Setup : \"H_Setup\" is created by  User Id : \"Kaverib\" , User Name : \"Kaveri Bedar\"");
					sa.assertAll();
					
				}
				
				
				
			//	//AD_Ver_Audit _018 Verify the Audit trail entry  when deleted the verification setup for Humidity Loggers-Humidity Sensors
		
				@Test(priority=15,description = "AD_Ver_Audit _018 Verify the Audit trail entry  when deleted the verification setup for Humidity Loggers-Humidity Sensors\r\n"
						+ "		")

				public void AD_Ver_Audit_018() throws InterruptedException, AWTException, IOException {
					extentTest = extent
							.startTest("AD_Ver_Audit _018 Verify the Audit trail entry  when deleted the verification setup for Humidity Loggers-Humidity Sensors\r\n"
									+ "		");
					
					SoftAssert sa = new SoftAssert();
					
					EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
					EquipmentHubPage.IntiQual_Btn();
					EquipmentHubPage.clickon_Humidityradiobtn();
					setup_verificationpage=EquipmentHubPage.loggerokbutton();
					Thread.sleep(500);
					setup_verificationpage.CreateSetup();
					setup_verificationpage.ClicksaveBtn();
					setup_verificationpage.EntersetupName("H_Setup1");
					setup_verificationpage.Click_SetupName("H_Setup1");
					setup_verificationpage.DeleteSetup();
					UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
					tu.click_YesBtn_popup();
					EquipmentHubPage=setup_verificationpage.ClickBackBtn();
					MainHubPage=EquipmentHubPage.ClickBackBtn();
					AuditPage=MainHubPage.ClickAuditTitle();
					
					sa.assertEquals(AuditPage.get_auditEvent_text(),"Verification Setup : \"H_Setup\" is deleted by  User Id : \"Kaverib\" , User Name : \"Kaveri Bedar\"");
					sa.assertAll();
					
				}
				
				
				//AD_Ver_Audit _019 Verify the Audit trail entry  when printed the verification setup report for Humidity Loggers-Humidity Sensors
				
				@Test(priority=16,description = "AD_Ver_Audit _019 Verify the Audit trail entry  when printed the verification setup report for Humidity Loggers-Humidity Sensors")

				public void AD_Ver_Audit_019() throws InterruptedException, AWTException, IOException {
					extentTest = extent
							.startTest("AD_Ver_Audit _019 Verify the Audit trail entry  when printed the verification setup report for Humidity Loggers-Humidity Sensors");
					
					SoftAssert sa = new SoftAssert();
					
					EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
					EquipmentHubPage.IntiQual_Btn();
					EquipmentHubPage.clickon_Humidityradiobtn();
					setup_verificationpage=EquipmentHubPage.loggerokbutton();
					Thread.sleep(500);
					setup_verificationpage.CreateSetup();
					setup_verificationpage.ClicksaveBtn();
					setup_verificationpage.EntersetupName("H_Setup2");
					setup_verificationpage.PrintSetup();
					UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
					Thread.sleep(4000);
					setup_verificationpage.click_PDFpopup_OkBtn();
					Thread.sleep(3000);
					tu.click_ALTf4_KeyStroke_ToCloseApp();
					Thread.sleep(2000);
					EquipmentHubPage=setup_verificationpage.ClickBackBtn();
					MainHubPage=EquipmentHubPage.ClickBackBtn();
					AuditPage=MainHubPage.ClickAuditTitle();
					
					sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"verification setup print\" operation in \"verification setup\" screen");
					sa.assertAll();	
				}
				
				
				
				//AD_Ver_Audit _020 Verify the Audit trail entry  when started the verification study for Humidity Loggers-Humidity Sensors
				
				
				 @Test(priority=17,description = "AD_Ver_Audit _020 Verify the Audit trail entry  when started the verification study for Humidity Loggers-Humidity Sensors")

				public void AD_Ver_Audit_020() throws InterruptedException, AWTException, IOException {
					extentTest = extent
							.startTest("AD_Ver_Audit _020 Verify the Audit trail entry  when started the verification study for Humidity Loggers-Humidity Sensors");
					
					SoftAssert sa = new SoftAssert();
					
					EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
					EquipmentHubPage.IntiQual_Btn();
					EquipmentHubPage.clickon_Humidityradiobtn();
					setup_verificationpage=EquipmentHubPage.loggerokbutton();
					Thread.sleep(500);
					setup_verificationpage.CreateSetup();
					setup_verificationpage.ClicksaveBtn();
					setup_verificationpage.EntersetupName("T_Setup6");
					SelectBaseStationPage=setup_verificationpage.ClicknextBtn();
					TimeUnit.SECONDS.sleep(30);
					SelectBaseStationPage.Click_DiscoverBS();
					TimeUnit.SECONDS.sleep(30);
					String BSIP="10.17.18.112";
					SelectBaseStationPage.Select_BSListbox("Ethernet IP-- " + BSIP);	
					SelectLoggersPage=SelectBaseStationPage.Click_ConnectBtn();
					TimeUnit. MINUTES. sleep(2);//need to wakeup/connect logger to bsestation
					SelectLoggersPage.Select_LogListbox("TW74");
					Thread.sleep(2000);
					SensorsInformationPage=SelectLoggersPage.clickNext_SensorinfoBtn();
					TimeUnit.SECONDS.sleep(30);
					ProgramLoggersPage=SensorsInformationPage.click_NextButton_withUnmappedSensors();
					TimeUnit.SECONDS.sleep(50);
					VerificationPage=ProgramLoggersPage.click_nextbtnV();
					VerificationPage.click_Start_verificationbtn();
					UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
					Thread.sleep(500);
					MainHubPage=VerificationPage.click_backbtn();
					Thread.sleep(500);
					AuditPage=MainHubPage.ClickAuditTitle();
					
		sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Start Verification\" operation in \"Verification\" screen");



		//MainHubPage=AuditPage.Click_BackBtn();
		//SelectBaseStationPage=MainHubPage.Click_Discover();
		//TimeUnit.SECONDS.sleep(30);
		//SelectBaseStationPage.Click_DiscoverBS();
		//TimeUnit.SECONDS.sleep(30);
		//SelectBaseStationPage.Select_BSListbox("Ethernet IP-- " + BSIP);
		//SelectBaseStationPage.Click_BSsettingsBtn();
		//SelectBaseStationPage.Click_SetBSideal();
		//tu.click_OK_popup();
		//UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
		//TimeUnit.SECONDS.sleep(10);
		//MainHubPage=	SelectBaseStationPage.clickBackBtn();
		//MainHubPage.UserSignOut();


		sa.assertAll();
				}
				
				//AD_Ver_Audit _021 Verify the Audit trail entry  when aborted the verification study for Humidity Loggers-Humidity Sensors
				
				@Test(priority=18,description = "AD_Ver_Audit _021 Verify the Audit trail entry  when aborted the verification study for Humidity Loggers-Humidity Sensors")
				public void AD_Ver_Audit_021() throws InterruptedException, AWTException, IOException {
					extentTest = extent
							.startTest("AD_Ver_Audit _021 Verify the Audit trail entry  when aborted the verification study for Humidity Loggers-Humidity Sensors");
					
					SoftAssert sa = new SoftAssert();
					
					EquipmentHubPage	=MainHubPage.ClickEquipmentTile();
					EquipmentHubPage.IntiQual_Btn();
					EquipmentHubPage.clickon_Humidityradiobtn();
					setup_verificationpage=EquipmentHubPage.loggerokbutton();
					Thread.sleep(500);
					setup_verificationpage.CreateSetup();
					setup_verificationpage.ClicksaveBtn();
					setup_verificationpage.EntersetupName("H_Setup7");
					SelectBaseStationPage=setup_verificationpage.ClicknextBtn();
					TimeUnit.SECONDS.sleep(30);
					SelectBaseStationPage.Click_DiscoverBS();
					TimeUnit.SECONDS.sleep(30);
					String BSIP="10.17.18.112";
					SelectBaseStationPage.Select_BSListbox("Ethernet IP-- " + BSIP);	
					SelectLoggersPage=SelectBaseStationPage.Click_ConnectBtn();
					TimeUnit. MINUTES. sleep(2);//need to wakeup/connect logger to bsestation
					SelectLoggersPage.Select_LogListbox("TW74");
					Thread.sleep(2000);
					SensorsInformationPage=SelectLoggersPage.clickNext_SensorinfoBtn();
					TimeUnit.SECONDS.sleep(30);
					ProgramLoggersPage=SensorsInformationPage.click_NextButton_withUnmappedSensors();
					TimeUnit.SECONDS.sleep(50);
					VerificationPage=ProgramLoggersPage.click_nextbtnV();
					VerificationPage.click_Abort_verificationbtn();
					
					  String date1=tu.CurrentDatenTime_certainformat(); System.out.println(date1);
					 			
					UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "committed");
					MainHubPage	=VerificationPage.click_YesBtn_popup();
					Thread.sleep(500);
					 String date2=tu.CurrentDatenTime_certainformat();
					 System.out.println(date2);
					Thread.sleep(1000);
					AuditPage=MainHubPage.ClickAuditTitle();
				   
			      	//System.out.println(date);
					sa.assertEquals(AuditPage.get_auditEvent_text(),"Verification aborted by User: Kaveri Bedar, ID: Kaverib at "+ date2);
					
					
					sa.assertAll();
				}*/

				
		
}