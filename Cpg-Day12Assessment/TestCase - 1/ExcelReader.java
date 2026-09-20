
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    public static String getData(String sheetName, int rowNumber,
                                 int columnNumber) {

        Workbook workbook = null;

        try {
            InputStream inputStream = null;

            // First, try to read from src/test/resources
            inputStream = ExcelReader.class.getClassLoader()
                    .getResourceAsStream("TestData.xlsx");

            // If the resource is not found, use the project path
            if (inputStream == null) {
                File excelFile = new File(
                        "src/test/resources/TestData.xlsx");

                if (!excelFile.exists()) {
                    throw new RuntimeException(
                            "Excel file not found at: "
                            + excelFile.getAbsolutePath());
                }

                inputStream = new FileInputStream(excelFile);
            }

            workbook = WorkbookFactory.create(inputStream);

            if (workbook.getSheet(sheetName) == null) {
                throw new RuntimeException(
                        "Sheet not found: " + sheetName);
            }

            DataFormatter formatter = new DataFormatter();

            String data = formatter.formatCellValue(
                    workbook.getSheet(sheetName)
                            .getRow(rowNumber)
                            .getCell(columnNumber)
            );

            inputStream.close();

            return data;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to read Excel data. Check file path, "
                    + "sheet name, row and column.", e);

        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}