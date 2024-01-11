package com.advrt.pages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.advrt.base.BaseClass;
import com.advrt.pages.FM_SyncOutPage;

public class FM_SyncOutAssetListPage extends BaseClass{

	  
    //Page element variable declaration definition
                   //WebElement SyncOutSelectAllBtn=null;
                   public WebElement SyncOutUsersChkbox=null;
                   WebElement SyncOutAdtChkbox=null;
                   WebElement SyncOutEqpmntChkbox=null;
                   WebElement SyncOutTmpltChkbox=null;
                   WebElement SyncOutOKBtn=null;
                   WebElement SyncOutCancelbtn=null;
                   WebElement SyncOutNoAssets=null;
                   //WebElement SyncOutAssets=null;
                   
                   //Page element Initialize method
                   private void initElements()
                   {
                                  //SyncOutSelectAllBtn= driver.findElementByAccessibilityId("SyncOutSelectAllButton");
                                  SyncOutUsersChkbox=driver.findElementByAccessibilityId("SyncOutUsersCheckBox");
                                  SyncOutAdtChkbox=driver.findElementByAccessibilityId("SyncOutAuditCheckBox");
                   SyncOutEqpmntChkbox=driver.findElementByAccessibilityId("SyncOutEquipmentCheckBox");
//                          SyncOutTmpltChkbox=driver.findElementByAccessibilityId("SyncOutPFTemplateCheckBox"); //SyncOutTemplateCheckBox
                                  SyncOutOKBtn=driver.findElementByName("OK");
                                  SyncOutCancelbtn=driver.findElementByName("Cancel");
                                  SyncOutNoAssets=driver.findElementByClassName("TextBlock");
                                  //SyncOutAssets=driver.findElementByAccessibilityId("SyncOutitemGridViewSmall");
                   }
                   
                   // Constructor for initializing the page elements
                   FM_SyncOutAssetListPage() throws IOException
                   {
                                  super();
                                  initElements();
                   }
                   
                   // Release memory
                   public void resetWebElements()
                   {
                                  //SyncOutSelectAllBtn=null;
                                  SyncOutUsersChkbox=null;
                                  SyncOutAdtChkbox=null;
                                  SyncOutEqpmntChkbox=null;
                                  SyncOutTmpltChkbox=null;
                                  SyncOutOKBtn=null;
                                  SyncOutCancelbtn=null;
                                  SyncOutNoAssets=null;
                                  //SyncOutAssets=null;
                   }
                   
                   /*----------------------
                   Methods of SyncOut Select Page
                   ------------------------*/
                   
/*                          public boolean Is_SyncOutSelectAllBtnVisible()
                   {
                                  return IsElementVisibleStatus(SyncOutSelectAllBtn);
                   } */
                   
                   public boolean Is_SyncOutUsersChkboxVisible()
                   {
                                  return IsElementVisibleStatus(SyncOutUsersChkbox);
                   }
                   
                   public boolean Is_SyncOutAdtChkboxVisible()
                   {
                                  return IsElementVisibleStatus(SyncOutAdtChkbox);
                   }
                   
                   public boolean Is_SyncOutEqpmntChkboxVisible()
                   {
                                  return IsElementVisibleStatus(SyncOutEqpmntChkbox);
                   }
                   
                   public boolean Is_SyncOutTmpltChkboxVisible()
                   {
                                  return IsElementVisibleStatus(SyncOutTmpltChkbox);
                   }
                   
                   public boolean Is_SyncOutNoassetsVisible()
                   {
                                  return IsElementVisibleStatus(SyncOutNoAssets);
                   }
                   
                   public boolean Is_SyncOutOKBtnEnabled()
                   {
                                  return IsElementEnabledStatus(SyncOutOKBtn);
                   }
                   public boolean Is_SyncOutSlctAllBtnVisible()
                   {
                 	  WebElement SyncOutSelectAllBtn= driver.findElementByAccessibilityId("SyncOutSelectAllButton");
                 	  return IsElementVisibleStatus(SyncOutSelectAllBtn);
                   }
                   public void Select_SlctAllBtn()
                   {
                 	  WebElement SyncOutSelectAllBtn= driver.findElementByAccessibilityId("SyncOutSelectAllButton");
                 	  if(!checkboxSelectStatus(SyncOutSelectAllBtn))
                 	  {
                 		  clickOn(SyncOutSelectAllBtn);
                 	  }
                   }
                   public int folderCountWithPrefix(String folderPath, String folderNamePrefix)
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
                               if (file.isDirectory() && file.getName().startsWith(folderNamePrefix)) {
                                   folderCount++;
                               }
                           }
                       }
                       return folderCount;
                   }
                   
                   public boolean Is_SyncOutSlctAllBtnSelected()
                   {
                 	  WebElement SyncOutSelectAllBtn= driver.findElementByAccessibilityId("SyncOutSelectAllButton");
                 	  return checkboxSelectStatus(SyncOutSelectAllBtn);
                   }
                   public boolean Is_SyncOutDeSlctBtnVisible() {
                 	  WebElement deSelect = driver.findElementByAccessibilityId("SyncOutDeSelectAllButton");
                 	  return IsElementVisibleStatus(deSelect);
                   }
                   public void click_DeSelectBtn()
                   {
                 	  WebElement deSelect = driver.findElementByAccessibilityId("SyncOutDeSelectAllButton");
                 	  clickOn(deSelect);
                   }
                   public boolean is_DeSlctAllBtnSelected()
                   {
                 	  WebElement deSelect = driver.findElementByAccessibilityId("SyncOutDeSelectAllButton");
                 	  return checkboxSelectStatus(deSelect);
                   }
                   
                 //click on X Button
           		public FM_SyncOutPage click_XBtn() throws IOException
           		{
           			WebElement xBtn = driver.findElementByName("Cancel");
           			clickOn(xBtn);
           			return new FM_SyncOutPage();
           		}
           	
                 //All Asset path visibility checking
               	public boolean assets_PathVisible()
               	{
               		List<WebElement> assetPath = driver.findElementsByAccessibilityId("AssetIdTextBlock");
               		
               		boolean status = false;
               		for(WebElement asstPth : assetPath)
               		{
               			status = IsElementVisibleStatus(asstPth);
               		}
               		return status;
               	}
               	public boolean is_AssetNameDisplayed()
         		{
         			List<WebElement> assetName = driver.findElementsByAccessibilityId("AssetSearchNameTextBlock");
         			boolean status = false;
         			for(WebElement name:assetName)
         			{
         				if(IsElementVisibleStatus(name))
         					status = true;
         			}
         			return status;
         		}
               	public void click_ReqAssetDisplayed(String aName)
         		{
         			List<WebElement> assetName = driver.findElementsByAccessibilityId("AssetSearchNameTextBlock");
         			for(WebElement name:assetName)
         			{
         				if(FetchText(name).equalsIgnoreCase(aName))
         				{
         					clickOn(name);
         					break;
         				}
         			}
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
               	public boolean is_AssetModifiedDateDisplayed()
         		{
         			List<WebElement> assetDate = driver.findElementsByAccessibilityId("LastActivityDetailsTextBlock");
         			
         			boolean status = false;
         			for(WebElement date:assetDate)
         			{
         				if(IsElementVisibleStatus(date))
         					status = true;
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
               	public boolean is_CancelBtnVisible()
               	{
               		WebElement cancelBtn = driver.findElementByName("Cancel");
               		return IsElementVisibleStatus(cancelBtn);		
               	}
               	public String is_NoAssetsTextVisible()
               	{
               		WebElement noAsset = driver.findElementByName("No Assets Found...!");
               		return FetchText(noAsset);
               	}
                   public void click_OKbtn() throws InterruptedException
                   {
                                  Thread.sleep(8000);
                                  clickOn(SyncOutOKBtn);                               
                                  //((JavascriptExecutor)driver).executeScript("arguments[1].click();",SyncOutOKBtn );
                   }
                   public String get_AlertMsg_text() throws InterruptedException {
               		int explicitWaitTimeout = 200000; 
                       driver.manage().timeouts().implicitlyWait(explicitWaitTimeout, TimeUnit.SECONDS);
               		String alertMsg = "";
               		try
               		{
               			WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
               			alertMsg = FetchText(Msg);
               		}
               		catch (Exception e) {
               			WebElement Msg1 = driver.findElementByAccessibilityId("displayMessageTextBlock");
               			alertMsg = FetchText(Msg1);
               		}		
               		return alertMsg;
               	}

                   public void clickOk_OnSyncOutSelections() throws InterruptedException
                   {
                      //     WebElement okBtn = driver.findElementByAccessibilityId("SyncOutAssetsPopup").findElement(By.name("OK"));  
                       List<WebElement> okBtn = driver.findElementsByName("OK"); 
                 	  clickOn(okBtn.get(1));                               
                   }
               	public boolean is_PassFailCriteriaVisble()
               	{
               		WebElement passFailCrite = driver.findElementByAccessibilityId("SyncOutTemplateCheckBox");
               		return IsElementVisibleStatus(passFailCrite);
               	}
               	public void unSelectPassFailCriteria()
               	{
               		WebElement passFailCrite = driver.findElementByAccessibilityId("SyncOutTemplateCheckBox");
               		if(checkboxSelectStatus(passFailCrite))
               		{
               			clickOn(passFailCrite);
               		}
               	}
               	//Verify wether SyncIn pop up window is displayed or not
               		public boolean is_SyncInPopUpWindowDisplyed()
               		{
               			boolean status = false;
               			try
               			{
               				WebElement syncInPopup = driver.findElementByAccessibilityId("SyncOutAssetsPopup");
               				if(IsElementVisibleStatus(syncInPopup))
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
               		//	return IsElementVisibleStatus(syncInPopup);
               			return status;
               		}
                                                                               
                   public void click_Cancelbtn() throws InterruptedException
                   {
                                  Thread.sleep(5000);
                                  clickOn(SyncOutCancelbtn);                                        
                   }
                   
                   public void click_onUsers() throws InterruptedException
                   {
                                  Thread.sleep(500);
                                  clickOn(SyncOutUsersChkbox);
                   }
                   public void unSelect_Users()
                   {
                 	  if(checkboxSelectStatus(SyncOutUsersChkbox))
                 	  {
                 		  clickOn(SyncOutUsersChkbox);
                 	  }
                   }
                   public void Select_Users()
                   {
                 	  if(!checkboxSelectStatus(SyncOutUsersChkbox))
                 	  {
                 		  clickOn(SyncOutUsersChkbox);
                 	  }
                   }
                   public void unSelect_onTemplate() throws InterruptedException
                   {
                 	  if(checkboxSelectStatus(SyncOutTmpltChkbox))
                 	  {
                            clickOn(SyncOutTmpltChkbox);
                 	  }           
                   }
                   public void click_onTemplate() throws InterruptedException
                   {
                 	  if(!checkboxSelectStatus(SyncOutTmpltChkbox))
                 	  {
                            clickOn(SyncOutTmpltChkbox);
                 	  }           
                   }
                 //check assets are not selected or not
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
    public void unSelect_EquipmentChkBx()
    {
 	   if(checkboxSelectStatus(SyncOutEqpmntChkbox))
 	   {
 		   clickOn(SyncOutEqpmntChkbox);
 	   }
    }
    public void select_EquipmentChkBx()
    {
 	   if(!checkboxSelectStatus(SyncOutEqpmntChkbox))
 	   {
 		   clickOn(SyncOutEqpmntChkbox);
 	   }
    }
       
    
    
    
    public void click_YesBtn_popup() throws IOException {
		WebElement Yes_Btn = driver.findElementByAccessibilityId("Button1");
		clickOn(Yes_Btn);
	}
	
}
