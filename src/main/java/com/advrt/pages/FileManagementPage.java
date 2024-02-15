
package com.advrt.pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;

import com.advrt.pages.FM_SyncInPage;
import com.advrt.pages.FM_VRTConvertPage;
import com.advrt.base.BaseClass;

public class FileManagementPage extends BaseClass {


               // FileManagementPage Element definition
               WebElement SyncInBtn = null;
               WebElement SyncOutBtn = null;
               WebElement ArchiveBtn = null;
               WebElement VRTConvertBtn = null;
               WebElement    BackButton=null;
               
               
               private void initElements() {
                              ArchiveBtn = driver.findElementByAccessibilityId("Archive");
                              SyncInBtn = driver.findElementByAccessibilityId("SyncIn");
                              SyncOutBtn = driver.findElementByAccessibilityId("SyncOut");
                              VRTConvertBtn = driver.findElementByAccessibilityId("VRTConvert");
                              //OKButton=driver.findElementByName("OK");
                              BackButton = driver.findElementByAccessibilityId("BackButton");
                              
                              
               }

               FileManagementPage() throws IOException {
                              super();
                              initElements();

               }
               
               //Release memory
               public void resetWebElements()
               {
                              ArchiveBtn = null;
                              VRTConvertBtn = null;
                              SyncInBtn = null;
                              SyncOutBtn = null;
                        
                              //OKButton=null;
                              
                                             
               }

               /*----------------------
               Methods of SyncIn Page
               ------------------------*/


               // Check if SyncInHeaderBtn is displayed
               public boolean IsDisplayed_SyncInHeaderBtn() {
                              return IsElementEnabledStatus(SyncInBtn);
               }

               // Move to SynIn Page by Clicking the header SyncIn Button
               public FM_SyncInPage ClickSyncInBtn_SyncinPage(String UID, String PW) throws InterruptedException, IOException {
                              clickOn(SyncInBtn);
                              Thread.sleep(500);
                              UserLoginPopup(UID, PW);
                              return new FM_SyncInPage();
               }
               
               
            // Move to SynIn Page by Clicking the header SyncIn Button
               public FM_SyncInPage ClickSyncInBtn_SyncinPagewithcommit(String UID, String PW,String commit) throws InterruptedException, IOException {
                              clickOn(SyncInBtn);
                              Thread.sleep(500);
                              UserLoginPopup_UserCommentTextBox(UID, PW,commit);
                              return new FM_SyncInPage();
               }
               // Verify if login Pop is displayed by Clicking the header SyncIn Button
               public void ClickSyncInBtn(String UID, String PW) throws InterruptedException, IOException {
                              clickOn(SyncInBtn);
                              Thread.sleep(500);
                              UserLoginPopup(UID, PW);

               }
            // Fetch the Save Alert message
           	public String AlertMsg() {
           		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
           		return FetchText(Msg);
           	}


               /*----------------------
               Methods of Sync Out Page
               ------------------------*/
           	
           	
           	
           	
         // Move to SynIn Page by Clicking the header SyncIn Button
            public FM_SyncOutPage ClickSyncoutBtn_SyncOutPagewithcommit(String UID, String PW,String commit) throws InterruptedException, IOException {
                           clickOn(SyncOutBtn);
                           Thread.sleep(500);
                           UserLoginPopup_UserCommentTextBox(UID, PW,commit);
                           return new FM_SyncOutPage();
            }
           	
           	
           	
           	
           	
           	
           	
               // Move to SynOut Page by Clicking the header SyncIn Button
           	
               public boolean IsDisplayed_SyncOutHeaderBtn() {
                              return IsElementEnabledStatus(SyncOutBtn);
               }
               
               // is ArchiveFolderBrowseButton visible
               
               public boolean IsArchiveFolderBrowseButton_visible() {
            WebElement ArchiveFolderBrowseButton = driver.findElementByAccessibilityId("ArchiveFolderBrowseButton");
                   return IsElementEnabledStatus(ArchiveFolderBrowseButton);
    }
               
               public FM_SyncOutPage ClickSyncOutBtn_SyncOutPage(String UID, String PW) throws InterruptedException, IOException {
                              clickOn(SyncOutBtn);
                              Thread.sleep(500);
                              UserLoginPopup(UID, PW);
                              return new FM_SyncOutPage();
               }
               
               // Verify if login Pop is displayed by Clicking the header SyncIn Button
               public void ClickSyncOutBtn(String UID, String PW) throws InterruptedException, IOException {
                              clickOn(SyncOutBtn);
                              Thread.sleep(500);
                              UserLoginPopup(UID, PW);
               }
               public void  Click_on_SyncOutBtn() throws InterruptedException, IOException {
                              clickOn(SyncOutBtn);
                              Thread.sleep(500);
                              //UserLoginPopup(null, null);
                              //return new FileManagementPage();
               }
               //505327
               
               public void Click_OKbtn_Syncout() throws InterruptedException
               {
            	   {
            	        WebElement okbtn = driver.findElementByAccessibilityId("ValidateUserOK");
            	        clickOn(SyncOutBtn);
            	        Thread.sleep(500);
            	        clickOn(okbtn);        
            	    }                 
               }
               
               
            // Move to SynIn Page by Clicking the header SyncIn Button
               public FM_SyncInPage AD_ClickSyncInBtn_SyncinPage_withcommit(String UID, String PW,String Pwd) throws InterruptedException, IOException {
                              clickOn(SyncInBtn);
                              Thread.sleep(500);
                              UserLoginPopup_UserCommentTextBox(UID, PW, Pwd);
                              return new FM_SyncInPage();
               }
               

//            // Fetch the alert message when a user does not have privilege to access
//            public String AlertMsg() {
//                           WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
//                           return FetchText(Msg);
//            }
               
               /*----------------------
               Methods of Archive Page
               ------------------------*/
               // Move to Archive Page by Clicking the header SyncIn Button
               public FM_ArchivePage Click_ArchiveTab(String UID, String PW) throws InterruptedException, IOException {
                              clickOn(ArchiveBtn);
                              Thread.sleep(500);
                              UserLoginPopup(UID, PW);
                              return new FM_ArchivePage();
               }
               
               
               public FM_ArchivePage Click_ArchiveTabwithcommit(String UID, String PW,String commit) throws InterruptedException, IOException {
                   clickOn(ArchiveBtn);
                   Thread.sleep(500);
                   UserLoginPopup_UserCommentTextBox(UID, PW,commit);
                   return new FM_ArchivePage();
    }
    
               
            // Move to Archive Page by Clicking the header SyncIn Button
               public void Click_ArchiveTabwithcomment_alert(String UID, String PW,String commit) throws InterruptedException, IOException {
                              clickOn(ArchiveBtn);
                              Thread.sleep(500);
                              UserLoginPopup_UserCommentTextBox(UID, PW,commit);
                              
               }
               
               // Alert message Move to Archive Page by Clicking the header SyncIn Button
               public void Click_ArchiveTab_Alertmsg(String UID, String PW) throws InterruptedException, IOException {
                              clickOn(ArchiveBtn);
                              Thread.sleep(500);
                              UserLoginPopup(UID, PW);
               }
               
               
               /*----------------------
               Methods of VRTConvrt Page
               ------------------------*/
               // Move to Archive Page by Clicking the header SyncIn Button
               public FM_VRTConvert Click_VRTConvertTab(String UID, String PW) throws InterruptedException, IOException {
                              clickOn(ArchiveBtn);
                              Thread.sleep(500);
                              UserLoginPopup(UID, PW);
                              return new FM_VRTConvert();
               }
               
               
               public FM_VRTConvert Click_VRTConvertTabwithcommit(String UID, String PW,String commit) throws InterruptedException, IOException {
                   clickOn(ArchiveBtn);
                   Thread.sleep(500);
                   UserLoginPopup_UserCommentTextBox(UID, PW,commit);
                   return new FM_VRTConvert();
    }
               
               // Move to Archive Page by Clicking the header SyncIn Button
               public void Click_VRTConvertTab_WithAlertMsg(String UID, String PW) throws InterruptedException, IOException {
                              clickOn(ArchiveBtn);
                              Thread.sleep(500);
                              UserLoginPopup(UID, PW);
               
			}

               
            // Move to SynIn Page by Clicking the header SyncIn Button
               public void AD_ClickSyncInBtn_SyncinPage_withcommit_alrt(String UID, String PW,String Pwd) throws InterruptedException, IOException {
                              clickOn(SyncInBtn);
                              Thread.sleep(500);
                              UserLoginPopup_UserCommentTextBox(UID, PW, Pwd);
                             
               }
               
               
               
               // Move to SynIn Page by Clicking the header SyncIn Button
               public FM_SyncInPage ClickSyncInBtn_SyncinPage(String UID, String PW,String comm) throws InterruptedException, IOException {
                              clickOn(SyncInBtn);
                              Thread.sleep(500);
                              UserLoginPopup_UserCommentTextBox(UID, PW,comm);
                              return new FM_SyncInPage();
               }
               
               
               public FM_SyncOutPage ClickSyncOutBtn_SyncOutPage(String UID, String PW, String com) throws InterruptedException, IOException {
                   clickOn(SyncOutBtn);
                   Thread.sleep(500);
                   UserLoginPopup_UserCommentTextBox(UID, PW,com);
                   return new FM_SyncOutPage();
    }
               
               
//BackButton
               
               public MainHubPage click_Backbtn() throws IOException, InterruptedException {
            	   Thread.sleep(1000);
            	   clickOn(BackButton);
            	   return new MainHubPage();
            	   
               }
               
               public FM_VRTConvertPage Click_AvsConvert_Btn(String UID, String PW) throws InterruptedException, IOException {
  	   			  clickOn(VRTConvertBtn);
                    Thread.sleep(500);
                    UserLoginPopup(UID, PW);
                    return new FM_VRTConvertPage();
     }
               public FM_VRTConvertPage Click_AvsConvert_Btnwithcomment(String UID, String PW,String commit) throws InterruptedException, IOException {
   	   			  clickOn(VRTConvertBtn);
                     Thread.sleep(500);
                     UserLoginPopup_UserCommentTextBox(UID, PW,commit);
                     return new FM_VRTConvertPage();
      }
                
               
		
}
