/**
* @author 
*
*/

package com.advrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.advrt.pages.AD_UMPage;
import com.advrt.pages.SelectBaseStationPage;
import com.advrt.base.BaseClass;

public class MainHubPage extends BaseClass {

	// Main Hub Page Element definition
	WebElement MainUILoggedinUserTitle = null;
	//WebElement MainUILoggedinUserName = null;
	WebElement MainUIAdminTile = null;
	
	WebElement MainUIPageTitle = null;
	WebElement MainUIEquipmentTitle = null;
	WebElement FileManagementTitle = null;
	WebElement AuditTitle = null;

	private void initElements() {
		// Main Hub Page Page Element definition
		//MainUILoggedinUserTitle = driver.findElementByAccessibilityId("UserDesignationTextBlock");
		//MainUILoggedinUserName = driver.findElementByAccessibilityId("UserNameTextBlock");
		MainUIAdminTile = driver.findElementByName("Admin");
		MainUIPageTitle = driver.findElementByName("ValProbe RT System");
		MainUIEquipmentTitle = driver.findElementByName("Equipment");
		FileManagementTitle = driver.findElementByName("File Management");
		AuditTitle = driver.findElementByName("Audit");
		// Discover

	}

	public MainHubPage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		MainUILoggedinUserTitle = null;
	
		MainUIAdminTile = null;
		MainUIPageTitle = null;
		MainUIEquipmentTitle = null;
		FileManagementTitle = null;
		AuditTitle = null;

		// System.out.println("Login Page elements memory released");
	}

	// click on discover tile
	public SelectBaseStationPage clickonDiscoverTile() throws InterruptedException, IOException {
		WebElement DiscoverTile = driver.findElementByName("Discover");
		clickOn(DiscoverTile);
		return new SelectBaseStationPage();
	}

	// Verify the Main Hub Page title name
	public boolean Is_mainHubPageTitle_Visible() {
		return IsElementVisibleStatus(MainUIPageTitle);
	}

	// Is UserDesignation text Block Presence
	public boolean IsUserDesigBlockPresence() {
		WebElement UserDesigTextBlock = driver.findElementByAccessibilityId("UserDesignationTextBlock");
		return IsElementEnabledStatus(UserDesigTextBlock);
	}
	
	// Click the Admin Tile to navigate UserManagementPage
		public AD_UMPage AD_ClickAdminTile_UMpage() throws InterruptedException, IOException {
			clickOn(MainUIAdminTile);
			Thread.sleep(500);
			return new AD_UMPage();
		}
		
		
		
		public UserManagementPage_Manual ClickAdminTile_manualUM() throws InterruptedException, IOException {
			clickOn(MainUIAdminTile);
			Thread.sleep(500);
			return new UserManagementPage_Manual();
		}
		

	// Verify the Logged in User credentials
	public String LoggedinUserName() {
		WebElement MainUILoggedinUserName = driver.findElementByAccessibilityId("UserNameTextBlock");
		return FetchText(MainUILoggedinUserName);
	}

	//
	public String UserNameText() {
		WebElement UserNameText = driver.findElementByAccessibilityId("UserNameTextBlock");
		return FetchText(UserNameText);
	}

	// Sign out Operation
	public LoginPage UserSignOut() throws InterruptedException, IOException {

		WebElement MainUILoggedinUserName = driver.findElementByAccessibilityId("UserNameTextBlock");
		clickOn(MainUILoggedinUserName);
		Thread.sleep(500);
		WebElement MainUISignOut = driver.findElementByName("Sign out");
		clickOn(MainUISignOut);
		Thread.sleep(1000);
		return new LoginPage();
	}

	// Click the Admin Tile to navigate UserManagementPage
	public UserManagementPage ClickAdminTile_UMpage() throws InterruptedException, IOException {
		clickOn(MainUIAdminTile);
		Thread.sleep(500);
		return new UserManagementPage();
	}
	
	
	// Click the Admin Tile to navigate PreferencesPage
	public PreferencesPage ClickAdminTile_Prefpage() throws InterruptedException, IOException {
		clickOn(MainUIAdminTile);
		Thread.sleep(1000);
		return new PreferencesPage();
	}
	
	// Click the Admin Tile to navigate PreferencesPage
	public PoliciesPage ClickAdminTile_Polpage() throws InterruptedException, IOException {
		clickOn(MainUIAdminTile);
		Thread.sleep(1000);
		return new PoliciesPage();
	}

	// Click the Admin Tile when SuperVisor does not have default access privilege
	public void ClickAdminTile() throws InterruptedException {
		clickOn(MainUIAdminTile);
		Thread.sleep(500);
	}

	// Click the Asset Tile
	public assetHubPage Click_AssetTile() throws InterruptedException, IOException {
		WebElement MainUIAssetTile = driver.findElementByName("Assets");
		clickOn(MainUIAssetTile);
		Thread.sleep(500);
		return new assetHubPage();
	}

	// Click the Asset Tile
	public assetHubPage Click_AssetTile2() throws InterruptedException, IOException {
		WebElement MainUIAssetTile = driver.findElementByName("Assets");
		clickOn(MainUIAssetTile);
		Thread.sleep(500);
		return new assetHubPage();
	}

	// Fetch the Asset count data in the Asset Tile
	public String AssetCountInAssetTileOfMainHubPage() throws InterruptedException {
		WebElement AssetCountInfoInAsstTile = driver.findElementByAccessibilityId("TitleCountTextBlock");

		String AstCnt = FetchText(AssetCountInfoInAsstTile);
		// System.out.println("AstCnt in Main Hub Page: "+AstCnt);
		return AstCnt;
	}
	
	// is MainUIEquipmentTitle visible
	public boolean IsEquipmentTile_Visible() {
		return IsElementVisibleStatus(MainUIEquipmentTitle);

	}

	// Click the Equipment Tile
	public EquipmentHubPage ClickEquipmentTile() throws InterruptedException, IOException {
		clickOn(MainUIEquipmentTitle);
		Thread.sleep(500);
		return new EquipmentHubPage();
	}
	
	// Fetch EquipmentDueCalibration_Count
	public String FetchTxt_EquipmentDueCalibration_Count(int j) throws InterruptedException, IOException {
		List<WebElement> Listcounts = driver.findElementByName("Equipment").findElements(By.className("TextBlock"));

		return Listcounts.get(j).getText();

	}


	// Click the Equipment Tile
	public FileManagementPage ClickFileManagementTitle() throws InterruptedException, IOException {
		clickOn(FileManagementTitle);
		Thread.sleep(500);
		return new FileManagementPage();
	}

	// Click the Audit Title
	public AuditPage ClickAuditTitle() throws InterruptedException, IOException {
		clickOn(AuditTitle);
		Thread.sleep(1000);
		return new AuditPage();
	}

	// Click the Audit Title
	public void Alert_AuditTitle() throws InterruptedException {
		clickOn(AuditTitle);
		Thread.sleep(500);
	}

	// click on connection btn
	public void click_connectBtn() {
		WebElement btnConnect = driver.findElementByAccessibilityId("btnConnectIO");
		clickOn(btnConnect);
	}
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	
	// Click the Audit Tile when user does not have default access privilege
		public void ClickAuditTitle_alrt() throws InterruptedException {
			clickOn(AuditTitle);
			Thread.sleep(500);
		}
	
	//Fetch Text Equipment count From Equipement Tile
			public String EquipmentCntInEquipmentTileOfMainHubPage() throws InterruptedException {
				List<WebElement> eqpCoucnt = driver.findElementsByAccessibilityId("TitleCountTextBlock");
				return FetchText(eqpCoucnt.get(2));
			}
			

			
			public SelectBaseStationPage Click_Discover() throws InterruptedException, IOException {
				WebElement DiscoverTile = driver.findElementByName("Discover");
				clickOn(DiscoverTile);
				Thread.sleep(500);
				return new SelectBaseStationPage();
			}

			public void Click_Discover_alrt() throws InterruptedException, IOException {
				WebElement DiscoverTile = driver.findElementByName("Discover");
				clickOn(DiscoverTile);
				Thread.sleep(500);
				
			}
			
			
			public ADUM_page ClickAdminTile_ADUM() throws InterruptedException, IOException {
				clickOn(MainUIAdminTile);
				Thread.sleep(500);
				return new ADUM_page();
			}
			
			
			
			
}
