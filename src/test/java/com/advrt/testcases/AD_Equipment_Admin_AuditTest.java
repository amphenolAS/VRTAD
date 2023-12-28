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
import com.advrt.pages.MainHubPage;
import com.advrt.pages.UserManagementPage_Manual;
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.AuditPage;
import com.advrt.pages.assetHubPage;

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


public class AD_Equipment_Admin_AuditTest extends BaseClass{
	
	public AD_Equipment_Admin_AuditTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


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

	EquipmentHubPage EquipmentHubPage;
	NewEquipmentCreation_Page NewEquipmentCreation_Page;
	Equipment_IRTDHubPage Equipment_IRTDHubPage;
	Equipment_IRTDDetailspage Equipment_IRTDDetailspage;
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_AD_Equipment_Admin_AuditTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD_Equipment_Admin_AuditTest Test in Progress..");
		

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
			ADUM_page.select_grp("Automation");//Automation
			//AD_UMPage.Select_user();
			//ADUM_page.select_user(0);
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
			System.out.println("AD_Equipment_Admin_AuditTest  test completed");
			Thread.sleep(500);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("ruchika1","Amphenol@123");
		//ADUM_page = MainHubPage.ClickAdminTile_UMpage();
		//PoliciesPage = MainHubPage.ClickAdminTile_Polpage();
		//ADUM_page =	PoliciesPage.ClickUM_Tab_AD();
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
	
	
	//AD_Equipment_Audit_001 Verify the Audit trail entry  while login with the  Active Directory Admin  User

	@Test(description = "AD_Equipment_Audit _001 Verify the Audit trail entry  while login with the  Active Directory Admin  User")

	public void AD_Equipment_Audit_001() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("AD_Equipment_Audit _001 Verify the Audit trail entry  while login with the  Active Directory Admin  User");
		
		SoftAssert sa = new SoftAssert();
		AuditPage = MainHubPage.ClickAuditTitle();
		
		sa.assertEquals(AuditPage.get_auditEvent_text(),"User ID : \"Ruchika1\",User Name : \"Ruchika1\" Logged in to System.");
		sa.assertAll();
		
	}

	// AD_Equipment_Audit _002 Verify the Audit trail entry while Creating a new Equipment with the  Active Directory Admin   User

	@Test(description = "AD_Equipment_Audit _002 Verify the Audit trail entry while Creating a new Equipment with the  Active Directory Admin   User")

	public void AD_Asset_Audit_002() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"AD_Equipment_Audit _002 Verify the Audit trail entry while Creating a new Equipment with the  Active Directory Admin   User");

		SoftAssert sa = new SoftAssert();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();

		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EN002", "2");
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Equipcreation");
		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();
		MainHubPage = EquipmentHubPage.ClickBackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String ExpectMSG = "Equipment : \"EN002\" is created by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\"";
		sa.assertEquals(AuditPage.get_auditEvent_text(), ExpectMSG, "FAIL:The Audit trail record for Equipment creation is not audited ");
		sa.assertAll();
		

	}
	
	//AD_Equipment_Audit _003 Verify the Audit trail entry while Deleting Equipment with the  Active Directory Admin   User

	@Test(groups = { "Sanity",
			"Regression" }, description = "AD_Asset_ Audit 003-Verify the Audit trail entry after modifying asset name with the  Active Directory Admin User")

	public void AD_Asset_Audit_003() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"AD_Asset_ Audit 003-Verify the Audit trail entry after modifying asset name with the  Active Directory Admin User");

		SoftAssert sa = new SoftAssert();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();

		NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "EN003", "3");
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Equipcreation");
		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();
		Equipment_IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("EN003");
		Equipment_IRTDDetailspage.clickDeleteEquipmentIcon();
		Equipment_IRTDDetailspage.ClickYesBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Equipcreation");
		Equipment_IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();
		EquipmentHubPage = Equipment_IRTDHubPage.click_Back_btn();
		MainHubPage = EquipmentHubPage.ClickBackBtn();
		
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String ExpectMSG = "Equipment : \"EN003\" is deleted by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\"";
		
		sa.assertEquals(AuditPage.get_auditEvent_text(), ExpectMSG, "FAIL:The Audit trail record for Equipment delete is not audited ");
		sa.assertAll();
		
	}
	
   //AD_Equipment_Audit _004 Verify the Audit trail entry while Modifying Equipment ID with the  Active Directory Admin User
	
	@Test( description = "AD_Equipment_Audit _004 Verify the Audit trail entry while Modifying Equipment ID with the  Active Directory Admin User")

	public void AD_Asset_Audit_004() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"AD_Equipment_Audit _004 Verify the Audit trail entry while Modifying Equipment ID with the  Active Directory Admin User");

		SoftAssert sa = new SoftAssert();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();

		NewEquipmentCreation_Page.EqipCreation("IRTD", "EN004", "SL4", "4");
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Equipcreation");
		EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();
		Equipment_IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("EN004");
		Equipment_IRTDDetailspage.enter_IDname("A4");
		Equipment_IRTDDetailspage.ClickSaveButton();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Equipcreation");
		Equipment_IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();
		
		EquipmentHubPage = Equipment_IRTDHubPage.click_Back_btn();
		MainHubPage = EquipmentHubPage.ClickBackBtn();
		
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String ExpectMSG = "“Equipment Name” field of  “EN004” updated from “4” to “A4” modified by User ID : \"Ruchika1\" , User Name: \"Ruchika1\"";
		
		sa.assertEquals(AuditPage.get_auditEvent_text(), ExpectMSG, "FAIL:The Audit trail record for Modifying Equipment ID  is not audited ");
		
		MainHubPage =	AuditPage.Click_BackBtn();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		
		
		Equipment_IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("EN004");
		Equipment_IRTDDetailspage.enter_IDname("");
		Equipment_IRTDDetailspage.ClickSaveButton();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Equipcreation");
		Equipment_IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();
		
		EquipmentHubPage = Equipment_IRTDHubPage.click_Back_btn();
		MainHubPage = EquipmentHubPage.ClickBackBtn();
		
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String ExpectMSG1 = "“Equipment Name” field of  “EN004” updated from “A4” to “” modified by User ID : \"Ruchika1\" , User Name: \"Ruchika1\"";
		sa.assertEquals(AuditPage.get_auditEvent_text(), ExpectMSG1, "FAIL:The Audit trail record for delete Equipment ID  is not audited ");

		
		//AD_Equipment_Audit _006 Verify the Audit trail entry while Adding new   Equipment ID with the  Active Directory Admin  User
		
		MainHubPage =	AuditPage.Click_BackBtn();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		
		
		Equipment_IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("EN004");
		Equipment_IRTDDetailspage.enter_IDname("NewID4");
		Equipment_IRTDDetailspage.ClickSaveButton();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Equipcreation");
		Equipment_IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();
		
		EquipmentHubPage = Equipment_IRTDHubPage.click_Back_btn();
		MainHubPage = EquipmentHubPage.ClickBackBtn();
		
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String ExpectMSG2 = "“Equipment Name” field of  “EN004” updated from “” to “NewID4” modified by User ID : \"Ruchika1\" , User Name: \"Ruchika1\"";
		sa.assertEquals(AuditPage.get_auditEvent_text(), ExpectMSG2, "FAIL:The Audit trail record for delete Equipment ID  is not audited ");
	
		sa.assertAll();	

	}
	
	
	// AD_Equipment_Audit _005 Verify the Audit trail entry while Deleting Equipment
	// ID with the Active Directory Admin User

	@Test(description = "AD_Equipment_Audit _005 Verify the Audit trail entry while Deleting  Equipment ID with the  Active Directory Admin  User")

	public void AD_Asset_Audit_005() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"AD_Equipment_Audit _005 Verify the Audit trail entry while Deleting  Equipment ID with the  Active Directory Admin  User");

		System.out.println("This test case is covered on AD_Asset_Audit_004 ");

	}

	// AD_Equipment_Audit _006 Verify the Audit trail entry while Adding new
	// Equipment ID with the Active Directory Admin User

	@Test(description = "AD_Equipment_Audit _006 Verify the Audit trail entry while Adding new   Equipment ID with the  Active Directory Admin  User")

	public void AD_Asset_Audit_006() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"AD_Equipment_Audit _006 Verify the Audit trail entry while Adding new   Equipment ID with the  Active Directory Admin  User");

		System.out.println("This test case is covered on AD_Asset_Audit_004 ");

	}
	
	
	//AD_Equipment_Audit _007 Verify the Audit trail entry while Modifying Equipment Image with the  Active Directory Admin  User
	
	
		@Test( description = "AD_Equipment_Audit _007 Verify the Audit trail entry while Modifying Equipment Image with the  Active Directory Admin  User")

		public void AD_Equipment_Audit_007() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"AD_Equipment_Audit _007 Verify the Audit trail entry while Modifying Equipment Image with the  Active Directory Admin  User");

			SoftAssert sa = new SoftAssert();
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			
			NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
			NewEquipmentCreation_Page.EqipCreation_WithoutClickingSaveBtn("IRTD", "007", "m7M");
			NewEquipmentCreation_Page.click_EquipmentImage_UploadButton();
			Thread.sleep(2000);
			tu.uploadDoc("VRT_Pro.JPG");
			Thread.sleep(1000);
			NewEquipmentCreation_Page.ClickSaveButton();
			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Eqipcreation");
			EquipmentHubPage =	NewEquipmentCreation_Page.ClickBackBtn();
			Thread.sleep(2000);
		
			Equipment_IRTDHubPage = EquipmentHubPage.click_IRTDTile();
			
			Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("007");
			
			Equipment_IRTDDetailspage.click_Browse_btn();
			Thread.sleep(2000);
			tu.uploadDoc("Pressure.jpg");
			Equipment_IRTDDetailspage.ClickSaveButton();
			
			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Eqipcreation");
	        Equipment_IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();	
			EquipmentHubPage = Equipment_IRTDHubPage.click_Back_btn();
			MainHubPage = EquipmentHubPage.ClickBackBtn();
			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);
			String ExpectMSG = "Equipment Image field of \"007\" modified by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\"";
			sa.assertEquals(AuditPage.get_auditEvent_text(), ExpectMSG, "FAIL:The Audit trail record for image Equipment modification  is not audited ");
		
			MainHubPage = AuditPage.Click_BackBtn();
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			
	        Equipment_IRTDHubPage = EquipmentHubPage.click_IRTDTile();
			
			Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("007");
			Equipment_IRTDDetailspage.click_DeleteEquipImage();
			Equipment_IRTDDetailspage.Click_HistoryButton();
			Equipment_IRTDDetailspage.ClickSaveButton();
			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Eqipcreation");
			
			Equipment_IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();	
			EquipmentHubPage = Equipment_IRTDHubPage.click_Back_btn();
			MainHubPage = EquipmentHubPage.ClickBackBtn();
			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);
			String ExpectMSG1 = "Equipment Image field of \"007\" deleted by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\"";
			sa.assertEquals(AuditPage.get_auditEvent_text(), ExpectMSG1, "FAIL:The Audit trail record for image Equipment modification  is not audited ");
		
			//
			MainHubPage = AuditPage.Click_BackBtn();
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			
	        Equipment_IRTDHubPage = EquipmentHubPage.click_IRTDTile();
			
			Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("007");
			Equipment_IRTDDetailspage.click_Browse_btn();
			Thread.sleep(2000);
			tu.uploadDoc("VRT_Pro.JPG");
			Thread.sleep(1000);
			Equipment_IRTDDetailspage.ClickSaveButton();
			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Eqipcreation");
			
			Equipment_IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();	
			EquipmentHubPage = Equipment_IRTDHubPage.click_Back_btn();
			MainHubPage = EquipmentHubPage.ClickBackBtn();
			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);
			String ExpectMSG2 = "Equipment Image field of \"007\" added by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\"";
			sa.assertEquals(AuditPage.get_auditEvent_text(), ExpectMSG2, "FAIL:The Audit trail record for image Equipment modification  is not audited ");
		
			sa.assertAll();	
			}
	
	
	//AD_Equipment_Audit _008 Verify the Audit trail entry while Deleting  Equipment Image with the  Active Directory Admin  User

		@Test(description = "AD_Equipment_Audit _008 Verify the Audit trail entry while Deleting  Equipment Image with the  Active Directory Admin  User")

		public void AD_Equipment_Audit_008() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"AD_Equipment_Audit _008 Verify the Audit trail entry while Deleting  Equipment Image with the  Active Directory Admin  User");

			System.out.println("This test case is covered on AD_Asset_Audit_007");

		}
		
	   //AD_Equipment_Audit _009 Verify the Audit trail entry while Adding new  Equipment Image with the  Active Directory Admin  User
		
		@Test(description = "AD_Equipment_Audit _009 Verify the Audit trail entry while Adding new  Equipment Image with the  Active Directory Admin  User")

		public void AD_Equipment_Audit_009() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"AD_Equipment_Audit _009 Verify the Audit trail entry while Adding new  Equipment Image with the  Active Directory Admin  User");

			System.out.println("This test case is covered on AD_Asset_Audit_007");

		}
		
	//AD_Equipment_Audit _010 Verify the Audit trail entry while Copy to Drive  with the  Active Directory Admin   User
		
		@Test(description = "AD_Equipment_Audit _010 Verify the Audit trail entry while Copy to Drive  with the  Active Directory Admin User")

		public void AD_Equipment_Audit_010() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"AD_Equipment_Audit _010 Verify the Audit trail entry while Copy to Drive  with the  Active Directory Admin User");

			SoftAssert sa = new SoftAssert();

			EquipmentHubPage = MainHubPage.ClickEquipmentTile();

			NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
			NewEquipmentCreation_Page.EqipCreation_MandatoryFields("IRTD", "ABC1", "10l");
			UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Eqipcreation");
			EquipmentHubPage = NewEquipmentCreation_Page.ClickBackBtn();
			
			Equipment_IRTDHubPage = EquipmentHubPage.click_IRTDTile();
			Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("ABC1");
			Equipment_IRTDDetailspage.click_UploadDocsBtn();
			UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Eqipcreation");
			//Thread.sleep(2000);
			tu.uploadDoc("HelpFileWord");
			Equipment_IRTDHubPage=Equipment_IRTDDetailspage.click_Back_btn();
			Thread.sleep(2000);
			Equipment_IRTDDetailspage = Equipment_IRTDHubPage.Click_IrtdSerialNo("ABC1");
			Equipment_IRTDDetailspage.Select_DocName("HelpFileWord.docx");
			Thread.sleep(1000);
			Equipment_IRTDDetailspage.Equip_click_CopyToDrive();
			Equipment_IRTDDetailspage.selectFolder_CopyToDrive("AutoLogs");
			

			UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Eqipcreation");

			//String foldrpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AutoLogs";
			// System.out.println("Act Folderpath: "+filepath);
			Equipment_IRTDHubPage = Equipment_IRTDDetailspage.click_Back_btn();	
			EquipmentHubPage = Equipment_IRTDHubPage.click_Back_btn();
			MainHubPage = EquipmentHubPage.ClickBackBtn();
			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);
			String ExpectMSG2 = "Verification - \"HelpFileWord.docx\" , \"Copy to drive\" operation was performed by User Id : \"Ruchika1\", User Name : \"Ruchika1\" to \"C:\\Users\\Kaveri.Bedar\\git\\ADVRT\\src\\test\\resources\\TestData\\AutoLogs\"";
			sa.assertEquals(AuditPage.get_auditEvent_text(), ExpectMSG2, "FAIL:The Audit trail record for image Equipment modification  is not audited ");
		
			sa.assertAll();	

		}
}