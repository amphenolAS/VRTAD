/**
 * @author kaveri

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
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.AuditPage;
import com.advrt.pages.assetHubPage;
import com.advrt.pages.FM_SyncInPage;
import com.advrt.pages.ADUM_page;
import com.advrt.pages.assetDetailsPage;
import com.advrt.pages.EquipmentHubPage;
import com.advrt.pages.NewEquipmentCreation_Page;
import com.advrt.pages.Copyassetpage;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.pages.Equipment_IRTDHubPage;
import com.advrt.pages.Equipment_IRTDDetailspage;
import com.advrt.utility.ADUserManagementUtility;
import com.advrt.utility.TestUtilities;
import com.advrt.pages.FileManagementPage;
import com.advrt.pages.SyncInAssetListPage;
import com.advrt.pages.FM_ArchivePage;
import com.advrt.pages.FM_ArchiveSelectionPage;


public class AD_FM_Archive_Admin_AuditTest extends BaseClass{
	
	public AD_FM_Archive_Admin_AuditTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

//com.advrt.testcases.AD_FM_Archive_Admin_AuditTest#AD_Archive_001
	
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
	SyncInAssetListPage SyncInAssetListPage;
	EquipmentHubPage EquipmentHubPage;
	NewEquipmentCreation_Page NewEquipmentCreation_Page;
	Equipment_IRTDHubPage Equipment_IRTDHubPage;
	Equipment_IRTDDetailspage Equipment_IRTDDetailspage;
	FM_ArchivePage FM_ArchivePage;
	FM_ArchiveSelectionPage FM_ArchiveSelectionPage;
	FM_SyncInPage FM_SyncInPage;
	
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"AD_FM_Archive_Admin_AuditTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD_FM_Archive_Admin_AuditTest in Progress..");
		

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
			try {
				tu.click_OK_popup();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
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
			ADUM_page.select_grp("Automation");//Automation
			ADUM_page.enterNewUserTitle("Manager");
			ADUM_page.SelectUType("SystemAdministrator");
			Thread.sleep(1000);
			ADUM_page.ClickNewUserSaveButton();
		
			tu.UserLoginPopup_UserCommentTextBox("1", "111111", "created");
			tu.click_OK_popup();
			tu.click_OK_popup();
			Thread.sleep(2000);	
			
		}
	
	//After All the tests are conducted
	//@AfterTest
	@AfterClass
		public void endReport() throws InterruptedException {
			extent.flush();
			extent.close();
			System.out.println("AD_FM_Archive_Admin_AuditTest  test completed");
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
	
	//TCs 3,5,6 are NA
	
	
	//AD_Archive_001 Verify the Audit trail entry  while login with the  Active Directory Admin  User

	@Test(priority=0,description = "AD_Archive_001 Verify the Audit trail entry  while login with the  Active Directory Admin  User")

	public void AD_Archive_001() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("AD_Archive_001 Verify the Audit trail entry  while login with the  Active Directory Admin  User");
		
		SoftAssert sa = new SoftAssert();
		AuditPage = MainHubPage.ClickAuditTitle();
		
		sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\",User Name : \"Kaveri Bedar\" Logged in to System.");
		
		sa.assertAll();
		
	}

	
	//AD_Archive _002 Verify the Audit trail entry while peforming Archive  operation with the  Active Directory Admin  User
	
	@Test(priority=1,description = "AD_Archive _002 Verify the Audit trail entry while peforming Archive  operation with the  Active Directory Admin  User")

	public void AD_Archive_002() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("AD_Archive _002 Verify the Audit trail entry while peforming Archive  operation with the  Active Directory Admin  User");
		
		SoftAssert sa = new SoftAssert();
		
		//Need to perform sync in operation
				FileManagementPage = MainHubPage.ClickFileManagementTitle();
				FM_SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kaverib","Amphenol@123","commit");
				FM_SyncInPage.enter_Filepath("syncin");
				FM_SyncInPage.click_FltrBtn();
				//FM_SyncInPage.unSelect_FilterBtn();
				Thread.sleep(500);
				SyncInAssetListPage = FM_SyncInPage.click_SyncInOK_btn();
				SyncInAssetListPage.click_EquipmentCheckBox();
				SyncInAssetListPage.click_SelectAllBtn();
				Thread.sleep(500);
				SyncInAssetListPage.click_OkBtn();
				SyncInAssetListPage.click_AlrtYesBtn();
				Thread.sleep(9000);
				SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
				
				//Re Launch the application
				LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
				LoginPage = new LoginPage();
				MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
	
				// Conduct a Archive operation
		
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_ArchivePage = FileManagementPage.Click_ArchiveTabwithcommit("kaverib", "Amphenol@123","commit");
			FM_ArchivePage.enter_Filepath("Archive");
			FM_ArchivePage.click_ArchiveDate();
			FM_ArchivePage.click_Ok_DateSelectionPopUp();
			Thread.sleep(500);
			FM_ArchiveSelectionPage = FM_ArchivePage.click_OkBtn();
			FM_ArchiveSelectionPage.select_SetupsChkBox();
			FM_ArchiveSelectionPage.click_SelectAllBtn();
			Thread.sleep(500);
			FM_ArchiveSelectionPage.click_Ok_ArhiveSelectionPopUp();
			Thread.sleep(500);
			tu.click_YesBtn_popup();
try {
	tu.click_OK_popup();
} catch (Exception e) {
	e.printStackTrace();
}
			
			Thread.sleep(3000); 
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			LoginPage LoginPage=new LoginPage();
			MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			
			AuditPage = MainHubPage.ClickAuditTitle();
			AuditPage.Click_ActionFilter_Icon();
			AuditPage.EnterTxt_ActionFilter("User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Archive\" operation in \"FileManagementScreen\" screen");
			
			AuditPage.click_Action_FilterBtn();
			sa.assertEquals(AuditPage.get_auditEvent_text(),
					"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Archive\" operation in \"FileManagementScreen\" screen");
			
			sa.assertAll();
			
		}
	
	
		
		//AD_Archive _004 Verify the no Audit trail entry is recoreded related to Equipments when
		//peforming Archive  operation when Equipments check box is unckecked with the  Active Directory Admin  User
		
		
		@Test(priority=2,description = "AD_Archive _004 Verify the no Audit trail entry is recoreded related to Equipments when\r\n"
				+ "		//peforming Archive  operation when Equipments check box is unckecked with the  Active Directory Admin  User")

		public void AD_Archive_004() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Archive _004 Verify the no Audit trail entry is recoreded related to Equipments when\r\n"
							+ "		//peforming Archive  operation when Equipments check box is unckecked with the  Active Directory Admin  User");
			
			SoftAssert sa = new SoftAssert();
		
		// Conduct a Syncin operation
			
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kaverib","Amphenol@123","commit");
			FM_SyncInPage.enter_Filepath("syncin");
			FM_SyncInPage.click_FltrBtn();
			//FM_SyncInPage.unSelect_FilterBtn();
			Thread.sleep(500);
			SyncInAssetListPage = FM_SyncInPage.click_SyncInOK_btn();
			SyncInAssetListPage.click_EquipmentCheckBox();
			SyncInAssetListPage.click_SelectAllBtn();
			Thread.sleep(500);
			SyncInAssetListPage.click_OkBtn();
			SyncInAssetListPage.click_AlrtYesBtn();
			Thread.sleep(9000);
			SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
			
				LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
				LoginPage = new LoginPage();
				MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
				
				// Conduct a Archive operation
		
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_ArchivePage = FileManagementPage.Click_ArchiveTabwithcommit("kaverib", "Amphenol@123","commit");
			FM_ArchivePage.enter_Filepath("Archive");
			FM_ArchivePage.click_ArchiveDate();
			FM_ArchivePage.click_Ok_DateSelectionPopUp();
			Thread.sleep(500);
			FM_ArchiveSelectionPage = FM_ArchivePage.click_OkBtn();
			FM_ArchiveSelectionPage.select_SetupsChkBox();
			FM_ArchiveSelectionPage.click_SelectAllBtn();
			FM_ArchiveSelectionPage.unSelect_AllEqpBtn();
			Thread.sleep(500);
			FM_ArchiveSelectionPage.click_Ok_ArhiveSelectionPopUp();
			Thread.sleep(500);
			tu.click_YesBtn_popup();
try {
	tu.click_OK_popup();
} catch (Exception e) {
	e.printStackTrace();
}
			
			Thread.sleep(3000); 
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			LoginPage LoginPage=new LoginPage();
			MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			
			AuditPage = MainHubPage.ClickAuditTitle();
			//AuditPage.Click_ActionFilter_Icon();
			//AuditPage.EnterTxt_ActionFilter("User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Archive\" operation in \"FileManagementScreen\" screen");
			//AuditPage.EnterTxt_ActionFilter("\"Equipments\" Archive completed.");
			//AuditPage.click_Action_FilterBtn();
			//String Expected="\"Equipments\" Archive completed.\"";
			
			sa.assertFalse(AuditPage.get_auditEvent_text17());
			
			sa.assertAll();
				
			}
		
		
		//AD_Archive _007 Verify the  Audit trail entry is recoreded related to Equipments  when peforming Archive  operation when Equipments check box is ckecked with the  Active Directory Admin  User

		@Test(priority=3,description = "AD_Archive _007 Verify the  Audit trail entry is recoreded related to Equipments  when peforming Archive  operation when Equipments check box is ckecked with the  Active Directory Admin  User")

		public void AD_Archive_007() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Archive _007 Verify the  Audit trail entry is recoreded related to Equipments  when peforming Archive  operation when Equipments check box is ckecked with the  Active Directory Admin  User");
			
			SoftAssert sa = new SoftAssert();
		
		// Conduct a Syncin operation
			
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kaverib","Amphenol@123","commit");
			FM_SyncInPage.enter_Filepath("syncin");
			FM_SyncInPage.click_FltrBtn();
			//FM_SyncInPage.unSelect_FilterBtn();
			Thread.sleep(500);
			SyncInAssetListPage = FM_SyncInPage.click_SyncInOK_btn();
			SyncInAssetListPage.click_EquipmentCheckBox();
			SyncInAssetListPage.click_SelectAllBtn();
			Thread.sleep(500);
			SyncInAssetListPage.click_OkBtn();
			SyncInAssetListPage.click_AlrtYesBtn();
			Thread.sleep(9000);
			SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
			
				LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
				LoginPage = new LoginPage();
				MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
				
				// Conduct a Archive operation
		
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_ArchivePage = FileManagementPage.Click_ArchiveTabwithcommit("kaverib", "Amphenol@123","commit");
			FM_ArchivePage.enter_Filepath("Archive");
			FM_ArchivePage.click_ArchiveDate();
			FM_ArchivePage.click_Ok_DateSelectionPopUp();
			Thread.sleep(500);
			FM_ArchiveSelectionPage = FM_ArchivePage.click_OkBtn();
			FM_ArchiveSelectionPage.select_SetupsChkBox();
			FM_ArchiveSelectionPage.click_SelectAllBtn();
			Thread.sleep(500);
			FM_ArchiveSelectionPage.click_Ok_ArhiveSelectionPopUp();
			Thread.sleep(500);
			tu.click_YesBtn_popup();
try {
	tu.click_OK_popup();
} catch (Exception e) {
	e.printStackTrace();
}
			
			Thread.sleep(3000); 
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			LoginPage LoginPage=new LoginPage();
			MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			
			AuditPage = MainHubPage.ClickAuditTitle();
			AuditPage.Click_ActionFilter_Icon();
			//AuditPage.EnterTxt_ActionFilter("User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Archive\" operation in \"FileManagementScreen\" screen");
			AuditPage.EnterTxt_ActionFilter("\"Equipments\" Archive completed.");
			AuditPage.click_Action_FilterBtn();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(),"\"Equipments\" Archive completed.","FAIL:No entry for equipment archive");
	
			sa.assertAll();
				
			}
		
		//AD_Archive _009 Verify the Audit trail entry while peforming Archive  operation by selecting only 1 asset  with the  Active Directory Admin  User
	
		@Test(priority=4,description = "AD_Archive _009 Verify the Audit trail entry while peforming Archive operation by selecting only 1 asset  with the  Active Directory Admin  User"
				+ "	")

		public void AD_Archive_009() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Archive _009 Verify the Audit trail entry while peforming Archive  operation by selecting only 1 asset  with the  Active Directory Admin  User");
			
			SoftAssert sa = new SoftAssert();
		
		// Conduct a Syncin operation	
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kaverib","Amphenol@123","commit");
			FM_SyncInPage.enter_Filepath("syncin");
			FM_SyncInPage.click_FltrBtn();
			//FM_SyncInPage.unSelect_FilterBtn();
			Thread.sleep(500);
			SyncInAssetListPage = FM_SyncInPage.click_SyncInOK_btn();
			SyncInAssetListPage.click_EquipmentCheckBox();
			SyncInAssetListPage.click_SelectAllBtn();
			Thread.sleep(500);
			SyncInAssetListPage.click_OkBtn();
			SyncInAssetListPage.click_AlrtYesBtn();
			Thread.sleep(9000);
			SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
			
			
				LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
				LoginPage = new LoginPage();
				MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
				
				// Conduct a Archive operation
		
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_ArchivePage = FileManagementPage.Click_ArchiveTabwithcommit("kaverib", "Amphenol@123","commit");
			FM_ArchivePage.enter_Filepath("Archive");
			FM_ArchivePage.click_ArchiveDate();
			FM_ArchivePage.click_Ok_DateSelectionPopUp();
			Thread.sleep(500);
			FM_ArchiveSelectionPage = FM_ArchivePage.click_OkBtn();
			FM_ArchiveSelectionPage.select_SetupsChkBox();
			FM_ArchiveSelectionPage.click_SelectAllBtn();
			Thread.sleep(500);
			FM_ArchiveSelectionPage.click_Ok_ArhiveSelectionPopUp();
			Thread.sleep(500);
			tu.click_YesBtn_popup();
try {
	tu.click_OK_popup();
} catch (Exception e) {
	e.printStackTrace();
}
			
			Thread.sleep(3000); 
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			LoginPage LoginPage=new LoginPage();
			MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			
			AuditPage = MainHubPage.ClickAuditTitle();
			AuditPage.Click_ActionFilter_Icon();
			//AuditPage.EnterTxt_ActionFilter("User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Archive\" operation in \"FileManagementScreen\" screen");
			AuditPage.EnterTxt_ActionFilter("\"SyncInAsset  (1584017028)\" Archive completed.");

			AuditPage.click_Action_FilterBtn();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(),"\"SyncInAsset  (1584017028)\" Archive completed.","FAIL:No entry for Asset archive");
	
			sa.assertAll();
				
			}
		
		
		//AD_Archive _010 Verify the Audit trail entry while 
		//peforming Archive  operation by selecting 200 asset  with the  Active Directory Admin  User
		
		
		@Test(priority=5,description = "AD_Archive _010 AD_Archive _010 Verify the Audit trail entry while \r\n"
				+ "		//peforming Archive  operation by selecting 200 asset  with the  Active Directory Admin  User")

		public void AD_Archive_010() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Archive _010 AD_Archive _010 Verify the Audit trail entry while peforming Archive  operation by selecting 200 asset  with the  Active Directory Admin  User");
			
			SoftAssert sa = new SoftAssert();
		
			// Conduct a Syncin operation	
						
			
			            FileManagementPage = MainHubPage.ClickFileManagementTitle();
						FM_SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kaverib","Amphenol@123","commit");
						FM_SyncInPage.enter_Filepath("200_Assets");
						FM_SyncInPage.click_FltrBtn();
						//FM_SyncInPage.unSelect_FilterBtn();
						Thread.sleep(500);
						SyncInAssetListPage = FM_SyncInPage.click_SyncInOK_btn();
						SyncInAssetListPage.click_EquipmentCheckBox();
						SyncInAssetListPage.click_SelectAllBtn();
						Thread.sleep(500);
						SyncInAssetListPage.click_OkBtn();
						SyncInAssetListPage.click_AlrtYesBtn();
						TimeUnit.MINUTES.sleep(1);
						SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
						
						
							LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
							LoginPage = new LoginPage();
							MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
							
							// Conduct a Archive operation
					
						FileManagementPage = MainHubPage.ClickFileManagementTitle();
						FM_ArchivePage = FileManagementPage.Click_ArchiveTabwithcommit("kaverib", "Amphenol@123","commit");
						FM_ArchivePage.enter_Filepath("Archive");
						FM_ArchivePage.click_ArchiveDate();
						FM_ArchivePage.click_Ok_DateSelectionPopUp();
						Thread.sleep(500);
						FM_ArchiveSelectionPage = FM_ArchivePage.click_OkBtn();
						FM_ArchiveSelectionPage.select_SetupsChkBox();
						FM_ArchiveSelectionPage.click_SelectAllBtn();
						Thread.sleep(500);
						FM_ArchiveSelectionPage.click_Ok_ArhiveSelectionPopUp();
						Thread.sleep(500);
						tu.click_YesBtn_popup();
			try {
				tu.click_OK_popup();
			} catch (Exception e) {
				e.printStackTrace();
			}
						
						Thread.sleep(3000); 
						LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
						LoginPage LoginPage=new LoginPage();
						MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			
			AuditPage = MainHubPage.ClickAuditTitle();
			AuditPage.Click_ActionFilter_Icon();
			//AuditPage.EnterTxt_ActionFilter("User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Archive\" operation in \"FileManagementScreen\" screen");
			AuditPage.EnterTxt_ActionFilter("\"Asset200  (1681830079)\" Archive completed.");
			AuditPage.click_Action_FilterBtn();
			sa.assertEquals(AuditPage.get_auditEvent_text(),"\"Asset200  (1681830079)\" Archive completed.","FAIL:No entry for Asset archive");
			
			AuditPage.Click_ActionFilter_Icon();
			AuditPage.EnterTxt_ActionFilter("\"Asset100  (1681825139)\" Archive completed.");
			AuditPage.click_Action_FilterBtn();
			sa.assertEquals(AuditPage.get_auditEvent_text(),"\"Asset100  (1681825139)\" Archive completed.","FAIL:No entry for Asset archive");
			
		
			AuditPage.Click_ActionFilter_Icon();
			AuditPage.EnterTxt_ActionFilter("\"Asset150  (1681827503)\" Archive completed.");
			AuditPage.click_Action_FilterBtn();
			sa.assertEquals(AuditPage.get_auditEvent_text(),"\"Asset150  (1681827503)\" Archive completed.","FAIL:No entry for Asset archive");
			
			
			AuditPage.Click_ActionFilter_Icon();
			AuditPage.EnterTxt_ActionFilter("\"Asset01  (1681820542)\" Archive completed.");
			AuditPage.click_Action_FilterBtn();
			sa.assertEquals(AuditPage.get_auditEvent_text(),"\"Asset01  (1681820542)\" Archive completed.","FAIL:No entry for Asset archive");
			
			
			
			sa.assertAll();
				
			}
		
		//AD_Archive _011 Verify the Audit trail entry while peforming Archive  operation by Select All button  with the  Active Directory Admin  User
		
		@Test(priority=6,description = "AD_Archive _011 Verify the Audit trail entry while peforming Archive  operation by Select All button  with the  Active Directory Admin  User")

		public void AD_Archive_011() throws InterruptedException, AWTException, IOException {
			extentTest = extent
					.startTest("AD_Archive _011 Verify the Audit trail entry while peforming Archive  operation by Select All button  with the  Active Directory Admin  User");
			
			SoftAssert sa = new SoftAssert();
		
		// Conduct a Syncin operation
			
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPagewithcommit("kaverib","Amphenol@123","commit");
			FM_SyncInPage.enter_Filepath("syncin");
			FM_SyncInPage.click_FltrBtn();
			//FM_SyncInPage.unSelect_FilterBtn();
			Thread.sleep(500);
			SyncInAssetListPage = FM_SyncInPage.click_SyncInOK_btn();
			SyncInAssetListPage.click_EquipmentCheckBox();
			SyncInAssetListPage.click_SelectAllBtn();
			Thread.sleep(500);
			SyncInAssetListPage.click_OkBtn();
			SyncInAssetListPage.click_AlrtYesBtn();
			Thread.sleep(9000);
			SyncInAssetListPage.click_Success_alrtMeg_OkBtn();
			
				LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
				LoginPage = new LoginPage();
				MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
				
				// Conduct a Archive operation
		
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_ArchivePage = FileManagementPage.Click_ArchiveTabwithcommit("kaverib", "Amphenol@123","commit");
			FM_ArchivePage.enter_Filepath("Archive");
			FM_ArchivePage.click_ArchiveDate();
			FM_ArchivePage.click_Ok_DateSelectionPopUp();
			Thread.sleep(500);
			FM_ArchiveSelectionPage = FM_ArchivePage.click_OkBtn();
			FM_ArchiveSelectionPage.click_SelectAllBtn();
			Thread.sleep(500);
			FM_ArchiveSelectionPage.click_Ok_ArhiveSelectionPopUp();
			Thread.sleep(500);
			tu.click_YesBtn_popup();
try {
	tu.click_OK_popup();
} catch (Exception e) {
	e.printStackTrace();
}
			
			Thread.sleep(3000); 
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			LoginPage LoginPage=new LoginPage();
			MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
			
			AuditPage = MainHubPage.ClickAuditTitle();
			AuditPage.Click_ActionFilter_Icon();
			AuditPage.EnterTxt_ActionFilter("User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Archive\" operation in \"FileManagementScreen\" screen");
		
			AuditPage.click_Action_FilterBtn();
			
			sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Kaverib\" ,  User Name: \"Kaveri Bedar\" logged in to do  \"Archive\" operation in \"FileManagementScreen\" screen","FAIL:No entry for equipment archive");
	
			sa.assertAll();
				
			}
		
}