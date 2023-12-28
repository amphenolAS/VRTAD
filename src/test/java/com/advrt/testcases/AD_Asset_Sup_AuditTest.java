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
import com.advrt.pages.assetCreationPage;
import com.advrt.pages.ADUM_page;
import com.advrt.pages.assetDetailsPage;
import com.advrt.pages.Copyassetpage;
import com.advrt.pages.DefaultUserPrivilages_page;
import com.advrt.utility.ADUserManagementUtility;
import com.advrt.utility.TestUtilities;


public class AD_Asset_Sup_AuditTest extends BaseClass{
	
	public AD_Asset_Sup_AuditTest() throws IOException {
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
	assetCreationPage assetCreationPage;
	static String AdmnUN = "User1";
	
	
	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_AD_Asset_Sup_AuditTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("AD-UM Test in Progress..");
		

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
			// Create the default supervisor USer
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
			PoliciesPage.clickOn_AcceptBtn();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			tu.click_OK_popup();
			Thread.sleep(1000);
			ADUM_page =	PoliciesPage.ClickUM_Tab_AD();
		
			ADUM_page.select_grp("QA Testers");
			//ADUM_page.select_user(0);
			ADUM_page.enterNewUserTitle("Manager");
			ADUM_page.SelectUType("SystemAdministrator");
			Thread.sleep(1000);
			ADUM_page.ClickNewUserSaveButton();
			
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			tu.click_OK_popup();
			tu.click_OK_popup();
			Thread.sleep(2000);
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
			ADUM_page=MainHubPage.ClickAdminTile_ADUM();
			//AD_UMPage.Select_grp();
			ADUM_page.select_grp("Automation");//Automation
			//AD_UMPage.Select_user();
			//ADUM_page.select_user(0);
			ADUM_page.enterNewUserTitle("Manager");
			ADUM_page.SelectUType("Supervisor");
			Thread.sleep(1000);
			ADUM_page.ClickNewUserSaveButton();
			
			tu.UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "updated");
			tu.click_OK_popup();
			
			MainHubPage = ADUM_page.ClickBackButn();
			LoginPage = MainHubPage.UserSignOut();
			tu.AppClose();
			Thread.sleep(2000);
		
		}
	
	//After All the tests are conducted
	//@AfterTest
	@AfterClass
		public void endReport() throws InterruptedException {
			extent.flush();
			extent.close();
			System.out.println("AD-LOGIN  test completed");
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
	 * @throws ParseException 
	*********/
	
//26,27,28,48

  //AD_Asset_Audit-026 Verify the Audit trail entry  while Creating a new Asset with the  Active Directory Supervisor   User

	@Test(groups = { "Sanity",
			"Regression" }, description = "AD_Asset_ Audit 002 Verify the Audit trail entry  while Creating a new Asset with the  Active Directory Supervisor User")

	public void AD_Asset_Audit_026() throws InterruptedException, AWTException, IOException, ParseException {
		extentTest = extent.startTest(
				"AD_Asset_ Audit 002 Verify the Audit trail entry  while Creating a new Asset with the  Active Directory Supervisor User");

		SoftAssert sa = new SoftAssert();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		//assetCreationPage.assetCreation("ADAssetAudit026", "A02", "HeathBath", "Aas", "Hyd");
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("ADAssetAudit026", "A02", "HeatBath", "Aas", "Hyd","VRT","1","cu m",crntDate,"1","Months","Test Description");

		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset : \"ADAssetAudit026\" is created by User Id : \"Ruchika1\" , User Name : \"Ruchika1\".");
		sa.assertAll();

	}
	
	//AD_Asset_ Audit027 Verify the Audit trail entry after modifying asset name with the  Active Directory Supervisor   User
	


	@Test(groups = { "Sanity",
			"Regression" }, description = "AD_Asset_ Audit_027 Verify the Audit trail entry after modifying asset name with the  Active Directory Supervisor User")

	public void AD_Asset_Audit_027() throws InterruptedException, AWTException, IOException, ParseException {
		extentTest = extent.startTest(
				"AD_Asset_ Audit_027 Verify the Audit trail entry after modifying asset name with the  Active Directory Supervisor User");

		SoftAssert sa = new SoftAssert();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		//assetCreationPage.assetCreation("Asset027", "03", "HeathBath", "Aas", "Hyd");
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset027", "03", "HeatBath", "Aas", "Hyd","VRT","1","cu m",crntDate,"1","Months","Test Description");


		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		assetHubPage = assetCreationPage.clickBackBtn();

		assetDetailsPage = assetHubPage.click_assetTile("Asset027");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterAssetName("Adt27");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();

		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset : \"Asset027\" is modified to ,\" Adt27 \"  by User ID : \"Ruchika1\"  and  User Name : \"Ruchika1\" .");
		sa.assertAll();

	}
	
	
	//AD_Asset_ Audit 028 Verify the Audit trail entry after modifying Asset ID with the  Active Directory Supervisor User
	
	@Test( description = "AD_Asset_ Audit 028 Verify the Audit trail entry after modifying Asset ID with the  Active Directory Supervisor User")

	public void AD_Asset_Audit_028() throws InterruptedException, AWTException, IOException, ParseException {
		extentTest = extent.startTest(
				"AD_Asset_ Audit 028 Verify the Audit trail entry after modifying Asset ID with the  Active Directory Supervisor User");

		SoftAssert sa = new SoftAssert();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		//assetCreationPage.assetCreation("Asset028", "28", "HeathBath", "Aas", "Hyd");
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("Asset028", "28", "HeatBath", "Aas", "Hyd","VRT","1","cu m",crntDate,"1","Months","Test Description");


		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		assetHubPage = assetCreationPage.clickBackBtn();

		assetDetailsPage = assetHubPage.click_assetTile("Asset028");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterAssetID("A28");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();

		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset : \"Asset028\" \"Asset ID\" field modified from \" 28 to A28 \" by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\".");
		
		
		MainHubPage = AuditPage.Click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Asset028");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterAssetType("Sterilizer");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();

		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset : \"Asset028\" \"Type\" field modified from HeatBath to HeatBathSterilizer by  User ID : Ruchika1 , User Name : Ruchika1.");
		Thread.sleep(1000);
		MainHubPage = AuditPage.Click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Asset028");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterManufacturerName("M1");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset : \"Asset028\" \"Manufacturer\" field modified from Aas to M1 by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\".");
	
		
		
		Thread.sleep(1000);
		MainHubPage = AuditPage.Click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Asset028");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterLocation("India");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset : \"Asset028\" \"Location\" field modified from Hyd to India by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\".");
		
		sa.assertAll();

		
		

	}
	
	//AD_Asset_ Audit_029 Verify the Audit trail entry after modifying Asset Type with the  Active Directory Supervisor User

	@Test(description = "AD_Asset_ Audit_029 Verify the Audit trail entry after modifying Asset Type with the  Active Directory Supervisor User")

	public void AD_Asset_Audit_029() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"AD_Asset_ Audit_029 Verify the Audit trail entry after modifying Asset Type with the  Active Directory Supervisor User");

		System.out.println("This tcs is handeled on aboove AD_Asset_Audit_028");
	}
	

	// AD_Asset_ Audit _030 Verify the Audit trail entry after modifying Asset manufacturer with the  Active Directory Supervisor User

	@Test( description = "AD_Asset_ Audit 030 Verify the Audit trail entry after modifying Asset manufacturer with the  Active Directory Supervisor User")

	public void AD_Asset_Audit_030() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"AD_Asset_ Audit 030 Verify the Audit trail entry after modifying Asset manufacturer with the  Active Directory Supervisor User");

		System.out.println("This tcs is handeled on aboove AD_Asset_Audit_028");
	}

	
	// AD_Asset_ Audit_031 Verify the Audit trail entry after modifying Asset location with the  Active Directory Supervisor User

	@Test(description = "AD_Asset_ Audit_031 Verify the Audit trail entry after modifying Asset location with the  Active Directory Supervisor User")

	public void AD_Asset_Audit_031() throws InterruptedException, AWTException, IOException {
		
		extentTest = extent.startTest(
				"AD_Asset_ Audit_031 Verify the Audit trail entry after modifying Asset location with the  Active Directory Supervisor User");

		System.out.println("This tcs is handeled on aboove AD_Asset_ Audit_028");
	}
	
	
  //AD_Asset_ Audit 032 Verify the Audit trail entry after modifying Asset Model with the  Active Directory Supervisor User
	
	
	@Test( description = "AD_Asset_ Audit 032 Verify the Audit trail entry after modifying Asset Model with the  Active Directory Supervisor User")

	public void AD_Asset_Audit_032() throws InterruptedException, AWTException, IOException, ParseException {
		extentTest = extent.startTest("AD_Asset_ Audit 032 Verify the Audit trail entry after modifying Asset Model with the  Active Directory Supervisor User");

		SoftAssert sa = new SoftAssert();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		
		assetCreationPage.assetCreationWithAllFieldEntry("Ast032", "32", "HeatBath", "Aas", "Hyd","VRT","1","cu m",crntDate,"1","Months","Test Description");

		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		assetHubPage = assetCreationPage.clickBackBtn();

		assetDetailsPage = assetHubPage.click_assetTile("Ast032");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterModelName("VRT1");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();

		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset : \"Ast032\" \"Model\" field modified from VRT to VRT1 by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\".");
		
		
		Thread.sleep(1000);
		MainHubPage = AuditPage.Click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Ast032");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterModelName("");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset : \"Ast032\" \"Model\" field modified from VRT1 to  by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\".");
			
		Thread.sleep(1000);
		MainHubPage = AuditPage.Click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Ast032");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterModelName("");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset : \"Ast032\" \"Model\" field modified from  to VRT2 by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\".");
		
		
		//Verify the Audit trail entry after modifying Asset Size with the Active Directory supervisor User
		
		Thread.sleep(1000);
		MainHubPage = AuditPage.Click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Ast032");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.enterSize_Unit("2","cu ft");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset : \"Ast032\" \"Size\" Units field modified from cu m to cu ft by User ID : \"Ruchika1\" , User Name : \"Ruchika1\".");
		
			
		
		//AD_Asset_ Audit 013 Verify the Audit trail entry after modifying Asset Last validated with the  Active Directory supervisor   User
	
	
		Thread.sleep(1000);
		MainHubPage = AuditPage.Click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Ast032");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.selectAssetLastVldDate("19", "11", "2023");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

	//	sa.assertEquals(AuditPage.get_auditEvent_text(),
	//			"Asset : \"Ast008\" \"Last Validated\" field modified from 20-11-2023 15:50:36 to 19-11-2023 15:50:36 by  User ID : \"Ruchika1\" , User Name : \"Ruchika1\".");
		
	// Verify the Audit trail entry after modifying Asset Validation frequency with the  Active Directory Supervisor   User
		
		Thread.sleep(1000);
		MainHubPage = AuditPage.Click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Ast032");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.selectAssetFreq("2");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset Calibration Frequency\" field of Ast032 updated from 1 to 2 by User Id : \"Ruchika1\" , User Name : \"Ruchika1\"");
	
		
		//AD_Asset_ Audit 015 Verify the Audit trail entry after modifying Asset Validation frequency with weeks option form drop down with the  Active Directory supervisor   User
	
		Thread.sleep(1000);
		MainHubPage = AuditPage.Click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Ast032");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.selectAssetFreqIntrvl("Weeks");
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset Calibration Frequency\" field of Ast032 updated from Months to Weeks by User Id : \"Ruchika1\" , User Name : \"Ruchika1\"");
		
		Thread.sleep(1000);
		MainHubPage = AuditPage.Click_BackBtn();
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Ast032");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.selectAssetFreqIntrvl("Months");//Months
		assetCreationPage.clickSaveBtn();
		tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
		tu.click_Close_alertmsg();
		
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.click_BackBtn();

		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);

		sa.assertEquals(AuditPage.get_auditEvent_text(),
				"Asset Calibration Frequency\" field of Ast032 updated from Weeks to Months by User Id : \"Ruchika1\" , User Name : \"Ruchika1\"");
	
	//years
	
	Thread.sleep(1000);
	MainHubPage = AuditPage.Click_BackBtn();
	assetHubPage = MainHubPage.Click_AssetTile2();
	assetDetailsPage = assetHubPage.click_assetTile("Ast032");
	assetCreationPage = assetDetailsPage.click_assetEditBtn();
	assetCreationPage.selectAssetFreqIntrvl("Years");//Years
	assetCreationPage.clickSaveBtn();
	tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
	tu.click_Close_alertmsg();
	
	assetDetailsPage = assetCreationPage.click_BackBtn();
	assetHubPage = assetDetailsPage.ClickBackBtn();
	MainHubPage = assetHubPage.click_BackBtn();

	AuditPage = MainHubPage.ClickAuditTitle();
	Thread.sleep(2000);

	sa.assertEquals(AuditPage.get_auditEvent_text(),
			"Asset Calibration Frequency\" field of Ast032 updated from Months to Years by User Id : \"Ruchika1\" , User Name : \"Ruchika1\"");
}
	
	
	//AD_Asset_Audit_033-Verify the Audit trail entry after Deleting  Asset Model with the  Active Directory Supervisor User
	
	
	@Test( description = "AD_Asset_ Audit 033 Verify the Audit trail entry after Deleting  Asset Model with the  Active Directory Supervisor User")

	public void AD_Asset_Audit_033() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest("AD_Asset_Audit_033 - Verify the Audit trail entry after Deleting  Asset Model with the  Active Directory Supervisor User");

		System.out.println("This Tc has handeled in  AD_Asset_Audit_032");
	
	}
	
	
	//AD_Asset_ Audit 034 Verify the Audit trail entry after Defining new   Asset Model with the  Active Directory Supervisor User
	
	
		@Test( description = "AD_Asset_ Audit 034 Verify the Audit trail entry after Defining new   Asset Model with the  Active Directory Supervisor User")

		public void AD_Asset_Audit_034() throws InterruptedException, AWTException, IOException {
			
			extentTest = extent.startTest("AD_Asset_ Audit 034 Verify the Audit trail entry after Defining new   Asset Model with the  Active Directory Supervisor User");

			System.out.println("This Tc has handeled in  AD_Asset_Audit_032");
		}
		
    //AD_Asset_ Audit_035 Verify the Audit trail entry after modifying Asset Size with the  Active Directory Supervisor User
		
		@Test( description = "AD_Asset_ Audit 035 Verify the Audit trail entry after modifying Asset Size with the  Active Directory Supervisor User")

		public void AD_Asset_Audit_035() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest("AD_Asset_ Audit 035 Verify the Audit trail entry after modifying Asset Size with the  Active Directory Supervisor User");

			System.out.println("This Tc has handeled in  AD_Asset_Audit_032");
		}
		

		// AD_Asset_ Audit 036 Verify the Audit trail entry after setting to default
		// value in Asset Size with the Active Directory Supervisor User

		@Test(description = "AD_Asset_ Audit 036 Verify the Audit trail entry after setting to default value in  Asset Size with the  Active Directory Supervisor   User")

		public void AD_Asset_Audit_036() throws InterruptedException, AWTException, IOException {

			extentTest = extent.startTest(
					"AD_Asset_ Audit 036 Verify the Audit trail entry after setting to default value in  Asset Size with the  Active Directory Supervisor   User");

			System.out.println("This Tc has handeled in  AD_Asset_Audit_032");
		}
		
		
		
		
	     // AD_Asset_ Audit 037 Verify the Audit trail entry after modifying Asset Last validated with the  Active Directory Supervisor   User
		
		@Test( description = "AD_Asset_ Audit 037 Verify the Audit trail entry after modifying Asset Last validated with the  Active Directory Supervisor   User"
				+ "	")

		public void AD_Asset_Audit_037() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest("AD_Asset_ Audit 037 Verify the Audit trail entry after modifying Asset Last validated with the  Active Directory Supervisor   User"
					+ "	");

			System.out.println("This Tc has handeled in  AD_Asset_Audit_032");
		}
		
		
		

//AD_Asset_ Audit 038 Verify the Audit trail entry after modifying Asset Validation frequency with the  Active Directory Supervisor   User

		@Test( description = "AD_Asset_ Audit 038 Verify the Audit trail entry after modifying Asset Validation frequency with the  Active Directory Supervisor   User"
				+ "	")

		public void AD_Asset_Audit_038() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest("AD_Asset_ Audit 038 Verify the Audit trail entry after modifying Asset Validation frequency with the  Active Directory Supervisor   User"
					+ "	");

			System.out.println("This Tc has handeled in  AD_Asset_Audit_032");
		}
		
		
		
		
//AD_Asset_ Audit 039 Verify the Audit trail entry after modifying Asset Validation frequency with weeks option form drop down with the  Active Directory Supervisor   User

		@Test( description = "AD_Asset_ Audit 039 Verify the Audit trail entry after modifying Asset Validation frequency with weeks option form drop down with the  Active Directory Supervisor   User"
				+ "	")

		public void AD_Asset_Audit_039() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest("AD_Asset_ Audit 039 Verify the Audit trail entry after modifying Asset Validation frequency with weeks option form drop down with the  Active Directory Supervisor   User"
					+ "	");

			System.out.println("This Tc has handeled in  AD_Asset_Audit_032");
		}
		
//AD_Asset_ Audit 040 Verify the Audit trail entry after modifying Asset Validation frequency with Months option form drop down with the  Active Directory Supervisor   User
		
		@Test( description = "AD_Asset_ Audit 040 Verify the Audit trail entry after modifying Asset Validation frequency with Months option form drop down with the  Active Directory Supervisor   User")

		public void AD_Asset_Audit_016() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest("AD_Asset_ Audit 040 Verify the Audit trail entry after modifying Asset Validation frequency with Months option form drop down with the  Active Directory Supervisor   User");

			System.out.println("This Tc has handeled in  AD_Asset_Audit_032");
		}
		
		
		
   //AD_Asset_ Audit 041 Verify the Audit trail entry after modifying Asset Validation frequency with Year option form drop down with the  Active Directory Supervisor User

		@Test(description = "AD_Asset_ Audit 041 Verify the Audit trail entry after modifying Asset Validation frequency with Year option form drop down with the  Active Directory Supervisor   User")

		public void AD_Asset_Audit_041() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"AD_Asset_ Audit 041 Verify the Audit trail entry after modifying Asset Validation frequency with Year option form drop down with the  Active Directory Supervisor   User");

			System.out.println("This Tc has handeled in  AD_Asset_Audit_032");
		}
		
   //AD_Asset_ Audit 042 Verify the Audit trail entry after modifying Asset Description with the  Active Directory Supervisor   User
		
		@Test(description = "AD_Asset_ Audit 042 Verify the Audit trail entry after modifying Asset Description with the  Active Directory Supervisor   User")

		public void AD_Asset_Audit_042() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"AD_Asset_ Audit 042 Verify the Audit trail entry after modifying Asset Description with the  Active Directory Supervisor   User");
			SoftAssert sa = new SoftAssert();
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetCreationPage = assetHubPage.ClickAddAssetBtn();
			
			assetCreationPage.assetCreationWithAllFieldEntry("Ast042", "42", "HeatBath", "Aas", "Hyd","VRT","1","cu m","11/20/2023","1","Months","Add");

			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
			tu.click_Close_alertmsg();
			assetHubPage = assetCreationPage.clickBackBtn();

			assetDetailsPage = assetHubPage.click_assetTile("Ast042");
			assetCreationPage = assetDetailsPage.click_assetEditBtn();
			assetCreationPage.enterAstDescription("TestAdd");
			assetCreationPage.clickSaveBtn();
			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
			tu.click_Close_alertmsg();

			assetDetailsPage = assetCreationPage.click_BackBtn();
			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();

			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);

			sa.assertEquals(AuditPage.get_auditEvent_text(),
					"Asset : \"Ast042\"  \"Description\" field modified from \"  Add to TestAdd \" by User Id : \"Ruchika1\" , User Name : \"Ruchika1\".");
			
			Thread.sleep(1000);
			MainHubPage = AuditPage.Click_BackBtn();
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile("Ast042");
			assetCreationPage = assetDetailsPage.click_assetEditBtn();
			assetCreationPage.enterAstDescription("");//Years
			assetCreationPage.clickSaveBtn();
			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
			tu.click_Close_alertmsg();
			
			assetDetailsPage = assetCreationPage.click_BackBtn();
			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();

			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);

			sa.assertEquals(AuditPage.get_auditEvent_text(),
					"Asset : \"Ast042\"  \"Description\" field modified from \"  TestAdd to  \" by User Id : \"Ruchika1\" , User Name : \"Ruchika1\".");

			Thread.sleep(1000);
			MainHubPage = AuditPage.Click_BackBtn();
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile("Ast042");
			assetCreationPage = assetDetailsPage.click_assetEditBtn();
			assetCreationPage.enterAstDescription("NewComment");//Years
			assetCreationPage.clickSaveBtn();
			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
			tu.click_Close_alertmsg();
			
			assetDetailsPage = assetCreationPage.click_BackBtn();
			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();

			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);

			sa.assertEquals(AuditPage.get_auditEvent_text(),
					"Asset : \"Ast042\"  \"Description\" field modified from \"   to NewComment \" by User Id : \"Ruchika1\" , User Name : \"Ruchika1\".");
			
			sa.assertAll();
			
			
			
		}
			
		
	//AD_Asset_ Audit 043 Verify the Audit trail entry after Deleting  Asset Description with the  Active Directory Supervisor   User
		
		@Test(description = "AD_Asset_ Audit 043 Verify the Audit trail entry after Deleting  Asset Description with the  Active Directory Supervisor   User")

		public void AD_Asset_Audit_019() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"AD_Asset_ Audit 043 Verify the Audit trail entry after Deleting  Asset Description with the  Active Directory Supervisor   User");

			System.out.println("This Tc has handeled in  AD_Asset_Audit_042");
		}
		
		// AD_Asset_ Audit 044 Verify the Audit trail entry after Adding new  Asset Description with the  Active Directory Supervisor   User

		@Test(description = "AD_Asset_ Audit 044 Verify the Audit trail entry after Adding new  Asset Description with the  Active Directory Supervisor   User"
				+ "	")

		public void AD_Asset_Audit_044() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"AD_Asset_ Audit 044 Verify the Audit trail entry after Adding new  Asset Description with the  Active Directory Supervisor   User"
							+ "	");

			System.out.println("This Tc has handeled in  AD_Asset_Audit_042");
		}
		
    //AD_Asset_ Audit 045 Verify the Audit trail entry after Changing Asset image with the  Active Directory Supervisor   User		
		
		
		@Test( description = "AD_Asset_ Audit 045 Verify the Audit trail entry after Changing Asset image with the  Active Directory Supervisor   User")
		public void AD_Asset_Audit_045 ()
				throws InterruptedException, ParseException, AWTException, IOException {
			extentTest = extent.startTest("AD_Asset_ Audit 045 Verify the Audit trail entry after Changing Asset image with the  Active Directory Supervisor   User");
			SoftAssert sa = new SoftAssert();
			
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetCreationPage = assetHubPage.ClickAddAssetBtn();
			// Add any image to the Asset Image place holder1 using browse button
			assetCreationPage.click_ImgBrws_Btn();
			Thread.sleep(2000);
			tu.uploadDoc("avsLOGO.jpg");

			// Capture the expected Image added to the Asset placeholder 1
			assetCreationPage.Capture_AsstImg1("1stAddedImg_Asst37_AsstCreation");
			String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
			
			assetCreationPage.assetCreationWithAllFieldEntry("Ast045", "21", "HeatBath", "Aas", "Hyd","VRT","1","cu m",crntDate,"1","Months","Test Description");
	
		    tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "comment");
			// Move to AssetHub Page
			assetHubPage = assetCreationPage.clickBackBtn();

			// Again select the above Asset created to move to Asset detail page and then
			// click the Edit asset button
			assetDetailsPage = assetHubPage.click_assetTile("Ast045");
			assetCreationPage = assetDetailsPage.click_assetEditBtn();

			// Click the Image1 Place Holder to change the Image using Edit button
			assetCreationPage.click_Img1_Placeholder_Btn();
			// Click the Image1 edit button to change image
			assetCreationPage.click_Img_Placeholder_Edit_Btn();
			Thread.sleep(2000);
			// Add a new image
			tu.uploadDoc("VRT_Pro.JPG");

			// Save the asset
			assetCreationPage.clickSaveBtn();
			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "comment");
			assetDetailsPage = assetCreationPage.click_BackBtn();

			assetCreationPage = assetDetailsPage.click_assetEditBtn();

			// Capture the actual Image saved to the Asset placeholder 1
			assetCreationPage.Capture_AsstImg1("2ndAddedImg_Asst37_AsstCreation");

			boolean status_ImgCompare = tu.compareImage("1stAddedImg_Asst37_AsstCreation",
					"2ndAddedImg_Asst37_AsstCreation");

			sa.assertEquals(status_ImgCompare, true,
					"FAIL: The Asset new Image added/saved is same to one added previously");

			assetDetailsPage = assetCreationPage.click_BackBtn();
			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();

			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);

			sa.assertEquals(AuditPage.get_auditEvent_text(),
					"Asset : \"Ast045\"  is modified by User ID : \"Ruchika1 \", User Name : \"Ruchika1\" .");
			
			Thread.sleep(1000);
			
			MainHubPage = AuditPage.Click_BackBtn();
			
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile("Ast045");
			assetCreationPage = assetDetailsPage.click_assetEditBtn();
			
			assetCreationPage.click_Img1_Placeholder_Btn();
			// Click the Image1 edit button to change image
			assetCreationPage.click_Img_Placeholder_Delete_Btn();

			// Save the asset
			assetCreationPage.clickSaveBtn();
			
			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "comment");
			assetDetailsPage = assetCreationPage.click_BackBtn();
			
			assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();

			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);

			sa.assertEquals(AuditPage.get_auditEvent_text(),
					"Asset : \"Ast045\"  is modified by User ID : \"Ruchika1 \", User Name : \"Ruchika1\" .");
			
			
			sa.assertAll();
		
		
		}
		
		
		// AD_Asset_ Audit 046 Verify the Audit trail entry after Deleting Asset image
		// with the Active Directory Supervisor User

		@Test(description = "AD_Asset_ Audit 046 Verify the Audit trail entry after Deleting  Asset image with the  Active Directory Supervisor User")
		public void AD_Asset_Audit_046() throws InterruptedException, ParseException, AWTException, IOException {
			extentTest = extent.startTest(
					"AD_Asset_ Audit 046 Verify the Audit trail entry after Deleting  Asset image with the  Active Directory Supervisor User");

			System.out.println("This tc has handeled on AD_Asset_Audit_045");
		}
		

		// AD_Asset_ Audit 047 Verify the Audit trail entry after Uploading new Asset
		// image with the Active Directory Supervisor User

		@Test(description = "AD_Asset_ Audit 047 Verify the Audit trail entry after Uploading new  Asset image with the  Active Directory Supervisor User")
		public void AD_Asset_Audit_023() throws InterruptedException, ParseException, AWTException, IOException {
			extentTest = extent.startTest(
					"AD_Asset_ Audit 047 Verify the Audit trail entry after Uploading new  Asset image with the  Active Directory Supervisor User");

			System.out.println("This tc has handeled on AD_Asset_Audit_047");
		}		
		
		
		
		//AD_Asset_ Audit 048 Verify the Audit trail entry after performing copy asset with Active Directory Supervisor User
		
	
		@Test(description = "AD_Asset_ Audit 048 Verify the Audit trail entry after performing copy asset with Active Directory Supervisor   User")

		public void AD_Asset_Audit_048() throws InterruptedException, AWTException, IOException {
			
			extentTest = extent.startTest("AD_Asset_ Audit 048 Verify the Audit trail entry after performing copy asset with Active Directory Supervisor   User");
			
			SoftAssert sa = new SoftAssert();
            MainHubPage.UserSignOut();
			
			//-----------------
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
			ADUM_page=MainHubPage.ClickAdminTile_ADUM();
			ADUM_page.select_grp("Automation");
			ADUM_page.enterNewUserTitle("Manager");
			DefaultUserPrivilages_page=ADUM_page.SelectUType1("NewUserType");
			DefaultUserPrivilages_page=ADUM_page.newUserType1("Supervisor");
			DefaultUserPrivilages_page.Click_CopyFiles_Reports();
			//ADUM_page	=DefaultUserPrivilages_page.click_save_btn();
		    DefaultUserPrivilages_page.click_savebtn_alert();
			Thread.sleep(1000);
			tu.UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "created");
			Thread.sleep(1000);
			ADUM_page.enterNewUserTitle("Manager");
			ADUM_page.SelectUType("Supervisor");
			ADUM_page.ClickNewUserSaveButton();
			tu.UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "created");
			tu.click_OK_popup();
			
			MainHubPage = ADUM_page.ClickBackButn();
			LoginPage = MainHubPage.UserSignOut();
			
			
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login("ruchika1","Amphenol@123");
			
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetCreationPage = assetHubPage.ClickAddAssetBtn();
			
			assetCreationPage.assetCreationWithAllFieldEntry("Asset048", "48", "HeatBath", "Aas", "Hyd","VRT","1","cu m","11/20/2023","1","Months","Add");

			tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "Assetcreation");
			tu.click_Close_alertmsg();
			assetHubPage = assetCreationPage.clickBackBtn();

			assetDetailsPage = assetHubPage.click_assetTile("Asset048");
			Copyassetpage = assetDetailsPage.clickCopyasset();
			Copyassetpage.copyAsset_Creation("CopyAst48", "24");
			
		    tu.UserLoginPopup_UserCommentTextBox("ruchika1", "Amphenol@123", "copyassetdone");
		    
		    
		    assetDetailsPage =  Copyassetpage.clickBack_Button();
		    assetHubPage = assetDetailsPage.ClickBackBtn();
			MainHubPage = assetHubPage.click_BackBtn();
			
			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);

			sa.assertEquals(AuditPage.get_auditEvent_text(),
					"Asset : \"CopyAst48\" is created by User Id : \"Ruchika1\" , User Name : \"Ruchika1\".");
			
			sa.assertAll();
			 
		}
			
		
		
		
}