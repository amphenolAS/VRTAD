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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.advrt.base.BaseClass;
import com.advrt.pages.LoginPage;
import com.advrt.pages.MainHubPage;
import com.advrt.pages.PoliciesPage;
import com.advrt.pages.PreferencesPage;
import com.advrt.pages.UserManagementPage;//
import com.advrt.pages.AuditPage;
import com.advrt.pages.AD_UMPage;
import com.advrt.pages.EquipmentHubPage;
import com.advrt.pages.NewEquipmentCreation_Page;
import com.advrt.utility.TestUtilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AD_PreferenceTest extends BaseClass {

	public AD_PreferenceTest() throws IOException {
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
	PreferencesPage preferencesPage;
	AuditPage AuditPage;
	EquipmentHubPage EquipmentHubPage;
	NewEquipmentCreation_Page NewEquipmentCreation_Page;
	static String AdmnUN = "User1";


	//Before All the tests are conducted
	@BeforeClass
	//@BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ER"+"_ADpreferenceTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		//extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		//extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		//extent.addSystemInfo("ScriptVersion-Git", prop1.getProperty("git.commit.id.describe-short").split("-")[0]);
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("ADpreference Test in Progress..");


		
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
		//---UserManagementPage = PoliciesPage.click_UMHeader();
		//UserManagementPage.ClickNewUser();

		PoliciesPage.Click_ActiveDirectoryUserbutton_Btn();
		PoliciesPage.ActiveDirectoryUserLoginPopup("Kiranc@VRT.LOCAL", "Amphenol@123", "10.17.17.54", "Secure");
		PoliciesPage.clickOn_ConnectBtn();
		PoliciesPage.ClickSaveButton();
		PoliciesPage.clickOn_AcceptBtn();
		//---UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		tu.click_OK_popup();


		AD_UMPage=PoliciesPage.click_AD_UMHeader();
		//AD_UMPage.select_grp();
		AD_UMPage.select_grp("QA Testers");
		//AD_UMPage.Select_user();
		AD_UMPage.select_user(1);
		AD_UMPage.select_UserTitle("Manager");
		AD_UMPage.select_UserType1("SystemAdministrator");
		AD_UMPage.clickSavebtn();
		tu.click_OK_popup();


		AppClose();
		Thread.sleep(2000);

	}

	//After All the tests are conducted
	//@AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("AD Preference test completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();

		//--MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		AD_UMPage=MainHubPage.AD_ClickAdminTile_UMpage();
		preferencesPage =AD_UMPage.Click_PreferenceTab();

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

	
	//Preferences01-Verify if Mandatory User Comment Audit Trail field available in the Preferences screen

	@Test(priority = 0,groups  = { "Sanity",
	"Regression" }, description = "Preferences01-Verify if Mandatory User Comment Audit Trail field available in the Preferences screen")

	public void ADP01() throws InterruptedException {
		extentTest = extent
				.startTest("Preferences01-Verify if Mandatory User Comment Audit Trail field available in the Preferences screen");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(preferencesPage.IsUserAudit_Presence(), true,
				"Fail:  UserAudit checkbox is not Displayed");

		sa.assertAll();
	}



	//Preferences02-Verify if Mandatory User Comment Audit Trail field is unchecked by default in the Preferences screen

	@Test(priority = 1,groups = { "Sanity",
	"Regression" }, description = "Preferences02-Verify if Mandatory User Comment Audit Trail field is unchecked by default in the Preferences screen")

	public void ADP02() throws InterruptedException {
		extentTest = extent
				.startTest("Preferences02-Verify if Mandatory User Comment Audit Trail field is unchecked by default in the Preferences screen");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(preferencesPage.IsMandatoryuser_checked(), false,
				"Fail:  UserAudit checkbox is enableds");

		sa.assertAll();
	}


	//Preferences03-Verify if audit comments should be recorded when the Mandatory User Comment Audit Trail checkbox is checked
	@Test(priority = 2,groups = { "Sanity",
	"Regression" }, description = "Preferences03-Verify if audit comments should be recorded when the Mandatory User Comment Audit Trail checkbox is checked")

	public void ADP03() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences03-Verify if audit comments should be recorded when the Mandatory User Comment Audit Trail checkbox is checked");
		SoftAssert sa = new SoftAssert();

		preferencesPage.click_Mandatory_user_comment();
		preferencesPage.click_SaveBtn();
		//UserLoginPopup("kiranc","Amphenol@123");
		UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","usercommitted.");

		tu.click_OK_popup();
		MainHubPage=preferencesPage.ClickBackButn();
		Thread.sleep(500);
		AuditPage = MainHubPage.ClickAuditTitle();

		String ExpectedMsg="usercommitted.";
		String ActualMsg= AuditPage.get_auditUsercommit_text();
		sa.assertEquals(ActualMsg, ExpectedMsg, "FAIL:The Audit trail record for audit comments  activity is not exist ");



		sa.assertAll();
	}


	//Preferences04-Verify if audit comments should not be recorded when the Mandatory User Comment Audit Trail checkbox is unchecked
	@Test(priority = 3,groups = { "Sanity",
	"Regression" }, description = "Preferences04-Verify if audit comments should not be recorded when the Mandatory User Comment Audit Trail checkbox is unchecked")

	public void ADP04() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences04-Verify if audit comments should not be recorded when the Mandatory User Comment Audit Trail checkbox is unchecked");
		SoftAssert sa = new SoftAssert();

		if(preferencesPage.IsMandatoryuser_checked())
		{
		preferencesPage.click_Mandatory_user_comment();
		preferencesPage.click_SaveBtn();
		UserLoginPopup("kiranc","Amphenol@123");
		}else {
			preferencesPage.click_SaveBtn();
			UserLoginPopup("kiranc","Amphenol@123");
			System.out.println("Not enabled");
		}
		


		//UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","NA");
		//tu.click_OK_popup();
		//MainHubPage=preferencesPage.ClickBackButn();
		//Thread.sleep(500);
		//AuditPage = MainHubPage.ClickAuditTitle();


		String ExpectedMsg="User Comment Audit Trail Cannot be blank.";
		String ActualMsg= tu.get_popup_text();
		sa.assertEquals(ActualMsg, ExpectedMsg, "FAIL:The Audit trail record for audit comments  activity is not exist ");
		//sa.assertEquals(tu.ISpopupVisible(), true,"FAIL:popup is not visible");
		sa.assertAll();
	}			



	//Preferences05-Verify if instrument calibration warning field should be available in the Preferences screen

	@Test(priority = 4,groups = { "Sanity",
	"Regression" }, description = "Preferences05-Verify if instrument calibration warning field should be available in the Preferences screen")

	public void ADP05() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences05-Verify if instrument calibration warning field should be available in the Preferences screen");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(preferencesPage.IsInstCal_Presence(),true, "FAIL:The Audit trail record for audit comments  activity is not exist ");

		sa.assertAll();
	}	


	//Preferences06-Verify if instrument calibration warning field is checked and displayed 12 months by default in the Preferences screen

	@Test(priority = 5,groups = { "Sanity",
	"Regression" }, description = "Preferences06-Verify if instrument calibration warning field is checked and displayed 12 months by default in the Preferences screen")

	public void ADP06() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences06-Verify if instrument calibration warning field is checked and displayed 12 months by default in the Preferences screen");
		SoftAssert sa = new SoftAssert();
		// preferencesPage.clickon_IsInstCal();

		sa.assertEquals(preferencesPage.Fetch_Defaultval_monthComboBox(),"12", "FAIL:when clicked on instrument calibration warning field is checked , 12 months is not displayed by default in the Preferences screen");
		sa.assertAll();
	}


	//Preferences07-Verify if number of equipments which are in calibration due should be displayed for the equipment when instrument calibration warning checkbox checked

	@Test(priority = 7,groups = { "Sanity",
	"Regression" }, description = "Preferences07-Verify if number of equipments which are in calibration due should be displayed for the equipment when instrument calibration warning checkbox checked")

	public void ADP07() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences07-Verify if number of equipments which are in calibration due should be displayed for the equipment when instrument calibration warning checkbox checked");
		SoftAssert sa = new SoftAssert();
		
		if(preferencesPage.IsInstCal_checked()) {
			System.out.println("checked");
		}else {
			preferencesPage.clickon_IsInstCal();
			preferencesPage.click_SaveBtn();
			UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "NA");
			tu.click_OK_popup();
		}
		MainHubPage=preferencesPage.ClickBackButn();
		Thread.sleep(500);
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		Thread.sleep(500);
		NewEquipmentCreation_Page = EquipmentHubPage.ClickAddButton();
		//As instrumnt calibration is 12month we are creating equipment with due calibration date less than a year it is visible in equipment tile
		NewEquipmentCreation_Page.EqipCreation_WithoutClickingSaveBtn( "IRTD", "Asset","1234");
		NewEquipmentCreation_Page.selectReqDate("November","12","2024");
		NewEquipmentCreation_Page. select_LastDate();
		NewEquipmentCreation_Page.ClickSaveButton();
		UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "NA");
		EquipmentHubPage=NewEquipmentCreation_Page.ClickBackBtn();
		MainHubPage=EquipmentHubPage.ClickBackBtn();
		String actual= MainHubPage.EquipmentCntInEquipmentTileOfMainHubPage();


		sa.assertEquals(actual,"1","FAIL:Equipment count is null");

		sa.assertAll();
	}


//this case will fail in when ran in suite because we have already created an equipment in previous case but when ran individual it will pass
	//Preferences08-Verify if number of equipments which are in calibration due should not be displayed for the equipment when instrument calibration warning checkbox unchecked
	@Test(priority = 6,groups = { "Sanity",
	"Regression" }, description = "Preferences08-Verify if number of equipments which are in calibration due should not be displayed for the equipment when instrument calibration warning checkbox unchecked")

	public void ADP08() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences08-Verify if number of equipments which are in calibration due should not be displayed for the equipment when instrument calibration warning checkbox unchecked");
		SoftAssert sa = new SoftAssert();

		if(preferencesPage.IsInstCal_checked()) {
			preferencesPage.clickon_IsInstCal();
			preferencesPage.click_SaveBtn();
			UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "NA");
			tu.click_OK_popup();
			System.out.println("checked");
		}else {
			preferencesPage.clickon_IsInstCal();
			preferencesPage.click_SaveBtn();
			UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "NA");
			tu.click_OK_popup();
		}
		
		MainHubPage=preferencesPage.ClickBackButn();
		MainHubPage.UserSignOut();
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login("kiranc","Amphenol@123");
		//EquipmentHubPage = MainHubPage.ClickEquipmentTile();


		String actual= MainHubPage.EquipmentCntInEquipmentTileOfMainHubPage();

		sa.assertEquals(actual,"0","FAIL:Equipment count is Not null");

		sa.assertAll();
	}



	
	//Preferences09-Verify if Allow Editing of D Value in Lethality Equation field available in the Preferences screen
	@Test(priority = 8,groups = { "Sanity",
	"Regression" }, description = "Preferences09-Verify if Allow Editing of D Value in Lethality Equation field available in the Preferences screen")

	public void ADP09() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences09-Verify if Allow Editing of D Value in Lethality Equation field available in the Preferences screen");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(preferencesPage.IsAllowDValueEditing_Presence(),true,"FAIL:Allow Editing of D Value in Lethality Equation field is NOT available in the Preferences screen");

		sa.assertAll();
	}

	//Preferences10-Verify if Allow Editing of D Value in Lethality Equation field is unchecked by default in the Preferences screen
	@Test(groups = { "Sanity",
	"Regression" }, description = "Preferences10-Verify if Allow Editing of D Value in Lethality Equation field is unchecked by default in the Preferences screen")

	public void ADP10() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences10-Verify if Allow Editing of D Value in Lethality Equation field is unchecked by default in the Preferences screen");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(preferencesPage.IsDValueCheckBox_selected(),false,"FAIL:Allow Editing of D Value in Lethality Equation field is NOT unchecked by default in the Preferences screen");

		sa.assertAll();
	}



	/*CRT dependent
	//Preferences11-Verify if Customize option should be available in Edit Parameters screen if Allow Editing of D Value in Lethality Equation checkbox is checked

	@Test(groups = { "Sanity",
	"Regression" }, description = "Preferences11-Verify if Customize option should be available in Edit Parameters screen if Allow Editing of D Value in Lethality Equation checkbox is checked")

	public void ADP11() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences11-Verify if Customize option should be available in Edit Parameters screen if Allow Editing of D Value in Lethality Equation checkbox is checked");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals();

		sa.assertAll();
	}


	//Preferences12-Verify if Customize option should not be available in Edit Parameters screen if Allow Editing of D Value in Lethality Equation checkbox is unchecked
	@Test(groups = { "Sanity",
	"Regression" }, description = "Preferences12-Verify if Customize option should not be available in Edit Parameters screen if Allow Editing of D Value in Lethality Equation checkbox is unchecked")

	public void ADP12() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("/Preferences12-Verify if Customize option should not be available in Edit Parameters screen if Allow Editing of D Value in Lethality Equation checkbox is unchecked");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals();

		sa.assertAll();
	}*/

	
	//Preferences13-Verify if Report Footer field available in the Preferences screen
	@Test(priority = 9,groups = { "Sanity",
	"Regression" }, description = "Preferences13-Verify if Report Footer field available in the Preferences screen")

	public void ADP13() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences13-Verify if Report Footer field available in the Preferences screen");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(preferencesPage.IsPerformedByTextBlock_Presence(),true,"FAIL:Reportfooter performedby is not present");
		sa.assertEquals(preferencesPage.IsReviewedByTextBlock_Presence(),true,"FAIL:Reportfooter Reviewedby is not present");
		sa.assertEquals(preferencesPage.IsReportfooter_Presence(),true,"FAIL:Reportfooter is not present");

		sa.assertAll();
	}

	//Preferences14-Verify if First Page and Last Page fields is checked by default in the Preferences screen

	@Test(priority = 10,groups = { "Sanity",
	"Regression" }, description = "Preferences14-Verify if First Page and Last Page fields is checked by default in the Preferences screen")

	public void ADP14() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences14-Verify if First Page and Last Page fields is checked by default in the Preferences screen");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(preferencesPage.IsFooterFirstPageCheckBox_selected(),true,"FAIL:First Page and Last Page fields is NOT checked by default in the Preferences screen");
		sa.assertEquals(preferencesPage.IsFooterLastPageCheckBox_selected(),true,"FAIL:First Page and Last Page fields is NOT checked by default in the Preferences screen");
		
		sa.assertAll();
	}


	//CRT depedent
	/*
	@Test(groups = { "Sanity",
	"Regression" }, description = "Preferences15-Verify if user able to save the Preferences without selecting the First Page and Last Page fields")

	public void ADP15() throws InterruptedException, IOException {
		extentTest = extent
				.startTest("Preferences15-Verify if user able to save the Preferences without selecting the First Page and Last Page fields");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals();

		sa.assertAll();
	}

	//CRT depedent
		/*
		@Test(groups = { "Sanity",
		"Regression" }, description = "Preferences15-Verify if user able to save the Preferences without selecting the First Page and Last Page fields")

		public void ADP16() throws InterruptedException, IOException {
			extentTest = extent
					.startTest("Preferences15-Verify if user able to save the Preferences without selecting the First Page and Last Page fields");
			SoftAssert sa = new SoftAssert();

			sa.assertEquals();

			sa.assertAll();
		}*/



	
	//Policies
	@Test(priority = 15,groups = { "Sanity",
	"Regression" }, description = "AD_Policies03-Verify if Required minimum password length should not applicable when Active Directory Enabled")

	public void AD_Policies03() throws InterruptedException, IOException {
		extentTest = extent.startTest(
				"AD_Policies03-Verify if Required minimum password length should not applicable when Active Directory Enabled");
		SoftAssert sa = new SoftAssert();

		AD_UMPage=preferencesPage.click_UMtab();
		//tu.click_YesBtn_popup();
		AD_UMPage.selectListUser();

		sa.assertEquals(AD_UMPage.NamePresence(), false,
				"Fail: Namepresence check box is not enabled");

		sa.assertEquals(AD_UMPage.IDPresence(), false,
				"Fail: Namepresence check box is not enabled");

		sa.assertAll();

	}
}
