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
import com.advrt.pages.SyncInAssetListPage;
import com.advrt.pages.FileManagementPage;
import com.advrt.pages.SelectBaseStationPage;
import com.advrt.pages.Equipment_IRTDHubPage;
import com.advrt.pages.Equipment_IRTDDetailspage;
import com.advrt.utility.TestUtilities;


public class AD_GuestPrivilageAccessTest1 extends BaseClass{

	public AD_GuestPrivilageAccessTest1() throws IOException {
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
	static String AdmnUN = "User1";


	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"AD_GuestPrivilageAccessTest1"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD_GuestPrivilageAccessTest1 Test in Progress..");



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
		Thread.sleep(500);
		PoliciesPage.clickOn_AcceptBtn();	
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();
		Thread.sleep(1000);
		AD_UMPage=PoliciesPage.click_AD_UMHeader();
		//Thread.sleep(5000);
		AD_UMPage.select_grp("QA Testers");
		//Thread.sleep(5000);
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
		AD_UMPage.select_grp("QA Testers");
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("AllUser");
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
		System.out.println("AD GuestPrivilageAccess1 test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();
		AD_UMPage.select_grp("QA Testers");
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("NewUserType");
		DefaultUserPrivilages_page=AD_UMPage.newUserType("AllUser");
		//PoliciesPage=AD_UMPage.AD_Click_Policy();
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



	//PA056-Verify if Guest User not able to access the User Management Module when the User Management Privilege is unchecked

	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "PA056-Verify if Guest User not able to access the User Management Module when the User Management Privilege is unchecked")

	public void PA056() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("PA056-Verify if Guest User not able to access the User Management Module when the User Management Privilege is unchecked");
		SoftAssert sa = new SoftAssert();

		DefaultUserPrivilages_page.Click_Create_UserManagement();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		//tu.click_OK_popup();
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		Thread.sleep(500);
		PoliciesPage=MainHubPage.ClickAdminTile_Polpage();

		sa.assertEquals(PoliciesPage.IsUMHeaderTabEnabled(),false,
				"FAIL:User is able to access UserManagement screen");


		sa.assertAll();

	}


	//PA058-Verify if Guest User not able to access the Run Qualification Module when the Run Qualification Privilege is unchecked

	@Test(priority=1,groups = { "Sanity",
	"Regression" }, description = "PA058-Verify if Guest User not able to access the Run Qualification Module when the Run Qualification Privilege is unchecked")

	public void PA058() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA058-Verify if Guest User not able to access the Run Qualification Module when the Run Qualification Privilege is unchecked");
		SoftAssert sa = new SoftAssert();

		DefaultUserPrivilages_page.Click_RunQualification();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
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
		Setup_SensorConfigPage = Setup_defineSetupPage.click_defineSetupPage_nxtBtn();
		Thread.sleep(500);
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
		assetDetailsPage.Click_SetupName("SetUp");
		assetDetailsPage.click_InitiateQualBtn();
		Thread.sleep(500);
		String ActualMsg=tu.get_AlertMsg_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access the Run Qualification Module when the Run Qualification Privilege is unchecked");


		sa.assertAll();
	}




	//PA064-Verify if Guest User not able to access the Create Assets Module when the Create Assets Privilege is unchecked


	@Test(priority=4,groups = { "Sanity",
	"Regression" }, description = "PA064-Verify if Guest User not able to access the Create Assets Module when the Create Assets Privilege is unchecked")

	public void PA064() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA064-Verify if Guest User not able to access the Create Assets Module when the Create Assets Privilege is unchecked\r\n"
						+ "");
		SoftAssert sa = new SoftAssert();

		DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		//PoliciesPage=MainHubPage.ClickAdminTile_Polpage();
		assetHubPage=MainHubPage.Click_AssetTile2();
		// assetCreationPage= assetHubPage.Click_AddAssetButton();//
		assetHubPage.Click_AddButton();
		Thread.sleep(500);

		String ActualMsg=tu.get_AlertMsg_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access the Create Assets Module when the Create Assets Privilege is unchecked");


		sa.assertAll();
	}


	//PA066-Verify if Guest User not able to access the Modify Assets Module when the Modify Assets Privilege is unchecked


	@Test(priority=5,groups = { "Sanity",
	"Regression" }, description = "PA066-Verify if Guest User not able to access the Modify Assets Module when the Modify Assets Privilege is unchecked")

	public void PA066() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA066-Verify if Guest User not able to access the Modify Assets Module when the Modify Assets Privilege is unchecked");
		SoftAssert sa = new SoftAssert();


		DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();
		DefaultUserPrivilages_page.Click_Create_ModifyAssets();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");

		assetCreationPage.assetCreationWithAllFieldEntry("Asset04", "04", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		//assetDetailsPage=assetCreationPage.click_BackBtn();//
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage=assetHubPage.click_assetTile("Asset04");
		assetDetailsPage.click_assetEditBtn_alrt();

		Thread.sleep(500);

		String ActualMsg=tu.get_AlertMsg_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access  Modify Assets the Module when the Modify Assets  Privilege is unchecked");


		sa.assertAll();
	}



	//PA068-Verify if Guest User not able to access the Delete Assets Module when the Delete Assets Privilege is unchecked


	@Test(priority=6,groups = { "Sanity",
	"Regression" }, description = "PA068-Verify if Guest User not able to access the Delete Assets Module when the Delete Assets Privilege is unchecked")

	public void PA014() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA068-Verify if Guest User not able to access the Delete Assets Module when the Delete Assets Privilege is unchecked");
		SoftAssert sa = new SoftAssert();


		DefaultUserPrivilages_page.Click_DeleteAssets();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset05", "05", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		//assetDetailsPage=assetCreationPage.click_BackBtn();//
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage=assetHubPage.click_assetTile("Asset05");
		assetDetailsPage.Click_DeleteAsset();
		//UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted."); 

		Thread.sleep(500);

		String ActualMsg=tu.get_AlertMsg_text();
		//String ActualMsg=tu.get_popup_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access  Modify Assets the Module when the Modify Assets  Privilege is unchecked");

		sa.assertAll();
	}


	//PA070-Verify if Guest User not able to access the Copy Files/Reports Module when the Copy Files/Reports Privilege is unchecked


	//PA072-Verify if Guest User not able to access the Create Setups Module when the Create Setups Privilege is unchecked

	@Test(priority=7,groups = { "Sanity",
	"Regression" }, description = "PA072-Verify if Guest User not able to access the Create Setups Module when the Create Setups Privilege is unchecked")

	public void PA072() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA072-Verify if Guest User not able to access the Create Setups Module when the Create Setups Privilege is unchecked");
		SoftAssert sa = new SoftAssert();

		//DefaultUserPrivilages_page.Click_Create_AssetCheckBox1();//must uncommit at suite level
		DefaultUserPrivilages_page.Click_Create_SetUp();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset06", "06", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		//assetDetailsPage=assetCreationPage.click_BackBtn();//
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage=assetHubPage.click_assetTile("Asset06");
		assetDetailsPage.click_NewStupCreateBtn_alert();
		Thread.sleep(500);

		String ActualMsg=tu.get_AlertMsg_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access  Create Setup Module when the Create Setup Privilege is unchecked");

		sa.assertAll();

	}		

	//PA074-Verify if Guest User not able to access the Modify Setups Module when the Modify Setups Privilege is unchecked

	@Test(priority=8,groups = { "Sanity",
	"Regression" }, description = "PA074-Verify if Guest User not able to access the Modify Setups Module when the Modify Setups Privilege is unchecked")

	public void PA074() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA074-Verify if Guest User not able to access the Modify Setups Module when the Modify Setups Privilege is unchecked");
		SoftAssert sa = new SoftAssert();

		//DefaultUserPrivilages_page.Click_Create_AssetCheckBox1()
		DefaultUserPrivilages_page.Click_Create_SetUp();
		DefaultUserPrivilages_page.Click_ModifySetup();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset07", "07", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		//assetDetailsPage=assetCreationPage.click_BackBtn();//
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage=assetHubPage.click_assetTile("Asset07");

		// Define Setup
		Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		Setup_defineSetupPage.clear_defineSetupPage_setupName();
		Setup_defineSetupPage.enter_defineSetupPage_setupName("SetUp9");
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
		assetDetailsPage.Click_SetupName("SetUp9");

		//Edit Setup
		//	Setup_defineSetupPage = assetDetailsPage.editStupBtn_Position_0();//click_editStupBtn1()
		assetDetailsPage.click_editStupBtn0();

		Thread.sleep(500);

		String ActualMsg=tu.get_AlertMsg_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access  modify Setup Module when the Modify Setup Privilege is unchecked");

		sa.assertAll();
	}


	//PA076-Verify if Guest User not able to access the Delete Setups Module when the Delete Setups Privilege is unchecked

	@Test(priority=9,groups = { "Sanity",
	"Regression" }, description = "PA076-Verify if Guest User not able to access the Delete Setups Module when the Delete Setups Privilege is unchecked")

	public void PA076() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA076-Verify if Guest User not able to access the Delete Setups Module when the Delete Setups Privilege is unchecked");
		SoftAssert sa = new SoftAssert();


		DefaultUserPrivilages_page.Click_DeleteSetUp();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset08", "08", "HeatBath", "Aas", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "5", "Weeks", "1st Asset Creation");
		UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
		Thread.sleep(1000);
		//assetDetailsPage=assetCreationPage.click_BackBtn();//
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage=assetHubPage.click_assetTile("Asset08");

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
		assetDetailsPage.Click_SetupName("SetUp1");

		assetDetailsPage.click_DeleteButton();
		//UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted."); 

		Thread.sleep(500);

		 String ActualMsg=tu.get_AlertMsg_text();
		//String ActualMsg=tu.get_popup_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access  modify Setup Module when the Modify Setup Privilege is unchecked");

		sa.assertAll();
	}



	//PA078-Verify if Guest User not able to access the Archive Data Module when the Archive Data Privilege is unchecked

	@Test(priority=19,groups = { "Sanity",
	"Regression" }, description = "PA078-Verify if Guest User not able to access the Archive Data Module when the Archive Data Privilege is unchecked")

	public void PA078() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA078-Verify if Guest User not able to access the Archive Data Module when the Archive Data Privilege is unchecked");
		SoftAssert sa = new SoftAssert();


		DefaultUserPrivilages_page.Click_ArchieveData();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		FileManagementPage=MainHubPage.ClickFileManagementTitle();
		FileManagementPage.Click_ArchiveTabwithcomment_alert("kiranc1","Amphenol@123","commit");

		Thread.sleep(500);


		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(tu.get_AlertMsg_text(), ExpectedMsg,
				"FAIL:User  able to access  Archieve when the Archieve is unchecked");


		sa.assertAll();}

	
	

	//Pa080-Verify if Guest User not able to access the Create Equipment Module when the Create Equipment Privilege is unchecked

	@Test(priority=10,groups = { "Sanity",
	"Regression" }, description = "Pa080-Verify if Guest User not able to access the Create Equipment Module when the Create Equipment Privilege is unchecked")

	public void PA080() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("Pa080-Verify if Guest User not able to access the Create Equipment Module when the Create Equipment Privilege is unchecked");
		SoftAssert sa = new SoftAssert();


		DefaultUserPrivilages_page.Click_Create_Equipmet();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		EquipmentHubPage=MainHubPage.ClickEquipmentTile();
		EquipmentHubPage.ClickAddButton_alrt();

		Thread.sleep(500);


		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(tu.get_AlertMsg_text(), ExpectedMsg,
				"FAIL:User  able to access  Equipment Module when the Equipment Privilege is unchecked");


		sa.assertAll();}

/*Edit buton not there
	//PA082-Verify if Guest User not able to access the Modify Equipment Module when the Modify Equipment Privilege is unchecked

	@Test(priority=11,groups = { "Sanity",
	"Regression" }, description = "PA082-Verify if Guest User not able to access the Modify Equipment Module when the Modify Equipment Privilege is unchecked")

	public void PA082() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA082-Verify if Guest User not able to access the Modify Equipment Module when the Modify Equipment Privilege is unchecked");
		SoftAssert sa = new SoftAssert();


		//DefaultUserPrivilages_page.Click_Create_Equipmet();//must uncommit while running in suite
		DefaultUserPrivilages_page.Click_Create_Equipmet();
		DefaultUserPrivilages_page.Click_ModifyEquipment();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		EquipmentHubPage=MainHubPage.ClickEquipmentTile();
		   NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
			
			//Creating 1st Equipment
		   NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EL010b", "010b");
			Thread.sleep(500);
				UserLoginPopup_UserCommentTextBox("kiranc1","Amphenol@123","usercommitted.");
			EquipmentHubPage=NewEquipmentCreation_Page.ClickBackBtn();


		Thread.sleep(500);


		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(tu.get_AlertMsg_text(), ExpectedMsg,
				"FAIL:User  able to access  Equipment Module when the Equipment Privilege is unchecked");


		sa.assertAll();}*/

	
	//PA084-Verify if Guest User not able to access the Delete Equipment Module when the Delete Equipment Privilege is unchecked

	@Test(priority=12,groups = { "Sanity",
	"Regression" }, description = "PA084-Verify if Guest User not able to access the Delete Equipment Module when the Delete Equipment Privilege is unchecked")

	public void PA084() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA084-Verify if Guest User not able to access the Delete Equipment Module when the Delete Equipment Privilege is unchecked");
		SoftAssert sa = new SoftAssert();


		DefaultUserPrivilages_page.Click_Create_Equipmet();
		DefaultUserPrivilages_page.Click_DeleteEquipment();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
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
			UserLoginPopup_UserCommentTextBox("kiranc1", "Amphenol@123", "usercomitted");
		Thread.sleep(1000);

		 String ActualMsg=tu.get_AlertMsg_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access  modify Setup Module when the Modify Setup Privilege is unchecked");


		sa.assertAll();}

	//PA086-Verify if Guest User not able to access the Manual Sync Module when the Manual Sync Privilege is unchecked

	@Test(priority=13,groups = { "Sanity",
	"Regression" }, description = "PA086-Verify if Guest User not able to access the Manual Sync Module when the Manual Sync Privilege is unchecked")

	public void PA086() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA086-Verify if Guest User not able to access the Manual Sync Module when the Manual Sync Privilege is unchecked");
		SoftAssert sa = new SoftAssert();

		DefaultUserPrivilages_page.Click_ManualSync();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.AD_ClickSyncInBtn_SyncinPage_withcommit_alrt("kiranc1","Amphenol@123","usercommited");

		//String ActualMsg=tu.get_popup_text();
		String ActualMsg=tu.get_AlertMsg_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access the Manual sync Module when the Manual sync  Privilege is unchecked");

		sa.assertAll();

	}


	//PA092-Verify if Guest User not able to access the Camera Access Module when the Camera Access Privilege is unchecked

	@Test(priority=14,groups = { "Sanity",
	"Regression" }, description = "PA092-Verify if Guest User not able to access the Camera Access Module when the Camera Access Privilege is unchecked")

	public void PA092() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA092-Verify if Guest User not able to access the Camera Access Module when the Camera Access Privilege is unchecked");
		SoftAssert sa = new SoftAssert();

		DefaultUserPrivilages_page.Click_CameraAccess();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.click_Img_Camera_Btn();
		String ActualMsg=tu.get_AlertMsg_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access the Camera Module when the Camera access  Privilege is unchecked");

		sa.assertAll();
	}



	//PA100-Verify if Guest User not able to access the Audit Trail Module when the Audit Trail Privilege is unchecked

	@Test(priority=15,groups = { "Sanity",
	"Regression" }, description = "PA100-Verify if Guest User not able to access the Audit Trail Module when the Audit Trail Privilege is unchecked")

	public void PA0100() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA100-Verify if Guest User not able to access the Audit Trail Module when the Audit Trail Privilege is unchecked");
		SoftAssert sa = new SoftAssert();

		DefaultUserPrivilages_page.Click_AuditCheckBox();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		MainHubPage.ClickAuditTitle_alrt();

		Thread.sleep(500);

		String ActualMsg=tu.get_AlertMsg_text();
		String ExpectedMsg="User does not have sufficient privileges to perform this operation";

		sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access the Run Verification Module when the Run Verification  Privilege is unchecked");

		sa.assertAll();
	}



	//PA102-Verify if Guest User not able to access the Policies Module when the Policies Privilege is unchecked

	@Test(priority=16,groups = { "Sanity",
	"Regression" }, description = "PA102-Verify if Guest User not able to access the Policies Module when the Policies Privilege is unchecked")

	public void PA0102() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA102-Verify if Guest User not able to access the Policies Module when the Policies Privilege is unchecked");
		SoftAssert sa = new SoftAssert();

        DefaultUserPrivilages_page.Click_Create_UserManagement();
		DefaultUserPrivilages_page.Click_Policies();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(3000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		AD_UMPage= MainHubPage.AD_ClickAdminTile_UMpage();

		Thread.sleep(500);


		sa.assertEquals(AD_UMPage.IsPoliciestabEnabled(), false,
				"FAIL:User  able to access  Policies  Module when the Policies Privilege is unchecked");


		sa.assertAll();
	}


	//PA104-Verify if Guest User not able to access the Preferences Module when the Preferences Privilege is unchecked

	@Test(priority=17,groups = { "Sanity",
	"Regression" }, description = "PA104-Verify if Guest User not able to access the Preferences Module when the Preferences Privilege is unchecked")

	public void PA0104() throws InterruptedException, IOException, ParseException {
		extentTest = extent
				.startTest("PA104-Verify if Guest User not able to access the Preferences Module when the Preferences Privilege is unchecked");
		SoftAssert sa = new SoftAssert();


		DefaultUserPrivilages_page.Click_Create_Preferences();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(3000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		AD_UMPage= MainHubPage.AD_ClickAdminTile_UMpage();
		// AD_UMPage.ClickPreferenceTab();

		Thread.sleep(500);

		sa.assertEquals(AD_UMPage.PreferencetabEnabled(), false,
				"FAIL:User  able to access  Policies  Module when the Policies Privilege is unchecked");


		sa.assertAll();
	}


	//PA106-Verify if Guest User not able to access the Hardware Maintenance Module when the Hardware Maintenance Privilege is unchecked

	@Test(priority=18,groups = { "Sanity",
	"Regression" }, description = "PA106-Verify if Guest User not able to access the Hardware Maintenance Module when the Hardware Maintenance Privilege is unchecked")

	public void PA0106() throws InterruptedException, IOException, ParseException, AWTException {
		extentTest = extent
				.startTest("PA106-Verify if Guest User not able to access the Hardware Maintenance Module when the Hardware Maintenance Privilege is unchecked");
		SoftAssert sa = new SoftAssert();

		DefaultUserPrivilages_page.Click_HardwarreMaintaince();
		DefaultUserPrivilages_page.NewSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
		MainHubPage=AD_UMPage.click_BackBtn();
		Thread.sleep(2000);
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc1","Amphenol@123");
		//MainHubPage.Click_Discover_alrt();
		AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();

		sa.assertEquals(AD_UMPage.HardwaretabEnabled(),false,
				"FAIL:User  able to access the Hardware Maintenance when the  Hardware Maintenance  Privilege is unchecked");

		sa.assertAll();

	}
	
	
	/*CRT Dependent
	//PA088-Verify if Guest User not able to access the Create Reports Module when the Create Reports Privilege is unchecked
	
	
		@Test(priority=18,groups = { "Sanity",
		"Regression" }, description = "PA088-Verify if Guest User not able to access the Create Reports Module when the Create Reports Privilege is unchecked")

		public void PA088() throws InterruptedException, IOException, ParseException, AWTException {
			extentTest = extent
					.startTest("PA088-Verify if Guest User not able to access the Create Reports Module when the Create Reports Privilege is unchecked");
			SoftAssert sa = new SoftAssert();

			System.out.println("CRT DEPENDENT from build 16");
			DefaultUserPrivilages_page.Click_Create_Reports();
			DefaultUserPrivilages_page.Click_ManualSync();
			DefaultUserPrivilages_page.NewSaveButton();
			UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
			AD_UMPage.Select_grp();
			AD_UMPage.select_2grp("QA Testers2");
			Thread.sleep(500);
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("AllUser");
			AD_UMPage.clickSavebtn();
			UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
			tu.click_OK_popup();
			MainHubPage=AD_UMPage.click_BackBtn();
			Thread.sleep(2000);
			MainHubPage.UserSignOut();
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
			assetDetailsPage.Click_GenerateReportsBtn();
			
			String ActualMsg=tu.get_AlertMsg_text();
				String ExpectedMsg="User does not have sufficient privileges to perform this operation";

				sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access the Manual sync Module when the Manual sync  Privilege is unchecked");

			sa.assertAll();

		}*/
	
	//PA090-Verify if Guest User  not able to access the Delete StudyFiles/Reports Module when the Delete StudyFiles/Reports Privilege is unchecked
		@Test(priority=18,groups = { "Sanity",
		"Regression" }, description = "PA090-Verify if Guest User  not able to access the Delete StudyFiles/Reports Module when the Delete StudyFiles/Reports Privilege is unchecked")

		public void PA090() throws InterruptedException, IOException, ParseException, AWTException {
			extentTest = extent
					.startTest("PA090-Verify if Guest User  not able to access the Delete StudyFiles/Reports Module when the Delete StudyFiles/Reports Privilege is unchecked");
			SoftAssert sa = new SoftAssert();

			DefaultUserPrivilages_page.Click_DeleteStudyFiles_Reports();
			DefaultUserPrivilages_page.Click_ManualSync();
			DefaultUserPrivilages_page.NewSaveButton();
			UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
			AD_UMPage.Select_grp();
			AD_UMPage.select_2grp("QA Testers2");
			Thread.sleep(500);
			AD_UMPage.select_UserTitle("Manager");
			AD_UMPage.select_UserType1("AllUser");
			AD_UMPage.clickSavebtn();
			UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "usercomitted");
			tu.click_OK_popup();
			MainHubPage=AD_UMPage.click_BackBtn();
			Thread.sleep(2000);
			MainHubPage.UserSignOut();
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
			assetDetailsPage.Qual_DeleteBtn();
			
			String ActualMsg=tu.get_AlertMsg_text();
				String ExpectedMsg="User does not have sufficient privileges to perform this operation";

				sa.assertEquals(ActualMsg, ExpectedMsg,
				"FAIL:User  able to access the Manual sync Module when the Manual sync  Privilege is unchecked");

			sa.assertAll();

		}
}
