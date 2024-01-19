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
import com.advrt.pages.Edit_Equipmentpage;//
import com.advrt.pages.FM_SyncInPage;
import com.advrt.pages.Equipment_IRTDHubPage;
import com.advrt.pages.SyncInAssetListPage;
import com.advrt.pages.Equipment_IRTDDetailspage;
import com.advrt.pages.FileManagementPage;
import com.advrt.pages.SelectBaseStationPage;//
import com.advrt.pages.RW_FileSelctionPage;
import com.advrt.utility.TestUtilities;


public class AD_GuestPrivilageAccessTest extends BaseClass{

	public AD_GuestPrivilageAccessTest() throws IOException {
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
	//Setup_CalVerParametersPage Setup_CalVerParametersPage;
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

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"AD_GuestPrivilageAccessTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("ADprivilageAccess Test in Progress..");


		
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
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();
Thread.sleep(1000);
		AD_UMPage=PoliciesPage.click_AD_UMHeader();
		AD_UMPage.select_grp("QA Testers");
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("SystemAdministrator");
		AD_UMPage.clickSavebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		Thread.sleep(1000);
		tu.click_OK_popup();
		Thread.sleep(1000);
		tu.click_OK_popup();
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();
		Thread.sleep(500);
		//AD_UMPage.Select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("All");
		DefaultUserPrivilages_page.AllPrivilages();
		DefaultUserPrivilages_page.NewSaveButton();
		Thread.sleep(500);
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		PoliciesPage=AD_UMPage.AD_Click_Policy();
		Thread.sleep(5000);
	    AD_UMPage=PoliciesPage.click_AD_UMHeader();
	    Thread.sleep(5000);
	    PoliciesPage=AD_UMPage.AD_Click_Policy();
		PoliciesPage.click_on_AllowGuest();
		PoliciesPage.selectGuestuser(4);
		PoliciesPage.ClickSaveButton();
		Thread.sleep(500);
		PoliciesPage.clickOn_AcceptBtn();
		//tu.click_OK_popup();
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
		tu.click_OK_popup();
		MainHubPage=PoliciesPage.click_BackBtn();
		MainHubPage.UserSignOut();

		AppClose();
		Thread.sleep(2000);

	}

	//After All the tests are conducted
	//@AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("AD GuestPrivilageAccess test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();
		PoliciesPage=AD_UMPage.AD_Click_Policy();
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



	//PA055-Verify if GuestUser able to access the User Management Module when the User Management Privilege is checked

	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA055-Verify if GuestUser able to access the User Management Module when the User Management Privilege is checked")

	public void PA055() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("PA055-Verify if GuestUser able to access the User Management Module when the User Management Privilege is checked");
		SoftAssert sa = new SoftAssert();
		
//		AD_UMPage=PoliciesPage.click_AD_UMHeader();
//		AD_UMPage.Select_grp();
//		AD_UMPage.select_grp("QA Testers");
//		AD_UMPage.Select_user();
//		AD_UMPage.select_user(1);
//		AD_UMPage.select_UserTitle("Manager");
//		AD_UMPage.select_UserType1("NewUserType");
//		DefaultUserPrivilages_page=AD_UMPage.newUserType("NUserType");
//		DefaultUserPrivilages_page.Click_Create_UserManagement();
//		DefaultUserPrivilages_page.NewSaveButton();
//		Thread.sleep(500);
//		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
//		PoliciesPage=AD_UMPage.AD_Click_Policy();
//		PoliciesPage.click_on_AllowGuest();
//		PoliciesPage.selectGuestuser(4);
//		PoliciesPage.ClickSaveButton();
//		tu.click_OK_popup();
//		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");
//		tu.click_OK_popup();
//		MainHubPage=PoliciesPage.click_BackBtn();
//		MainHubPage.UserSignOut();
//		LoginPage = new LoginPage();
//		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		AD_UMPage=PoliciesPage.click_AD_UMHeader();
		
	
		sa.assertEquals(AD_UMPage.UMtabPresence(), true,
				"Fail: AdminUmPriv is Not checked");

	
		sa.assertAll();
	}

	

//PA057-Verify if Guest User able to access the Run Qualification Module when the Run Qualification Privilege is checked
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA057-Verify if Guest User able to access the Run Qualification Module when the Run Qualification Privilege is checked")

	public void PA057() throws InterruptedException, IOException ,ParseException{
		extentTest = extent
				.startTest("PA057-Verify if Guest User able to access the Run Qualification Module when the Run Qualification Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		
	    MainHubPage=PoliciesPage.click_BackBtn();
		assetHubPage=MainHubPage.Click_AssetTile2();
	    assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		// Define Setup
				Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
				Setup_defineSetupPage.clear_defineSetupPage_setupName();
				Setup_defineSetupPage.enter_defineSetupPage_setupName("SetUp");
				Setup_defineSetupPage.enter_defineSetupPage_SensorCount("1");
				Thread.sleep(1000);
				Setup_SensorConfigPage = Setup_defineSetupPage.click_defineSetupPage_nxtBtn();
				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
				Setup_SensorConfigPage.click_SelectBtn();
				Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
				Setup_SensorConfigPage.select_Sensortype_temp();
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
				assetDetailsPage.Click_SetupName("SetUp");
				assetDetailsPage.click_InitiateQualBtn();
				SelectBaseStationPage = assetDetailsPage.Enter_SOP("01");

				sa.assertEquals(SelectBaseStationPage.SelectBaseStationTitle_state(), true,
						"Fail: SOP did not accept valid data ");
				sa.assertAll();
	
	}


	
//PA063-Verify if Guest User able to access the Create Assets Module when the Create Assets Privilege is checked
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA063-Verify if Guest User able to access the Create Assets Module when the Create Assets Privilege is checked")

	public void PA063() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA063-Verify if Guest User able to access the Create Assets Module when the Create Assets Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
		assetHubPage=MainHubPage.Click_AssetTile2();
	    assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset04", "04", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "2nd Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset04");
	
		sa.assertEquals(assetDetailsPage.assetDetailPageTitle_Visible(), true, "Fail : Page title is not displayed");
	
	
	
	}
	
//PA065-Verify if Guest User able to access the Modify Assets Module when the Modify Assets Privilege is checked
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA065-Verify if Guest User able to access the Modify Assets Module when the Modify Assets Privilege is checked")

	public void PA065() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA065-Verify if Guest User able to access the Modify Assets Module when the Modify Assets Privilege is checked");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=PoliciesPage.click_BackBtn();
		assetHubPage=MainHubPage.Click_AssetTile2();
	    assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset05", "05", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "2nd Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset05");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		String NewassetID = "04";
		assetCreationPage.enterAssetID(NewassetID);
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
	
//PA067-Verify if Guest User able to access the Delete Assets Module when the Delete Assets Privilege is checked	

	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA067-Verify if Guest User able to access the Delete Assets Module when the Delete Assets Privilege is checked")

	public void PA067() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA067-Verify if Guest User able to access the Delete Assets Module when the Delete Assets Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
		assetHubPage=MainHubPage.Click_AssetTile2();
	    assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset06", "06", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "2nd Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset06");
		assetDetailsPage.Click_DeleteAsset();
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		//assetDetailsPage.ClickOK_btn();
		assetDetailsPage.click_YesBtn_popup();
		Thread.sleep(500);
		assetHubPage=assetDetailsPage.ClickBackBtn();
		MainHubPage=assetHubPage.click_BackBtn();
		
        AuditPage=MainHubPage.ClickAuditTitle();
        Thread.sleep(500);
		String ActualMsg=AuditPage.get_auditEvent_text();
		String ExpectedMsg="Asset: \"Asset06\" is deleted by User Id : \"kiranc1\" , User Name : \"Guest\"";

		sa.assertEquals(ActualMsg,ExpectedMsg,
				"Fail:Asset is not deleted");

	
		sa.assertAll();
	}
	

//PA069-Verify if Guest User able to access the Copy Files/Reports Module when the Copy Files/Reports Privilege is checked

//PA071-Verify if Guest User able to access the Create Setups Module when the Create Setups Privilege is checked
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA071-Verify if Guest User able to access the Create Setups Module when the Create Setups Privilege is checked")

	public void PA071() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA071-Verify if Guest User able to access the Create Setups Module when the Create Setups Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
		assetHubPage=MainHubPage.Click_AssetTile2();
	    assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset07", "07", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "2nd Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);		
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset07");
		// Define Setup
				Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
				Setup_defineSetupPage.clear_defineSetupPage_setupName();
				Setup_defineSetupPage.enter_defineSetupPage_setupName("SetUp3");
				Setup_defineSetupPage.enter_defineSetupPage_SensorCount("1");
				Thread.sleep(1000);
				Setup_SensorConfigPage = Setup_defineSetupPage.click_defineSetupPage_nxtBtn();
				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
				Setup_SensorConfigPage.click_SelectBtn();
				Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
				Setup_SensorConfigPage.select_Sensortype_temp();
				Setup_SensorConfigPage.Enter_SensorLabel("TC3");
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
				sa.assertEquals(assetDetailsPage.SetupName_Visible1(), "SetUp3",
						"FAIL:The Setup Name is not displayed in the available setup");
				
			
		sa.assertAll();
	}

		
	
	
//PA073-Verify if Guest User able to access the Modify Setups Module when the Modify Setups Privilege is checked
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA073-Verify if Guest User able to access the Modify Setups Module when the Modify Setups Privilege is checked")

	public void PA073() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA073-Verify if Guest User able to access the Modify Setups Module when the Modify Setups Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
		assetHubPage=MainHubPage.Click_AssetTile2();
	    assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset08", "08", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "2nd Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);		
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset08");
		// Define Setup
				Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
				Setup_defineSetupPage.clear_defineSetupPage_setupName();
				Setup_defineSetupPage.enter_defineSetupPage_setupName("SetUp4");
				Setup_defineSetupPage.enter_defineSetupPage_SensorCount("1");
				Thread.sleep(1000);
				Setup_SensorConfigPage = Setup_defineSetupPage.click_defineSetupPage_nxtBtn();
				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
				Setup_SensorConfigPage.click_SelectBtn();
				Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
				Setup_SensorConfigPage.select_Sensortype_temp();
				Setup_SensorConfigPage.Enter_SensorLabel("TC4");
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
				assetDetailsPage.Click_SetupName("SetUp4");
				
				
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
				Thread.sleep(1000);
				Setup_SensorConfigPage = Setup_defineSetupPage.click_defineSetupPage_nxtBtn();
				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
				Setup_SensorConfigPage.click_SelectBtn();
				Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
				Setup_SensorConfigPage.select_Sensortype_temp();
				//Setup_SensorConfigPage.Enter_Num_To(TempCount);
				Setup_SensorConfigPage.Enter_SensorLabel("TC5");
				Setup_SensorConfigPage.Click_assignBtn();
				//Setup_SensorConfigPage. Click_nextbtn_YesPopup1();
				Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn(); 
				Setup_GroupSensorsPage.click_DfltGrp_Btn();
				Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
				//Setup_CalVerParametersPage=	Setup_CalculationsPage.Click_NxtBtn();
				Setup_QualParamPage=	Setup_CalculationsPage.Click_NxtBtn();
				Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
				Setup_ReviewPage.click_Save_Btn("Manual", "Yes", "kiranc1","Amphenol@123","usercommitted.");
				// Thread.sleep(1000);
				assetDetailsPage = Setup_ReviewPage.click_backBtn();

				assetDetailsPage.Click_SetupName("SetUp4");
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
	
	
	
//PA075-Verify if Guest User able to access the Delete Setups Module when the Delete Setups Privilege is checked
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA073-Verify if Guest User able to access the Modify Setups Module when the Modify Setups Privilege is checked")

	public void PA075() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA073-Verify if Guest User able to access the Modify Setups Module when the Modify Setups Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
		assetHubPage=MainHubPage.Click_AssetTile2();
	    assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset09", "09", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "2nd Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);		
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset09");
		// Define Setup
				Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
				Setup_defineSetupPage.clear_defineSetupPage_setupName();
				Setup_defineSetupPage.enter_defineSetupPage_setupName("SetUp5");
				Setup_defineSetupPage.enter_defineSetupPage_SensorCount("1");
				Thread.sleep(1000);
				Setup_SensorConfigPage = Setup_defineSetupPage.click_defineSetupPage_nxtBtn();
				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_TemperatureCount_textField("1");
				Setup_SensorConfigPage.click_SelectBtn();
				Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
				Setup_SensorConfigPage.select_Sensortype_temp();
				Setup_SensorConfigPage.Enter_SensorLabel("TC5");
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
				assetDetailsPage.Click_SetupName("SetUp5");
				
				
				assetDetailsPage.Click_DeleteBtn_report();
				UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
				tu.click_YesBtn_popup();
				Thread.sleep(500);
				assetHubPage = assetDetailsPage.ClickBackBtn();
				MainHubPage = assetHubPage.click_BackBtn();
				Thread.sleep(500);
				AuditPage=MainHubPage.ClickAuditTitle();
				Thread.sleep(2000);
				String Actionmsg = AuditPage.get_auditEvent_text();
				String ExpectMSG = "Setup : \"SetUp5\"  deleted by User ID : \"kiranc1\", User Name : \"Guest\"";
				sa.assertEquals(Actionmsg, ExpectMSG,
						"FAIL: Audit trial record does not exists for Delete study file activity");
				sa.assertAll();
	}
	
//PA077-Verify if Guest User able to access the Archive Data Module when the Archive Data Privilege is checked
	
//PA079-Verify if Guest User able to access the Create Equipment Module when the Create Equipment Privilege is checked
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA079-Verify if Guest User able to access the Create Equipment Module when the Create Equipment Privilege is checked")

	public void PA079() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA079-Verify if Guest User able to access the Create Equipment Module when the Create Equipment Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
		EquipmentHubPage=MainHubPage.ClickEquipmentTile();
	       NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
			
			//Creating 1st Equipment
	       NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EL009b", "009b");
	       Thread.sleep(500);
			UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
			EquipmentHubPage=NewEquipmentCreation_Page.ClickBackBtn();
		    MainHubPage=	EquipmentHubPage.ClickBackBtn();
			
			Thread.sleep(2000);
			
					sa.assertEquals(EquipmentHubPage.EpuipmentCountOnEquipmentHubPage()>0,true,
							"FAIL: User unable to access Equipment module even when the quipment module is checked");
					sa.assertAll();
		
		
				sa.assertAll();
	}
	
	
//PA081-Verify if Guest User able to access the Modify Equipment Module when the Modify Equipment Privilege is checked
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA081-Verify if Guest User able to access the Modify Equipment Module when the Modify Equipment Privilege is checked")

	public void PA081() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA081-Verify if Guest User able to access the Modify Equipment Module when the Modify Equipment Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
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
	
	
	
//PA083-Verify if Guest User able to access the Delete Equipment Module when the Delete Equipment Privilege is checked
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA083-Verify if Guest User able to access the Delete Equipment Module when the Delete Equipment Privilege is checked")

	public void PA083() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA083-Verify if Guest User able to access the Delete Equipment Module when the Delete Equipment Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
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
		
			sa.assertFalse(EquipmentHubPage.is_ReqEpuipmentVisible("12AD"), "Fail: Application still showing Equipment after deleting");
			sa.assertAll();
				sa.assertAll();
	}
	
	
//PA085-Verify if Guest User able to access the Manual Sync Module when the Manual Sync Privilege is checked	

	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA085-Verify if Guest User able to access the Manual Sync Module when the Manual Sync Privilege is checked")

	public void PA085() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA085-Verify if Guest User able to access the Manual Sync Module when the Manual Sync Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.AD_ClickSyncInBtn_SyncinPage_withcommit("kiranc1","Amphenol@123","usercommited");
		SyncInPage.enter_Filepath("syncin");
		SyncInPage.click_FltrBtn();
		SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
		SyncInAssetListPage.click_EquipmentCheckBox();
		SyncInAssetListPage.click_SelectAllBtn();
		SyncInAssetListPage.click_OkBtn();
		//SyncInAssetListPage.click_OkOnSyncInSelections();
		SyncInAssetListPage.click_AlrtYesBtn();
//		Thread.sleep(9000);
		//SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
		Thread.sleep(5000);
		
		String ActualMsg=tu.get_popup_text();
		String ExpectedMsg="Sync In Successful. \n"
				+ "Application will be closed now.Please Relaunch the Application to get the refreshed data.";

	sa.assertEquals(ActualMsg, ExpectedMsg,
	"FAIL:User  not able to access  Manual syncin when the Manual syncin is checked");

		
		
				sa.assertAll();
	}
	

	
//PA089-Verify if Guest User able to access the Delete StudyFiles/Reports Module when the Delete StudyFiles/Reports Privilege is checked 
	
//PA091-Verify if Guest User able to access the Camera Access Module when the Camera Access Privilege is checked 
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA091-Verify if Guest User able to access the Camera Access Module when the Camera Access Privilege is checked")

	public void PA091() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA091-Verify if Guest User able to access the Camera Access Module when the Camera Access Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.capture_Camera_Img();

		// Capture the expected Image added to the Asset placeholder 1
		assetCreationPage.Capture_AsstImg1("Expected_Asst36_AsstCreation");

		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset10", "10", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		
		assetHubPage = assetCreationPage.clickBackBtn();

		// Again select the above Asset created to move to Asset detail page and then
		// click the Edit asset button
		Thread.sleep(2000);
		assetDetailsPage = assetHubPage.click_assetTile("Asset10");
		Thread.sleep(500);
		
		assetCreationPage = assetDetailsPage.click_assetEditBtn();

		// Capture the actual Image saved to the Asset placeholder 1
		assetCreationPage.Capture_AsstImg1("Actual_Asst36_AsstCreation");

		boolean status_ImgCompare = tu.compareImage("Expected_Asst36_AsstCreation", "Actual_Asst36_AsstCreation");

		sa.assertFalse(status_ImgCompare, "FAIL: The Asset Image saved is not same as what was captured");
		sa.assertAll();

		
		
				sa.assertAll();
	}
//PA093-Verify if Guest User able to access the Create Pass Fail Template Module when the Create Pass Fail Template Privilege is checked 

//PA095-Verify if Guest User able to access the Edit Pass Fail Template Module when the Edit Pass Fail Template Privilege is checked 

//PA097-Verify if Guest User able to access the Delete Pass Fail Template Module when the Delete Pass Fail Template Privilege is checked 
	
//PA099-Verify if Guest User able to access the Audit Trail Module when the Audit Trail Privilege is checked
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA099-Verify if Guest User able to access the Audit Trail Module when the Audit Trail Privilege is checked")

	public void PA099() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA099-Verify if Guest User able to access the Audit Trail Module when the Audit Trail Privilege is checked");
		SoftAssert sa = new SoftAssert();
		

		MainHubPage=PoliciesPage.click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		
				sa.assertEquals(AuditPage.AuditHeadTitleVisible(),true,
						"FAIL: User unable to access Audit Trail module even whwn the audit trail is checked");
				sa.assertAll();
	}	
	
//PA101-Verify if Guest User able to access the Policies Module when the Policies Privilege is checked

	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA101-Verify if Guest User able to access the Policies Module when the Policies Privilege is checked")

	public void PA0101() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA101-Verify if Guest User able to access the Policies Module when the Policies Privilege is checked");
		SoftAssert sa = new SoftAssert();
		
		sa.assertEquals(PoliciesPage.IsPolicies_screenDisplayed(),true,
				"FAIL: User unable to access Audit Trail module even whwn the audit trail is checked");
		
				sa.assertAll();
	}	
	
	
//PA103-Verify if Guest User able to access the Preferences Module when the Preferences Privilege is checked 
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA103-Verify if Guest User able to access the Preferences Module when the Preferences Privilege is checked ")

	public void PA0103() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA103-Verify if Guest User able to access the Preferences Module when the Preferences Privilege is checked ");
		SoftAssert sa = new SoftAssert();
		
		PreferencesPage=PoliciesPage.Clickpreference_TAB();
		sa.assertEquals(PreferencesPage.IsPreferences_screenDisplayed(),true,
				"FAIL: User unable to access Audit Trail module even whwn the audit trail is checked");
		
				sa.assertAll();
	}	
	

//PA105-Verify if Guest User able to access the Hardware Maintenance Module when the Hardware Maintenance Privilege is checked 
	
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA105-Verify if Guest User able to access the Hardware Maintenance Module when the Hardware Maintenance Privilege is checked ")

	public void PA0105() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA105-Verify if Guest User able to access the Hardware Maintenance Module when the Hardware Maintenance Privilege is checked ");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=PoliciesPage.click_BackBtn();
		SelectBaseStationPage=MainHubPage.Click_Discover();
		
		sa.assertEquals(SelectBaseStationPage.SelectBaseStationTitle_state(), true,
				"Fail: Guest unable to access Verification module  ");
		sa.assertAll();
		
	}	


/*CRT Dependent
	//PA087-Verify if Guest User able to access the Create Reports Module when the Create Reports Privilege is checked 
	@Test(priority=19,groups = { "Sanity",
	"Regression" }, description = "PA087-Verify if Guest User able to access the Create Reports Module when the Create Reports Privilege is checked ")

	public void PA087() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA087-Verify if Guest User able to access the Create Reports Module when the Create Reports Privilege is checked  ");
		SoftAssert sa = new SoftAssert();

		System.out.println("CRT DEPENDENT from build 16");
		MainHubPage=PoliciesPage.click_BackBtn();
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
		

	}*/

	//PA089-Verify if Guest User able to access the Delete StudyFiles/Reports Module when the Delete StudyFiles/Reports Privilege is checked 


	@Test(priority=20,groups = { "Sanity",
	"Regression" }, description = "PA089-Verify if Guest User able to access the Delete StudyFiles/Reports Module when the Delete StudyFiles/Reports Privilege is checked ")

	public void PA089() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA089-Verify if Guest User able to access the Delete StudyFiles/Reports Module when the Delete StudyFiles/Reports Privilege is checked ");
		SoftAssert sa = new SoftAssert();

		
		MainHubPage=PoliciesPage.click_BackBtn();
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
		String ExpectMSG = "Qualification Study : \"manual 1 min sampling\"  deleted by User ID : \"kiranc1\", User Name : \"Guest\"";

		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Deletion of a Detailed report ");
		sa.assertAll();

	}





}