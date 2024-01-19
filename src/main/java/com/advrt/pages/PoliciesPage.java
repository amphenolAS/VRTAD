/**
* @author ruchika.behura
*
*/

package com.advrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.advrt.pages.AD_UMPage;
import com.advrt.base.BaseClass;

public class PoliciesPage extends BaseClass {
	// Page element variable declaration definition
	WebElement UMHeaderText = null;
	WebElement PoliciesHeaderText = null;
	WebElement PreferencesHeaderText = null;
	WebElement HWMaintenanceHeaderText = null;
	WebElement pwdcombobox = null;
	WebElement SaveBtn = null;
	WebElement Passwords = null;
	WebElement UserManagement_TAB = null;
	WebElement ExpirePasswordComboBox = null;
	WebElement LoginFailuresComboBox = null;
	WebElement DisplayUserIdEntryCheckBox = null;
	WebElement DisablePasswordSystemCheckBox = null;
	WebElement PasswordAplhaNumericSystemCheckBox = null;
	WebElement InstrumentCalibWarningCheckBox = null;
	WebElement AutoSyncOutCheckBox = null;

	WebElement pwdcheckbox = null;
	WebElement ExpirePasswordCheckBox = null;
	WebElement DisableUserafterAttemptsCheckBox = null;
	WebElement Preferences_TAB = null;
	WebElement InstrumentCalibWarningComboBox = null;
	WebElement CancelButton = null;
	WebElement ActiveDirectoryUser_btn = null;
	WebElement AllowGuestloginCheckBox=null;
	WebElement GuestUserTypeComboBox=null;
	

	// Page element Initialize method
	private void initElements() {
		UMHeaderText = driver.findElementByName("User Management");
		PoliciesHeaderText = driver.findElementByAccessibilityId("PoliciesButton");
		PreferencesHeaderText = driver.findElementByAccessibilityId("PreferencesButton");
		HWMaintenanceHeaderText = driver.findElementByAccessibilityId("HardwareMaintButton");
		pwdcombobox = driver.findElementByAccessibilityId("RequireMinLengthPasswordComboBox");
		pwdcheckbox = driver.findElementByAccessibilityId("RequireMinLengthPasswordCheckBox");
		// ExpirePasswordComboBox =
		// driver.findElementByAccessibilityId("ExpirePasswordComboBox_A_ID2");
		ExpirePasswordCheckBox = driver.findElementByAccessibilityId("ExpirePasswordCheckBox");
		SaveBtn = driver.findElementByAccessibilityId("SaveButton");
		// Passwords = driver.findElementByName("Passwords");
		UserManagement_TAB = driver.findElementByAccessibilityId("UserManagementButton");
		Preferences_TAB = driver.findElementByAccessibilityId("PreferencesButton");
		LoginFailuresComboBox = driver.findElementByAccessibilityId("LoginFailuresComboBox");
		DisplayUserIdEntryCheckBox = driver.findElementByName("Display user id during entry");
		// DisablePasswordSystemCheckBox =
		// driver.findElementByAccessibilityId("DisablePasswordSystemCheckBox");
		PasswordAplhaNumericSystemCheckBox = driver.findElementByAccessibilityId("PasswordAplhaNumericSystemCheckBox");

		// InstrumentCalibWarningCheckBox =
		// driver.findElementByAccessibilityId("InstrumentCalibWarningCheckBox");
		AutoSyncOutCheckBox = driver.findElementByAccessibilityId("AutoSyncOutCheckBox");

		DisableUserafterAttemptsCheckBox = driver.findElementByAccessibilityId("DisableUserafterAttemptsCheckBox");
		// InstrumentCalibWarningComboBox =
		// driver.findElementByAccessibilityId("InstrumentCalibWarning_A_ID3");
		CancelButton = driver.findElementByAccessibilityId("CancelButton");
		ActiveDirectoryUser_btn = driver.findElementByAccessibilityId("tgbLDAPUser");
		AllowGuestloginCheckBox=driver.findElementByAccessibilityId("AllowGuestloginCheckBox");
		GuestUserTypeComboBox=driver.findElementByAccessibilityId("UserTypeComboBox");

	}

	// Constructor for initializing the page elements
	PoliciesPage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		UMHeaderText = null;
		PoliciesHeaderText = null;
		PreferencesHeaderText = null;
		pwdcheckbox = null;
		ExpirePasswordCheckBox = null;
		DisableUserafterAttemptsCheckBox = null;
		Preferences_TAB = null;
		InstrumentCalibWarningComboBox = null;
		CancelButton = null;
		Passwords = null;
		UserManagement_TAB = null;
		ActiveDirectoryUser_btn = null;
	 AllowGuestloginCheckBox=null;
	GuestUserTypeComboBox=null;
	}

	/*----------------------
	Methods of Policies Page
	------------------------*/

	// Check if UserManagement header tab is displayed & enabled
	public boolean IsUMHeaderTabEnabled() {
		return IsElementEnabledStatus(UMHeaderText);
	}

	// Check if Policies page is displayed
	public boolean IsPolicies_screenDisplayed() {
		return IsElementEnabledStatus(PoliciesHeaderText);
	}

	// Check if Preferences tab is Enabled
	public boolean IsPreferenceTab_Enabled() {
		return IsElementEnabledStatus(PreferencesHeaderText);
	}

	// Check if Preferences tab is Enabled
	public boolean IsHWMaintenanceTab_Enabled() {
		return IsElementEnabledStatus(HWMaintenanceHeaderText);
	}

	// Navigate to UM page
	public UserManagementPage click_UMHeader() throws IOException {
		clickOn(UserManagement_TAB);
		return new UserManagementPage();
	}
	
	// Navigate to UM page
		public UserManagementPage_Manual click_UMHeader1() throws IOException {
			clickOn(UserManagement_TAB);
			return new UserManagementPage_Manual();
		}

	// click on clickOn(pwdcombobox);
	public void PWDLengthBox_Click() throws InterruptedException {
		clickOn(pwdcombobox);
	}

	// FetchText from pwd char combobox
	public String pwdLengthcombobox_text() {
		return FetchText(pwdcombobox.findElement(By.className("TextBlock")));
	}

	// click on InstrumentCalibWarningComboBox
	public void Click_InstrumentCalibWarningComboBox() throws InterruptedException {
		clickOn(InstrumentCalibWarningComboBox);
	}

	// fetch text from InstrumentCalibWarningComboBox
	public String InstrumentCalibWarningComboBox_text() {
		return FetchText(InstrumentCalibWarningComboBox);

	}

	public void ICW_9Months() throws InterruptedException {
		Click_InstrumentCalibWarningComboBox();
		WebElement option1 = driver.findElementByName("9 Months");
		clickOn(option1);
	}

	// Select any minimum length pwd
	public void SelectAny_Option_FromPWDLengthBox(String CharLength) throws InterruptedException {
		String ch1 = pwdLengthcombobox_text();
		// System.out.println(ch1);
		String SPart = ch1.split(" ")[0];
		// System.out.println(SPart);

		PWDLengthBox_Click();
		PWDLengthBox_Click();
		Actions ac = new Actions(driver);

		if (CharLength.equals("6")) {
			Thread.sleep(1000);
			ClickSaveButton();
		} else {
			for (int i = 0; i <= 11; i++) {
				ac.sendKeys(Keys.ARROW_DOWN).build().perform();
				Thread.sleep(1000);
				String ch2 = pwdLengthcombobox_text().split(" ")[0];
				// System.out.println(ch2);
				if (ch2.equals(CharLength)) {
					Thread.sleep(1000);
					break;
				}
			}

			ClickSaveButton();
		}

	}

	// Is password combobox visible
	public boolean IspwdcomboboxVisible() {
		return IsElementEnabledStatus(pwdcombobox);
	}

	// Is LoginFailuresComboBox enable
	public boolean IsLoginFailuresComboBox_Enable() {
		return IsElementEnabledStatus(LoginFailuresComboBox);
	}

	// DisplayUserIdEntryCheckBox
	public boolean IsUserIdEntryCheckBoxVisible() {
		return IsElementEnabledStatus(DisplayUserIdEntryCheckBox);
	}

	public boolean IsUserIdEntryCheckBox_Enabled() {
		return checkboxSelectStatus(DisplayUserIdEntryCheckBox);//DisplayUserIdEntryCheckBox
	}

//	public void UserIdEntryCheckBox_Status() throws InterruptedException {
//		if (checkboxSelectStatus(DisplayUserIdEntryCheckBox) == true) {
//			System.out.println("DisplayUserIdEntryCheckBox is selected");
//		} else {
//			clickOn(AllowDValueEditingCheckBox);
//			ClickSaveButton();
//		}
//	}

	// DisablePasswordSystemCheckBox
	public boolean IsPwdsystemcheckBoxVisible() {
		return IsElementEnabledStatus(DisablePasswordSystemCheckBox);
	}

	// PasswordAplhaNumericSystemCheckBox
	public boolean IsSpecialCharCheckBoxVisible() {

		return IsElementEnabledStatus(PasswordAplhaNumericSystemCheckBox);
	}

	// click PasswordAplhaNumericSystemCheckBox
	public void click_RequireSpecialCharacters() {
		clickOn(PasswordAplhaNumericSystemCheckBox);
	}

	// InstrumentCalibWarningCheckBox
	public boolean IsInstrumentCalibWarningCheckBoxVisible() {
		return IsElementEnabledStatus(InstrumentCalibWarningCheckBox);
	}

	// ExpirePasswordComboBox is visible
	public boolean IsExpirePasswordComboBoxVisible() {
		return IsElementEnabledStatus(ExpirePasswordComboBox);
	}

	// AutoSyncOutCheckBox
	public boolean IsAutoSyncOutCheckBoxVisible() {
		return IsElementEnabledStatus(AutoSyncOutCheckBox);
	}

	public void EnterUID(String UN, String PW) {
		WebElement LgInUID = driver.findElementByAccessibilityId("UserIdTextBox");
		WebElement LgInPW = driver.findElementByAccessibilityId("PasswordTextBox");
		LgInUID.sendKeys(UN);
		LgInPW.sendKeys(PW);
	}

	// Click Save button
	public void ClickSaveButton() throws InterruptedException {
		clickOn(SaveBtn);
		clickOn(SaveBtn);
	}

	// Click Cancel Button
	public void ClickCancelButton() throws InterruptedException {
		Thread.sleep(1000);
		clickOn(CancelButton);
	}
	
	
	// Click Cancel Button
		public void Click_ADCancelButton() throws InterruptedException {
			WebElement cnclBtn=driver.findElementByAccessibilityId("btnCancel");
			Thread.sleep(1000);
			clickOn(cnclBtn);
		}

	// Click on um tab
	public UserManagementPage ClickUserManagement_TAB() throws InterruptedException, IOException {
		clickOn(UserManagement_TAB);
		return new UserManagementPage();
	}

	// Click on preference tab
	public PreferencesPage Clickpreference_TAB() throws InterruptedException, IOException {
		clickOn(Preferences_TAB);
		return new PreferencesPage();
	}

	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}
	
	
	public boolean ADUserLoginPopupVisible() throws InterruptedException {
		
		
		boolean status = false;
		try
		{
			WebElement LgInPopup = driver.findElementByAccessibilityId("LDAPCredentialTextBlock");
			if(IsElementVisibleStatus(LgInPopup))
			{
				status = true;
			}
		}
		catch (Exception e) {
			e.getMessage();
		}
		
		return status;
	}

	// click on BackButton
	public MainHubPage click_BackBtn() throws InterruptedException, IOException {
		WebElement BackButton = driver.findElementByAccessibilityId("BackButton");
		clickOn(BackButton);
		Thread.sleep(1000);
		return new MainHubPage();
	}

	// click on pwdcombobox for check/Uncheck combo box
	public void clickOn_PWDcheckbox() throws InterruptedException {
		clickOn(pwdcheckbox);
		Thread.sleep(1000);
		ClickSaveButton();
	}

	public void click_PWDcheckbox() throws InterruptedException {
		clickOn(pwdcheckbox);
	}

	public void Enable_Editing_PWDcheckbox() throws InterruptedException {
		if (checkboxSelectStatus(pwdcheckbox) == true) {
			System.out.println("Min Length Password CheckBox is selected");
		} else {
			clickOn(pwdcheckbox);
			ClickSaveButton();
		}
	}

	public void Select_PWDcheckbox() throws InterruptedException {
		clickOn(pwdcheckbox);

	}

//Get text of the Delete Alert message
	public String get_text_from_Alertpopup() {
		WebElement deleteAsset_popup = driver.findElementByAccessibilityId("Content_String");
		return deleteAsset_popup.getAttribute("Name");
	}

//Fetch the  Alert message
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

//fetch text
	public String fetch_expirepwd_Defaulttext() {
		return ExpirePasswordComboBox.getText();
	}

	public void SelectAny_Value_fromExpirePwd_DD(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementByAccessibilityId("ExpirePasswordComboBox_A_ID2")
				.findElements(By.className("ComboBoxItem"));
		options.get(index).click();
		Thread.sleep(1000);
		ClickSaveButton();
	}

	public String FetchTxt_from_ExpirePwdDD(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementByAccessibilityId("ExpirePasswordComboBox_A_ID2")
				.findElements(By.className("ComboBoxItem"));

		return FetchText(options.get(index));
	}

//click On ExpirePasswordCheckBox
	public void click_ExpirePasswordCheckBox() throws InterruptedException {
		clickOn(ExpirePasswordCheckBox);
	}

	// DisableUserafterAttemptsCheckBox
	public void click_DisableUserafterAttemptsCheckBox() throws InterruptedException {

		clickOn(DisableUserafterAttemptsCheckBox);
	}

	//
	public void Select_From_LoginFailuresDD(int index) throws InterruptedException {

		clickOn(LoginFailuresComboBox);
		List<WebElement> options = driver.findElementByAccessibilityId("LoginFailuresComboBox")
				.findElements(By.className("ComboBoxItem"));
		options.get(index).click();
		Thread.sleep(1000);
		ClickSaveButton();
	}

	// Verify the Asset ID Field is enable or not

	public String UseID_Masked() {
		WebElement Idfield = driver.findElementByAccessibilityId("UserIdAsetrik");
		return Idfield.getAttribute("Value.Value");

	}

	public String Fetch_UseID() {
		WebElement Idfield = driver.findElementByAccessibilityId("UserIdTextBox");
		return Idfield.getAttribute("Value.Value");

	}

	// click on DisplayUserIdEntryCheckBox

	public void click_DisplayUserIdEntryCheckBox() {
		clickOn(DisplayUserIdEntryCheckBox);
	}

	// click on DisablePasswordSystemCheckBox

	public void click_DisablePasswordSystemCheckBox() {
		clickOn(DisablePasswordSystemCheckBox);
	}

	// click on yes or No btn in the alert message
	public LoginPage alertOptionYES() throws IOException {

		WebElement Yes_Alert_btn = driver.findElementByName("Yes");
		clickOn(Yes_Alert_btn);
		return new LoginPage();
	}

	public void clickalertYes() throws IOException {
		WebElement Yes_Alert_btn = driver.findElementByName("Yes");
		clickOn(Yes_Alert_btn);

	}

	// click on ExpirePasswordComboBox

	public void click_ExpirePasswordComboBox() {
		clickOn(ExpirePasswordComboBox);

	}

	// Select Expire Password
	public void selectExpirePassword(String pw) throws InterruptedException {
		// click_ExpirePasswordComboBox();
		String pw1 = fetch_expirepwd_Defaulttext();
		String SPart = pw1.split(" ")[0];
		int val = Integer.parseInt(SPart);
		// System.out.println("ActPW: "+val);

		// String firstPart = pw.split(" ")[0];
		int val1 = Integer.parseInt(pw);
		// System.out.println("SetPW: "+val1);

		click_ExpirePasswordComboBox();
		click_ExpirePasswordComboBox();
		Actions ac = new Actions(driver);

		if (val1 < val) {
			for (int i = 0; i <= val; i++) {
				ac.sendKeys(Keys.ARROW_UP).build().perform();
				if (fetch_expirepwd_Defaulttext().split(" ")[0].equals(pw)) {
					Thread.sleep(1000);
					break;
				}
			}

		} else if (val1 > val) {
			for (int i = 0; i <= (368 - val); i++) {
				ac.sendKeys(Keys.ARROW_DOWN).build().perform();
				if (fetch_expirepwd_Defaulttext().split(" ")[0].equals(pw)) {
					Thread.sleep(1000);
					break;
				}
			}

		}
	}

	// Enable the Instrument Calibration warning by clicking on
	// Click_InstrumentCalibWarningCheckBox
	public void Enable_InstrumentCalibWarningCheckBox() throws InterruptedException {
		String InstruCalCheckBx_State = InstrumentCalibWarningCheckBox.getAttribute("Toggle.ToggleState");
		// System.out.println(InstruCalCheckBx_State);
		// For enabling the Instru. Cal warning feature enter Yes as parameter
		if (InstruCalCheckBx_State.equals("0")) {
			clickOn(InstrumentCalibWarningCheckBox);
			ClickSaveButton();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		}

	}

	// Disable the Instrument Calibration warning by clicking on
	// Click_InstrumentCalibWarningCheckBox
	public void Disable_InstrumentCalibWarningCheckBox() throws InterruptedException {
		String InstruCalCheckBx_State = InstrumentCalibWarningCheckBox.getAttribute("Toggle.ToggleState");
		// System.out.println(InstruCalCheckBx_State);
		// For enabling the Instru. Cal warning feature enter Yes as parameter
		if (InstruCalCheckBx_State.equals("1")) {
			clickOn(InstrumentCalibWarningCheckBox);
			ClickSaveButton();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		}

	}

	// Is Active Directory User button is available in the Policies screen

	public boolean Is_ActiveDirectoryUserbutton_Displayed() {
		return IsElementEnabledStatus(ActiveDirectoryUser_btn);
	}

	// click on the Active Directory User button

	public void Click_ActiveDirectoryUserbutton_Btn() {
		clickOn(ActiveDirectoryUser_btn);
	}

	// Is ActiveDirectory LDAPLoginPopup is available in the Policies screen

	public boolean Is_LDAPLoginPopup_Displayed() {
		WebElement LDAPLoginPopup = driver.findElementByAccessibilityId("LDAPLoginPopup");
		return IsElementEnabledStatus(LDAPLoginPopup);
	}
	// LDAPLoginPopup

	
	//LDAPCloseButton
	
	
	public void Click_LDAPCloseButton_Btn() throws InterruptedException {
		WebElement LDAPLoginPopup = driver.findElementByAccessibilityId("LDAPCloseButton");
		Thread.sleep(500);
		clickOn(LDAPLoginPopup);
	}
		
	
	
	// AuthenticationTypeComboBox

	public void select_Authentication_Type(String Atype) throws InterruptedException {

		WebElement ATypeComboBox = driver.findElementByAccessibilityId("AuthenticationTypeComboBox");
		WebElement AType1 = driver.findElementByName("Secure");
		WebElement AType2 = driver.findElementByName("SecureSocketsLayer");

		if (Atype.equals(AType1.getText())) {
			clickOn(AType1);
			Thread.sleep(500);
		} else if (Atype.equals(AType2.getText())) {
			clickOn(AType2);
		}
	}
	
	
	
	
	// getText from Authentication Type ComboBox

	public String getText_ATypeComboBox() {
		WebElement ATypeComboBox = driver.findElementByAccessibilityId("AuthenticationTypeComboBox");
		return FetchText(ATypeComboBox);
	}

	// clickOn(ATypeComboBox);

	public void clickOn_ATypeComboBox() {
		WebElement ATypeComboBox = driver.findElementByAccessibilityId("AuthenticationTypeComboBox");
		clickOn(ATypeComboBox);
	}

	// Click on ConnectBtn

	public void clickOn_ConnectBtn() {
		WebElement ConnectBtn = driver.findElementByAccessibilityId("btnConnect");
		clickOn(ConnectBtn);
	}

	// getText from Authentication Type ComboBox

	public String getText_PortNoTextBox() {
		WebElement PortNo = driver.findElementByAccessibilityId("PortNoTextBox");
		return FetchText(PortNo);
	}

	// Enter pwd in to password textbox of Active Directory User credentials popup

	public void Enter_PortNo(String no) throws InterruptedException {
		WebElement PortNo = driver.findElementByAccessibilityId("PortNoTextBox");
		ClearText(PortNo);
		enterText(PortNo, no);
	}

	// LDAPLogin Popup function to be called where invoked in respective pages

	public void ActiveDirectoryUserLoginPopup(String UID, String PW, String DN, String AType)
			throws InterruptedException {
		WebElement ADLgInPopup = driver.findElementByName("Enter ActiveDirectory User Credentials");
		/*
		 * WebElement ADLgInUID = driver.findElementByAccessibilityId("UserIdTextBox");
		 * WebElement ADLgInPW = driver.findElementByAccessibilityId("PasswordTextBox");
		 * WebElement ADDomainName =
		 * driver.findElementByAccessibilityId("LDAPDomainName"); WebElement
		 * ADAuthenticationType =
		 * driver.findElementByAccessibilityId("AuthenticationTypeComboBox");
		 * //WebElement ADPortNo = driver.findElementByAccessibilityId("PortNoTextBox");
		 * WebElement ConnectBtn = driver.findElementByAccessibilityId("btnConnect");
		 */
		if (ADLgInPopup.isDisplayed()) {
			Enter_AD_uid(UID);
			Enter_AD_pwd(PW);
			Enter_AD_DomainName(DN);
			clickOn_ATypeComboBox();
			select_Authentication_Type(AType);
			Thread.sleep(1000);
		}
	}

	
	
	
	
	
	//Fetch value of AD connection status 
	
	//textblockConnStatus
	
	public String get_connectionStatus() {
		WebElement connectionStatus = driver.findElementByAccessibilityId("textblockConnStatus");
		return FetchText(connectionStatus);
	}
	
	
	
	
	
	
	// Verify if user able to enter the user name in the username field in the Enter
	// Active Directory User credentials popup

	public void Enter_AD_uid(String UID) throws InterruptedException {
	
		WebElement ADLgInUID = driver.findElementByAccessibilityId("UserIdTextBox");
		ClearText(ADLgInUID);
		ADLgInUID.sendKeys(UID);
	}

	// Get the AD userid text

	public String get_ADuserID_text() {
		WebElement ADLgInUID = driver.findElementByAccessibilityId("UserIdTextBox");
		return FetchText(ADLgInUID);
	}

	// Enter pwd in to password textbox of Active Directory User credentials popup

	public void Enter_AD_pwd(String PWD) throws InterruptedException {
		WebElement ADLgInPW = driver.findElementByAccessibilityId("PasswordTextBox");
		ClearText(ADLgInPW);
		ADLgInPW.sendKeys(PWD);
	}

	// Get the AD pwd text
	public String get_ADpwd_text() {
		WebElement ADLgInPW = driver.findElementByAccessibilityId("PasswordTextBox");
		return FetchText(ADLgInPW);
	}

	// Enter AD Domain Name text to Active Directory User credentials popup

	public void Enter_AD_DomainName(String DN) throws InterruptedException {
		WebElement ADDomainName = driver.findElementByAccessibilityId("LDAPDomainName");
		ClearText(ADDomainName);
		enterText(ADDomainName, DN);
	}

	// Get the AD Domain Name text
	public String get_AD_DomainName_text() {
		WebElement ADDomainName = driver.findElementByAccessibilityId("LDAPDomainName");
		return FetchText(ADDomainName);
	}
	
	// Navigate to ADUM page
			public AD_UMPage click_AD_UMHeader() throws IOException, InterruptedException {
			clickOn(UserManagement_TAB);
			Thread.sleep(500);
			return new AD_UMPage();
				
			}
	
	
	//click on Accept btn 
	
	public void clickOn_AcceptBtn() {
		WebElement acceptbtn = driver.findElementByAccessibilityId("Button1");
		clickOn(acceptbtn);
	}
	
	
	
	public void selectListUser(String user) throws InterruptedException {
		List<WebElement> users=driver.findElementByAccessibilityId("UserListScrollViewer").findElements(By.className("ListBox"));
		for(int i=0;i<=users.size();i++) {
			System.out.println(users.size());
		List<WebElement> usersinfo=driver.findElementByAccessibilityId("UsersListBox").findElements(By.className("ListBoxItem"));
		for(int j=0;j<=usersinfo.size();j++) {
			String str=users.get(j).getText();
			if(str.equals(user)) {
				users.get(j).click();
				Thread.sleep(1000);
				break;
			}else {
				Actions ac= new Actions(driver);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();}
			}
		}
		}
		

	
	//AllowGuestloginCheckBox
	
	public void click_on_AllowGuest() {
		clickOn(AllowGuestloginCheckBox);
	}
	
	public void click_on_AllowGuest2() {
		Actions act=new Actions(driver);
		act.doubleClick(AllowGuestloginCheckBox);
		
	}
	
	public boolean IsAllowGuestEnabled() {
		return IsElementEnabledStatus(AllowGuestloginCheckBox);
	}
	
	public boolean IsGuestUsertypeEnabled() {
		return IsElementEnabledStatus(GuestUserTypeComboBox);
	}
	
	
	public void selectGuestuser(int i) {
		clickOn(GuestUserTypeComboBox);
		List<WebElement> user=driver.findElementByAccessibilityId("UserTypeComboBox").findElements(By.className("ComboBoxItem"));
		user.get(i).click();
	}

	public AD_UMPage click_YesBtn_popup() throws IOException {
		
		WebElement Yes_Btn = driver.findElementByAccessibilityId("Button1");
		clickOn(Yes_Btn);
		return new AD_UMPage();
	}
	
	
	
	public boolean IsAllowGuestuser_checked() {
		return checkboxSelectStatus(GuestUserTypeComboBox);//checkboxSelectStatus
		}
	
	
	//====Ruchika
	
	public UserManagementPage_Manual ClickUserManagement_TAB1() throws InterruptedException, IOException {
		clickOn(UserManagement_TAB);
		return new UserManagementPage_Manual();
	}
	
	
	public ADUM_page ClickUM_Tab_AD() throws InterruptedException, IOException {
		clickOn(UserManagement_TAB);
		return new ADUM_page();
	}
	
	
	public UserManagementPage_Manual click_UmHeader() throws IOException {
		clickOn(UserManagement_TAB);
		return new UserManagementPage_Manual();
	}
	
///------------checking
	
	
	
	public boolean IspwdlengthcheckboxEnabled() {
		return IsElementEnabledStatus(pwdcheckbox);
	}
	
	
	public boolean IsExpirepwdcheckboxEnabled() {
		return IsElementEnabledStatus(ExpirePasswordCheckBox);
	}
	
		
	public boolean IsDisableUserafterAttemptsCheckBoxEnabled() {
		return IsElementEnabledStatus(DisableUserafterAttemptsCheckBox);
	}
	
	
	public boolean IsUserIdEntryCheckBox_Enabled1() {
		return IsElementEnabledStatus(DisableUserafterAttemptsCheckBox);
	}
	



	public boolean Is_ActiveDirectoryUserbutton_Enabled() {
		return IsElementEnabledStatus(ActiveDirectoryUser_btn);
	}
	
	
	
	
	
	//is ADlogin pop up displaying 
	public boolean is_ADLoginpopup_visible() {
	WebElement ADLgInPopup = driver.findElementByName("Enter ActiveDirectory User Credentials");
	return IsElementVisibleStatus (ADLgInPopup);
	}
	
	

	//btnRetry
	
	public boolean  is_UpdateBtnvisible() {
		WebElement update_Btn = driver.findElementByAccessibilityId("btnRetry");
		return IsElementVisibleStatus(update_Btn);
	}
	
	
	//click on update btn
	
	public void  click_UpdateBtn() {
		WebElement update_Btn = driver.findElementByAccessibilityId("btnRetry");
		clickOn(update_Btn);
	}
	
	
	public boolean  is_connectionstatusvisible() {
		WebElement connection_Btn = driver.findElementByAccessibilityId("textblockConnStatus");
		return IsElementVisibleStatus(connection_Btn);
	}
	
	public  String is_connectionstatus() throws InterruptedException {
		WebElement connection_status = driver.findElementByAccessibilityId("textblockConnStatus");
		Thread.sleep(500);
		return FetchText(connection_status);
	}
	
	
	
}
	
	

