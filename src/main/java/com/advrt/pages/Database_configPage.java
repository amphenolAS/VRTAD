package com.advrt.pages;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.advrt.base.BaseClass;

public class Database_configPage extends BaseClass {

	// FileManagementPage Element definition
	WebElement fileSystem = null;
	WebElement dataBase = null;
	WebElement UserManagement_TAB = null;
	WebElement DataConfigHeaderText = null;
	WebElement Policies_TAB = null;

	void initElements() {
	
		DataConfigHeaderText = driver.findElementByAccessibilityId("DatabaseConfigButton");
		Policies_TAB = driver.findElementByAccessibilityId("PoliciesButton");
	}

	Database_configPage() throws IOException {
		super();
		initElements();

	}

	// Release memory
	public void resetWebElements() {
		/*
		 * Audit_HeadTitle = null; ActionFilter_Icon = null; ActionFilter_Icon = null;
		 * GenerateReport_Btn = null;
		 */

		// fileSystem=null;
		// dataBase=null;
		DataConfigHeaderText = null;
		UserManagement_TAB = null;
		Policies_TAB = null;

	}

	// Navigate to UM page
	public UserManagementPage click_UMHeader() throws IOException {
		UserManagement_TAB = driver.findElementByAccessibilityId("UserManagementButton");
		clickOn(UserManagement_TAB);
		return new UserManagementPage();
	}

	// Navigate to UM page
	public AD_UMPage click_UMHeaderManual() throws IOException {
		UserManagement_TAB = driver.findElementByAccessibilityId("UserManagementButton");
		clickOn(UserManagement_TAB);
		return new AD_UMPage();
	}

	// Navigate to UM page
	public UserManagementPage_Manual click_UMHeaderMnl() throws IOException {
		UserManagement_TAB = driver.findElementByAccessibilityId("UserManagementButton");
		clickOn(UserManagement_TAB);
		return new UserManagementPage_Manual();
	}

	// Check if Policies page is displayed
	public boolean IsDataConfig_screenDisplayed() {
		return IsElementEnabledStatus(DataConfigHeaderText);
	}

	public PoliciesPage click_PolicyPage() throws InterruptedException, IOException {
		Thread.sleep(1000);
		clickOn(Policies_TAB);
		return new PoliciesPage();

	}

	/*
	 * // Audit TextBox is Visible public boolean AuditHeadTitleVisible() throws
	 * InterruptedException { return IsElementVisibleStatus(Audit_HeadTitle); }
	 * 
	 * // Fetch the alert message when a user does not have privilege to access
	 * public String AlertMsg() { WebElement Msg =
	 * driver.findElementByAccessibilityId("displayMessageTextBlock"); return
	 * FetchText(Msg); }
	 * 
	 * //Click on Action filter icon to open the filter public void
	 * Click_ActionFilter_Icon() {
	 * 
	 * List<WebElement> filtericon =
	 * driver.findElementsByAccessibilityId("PART_FilterButton");
	 * System.out.println(filtericon.size()); filtericon.get(3).click(); }
	 */

}