package com.advrt.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.advrt.base.BaseClass;

public class FM_SyncOutwithAsset extends BaseClass{
               
               //Page element variable declaration definition
                              WebElement SyncOutSelectAllBtn=null;
                              public WebElement SyncOutUsersChkbox=null;
                              WebElement SyncOutAdtChkbox=null;
                              WebElement SyncOutEqpmntChkbox=null;
                              WebElement SyncOutTmpltChkbox=null;
                              WebElement SyncOutOKBtn=null;
                              WebElement SyncOutCancelBtn=null;
                              WebElement SyncOutAssets=null;
                              
                              //Page element Initialize method
                              private void initElements()
                              {
                                             SyncOutSelectAllBtn= driver.findElementByAccessibilityId("SyncOutSelectAllButton");
                                             SyncOutUsersChkbox=driver.findElementByAccessibilityId("SyncOutUsersCheckBox");
                                             SyncOutAdtChkbox=driver.findElementByAccessibilityId("SyncOutAuditCheckBox");
                              SyncOutEqpmntChkbox=driver.findElementByAccessibilityId("SyncOutEquipmentCheckBox");
                                     SyncOutTmpltChkbox=driver.findElementByAccessibilityId("SyncOutTemplateCheckBox");
                                             SyncOutOKBtn=driver.findElementByName("OK");
                                             SyncOutCancelBtn=driver.findElementByName("Cancel");
                                             SyncOutAssets=driver.findElementByAccessibilityId("SyncOutitemGridViewSmall");
                              }
                              
                              // Constructor for initializing the page elements
                              FM_SyncOutwithAsset() throws IOException
                              {
                                             super();
                                             initElements();
                              }
                              
                              // Release memory
                              public void resetWebElements()
                              {
                                             SyncOutSelectAllBtn=null;
                                             SyncOutUsersChkbox=null;
                                             SyncOutAdtChkbox=null;
                                             SyncOutEqpmntChkbox=null;
                                             SyncOutTmpltChkbox=null;
                                             SyncOutOKBtn=null;
                                             SyncOutCancelBtn=null;
                                             SyncOutAssets=null;
                              }
                              
                              /*----------------------
                              Methods of SyncOut Select Page
                              ------------------------*/
                              
                              public boolean Is_SyncOutSelectAllBtnVisible()
                              {
                                             return IsElementVisibleStatus(SyncOutSelectAllBtn);
                              }
                              
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
                              
                              public boolean Is_SyncOutOKBtnEnabled()
                              {
                                             return IsElementEnabledStatus(SyncOutOKBtn);
                              }
                              
                              public void click_OKbtn() throws InterruptedException
                              {
                                             Thread.sleep(500);
                                             clickOn(SyncOutOKBtn); 
                              }
                              
                              public MainHubPage ClickBackButn() throws IOException {
                                             WebElement BackUMBtn = driver.findElementByAccessibilityId("BackButton");
                                             clickOn(BackUMBtn);
                                             return new MainHubPage();
                              }
                              
                              public void click_okbtn() throws InterruptedException
                              {
                                             Thread.sleep(500);
                                             clickOn(SyncOutOKBtn);
                                                            
                              }

}
