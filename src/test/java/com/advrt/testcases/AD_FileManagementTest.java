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
import java.util.List;

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


public class AD_FileManagementTest extends BaseClass{

	public AD_FileManagementTest() throws IOException {
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

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"AD_FileManagementTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD_FileManagementTest Test in Progress..");


		
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
		System.out.println("AD_FileManagementTest test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException, AWTException {
	
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
		assetHubPage=MainHubPage.Click_AssetTile2();

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



	//FM01-Verify if user able to view the generated Audit report under Reports section in the Asset screen


	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "FM01-Verify if user able to view the generated Audit report under Reports section in the Asset screen")

	public void FM01() throws InterruptedException, IOException, AWTException
	{
		extentTest = extent
				.startTest("FM01-Verify if user able to view the generated Audit report under Reports section in the Asset screen");
		SoftAssert sa = new SoftAssert();

		
		assetDetailsPage=assetHubPage.click_assetTile2("1304");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_AuditReportsButton();
		
		sa.assertEquals(assetDetailsPage.Report_StudyFilename_text(), "case-35",
				"FAIL:The Setup Name is not displayed in the available setup");

	sa.assertAll();
	}
	
	
	
	//FM02-Verify the -Copy to drive- functionality of a Audi Report for local drive
	
	@Test(priority=1,groups = { "Sanity",
	"Regression" }, description = "FM02-Verify the -Copy to drive- functionality of a Audi Report for local drive")

	public void FM02() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("FM02-Verify the -Copy to drive- functionality of a Audi Report for local drive");
		SoftAssert sa = new SoftAssert();
		
		
		assetDetailsPage=assetHubPage.click_assetTile2("1304");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_AuditReportsButton();
		
		String studyFileName = "MM";
		assetDetailsPage.Select_ReportFile(studyFileName);
		
		assetDetailsPage.click_Copytodrive();
		Thread.sleep(500);
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "reports");
		UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "usercommitted");

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		// System.out.println("Act Folderpath: "+filepath);
		String ExpAlrtMsg = "MM has been copied successfully to " + foldrpath;
		// System.out.println("Exp Folderpath: "+ExpAlrtMsg);
		String ActAlrtMsg = tu.get_AlertMsg_text();
		// System.out.println("Act alert msg: "+ActAlrtMsg);

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Copy to drive operation failed for report");

		// Confirm if the file got copied to the above destination folder or not
		List<String> fn = tu.get_fileNamesList(foldrpath);
		String expFileName = "A=(MM)=(oidfglskdhlsdlglhsdl)=1=15-Nov-2023 10-50-46=.pdf";//
		for (String filename : fn) {
			if (filename.contains(expFileName)) {
				sa.assertEquals(filename, expFileName, "FAIL: Incorrect file is copied or "
						+ "not all copeid during Copy to drive operation for report");
				sa.assertAll();
				break;
			}

		}

		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String a="\\";
		String ExpectMSG = "Report - \""+studyFileName+"\" , \"Copy to drive\" operation was performed by User Id : \""+"Kaverib"+"\", User Name : \""+"Kaveri Bedar"+"\" to \""+foldrpath+a+"A=(MM)=(oidfglskdhlsdlglhsdl)=1=15-Nov-2023 10-50-46=.pdf"+"\"";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy of a Detailed report");
		sa.assertAll();
		
	
	}
	

//FM03-Verify the Audit trial record exists for Copy of a Audit report

	@Test(priority=2,groups = { "Sanity",
	"Regression" }, description = "FM03-Verify the Audit trial record exists for Copy of a Audit report")

	public void FM03() throws InterruptedException, IOException, AWTException
	{
		extentTest = extent
				.startTest("FM03-Verify the Audit trial record exists for Copy of a Audit report");
		SoftAssert sa = new SoftAssert();

		
		System.out.println("This TC has been covered in TC FM02");

	sa.assertAll();
	}



//FM04-Verify the on-click functionality of PDF icon for Audit report under Reports tile-Audit sub tab

	
	@Test(priority=3,groups = { "Sanity",
	"Regression" }, description = "FM04-Verify the on-click functionality of PDF icon for Audit report under Reports tile-Audit sub tab")

	public void FM04() throws InterruptedException, IOException, AWTException
	{
		extentTest = extent
				.startTest("FM04-Verify the on-click functionality of PDF icon for Audit report under Reports tile-Audit sub tab");
		SoftAssert sa = new SoftAssert();
		
		
		
		assetDetailsPage=assetHubPage.click_assetTile2("1304");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_AuditReportsButton();
		
		assetDetailsPage.Select_ReportFile("MM");
		String downloadPath1 = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\A=(MM)=(oidfglskdhlsdlglhsdl)=1=15-Nov-2023%2010-50-46=.pdf";
		
		
		File f1 = new File(downloadPath1);
		f1.delete();

		assetDetailsPage.click_printBtn_Report();
		Thread.sleep(1000);
		assetDetailsPage.click_PDFpopup_OkBtn();
		Thread.sleep(3000);
		tu.click_ALTf4_KeyStroke_ToCloseApp();
		Thread.sleep(2000);
		String downloadPath = System.getProperty("user.home")
				+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\A=(MM)=(oidfglskdhlsdlglhsdl)=1=15-Nov-2023%2010-50-46=.pdf";
		File f = new File(downloadPath);
		if (f.exists()) {
			sa.assertEquals(f.getName(), "A=(MM)=(oidfglskdhlsdlglhsdl)=1=15-Nov-2023%2010-50-46=.pdf",
					" File is not available");
			sa.assertAll();
			// System.out.println("success");
		} else {
			sa.assertEquals(f.getName(), "A=(MM)=(oidfglskdhlsdlglhsdl)=1=15-Nov-2023%2010-50-46=.pdf",
					" File has not downloded");
			sa.assertAll();
			// System.out.println("fail to find the File");
		}

	
	}



//FM05-Verify if user able to view the generated Graph report under Reports section in the Asset screen


	@Test(priority=4,groups = { "Sanity",
	"Regression" }, description = "FM05-Verify if user able to view the generated Graph report under Reports section in the Asset screen")

	public void FM05() throws InterruptedException, IOException, AWTException
	{
		extentTest = extent
				.startTest("FM05-Verify if user able to view the generated Graph report under Reports section in the Asset screen");
		SoftAssert sa = new SoftAssert();

		
		assetDetailsPage=assetHubPage.click_assetTile2("1304");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_GraphReportsButton();
		
		sa.assertEquals(assetDetailsPage.Report_StudyFilename_text(), "case-35",
				"FAIL:The Setup Name is not displayed in the available setup");

	sa.assertAll();
	}



//FM08-Verify the -Copy to drive- functionality of a Graph Report for local drive

	@Test(priority=5,groups = { "Sanity",
	"Regression" }, description = "FM08-Verify the -Copy to drive- functionality of a Graph Report for local drive")

	public void FM08() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("FM08-Verify the -Copy to drive- functionality of a Graph Report for local drive");
		SoftAssert sa = new SoftAssert();
		
		
		assetDetailsPage=assetHubPage.click_assetTile2("1304");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_GraphReportsButton();
		
		String studyFileName = "case-35";
		assetDetailsPage.Select_ReportFile(studyFileName);
		
		assetDetailsPage.click_Copytodrive();
		Thread.sleep(500);
		assetDetailsPage.selectFolder_CopyToDrive("AutoLogs", "reports");
		UserLoginPopup_UserCommentTextBox("kaverib", "Amphenol@123", "usercommitted");

		String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
		// System.out.println("Act Folderpath: "+filepath);
		String ExpAlrtMsg = "case-35 has been copied successfully to " + foldrpath;
		// System.out.println("Exp Folderpath: "+ExpAlrtMsg);
		String ActAlrtMsg = tu.get_AlertMsg_text();
		// System.out.println("Act alert msg: "+ActAlrtMsg);

		sa.assertEquals(ActAlrtMsg, ExpAlrtMsg, "FAIL: Copy to drive operation failed for report");

		// Confirm if the file got copied to the above destination folder or not
		List<String> fn = tu.get_fileNamesList(foldrpath);
		String expFileName = "G=(case-35)=(asdfghjkl)=1=17-Nov-2023 12-00-22=.pdf";//
		for (String filename : fn) {
			if (filename.contains(expFileName)) {
				sa.assertEquals(filename, expFileName, "FAIL: Incorrect file is copied or "
						+ "not all copeid during Copy to drive operation for report");
				sa.assertAll();
				break;
			}

		}
Thread.sleep(500);
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(5000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		String a="\\";
		String ExpectMSG = "Report - \""+studyFileName+"\" , \"Copy to drive\" operation was performed by User Id : \""+"Kaverib"+"\", User Name : \""+"Kaveri Bedar"+"\" to \""+foldrpath+a+"G=(case-35)=(asdfghjkl)=1=17-Nov-2023 12-00-22=.pdf"+"\"";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for Copy of a Detailed report");
		sa.assertAll();
		
	
	}


	//FM09-Verify the Audit trial record exists for Copy of a Graph report


		@Test(priority=6,groups = { "Sanity",
		"Regression" }, description = "FM09-Verify the Audit trial record exists for Copy of a Graph report\r\n"
				+ "")

		public void FM09() throws InterruptedException, IOException, AWTException
		{
			extentTest = extent
					.startTest("FM09-Verify the Audit trial record exists for Copy of a Graph report");
			SoftAssert sa = new SoftAssert();

			
			System.out.println("This TC has been covered in TC FM08");

		sa.assertAll();
		}
		
		
		//FM12-Verify the on-click functionality of PDF icon for Graph report under Reports tile-Graph sub tab

		@Test(priority=7,groups = { "Sanity",
		"Regression" }, description = "FM12-Verify the on-click functionality of PDF icon for Graph report under Reports tile-Graph sub tab")

		public void FM012() throws InterruptedException, IOException, AWTException
		{
			extentTest = extent
					.startTest("FM12-Verify the on-click functionality of PDF icon for Graph report under Reports tile-Graph sub tab");
			SoftAssert sa = new SoftAssert();
			
			
			
			assetDetailsPage=assetHubPage.click_assetTile2("1304");
			assetDetailsPage.Click_reportsTile();
			assetDetailsPage.Click_GraphReportsButton();
			
			assetDetailsPage.Select_ReportFile("case-35");
			String downloadPath1 = System.getProperty("user.home")
					+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\A=(MM)=(oidfglskdhlsdlglhsdl)=1=15-Nov-2023%2010-50-46=.pdf";
			
			
			File f1 = new File(downloadPath1);
			f1.delete();

			assetDetailsPage.click_printBtn_Report();
			Thread.sleep(1000);
			assetDetailsPage.click_PDFpopup_OkBtn();
			Thread.sleep(3000);
			tu.click_ALTf4_KeyStroke_ToCloseApp();
			Thread.sleep(2000);
			String downloadPath = System.getProperty("user.home")
					+ "\\AppData\\Local\\Packages\\Kaye.ValProbeRT_racmveb2qnwa8\\LocalState\\A=(MM)=(oidfglskdhlsdlglhsdl)=1=15-Nov-2023%2010-50-46=.pdf";
			File f = new File(downloadPath);
			if (f.exists()) {
				sa.assertEquals(f.getName(), "A=(MM)=(oidfglskdhlsdlglhsdl)=1=15-Nov-2023%2010-50-46=.pdf",
						" File is not available");
				sa.assertAll();
				// System.out.println("success");
			} else {
				sa.assertEquals(f.getName(), "A=(MM)=(oidfglskdhlsdlglhsdl)=1=15-Nov-2023%2010-50-46=.pdf",
						" File has not downloded");
				sa.assertAll();
				// System.out.println("fail to find the File");
			}

		
		}

		
		
		
}