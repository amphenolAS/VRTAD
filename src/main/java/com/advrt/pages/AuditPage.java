/**
* @author ruchika.behura
*
*/

package com.advrt.pages;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.advrt.base.BaseClass;

public class AuditPage extends BaseClass {

	// FileManagementPage Element definition
	WebElement Audit_HeadTitle = null;
	WebElement ActionFilter_Icon = null;
	WebElement BackBtn_AuditPg = null;
	WebElement GenerateReport_Btn = null;

	void initElements() {
		Audit_HeadTitle = driver.findElementByName("Audit Trail");
		ActionFilter_Icon = driver.findElementByAccessibilityId("PART_FilterButton");
		BackBtn_AuditPg = driver.findElementByAccessibilityId("ArrowGlyph");
		GenerateReport_Btn = driver.findElementByAccessibilityId("GenerateReportButton");
	}

	AuditPage() throws IOException {
		super();
		initElements();

	}

	// Release memory
	public void resetWebElements() {
		Audit_HeadTitle = null;
		ActionFilter_Icon = null;		
		ActionFilter_Icon = null;
		GenerateReport_Btn = null;

	}

	// Audit TextBox is Visible
	public boolean AuditHeadTitleVisible() throws InterruptedException {
		return IsElementVisibleStatus(Audit_HeadTitle);
	}

	// Fetch the alert message when a user does not have privilege to access
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}
	
	//Click on Action filter icon to open the filter
	public void Click_ActionFilter_Icon() {

		List<WebElement> filtericon = driver.findElementsByAccessibilityId("PART_FilterButton");
		System.out.println(filtericon.size());
		filtericon.get(3).click();
	}

	// Click on Action Filter (ok)Btn to get the filter result
	public void click_Action_FilterBtn() throws IOException, InterruptedException {
		// List<WebElement> filterBtn =
		// driver.findElementsByAccessibilityId("PART_FilterButton");
		List<WebElement> filterBtn = driver.findElementsByName("Filter");
		// System.out.println(filterBtn.size());

		filterBtn.get(1).click();
		// WebElement msg = driver.findElementByName(");

	}

	// Enter value into Action filter text box
	public void EnterTxt_ActionFilter(String val) {
		WebElement ActionFltr_ValueBox = driver.findElementByAccessibilityId("PART_ValueBox");
		clickOn(ActionFltr_ValueBox);
		ClearText(ActionFltr_ValueBox);
		enterText(ActionFltr_ValueBox, val);
	}

	// Verify that the below details is displaying when user filter the particular action
	public String get_auditEvent_text() {
		List<WebElement> Act_Txt = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(Act_Txt.get(3));
	}
	
	// Verify that the below details is displaying when user filter the particular action
		public String get_auditEvent_text10() {
			List<WebElement> Act_Txt = driver.findElementByAccessibilityId("PART_ScrollViewer")
					.findElements(By.className("TextBlock"));
			return FetchText(Act_Txt.get(10));
		}
	
	//CLick Back button to navigate to Main Hub page
	public MainHubPage Click_BackBtn() throws IOException {
		clickOn(BackBtn_AuditPg);		
		return new MainHubPage();
	}
	
	public boolean is_RequireditEventDisplayed(int index) {
		List<WebElement> Act_Txt = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return IsElementVisibleStatus(Act_Txt.get(index));
	}
	public String get_ReqauditEvent_Time(int index) {
		List<WebElement> Act_Txt = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(Act_Txt.get(index));
	}
	//CLick Generate button to print Audit report
	public void Click_GenerateReport_Btn() throws InterruptedException {
		clickOn(GenerateReport_Btn);
		Thread.sleep(1000);
	}

	// Verify that the below details is displaying when user filter the particular action
			public String get_userName_text() {
				List<WebElement> Act_Txt = driver.findElementByAccessibilityId("PART_ScrollViewer")
						.findElements(By.className("TextBlock"));
				return FetchText(Act_Txt.get(1));
			}
			
			
			public String get_auditUsercommit_text() {
				List<WebElement> Act_Txt = driver.findElementByAccessibilityId("PART_ScrollViewer")
						.findElements(By.className("TextBlock"));
				return FetchText(Act_Txt.get(4));
			}
	
}
