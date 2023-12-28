package com.advrt.pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.advrt.base.BaseClass;
import com.advrt.utility.TestUtilities;

public class DefaultUserPrivilages_page extends BaseClass {

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


	
	//newUserTypePopup ELements
	WebElement	NUUserManagement=null;
	WebElement	NUCreateAssets=null;
	WebElement	NUCreatesetup=null;
	WebElement	NUCreateEquipment=null;
	WebElement	NUCreateReports=null;
	WebElement	NUCreatePassFailTeamplate=null;
	WebElement	NUPreferences=null;
	WebElement	NURunCalibration=null;
	WebElement	NUModifyAssets=null;
	WebElement	NUModifySetUp=null;
	WebElement NUModifyEquipment=null;
	WebElement NUCameraAccess=null;
	WebElement NUEditPassFail=null;
	WebElement NUPolicies=null;
	WebElement NURunQualification=null;
	WebElement NUDeleteAssets=null;
	WebElement NUDeleteSetUp=null;
	WebElement NUDeleteEquipment=null;
	WebElement NUDeleteStudyFiles_Reports=null;
	WebElement NUDeletePassFailTemplate=null;
	WebElement	NUHardwarreMaintaince=null;
	WebElement	NURunVerification=null;
	WebElement	NUCopyFiles_Reports=null;
	WebElement	NUArchieveData=null;
	WebElement NUManualSync=null;
	WebElement	NUChangeConsleTime=null;
	WebElement	NUAuditTrail=null;




	private void initElements() {
	
		
		
		//NewUserTypepopup Elements
		
		NUUserManagement= driver.findElementByAccessibilityId("UPAdminCheckBox");
		NUCreateAssets=driver.findElementByAccessibilityId("UPAssetsPrivlegesCheckBox");
		NUCreatesetup=driver.findElementByAccessibilityId("UPSetupCreationCheckBox");
		NUCreateEquipment=driver.findElementByAccessibilityId("UPEquipmentPrivlegesCheckBox");
		NUCreateReports=driver.findElementByAccessibilityId("UPCreateReportsCheckBox");
		NUCreatePassFailTeamplate=driver.findElementByAccessibilityId("UPCreateTemplateCheckBox");
		NUPreferences=driver.findElementByAccessibilityId("UPPreferencesCheckBox");
		NURunCalibration=driver.findElementByAccessibilityId("UPCalibrationExecutionCheckBox");
		NUModifyAssets=driver.findElementByAccessibilityId("UPModifyAssetsPrivlegesCheckBox");
		NUModifySetUp=driver.findElementByAccessibilityId("UPModifySetupCheckBox");
		NUModifyEquipment=driver.findElementByAccessibilityId("UPModifyEquipmentPrivlegesCheckBox");
		NUCameraAccess=driver.findElementByAccessibilityId("UPCamerAccessCheckbox");
		NUEditPassFail=driver.findElementByAccessibilityId("UPEditTemplateCheckBox");
		NUPolicies=driver.findElementByAccessibilityId("UPPoliciesCheckBox");
		NURunQualification=driver.findElementByAccessibilityId("UPQualificationExecutionCheckBox");
		NUDeleteAssets=driver.findElementByAccessibilityId("UPAssetDeleteCheckBox");
		NUDeleteSetUp=driver.findElementByAccessibilityId("UPSetupDeleteCheckBox");
		NUDeleteEquipment=driver.findElementByAccessibilityId("UPEquipmentDeleteCheckBox");
		NUDeleteStudyFiles_Reports=driver.findElementByAccessibilityId("UPDeleteFilesReportsCheckBox");
		NUDeletePassFailTemplate=driver.findElementByAccessibilityId("UPDeleteTemplateCheckBox");
		NUHardwarreMaintaince=driver.findElementByAccessibilityId("UPHardwareMaintenanceCheckBox");
		NURunVerification=driver.findElementByAccessibilityId("UPRunVerificationCheckBox");
		NUCopyFiles_Reports=driver.findElementByAccessibilityId("UPCopyFilesReportsCheckBox");
		NUArchieveData=driver.findElementByAccessibilityId("UPArchiveDataCheckBox");
		NUManualSync=driver.findElementByAccessibilityId("UPManualSyncCheckBox");
		NUChangeConsleTime=driver.findElementByAccessibilityId("UPChngConsoleTimeCheckBox");
		NUAuditTrail=driver.findElementByAccessibilityId("UPAuditViewPrintCheckBox");



		
		


	}
	DefaultUserPrivilages_page() throws IOException {
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

		
		NUUserManagement=null;
		NUCreateAssets=null;
		NUCreatesetup=null;
		NUCreateEquipment=null;
		NUCreateReports=null;
		NUCreatePassFailTeamplate=null;
		NUPreferences=null;
		NURunCalibration=null;
		NUModifyAssets=null;
		NUModifySetUp=null;
		NUModifyEquipment=null;
		NUCameraAccess=null;
		NUEditPassFail=null;
		NUPolicies=null;
		NURunQualification=null;
		NUDeleteAssets=null;
		NUDeleteSetUp=null;
		NUDeleteEquipment=null;
		NUDeleteStudyFiles_Reports=null;
		NUDeletePassFailTemplate=null;
		NUHardwarreMaintaince=null;
		NURunVerification=null;
		NUCopyFiles_Reports=null;
		NUArchieveData=null;
		NUManualSync=null;
		NUChangeConsleTime=null;
		NUAuditTrail=null;




	}

	//METHODS
			
			// Click on the Create Asset Privilege Check Box
            public void Click_Create_AssetCheckBox1() {
                            clickOn(NUCreateAssets);
            }
            
            public boolean IsCreate_AssetCheckBox_checked() {
				return checkboxSelectStatus(NUCreateAssets);
				}

         // Click on the UserManagement Privilege Check Box
            public void Click_Create_UserManagement() {
                            clickOn(NUUserManagement);
            }
            
            
         // Click on the Createsetup Privilege Check Box
            public void Click_Create_SetUp() {
                clickOn(NUCreatesetup);
              }
            
            
            public boolean IsCreate_SetupCheckBox_checked() {
				return checkboxSelectStatus(NUCreatesetup);
				}

         // Click on the CreateEquipment Privilege Check Box
            public void Click_Create_Equipmet() {
                clickOn(NUCreateEquipment);
              }

         // Click on the CreateReports Privilege Check Box 
            public void Click_Create_Reports() {
                clickOn(NUCreateReports);
              }

         // Click on the CreatePassFailTeamplate Privilege Check Box 
            public void Click_Create_PassFailTemplate() {
                clickOn(NUCreatePassFailTeamplate);
              }
           
         // Click on the Preferences Privilege Check Box
            public void Click_Create_Preferences() {
                clickOn(NUPreferences);
              }
            
         // Click on the ModifyAssets Privilege Check Box
            public void Click_Create_ModifyAssets() {
                clickOn(NUModifyAssets);
              }
            
         // Click on the ModifySetUp Privilege Check Box
            public void Click_ModifySetup() {
                clickOn(NUModifySetUp);
              }
            
         // Click on the ModifyEquipment Privilege Check Box
            public void Click_ModifyEquipment() {
                clickOn(NUModifyEquipment);
              }

         // Click on the CameraAccess  Privilege Check Box
            public void Click_CameraAccess() {
                clickOn(NUCameraAccess);
              }

         // Click on the  EditPassFail Privilege Check Box
            public void Click_EditPassFail() {
                clickOn(NUEditPassFail);
              }

         // Click on the Policies Privilege Check Box
            public void Click_Policies() {
                clickOn(NUPolicies);
              }
            
            
         // Click on the RunVerification Privilege Check Box
            public void Click_NURunCalibration() {
                clickOn(NURunCalibration);
              }

         // Click on the RunQualification Privilege Check Box
            public void Click_RunQualification() {
                clickOn(NURunQualification);
              }

         // Click on the DeleteAssets Privilege Check Box
            public void Click_DeleteAssets() {
                clickOn(NUDeleteAssets);
              }

         // Click on the DeleteSetUp Privilege Check Box
            public void Click_DeleteSetUp() {
                clickOn(NUDeleteSetUp);
              }

         // Click on the DeleteEquipment Privilege Check Box
            public void Click_DeleteEquipment() {
                clickOn(NUDeleteEquipment);
              }

         // Click on the DeleteStudyFiles_Reports Privilege Check Box
            public void Click_DeleteStudyFiles_Reports() {
                clickOn(NUDeleteStudyFiles_Reports);
              }

            
         // Click on the DeletePassFailTemplate Privilege Check Box
            public void Click_DeletePassFailTemplate() {
                clickOn(NUDeletePassFailTemplate);
              }

         // Click on the HardwarreMaintaince Privilege Check Box
            public void Click_HardwarreMaintaince() {
                clickOn(NUHardwarreMaintaince);
              }
            
         // Click on the RunVerification Privilege Check Box
            public void Click_RunVerification() {
                clickOn(NURunVerification);
              }

         // Click on the CopyFiles_ReportsPrivilege Check Box
            public void Click_CopyFiles_Reports() {
                clickOn(NUCopyFiles_Reports);
              }
            
         // Click on the ArchieveData Privilege Check Box
            public void Click_ArchieveData() {
                clickOn(NUArchieveData);
              }
            
           
         // Click on the ManualSync Privilege Check Box
            public void Click_ManualSync() {
                clickOn(NUManualSync);
              }
            
         // Click on the ChangeConsleTime Privilege Check Box
            public void Click_ChangeConsleTime() {
                clickOn(NUChangeConsleTime);
              }
          
         // Click on the Create Audit Privilege Check Box
            public void Click_AuditCheckBox() {
            	 clickOn(NUAuditTrail);}
            	 
            
            public void AllPrivilages() {
            	
         		clickOn(NUUserManagement);
         		clickOn(NUCreateAssets);
         		clickOn(NUCreatesetup);
         		clickOn(NUCreateEquipment);
         		clickOn(NUCreateReports);
         		clickOn(NUCreatePassFailTeamplate);
         		clickOn(NUPreferences);
         		clickOn(NURunCalibration);
         		clickOn(NUModifyAssets);
         		clickOn(NUModifySetUp);
         		clickOn(NUModifyEquipment);
         		clickOn(NUCameraAccess);
         		clickOn(NUEditPassFail);
         		clickOn(NUPolicies);
         		clickOn(NURunQualification);
         		clickOn(NUDeleteAssets);
         		clickOn(NUDeleteSetUp);
         		clickOn(NUDeleteEquipment);
         		clickOn(NUDeleteStudyFiles_Reports);
         		clickOn(NUDeletePassFailTemplate);
         		clickOn(NUHardwarreMaintaince);
         		clickOn(NURunVerification);
         		clickOn(NUCopyFiles_Reports);
         		clickOn(NUArchieveData);
         		clickOn(NUManualSync);
         		clickOn(NUChangeConsleTime);
         		clickOn(NUAuditTrail);
         		
         		//WebElement save=driver.findElementByAccessibilityId("btnConnect");
              	 //clickOn(save);

			}
            
            
            	
            
            //Click on the save Button
            	  public void NewSaveButton() {
              WebElement save=driver.findElementByAccessibilityId("btnConnect");
           	 clickOn(save);

				}
            	  
            	  
            	  
            	  
            	  public DefaultUserPrivilages_page newUserType(String name) throws IOException {
            			WebElement NewuserType=driver.findElementByAccessibilityId("NewDefaultUserPermissionsPopup").findElement(By.className("TextBox"));
            			clickOn(NewuserType);
            			enterText(NewuserType,name);
            			//NewuserType.sendKeys("NUserType");
            			return new DefaultUserPrivilages_page();
            		}
            	  
            	  
            	 /* 
            	  public void defaultprivilageDropdown(String type) throws InterruptedException {
					WebElement dropdown=driver.findElementByAccessibilityId("EditableCombo");
					clickOn(dropdown);
					List<WebElement> dropdownlist=dropdown.findElements(By.className("ComboBoxItem"));
							for(int i=0;i<=dropdownlist.size();i++) {
								String str=	dropdownlist.get(i).getText();
								if(str.equals(type)) {
									dropdownlist.get(i).click();
									Thread.sleep(500);
									break;
								}
							}
				}*/
   
            	  
            	  //----Ruchika methods
            	  
            	  public DefaultUserPrivilages_page Enter_NewUserType(String name) throws IOException, InterruptedException {

            			WebElement NewUserType = driver.findElementByAccessibilityId("NewDefaultUserPermissionsPopup")
            					.findElement(By.className("TextBox"));

            			clickOn(NewUserType);

            			enterText(NewUserType, name);
            			return new DefaultUserPrivilages_page();

            		}
            	  
            	  
            	  public void click_ModifyAssetsPrivlegesCheckBox() throws IOException, InterruptedException {

            			clickOn(NUModifyAssets);
            			Thread.sleep(1000);
            		}
            	  
            	  
            	  public ADUM_page click_save_btn() throws IOException, InterruptedException {

            			WebElement btnConnect = driver.findElementByAccessibilityId("btnConnect");
            			clickOn(btnConnect);
            			tu.UserLoginPopup_UserCommentTextBox("kiranc","Amphenol@123","comment");
            			
            			Thread.sleep(1000);
            			return new ADUM_page();

            		}
            	  
            	  
            	// UPAssetsPrivlegesCheckBox

            		public void click_UPAssetsPrivlegesCheckBox() throws IOException, InterruptedException {

            			clickOn(NUCreateAssets);
            			Thread.sleep(1000);
            		}

            		
            		// UPAssetDeleteCheckBox

            		public void click_UPAssetDeleteCheckBox() throws IOException, InterruptedException {

            			clickOn(NUDeleteAssets);
            			Thread.sleep(1000);
            		}

            		// UPSetupCreationCheckBox

            		public void click_UPSetupCreationCheckBox() throws IOException, InterruptedException {

            			clickOn(NUCreatesetup);
            			Thread.sleep(1000);
            		}

            		// UPSetupModifyCheckBox

            		public void click_UPModifySetupCheckBox() throws IOException, InterruptedException {

            			clickOn(NUModifySetUp);
            			Thread.sleep(1000);
            		}

            		// UPEquipmentPrivlegesCheckBox

            		public void click_UPEquipmentPrivlegesCheckBox() throws IOException, InterruptedException {

            			clickOn(NUCreateEquipment);
            			Thread.sleep(1000);
            		}

            		//ModifyEquipmentPrivlegesCheckBox

            		public void click_ModifyEquipmentPrivlegesCheckBox() throws IOException, InterruptedException {

            			clickOn(NUModifyEquipment);
            			Thread.sleep(1000);
            		}

            		// UPEquipmentDeleteCheckBox

            		public void click_UPEquipmentDeleteCheckBox() throws IOException, InterruptedException {

            			clickOn(NUDeleteEquipment);
            			Thread.sleep(1000);
            		}
            		
            		//UPPoliciesCheckBox
            		
            		
            		public void click_UPPoliciesCheckBox() throws IOException, InterruptedException {

            			clickOn(NUPolicies);
            			Thread.sleep(1000);
            		}
            		
            		//click_UPAdminCheckBox
            		
            		public void click_UPUserManagementCheckBox() throws IOException, InterruptedException {

            			clickOn(NUUserManagement);
            			Thread.sleep(1000);
            		}
            		
            		//click_UPSetupdeleteCheckBox
            		
            		public void click_UPSetupdeleteCheckBox() throws IOException, InterruptedException {

            			clickOn(NUDeleteSetUp);
            			Thread.sleep(1000);
            		}
            		
            		
            		//PreferencesCheckBox
            		
            		
            			public void click_UPPreferencesCheckBox() throws IOException, InterruptedException {

            				clickOn(NUPreferences);
            				Thread.sleep(1000);
            			}
            			
            			
            		
            			
            			public void click_HardwareMaintenanceCheckBox() throws IOException, InterruptedException {

            				clickOn(NUHardwarreMaintaince);
            				Thread.sleep(1000);
            			}
            			
            		


            		

            		public void Select_UT_NewUtypecombobox(String name) throws IOException {
            			WebElement Editcombo = driver.findElementByAccessibilityId("EditableCombo");
            			clickOn(Editcombo);
            			List<WebElement> userlist = driver.findElementByAccessibilityId("EditableCombo")
            					.findElements(By.className("ComboBoxItem"));

            			WebElement userName = userlist.get(0);
            			userName.sendKeys(name); // EditableCombo
            			WebElement ele = driver.findElementByName(name);
            			clickOn(ele);
            		}

            		


            		// Select/Click any User in the UserList Panel
            		public void Select_UT_NewUtypecombobox1(String UN) throws InterruptedException {
            			WebElement combobox = driver.findElementByAccessibilityId("EditableCombo");
            			clickOn(combobox);
            		
            			List<WebElement> Userslist = driver.findElementByAccessibilityId("EditableCombo")
            					.findElements(By.className("ComboBoxItem"));
            			
            			System.out.println(Userslist.size());
            			//Userslist.get(0).click();

            			/*for (int i = 0; i < Userslist.size(); i++) {

            				String UNtext1 = Fetchtxt_NewUserType();
            				// System.out.println(UNtext1);
            				if (UNtext1.equalsIgnoreCase(UN)) {
            					clickOn(EditableTextBox);
            					break;
            				} else {
            					Actions ac = new Actions(driver);
            					ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
            				}
            			}*/
            		}
            		
            		
            		
            		// Is Update button enabled

            		public boolean Is_UpdateBtn_Enabled() {

            			WebElement btnconnect = driver.findElementByAccessibilityId("btnConnect");
            			return IsElementEnabledStatus(btnconnect);

            		}

            		// click on update button

            		public ADUM_page clickOn_UpdateBtn() throws IOException, InterruptedException {

            			WebElement btnconnect = driver.findElementByAccessibilityId("btnConnect");
            			clickOn(btnconnect);

            			tu.UserLoginPopup_UserCommentTextBox("kiranc", "Amphenol@123", "comment");

            			Thread.sleep(1000);
            			return new ADUM_page();

            		}
            		
            		
            		
            		// is checkbox checkedin

            		public boolean Is_UPAdminCheckBox_checkedin() {
            			return checkboxSelectStatus(NUUserManagement);

            		}
            		
            		
            		
            		//is UPAssetsPrivlegesCheckBox checkedin

            		public boolean Is_UPAssetsPrivlegesCheckBox_checkedin() {
            			return checkboxSelectStatus(NUCreateAssets);

            		}
            		
            		
            		
            		// Fetch Text from EditableTextBox

            		public String Fetchtxt_NewUserType() {
            			WebElement Newutype = driver.findElementByAccessibilityId("EditableTextBox");
            			return FetchText(Newutype);

            		}
            		
            		
            		
            		// Click on save button , to appear the alert btn

            		public void click_savebtn_alert() throws IOException, InterruptedException {

            			WebElement btnConnect = driver.findElementByAccessibilityId("btnConnect");
            			clickOn(btnConnect);

            		}

          
            		
            		
            		
            		
}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			


