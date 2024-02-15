package com.advrt.pages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.advrt.base.BaseClass;
import com.advrt.utility.TestUtilities;

public class FM_ArchiveSelectionPage extends BaseClass{

	TestUtilities tu = new TestUtilities();
    //Page element variable declaration definition
    WebElement ArchivePopupDate = null;
    WebElement setupChkBx = null;
    WebElement studyFileCheckBx = null;
    WebElement reporterChkBx = null;
   
    //Page element Initialize method
    private void initElements()
    {
    				ArchivePopupDate = driver.findElementByAccessibilityId("AssestPopupDateHeaderLabels");
                    setupChkBx = driver.findElementByAccessibilityId("ArchSetupsCheckBox");
                    studyFileCheckBx = driver.findElementByAccessibilityId("ArchStudyCheckBox");
                    reporterChkBx = driver.findElementByAccessibilityId("ArchReportCheckBox");
    }
    
    //Constructor for initializing the page elements
    FM_ArchiveSelectionPage() throws IOException
    {
                    super();
                    initElements();
    }
    
    //Release memory
    public void resetWebElements()
    {
    	ArchivePopupDate = null;
    			setupChkBx = null;
    			studyFileCheckBx = null;               
    			reporterChkBx = null; 
    }
    
    //Verifying AssestPopupDate visibility
    public boolean is_ArchivePopupDateVisible()
    {
    	return IsElementVisibleStatus(ArchivePopupDate);
    }
    //Verifying setup Check Box visibility
    public boolean is_SetupChkBxVisible()
    {
    	return IsElementVisibleStatus(setupChkBx);
    }
    public boolean is_SetupChkBoxSelected()
    {
    	return checkboxSelectStatus(setupChkBx);
    }
    //Verifying study Files Check Box
    public boolean is_StudyFileCheckBxVisible()
    {
    	return IsElementVisibleStatus(studyFileCheckBx);
    }
    public boolean is_StudyFileCheckBxSelected()
    {
    	return checkboxSelectStatus(studyFileCheckBx);
    }
    //Verifying Reporter Check Box Visibility
    public boolean is_ReporterChkBxVisible()
    {
    	return IsElementVisibleStatus(reporterChkBx);
    }
    public boolean is_ReporterChkBxSelected()
    {
    	return checkboxSelectStatus(reporterChkBx);
    }
    //Get date from Archive Selection Page
    public String getDate_ArchiveSlctionWindow()
    {
    	return FetchText(ArchivePopupDate);
    }
    public void select_SetupsChkBox()
    {
    	if(!checkboxSelectStatus(setupChkBx))
    	{
    		clickOn(setupChkBx);
    	}
    }
    public void unSelect_SetupsChkBox()
    {
    	if(checkboxSelectStatus(setupChkBx))
    	{
    		clickOn(setupChkBx);
    	}
    }
    public void select_StudyFileChkBox()
    {
    	if(!checkboxSelectStatus(studyFileCheckBx))
    	{
    		clickOn(studyFileCheckBx);
    	}
    }
    public void unSelect_StudyFileChkBox()
    {
    	if(checkboxSelectStatus(studyFileCheckBx))
    	{
    		clickOn(studyFileCheckBx);
    	}
    }
    public void select_ReportChkBox()
    {
    	if(!checkboxSelectStatus(reporterChkBx))
    	{
    		clickOn(reporterChkBx);
    	}
    }
    public void unSelect_ReportChkBox()
    {
    	if(checkboxSelectStatus(reporterChkBx))
    	{
    		clickOn(reporterChkBx);
    	}
    }
    public String get_DateOnAssets(int num)
    {
    	List<WebElement> dateList = driver.findElementsByAccessibilityId("LastActivityDetailsTextBlock");
    	
    	return FetchText(dateList.get(num));
    }
	//Verify wether SyncIn pop up window is displayed or not
		public boolean is_ArchiveSelectionWindowDisplyed()
		{
			boolean status = false;
			try
			{
				WebElement ArchivePopUp = driver.findElementByXPath("//*[normalize-space(.='Light Dismiss')]");
				if(IsElementVisibleStatus(ArchivePopUp))
				{
					status = true;
				}
				else
				{
					status = false;
				}
			}
			catch (Exception e) {
				e.getMessage();
			}
			return status;
		}
		
		 //check assets are not selected or not
      	public boolean is_AllAssetsVisible()
      	{
      		List<WebElement> assetOnPopUp = driver.findElementsByClassName("GridViewItem");
      		boolean status = false;
      		for(WebElement assetcnt:assetOnPopUp)
      		{
      			if(IsElementVisibleStatus(assetcnt))
      			{
      				status = true;
      			}
      		}
      		return status;
      	}
      	public boolean is_AssetsSelectedState()
      	{
      		List<WebElement> assetOnPopUp = driver.findElementsByClassName("GridViewItem");
      		boolean status = false;
      		for(WebElement assetcnt:assetOnPopUp)
      		{
      			if(checkboxSelectStatus(assetcnt))
      			{
      				status = true;
      			}
      		}
      		return status;
      	}
    	public boolean is_AssetNameDisplayed()
 		{
 			List<WebElement> assetName = driver.findElementsByAccessibilityId("AssetNameTextBlock");
 			boolean status = false;
 			for(WebElement name:assetName)
 			{
 				if(IsElementVisibleStatus(name))
 					status = true;
 			}
 			return status;
 		}
    	public boolean is_AssetIdDisplayed()
 		{
 			List<WebElement> assetId = driver.findElementsByAccessibilityId("AssetIdTextBlock");
 			boolean status = false;
 			for(WebElement id:assetId)
 			{
 				if(IsElementVisibleStatus(id))
 					status = true;
 			}
 			return status;
 		}
    	public boolean is_AssetSearchNameDisplayed()
		{
			List<WebElement> assetSearchName = driver.findElementsByAccessibilityId("AssetSearchNameTextBlock");
			
			boolean status = false;
			for(WebElement date:assetSearchName)
			{
				if(IsElementVisibleStatus(date))
					status = true;
			}
			return status;
		}
      	public void click_DeSelectBtn()
      	{
      		WebElement deSlctBtn = driver.findElementByAccessibilityId("SyncInDeSelectAllButton");
      		clickOn(deSlctBtn);
      	}
      	public int count_AssetsList()
      	{
      		List<WebElement> assetOnPopUp = driver.findElementsByClassName("GridViewItem");
      		int count = 0;
      		for(WebElement assetcnt:assetOnPopUp)
      		{
      			if(IsElementVisibleStatus(assetcnt))
      			{
      				count++;
      			}
      		}
      		return count;
      	}
      	
    	public void click_Asset(String aName)
      	{
      		List<WebElement> assetName = driver.findElementsByAccessibilityId("AssetSearchNameTextBlock");
      		for(WebElement name: assetName)
      		{
      			if(FetchText(name).equalsIgnoreCase(aName))
      			{
      				clickOn(name);
      				break;
      			}
      		}
      	}
    	public boolean is_SelectAllBtnEnabled()
    	{
    		WebElement selectAll = driver.findElementByAccessibilityId("SyncInSelectAllButton");
    		return IsElementEnabledStatus(selectAll);
    	}
    	public void click_SelectAllBtn()
    	{
    		WebElement selectAll = driver.findElementByAccessibilityId("SyncInSelectAllButton");
    		clickOn(selectAll);
    	}
    	public boolean is_AllAssetsSelected()
       	{
       		List<WebElement> assetOnPopUp = driver.findElementsByClassName("GridViewItem");
       		boolean status = false;
       		for(WebElement assetcnt:assetOnPopUp)
       		{
       			if(checkboxSelectStatus(assetcnt))
       			{
       				status = true;
       			}
       		}
       		return status;
       	}
    	public boolean is_AllAssetsEnabled()
       	{
       		List<WebElement> assetOnPopUp = driver.findElementsByClassName("GridViewItem");
       		boolean status = false;
       		for(WebElement assetcnt:assetOnPopUp)
       		{
       			if(IsElementEnabledStatus(assetcnt))
       			{
       				status = true;
       			}
       		}
       		return status;
       	}
      	public boolean is_OkBtnVisible()
    	{
    		List<WebElement> okBtn = driver.findElementsByName("OK");
    		return IsElementVisibleStatus(okBtn.get(1));
    	}
      	public boolean is_OkBtnEnabled()
    	{
    		List<WebElement> okBtn = driver.findElementsByName("OK");
    		return IsElementEnabledStatus(okBtn.get(1));
    	}
      	 //select ok Button on Archive selection window
        public void click_Ok_ArhiveSelectionPopUp() throws InterruptedException
        {
        	List<WebElement> okBtn = driver.findElementsByName("OK");
        	Thread.sleep(1000);
        	clickOn(okBtn.get(1));
        }
        public int folderCount(String folderPath)
        {
      	  File folder = new File(folderPath);
            if (!folder.exists() || !folder.isDirectory()) {
                System.out.println("Invalid folder path or folder does not exist.");
                return 0;
            }

            int folderCount = 0;
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        folderCount++;
                    }
                }
            }
            return folderCount;
        }
        
      	public boolean is_CancelBtnVisible()
      	{
      		WebElement cancelBtn = driver.findElementByName("Cancel");
      		return IsElementVisibleStatus(cancelBtn);		
      	}
      	public boolean is_AllEqpBtnVisible()
      	{
      		WebElement allEqp = driver.findElementByAccessibilityId("SyncInEquipmentCheckBox");
      		return IsElementVisibleStatus(allEqp);
      	}
      	public void select_AllEqpBtn()
      	{
      		WebElement allEqp = driver.findElementByAccessibilityId("SyncInEquipmentCheckBox");
      		if(!checkboxSelectStatus(allEqp))
      		{
      			clickOn(allEqp);
      		}
      	}
      	public void unSelect_AllEqpBtn()
      	{
      		WebElement allEqp = driver.findElementByAccessibilityId("SyncInEquipmentCheckBox");
      		if(checkboxSelectStatus(allEqp))
      		{
      			clickOn(allEqp);
      		}
      	}
      	public FM_ArchivePage click_CancelBtn() throws IOException
      	{
      		WebElement cancelBtn = driver.findElementByName("Cancel");
      		clickOn(cancelBtn);
      		return new FM_ArchivePage();		
      	}
      	public boolean is_NoAssetsFoundMsgVisible(String message)
      	{
      		WebElement meg = driver.findElementByName(message);
      		return IsElementVisibleStatus(meg);
      	}
      	 //click on X Button
		public FM_ArchivePage click_XBtn() throws IOException
		{
			List<WebElement> xBtn = driver.findElementByAccessibilityId("ArchiveAssetPopup").findElements(By.className("Button"));
			clickOn(xBtn.get(0));
			return new FM_ArchivePage();
		}
    	//close button visibility
       	public boolean is_CloseBtnVisible()
       	{
       		WebElement close = driver.findElementByXPath("//*[normalize-space(.='Close Advanced Validation System')]");
       		return IsElementVisibleStatus(close);
       	}
    
    
}
