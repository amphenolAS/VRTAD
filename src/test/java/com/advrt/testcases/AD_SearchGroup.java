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
import com.advrt.pages.FileManagementPage;//
import com.advrt.pages.Equipment_IRTDHubPage;
import com.advrt.pages.Equipment_IRTDDetailspage;
import com.advrt.utility.TestUtilities;


public class AD_SearchGroup extends BaseClass{

	public AD_SearchGroup() throws IOException {
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

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_AD_SearchGroupTest"+".html",true);
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
		AD_UMPage.select_grp("Automation");
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
		System.out.println("AD SearchGroup test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kaverib","Amphenol@123");
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



	//Verify if search button is avaliable under select group drop down in user management 

	@Test(priority=0,groups = { "Sanity",
	"Regression" }, description = "SG001-Verify if search button is avaliable under select group drop down in user management")

	public void SG001() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("SG001-Verify if search button is avaliable under select group drop down in user management");
		SoftAssert sa = new SoftAssert();

	AD_UMPage.Select_grp();
	String Actualvalue=AD_UMPage.selectgroupsearch();
    String Expectedvalue="Search Group";
    
		//By default system Admin have the privilages for UM pages
		sa.assertEquals(Actualvalue,Expectedvalue,
				"Fail: search button is not available");

		sa.assertAll();
	}

	
//Verify if user is not able to view the list of users  dropdown if group is not found
	@Test(priority=1,groups = { "Sanity",
	"Regression" }, description = "Verify if user is not able to view the list of users  dropdown if group is not found")

	public void SG002() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Verify if user is not able to view the list of users  dropdown if group is not found");
		SoftAssert sa = new SoftAssert();
	
		AD_UMPage.SearchSpecificGrp("xyz");
		AD_UMPage.Select_user();
	
		//by default in the userslist their is select user which is taking the count as 1
	sa.assertEquals(AD_UMPage.users_count(),1,"FAIL:Users are being dispalyed" );

		

		sa.assertAll();
	}


	
	//Verify if a message is being displayed when there are no users in configured group after clicking on save button 

	@Test(priority=2,groups = { "Sanity",
	"Regression" }, description = "Verify if a message is being displayed when there are no users in configured group after clicking on save button")

	public void SG003() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Verify if a message is being displayed when there are no users in configured group after clicking on save button");
		SoftAssert sa = new SoftAssert();

	AD_UMPage.select_grp("Automation1");
	AD_UMPage.select_UserTitle("Manager");
	AD_UMPage.select_UserType1("SystemAdministrator");
	AD_UMPage.clickSavebtn();
	
	String ActualMsg=tu.get_popup_text();
	String ExpectedMsg="Users Do not Exist in the Selected Group";
	
		//By default system Admin have the privilages for UM pages
		sa.assertEquals(ActualMsg,ExpectedMsg,
				"Fail: Msg is not being displayed");

		sa.assertAll();
	}
	
	
	
	//Verify if message is being displayed when user does not give any input  and click on enter button 

		@Test(priority=3,groups = { "Sanity",
		"Regression" }, description = "Verify if message is being displayed when user does not give any input  and click on enter button")

		public void SG004() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("Verify if message is being displayed when user does not give any input  and click on enter button");
			SoftAssert sa = new SoftAssert();

		AD_UMPage.select_grp("Automation");
		
		AD_UMPage.clickSavebtn();
		
		String ActualMsg=tu.get_popup_text();
		String ExpectedMsg="Please provide valid title";
		
			sa.assertEquals(ActualMsg,ExpectedMsg,
					"Fail: Msg is not being displayed");

			sa.assertAll();
		}

		
		
		//Verify if user is able to search group with maximum characters(50) 
		
		
		@Test(priority=4,groups = { "Sanity",
		"Regression" }, description = "Verify if user is able to search group with maximum characters(50) ")

		public void SG005() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("Verify if user is able to search group with maximum characters(50) ");
			SoftAssert sa = new SoftAssert();
			
			String expectedtxt = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345"; // 155 char input
			AD_UMPage.Enter_datain_searchgroup(expectedtxt);
			
			String actualtextentered =AD_UMPage.getgroupNameTxtBox();

			sa.assertEquals(actualtextentered.length(), 125, "FAIL: search group accepts more than 50 characters");
	
			sa.assertAll();
		}
		
		//Verify if user is able to search group by entering full name of the Group
		
		@Test(priority=5,groups = { "Sanity",
		"Regression" }, description = "erify if user is able to search group by entering full name of the Group")

		public void SG006() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("erify if user is able to search group by entering full name of the Group");
			SoftAssert sa = new SoftAssert();
			AD_UMPage.select_grp("Automation");
			
			sa.assertTrue(AD_UMPage.users_isDisplayed(),"FAIL:users box is not available");
			

			sa.assertAll();
		}
		
		
		//Verify if user is able to see the list of users in the select users drop down after entering the full group name 

		@Test(priority=6,groups = { "Sanity",
		"Regression" }, description = "Verify if user is able to see the list of users in the select users drop down after entering the full group name")

		public void SG007() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("Verify if user is able to see the list of users in the select users drop down after entering the full group name");
			SoftAssert sa = new SoftAssert();

		AD_UMPage.select_grp("Automation");
		AD_UMPage.Select_user();
	
		sa.assertEquals(AD_UMPage.UserCount()>0,true,
					"Fail: Users are not available");

			sa.assertAll();
		}
		
		//Verify if user is able to Enter data in AD search text field 
		
		@Test(priority=7,groups = { "Sanity",
		"Regression" }, description = "Verify if user is able to Enter data in AD search text field")

		public void SG008() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("Verify if user is able to Enter data in AD search text field");
			SoftAssert sa = new SoftAssert();

			AD_UMPage.Enter_datain_searchgroup("Automation");
			
			String Actualvalue=AD_UMPage.getgroupNameTxtBox();
		    String Expectedvalue="Automation";
		    
				
				sa.assertEquals(Actualvalue,Expectedvalue,
						"Fail: user unable to enter in search box");
			
		
			sa.assertAll();
		}

		
}