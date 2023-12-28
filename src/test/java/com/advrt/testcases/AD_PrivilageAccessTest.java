/**
 * @author kaveriB

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
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.PreferencesPage;
import com.advrt.pages.AD_UMPage;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.pages.AuditPage;
import com.advrt.pages.assetHubPage;
import com.advrt.pages.assetCreationPage;
import com.advrt.pages.assetDetailsPage;
import com.advrt.pages.Setup_defineSetupPage;
import com.advrt.pages.Setup_SensorConfigPage;
import com.advrt.pages.Setup_GroupSensorsPage;
import com.advrt.pages.Setup_CalculationsPage;
import com.advrt.pages.Setup_QualParamPage;
import com.advrt.pages.Setup_ReviewPage;
import com.advrt.pages.EquipmentHubPage;
import com.advrt.pages.NewEquipmentCreation_Page;
import com.advrt.pages.Equipment_IOBoxPage;
import com.advrt.pages.Edit_Equipmentpage;
import com.advrt.pages.SelectBaseStationPage;
import com.advrt.pages.FM_SyncInPage;
import com.advrt.pages.SyncInAssetListPage;
import com.advrt.pages.FileManagementPage;
import com.advrt.pages.Equipment_IRTDHubPage;
import com.advrt.pages.Equipment_IRTDDetailspage;
import com.advrt.pages.RW_FileSelctionPage;
import com.advrt.utility.TestUtilities;


public class AD_PrivilageAccessTest extends BaseClass{

	public AD_PrivilageAccessTest() throws IOException {
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
	DefaultUserPrivilages_page DefaultUserPrivilages_page;
	AuditPage AuditPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	Setup_defineSetupPage Setup_defineSetupPage;
	Setup_SensorConfigPage Setup_SensorConfigPage;
	Setup_GroupSensorsPage Setup_GroupSensorsPage;
	Setup_CalculationsPage Setup_CalculationsPage;
	Setup_QualParamPage Setup_QualParamPage;
	Setup_ReviewPage Setup_ReviewPage;
	PreferencesPage PreferencesPage;
	EquipmentHubPage EquipmentHubPage;
	NewEquipmentCreation_Page NewEquipmentCreation_Page;
	Equipment_IOBoxPage Equipment_IOBoxPage;
	Edit_Equipmentpage Edit_Equipmentpage;
	FileManagementPage FileManagementPage;
	FM_SyncInPage SyncInPage;
	SyncInAssetListPage SyncInAssetListPage;
	SelectBaseStationPage SelectBaseStationPage;
	Equipment_IRTDHubPage Equipment_IRTDHubPage;
	Equipment_IRTDDetailspage Equipment_IRTDDetailspage;
	RW_FileSelctionPage RW_FileSelctionPage;
	static String AdmnUN = "User1";


	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_AD_PrivilageAccessTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD_PrivilageAccessTest Test in Progress..");


		
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

		//MainHubPage = AD_UMPage.click_BackBtn();
		//LoginPage = MainHubPage.UserSignOut();
		AppClose();
		Thread.sleep(2000);

	}

	//After All the tests are conducted
	//@AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("AD PrivilageAccess test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();

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



	//PA001-Verify if User able to access the User Management Module when the User Management Privilege is checked

	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA001-Verify if User able to access the User Management Module when the User Management Privilege is checked")

	public void PA001() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("PA001-Verify if User able to access the User Management Module when the User Management Privilege is checked");
		SoftAssert sa = new SoftAssert();

		AD_UMPage.select_grp("QA Testers");
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("SystemAdministrator");
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();

		//By default system Admin have the privilages for UM page
		sa.assertEquals(AD_UMPage.UMtabPresence(), true,
				"Fail: AdminUmPriv is Not checked");

		sa.assertAll();
	}


	//PA003-Verify if User able to access the Run Qualification Module when the Run Qualification Privilege is checked
	
	@Test(priority=1,groups = { "Sanity",
	"Regression" }, description = "PA003-Verify if User able to access the Run Qualification Module when the Run Qualification Privilege is checked")

	public void PA003() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("PA003-Verify if User able to access the Run Qualification Module when the Run Qualification Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("NUserType");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		DefaultUserPrivilages_page.Click_RunQualification();
		DefaultUserPrivilages_page.NewSaveButton();
		Thread.sleep(500);
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		MainHubPage=AD_UMPage.click_BackBtn();
		AuditPage=MainHubPage.ClickAuditTitle();
		
		String ActualMsg=AuditPage.get_auditEvent_text();
		String ExpectedMsg=" User Type : \"NUserType\" , User Privileges : \"Run Qualification\" created by User ID : \"kiranc\" , User Name : \"kiran c\"";

		sa.assertEquals(ActualMsg,ExpectedMsg,
				"Fail:User unable to access Run qualification even if it is checked");
			

		sa.assertAll();
	}

	
	
	//PA005-Verify if User able to access the Run Calibration Module when the Run Calibration Privilege is checked
	
	@Test(priority=2,groups = { "Sanity",
	"Regression" }, description = "PA005-Verify if User able to access the Run Calibration Module when the Run Calibration Privilege is checked")

	public void PA005() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("PA005-Verify if User able to access the Run Calibration Module when the Run Calibration Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("NUser");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		DefaultUserPrivilages_page.Click_NURunCalibration();
		DefaultUserPrivilages_page.NewSaveButton();
		Thread.sleep(500);
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		MainHubPage=AD_UMPage.click_BackBtn();
		AuditPage=MainHubPage.ClickAuditTitle();
		
		String ActualMsg=AuditPage.get_auditEvent_text();
		String ExpectedMsg=" User Type : \"NUser\" , User Privileges : \"Run Calibration\" created by User ID : \"kiranc\" , User Name : \"kiran c\"";

		sa.assertEquals(ActualMsg,ExpectedMsg,
				"Fail:User unable to access Run calibration even if it is checked");
			

		sa.assertAll();
	}

	
	//PA007-Verify if User able to access the Run Verification Module when the Run Verification Privilege is checked
	
	@Test(priority=3,groups = { "Sanity",
	"Regression" }, description = "PA007-Verify if User able to access the Run Verification Module when the Run Verification Privilege is checked")

	public void PA007() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("PA007-Verify if User able to access the Run Verification Module when the Run Verification Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User1");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		DefaultUserPrivilages_page.Click_RunVerification();
		DefaultUserPrivilages_page.NewSaveButton();
		Thread.sleep(500);
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		MainHubPage=AD_UMPage.click_BackBtn();
		AuditPage=MainHubPage.ClickAuditTitle();
		
		String ActualMsg=AuditPage.get_auditEvent_text();
		String ExpectedMsg=" User Type : \"User1\" , User Privileges : \"Run Verification\" created by User ID : \"kiranc\" , User Name : \"kiran c\"";

		sa.assertEquals(ActualMsg,ExpectedMsg,
				"Fail:User unable to access Run Verification even if it is checked");
			

		sa.assertAll();
	}
	
	//PA009-Verify if User able to access the Create Assets Module when the Create Assets Privilege is checked
	
	@Test(priority=4,groups = { "Sanity",
	"Regression" }, description = "PA009-Verify if User able to access the Create Assets Module when the Create Assets Privilege is checked")

	public void PA009() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA009-Verify if User able to access the Create Assets Module when the Create Assets Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		Thread.sleep(500);
		AD_UMPage.select_grp("QA Testers");
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		Thread.sleep(500);
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.Select_grp();
		AD_UMPage.select_2grp("QA Testers2");
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("User");
		Thread.sleep(500);
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage=	assetHubPage.click_assetTile("Asset01");

		sa.assertEquals(assetDetailsPage.assetDetailPageTitle_Visible(), true, "Fail : Page title is not displayed");
	
		sa.assertAll();
	}
	
	
	//PA011-Verify if User able to access the Modify Assets Module when the Modify Assets Privilege is checked
	
	@Test(priority=5,groups = { "Sanity",
	"Regression" }, description = "PA011-Verify if User able to access the Modify Assets Module when the Modify Assets Privilege is checked")

	public void PA0011() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA011-Verify if User able to access the Modify Assets Module when the Modify Assets Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		if(DefaultUserPrivilages_page.IsCreate_AssetCheckBox_checked()) {
		DefaultUserPrivilages_page.Click_Create_ModifyAssets();
		}
		else {
			DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
			DefaultUserPrivilages_page.Click_Create_ModifyAssets();
		}
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.Select_grp();
		AD_UMPage.select_2grp("QA Testers2");
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("User");
		Thread.sleep(500);
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset02", "02", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		//assetDetailsPage=assetCreationPage.click_BackBtn();//
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage=assetHubPage.click_assetTile("Asset02");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		String NewassetID = "04";
		assetCreationPage.enterAssetID(NewassetID);
		Thread.sleep(500);
		assetCreationPage.clickSaveBtn();
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		//tu.click_OK_popup();
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		String After_Clicking_Back = assetCreationPage.getEqpID();

		sa.assertEquals(NewassetID, After_Clicking_Back,
				"FAIL:The modified values is not displayed in the field");
		
	
		sa.assertAll();
	}
	
	
	
	
	//PA013-Verify if User able to access the Delete Assets Module when the Delete Assets Privilege is checked
	
	@Test(priority=6,groups = { "Sanity",
	"Regression" }, description = "PA013-Verify if User able to access the Delete Assets Module when the Delete Assets Privilege is checked")

	public void PA0013() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA013-Verify if User able to access the Delete Assets Module when the Delete Assets Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		if(DefaultUserPrivilages_page.IsCreate_AssetCheckBox_checked()) {
			DefaultUserPrivilages_page.Click_DeleteAssets();
			DefaultUserPrivilages_page.Click_AuditCheckBox();
			}
		else {
		DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
		DefaultUserPrivilages_page.Click_DeleteAssets();
        DefaultUserPrivilages_page.Click_AuditCheckBox();
		}
        
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.Select_grp();
		AD_UMPage.select_2grp("QA Testers2");
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("User");
		Thread.sleep(500);
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset033", "03", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage=assetHubPage.click_assetTile2("Asset033");
		Thread.sleep(500);
		assetDetailsPage.Click_DeleteAsset();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		//assetDetailsPage.ClickOK_btn();
		assetDetailsPage.click_YesBtn_popup();
		Thread.sleep(500);
		assetHubPage=assetDetailsPage.ClickBackBtn();
		MainHubPage=assetHubPage.click_BackBtn();
		Thread.sleep(500);
        AuditPage=MainHubPage.ClickAuditTitle();
        Thread.sleep(500);
		String ActualMsg=AuditPage.get_auditEvent_text();
		String ExpectedMsg="Asset: \"Asset033\" is deleted by User Id : \"kiranc\" , User Name : \"kiran c\"";

		sa.assertEquals(ActualMsg,ExpectedMsg,
				"Fail:Asset is not deleted");
			
		
		//sa.assertEquals(assetDetailsPage.assetDetailPageTitle_Visible(), false, "Fail : Page title is still displayed");
		//sa.assertEquals(assetHubPage.Is_assetHubPageTitle_Visible(), true, "Fail : Page title is not displayed");
		sa.assertAll();
		
		
			}
	
	/*
	//PA015-Verify if User able to access the Copy Files/Reports Module when the Copy Files/Reports Privilege is checked
	
	@Test(groups = { "Sanity",
	"Regression" }, description = "PA015-Verify if User able to access the Copy Files/Reports Module when the Copy Files/Reports Privilege is checked")

	public void PA0015() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("PA015-Verify if User able to access the Copy Files/Reports Module when the Copy Files/Reports Privilege is checked");
		SoftAssert sa = new SoftAssert();

		AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		
		//sa.assertEquals(ActualMsg,ExpectedMsg,
			//	"Fail:User unable to access Run Verification even if it is checked");
			

		sa.assertAll();
	}
	*/
	
	
	
	//PA017-Verify if User able to access the Create Setups Module when the Create Setups Privilege is checked
	
	@Test(priority = 7,groups = { "Sanity",
	"Regression" }, description = "PA017-Verify if User able to access the Create Setups Module when the Create Setups Privilege is checked")

	public void PA0017() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA017-Verify if User able to access the Create Setups Module when the Create Setups Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		if(DefaultUserPrivilages_page.IsCreate_AssetCheckBox_checked()) {
			DefaultUserPrivilages_page.Click_Create_SetUp();
			}
		else {
		DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
		DefaultUserPrivilages_page.Click_Create_SetUp();
        DefaultUserPrivilages_page.Click_AuditCheckBox();
		}
        
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.Select_grp();
		AD_UMPage.select_2grp("QA Testers2");
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("User");
		Thread.sleep(500);
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset04", "04", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset04");
		// Define Setup
				Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
				Setup_defineSetupPage.clear_defineSetupPage_setupName();
				Setup_defineSetupPage.enter_defineSetupPage_setupName("SetUp");
				Setup_defineSetupPage.enter_defineSetupPage_SensorCount("1");
				Thread.sleep(500);
				Setup_SensorConfigPage = Setup_defineSetupPage.click_defineSetupPage_nxtBtn();
				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
				Setup_SensorConfigPage.click_SelectBtn();
				//Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);//
				Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
				Setup_SensorConfigPage.select_Sensortype_temp();
				//Setup_SensorConfigPage.Enter_Num_To(TempCount);//
				Setup_SensorConfigPage.Enter_SensorLabel("TC");
				Setup_SensorConfigPage.Click_assignBtn();
				Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn();
				Setup_GroupSensorsPage.click_DfltGrp_Btn();
				Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
				//Setup_CalVerParametersPage=	Setup_CalculationsPage.Click_NxtBtn();
				Setup_QualParamPage=	Setup_CalculationsPage.Click_NxtBtn();
				Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
				Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "kiranc1","Amphenol@123","usercommitted.");
				assetDetailsPage = Setup_ReviewPage.click_backBtn();
				Thread.sleep(2000);
				sa.assertEquals(assetDetailsPage.SetupName_Visible1(), "SetUp",
						"FAIL:The Setup Name is not displayed in the available setup");
				
			
		sa.assertAll();
	}
	
	
	
	//PA019-Verify if User able to access the Modify Setups Module when the Modify Setups Privilege is checked
	
	@Test(priority = 8,groups = { "Sanity",
	"Regression" }, description = "PA019-Verify if User able to access the Modify Setups Module when the Modify Setups Privilege is checked")

	public void PA0019() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA019-Verify if User able to access the Modify Setups Module when the Modify Setups Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		if(DefaultUserPrivilages_page.IsCreate_AssetCheckBox_checked()) {
			//DefaultUserPrivilages_page.Click_Create_SetUp();
			DefaultUserPrivilages_page.Click_ModifySetup();
			}
		else {
		DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
		DefaultUserPrivilages_page.Click_Create_SetUp();
		DefaultUserPrivilages_page.Click_ModifySetup();
        DefaultUserPrivilages_page.Click_AuditCheckBox();
		}
        
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.Select_grp();
		AD_UMPage.select_2grp("QA Testers2");
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("User");
		Thread.sleep(500);
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset05", "05", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset05");
		// Define Setup
				Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
				Setup_defineSetupPage.clear_defineSetupPage_setupName();
				Setup_defineSetupPage.enter_defineSetupPage_setupName("SetUp1");
				Setup_defineSetupPage.enter_defineSetupPage_SensorCount("1");
				Thread.sleep(500);
				Setup_SensorConfigPage = Setup_defineSetupPage.click_defineSetupPage_nxtBtn();
				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
				Setup_SensorConfigPage.click_SelectBtn();
				//Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);//
				Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
				Setup_SensorConfigPage.select_Sensortype_temp();
				//Setup_SensorConfigPage.Enter_Num_To(TempCount);//
				Setup_SensorConfigPage.Enter_SensorLabel("TC");
				Setup_SensorConfigPage.Click_assignBtn();
				Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn();
				Thread.sleep(500);
				Setup_GroupSensorsPage.click_DfltGrp_Btn();
				Thread.sleep(500);
				Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
				//Setup_CalVerParametersPage=	Setup_CalculationsPage.Click_NxtBtn();
				Setup_QualParamPage=	Setup_CalculationsPage.Click_NxtBtn();
				Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
				Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "kiranc1","Amphenol@123","usercommitted.");
				assetDetailsPage = Setup_ReviewPage.click_backBtn();
				Thread.sleep(2000);
				assetDetailsPage.Click_SetupName("SetUp1");
				
				//Edit Setup
				Setup_defineSetupPage = assetDetailsPage.editStupBtn_Position_0();
				Setup_defineSetupPage.click_defineSetupPage_SensorCountField();
				Setup_defineSetupPage.clear_defineSetupPage_SensorCount1();
				Setup_defineSetupPage.enter_defineSetupPage_SensorCount("1");
				Setup_defineSetupPage.click_defineSetupPage_SOPField();
				Setup_defineSetupPage.clear_defineSetupPage_SOP();
				Setup_defineSetupPage.enter_defineSetupPage_SOP("SOP");
				Setup_defineSetupPage.click_defineSetupPage_LoadDescField();
				Setup_defineSetupPage.clear_defineSetupPage_LoadDesc();
				Setup_defineSetupPage.enter_defineSetupPage_LoadDesc("LoadDescription");
				Setup_defineSetupPage.click_defineSetupPage_commentsField();
				Setup_defineSetupPage.clear_defineSetupPage_comments();
				Setup_defineSetupPage.enter_defineSetupPage_comments("Comments");
				Thread.sleep(500);
				Setup_SensorConfigPage = Setup_defineSetupPage.click_defineSetupPage_nxtBtn();
				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
				Setup_SensorConfigPage.click_SelectBtn();
				Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
				Setup_SensorConfigPage.select_Sensortype_temp();
				//Setup_SensorConfigPage.Enter_Num_To(TempCount);
				Setup_SensorConfigPage.Enter_SensorLabel("TC1");
				Setup_SensorConfigPage.Click_assignBtn();
				//Setup_SensorConfigPage. Click_nextbtn_YesPopup1();
				Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn(); 
				Setup_GroupSensorsPage.click_DfltGrp_Btn();
				Thread.sleep(500);
				Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
				//Setup_CalVerParametersPage=	Setup_CalculationsPage.Click_NxtBtn();
				Setup_QualParamPage=	Setup_CalculationsPage.Click_NxtBtn();
				Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
				Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "kiranc1","Amphenol@123","usercommitted.");
				// Thread.sleep(1000);
				assetDetailsPage = Setup_ReviewPage.click_backBtn();

				assetDetailsPage.Click_SetupName("SetUp1");
				Setup_defineSetupPage = assetDetailsPage.editStupBtn_Position_0();
				
				String DisplayedSensorcount = Setup_defineSetupPage.get_Sensorcount_text1();
				String ExpectedSensorcount = "1";

				sa.assertEquals(DisplayedSensorcount, ExpectedSensorcount, "Fail: Expected Sensor count  is not displayed");

				String DisplayedSOPNumber = Setup_defineSetupPage.Fetch_sop_text();
				String ExpectedSOPnumber = "SOP";
				sa.assertEquals(DisplayedSOPNumber, ExpectedSOPnumber, "Fail: Expected SOP number  is not displayed");

				String DisplayedLoadDesc = Setup_defineSetupPage.getLoadDesc_txt();
				String ExpectedLoadDesc ="LoadDescription";
				sa.assertEquals(DisplayedLoadDesc, ExpectedLoadDesc, "Fail: Expected Load Description  is not displayed");

		sa.assertAll();
	}
	
	
	//PA021-Verify if User able to access the Delete Setups Module when the Delete Setups Privilege is checked
	
	@Test(priority = 9,groups = { "Sanity",
	"Regression" }, description = "PA021-Verify if User able to access the Delete Setups Module when the Delete Setups Privilege is checked")

	public void PA0021() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA021-Verify if User able to access the Delete Setups Module when the Delete Setups Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		if(DefaultUserPrivilages_page.IsCreate_AssetCheckBox_checked()) {
			//DefaultUserPrivilages_page.Click_Create_SetUp();
			DefaultUserPrivilages_page.Click_DeleteSetUp();
			// DefaultUserPrivilages_page.Click_AuditCheckBox();
			}
		else {
		DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
		DefaultUserPrivilages_page.Click_Create_SetUp();
		DefaultUserPrivilages_page.Click_DeleteSetUp();
        DefaultUserPrivilages_page.Click_AuditCheckBox();
		}
        
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.Select_grp();
		AD_UMPage.select_2grp("QA Testers2");
		Thread.sleep(500);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("User");
		Thread.sleep(500);
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset06", "06", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset06");
		// Define Setup
				Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
				Setup_defineSetupPage.clear_defineSetupPage_setupName();
				Setup_defineSetupPage.enter_defineSetupPage_setupName("SetUp2");
				Setup_defineSetupPage.enter_defineSetupPage_SensorCount("1");
				Thread.sleep(500);
				Setup_SensorConfigPage = Setup_defineSetupPage.click_defineSetupPage_nxtBtn();
				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
				Setup_SensorConfigPage.click_SelectBtn();
				//Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
				Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
				Setup_SensorConfigPage.select_Sensortype_temp();
				//Setup_SensorConfigPage.Enter_Num_To(TempCount);
				Setup_SensorConfigPage.Enter_SensorLabel("TC");
				Setup_SensorConfigPage.Click_assignBtn();
				Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn();
				Setup_GroupSensorsPage.click_DfltGrp_Btn();
				Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
				//Setup_CalVerParametersPage=	Setup_CalculationsPage.Click_NxtBtn();
				Setup_QualParamPage=	Setup_CalculationsPage.Click_NxtBtn();
				Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
				Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "kiranc1","Amphenol@123","usercommitted.");
				assetDetailsPage = Setup_ReviewPage.click_backBtn();
				Thread.sleep(2000);
				assetDetailsPage.Click_SetupName("SetUp2");
				assetDetailsPage.Click_DeleteBtn_report();
				UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
				tu.click_YesBtn_popup();
				Thread.sleep(1000);
				assetHubPage = assetDetailsPage.ClickBackBtn();
				MainHubPage = assetHubPage.click_BackBtn();
				Thread.sleep(500);
				AuditPage=MainHubPage.ClickAuditTitle();
				Thread.sleep(2000);
				String Actionmsg = AuditPage.get_auditEvent_text();
				String ExpectMSG = "Setup : \"SetUp2\"  deleted by User ID : \"Kiranc1\", User Name : \"Kiranc1\"";
				sa.assertEquals(Actionmsg, ExpectMSG,
						"FAIL: Audit trial record does not exists for Delete study file activity");
				sa.assertAll();
		
	}
	
	
	
	//PA045-Verify if User able to access the Audit Trail Module when the Audit Trail Privilege is checked
	
	@Test(priority = 10,groups = { "Sanity",
	"Regression" }, description = "PA045-Verify if User able to access the Audit Trail Module when the Audit Trail Privilege is checked")

	public void PA0045() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA045-Verify if User able to access the Audit Trail Module when the Audit Trail Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		if(DefaultUserPrivilages_page.IsCreate_AssetCheckBox_checked()) {
			DefaultUserPrivilages_page.Click_Create_SetUp();
			// DefaultUserPrivilages_page.Click_AuditCheckBox();
			}
		else {
		DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
		DefaultUserPrivilages_page.Click_Create_SetUp();
	     DefaultUserPrivilages_page.Click_AuditCheckBox();
		}
        
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.Select_grp();
		AD_UMPage.select_2grp("QA Testers2");
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("User");
		Thread.sleep(500);
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		
				sa.assertEquals(AuditPage.AuditHeadTitleVisible(),true,
						"FAIL: User unable to access Audit Trail module even whwn the audit trail is checked");
				sa.assertAll();
		
	}
	
	
	
	//PA047-Verify if User able to access the Policies Module when the Policies Privilege is checked
	
	@Test(priority = 11,groups = { "Sanity",
	"Regression" }, description = "PA047-Verify if User able to access the Policies Module when the Policies Privilege is checked")

	public void PA0047() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA047-Verify if User able to access the Policies Module when the Policies Privilege is checked");
		SoftAssert sa = new SoftAssert();

		
		Thread.sleep(500);
		AD_UMPage.select_grp("QA Testers");
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		if(DefaultUserPrivilages_page.IsCreate_AssetCheckBox_checked()) {
			DefaultUserPrivilages_page.Click_Create_SetUp();
			 DefaultUserPrivilages_page.Click_AuditCheckBox();
			 DefaultUserPrivilages_page.Click_Policies();
			}
		else {
		DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
		DefaultUserPrivilages_page.Click_Create_SetUp();
	     DefaultUserPrivilages_page.Click_AuditCheckBox();
	     DefaultUserPrivilages_page.Click_Policies();
		}
        
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.Select_grp();
		AD_UMPage.select_2grp("QA Testers2");
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("User");
		Thread.sleep(500);
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		//MainHubPage.ClickAdminTile();
		//MainHubPage.AD_ClickAdminTile_UMpage();
	PoliciesPage=MainHubPage.ClickAdminTile_Polpage();
		Thread.sleep(2000);
		
				sa.assertEquals(PoliciesPage.IsPolicies_screenDisplayed(),true,
						"FAIL: User unable to access Audit Trail module even whwn the audit trail is checked");
				sa.assertAll();
		
	}
	
	
	
	//PA050-Verify if User able to access the Preferences Module when the Preferences Privilege is checked 
	
	
	@Test(priority = 12,groups = { "Sanity",
	"Regression" }, description = "PA049-Verify if User able to access the Preferences Module when the Preferences Privilege is checked ")

	public void PA0050() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA049-Verify if User able to access the Preferences Module when the Preferences Privilege is checked ");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		if(DefaultUserPrivilages_page.IsCreate_AssetCheckBox_checked()) {
			DefaultUserPrivilages_page.Click_Create_SetUp();
			 DefaultUserPrivilages_page.Click_AuditCheckBox();
			// DefaultUserPrivilages_page.Click_Policies();
			 DefaultUserPrivilages_page.Click_Create_Preferences();
			}
		else {
		DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
		DefaultUserPrivilages_page.Click_Create_SetUp();
	     DefaultUserPrivilages_page.Click_AuditCheckBox();
	     DefaultUserPrivilages_page.Click_Policies();
	     DefaultUserPrivilages_page.Click_Create_Preferences();
		}
        
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.Select_grp();
		AD_UMPage.select_2grp("QA Testers2");
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("User");
		Thread.sleep(500);
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		//MainHubPage.ClickAdminTile();
		//MainHubPage.AD_ClickAdminTile_UMpage();
		PoliciesPage=MainHubPage.ClickAdminTile_Polpage();
		PreferencesPage=PoliciesPage.Clickpreference_TAB();
		Thread.sleep(2000);
		
				sa.assertEquals(PreferencesPage.IsPreferences_screenDisplayed(),true,
						"FAIL: User unable to access Audit Trail module even whwn the audit trail is checked");
				sa.assertAll();
		
	}
	
	
	
	//PA025-Verify if User able to access the Create Equipment Module when the Create Equipment Privilege is checked
	
	@Test(priority = 13,groups = { "Sanity",
	"Regression" }, description = "PA025-Verify if User able to access the Create Equipment Module when the Create Equipment Privilege is checked")

	public void PA0025() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA025-Verify if User able to access the Create Equipment Module when the Create Equipment Privilege is checked");
		SoftAssert sa = new SoftAssert();

		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
		//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
		DefaultUserPrivilages_page.Click_Create_Equipmet();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		AD_UMPage.Select_grp();
		AD_UMPage.select_2grp("QA Testers2");
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("User");
		Thread.sleep(500);
		AD_UMPage.clickSavebtn();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		MainHubPage.UserSignOut();
		//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		EquipmentHubPage=MainHubPage.ClickEquipmentTile();
       NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
		
		//Creating 1st Equipment
		
       NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EL009b", "009b");
       Thread.sleep(500);
		//NewEquipmentCreation_Page.selectReqDate("October","30","2024");
		//NewEquipmentCreation_Page.	select_LastDate();
		//NewEquipmentCreation_Page.ClickSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		EquipmentHubPage=NewEquipmentCreation_Page.ClickBackBtn();
	    MainHubPage=	EquipmentHubPage.ClickBackBtn();
		
		Thread.sleep(2000);
		
				sa.assertEquals(EquipmentHubPage.EpuipmentCountOnEquipmentHubPage()>0,true,
						"FAIL: User unable to access Equipment module even when the quipment module is checked");
				sa.assertAll();
		
}

//Individually getting passed
	//PA027-Verify if User able to access the Modify Equipment Module when the Modify Equipment Privilege is checked

@Test(priority = 14,groups = { "Sanity",
"Regression" }, description = "PA027-Verify if User able to access the Modify Equipment Module when the Modify Equipment Privilege is checked")

public void PA0027() throws InterruptedException, IOException, ParseException {
	extentTest = extent
			.startTest("PA027-Verify if User able to access the Modify Equipment Module when the Modify Equipment Privilege is checked");
	SoftAssert sa = new SoftAssert();

	
	AD_UMPage.select_grp("QA Testers");
	
	AD_UMPage.select_user(1);
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("NewUserType");
	DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
	DefaultUserPrivilages_page.Click_Create_UserManagement();//while running individually must uncommit
	DefaultUserPrivilages_page.Click_Create_Equipmet();// while running individually must uncommit
	DefaultUserPrivilages_page.Click_ModifyEquipment();
	DefaultUserPrivilages_page.NewSaveButton();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	Thread.sleep(1000);
	AD_UMPage.Select_grp();
	AD_UMPage.select_2grp("QA Testers2");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("User");
	Thread.sleep(500);
	AD_UMPage.clickSavebtn();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	tu.click_OK_popup();
	MainHubPage=AD_UMPage.click_BackBtn();
	MainHubPage.UserSignOut();
	//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
	LoginPage = new LoginPage();
	MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
	EquipmentHubPage=MainHubPage.ClickEquipmentTile();
   NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
	
	//Creating 1st Equipment
   NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EL010b", "010b");
	Thread.sleep(500);
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
	EquipmentHubPage=NewEquipmentCreation_Page.ClickBackBtn();
	Equipment_IRTDHubPage = EquipmentHubPage.click_IRTDTile();
	Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("EL010b");
	Equipment_IRTDDetailspage.enter_IRTDEquipName("test1");
	String expid = Equipment_IRTDDetailspage.fetch_ID();
	UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
	
	String actmsg = Equipment_IRTDDetailspage.AlertMsg();
	String Expmsg = "Equipment \"EL010b\" Updated successfully.";
	sa.assertEquals(actmsg, Expmsg, "FAIL: Alert message is not correct");

	Equipment_IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();
	Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("EL010b");
	String actid = Equipment_IRTDDetailspage.fetch_ID();
	sa.assertEquals(actid, expid, "FAIL: New ID has not updated");
			sa.assertAll();
	
}

//Individually getting passed
	//PA029-Verify if User able to access the Delete Equipment Module when the Delete Equipment Privilege is checked

@Test(priority = 15,groups = { "Sanity",
"Regression" }, description = "PA029-Verify if User able to access the Delete Equipment Module when the Delete Equipment Privilege is checked")

public void PA0029() throws InterruptedException, IOException, ParseException {
	extentTest = extent
			.startTest("PA029-Verify if User able to access the Delete Equipment Module when the Delete Equipment Privilege is checked");
	SoftAssert sa = new SoftAssert();

	//AD_UMPage.Select_grp();
	AD_UMPage.select_grp("QA Testers");
	//AD_UMPage.Select_user();
	AD_UMPage.select_user(1);
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("NewUserType");
	DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
	//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
	//DefaultUserPrivilages_page.Click_Create_Equipmet();//while running individually must uncommit
	//DefaultUserPrivilages_page.Click_ModifyEquipment();//while running individually must uncommit
	DefaultUserPrivilages_page.Click_DeleteEquipment();
	DefaultUserPrivilages_page.NewSaveButton();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	Thread.sleep(1000);
	AD_UMPage.Select_grp();
	AD_UMPage.select_2grp("QA Testers2");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("User");
	Thread.sleep(500);
	AD_UMPage.clickSavebtn();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	tu.click_OK_popup();
	MainHubPage=AD_UMPage.click_BackBtn();
	MainHubPage.UserSignOut();
	//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
	LoginPage = new LoginPage();
	MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
	EquipmentHubPage=MainHubPage.ClickEquipmentTile();
   NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
	
	//Creating 1st Equipment
   NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EL011b", "011b");
	UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
	EquipmentHubPage=NewEquipmentCreation_Page.ClickBackBtn();
	Equipment_IRTDHubPage = EquipmentHubPage.click_IRTDTile();
	Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("EL011b");
	Equipment_IRTDDetailspage.clickDeleteEquipmentIcon();
	tu.click_YesBtn_popup();
	UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
	Thread.sleep(2000);
	sa.assertFalse(EquipmentHubPage.is_ReqEpuipmentVisible("EL011b"), "Fail: Application still showing Equipment after deleting");
	sa.assertAll();
				
}

//PA037-Verify if User able to access the Camera Access Module when the Camera Access Privilege is checked 

@Test(priority=16,groups = { "Sanity",
"Regression" }, description = "PA037-Verify if User able to access the Camera Access Module when the Camera Access Privilege is checked ")

public void PA037() throws InterruptedException, IOException, ParseException {
	extentTest = extent
			.startTest("PA037-Verify if User able to access the Camera Access Module when the Camera Access Privilege is checked s");
	SoftAssert sa = new SoftAssert();

	//AD_UMPage.Select_grp();
	AD_UMPage.select_grp("QA Testers");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("NewUserType");
	DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
	Thread.sleep(500);
	//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
	if(DefaultUserPrivilages_page.IsCreate_AssetCheckBox_checked()) {
	//DefaultUserPrivilages_page.Click_Create_ModifyAssets();	
		 DefaultUserPrivilages_page.Click_AuditCheckBox();
		DefaultUserPrivilages_page.Click_CameraAccess();
		}
	else {
	DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
	DefaultUserPrivilages_page.Click_Create_ModifyAssets();
     DefaultUserPrivilages_page.Click_AuditCheckBox();
     DefaultUserPrivilages_page.Click_CameraAccess();
     DefaultUserPrivilages_page.Click_Policies();
   	}	
	DefaultUserPrivilages_page.NewSaveButton();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	Thread.sleep(1000);
	AD_UMPage.Select_grp();
	AD_UMPage.select_2grp("QA Testers2");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("User");
	Thread.sleep(500);
	AD_UMPage.clickSavebtn();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	tu.click_OK_popup();
	MainHubPage=AD_UMPage.click_BackBtn();
	MainHubPage.UserSignOut();
	//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
	LoginPage = new LoginPage();
	MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
	assetHubPage = MainHubPage.Click_AssetTile2();
	assetCreationPage = assetHubPage.ClickAddAssetBtn();
	assetCreationPage.capture_Camera_Img();

	// Capture the expected Image added to the Asset placeholder 1
	assetCreationPage.Capture_AsstImg1("Expected_Asst36_AsstCreation");

	String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
	assetCreationPage.assetCreationWithAllFieldEntry("Asset077", "07", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
			"cu", crntDate, "5", "Weeks", "1st Asset Creation");
	UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
	Thread.sleep(1000);
	
	assetHubPage = assetCreationPage.clickBackBtn();

	// Again select the above Asset created to move to Asset detail page and then
	// click the Edit asset button
	Thread.sleep(2000);
	assetDetailsPage = assetHubPage.click_assetTile("Asset077");
	Thread.sleep(500);
	
	assetCreationPage = assetDetailsPage.click_assetEditBtn();

	// Capture the actual Image saved to the Asset placeholder 1
	assetCreationPage.Capture_AsstImg1("Actual_Asst36_AsstCreation");

	boolean status_ImgCompare = tu.compareImage("Expected_Asst36_AsstCreation", "Actual_Asst36_AsstCreation");

	sa.assertFalse(status_ImgCompare, "FAIL: The Asset Image saved is not same as what was captured");
	sa.assertAll();



}



//PA031-Verify if User able to access the Manual Sync Module when the Manual Sync Privilege is checked

@Test(priority=17,groups = { "Sanity",
"Regression" }, description = "PA031-Verify if User able to access the Manual Sync Module when the Manual Sync Privilege is checked")

public void PA031() throws InterruptedException, IOException, ParseException, AWTException {
	extentTest = extent
			.startTest("PA031-Verify if User  able to access the Manual Sync Module when the Manual Sync Privilege is unchecked");
	SoftAssert sa = new SoftAssert();

	//AD_UMPage.Select_grp();
	AD_UMPage.select_grp("QA Testers");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("NewUserType");
	DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
	Thread.sleep(500);
	//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
	DefaultUserPrivilages_page.Click_ManualSync();
	DefaultUserPrivilages_page.NewSaveButton();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	Thread.sleep(1000);
	AD_UMPage.Select_grp();
	AD_UMPage.select_2grp("QA Testers2");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("User");
	Thread.sleep(500);
	AD_UMPage.clickSavebtn();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	tu.click_OK_popup();
	MainHubPage=AD_UMPage.click_BackBtn();
	MainHubPage.UserSignOut();
	//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
	LoginPage = new LoginPage();
	MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
	Thread.sleep(1000);
	//SyncIn
	FileManagementPage = MainHubPage.ClickFileManagementTitle();
	SyncInPage = FileManagementPage.AD_ClickSyncInBtn_SyncinPage_withcommit("kiranc","Amphenol@123","usercommited");
	SyncInPage.enter_Filepath("syncin");
	SyncInPage.click_FltrBtn();
	SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
	SyncInAssetListPage.click_EquipmentCheckBox();
	SyncInAssetListPage.click_SelectAllBtn();
	SyncInAssetListPage.click_OkBtn();
	//SyncInAssetListPage.click_OkOnSyncInSelections();
	SyncInAssetListPage.click_AlrtYesBtn();
//	Thread.sleep(9000);
	//SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
	Thread.sleep(5000);
	
	String ActualMsg=tu.get_popup_text();
	String ExpectedMsg="Sync In Successful. \n"
			+ "Application will be closed now.Please Relaunch the Application to get the refreshed data.";

sa.assertEquals(ActualMsg, ExpectedMsg,
"FAIL:User  not able to access  Manual syncin when the Manual syncin is checked");


	sa.assertAll();

}


//PA051-Verify if User able to access the Hardware Maintenance Module when the Hardware Maintenance Privilege is checked 

@Test(priority=18,groups = { "Sanity",
"Regression" }, description = "PA051-Verify if User able to access the Hardware Maintenance Module when the Hardware Maintenance Privilege is checked ")

public void PA051() throws InterruptedException, IOException, ParseException, AWTException {
	extentTest = extent
			.startTest("PA051-Verify if User able to access the Hardware Maintenance Module when the Hardware Maintenance Privilege is checked");
	SoftAssert sa = new SoftAssert();

	//AD_UMPage.Select_grp();
	AD_UMPage.select_grp("QA Testers");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("NewUserType");
	DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
	Thread.sleep(500);
	//DefaultUserPrivilages_page.Click_Create_UserManagement();while running individually must uncommit
	DefaultUserPrivilages_page.Click_HardwarreMaintaince();
	DefaultUserPrivilages_page.NewSaveButton();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	Thread.sleep(1000);
	AD_UMPage.Select_grp();
	AD_UMPage.select_2grp("QA Testers2");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("User");
	Thread.sleep(500);
	AD_UMPage.clickSavebtn();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	tu.click_OK_popup();
	MainHubPage=AD_UMPage.click_BackBtn();
	MainHubPage.UserSignOut();
	//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
	LoginPage = new LoginPage();
	MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
	SelectBaseStationPage=MainHubPage.Click_Discover();
	Thread.sleep(1000);
	
	sa.assertEquals(SelectBaseStationPage.SelectBaseStationTitle_state(), true,
			"Fail: Guest unable to access Verification module  ");
	sa.assertAll();
	

}


//PA033-Verify if User able to access the Create Reports Module when the Create Reports Privilege is checked 

@Test(priority=19,groups = { "Sanity",
"Regression" }, description = "PA033-Verify if User able to access the Create Reports Module when the Create Reports Privilege is checked ")

public void PA033() throws InterruptedException, IOException, ParseException, AWTException {
	extentTest = extent
			.startTest("PA033-Verify if User able to access the Create Reports Module when the Create Reports Privilege is checked ");
	SoftAssert sa = new SoftAssert();

	
	AD_UMPage.select_grp("QA Testers");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("NewUserType");
	DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
	Thread.sleep(500);
	//DefaultUserPrivilages_page.Click_Create_UserManagement();//while running individually must uncommit
	DefaultUserPrivilages_page.Click_Create_Reports();
	DefaultUserPrivilages_page.Click_ManualSync();///while running individually must uncommit
	DefaultUserPrivilages_page.NewSaveButton();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	Thread.sleep(1000);
	AD_UMPage.Select_grp();
	AD_UMPage.select_2grp("QA Testers2");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("User");
	Thread.sleep(500);
	AD_UMPage.clickSavebtn();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	tu.click_OK_popup();
	MainHubPage=AD_UMPage.click_BackBtn();
	MainHubPage.UserSignOut();
	//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
	LoginPage = new LoginPage();
	MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
	FileManagementPage = MainHubPage.ClickFileManagementTitle();
	SyncInPage = FileManagementPage.AD_ClickSyncInBtn_SyncinPage_withcommit("kiranc","Amphenol@123","usercommited");
	SyncInPage.enter_Filepath("syncin");
	SyncInPage.click_FltrBtn();
	SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
	SyncInAssetListPage.click_EquipmentCheckBox();
	SyncInAssetListPage.click_SelectAllBtn();
	SyncInAssetListPage.click_OkBtn();
	SyncInAssetListPage.click_AlrtYesBtn();
	Thread.sleep(9000);
	SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
	Thread.sleep(2000);
	
	
	LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
	Thread.sleep(500);
	LoginPage = new LoginPage();
	MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
	
	assetHubPage=MainHubPage.Click_AssetTile2();
	assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
	assetDetailsPage.click_QualTile();
	assetDetailsPage.Select_QualFile("manual 1 min sampling");
	RW_FileSelctionPage=assetDetailsPage.Click_GenerateReportsBtn_RWpage();
	
	sa.assertEquals(RW_FileSelctionPage.assetDetailTitle_Visible(), true,
			"Fail: Guest unable to access Verification module  ");
	sa.assertAll();
	

}

//PA035-Verify if User able to access the Delete StudyFiles/Reports Module when the Delete StudyFiles/Reports Privilege is checked 



@Test(priority=20,groups = { "Sanity",
"Regression" }, description = "PA035-Verify if User able to access the Delete StudyFiles/Reports Module when the Delete StudyFiles/Reports Privilege is checked  ")

public void PA035() throws InterruptedException, IOException, ParseException, AWTException {
	extentTest = extent
			.startTest("PA035-Verify if User able to access the Delete StudyFiles/Reports Module when the Delete StudyFiles/Reports Privilege is checked  ");
	SoftAssert sa = new SoftAssert();

	
	AD_UMPage.select_grp("QA Testers");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("NewUserType");
	DefaultUserPrivilages_page=AD_UMPage.newUserType("User");
	Thread.sleep(500);
	//DefaultUserPrivilages_page.Click_Create_UserManagement();//while running individually must uncommit
	//DefaultUserPrivilages_page.Click_Create_Reports();
	//DefaultUserPrivilages_page.Click_ManualSync();//must commit while running through suite
	DefaultUserPrivilages_page.Click_DeleteStudyFiles_Reports();
	DefaultUserPrivilages_page.Click_AuditCheckBox();
	DefaultUserPrivilages_page.NewSaveButton();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	Thread.sleep(1000);
	AD_UMPage.Select_grp();
	AD_UMPage.select_2grp("QA Testers2");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("User");
	Thread.sleep(500);
	AD_UMPage.clickSavebtn();
	UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
	tu.click_OK_popup();
	MainHubPage=AD_UMPage.click_BackBtn();
	MainHubPage.UserSignOut();
	//as we have assigned newusertype to QATesters2,we are using that user which are available in QATesters2
	LoginPage = new LoginPage();
	MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
	FileManagementPage = MainHubPage.ClickFileManagementTitle();
	SyncInPage = FileManagementPage.AD_ClickSyncInBtn_SyncinPage_withcommit("kiranc","Amphenol@123","usercommited");
	SyncInPage.enter_Filepath("syncin");
	SyncInPage.click_FltrBtn();
	SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
	SyncInAssetListPage.click_EquipmentCheckBox();
	SyncInAssetListPage.click_SelectAllBtn();
	SyncInAssetListPage.click_OkBtn();
	SyncInAssetListPage.click_AlrtYesBtn();
	Thread.sleep(9000);
	SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
	Thread.sleep(2000);
	
	
	LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
	Thread.sleep(500);
	LoginPage = new LoginPage();
	MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
	
	assetHubPage=MainHubPage.Click_AssetTile2();
	assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
	assetDetailsPage.click_QualTile();
	assetDetailsPage.Select_QualFile("manual 1 min sampling");
	
	//RW_FileSelctionPage=assetDetailsPage.Click_GenerateReportsBtn_RWpage();
	assetDetailsPage.Qual_DeleteBtn();
	UserLoginPopup_UserCommentTextBox("kiranc1", "Amphenol@123", "committed");
	Thread.sleep(2000);
	
	tu.click_YesBtn_popup();
	Thread.sleep(500);
	assetHubPage = assetDetailsPage.ClickBackBtn();
	Thread.sleep(500);
	MainHubPage = assetHubPage.click_BackBtn();
	Thread.sleep(500);
	AuditPage = MainHubPage.ClickAuditTitle();
	Thread.sleep(2000);
	String Actionmsg = AuditPage.get_auditEvent_text();
	String ExpectMSG = "Qualification Study : \"manual 1 min sampling\"  deleted by User ID : \"Kiranc1\", User Name : \"Kiranc1\"";
	sa.assertEquals(Actionmsg, ExpectMSG,
			"FAIL: Audit trial record does not exists for Deletion of a Detailed report ");
	sa.assertAll();

}









}