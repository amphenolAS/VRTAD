package com.advrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.advrt.base.BaseClass;

public class SensorsInformationPage extends BaseClass {

	// Calculation page element variable declaration definition
	WebElement SensorsinfoTitle = null;
	WebElement back_Btn = null;
	WebElement NextButton = null;

	private void initializeEelements() {
		SensorsinfoTitle = driver.findElementByName("Sensors Information");
		back_Btn = driver.findElementByAccessibilityId("PreviousButton");
		NextButton = driver.findElementByAccessibilityId("NextButton");
	}

	SensorsInformationPage() throws IOException {
		super();
		initializeEelements();
	}

	// Release memory
	public void resetWebElements() {
		SensorsinfoTitle = null;
		back_Btn = null;
		NextButton = null;
	}

// Check the presence of Mapping Sensors Station header title

	public boolean SensorinfoTitle_state() {
		return IsElementVisibleStatus(SensorsinfoTitle);
	}

	// click on back btn
	public void click_back_Btn() throws InterruptedException {
		clickOn(back_Btn);
		Thread.sleep(2000);
	}

	// click on Select All check box
	public void sensorAutoMao_operation() throws InterruptedException {
		WebElement SelectAll_checkbox = driver.findElementByName("Select All");
		clickOn(SelectAll_checkbox);
		Thread.sleep(2000);
		WebElement GroupSelectionOKBtn = driver.findElementByAccessibilityId("GroupSelectionOKBtn");
		clickOn(GroupSelectionOKBtn);
		Thread.sleep(2000);
	}

	// click on program loggers
	public ProgramLoggersPage click_NextButton_withUnmappedSensors() throws IOException, InterruptedException {
		clickOn(NextButton);
		Thread.sleep(3000);		
		try {
			WebElement Yes_btn = driver.findElementByName("Yes");
			clickOn(Yes_btn);
			Thread.sleep(5000);	
		} catch (Exception e) {
			System.out.println("No Alert message on Sensor count difference while mapping sensors");
		}
		Thread.sleep(2000);	
		return new ProgramLoggersPage();
	}

}
