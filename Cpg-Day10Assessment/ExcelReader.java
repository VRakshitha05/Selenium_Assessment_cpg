
package utilities;

import java.io.InputStream;
import org.apache.poi.ss.usermodel.*;

public class ExcelReader {

    public static String getData(
            String sheetName, int rowNum, int colNum) {

        try (InputStream input = ExcelReader.class
                .getClassLoader()
                .getResourceAsStream("TestData.xlsx")) {

            if (input == null) {
                throw new RuntimeException(
                        "TestData.xlsx not found");
            }

            Workbook workbook = WorkbookFactory.create(input);

            Sheet sheet = workbook.getSheet(sheetName);

            DataFormatter formatter = new DataFormatter();

            String data = formatter.formatCellValue(
                    sheet.getRow(rowNum).getCell(colNum));

            workbook.close();

            return data;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error reading Excel data", e);
        }
    }
}