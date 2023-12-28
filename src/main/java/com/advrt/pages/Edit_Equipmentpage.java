/**
* @author ruchika.behura
*
*/

package com.advrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.advrt.utility.TestUtilities;
import com.advrt.base.BaseClass;

public class Edit_Equipmentpage extends BaseClass {

                

                Edit_Equipmentpage Edit_Equipmentpage;
                TestUtilities tu = new TestUtilities();
                // EquipmentHubPage Element definition
                WebElement Edit_EqipmentHeader = null;           
                WebElement ModelNumber = null;
                WebElement EquipmentSaveButton = null;
                WebElement EquipmentTypeUMDropDown = null;
                WebElement EqpmCancelBtn = null;
                WebElement backBtn = null;

                // Button1
                private void initElements() {
                                Edit_EqipmentHeader = driver.findElementByAccessibilityId("EquipmentTextBlock");
                                ModelNumber = driver.findElementByAccessibilityId("ModelNumberTextBox");
                                EquipmentSaveButton = driver.findElementByAccessibilityId("SaveButton");
                                EquipmentTypeUMDropDown = driver.findElementByAccessibilityId("EquipmentTypeComboBox");
                                EqpmCancelBtn = driver.findElementByAccessibilityId("CancelButton");
                                EquipmentSaveButton = driver.findElementByName("Save");
                                backBtn = driver.findElementByAccessibilityId("ArrowGlyph");
                }

                Edit_Equipmentpage() throws IOException {
                                super();
                                initElements();

                }

                // Release memory
                public void resetWebElements() {
                                Edit_EqipmentHeader = null;
                                ModelNumber = null;
                                EquipmentTypeUMDropDown = null;
                                EqpmCancelBtn = null;
                                backBtn = null;

                }
//ModelNumberTextBox
                public void enterNewModelNumber(String MNum) {
                                ClearText(ModelNumber);
                                enterText(ModelNumber, MNum);
                }
                
                // Click on Save button
                                public void ClickSaveButton() throws InterruptedException {
                                                clickOn(EquipmentSaveButton);
                                                Thread.sleep(1000);
                                }
                                public void EqipCreation_WithoutClickingSaveBtn(String Etype, String EMN) throws InterruptedException {
                            		select_EquipmentType(Etype);
                            		enterNewModelNumber(EMN);
                            	}
    //fetching text from EquipmentTypeUMDropDown
    public String getText_EqpTypeDD()
    {
    	return FetchText(EquipmentTypeUMDropDown);
    }
    public String getDate_CaliDueDate()
	{
		List<WebElement> CaliDueDate = driver.findElementsByAccessibilityId("PART_PickerButton");
		return FetchText(CaliDueDate.get(1));
	}
                                public void select_EquipmentType(String Etype) throws InterruptedException {
                            		// System.out.println(Etype);
                            		clickOn(EquipmentTypeUMDropDown);
                            		Thread.sleep(500);
                            		
                            		List<WebElement> dropDwonValues = driver.findElementByAccessibilityId("EquipmentTypeComboBox")
                            				//	List<WebElement> dropDwonValues = driver.findElementByName("Select")
                            							.findElements(By.className("ComboBoxItem"));
                            				Actions act = new Actions(driver);
                            					for(WebElement option: dropDwonValues)
                            					{
                            						
                            						if(FetchText(option).equalsIgnoreCase(Etype))
                            						{
                            							clickOn(option);
                            							break;
                            						}
                            						else
                            						{
                            							act.sendKeys(Keys.ARROW_DOWN).build().perform();
                            						}
                            					}
     
                                }
      
      //click back button
      public Equipment_IOBoxPage click_Back() throws IOException
      {
    	  clickOn(backBtn);
    	  return new Equipment_IOBoxPage();
      }
      //fecth text from Caliberation Due Date
      public void click_CaliDueDate()
       {
      		List<WebElement> CaliDueDate = driver.findElementsByAccessibilityId("PART_PickerButton");
        	clickOn(CaliDueDate.get(1));
        }
      public void select_LastDate()
  	{
  		WebElement selectBtn = driver.findElementByAccessibilityId("PART_SelectorOKButton");//PART_Popup
  		clickOn(selectBtn);
  	}
      //click on cancel button
      public void click_Cancel()
       {
          clickOn(EqpmCancelBtn);
       }                            
}
