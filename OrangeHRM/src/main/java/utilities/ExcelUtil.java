package utilities;

import java.io.FileInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtil {
	private static final Logger log = LogManager.getLogger(ExcelUtil.class);
	private static final String EXCEL_PATH = "src/test/resources/testdata/testdata.xlsx";

	@DataProvider(name = "loginData")
	public static Object[][] getLoginData() {
		return getData("Login");
	}

	@DataProvider(name = "adminData")
	public static Object[][] getAdminData() {
		return getData("Admin");
	}

	@DataProvider(name = "pimData")
	public static Object[][] getPimData() {
		return getData("PIM");
	}

	@DataProvider(name = "leaveData")
	public static Object[][] getLeaveData() {
		return getData("Leave");
	}

	public static Object[][] getData(String sheetName) {
		Object[][] data = null;
		try (FileInputStream fis = new FileInputStream(EXCEL_PATH); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				log.warn("Sheet " + sheetName + " is missing.");
				return new Object[0][0];
			}
			int rows = sheet.getPhysicalNumberOfRows();
			int cols = sheet.getRow(0).getPhysicalNumberOfCells();

			data = new Object[rows - 1][cols];

			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (sheet.getRow(i) != null && sheet.getRow(i).getCell(j) != null) {
						data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
					} else {
						data[i - 1][j] = "";
					}
				}
			}
			log.info("Extracted " + (rows - 1) + " rows from sheet: " + sheetName);
		} catch (Exception e) {
			log.error("Failed reading Excel Data for sheet: " + sheetName, e);
		}
		return data;
	}
}