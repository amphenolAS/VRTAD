package com.advrt.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.advrt.base.BaseClass;

public class userManagementUtility extends BaseClass{
	
	public userManagementUtility() throws IOException {
		super();

	}
	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/src/test/resources/TestData/" + "UserManagementTestData.xlsx";

	static Workbook book;
	static Sheet sheet;

	// Read TestData from the Excel sheet
	public static Object[][] getTestData(String sheetName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(TestData_sheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(fis);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum() + "--------" + sheet.getRow(0).getLastCellNum());

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				// System.out.println(data[i][j]);
			}
		}
		return data;
	}

	// Data Providers
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//ADMIN module related Test Data reference
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	@DataProvider(name = "LOGIN_024")
	public static Object[][] LOGIN_024() {
		String sheetName = "LOGIN_024";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "tcADMN008")
	public static Object[][] ADMN008() {
		String sheetName = "tcADMN008";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "tcADMN009")
	public static Object[][] ADMN009() {
		String sheetName = "tcADMN009";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "tcADMN010")
	public static Object[][] ADMN010() {
		String sheetName = "tcADMN010";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "tcADMN011")
	public static Object[][] ADMN011() {
		String sheetName = "tcADMN011";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "tcADMN012")
	public static Object[][] ADMN012() {
		String sheetname = "tcADMN012";
		Object[][] data = getTestData(sheetname);
		return data;
	}

	@DataProvider(name = "tcADMN015")
	public static Object[][] ADMN015() {
		String sheetname = "tcADMN015";
		Object[][] data = getTestData(sheetname);
		return data;
	}
	
	@DataProvider(name = "tcADMN016")
	public static Object[][] ADMN016() {
		String sheetname = "tcADMN016";
		Object[][] data = getTestData(sheetname);
		return data;
	}
	
	@DataProvider(name = "tcADMN017")
	public static Object[][] ADMN017() {
		String sheetname = "tcADMN017";
		Object[][] data = getTestData(sheetname);
		return data;
	}
	
	@DataProvider(name = "tcADMN018")
	public static Object[][] ADMN018() {
		String sheetname = "tcADMN018";
		Object[][] data = getTestData(sheetname);
		return data;
	}
	

	@DataProvider(name = "tcADMN021")
	public static Object[][] ADMN021() {
		String sheetName = "tcADMN021";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "tcADMN022")
	public static Object[][] ADMN022() {
		String sheetname = "tcADMN022";
		Object[][] data = getTestData(sheetname);
		return data;
	}
	
	@DataProvider(name="tcADMN025")
	public static Object[][] ADMN025() {
		String sheetname = "tcADMN025";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	@DataProvider(name="tcADMN026")
	public static Object[][] ADMN026() {
		String sheetname = "tcADMN026";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	@DataProvider(name="tcADMN028")
	public static Object[][] ADMN028() {
		String sheetname = "tcADMN028";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	
	@DataProvider(name="tcADMN029")
	public static Object[][] ADMN029() {
		String sheetname = "tcADMN029";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	
	@DataProvider(name="tcADMN032")
	public static Object[][] ADMN032() {
		String sheetname = "tcADMN032";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	
	@DataProvider(name="ADMN034")
	public static Object[][] ADMN034() {
		String sheetname = "tcADMN034";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	
	@DataProvider(name = "ADMIN037F")
	public static Object[][] ADMIN037F() {
		String sheetName = "tcADMN037F";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN044")
	public static Object[][] ADMIN044() {
		String sheetName = "tcADMN044";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN045A")
	public static Object[][] ADMIN045A() {
		String sheetName = "tcADMN045A";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN045B")
	public static Object[][] ADMIN045B() {
		String sheetName = "tcADMN045B";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN062")
	public static Object[][] ADMIN062() {
		String sheetName = "tcADMN062";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN067A")
	public static Object[][] ADMIN067A() {
		String sheetName = "tcADMN067A";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN069F")
	public static Object[][] ADMIN069F() {
		String sheetName = "tcADMN069F";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN069G")
	public static Object[][] ADMIN069G() {
		String sheetName = "tcADMN069G";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMN070")
	public static Object[][] ADMN070() {
		String sheetName = "tcADMN070";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMN071")
	public static Object[][] ADMN071() {
		String sheetName = "tcADMN071";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN074")
	public static Object[][] ADMIN074() {
		String sheetName = "tcADMN074";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN082")
	public static Object[][] ADMIN082() {
		String sheetName = "tcADMN082";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN095")
	public static Object[][] ADMIN095() {
		String sheetName = "tcADMN095";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN099")
	public static Object[][] ADMIN099() {
		String sheetName = "tcADMN099";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN121")
	public static Object[][] ADMIN121() {
		String sheetName = "tcADMN121";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN126")
	public static Object[][] ADMIN126() {
		String sheetName = "tcADMN126";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN127")
	public static Object[][] ADMIN127() {
		String sheetName = "tcADMN127";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ADMIN128")
	public static Object[][] ADMIN128() {
		String sheetName = "tcADMN128";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="tcADMN131")
	public static Object[][] ADMN131() {
		String sheetname = "tcADMN131";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	
	@DataProvider(name = "SetupCreation_3")
	public static Object[][] SETUP_Creation() {
		String sheetName = "SetupCreation_3";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "maxuser")
	public static Object[][] maxuser() {
		String sheetName = "maxuser";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "maxuser2")
	public static Object[][] maxuser2() {
		String sheetName = "maxuser2";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	
}

