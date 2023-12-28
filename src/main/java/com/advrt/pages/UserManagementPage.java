/**
* @author ruchika.behura
*
*/

package com.advrt.pages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.advrt.pages.LoginPage;
import com.advrt.base.BaseClass;
import com.advrt.utility.TestUtilities;

public class UserManagementPage extends BaseClass {

	TestUtilities tu = new TestUtilities();
	// User Management element variable declaration definition
	WebElement UMHeaderText = null;
	WebElement PreferencesHeaderText = null;
	WebElement PoliciesHeaderText = null;
	WebElement NewUserUMBtn = null;
	WebElement UNUMField = null;
	WebElement UserIDUMField = null;
	WebElement PWUMField = null;
	WebElement ConPWUMField = null;
	WebElement TitleUMField = null;
	WebElement UserTypeUMDropDown = null;
	WebElement PhoneUMField = null;
	WebElement EmailUMField = null;
	WebElement DeleteUMBtn = null;
	WebElement UMAssetPriv = null;
	WebElement CreateSetupPriv = null;
	WebElement EditSetupPriv = null;
	WebElement DeleteSetup = null;
	WebElement RunVerification = null;
	WebElement SaveUMBtn = null;
	WebElement AdminUMPriv = null;
	WebElement AdminPolicyPriv = null;
	WebElement AdminPreferencePriv = null;
	WebElement AdminHWMaintenancePriv = null;
	WebElement CreateAssetPriv = null;
	WebElement EditAssetPriv = null;
	WebElement DeleteAssets = null;
	WebElement CreateEquipPriv = null;
	WebElement EditEquipPriv = null;
	WebElement DeleteEquipment = null;
	WebElement CreateReports = null;
	WebElement CreatePassFailTemplate = null;
	WebElement AuditTrail = null;
	WebElement RunQualification = null;
	WebElement DeleteStudyFiles = null;
	WebElement EditPassFailTemplate = null;
	WebElement RunCalibration = null;
	WebElement CopyFilesReports = null;
	WebElement ArchiveData = null;
	WebElement ManualSync = null;
	WebElement CameraAccess = null;
	WebElement DeletePassFailTemplate = null;
	WebElement ChangeConsoleTime = null;
	WebElement DisableCheckbox = null;
	WebElement UsersListButton = null;
	WebElement UMImgBtn = null;
	//List<WebElement> Combobx = null;

	private void initElements() {
		// UserManagement Page Element definition
		UMHeaderText = driver.findElementByName("User Management");
		PreferencesHeaderText = driver.findElementByAccessibilityId("PreferencesButton");
		PoliciesHeaderText = driver.findElementByAccessibilityId("PoliciesButton");
		NewUserUMBtn = driver.findElementByAccessibilityId("NewUserButton");
		UNUMField = driver.findElementByAccessibilityId("NameTextBox");
		UserIDUMField = driver.findElementByAccessibilityId("UserIDTextBox");
		PWUMField = driver.findElementByAccessibilityId("UMCPasswordTextBox");
		ConPWUMField = driver.findElementByAccessibilityId("ConfirmPasswordTextBox");
		TitleUMField = driver.findElementByAccessibilityId("EditableTextBox");
		UserTypeUMDropDown = driver.findElementByAccessibilityId("UserTypeComboBox");
		//Combobx = driver.findElementsByAccessibilityId("UserTypeComboBox");
		PhoneUMField = driver.findElementByAccessibilityId("PhoneTextBox");
		EmailUMField = driver.findElementByAccessibilityId("EmailTextBox");
		DeleteUMBtn = driver.findElementByName("Delete");
		UMImgBtn = driver.findElementByAccessibilityId("UserImage");
		SaveUMBtn = driver.findElementByAccessibilityId("SaveButton");
		//CancelUMBtn = driver.findElementByName("CancelButton");
		UsersListButton = driver.findElementByAccessibilityId("PrintUsersListButton");
		DisableCheckbox = driver.findElementByAccessibilityId("DisableUserCheckBox");

		// User Privilege element definition for Admin
		
		AdminUMPriv = driver.findElementByAccessibilityId("AdminCheckBox");
		AdminPolicyPriv = driver.findElementByAccessibilityId("PoliciesCheckBox");
		AdminPreferencePriv = driver.findElementByAccessibilityId("PreferencesCheckBox");
		AdminHWMaintenancePriv = driver.findElementByAccessibilityId("HardwareMaintenanceCheckBox");
		
		CreateAssetPriv = driver.findElementByAccessibilityId("AssetsPrivlegesCheckBox");
		EditAssetPriv = driver.findElementByAccessibilityId("ModifyAssetsPrivlegesCheckBox");
		
		CreateSetupPriv = driver.findElementByAccessibilityId("SetupCreationCheckBox");
		EditSetupPriv = driver.findElementByAccessibilityId("ModifySetupCheckBox");
		DeleteSetup = driver.findElementByAccessibilityId("SetupDeleteCheckBox");
		
		CreateEquipPriv = driver.findElementByAccessibilityId("EquipmentPrivlegesCheckBox");
		EditEquipPriv = driver.findElementByAccessibilityId("ModifyEquipmentPrivlegesCheckBox");
		DeleteEquipment = driver.findElementByAccessibilityId("EquipmentDeleteCheckBox");
		
		CreateReports = driver.findElementByAccessibilityId("CreateReportsCheckBox");
		CreatePassFailTemplate = driver.findElementByAccessibilityId("CreateTemplateCheckBox");
		AuditTrail = driver.findElementByAccessibilityId("AuditViewPrintCheckBox");
		
		RunQualification = driver.findElementByAccessibilityId("QualificationExecutionCheckBox");
		RunCalibration = driver.findElementByAccessibilityId("CalibrationExecutionCheckBox");
		RunVerification = driver.findElementByAccessibilityId("RunVerificationCheckBox");
		DeleteAssets = driver.findElementByAccessibilityId("AssetDeleteCheckBox");
		DeleteStudyFiles = driver.findElementByAccessibilityId("DeleteFilesReportsCheckBox");
		EditPassFailTemplate = driver.findElementByAccessibilityId("EditTemplateCheckBox");
		CopyFilesReports = driver.findElementByAccessibilityId("CopyFilesReportsCheckBox");
		ArchiveData = driver.findElementByAccessibilityId("ArchiveDataCheckBox");
		ManualSync = driver.findElementByAccessibilityId("ManualSyncCheckBox");
		CameraAccess = driver.findElementByAccessibilityId("CamerAccessCheckbox");
		DeletePassFailTemplate = driver.findElementByAccessibilityId("DeleteTemplateCheckBox");
		ChangeConsoleTime = driver.findElementByAccessibilityId("ChngConsoleTimeCheckBox");

	}

	UserManagementPage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		UMHeaderText = null;
		PreferencesHeaderText = null;
		PoliciesHeaderText = null;
		NewUserUMBtn = null;
		UNUMField = null;
		UserIDUMField = null;
		PWUMField = null;
		ConPWUMField = null;
		TitleUMField = null;
		UserTypeUMDropDown = null;
		PhoneUMField = null;
		EmailUMField = null;
		DeleteUMBtn = null;
		UMAssetPriv = null;
		CreateSetupPriv = null;
		EditSetupPriv = null;
		EditAssetPriv = null;
		RunCalibration = null;
		RunVerification = null;
		SaveUMBtn = null;
		AdminUMPriv = null;
		AdminPolicyPriv = null;
		AdminPreferencePriv = null;
		AdminHWMaintenancePriv = null;	
		CreateAssetPriv = null;
		EditAssetPriv = null;
		DeleteAssets = null;
		CreateEquipPriv = null;
		EditEquipPriv = null;
		CreateReports = null;
		CreatePassFailTemplate = null;
		AuditTrail = null;
		RunQualification = null;
		DeleteSetup = null;
		DeleteEquipment = null;
		DeleteStudyFiles = null;
		EditPassFailTemplate = null;
		CopyFilesReports = null;
		ArchiveData = null;
		ManualSync = null;
		CameraAccess = null;
		DeletePassFailTemplate = null;
		ChangeConsoleTime = null;
		DisableCheckbox = null;
		UsersListButton = null;
		UMImgBtn = null;
		//Combobx = null;
	}

	/*----------------------
	Methods of UserManagement Page
	------------------------*/
	// Check if UserManagement page is displayed
	public boolean IsUMscreenDisplayed() {
		return IsElementEnabledStatus(UMHeaderText);
	}
	
	public boolean IsNewUserBtnPresence() {
		return IsElementEnabledStatus(NewUserUMBtn);
	}

	// check if Save Button Enable
	public boolean IsSaveButtonEnable() {
		return IsElementEnabledStatus(SaveUMBtn);
	}
	
	
	// Click NewUser button
		public void ClickSaveUser() throws InterruptedException {
			Thread.sleep(1000);
			clickOn(SaveUMBtn);
					}


	// Click NewUser button
	public void ClickNewUser() throws InterruptedException {
		Thread.sleep(1000);
		clickOn(NewUserUMBtn);
		clickOn(NewUserUMBtn);
	}

	// Enter User Name text
	public void enterNewUserName(String NewUN) {
		ClearText(UNUMField);
		enterText(UNUMField, NewUN);
	}

	// Verify the User Name field presence...")
	public boolean UserNameFieldPresence() {
		return IsElementEnabledStatus(UNUMField);
	}

	// Verify the UserID Field presence...")
	public boolean UserIDFieldPresence() {
		return IsElementEnabledStatus(UserIDUMField);
	}

	// Verify the Password Field presence...")
	public boolean PassworFieldPresence() {
		return IsElementEnabledStatus(PWUMField);
	}

	// Verify the ConPassword Field presence...")
	public boolean ConPassworFieldPresence() {
		return IsElementEnabledStatus(ConPWUMField);
	}

	// Verify the Title Field presence...")
	public boolean TitleFieldPresence() {
		return IsElementEnabledStatus(TitleUMField);
	}

	// Verify the UserType Field presence...")
	public boolean UserTypeField_EnableState() {
		return IsElementEnabledStatus(UserTypeUMDropDown);
	}

	// Verify the Phone Field presence...")
	public boolean PhoneFieldPresence() {
		return IsElementEnabledStatus(PhoneUMField);
	}

	// Verify the Email Field presence...")
	public boolean EmailFieldPresence() {
		return IsElementEnabledStatus(EmailUMField);
	}

	// Fetch User Name text
	public String GetUserNametext() {
		return FetchText(UNUMField);
	}

	// Enter User ID text
	public void enterNewUserID(String NewUID) {
		ClearText(UserIDUMField);
		enterText(UserIDUMField, NewUID);
	}

	// Fetch User ID text
	public String GetUserIDtext() {
		return FetchText(UserIDUMField);
	}

	// Enter PW text
	public void enterNewUserPW(String NewPW) {
		ClearText(PWUMField);
		enterText(PWUMField, NewPW);
	}

	// fetch PW text
	public String get_PWField_text() {
		return FetchText(PWUMField);
	}

	// Enter ConfirmPW text
	public void enterNewUserConfPW(String NewCPW) {
		ClearText(ConPWUMField);
		enterText(ConPWUMField, NewCPW);
	}

	// Enter Title text
	public void enterNewUserTitle(String Title) throws InterruptedException {
		ClearText(TitleUMField);
		enterText(TitleUMField, Title);
		Thread.sleep(1000);
	}

	// Fetch Title text
	public String get_UserTitle() {
		return FetchText(TitleUMField);
	}

	// Enter Title text
	public void ClickTitlefield() {
		clickOn(TitleUMField);
	}

	// Fetch list of raw Asset location data as is viewed
	public String[] getUserTypelist() {
		clickOn(UserTypeUMDropDown);
		List<WebElement> Combobxlist = driver.findElementsByClassName("ComboBoxItem");

		String str[] = new String[Combobxlist.size()];

		for (int i = 0; i < Combobxlist.size(); i++) {
			str[i] = Combobxlist.get(i).getText();
		}

		//String[] Obtainedlist = removeDuplicateStringinArray(str, "Select");
		System.out.println(Arrays.toString(str));

		//clickOn(AssetModelTxtBox);
		System.out.println("---------");
		return str;
	}
	
	
	// Select UserType
	public void select_UserType(String Utype) throws InterruptedException {
		// System.out.println(Utype);
		clickOn(UserTypeUMDropDown);
		Thread.sleep(1000);
		WebElement UMAdministrator1 = driver.findElementByName("SystemAdministrator");
		WebElement UMSupervisor = driver.findElementByName("Supervisor");
		WebElement UMOperator = driver.findElementByName("Operator");
		WebElement UMSelect1 = driver.findElementByName("Select");

		if (Utype.equals(UMSelect1.getText())) {
			clickOn(UMSelect1);
			Thread.sleep(500);
		} else if (Utype.equals(UMAdministrator1.getText())) {
			// SelectAdministrator();
			clickOn(UMAdministrator1);
			// Thread.sleep(500);
		} else if (Utype.equals(UMSupervisor.getText())) {
			clickOn(UMSupervisor);
			Thread.sleep(500);
		} else if (Utype.equals(UMOperator.getText())) {
			clickOn(UMOperator);
			Thread.sleep(500);
		}

	}

	// Click the UserType Drop down List
	public void ClickUserTypeDropDown() throws InterruptedException {
		clickOn(UserTypeUMDropDown);
		Thread.sleep(500);
	}

	// Select User image
	public void selectUserImg(String imgName) throws AWTException, IOException, InterruptedException {
		clickOn(UMImgBtn);
		WebElement ImgBrowseBtn = driver.findElementByAccessibilityId("BrowseImage");
		clickOn(ImgBrowseBtn);
		Thread.sleep(1000);
		tu.uploadDoc(imgName);
	}

	// Select Sys Admin from the UserType drop-down list
	public void SelectAdministrator() {
		WebElement UMAdministrator = driver.findElementByName("SystemAdministrator");
		clickOn(UMAdministrator);
	}

	// Select Supervisor from the UserType drop-down list
	public void SelectSupervisor() {
		WebElement UMSupervisor = driver.findElementByName("Supervisor");
		clickOn(UMSupervisor);
	}

	// Select Operator from the UserType drop-down list
	public void SelectOperator() {
		WebElement UMOperator = driver.findElementByName("Operator");
		clickOn(UMOperator);
	}

	// Enter Phone text
	public void enterNewUserPhone(String Phone) {
		ClearText(PhoneUMField);
		enterText(PhoneUMField, Phone);
	}
	
	// Fetch Phone text
	public String get_UserPhoneTxt() {
		return FetchText(PhoneUMField);
	}

	// Click Phone Filed
	public void ClickPhone() {
		clickOn(PhoneUMField);
	}

	// Enter email text
	public void enterNewUserEmail(String email) {
		ClearText(EmailUMField);
		enterText(EmailUMField, email);
	}

	// Click Save button
	public void ClickNewUserSaveButton() throws InterruptedException {
		clickOn(SaveUMBtn);
	}

	// Click Back Button to move to AdminTile
	public MainHubPage ClickBackButn() throws IOException, InterruptedException {
		WebElement BackUMBtn = driver.findElementByAccessibilityId("BackButton");
		clickOn(BackUMBtn);
		Thread.sleep(1000);
		return new MainHubPage();
	}

	// Click Userlist window of UM Page
	public void click_UsrLstBx() throws IOException {
		WebElement usrListBox = driver.findElementByAccessibilityId("UsersListBox");
		clickOn(usrListBox);

	}

	// Select/Click any User in the UserList Panel
	public void clickAnyUserinUserList(String UN) throws InterruptedException {
		List<WebElement> Userslist = driver.findElementByAccessibilityId("UsersListBox")
				.findElements(By.className("ListBoxItem"));
		// System.out.println("Total Number of Users created: " + Userslist.size());
		Userslist.get(0).click();

		for (int i = 0; i < Userslist.size(); i++) {

			String UNtext1 = GetUserNametext();
			// System.out.println(UNtext1);
			if (UNtext1.equalsIgnoreCase(UN)) {
				clickOn(UNUMField);
				break;
			} else {
				Actions ac = new Actions(driver);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
			}
		}
	}
	
	// Verify if any User available in the UserList Panel
	public boolean check_UserPresenceInThe_UserList(String UN) throws InterruptedException {
		boolean flag = false;
		List<WebElement> Userslist = driver.findElementByAccessibilityId("UsersListBox")
				.findElements(By.className("ListBoxItem"));
		// System.out.println("Total Number of Users created: " + Userslist.size());
		if (Userslist.size()==0) {
			return flag;
		} else {
			Userslist.get(0).click();

			for (int i = 0; i < Userslist.size(); i++) {

				String UNtext1 = GetUserNametext();
				// System.out.println(UNtext1);
				if (UNtext1.equalsIgnoreCase(UN)) {
					clickOn(UNUMField);
					flag = true;
					break;
				} else {
					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				}
			}
			return flag;
		}
	}

	// Click On All Checked boxes to customized the privileges
	public void Click_AllCheckBox() throws InterruptedException, AWTException {
		//ClkscrollBar_up();
		clickOn(AdminUMPriv);
		clickOn(AdminPolicyPriv);
		clickOn(AdminPreferencePriv);
		clickOn(AdminHWMaintenancePriv);		
		clickOn(RunQualification);
		clickOn(RunCalibration);
		clickOn(RunVerification);
		clickOn(CreateAssetPriv);
		clickOn(EditAssetPriv);
		clickOn(DeleteAssets);
		clickOn(CopyFilesReports);
		clickOn(CreateSetupPriv);
		clickOn(EditSetupPriv);
		clickOn(DeleteSetup);
		clickOn(ArchiveData);
		clickOn(CreateEquipPriv);
		clickOn(EditEquipPriv);
		clickOn(DeleteEquipment);
		clickOn(ManualSync);
		clickOn(CreateReports);
		clickOn(DeleteStudyFiles);
		clickOn(CameraAccess);
		clickOn(CreatePassFailTemplate);
		clickOn(EditPassFailTemplate);
		clickOn(DeletePassFailTemplate);
		clickOn(AuditTrail);
		clickOn(ChangeConsoleTime);
		// Thread.sleep(1000);
	}

	// Scroll down and click on AuditTrail user privilege checkbox
	public void ClkAuditTrail() throws InterruptedException, AWTException {

		WebElement scrollbar = driver.findElementByAccessibilityId("VerticalSmallIncrease");
		clickOn(scrollbar);
		clickOn(scrollbar);
		clickOn(scrollbar);

		clickOn(AuditTrail);
		Thread.sleep(500);

	}

/*	// Scroll Up and click 3 times
	public void ClkscrollBar_up() throws InterruptedException, AWTException {

		WebElement scrollbar = driver.findElementByAccessibilityId("VerticalSmallDecrease");
		clickOn(scrollbar);
		clickOn(scrollbar);
		clickOn(scrollbar);
	}

	// Scroll down and click 3 times
	public void ClkscrollBar_down() throws InterruptedException, AWTException {

		WebElement scrollbar = driver.findElementByAccessibilityId("VerticalSmallIncrease");
		clickOn(scrollbar);
		clickOn(scrollbar);
		clickOn(scrollbar);
	}
	*/

	// Verify if Run Qual Privilege checkbox enabled or not
	public boolean PrivRunQual_Enablestatus() {
		return IsElementEnabledStatus(RunQualification);
	}
	
	// click/select Run Qual Privilege checkbox
	public void clickPrivRunQual() throws InterruptedException {
		clickOn(RunQualification);
		Thread.sleep(1000);
	}

	// Verify if Run Qual Privilege checked/selected or not
	public boolean PrivRunQualstatus() {
		return checkboxSelectStatus(RunQualification);
	}

	// check/select Run Cal Privilege checkbox
	public void clickPrivRunCal() throws InterruptedException {
		clickOn(RunCalibration);
		Thread.sleep(1000);
	}

	// Verify if Run Cal Privilege checked/selected or not
	public boolean PrivRunCalstatus() {
		return checkboxSelectStatus(RunCalibration);
	}
	
	// check/select Run Verification Privilege checkbox
	public void click_PrivRunVerf() throws InterruptedException {
		clickOn(RunVerification);
		Thread.sleep(1000);
	}
	
	// Verify if Run Verification Privilege checked/selected or not
	public boolean RunVerifcationstatus() {
		return checkboxSelectStatus(RunVerification);
	}

	// check/select Create Setup Privilege checkbox
	public void click_CreatSetupPriv() throws InterruptedException {
		clickOn(CreateSetupPriv);
		Thread.sleep(1000);
	}
	
	// Verify if Create Setup Privilege checked/selected or not
	public boolean Create_SetupPrivstatus() {
		return checkboxSelectStatus(CreateSetupPriv);
	}
		
	// check/select Edit Setup Privilege checkbox
	public void click_EditSetupPriv() throws InterruptedException {
		clickOn(EditSetupPriv);
		Thread.sleep(1000);
	}

	// Verify if Edit Setup Privilege checked/selected or not
	public boolean Edit_SetupPrivstatus() {
		return checkboxSelectStatus(EditSetupPriv);
	}

	// Fetch the Save Alert message
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	// Is alert message visible
	public boolean Is_alertvisible() throws InterruptedException {
		WebElement alertMsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return IsElementVisibleStatus(alertMsg);
	}

	// Close alert message if visible
	public void click_Close_alertmsg() throws InterruptedException {
		if (!IsElementVisibleStatus(driver.findElementByAccessibilityId("displayMessageTextBlock"))) {
			System.out.println("Buttom Appbar Alert message not displayed");
		} else {
			WebElement alertMsg_CloseBtn = driver.findElementByAccessibilityId("btnDelete");
			clickOn(alertMsg_CloseBtn);
		}
	}

	// Login Popup presence
	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}

	// Click on the User Image Tile Button
	public void click_UserImageTile() throws InterruptedException {		
		clickOn(UMImgBtn);
		// Thread.sleep(1000);
	}

	// Click the Browse button under User Image tile
	public void click_UploadBrowseBtn() throws InterruptedException {
		WebElement BrowseBtn = driver.findElementByAccessibilityId("BrowseButton");
		clickOn(BrowseBtn);
		Thread.sleep(1000);
	}

	// Click the Camera Icon under User Image tile
	public void click_CameraIcon() throws InterruptedException {
		WebElement CameraIcon = driver.findElementByAccessibilityId("CameraImage");
		clickOn(CameraIcon);
		// Thread.sleep(2000);
	}
	
	//Save the image clicked
	public void Capture_Img(String Img_Name) throws IOException {		
		tu.capture_element_screenshot(driver, UMImgBtn, "TestData", Img_Name);

	}

	// Camera On Header Title is Visible ...
	public boolean IsCameracloseBtn_Enable() {
		WebElement CameraClose = driver.findElementByName("Close Camera");
		return IsElementEnabledStatus(CameraClose);
	}	

	// check/select Disable User CheckBox
	public void Select_DisableUserCheckBox() throws InterruptedException {
		clickOn(DisableCheckbox);
	}

	// Get DisableUserCheckBox enable status 
	public boolean DisableUserCheckBox_Enablestate() {
		return IsElementEnabledStatus(DisableCheckbox);
	}
	
	// Check if DisableUserCheckBox checked or not
	public boolean DisableUserCheckBox_IsChecked() {
		String checkUncheckTxt = DisableCheckbox.getAttribute("Toggle.ToggleState");
		//System.out.println(checkUncheckTxt);
		boolean flag;
		
		if (checkUncheckTxt.equalsIgnoreCase("1")) {
			flag = true;
		} else {
			flag = false;
		}
		 return flag;
	}

	// Does it display the Save Alert message if a user disable his own account
	public boolean DisableAlertMsgVisible() throws InterruptedException {
		WebElement Dmsg = driver.findElementByName("Sorry, you cannot delete or disable the Logged in User Account");
		return IsElementVisibleStatus(Dmsg);
	}

	// Click on ok button in Alert message box if a user disable his own account
	public void click_okBtn() {
		WebElement ok = driver.findElementByAccessibilityId("Button0");
		clickOn(ok);
	}

	// Verify if Admin User Management Privilege checked/selected or not
	public boolean AdminUMstatus() {
		return checkboxSelectStatus(AdminUMPriv);
	}
	
	// Verify if AdminPolicyPriv Privilege checked/selected or not
	public boolean AdminPolicystatus() {
		return checkboxSelectStatus(AdminPolicyPriv);
	}
	
	// Verify if AdminPreferencePriv Privilege checked/selected or not
	public boolean AdminsPreferencetatus() {
		return checkboxSelectStatus(AdminPreferencePriv);
	}
	
	// Verify if AdminHWMaintenance Privilege checked/selected or not
	public boolean AdminHWMaintenancestatus() {
		return checkboxSelectStatus(AdminHWMaintenancePriv);
	}

	// Verify if Create Equipment Privilege checked/selected or not
	public boolean Create_Equipmentstatus() {
		return checkboxSelectStatus(CreateEquipPriv);
	}
	
	// Verify if Edit Equipment Privilege checked/selected or not
	public boolean Edit_Equipmentstatus() {
		return checkboxSelectStatus(EditEquipPriv);
	}

	// Verify if Create Reports Privilege checked/selected or not
	public boolean CreateReportsstatus() {
		return checkboxSelectStatus(CreateReports);
	}

	// Click on CreateReports
	public void CreateReports() {
		clickOn(CreateReports);
	}

	// Verify if Create Pass/Fail template Privilege checked/selected or not
	public boolean CreatePassFailtemplatestatus() {
		return checkboxSelectStatus(CreatePassFailTemplate);
	}

	// Verify if Audit trail template Privilege checked/selected or not
	public boolean Audittrailstatus() {
		return checkboxSelectStatus(AuditTrail);
	}

	// Verify if Delete Assets Privilege checked/selected or not
	public boolean DeleteAssetsstatus() {
		return checkboxSelectStatus(DeleteAssets);
	}

	// Verify if Delete Setup Privilege checked/selected or not
	public boolean DeleteSetupstatus() {
		return checkboxSelectStatus(DeleteSetup);
	}

	// Verify if Delete Equipment Privilege checked/selected or not
	public boolean DeleteEquipmentstatus() {
		return checkboxSelectStatus(DeleteEquipment);
	}

	// Verify if Delete Study Files/Reports Privilege checked/selected or not
	public boolean DeleteStudyFilesReportsstatus() {
		return checkboxSelectStatus(DeleteStudyFiles);
	}

	// Verify if Edit Pass/Fail template Privilege checked/selected or not
	public boolean EditPassFailtemplatestatus() {
		return checkboxSelectStatus(EditPassFailTemplate);
	}

	// Verify if Copy Files/Reports Privilege checked/selected or not
	public boolean CopyFilesReportsstatus() {
		return checkboxSelectStatus(CopyFilesReports);
	}

	// Verify if Archive data Privilege checked/selected or not
	public boolean Archivedatastatus() {
		return checkboxSelectStatus(ArchiveData);
	}

	// Verify if Camera Access Privilege checked/selected or not
	public boolean CameraAccessPrivstatus() {
		return checkboxSelectStatus(CameraAccess);
	}

	// Verify if Manual Sync Privilege checked/selected or not
	public boolean ManualSyncstatus() {
		return checkboxSelectStatus(ManualSync);
	}

	// Verify if Delete pass/fail template Privilege checked/selected or not
	public boolean Deletepassfailtemplatestatus() {
		return checkboxSelectStatus(DeletePassFailTemplate);
	}

	// Verify if Edit Asset Privilege checked/selected or not
	public boolean Create_AssetPrivstatus() {
		return checkboxSelectStatus(CreateAssetPriv);
	}
	
	// Verify if Edit Asset Privilege checked/selected or not
	public boolean Edit_AssetPrivstatus() {
		return checkboxSelectStatus(EditAssetPriv);
	}

	// Click on the Create Asset Privilege Check Box
	public void Click_Create_AssetCheckBox() {
		clickOn(CreateAssetPriv);
	}
	
	// Click on the Edit Asset Privilege Check Box
	public void Click_EditAssetCheckBox() {
		clickOn(EditAssetPriv);
	}

	// Verify if Change Console Time checked/selected or not
	public boolean ChangeConsoleTime_Priv_status() {
		return checkboxSelectStatus(ChangeConsoleTime);
	}
	
	// Click UsersListButton button
	public void ClickUsersListButton() {
		clickOn(UsersListButton);
	}

	// To open the user list pop up presence
	public boolean UserListOpenPopupvisible() throws InterruptedException {
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		WebElement UserlistopenPopup = driver.findElementByAccessibilityId("HeadText");
		return IsElementVisibleStatus(UserlistopenPopup);
	}

	// Click on Delete button
	public void ClickDeletebtn() {
		clickOn(DeleteUMBtn);
	}

	// confirmation pop-up should be displayed for Delete user
	public boolean Delete_confirmationPopupvisible() throws InterruptedException {
		WebElement DeletePopup = driver.findElementByAccessibilityId("Popup Window");
		return IsElementVisibleStatus(DeletePopup);
	}

	// Click on the Search box and enter valid user name
	public void EnterdatainSearchBox(String entrUN) throws InterruptedException {
		WebElement SrchBox = driver.findElementByAccessibilityId("SearchTextBox");
		clickOn(SrchBox);
		enterText(SrchBox, entrUN);
	}

	// Click cancel button
	public void ClickCancelBtn() {
		WebElement	CancelUMBtn = driver.findElementByAccessibilityId("CancelButton");
		clickOn(CancelUMBtn);
	}

	public boolean Iscancelvisible() throws InterruptedException {
		WebElement	CancelUMBtn = driver.findElementByName("CancelButton");
		return IsElementVisibleStatus(CancelUMBtn);
	}

	// Create First User of the System
	// Create First User of the System
		public LoginPage FirstUserCreation(String NewUN, String NewUID, String NewPW, String NewCPW, String Title,
				String Phone, String email) throws InterruptedException, IOException {
			ClickNewUser();
			enterNewUserName(NewUN);
			enterNewUserID(NewUID);
			enterNewUserPW(NewPW);
			enterNewUserConfPW(NewCPW);
			enterNewUserTitle(Title);
			ClickUserTypeDropDown();
			SelectAdministrator();
			enterNewUserPhone(Phone);
			enterNewUserEmail(email);
			ClickNewUserSaveButton();
			Thread.sleep(1500);
			tu.click_OK_popup();
			return new LoginPage();
		}

	// Create a New Admin User
	public void CreateAdminUser(String UID, String PW, String NewUN, String NewUID, String NewPW, String Title,
			String Phone, String email) throws InterruptedException {
		ClickNewUser();
		enterNewUserName(NewUN);
		enterNewUserID(NewUID);
		enterNewUserPW(NewPW);
		enterNewUserConfPW(NewPW);
		enterNewUserTitle(Title);
		ClickUserTypeDropDown();
		SelectAdministrator();
		enterNewUserPhone(Phone);
		enterNewUserEmail(email);
		ClickNewUserSaveButton();
		UserLoginPopup(UID, PW);
		Thread.sleep(2000);
		tu.click_OK_popup();
	}

	// Create a New Supervisor User
	public void CreateSupervisorUser(String UID, String PW, String NewUN, String NewUID, String NewPW, String Title,
			String Phone, String email) throws InterruptedException {
		ClickNewUser();
		enterNewUserName(NewUN);
		enterNewUserID(NewUID);
		enterNewUserPW(NewPW);
		enterNewUserConfPW(NewPW);
		enterNewUserTitle(Title);
		ClickUserTypeDropDown();
		SelectSupervisor();
		enterNewUserPhone(Phone);
		enterNewUserEmail(email);
		ClickNewUserSaveButton();

		UserLoginPopup(UID, PW);
		Thread.sleep(1000);
		tu.click_OK_popup();
	}

	// Create a New Operator User
	public void CreateOperatorUser(String UID, String PW, String NewUN, String NewUID, String NewPW, String Title,
			String Phone, String email) throws InterruptedException {
		ClickNewUser();
		enterNewUserName(NewUN);
		enterNewUserID(NewUID);
		enterNewUserPW(NewPW);
		enterNewUserConfPW(NewPW);
		enterNewUserTitle(Title);
		ClickUserTypeDropDown();
		SelectOperator();
		enterNewUserPhone(Phone);
		enterNewUserEmail(email);
		ClickNewUserSaveButton();

		UserLoginPopup(UID, PW);
		tu.click_OK_popup();
	}

	// User Management Creation with Mandatory fields
	public void UMCreation_MandatoryFields(String UName, String UID, String Pwd, String Titl, String Utype)
			throws InterruptedException {
		enterNewUserName(UName);
		enterNewUserID(UID);
		enterNewUserPW(Pwd);
		enterNewUserConfPW(Pwd);
		enterNewUserTitle(Titl);
		select_UserType(Utype);
		Thread.sleep(1000);
		ClickNewUserSaveButton();
	}

	// Checking Admin Privileges Without saving the data
	public void UMPrivilages(String UName, String UID, String Pwd, String Cpwd, String Titl, String Utype)
			throws InterruptedException {
		enterNewUserName(UName);
		enterNewUserID(UID);
		enterNewUserPW(Pwd);
		enterNewUserConfPW(Cpwd);
		enterNewUserTitle(Titl);
		select_UserType(Utype);
		Thread.sleep(1000);
	}

	// User Management Creation with Non Mandatory fields
	public void UMCreation_NonmandatoryFields(String UName, String UID, String Pwd, String Titl,
			String Utype, String phno, String Emil) throws InterruptedException {
		enterNewUserName(UName);
		enterNewUserID(UID);
		enterNewUserPW(Pwd);
		enterNewUserConfPW(Pwd);
		enterNewUserTitle(Titl);
		select_UserType(Utype);
		enterNewUserPhone(phno);
		enterNewUserEmail(Emil);
		Thread.sleep(1000);
		ClickNewUserSaveButton();
	}

	// Checking save button is Disable before entering password field in
	// UserManagement Creation screen
	public void UM_SaveBtnVerification(String UName, String UID, String pwd, String Cpwd, String Titl, String Utype)
			throws InterruptedException {
		enterNewUserName(UName);
		enterNewUserID(UID);
		enterNewUserPW(pwd);
		enterNewUserConfPW(Cpwd);
		enterNewUserTitle(Titl);
		select_UserType(Utype);
		//ClickNewUserSaveButton();

	}

	// Click on PoliciesHeaderText
	public PoliciesPage Click_Policy() throws IOException {
		clickOn(PoliciesHeaderText);
		return new PoliciesPage();
	}

	// Click on PoliciesHeaderText
	public PreferencesPage Click_PreferenceTab() throws IOException {
		clickOn(PreferencesHeaderText);
		return new PreferencesPage();
	}

	// User Management Creation with ALL fields
	public void UMCreation_AllFields(String UName, String UID, String Pwd, String Title, String Utype, String phno,
			String Email, String ImageName) throws InterruptedException, AWTException, IOException {
		enterNewUserName(UName);
		enterNewUserID(UID);
		enterNewUserPW(Pwd);
		enterNewUserConfPW(Pwd);
		enterNewUserTitle(Title);
		select_UserType(Utype);
		enterNewUserPhone(phno);
		enterNewUserEmail(Email);
		// selectUserImg(ImageName);
		Thread.sleep(500);
		ClickNewUserSaveButton();
	}

}
