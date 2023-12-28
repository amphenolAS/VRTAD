package com.advrt.pages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.advrt.base.BaseClass;
import com.advrt.utility.TestUtilities;

public class AD_UMPage extends BaseClass {

	TestUtilities tu = new TestUtilities();
	// User Management element variable declaration definition
	WebElement UsersList=null;
	WebElement select_grp=null;
	WebElement search_btn=null;
	WebElement select_User=null;
	WebElement User_name=null;
	WebElement User_Id=null;
	WebElement User_Title=null;
	WebElement User_type=null;
	WebElement User_phn=null;
	WebElement User_email=null;
	WebElement AD_UMHeaderText=null;
	WebElement PreferencesHeaderText=null;
	WebElement PoliciesHeaderText=null;
	WebElement save_btn=null;
	WebElement cancle_btn=null;
	
	
	//List<WebElement> Combobx = null;
	
	
	//Ruchika ADUM_page
	
	WebElement UMHeaderText = null;
	WebElement UserTypeUMDropDown = null;
	WebElement SaveUMBtn = null;
	WebElement TitleUMField = null;
	WebElement BackButton = null;
	WebElement	PWUMField=null;
	WebElement ConPWUMField=null;
	WebElement	UsersListButton=null;

	//Admin elements
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
	WebElement UMImgBtn = null;
	WebElement UMAssetPriv = null;
	WebElement CreateSetupPriv = null;
	WebElement EditSetupPriv = null;
	WebElement DeleteSetup = null;
	WebElement RunVerification = null;
	WebElement HardwareHeaderText=null;

	private void initElements() {
		// AD_UserManagement Page Element definition

		AD_UMHeaderText = driver.findElementByName("User Management");
		PreferencesHeaderText = driver.findElementByAccessibilityId("PreferencesButton");
		PoliciesHeaderText = driver.findElementByAccessibilityId("PoliciesButton");//FWUpgradeButton
		//HardwareHeaderText = driver.findElementByAccessibilityId("FWUpgradeButton");
		//UsersList=driver.findElementByAccessibilityId("PrintUsersListButton");
		//select_grp=driver.findElementByAccessibilityId("ComboBoxGroup");
		//select_User=driver.findElementByAccessibilityId("ComboBoxLDAPUsers");
		User_name=driver.findElementByAccessibilityId("NameTextBox");
		User_Id=driver.findElementByAccessibilityId("UserIDTextBox");
		//User_Title=driver.findElementByAccessibilityId("EditableCombo");
		//User_type=driver.findElementByAccessibilityId("UserTypeComboBox");
		User_phn=driver.findElementByAccessibilityId("PhoneTextBox");
		User_email=driver.findElementByAccessibilityId("EmailTextBox");
		//save_btn=driver.findElementByAccessibilityId("SaveButton");
		//cancle_btn=driver.findElementByAccessibilityId("CancelButton");
		//search_btn=driver.findElementByAccessibilityId("SearchTextBox");
		UMHeaderText = driver.findElementByName("User Management");
		UserTypeUMDropDown = driver.findElementByAccessibilityId("UserTypeComboBox");
		SaveUMBtn = driver.findElementByAccessibilityId("SaveButton");
		
		//BackButton = driver.findElementByAccessibilityId("BackButton");
		TitleUMField = driver.findElementByAccessibilityId("EditableTextBox");
		//PWUMField = driver.findElementByAccessibilityId("UMCPasswordTextBox");
		//ConPWUMField = driver.findElementByAccessibilityId("ConfirmPasswordTextBox");
		
		UsersListButton = driver.findElementByAccessibilityId("PrintUsersListButton");
		//DisableCheckbox = driver.findElementByAccessibilityId("DisableUserCheckBox");
		
		//ADMIN ELEMENTS
/*
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
*/
	}


	AD_UMPage() throws IOException {
		super();
		initElements();
	}


	// Release memory
	public void resetWebElements() {
		select_grp=null;
		select_User=null;
		UsersList=null;
		search_btn=null;
		User_name=null;
		User_Id=null;
		User_Title=null;
		User_type=null;
		User_phn=null;
		User_email=null;
		AD_UMHeaderText=null;
		PreferencesHeaderText=null;
		PoliciesHeaderText=null;
		save_btn=null;
		PWUMField=null;
		ConPWUMField=null;
		
		UMHeaderText = null;
		UserTypeUMDropDown = null;
		SaveUMBtn = null;
		TitleUMField = null;
		BackButton = null;

		AdminUMPriv = null;
		AdminPolicyPriv = null;
		AdminPreferencePriv = null;
		AdminHWMaintenancePriv = null;
		CreateAssetPriv = null;
		EditAssetPriv = null;
		DeleteAssets = null;
		CreateEquipPriv = null;
		EditEquipPriv = null;
		DeleteEquipment = null;
		CreateReports = null;
		CreatePassFailTemplate = null;
		AuditTrail = null;
		RunQualification = null;
		DeleteStudyFiles = null;
		EditPassFailTemplate = null;
		RunCalibration = null;
		CopyFilesReports = null;
		ArchiveData = null;
		ManualSync = null;
		CameraAccess = null;
		DeletePassFailTemplate = null;
		ChangeConsoleTime = null;
		DisableCheckbox = null;
		UMImgBtn = null;
		UMAssetPriv = null;
		CreateSetupPriv = null;
		EditSetupPriv = null;
		DeleteSetup = null;
		RunVerification = null;
		HardwareHeaderText=null;
		UsersListButton=null;

	}

	//METHODS
	

	// Verify the SelectGroup presence...")
	public boolean SelectGroupPresence() {
		return IsElementEnabledStatus(select_grp);
	}

	// Verify the SelectUser presence...")
	public boolean SelectUserPresence() {
		return IsElementEnabledStatus(select_User);
	}

	// Verify the NameUser presence...")
	public boolean NamePresence() {
		return IsElementEnabledStatus(User_name);
	}

	public boolean IDPresence() {
		return IsElementEnabledStatus(User_Id);
	}

	
	public boolean UMtabPresence() {
		return IsElementEnabledStatus(AD_UMHeaderText);
	}
	
	public boolean PreferencetabEnabled() {
		return IsElementEnabledStatus(PreferencesHeaderText);
	}
	
	
	public boolean HardwaretabEnabled() {
		return IsElementEnabledStatus(HardwareHeaderText);
	}
	
	// Check if Policies page is displayed
		public boolean IsPoliciestabEnabled() {
			return IsElementEnabledStatus(PoliciesHeaderText);
		}
	
	
	//Click on select Group
	public void Select_grp() throws InterruptedException {
		WebElement select_grp=driver.findElementByAccessibilityId("ComboBoxGroup");
		Thread.sleep(1000);
		clickOn(select_grp);
		Thread.sleep(1000);

	}

	//click on Select user

	public void Select_user() throws InterruptedException {
		WebElement select_User=driver.findElementByAccessibilityId("ComboBoxLDAPUsers");
		Thread.sleep(2000);
		clickOn(select_User);
	}


	// Click on PoliciesHeaderText
	public PreferencesPage Click_PreferenceTab() throws IOException, InterruptedException {
		Thread.sleep(500);
		clickOn(PreferencesHeaderText);
		return new PreferencesPage();
	}
	
	
	

	
	// Click on PoliciesHeaderText
		public void ClickPreferenceTab() throws InterruptedException {
			Thread.sleep(500);
			clickOn(PreferencesHeaderText);
			
		}

	//click on searchbtn
	public void Click_Search_btn() throws IOException, InterruptedException {
		WebElement	search_btn=driver.findElementByAccessibilityId("SearchTextBox");
		Thread.sleep(500);
		clickOn(search_btn);//

	}

	// verify Save button presence
	public boolean SaveBtn() {
		return IsElementVisibleStatus(save_btn);
	}
	
	
	// Verify if AdminUMPriv Privilege checked/selected or not
		public boolean AdminUMPrivstatus() {
			return checkboxSelectStatus(AdminUMPriv);
		}

		// Verify if AdminPolicyPriv Privilege checked/selected or not
		public boolean AdminPolicyPrivstatus() {
			return checkboxSelectStatus(AdminPolicyPriv);
		}
		// Verify if AdminPreferencePriv Privilege checked/selected or not
		public boolean AdminPreferencePrivstatus() {
			return checkboxSelectStatus(AdminPreferencePriv);
		}
		// Verify if AdminHWMaintenancePriv Privilege checked/selected or not
		public boolean AdminHWMaintenancePrivstatus() {
			return checkboxSelectStatus(AdminHWMaintenancePriv);
		}



	public void clickSavebtn() throws InterruptedException {
		WebElement save_btn=driver.findElementByAccessibilityId("SaveButton");
		Thread.sleep(2000);
		clickOn(save_btn);
	}

	// click on BackButton
	public MainHubPage click_BackBtn() throws InterruptedException, IOException {
		WebElement BackButton = driver.findElementByAccessibilityId("BackButton");
		clickOn(BackButton);
		Thread.sleep(1000);
		return new MainHubPage();
	}


	/*
	// Select UserType
	public void select_UserType(String Utype) throws InterruptedException {
		// System.out.println(Utype);
		WebElement User_type=driver.findElementByAccessibilityId("UserTypeComboBox");
		clickOn(User_type);
		Thread.sleep(1000);
		WebElement UMAdministrator1 = driver.findElementByName("SystemAdministrator");
		WebElement UMSupervisor = driver.findElementByName("Supervisor");
		WebElement UMOperator = driver.findElementByName("Operator");
		WebElement UMSelect1 = driver.findElementByName("Select");
		WebElement UMNewUser = driver.findElementByName("NewUserType");

		if (Utype.equals(UMSelect1.getText())) {
			clickOn(UMSelect1);
			Thread.sleep(500);
		} else if (Utype.equals(UMAdministrator1.getText())) {
			clickOn(UMAdministrator1);
			Thread.sleep(500);
			} 
		else if (Utype.equals(UMSupervisor.getText())) {
			clickOn(UMSupervisor);
			Thread.sleep(500);
		} else if (Utype.equals(UMOperator.getText())) {
			clickOn(UMOperator);
			Thread.sleep(500);
		}else if (Utype.equals(UMNewUser.getText())) {
			clickOn(UMNewUser);
			Thread.sleep(500);
		}

	}*/




	// Click on PoliciesHeaderText
	public PoliciesPage Click_Policy() throws IOException, InterruptedException {
		Thread.sleep(500);
		clickOn(PoliciesHeaderText);
		return new PoliciesPage();
	}


	// Click on PoliciesHeaderText
	public PoliciesPage AD_Click_Policy() throws IOException, InterruptedException {
		Thread.sleep(500);
		clickOn(PoliciesHeaderText);
		Thread.sleep(500);
		return new PoliciesPage();
	}
	
	
	
	
	public void select_2grp(String name) throws InterruptedException {
		//List<WebElement> grplist=driver.findElementByAccessibilityId("ComboBoxGroup").findElements(By.className("ComboBoxItem"));
		//grplist.get(1).sendKeys("QA Testers");

		WebElement grp2=driver.findElementByAccessibilityId("ComboBoxGroup");
		//grp2.clear();
		//grpName.sendKeys(name);
		Actions act=new Actions(driver);
		act.sendKeys(Keys.ARROW_DOWN).build().perform();
		
		Thread.sleep(1000);
		WebElement click=driver.findElementByName(name);
		clickOn(click);
	}


	public void select_user(int i) throws InterruptedException {
		Thread.sleep(2000);
	WebElement select_User=driver.findElementByAccessibilityId("ComboBoxLDAPUsers");
		clickOn(select_User);
		Thread.sleep(2000);
		List<WebElement> userlist=driver.findElementByAccessibilityId("ComboBoxLDAPUsers").findElements(By.className("ComboBoxItem"));
		userlist.get(i).click();

	}

	// Select UserType-----SelectUType
	public void select_UserType1(String Utype) throws InterruptedException {
		// System.out.println(Utype);
		WebElement User_type=driver.findElementByAccessibilityId("UserTypeComboBox");
		clickOn(User_type);
		Thread.sleep(1000);
		List<WebElement> usertypelist=driver.findElementByAccessibilityId("UserTypeComboBox").findElements(By.className("ComboBoxItem"));
		for(int i=0;i<=usertypelist.size();i++) {
			String str=	usertypelist.get(i).getText();
			if(str.equals(Utype)) {
				usertypelist.get(i).click();
				Thread.sleep(500);
				break;
			}
		}

	}
	
	
	public String Fetch_UserType() throws AWTException {
		WebElement UserTypeComboBox = driver.findElementByAccessibilityId("UserTypeComboBox");
		return FetchText(UserTypeComboBox);
		}

	// Select UserType----SelectUType1
	public DefaultUserPrivilages_page select_UserType2(String Utype) throws InterruptedException, IOException {
		// System.out.println(Utype);
		WebElement User_type=driver.findElementByAccessibilityId("UserTypeComboBox");
		clickOn(User_type);
		Thread.sleep(1000);
		List<WebElement> usertypelist=driver.findElementByAccessibilityId("UserTypeComboBox").findElements(By.className("ComboBoxItem"));
		for(int i=0;i<=usertypelist.size();i++) {
			String str=	usertypelist.get(i).getText();
			if(str.equals(Utype)) {
				usertypelist.get(i).click();
				Thread.sleep(500);
				break;
			}
		}
		return new DefaultUserPrivilages_page();

	}


	// Select UserTitle
	public void select_UserTitle(String Ttype) throws InterruptedException {
		// System.out.println(Utype);
		WebElement User_Title=driver.findElementByAccessibilityId("EditableCombo");
		clickOn(User_Title);
		Thread.sleep(1000);
		WebElement UMManager = driver.findElementByName("Manager");
		WebElement UMSelect1 = driver.findElementByName("Select");

		if (Ttype.equals(UMSelect1.getText())) {
			clickOn(UMSelect1);
			Thread.sleep(500);
		} else if (Ttype.equals(UMManager.getText())) {
			// SelectAdministrator();
			clickOn(UMManager);
			// Thread.sleep(500);
		} 

	}



	public void selectListUser() throws InterruptedException {
		List<WebElement> usersinfo=driver.findElementByAccessibilityId("UsersListBox").findElements(By.className("ListBoxItem"));
		usersinfo.get(0).click();
	}

	public String selectgroupsearch() throws InterruptedException {
		List<WebElement> groupinfo=driver.findElementByAccessibilityId("ComboBoxGroup").findElements(By.className("ComboBoxItem"));
		return FetchText(groupinfo.get(1));
	}



	public DefaultUserPrivilages_page newUserType(String name) throws IOException {
		WebElement NewuserType=driver.findElementByAccessibilityId("NewDefaultUserPermissionsPopup").findElement(By.className("TextBox"));
		clickOn(NewuserType);
		enterText(NewuserType,name);
		//NewuserType.sendKeys("NUserType");
		return new DefaultUserPrivilages_page();
	}


	
	public void select_grp(String name) throws InterruptedException {
		Thread.sleep(2000);
        WebElement SelectGroup = driver.findElementByAccessibilityId("ComboBoxGroup");
        clickOn(SelectGroup);
        List<WebElement> grplist=driver.findElementByAccessibilityId("ComboBoxGroup").findElements(By.className("ComboBoxItem"));
        //grplist.get(1).sendKeys("QA Testers");
        WebElement grpName=grplist.get(1);
              grpName.sendKeys(name);//EditableCombo
        WebElement click=driver.findElementByName(name);
        Thread.sleep(1000);
        clickOn(click);
        Thread.sleep(1000);
 }


	
	public boolean users_isDisplayed() {
		
		WebElement select_User=driver.findElementByAccessibilityId("ComboBoxLDAPUsers");
			clickOn(select_User);
			return select_User.isDisplayed();
	}
	
	
	
public  int users_count() {
		List<WebElement> select_User=driver.findElementsByAccessibilityId("ComboBoxLDAPUsers");
		return select_User.size();		
	}
	
	
	
	public int UserCount() {
		List<WebElement> usersinfo=driver.findElementByAccessibilityId("UsersListBox").findElements(By.className("ListBoxItem"));
		return usersinfo.size();
	}

	
	public void SearchSpecificGrp(String Name) throws InterruptedException {
		WebElement SelectGroup = driver.findElementByAccessibilityId("ComboBoxGroup");
        clickOn(SelectGroup);
        WebElement searchgroup=driver.findElementByName("Search Group");
        clickOn(searchgroup);
        //EditableTextBox
        WebElement TextBox=driver.findElementByAccessibilityId("EditableTextBox");
        clickOn(TextBox);
        TextBox.sendKeys(Name);
        WebElement search_btn=driver.findElementByAccessibilityId("SearchTextBox");
        clickOn(search_btn);
	}
	
	
	
	
	
	
	// Fetch the Agroup Name text
		public String getgroupNameTxtBox() {
			WebElement groupnameTxtBox=driver.findElementByAccessibilityId("EditableTextBox");
			return FetchText(groupnameTxtBox);
		}
		
		
		public void Enter_datain_searchgroup(String Name) throws InterruptedException {
			WebElement SelectGroup = driver.findElementByAccessibilityId("ComboBoxGroup");
	        clickOn(SelectGroup);
	        WebElement searchgroup=driver.findElementByName("Search Group");
	        clickOn(searchgroup);
	        //EditableTextBox
	        WebElement TextBox=driver.findElementByAccessibilityId("EditableTextBox");
	        clickOn(TextBox);
	        TextBox.sendKeys(Name);
	       		}





//Ruchika ADUM_page methods
		// Check if UserManagement page is displayed
		public boolean IsUMscreenDisplayed() {
			return IsElementEnabledStatus(UMHeaderText);
		}



		//Search group
				public void search_grp(String name) throws AWTException, InterruptedException {
					WebElement SelectGroup = driver.findElementByAccessibilityId("ComboBoxGroup");
					clickOn(SelectGroup);
					//Search Group
					WebElement SearchGroup = driver.findElementByName("Search Group");
					clickOn(SearchGroup);
					enterText(SearchGroup, name);
					Thread.sleep(2000);
					
					WebElement SearchTextBox = driver.findElementByAccessibilityId("SearchTextBox");
					clickOn(SearchTextBox);
					
					
				}




				// Check if UserManagement page is displayed
				
				public boolean IsUnmapDisplayed() {
					WebElement UnmapButton = driver.findElementByAccessibilityId("DeleteButton");
					return IsElementEnabledStatus(UnmapButton);
				}
				
				
				//click on unmap button 
				
				public void  click_UnmapBtn() {
					
					WebElement UnmapButton = driver.findElementByAccessibilityId("DeleteButton");
					clickOn(UnmapButton);
				}
				
				//Fetch the text from ComboBoxGroup select group
				
				public String Fetch_Groupname() throws AWTException {
					
					WebElement SelectGroup = driver.findElementByAccessibilityId("ComboBoxGroup");
					return FetchText(SelectGroup);
					}
				
		//
				public void ClickOn_SelectUsers() throws AWTException {
					WebElement SelectGroup = driver.findElementByAccessibilityId("ComboBoxLDAPUsers");
					clickOn(SelectGroup);
				}
				
				//verify select users options are available
				
				//Select User
				public boolean Is_SelectUser_available() throws AWTException {
					WebElement SelectGroup = driver.findElementByName("Select User");
					return IsElementVisibleStatus(SelectGroup);
				}

				
				
				// Select UserType
			/*	public void select_UserType(String Utype) throws InterruptedException {
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

				} */
				
				
				// Enter Title text
				public void enterNewUserTitle(String Title) throws InterruptedException {
					ClearText(TitleUMField);
					enterText(TitleUMField, Title);
					Thread.sleep(1000);
				}
				
				
				
				// Click Save button
				public void ClickNewUserSaveButton() throws InterruptedException {
					clickOn(SaveUMBtn);
				}
				
			
				
				
				public PoliciesPage ClickOn_PoliciesHeaderText() throws AWTException, IOException {
					
					clickOn(PoliciesHeaderText);
					return new PoliciesPage();
				}
				
				//Is NewUser Btn available 
				
				public boolean IsNewUserBtnPresence() {
				WebElement	NewUserUMBtn = driver.findElementByAccessibilityId("NewUserButton");
					return IsElementEnabledStatus(NewUserUMBtn);
				}
				
				
			
			
			
		 //is AssetsPrivleges CheckBox checked in
		    
		    public boolean Is_AssetsPrivlegesCheckBox_checkedin() {
		    	WebElement	AssetsPrivlegesCheckBox = driver.findElementByAccessibilityId("AssetsPrivlegesCheckBox");
		    
				return checkboxSelectStatus(AssetsPrivlegesCheckBox);
		   	 	 
		    }
		    
		    
		 //is ModifyAssetsPrivleges CheckBox checked in
		    
		    public boolean Is_ModifyPrivlegesCheckBox_checkedin() {
		    	
		    	WebElement	ModifyAssetsPrivlegesCheckBox = driver.findElementByAccessibilityId("ModifyAssetsPrivlegesCheckBox");
		    
				return checkboxSelectStatus(ModifyAssetsPrivlegesCheckBox);
		   	 	 
		    }
		    
		//is DeleteAssetsPrivleges CheckBox checked in
		    
		    public boolean Is_AssetDeleteCheckBox_checkedin() {
		    	
		    	WebElement	AssetDeleteCheckBox = driver.findElementByAccessibilityId("AssetDeleteCheckBox");
		    
				return checkboxSelectStatus(AssetDeleteCheckBox);
		   	 	 
		    }
		    
		    
		    //click_UPSetupCreationCheckBox
		    
		 public boolean Is_SetupCreationCheckBox_checkedin() {
		    	
		    	WebElement	SetupCreationCheckBox = driver.findElementByAccessibilityId("SetupCreationCheckBox");
		    
				return checkboxSelectStatus(SetupCreationCheckBox);
		   	 	 
		    }
		 
		 
		 
		//click_UPSetupmodifyCheckBox
		 
		public boolean Is_ModifySetupCheckBox_checkedin() {
		   	
		   	WebElement	ModifySetupCheckBox = driver.findElementByAccessibilityId("ModifySetupCheckBox");
		   
				return checkboxSelectStatus(ModifySetupCheckBox);
		  	 	 
		   }


		public boolean Is_DeleteSetupCheckBox_checkedin() {
		 	
		 	WebElement	SetupDeleteCheckBox = driver.findElementByAccessibilityId("SetupDeleteCheckBox");
		 
				return checkboxSelectStatus(SetupDeleteCheckBox);
			 	 
		 }


		//EquipmentPrivlegesCheckBox


		public boolean Is_EquipmentPrivlegesCheckBox_checkedin() {
		 	
		 	WebElement	EquipmentPrivlegesCheckBox = driver.findElementByAccessibilityId("EquipmentPrivlegesCheckBox");
		 
				return checkboxSelectStatus(EquipmentPrivlegesCheckBox);
			 	 
		 }


		//Modify Equipment Privileges CheckBox


		  public boolean Is_ModifyEquipmentPrivlegesCheckBox_checkedin() {
		 	
		 	WebElement	ModifyEquipmentPrivlegesCheckBox = driver.findElementByAccessibilityId("ModifyEquipmentPrivlegesCheckBox");
		 
				return checkboxSelectStatus(ModifyEquipmentPrivlegesCheckBox);
			 	 
		 }

		// Delete Equipment Privileges CheckBox


		public boolean Is_DeleteEquipmentPrivlegesCheckBox_checkedin() {
			
			WebElement	EquipmentDeleteCheckBox = driver.findElementByAccessibilityId("EquipmentDeleteCheckBox");

			return checkboxSelectStatus(EquipmentDeleteCheckBox);
			 	 
		}

		//Policies CheckBox Privileges CheckBox


		public boolean Is_PoliciesCheckBox_checkedin() {
			
			WebElement	PoliciesCheckBox = driver.findElementByAccessibilityId("PoliciesCheckBox");

			return checkboxSelectStatus(PoliciesCheckBox);
			 	 
		}


		//Preferences CheckBox  Privileges CheckBox


		public boolean Is_PreferencesCheckBox_checkedin() {
			
			WebElement	PreferencesCheckBox = driver.findElementByAccessibilityId("PreferencesCheckBox");

			return checkboxSelectStatus(PreferencesCheckBox);
			 	 
		}

		// Admin CheckBox CheckBox Privileges CheckBox


		public boolean Is_UserManagementCheckBox_checkedin() {
			
			WebElement	AdminCheckBox = driver.findElementByAccessibilityId("AdminCheckBox");

			return checkboxSelectStatus(AdminCheckBox);
			 	 
		}


		//Hardware Maintenance CheckBox Privileges CheckBox


		public boolean Is_HardwareMaintenanceCheckBox_checkedin() {
			
			WebElement	HardwareMaintenanceCheckBox = driver.findElementByAccessibilityId("HardwareMaintenanceCheckBox");

			return checkboxSelectStatus(HardwareMaintenanceCheckBox);
			 	 
		}



}
































