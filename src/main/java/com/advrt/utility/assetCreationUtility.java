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

public class assetCreationUtility extends BaseClass {

	public assetCreationUtility() throws IOException {
		super();
	}

	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/src/test/resources/TestData/"
			+ "AssetNameTestData.xlsx";

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
	// ASSET Creation module related Test Data reference
	@DataProvider(name = "ASST03")
	public static Object[][] getAstNameInvalidTestData() {

		// "AstName" sheet is the sheet name from which Data related to Asset Name field
		// is read
		String sheetName = "ASST03";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST02")
	public static Object[][] getAstNameValidTestData() {
		String sheetName = "ASST02";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST06")
	public static Object[][] getEqpIDinValidTestData() {
		String sheetName = "ASST06";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST05")
	public static Object[][] getEqpIDValidTestData() {
		String sheetName = "ASST05";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST10")
	public static Object[][] getAstTypeInValidTestData() {
		String sheetName = "ASST10";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST09")
	public static Object[][] getAstTypeValidTestData() {
		String sheetName = "ASST09";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST14")
	public static Object[][] getAstMakerValidTestData() {
		String sheetName = "ASST14";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST15")
	public static Object[][] getAstMakerInValidTestData() {
		String sheetName = "ASST15";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST18")
	public static Object[][] getAstLocationValidTestData() {
		String sheetName = "ASST18";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST19")
	public static Object[][] getAstLocationInValidTestData() {
		String sheetName = "ASST19";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST21")
	public static Object[][] getAstMdlValidTestData() {
		String sheetName = "ASST21";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST22")
	public static Object[][] getAstMdlInValidTestData() {
		String sheetName = "ASST22";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST24")
	public static Object[][] getAstSizeValidTestData() {
		String sheetName = "ASST24";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST25")
	public static Object[][] getAstSizeInValidTestData() {
		String sheetName = "ASST25";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST28")
	public static Object[][] getAstSizeUnitValidTestData() {
		String sheetName = "ASST28";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST29")
	public static Object[][] getAstSizeUnitInValidTestData() {
		String sheetName = "ASST29";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST30b")
	public static Object[][] ASST30b() {
		String sheetName = "ASST30b";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST32a")
	public static Object[][] ASST32a() {
		String sheetName = "ASST32a";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST32b")
	public static Object[][] ASST32b() {
		String sheetName = "ASST32b";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST32c")
	public static Object[][] ASST32c() {
		String sheetName = "ASST32c";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST35")
	public static Object[][] ASST35() {
		String sheetName = "ASST35";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST36")
	public static Object[][] ASST36() {
		String sheetName = "ASST36";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST37")
	public static Object[][] ASST37() {
		String sheetName = "ASST37";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST38")
	public static Object[][] ASST38() {
		String sheetName = "ASST38";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST39")
	public static Object[][] ASST39() {
		String sheetName = "ASST39";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST41")
	public static Object[][] ASST41() {
		String sheetName = "ASST41";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST42")
	public static Object[][] ASST42() {
		String sheetName = "ASST42";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "AssetAllData")
	public static Object[][] AssetAllData() {
		String sheetName = "AssetAllData";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST49")
	public static Object[][] ASST49() {
		String sheetName = "ASST49";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASSTHB006a")
	public static Object[][] ASSTHB006a() {
		String sheetName = "ASSTHB006a";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASSTHB006b")
	public static Object[][] ASSTHB006b() {
		String sheetName = "ASSTHB006b";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASSTHB008")
	public static Object[][] ASSTHB008() {
		String sheetName = "ASSTHB008";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASSTHB012a")
	public static Object[][] ASSTHB012a() {
		String sheetName = "ASSTHB012a";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASSTHB012b")
	public static Object[][] ASSTHB012b() {
		String sheetName = "ASSTHB012b";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "testAsset")
	public static Object[][] testAsset() {
		String sheetName = "testAsset";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST024_A")
	public static Object[][] ASST024_A() {
		String sheetName = "ASST024_A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST024_B")
	public static Object[][] ASST024_B() {
		String sheetName = "ASST024_B";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST024_D")
	public static Object[][] ASST024_D() {
		String sheetName = "ASST024_D";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST024_E")
	public static Object[][] ASST024_E() {
		String sheetName = "ASST024_E";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST001B")
	public static Object[][] ASST001B() {
		String sheetName = "ASST001B";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST025STP")
	public static Object[][] ASST025STP() {
		String sheetName = "ASST025STP";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	// ASST026STP
	@DataProvider(name = "ASST026STP")
	public static Object[][] ASST026STP() {
		String sheetName = "ASST026STP";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST027STP")
	public static Object[][] ASST027STP() {
		String sheetName = "ASST027STP";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "ASST009")
	public static Object[][] ASST009() {
		String sheetName = "ASST009";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "maxASST")
	public static Object[][] maxASST() {
		String sheetName = "maxASST";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST016STP")
	public static Object[][] ASST016STP() {
		String sheetName = "ASST016STP";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST037")
	public static Object[][] ASST037() {
		String sheetName = "ASST037";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CA002")
	public static Object[][] CA002() {
		String sheetName = "CA002";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CA004")
	public static Object[][] CA004() {
		String sheetName = "CA004";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CA005")
	public static Object[][] CA005() {
		String sheetName = "CA005";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CA007")
	public static Object[][] CA007() {
		String sheetName = "CA007";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CA008")
	public static Object[][] CA008() {
		String sheetName = "CA008";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CS002")
	public static Object[][] CS002() {
		String sheetName = "CS002";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CS013")
	public static Object[][] CS013() {
		String sheetName = "CS013";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CS012")
	public static Object[][] CS012() {
		String sheetName = "CS012";
		Object[][] data = getTestData(sheetName);
		return data;
	}
}
