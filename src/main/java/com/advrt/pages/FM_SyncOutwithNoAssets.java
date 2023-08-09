package com.advrt.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.advrt.base.BaseClass;

public class FM_SyncOutwithNoAssets extends BaseClass{
               
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
                                     SyncOutTmpltChkbox=driver.findElementByAccessibilityId("SyncOutTemplateCheckBox");
                                             SyncOutOKBtn=driver.findElementByName("OK");
                                             SyncOutCancelbtn=driver.findElementByName("Cancel");
                                             SyncOutNoAssets=driver.findElementByClassName("TextBlock");
                                             //SyncOutAssets=driver.findElementByAccessibilityId("SyncOutitemGridViewSmall");
                              }
                              
                              // Constructor for initializing the page elements
                              FM_SyncOutwithNoAssets() throws IOException
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
                              
                              public void click_OKbtn() throws InterruptedException
                              {
                                             Thread.sleep(8000);
                                             clickOn(SyncOutOKBtn);                               
                                             //((JavascriptExecutor)driver).executeScript("arguments[1].click();",SyncOutOKBtn );
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
                              public void click_onTemplate() throws InterruptedException
                              {
                                             Thread.sleep(500);
                                             clickOn(SyncOutTmpltChkbox);
                              }
               
                              
               
}
