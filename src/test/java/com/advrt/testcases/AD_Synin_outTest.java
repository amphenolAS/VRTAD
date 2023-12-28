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
import com.advrt.utility.setupCreationUtility;
import com.advrt.utility.userManagementUtility;


public class AD_Synin_outTest extends BaseClass{
	
	public AD_Synin_outTest() throws IOException {
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
	//FM_SyncInPage FM_SyncInPage;
	
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_AD_Synin_outTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("_AD_Synin_outTest Test in Progress..");
		

		
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
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
		
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
			System.out.println("AD_Synin_outTest test completed");
			Thread.sleep(500);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException, AWTException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
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
	
	
	
//Syncout01-Verify if user able to syncout the Active Directory Users, Policies and Customized user types
//Syncin01-Verify if user able to syncin the Active Directory Users and Customized user types
//Syncin02-Verify if user not able to syncin the Policies file from the File management
//Syncin03-Verify if user able to syncin the Policies file from the Policies tab
//Syncin04-Verify if user able to enable the Active Directory and Sync in the Data files where the selected folder having the Active Directory enabled files
//Syncin05-Verify if user able to enable the Active Directory and Sync in the Data files where the selected folder having the Active Directory disabled files
//Syncin06-Verify if user able to disable the Active Directory and Sync in the Data files where the selected folder having the Active Directory enabled files
//Syncin07-Verify if user able to disable the Active Directory and Sync in the Data files where the selected folder having the Active Directory disabled files

	
	//Syncin08-Verify if clicking on the browse button Select folder window should be opened
	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "Syncin08-Verify if clicking on the browse button Select folder window should be opened")

public void AD_Syncin08() throws InterruptedException, IOException, AWTException {
extentTest = extent
		.startTest("Syncin08-Verify if clicking on the browse button Select folder window should be opened");
SoftAssert sa = new SoftAssert();

MainHubPage=AD_UMPage.click_BackBtn();
MainHubPage.ClickFileManagementTitle();
FileManagementPage = MainHubPage.ClickFileManagementTitle();
SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kaverib","Amphenol@123","usercommitted");
SyncInPage.click_BrowseBtn();

//To verify file explorer window is invoked or not
sa.assertTrue(SyncInPage.is_ExplorerWinInvoked(), "Fail: File explorer window is invoked when clicking on Browse button");

sa.assertAll();
}
	
	
	
//Syncin09-Verify if user able to SyncIn the data once clicking on the SyncIn button after selecting the required path
	@Test(priority=2,groups = { "Sanity",
	"Regression" }, description = "Syncin09-Verify if user able to SyncIn the data once clicking on the SyncIn button after selecting the required path")

public void AD_Syncin09() throws InterruptedException, IOException, AWTException {
extentTest = extent
		.startTest("Syncin09-Verify if user able to SyncIn the data once clicking on the SyncIn button after selecting the required path");
SoftAssert sa = new SoftAssert();

MainHubPage=AD_UMPage.click_BackBtn();
MainHubPage.ClickFileManagementTitle();
FileManagementPage = MainHubPage.ClickFileManagementTitle();
SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kaverib","Amphenol@123","usercommitted");
SyncInPage.enter_Filepath("syncin");
Thread.sleep(500);
SyncInPage.click_FltrBtn();
SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
SyncInAssetListPage.click_EquipmentCheckBox();
SyncInAssetListPage.click_SelectAllBtn();
SyncInAssetListPage.click_OkBtn();
//SyncInAssetListPage.click_OkOnSyncInSelections();
SyncInAssetListPage.click_AlrtYesBtn();
Thread.sleep(5000);

String ActualMsg=tu.get_popup_text();
String ExpectedMsg="Sync In Successful. \n"
		+ "Application will be closed now.Please Relaunch the Application to get the refreshed data.";

sa.assertEquals(ActualMsg,ExpectedMsg,
		"Fail: There is NO Audit trail  entry  while login with the  Active Directory Admin  User");
sa.assertAll();
}

	
	
//Syncin10-Verify if Validation message should be displayed once clicking on the SyncIn button without selecting the path
	
	@Test(priority=1,groups = { "Sanity",
	"Regression" }, description = "Syncin10-Verify if Validation message should be displayed once clicking on the SyncIn button without selecting the path")

public void AD_Syncin10() throws InterruptedException, IOException, AWTException {
extentTest = extent
		.startTest("Syncin10-Verify if Validation message should be displayed once clicking on the SyncIn button without selecting the path");
SoftAssert sa = new SoftAssert();

MainHubPage=AD_UMPage.click_BackBtn();
MainHubPage.ClickFileManagementTitle();
FileManagementPage = MainHubPage.ClickFileManagementTitle();
SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kaverib","Amphenol@123","usercommitted");
SyncInPage.click_FltrBtn();
SyncInPage.click_SyncInOK_btn_alrt();

String ActualMsg=tu.get_AlertMsg_text();
String ExpectedMsg="Please select valid path";

sa.assertEquals(ActualMsg,ExpectedMsg,
		"Fail: There is NO Audit trail  entry  while login with the  Active Directory Admin  User");
sa.assertAll();
}

	
}
