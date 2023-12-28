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

public class Equipment_IOBoxPage extends BaseClass {

                

                Equipment_IOBoxPage Equipment_IOBoxPage;
                TestUtilities tu = new TestUtilities();
                // EquipmentHubPage Element definition
                WebElement IOBoxPage = null;
                WebElement Edit_equipment= null;
                WebElement DeleteEquipmentsButton = null;
                WebElement back = null;

                // Button1
                private void initElements() {
                                IOBoxPage = driver.findElementByAccessibilityId("EquipmentsNameTextBlock");
                                Edit_equipment = driver.findElementByAccessibilityId("EditEquipmentsButton");
                                DeleteEquipmentsButton = driver.findElementByAccessibilityId("DeleteEquipmentsButton");
                                back = driver.findElementByAccessibilityId("ArrowGlyph");
                                
                }

                Equipment_IOBoxPage() throws IOException {
                                super();
                                initElements();

                }

                // Release memory
                public void resetWebElements() {
                                IOBoxPage = null;
                                Edit_equipment = null;
                                DeleteEquipmentsButton = null;
                                back = null;
                                

                }

                // IRTD Details page Header is presence...
                public boolean IOBoxPage_HeaderPresence() {
                                return IsElementVisibleStatus(IOBoxPage);
                }


                public Edit_Equipmentpage click_Editequipment() throws IOException {
                                clickOn(Edit_equipment);
                                return new Edit_Equipmentpage();
                                
                }
                
                
                public void click_Editequipment_alrt() throws IOException {
                    clickOn(Edit_equipment);
                    
    }
                
                public void click_DeletEquipment() throws IOException {
                                clickOn(DeleteEquipmentsButton);
                }
                public EquipmentHubPage click_YesPop() throws IOException, InterruptedException
                {
                	tu.click_YesBtn_popup();
                	
                	return new EquipmentHubPage();
                }
                
                public boolean is_DeleteBtnVisible()
                {
                	return IsElementVisibleStatus(DeleteEquipmentsButton);
                }
                //find all the available details on IOBox Page
                public boolean is_EqpDetailsEnabled(String details)
                {
                List<WebElement> textField = driver.findElementsByClassName("TextBlock");
                boolean ele = false;
                for(WebElement item: textField)
                {
                	
					if(FetchText(item).equalsIgnoreCase(details))
                	IsElementEnabledStatus(item);
					ele =true;
					break;
                }
				return ele;
                }
                //click back
                public EquipmentHubPage click_Back() throws InterruptedException, IOException
                {
                	Thread.sleep(500);
                	clickOn(back);
                	return new EquipmentHubPage();
                }
}
