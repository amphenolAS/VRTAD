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
import com.advrt.pages.assetCreationPage;
import com.advrt.pages.MainHubPage;
import com.advrt.pages.UserManagementPage_Manual;
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.AuditPage;
import com.advrt.pages.assetHubPage;
import com.advrt.pages.FileManagementPage;
import com.advrt.pages.FM_SyncInPage;
import com.advrt.pages.SyncInAssetListPage;
import com.advrt.pages.ADUM_page;
import com.advrt.pages.assetDetailsPage;
import com.advrt.pages.EquipmentHubPage;
import com.advrt.pages.NewEquipmentCreation_Page;
import com.advrt.pages.Copyassetpage;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.pages.Equipment_IRTDHubPage;
import com.advrt.pages.Setup_defineSetupPage;
import com.advrt.pages.Setup_SensorConfigPage;
import com.advrt.pages.Equipment_IRTDDetailspage;
import com.advrt.pages.Setup_GroupSensorsPage;
import com.advrt.pages.Setup_CalculationsPage;
import com.advrt.pages.Setup_QualParamPage;
import com.advrt.pages.CopySetuppage;
import com.advrt.pages.Setup_ReviewPage;
import com.advrt.utility.ADUserManagementUtility;
import com.advrt.utility.TestUtilities;

public class AD_Setup_Admin extends BaseClass {

	public AD_Setup_Admin() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();

	// Initialization of pages
	Copyassetpage Copyassetpage;
	CopySetuppage CopySetuppage;
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
	FM_SyncInPage SyncInPage;
	SyncInAssetListPage SyncInAssetListPage;
	EquipmentHubPage EquipmentHubPage;
	NewEquipmentCreation_Page NewEquipmentCreation_Page;
	Equipment_IRTDHubPage Equipment_IRTDHubPage;
	Equipment_IRTDDetailspage Equipment_IRTDDetailspage;
	Setup_defineSetupPage defineSetupPage;
	Setup_SensorConfigPage Setup_SensorConfigPage;
	Setup_GroupSensorsPage Setup_GroupSensorsPage;
	Setup_CalculationsPage Setup_CalculationsPage ;
	Setup_QualParamPage Setup_QualParamPage;
	Setup_ReviewPage Setup_ReviewPage ;
	assetCreationPage assetCreationPage;
	static String AdmnUN = "User1";

	// Before All the tests are conducted
	@BeforeClass
	// @BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER" + "_AD_Setup_Admin" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		// extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		// extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		// extent.addSystemInfo("ScriptVersion-Git",
		// prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD-UM Test in Progress..");

		// Rename the VRT Data Files folder if exists in order to make the system
		// default
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service", "DataFiles");
		// Copy the Default DataFIles folder from Test Data to the App service location.
		String SrcLocation = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\DataFiles";
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
		UserLoginPopup_UserCommentTextBox("1", "111111", "created");
		tu.click_OK_popup();
		Thread.sleep(1000);
		ADUM_page = PoliciesPage.ClickUM_Tab_AD();
		ADUM_page.select_grp("Automation");// Automation
		// AD_UMPage.Select_user();
		// ADUM_page.select_user(0);
		ADUM_page.enterNewUserTitle("Manager");
		ADUM_page.SelectUType("SystemAdministrator");
		Thread.sleep(1000);
		ADUM_page.ClickNewUserSaveButton();

		UserLoginPopup_UserCommentTextBox("1", "111111", "created");
		tu.click_OK_popup();
		tu.click_OK_popup();
		Thread.sleep(2000);

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("ruchika1", "Amphenol@123");
		ADUM_page = MainHubPage.ClickAdminTile_ADUM();
		// AD_UMPage.Select_grp();
		ADUM_page.select_grp("Automation");// Automation
		// AD_UMPage.Select_user();
		// ADUM_page.select_user(0);
		DefaultUserPrivilages_page = ADUM_page.SelectUType1("NewUserType");
		DefaultUserPrivilages_page.Enter_NewUserType("SystemAdministrator");
		DefaultUserPrivilages_page.click_UPSetupCreationCheckBox();
		DefaultUserPrivilages_page.click_UPModifySetupCheckBox();
		// DefaultUserPrivilages_page.click_UPSetupdeleteCheckBox();
		ADUM_page = DefaultUserPrivilages_page.clickOn_UpdateBtn("ruchika1", "Amphenol@123", "text");
		MainHubPage = ADUM_page.ClickBackButn();
		ADUM_page = MainHubPage.ClickAdminTile_ADUM();
		Thread.sleep(1000);
		ADUM_page.select_grp("Automation");

		ADUM_page.SelectUType("SystemAdministrator");
		ADUM_page.enterNewUserTitle("Manager");
		Thread.sleep(1000);
		ADUM_page.ClickNewUserSaveButton();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "updated");
		tu.click_OK_popup();
		MainHubPage = ADUM_page.ClickBackButn();

		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage("ruchika1", "Amphenol@123","comm");
		SyncInPage.enter_Filepath("syncin");
		SyncInPage.click_FltrBtn();
		Thread.sleep(1000);
		SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
		SyncInAssetListPage.click_EquipmentCheckBox();
		SyncInAssetListPage.click_SelectAllBtn();
		SyncInAssetListPage.click_OkBtn();
		SyncInAssetListPage.click_AlrtYesBtn();
		Thread.sleep(8000);
		SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
		Thread.sleep(2000);

	}

	// After All the tests are conducted
	// @AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("AD_Setup_Admin  test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("ruchika1", "Amphenol@123");
		// ADUM_page = MainHubPage.ClickAdminTile_UMpage();
		// PoliciesPage = MainHubPage.ClickAdminTile_Polpage();
		// ADUM_page = PoliciesPage.ClickUM_Tab_AD();
	}

	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException, Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FailED IS # " + result.getName() + " #"); // to add name in extent
																								// report
			// TearDown of the App
			extentTest.log(LogStatus.FAIL, "TEST CASE FailED IS # " + result.getThrowable() + " #"); // to add
																										// error/exception
																										// in extent
																										// report

			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); // to add screenshot in extent
																							// report
			// extentTest.log(LogStatus.Fail, extentTest.addScreencast(screenshotPath));
			// //to add screencast/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
			// String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver,
			// result.getName());
			// extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));
			// //to add screenshot in extent report
		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
		// MainLoginPage.resetWebElements();
		Thread.sleep(5000);
		driver.quit();
	}

	/********
	 * Test Cases
	 * 
	 * @throws AWTException
	 * @throws IOException
	 *********/

	// Setup_Audit_001-Verify the Audit trail entry  while login with the  AD   group

	@Test(priority = 0,description = "Setup_Audit_001-Verify the Audit trail entry  while login with the  AD   group")

	public void Setup_Audit_001() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_001-Verify the Audit trail entry  while login with the  AD   group");

		SoftAssert sa = new SoftAssert();

		System.out.println("ok");

	}
	
	//Setup_Audit_002-Verify the Audit trail entry  while Creating a new Setup with the  AD   group

	@Test(priority = 1, description = "Setup_Audit_002-Verify the Audit trail entry  while Creating a new Setup with the  AD group")

	public void Setup_Audit_002() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_002-Verify the Audit trail entry  while Creating a new Setup with the  AD group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName("test");
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("300");
		defineSetupPage.enter_defineSetupPage_SOP("10");
		defineSetupPage.enter_defineSetupPage_LoadDesc("load");
		defineSetupPage.enter_defineSetupPage_comments("com");

		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.select_Sensortype_temp();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("TMP");
		Setup_SensorConfigPage.Click_assignBtn();

		Setup_SensorConfigPage.select_Sensortype_Pr();
		Setup_SensorConfigPage.Enter_Num_To("5");
		Setup_SensorConfigPage.Enter_SensorLabel("PSR");
		Setup_SensorConfigPage.Click_assignBtn();
		// Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.click_DfltGrp_Btn();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
		Setup_ReviewPage.clickSaveBtn();
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		assetDetailsPage = Setup_ReviewPage.click_backBtn();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();

		String expaudit = "  Setup \"test\" is created by User ID : \"Ruchika1\", User Name : \"Ruchika1\"";

		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");

		sa.assertAll();
	}
	
	//Setup_Audit_003-Verify the Audit trail entry  while Editing  a Setup in Define setup Page with the  AD   group
	
	
	@Test(priority = 2,description = "Setup_Audit_003-Verify the Audit trail entry  while Editing  a Setup in Define setup Page with the  AD   group")

	public void Setup_Audit_003() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_003-Verify the Audit trail entry  while Editing  a Setup in Define setup Page with the  AD   group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("test");
		
		defineSetupPage = assetDetailsPage.click_editStupBtn();
		defineSetupPage.enter_defineSetupPage_LoadDesc("aaa");
		
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
		Setup_ReviewPage.clickSaveBtn();
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		assetDetailsPage = Setup_ReviewPage.click_backBtn();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();

		String expaudit = "Setup: \"test\" is modified in Tab : \"Define Setup\" by User ID : \"Ruchika1\" , User Name: \"Ruchika1\"";

		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");

		sa.assertAll();
	
	
}
	
	//Setup_Audit_003-Verify the Audit trail entry  while Editing  a Setup in Define setup Page with the  AD   group
	
	
	/*	@Test(priority = 3,description = "Setup_Audit_004-Verify the Audit trail entry  while Editing  a Setup in sensor configuration page with the  AD   group")

		public void Setup_Audit_004() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"Setup_Audit_004-Verify the Audit trail entry  while Editing  a Setup in sensor configuration page with the  AD   group");

			SoftAssert sa = new SoftAssert();

			assetHubPage = MainHubPage.Click_AssetTile();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetDetailsPage.Click_SetupName("test");
			
			defineSetupPage = assetDetailsPage.click_editStupBtn();
			Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			Setup_SensorConfigPage.Enter_TemperatureCount_textField("6");
			
			Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
			
			Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
			Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
			Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
			Setup_ReviewPage.clickSaveBtn();
			UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
			assetDetailsPage = Setup_ReviewPage.click_backBtn();

			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();
			AuditPage = MainHubPage.ClickAuditTitle();

			String expaudit = "Setup: \"test\" is modified in Tab : \"Define Setup\" by User ID : \"Ruchika1\" , User Name: \"Ruchika1\"";

			sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");

			sa.assertAll();
		
		
	}
	*/
	
	
	//Setup_Audit_005-Verify the Audit trail entry  while Editing  a Setup in Group sensors  page with the  AD   group
	
	
	@Test(priority = 4,description = "Setup_Audit_005-Verify the Audit trail entry  while Editing  a Setup in Group sensors  page with the  AD   group")

	public void Setup_Audit_005() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_005-Verify the Audit trail entry  while Editing  a Setup in Group sensors  page with the  AD   group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("test");
		
		defineSetupPage = assetDetailsPage.click_editStupBtn();
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();	
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_GroupSensorsPage.Selectgroup(0);
		Setup_GroupSensorsPage.click_GroupEditIcon(0);
		Setup_GroupSensorsPage.Edit_GrpTitle_Save("NewTitle");
		
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();	
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
		Setup_ReviewPage.clickSaveBtn();
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		assetDetailsPage = Setup_ReviewPage.click_backBtn();

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();

		String expaudit = "Setup: \"test\" is modified in Tab : \"Group Sensors\" by User ID : \"Ruchika1\" , User Name: \"Ruchika1\"";

		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");

		sa.assertAll();
	
	
}
	
	
	//Setup_Audit_006-Verify the Audit trail entry  while Editing  a Setup in Calculations  page with the  AD   group
	
	@Test(priority = 5,description = "Setup_Audit_006-Verify the Audit trail entry  while Editing  a Setup in Calculations  page with the  AD   group")

	public void Setup_Audit_006() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_006-Verify the Audit trail entry  while Editing  a Setup in Calculations  page with the  AD   group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("test");
		
		defineSetupPage = assetDetailsPage.click_editStupBtn();
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();	
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
		
		Setup_CalculationsPage.enter_Zval("12");
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
		Setup_ReviewPage.clickSaveBtn();
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();

		String expaudit = "Setup: \"test\" is modified in Tab : \"Calculations\" by User ID : \"Ruchika1\" , User Name: \"Ruchika1\"";

		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");

		sa.assertAll();
	
	
	}
	
	
	//Setup_Audit_007-Verify the Audit trail entry  while Editing  a Setup in Qualification Parameters  page with the  AD   groups


	@Test(priority = 6,description = "Setup_Audit_007-Verify the Audit trail entry  while Editing  a Setup in Qualification Parameters  page with the  AD   groups")

	public void Setup_Audit_007() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_007- Verify the Audit trail entry  while Editing  a Setup in Qualification Parameters  page with the  AD   groups");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("test");
		
		defineSetupPage = assetDetailsPage.click_editStupBtn();
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();	
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		
		Setup_QualParamPage.select_SR("1 Second");
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
		Setup_ReviewPage.clickSaveBtn();
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();

		String expaudit = "Setup: \"test\" is modified in Tab : \"Qualification Parameters\" by User ID : \"Ruchika1\" , User Name: \"Ruchika1\"";

		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");

		sa.assertAll();
	
	
	}
	
//Setup_Audit_008-Verify the Audit trail entry  while creating  a new Setup using copy as new Setup functionality in review screen with the  AD   group


	@Test(priority = 7,description = "Setup_Audit_008-Verify the Audit trail entry  while creating  a new Setup using copy as new Setup functionality in review screen with the  AD   group")

	public void Setup_Audit_008() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_008-Verify the Audit trail entry  while creating  a new Setup using copy as new Setup functionality in review screen with the  AD   group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("test");
		
		defineSetupPage = assetDetailsPage.click_editStupBtn();
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();	
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		Setup_QualParamPage.select_SR("2 Seconds");
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
		Setup_ReviewPage.clickSaveBtn();
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		Setup_ReviewPage.click_CopyAsNewSetup_Button();
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		Setup_ReviewPage.Enter_NewSetupName("copysetup1");
		
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();

		String expaudit = "Setup: \"copysetup1\" copied by User ID : \"Ruchika1\" , User Name : \"Ruchika1\"";

		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");
		sa.assertAll();
	
	}

//Setup_Audit_009-Verify the Audit trail entry  while creating   Setup report using create Setup report functionality in review screen with the  AD   group

	
	@Test(priority = 8,description = "Setup_Audit_009-Verify the Audit trail entry  while creating   Setup report using create Setup report functionality in review screen with the  AD   group")

	public void Setup_Audit_009() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_009-Verify the Audit trail entry  while creating   Setup report using create Setup report functionality in review screen with the  AD   group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("test");
		
		defineSetupPage = assetDetailsPage.click_editStupBtn();
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();	
		Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
		Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
	
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
		//Setup_ReviewPage.clickSaveBtn();
		//UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		Setup_ReviewPage.create_setupReport_Button();
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		Setup_ReviewPage.click_PDFpopup_OkBtn();
		Thread.sleep(3000);
		Setup_ReviewPage.perform_alt_tab_OP();
		assetDetailsPage = Setup_ReviewPage.click_backBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();

		String expaudit = "Setup Review:  Setup report creation performed by User ID : \"Ruchika1\", User Name : \"Ruchika1\"";

		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");
		sa.assertAll();
	
	}
	
	
	
	
	//Setup_Audit_010-Verify the Audit trail entry  while creating   Setup report using Print  Setup report functionality in setup  screen with the  AD   group
	

   //will be tested manually


//Setup_Audit_012-Verify the Audit trail entry  while copying the  file using copy to drive functionality under Report  tile with the  AD   group
	
	@Test(priority = 11,description = "Setup_Audit_012-Verify the Audit trail entry  while copying the  file using copy to drive functionality under Report  tile with the  AD   group")

	public void Setup_Audit_012() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_012-Verify the Audit trail entry  while copying the  file using copy to drive functionality under Report  tile with the  AD   group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile1("test");
		Thread.sleep(1000);
		assetDetailsPage.click_Report_CopyToDrive_Btn();
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "reports");
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();

		String expaudit = "Report - \"test\" , \"Copy to drive\" operation was performed by User Id : \"Ruchika1\", User Name : \"Ruchika1\" to \"C:\\Users\\Ruchika.Behura\\eclipse-workspace\\ADVRT\\src\\test\\resources\\TestData\\AutoLogs\\Se=(test)=()=0=11-Dec-2023 15-27-41=.pdf\"";

		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");
		sa.assertAll();
	
	}
	
	//Setup_Audit_013-Verify the Audit trail entry  while Copying the Setup from one Assest to Another Assest with the  AD   group
	
	@Test(priority = 12,description = "Setup_Audit_013-Verify the Audit trail entry  while Copying the Setup from one Assest to Another Assest with the  AD   group\r\n"
			+ "")

	public void Setup_Audit_013() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_013-Verify the Audit trail entry  while Copying the Setup from one Assest to Another Assest with the  AD   group\r\n"
				+ "");

		SoftAssert sa = new SoftAssert();

	assetHubPage = MainHubPage.Click_AssetTile();
	assetCreationPage = assetHubPage.ClickAddAssetBtn();
	assetCreationPage.assetCreation("Ast004", "04", "HeatBath", "AAS", "HYBD");

	tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
	tu.click_Close_alertmsg();
	assetHubPage = assetCreationPage.clickBackBtn();
	assetDetailsPage = assetHubPage.click_assetTile("Ast004");
	defineSetupPage.enter_defineSetupPage_setupName("Set001");
	defineSetupPage.enter_defineSetupPage_SensorCount("50");
	Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
	Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
	Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
	Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
	Setup_SensorConfigPage.select_Sensortype_temp();
	Setup_SensorConfigPage.Enter_Num_To("5");
	Setup_SensorConfigPage.Enter_SensorLabel("TMP");
	Setup_SensorConfigPage.Click_assignBtn();
	// Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn();
	Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_LessSnsrconfig();
	Setup_GroupSensorsPage.click_DfltGrp_Btn();
	Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab();
	Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
	Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
	Setup_ReviewPage.clickSaveBtn();
	UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
	assetDetailsPage = Setup_ReviewPage.click_backBtn();
	assetHubPage = assetDetailsPage.ClickBackBtn();
	assetDetailsPage = assetHubPage.click_assetTile("Ast004");
	assetDetailsPage.Click_SetupName("Set001");
	CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
	CopySetuppage.clickONCheckBOX_1();
	CopySetuppage.click_copy_Btn();
	UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
	CopySetuppage.select_alertOption("Yes");
	assetDetailsPage = CopySetuppage.Click_Back_Btn();
	assetHubPage = assetDetailsPage.ClickBackBtn();
	MainHubPage = assetHubPage.click_BackBtn();
	AuditPage = MainHubPage.ClickAuditTitle();

	String expaudit = "\"1\" setup(s) copied successfully by User ID : \"Ruchika1\" , User Name: \"Ruchika1\"";

	sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");
	sa.assertAll();
	}
	
	
//Setup_Audit_014-Verify the Audit trail entry  while deleting the  file using delete functionality under Report  tile with the  AD   group	

	@Test(priority = 13,description = "Setup_Audit_014- Verify the Audit trail entry  while deleting the  file using delete functionality under Report  tile with the  AD   group	")

	public void Setup_Audit_014() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_014- Verify the Audit trail entry  while deleting the  file using delete functionality under Report  tile with the  AD   group	");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupReportsButton();
		assetDetailsPage.Select_ReportFile1("test");
		assetDetailsPage.Click_DeleteBtn_report();	
		assetDetailsPage.clickYes_delete();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		
		String expaudit ="Setup Report : \"test\"  deleted by User ID : \"Ruchika1\"";
		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup creation is not audited");
		sa.assertAll();
}
	
	
	//Setup_Audit_015-Verify the Audit trail entry  while copying the Setup using copy to drive functionality with the  AD   group
	
	
	
	@Test(priority = 14,description = "Setup_Audit_012-Verify the Audit trail entry  while copying the  file using copy to drive functionality under Report  tile with the  AD   group")

	public void Setup_Audit_015() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_012-Verify the Audit trail entry  while copying the  file using copy to drive functionality under Report  tile with the  AD   group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("test");
		Thread.sleep(1000);
		assetDetailsPage.click_Setup_CopyToDrive();
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "reports");
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();

		String expaudit = "Setup - \"test\" , \"Copy to drive\" operation was performed by User Id : \"Ruchika1\", User Name : \"Ruchika1\" to \"C:\\Users\\Ruchika.Behura\\eclipse-workspace\\ADVRT\\src\\test\\resources\\TestData\\AutoLogs\\00090FFE00016573329E.cfg\"";

		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: Setup copy drive is not audited");
		sa.assertAll();
	
	}
	
	//Setup_Audit_016-Verify the Audit trail entry  while copying the Qualification file using copy to drive functionality under qualification tile with the  AD   group
	
	@Test(priority = 15,description = "Setup_Audit_016-Verify the Audit trail entry  while copying the Qualification file using copy to drive functionality under qualification tile with the  AD   group")

	public void Setup_Audit_016() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_016-Verify the Audit trail entry  while copying the Qualification file using copy to drive functionality under qualification tile with the  AD   group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		Thread.sleep(1000);
		
		assetDetailsPage.click_Qual_CopyToDrive_Btn();
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "reports");
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();

		String expaudit = "Qualification - \"manual 1 min sampling\" , \"Copy to drive\" operation was performed by User Id : \"Ruchika1\", User Name : \"Ruchika1\" to \"C:\\Users\\Ruchika.Behura\\Pictures\\Feedback\\{72BD0E9A-C288-4B13-9D2C-C1FC0F01EE25}\\20200318131241_20200318155600_60_637201438503438416.rtq\"";

		sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: qual copy drive is not audited");
		sa.assertAll();
	}
	

//Setup_Audit_017-Verify the Audit trail entry  while deleting the  file using delete functionality under Document tile with the  AD   group


	@Test(priority = 16,description = "Setup_Audit_017-Verify the Audit trail entry  while deleting the  file using delete functionality under Document tile with the  AD   group")

	public void Setup_Audit_017() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_017-Verify the Audit trail entry  while deleting the  file using delete functionality under Document tile with the  AD   group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.Select_DocFile("LTR-40_Cooling.pdf");
		assetDetailsPage.Click_DeleteBtn_report();
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		tu.click_YesBtn_popup();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Document: \"LTR-40_Cooling.pdf\"  deleted by User ID : \"Ruchika1\", User Name : \"Ruchika1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Deletion of a  document under Documents tile");
		sa.assertAll();
}
	
	
	//Setup_Audit_018-Verify the Audit trail entry  while deleting the Qualification file using delete functionality under Qualification tile with the  AD   group
	


	@Test(priority = 17,description = "Setup_Audit_018-Verify the Audit trail entry  while deleting the Qualification file using delete functionality under Qualification tile with the  AD   group")

	public void Setup_Audit_018() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"Setup_Audit_018-Verify the Audit trail entry  while deleting the Qualification file using delete functionality under Qualification tile with the  AD   group");

		SoftAssert sa = new SoftAssert();

		assetHubPage = MainHubPage.Click_AssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Select_QualFile("manual 1 min sampling");
		assetDetailsPage.click_DeleteQualificationButton();
		UserLoginPopup_UserCommentTextBox("Ruchika1", "Amphenol@123", "comment");
		tu.click_YesBtn_popup();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String ExpectMSG = "Qualification Study : \"manual 1 min sampling\"  deleted by User ID : \"Ruchika1\", User Name : \"Ruchika1\"";
		sa.assertEquals(Actionmsg, ExpectMSG,
				"FAIL: Audit trial record does not exists for Delete study file activity");
		sa.assertAll();
}
	
	
	
	
	//Setup_Audit_023-Verify if Audit trail is displayed correctly for user wrong credentials
	
		@Test(priority = 18,description = "Setup_Audit_013-Verify the Audit trail entry  while Copying the Setup from one Assest to Another Assest with the  AD   group\r\n"
				+ "")

		public void Setup_Audit_023() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"Setup_Audit_013-Verify the Audit trail entry  while Copying the Setup from one Assest to Another Assest with the  AD   group\r\n"
					+ "");

			SoftAssert sa = new SoftAssert();

			assetHubPage = MainHubPage.Click_AssetTile();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetDetailsPage.Click_SetupName("Set001");
			CopySetuppage = assetDetailsPage.click_CopyStup_Btn();
			CopySetuppage.clickONCheckBOX_1();
			CopySetuppage.click_copy_Btn();
			UserLoginPopup_UserCommentTextBox("2", "459123", "comment");
			
			assetDetailsPage = CopySetuppage.Click_Back_Btn();
			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();
			AuditPage = MainHubPage.ClickAuditTitle();

			String expaudit = "Login attempt failed for User ID:\"2\", User Name: \"Unknown user\"";

			sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: invalid details is not audited");
			sa.assertAll();
			}
		
		
	//Setup_Audit_011-Verify the Audit trail entry  while Deleting the  Setup with the  AD   group
		

		@Test(priority = 19,description = "Setup_Audit_011-Verify the Audit trail entry  while Deleting the  Setup with the  AD   group"
				+ "")

		public void Setup_Audit_011() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"Setup_Audit_011-Verify the Audit trail entry  while Deleting the  Setup with the  AD   group");

			SoftAssert sa = new SoftAssert();

			assetHubPage = MainHubPage.Click_AssetTile();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetDetailsPage.Click_SetupName("manual 1 min sampling");
			assetDetailsPage.Click_DeleteBtn_report();
			UserLoginPopup_UserCommentTextBox("2", "459123", "comment");
			assetDetailsPage.clickYes_delete();
			
			assetDetailsPage = CopySetuppage.Click_Back_Btn();
			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();
			AuditPage = MainHubPage.ClickAuditTitle();

			String expaudit = "Setup : \"manual 1 min sampling\"  deleted by User ID : \"Ruchika1\", User Name : \"Ruchika1\"";

			sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL: delete setup details is not audited");
			sa.assertAll();
			
            }

		
		//Setup_Audit_024-Verify if Audit trail is displayed correctly for user without previleges
		
		@Test(priority = 20,description = "Setup_Audit_024-Verify if Audit trail is displayed correctly for user without previleges"
				+ "")

		public void Setup_Audit_024() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"Setup_Audit_024-Verify if Audit trail is displayed correctly for user without previleges");

			SoftAssert sa = new SoftAssert();

			assetHubPage = MainHubPage.Click_AssetTile();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetDetailsPage.Click_SetupName("manual 1 min sampling");
			assetDetailsPage.click_InitiateQualBtn();
			assetDetailsPage = CopySetuppage.Click_Back_Btn();
			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();
			AuditPage = MainHubPage.ClickAuditTitle();

			String expaudit = "User ID: \"Ruchika1\"  , User Name :\"Ruchika1\" has insufficient Privileges to perform Qualification Study";

			sa.assertEquals(AuditPage.get_auditEvent_text(), expaudit, "FAIL:without previleges details is not audited");
			sa.assertAll();
			
		}
}