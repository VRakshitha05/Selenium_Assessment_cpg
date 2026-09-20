package utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    public static String getData(
            String sheetName, int rowNumber, int columnNumber) {

        String filePath = "src/test/resources/TestData.xlsx";

        try (FileInputStream input =
                     new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(input)) {

            if (workbook.getSheet(sheetName) == null) {
                throw new RuntimeException(
                        "Sheet not found: " + sheetName);
            }

            DataFormatter formatter = new DataFormatter();

            return formatter.formatCellValue(
                    workbook.getSheet(sheetName)
                            .getRow(rowNumber)
                            .getCell(columnNumber));

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to read Excel file from: "
                    + new File(filePath).getAbsolutePath(), e);
        }
    }
}
