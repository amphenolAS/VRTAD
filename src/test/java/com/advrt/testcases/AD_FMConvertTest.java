/**
 * @author kaveriB
 *
 */

package com.advrt.testcases;


import java.awt.AWTException;
import java.io.File;
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
import com.advrt.pages.FileManagementPage;//
import com.advrt.pages.Equipment_IRTDHubPage;
import com.advrt.pages.Equipment_IRTDDetailspage;
import com.advrt.utility.TestUtilities;
import com.advrt.pages.FM_VRTConvertPage;


public class AD_FMConvertTest extends BaseClass{

	public AD_FMConvertTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	//com.advrt.testcases.AD_FMConvertTest#AD_Archive_001
	
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
	FM_VRTConvertPage FM_VRTConvertPage;


	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"AD_FMArchiveTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD_FMArchiveTest Test in Progress..");


		
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
		AD_UMPage.select_grp("Automation");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("SystemAdministrator");
		AD_UMPage.clickSavebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();
		tu.click_OK_popup();
		
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
	
		
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
		//MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
		
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
		System.out.println("AD_FMArchiveTest test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
		//assetHubPage=MainHubPage.Click_AssetTile2();

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

	 *********/



	// AD_Archive_001 Verify the Audit trail entry  while login with the  Active Directory Admin  User

		@Test(groups = { "Sanity",
				"Regression" }, description = "  AD_Archive_001 Verify the Audit trail entry  while login with the  Active Directory Admin  User")

		public void AD_Archive_001() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest(" AD_Archive_001 Verify the Audit trail entry  while login with the  Active Directory Admin  User");
			
			SoftAssert sa = new SoftAssert();
			AuditPage = MainHubPage.ClickAuditTitle();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(), "User ID : \"Kaverib\",User Name : \"Kaveri Bedar\" Logged in to System.");
			sa.assertAll();
			
		}
		
		//AD_Archive _002 Verify the Audit trail entry while peforming VRT Convert by selecting new study file  operation with the  Active Directory Admin  User
	
		@Test(groups = { "Sanity",
		"Regression" }, description = "AD_Archive _002 Verify the Audit trail entry while peforming VRT Convert by selecting new study file  operation with the  Active Directory Admin  User")

public void AD_Archive_002() throws InterruptedException, AWTException, IOException {
	extentTest = extent
			.startTest("AD_Archive _002 Verify the Audit trail entry while peforming VRT Convert by selecting new study file  operation with the  Active Directory Admin  User");
	
	SoftAssert sa = new SoftAssert();
	
	FileManagementPage = MainHubPage.ClickFileManagementTitle();
	FM_VRTConvertPage = FileManagementPage.Click_AvsConvert_Btnwithcomment("kaverib", "Amphenol@123","commit");
	//FM_VRTConvertPage.Click_BrowseBtn();
	
	FM_VRTConvertPage.enter_Filepath("VRT_Convert\\20240112142409_20240116014406_3_638410790492303341_1705069349_O.rtq");
	
	FM_VRTConvertPage.click_OkOnVRTConverter();
	Thread.sleep(5000);
	tu.click_OK_popup();
	
	/*
	String ActualMsg=tu.get_popup_text();
	String ExpectedMsg="VRT Convert Successful. \n"
			+ "Application will be closed now. Please Relaunch the Application to get the refreshed data.";
	

	sa.assertEquals(ActualMsg, ExpectedMsg,
			"FAIL:User  not able to access VRT convert when the VRT convert is checked");*/
	
	LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
	Thread.sleep(500);
	LoginPage = new LoginPage();
	MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
	
	AuditPage = MainHubPage.ClickAuditTitle();
	//AuditPage.Click_ActionFilter_Icon();
	//String str="Convert Raw File \"C:\Users\Kaveri.Bedar\git\VRTAD\src\test\resources\TestData\VRT_Convert\20240112142409_20240116014406_3_638410790492303341_1705069349_O.rtq\" - Successful. Conversion performed by User ID: \"kaverib\" User Name:\"Kaveri Bedar"\";
	//AuditPage.EnterTxt_ActionFilter("Convert Raw File \"C:\\Users\\Kaveri.Bedar\\git\\VRTAD\\src\\test\\resources\\TestData\\VRT_Convert\\20240112142409_20240116014406_3_638410790492303341_1705069349_O.rtq\" - Successful.Conversion performed by User ID: \"kaverib\"  User Name: \"Kaveri Bedar\"");
	//AuditPage.click_Action_FilterBtn();
	sa.assertEquals(AuditPage.get_auditEvent_text18(),
			"Convert Raw File  \"C:\\Users\\Kaveri.Bedar\\git\\VRTAD\\src\\test\\resources\\TestData\\VRT_Convert\\20240112142409_20240116014406_3_638410790492303341_1705069349_O.rtq\" -  Successful. Conversion performed by User ID:  \"Kaverib\"   User Name:  \"Kaveri Bedar\"   ");
	
	sa.assertAll();
}
		
		
		
	}