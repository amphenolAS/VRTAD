/**
* @author ruchika.behura
*
*/

package com.advrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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

public class ADUM_page extends BaseClass {

	TestUtilities tu = new TestUtilities();
	// User Management element variable declaration definition
	
	WebElement UMHeaderText = null;
	WebElement PreferencesHeaderText = null;
	WebElement PoliciesHeaderText = null;
	WebElement UserTypeUMDropDown = null;
	WebElement SaveUMBtn = null;
	WebElement TitleUMField = null;
	WebElement BackButton = null;
	

	
	//List<WebElement> Combobx = null;

	private void initElements() {
		// UserManagement Page Element definition
		UMHeaderText = driver.findElementByName("User Management");
		PreferencesHeaderText = driver.findElementByAccessibilityId("PreferencesButton");
		PoliciesHeaderText = driver.findElementByAccessibilityId("PoliciesButton");
		UserTypeUMDropDown = driver.findElementByAccessibilityId("UserTypeComboBox");
		SaveUMBtn = driver.findElementByAccessibilityId("SaveButton");
		
		//BackButton = driver.findElementByAccessibilityId("BackButton");
		//TitleUMField = driver.findElementByAccessibilityId("EditableTextBox");
	
		
		
	}

	ADUM_page() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		UMHeaderText = null;
		PreferencesHeaderText = null;
		PoliciesHeaderText = null;
		UserTypeUMDropDown = null;
		SaveUMBtn = null;
		TitleUMField = null;
		BackButton = null;
	}

	/*----------------------
	Methods of UserManagement Page
	------------------------*/
	
	// Check if UserManagement page is displayed
	public boolean IsUMscreenDisplayed() {
		return IsElementEnabledStatus(UMHeaderText);
	}
	
		//select 	QA testerfrom the group
	
	/*	public void Enter_QA_Group() throws AWTException {
			WebElement SelectGroup = driver.findElementByAccessibilityId("ComboBoxGroup");
			clickOn(SelectGroup);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_Q);
			robot.keyPress(KeyEvent.VK_ENTER);
		}
		
		*/
		
		
		//NewUserType
		
		public void SelectUType(String name) throws IOException {
			WebElement utype = driver.findElementByAccessibilityId("UserTypeComboBox");
			clickOn(utype);
			List<WebElement> userlist=driver.findElementByAccessibilityId("UserTypeComboBox").findElements(By.className("ComboBoxItem"));
			
			WebElement userName = userlist.get(1);
			userName.sendKeys(name); //EditableCombo
			WebElement ele=driver.findElementByName(name);
			clickOn(ele);
		
		}
		
		// Fetch Text of selected option from UserTypeComboBox
		
		public String Fetch_UserType() throws AWTException {
		WebElement UserTypeComboBox = driver.findElementByAccessibilityId("UserTypeComboBox");
		return FetchText(UserTypeComboBox);
		}

		
		
		public DefaultUserPrivilages_page SelectUType1(String name) throws IOException {
			WebElement utype = driver.findElementByAccessibilityId("UserTypeComboBox");
			clickOn(utype);
			List<WebElement> userlist=driver.findElementByAccessibilityId("UserTypeComboBox").findElements(By.className("ComboBoxItem"));
			
			WebElement userName = userlist.get(1);
			userName.sendKeys(name); //EditableCombo
			WebElement ele=driver.findElementByName(name);
			clickOn(ele);
			return new DefaultUserPrivilages_page();
			
		/*	System.out.println("userlist"+ userlist.size());
			
			userlist.get(0).click();

			for (int i = 0; i < userlist.size(); i++) {

				String UNtext1 = Fetch_UserType();
				// System.out.println(UNtext1);
				if (UNtext1.equalsIgnoreCase(name)) {
					clickOn(utype);
					break;
				} else {
					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				}*/
			}
		
		
		//NewDefaultUserPermissionsPopup		
		
		public void select_grp(String name) throws AWTException {
			WebElement SelectGroup = driver.findElementByAccessibilityId("ComboBoxGroup");
			clickOn(SelectGroup);
			List<WebElement> grplist=driver.findElementByAccessibilityId("ComboBoxGroup").findElements(By.className("ComboBoxItem"));
			
			//System.out.println(grplist.size());
			WebElement grpName=grplist.get(1);
				grpName.sendKeys(name);//EditableCombo
			WebElement ele=driver.findElementByName(name);
			clickOn(ele);

	/*	for (int i = 0; i < grplist.size(); i++) {
				
				//String UNtext1 = grplist.get(i).getText();
				 System.out.println(grplist.get(i).getText());
				if (grplist.get(i).getText().equalsIgnoreCase(name)) {
					grplist.get(i).click();
					break;
					
				} else {
					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				}
			}*/
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
		
		
		
		//ComboBoxGroup

		public void select_user(int i) throws InterruptedException {

			Thread.sleep(2000);

			WebElement usergroup = driver.findElementByAccessibilityId("ComboBoxLDAPUsers");
			clickOn(usergroup);
			List<WebElement> userlist = driver.findElementByAccessibilityId("ComboBoxLDAPUsers")
					.findElements(By.className("ComboBoxItem"));

			userlist.get(i).click();

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
			WebElement TitleUMField = driver.findElementByAccessibilityId("EditableTextBox");
			ClearText(TitleUMField);
			enterText(TitleUMField, Title);
			Thread.sleep(1000);
		}
		
		
		
		// Click Save button
		public void ClickNewUserSaveButton() throws InterruptedException {
		WebElement	SaveUMBtn = driver.findElementByAccessibilityId("SaveButton");
		Thread.sleep(2000);	
		clickOn(SaveUMBtn);
		}
		
		
		
		
		
	/*	public void NewDefaultUserPermission (String name) throws IOException {
			
			WebElement SelectGroup = driver.findElementByAccessibilityId("NewDefaultUserPermissionsPopup").findElement(By.className("TextBox"));
			
			clickOn(SelectGroup);
			enterText(SelectGroup, name);
			
			}

	*/
		
		
		public PoliciesPage ClickOn_PoliciesHeaderText() throws AWTException, IOException {
			
			clickOn(PoliciesHeaderText);
			return new PoliciesPage();
		}
		
		//Is NewUser Btn available 
		
		public boolean IsNewUserBtnPresence() {
		WebElement	NewUserUMBtn = driver.findElementByAccessibilityId("NewUserButton");
			return IsElementEnabledStatus(NewUserUMBtn);
		}
		
	
	public MainHubPage ClickBackButn() throws IOException, InterruptedException
	{
		WebElement Backbuton =driver.findElementByAccessibilityId("BackButton");
		Thread.sleep(500);
		clickOn(Backbuton);
		return new  MainHubPage() ;
	}
	
	
	public MainHubPage clickbackbtn() throws InterruptedException, IOException {
		WebElement Backbuton =driver.findElementByAccessibilityId("BackButton");
		Thread.sleep(500);
		clickOn(Backbuton);
		return new  MainHubPage();

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



//Click on select Group
	public void Select_grp() throws InterruptedException {
		WebElement select_grp=driver.findElementByAccessibilityId("ComboBoxGroup");
		Thread.sleep(1000);
		clickOn(select_grp);
		Thread.sleep(1000);

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

	//Click on the save Button
	  public void NewSaveButton() {
WebElement save=driver.findElementByAccessibilityId("btnConnect");
	 clickOn(save);

	}
	  
	  
	  
	  public DefaultUserPrivilages_page newUserType1(String name) throws IOException {
			WebElement NewuserType=driver.findElementByAccessibilityId("NewDefaultUserPermissionsPopup").findElement(By.className("TextBox"));
			clickOn(NewuserType);
			enterText(NewuserType,name);
			//NewuserType.sendKeys("NUserType");
			return new DefaultUserPrivilages_page();
		}

}
