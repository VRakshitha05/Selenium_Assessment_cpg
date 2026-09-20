package utilities;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    private static final String FILE_NAME =
            "MyInfoData.xlsx";


    public static String getData(
            String sheetName,
            int rowNumber,
            int columnNumber) {


        System.out.println(
                "----------------------------------------");

        System.out.println(
                "ExcelReader started");

        System.out.println(
                "File: " + FILE_NAME);

        System.out.println(
                "Sheet: " + sheetName);

        System.out.println(
                "Row: " + rowNumber);

        System.out.println(
                "Column: " + columnNumber);


        try {

            /*
             * Excel file location:
             *
             * src/test/resources/MyInfoData.xlsx
             */

            InputStream inputStream =
                    ExcelReader.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    FILE_NAME);


            if (inputStream == null) {

                throw new RuntimeException(
                        "Excel file not found: "
                        + FILE_NAME
                        + "\nExpected location:"
                        + " src/test/resources/"
                        + FILE_NAME);
            }


            System.out.println(
                    "Excel file found");


            Workbook workbook =
                    WorkbookFactory.create(
                            inputStream);


            System.out.println(
                    "Workbook opened successfully");


            Sheet sheet =
                    workbook.getSheet(
                            sheetName);


            if (sheet == null) {

                StringBuilder sheets =
                        new StringBuilder();


                for (int i = 0;
                     i < workbook.getNumberOfSheets();
                     i++) {

                    if (i > 0) {
                        sheets.append(", ");
                    }

                    sheets.append(
                            workbook.getSheetName(i));
                }


                workbook.close();

                inputStream.close();


                throw new RuntimeException(
                        "Sheet '" + sheetName
                        + "' not found.\n"
                        + "Available sheets: "
                        + sheets);
            }


            System.out.println(
                    "Sheet found: "
                    + sheetName);


            /*
             * We use 1-based row/column numbers.
             *
             * Row 1 = Excel header
             * Row 2 = test data
             *
             * Column 1 = A
             * Column 2 = B
             * etc.
             */

            int rowIndex =
                    rowNumber - 1;

            int columnIndex =
                    columnNumber - 1;


            Row row =
                    sheet.getRow(rowIndex);


            if (row == null) {

                workbook.close();

                inputStream.close();

                throw new RuntimeException(
                        "Row "
                        + rowNumber
                        + " does not exist in sheet "
                        + sheetName);
            }


            Cell cell =
                    row.getCell(columnIndex);


            if (cell == null) {

                workbook.close();

                inputStream.close();

                throw new RuntimeException(
                        "Cell does not exist."
                        + "\nSheet: "
                        + sheetName
                        + "\nRow: "
                        + rowNumber
                        + "\nColumn: "
                        + columnNumber);
            }


            DataFormatter formatter =
                    new DataFormatter();


            String value =
                    formatter.formatCellValue(
                            cell);


            workbook.close();

            inputStream.close();


            if (value == null ||
                value.trim().isEmpty()) {

                throw new RuntimeException(
                        "Cell is empty."
                        + "\nSheet: "
                        + sheetName
                        + "\nRow: "
                        + rowNumber
                        + "\nColumn: "
                        + columnNumber);
            }


            value =
                    value.trim();


            System.out.println(
                    "Excel value: " + value);


            System.out.println(
                    "ExcelReader completed successfully");


            System.out.println(
                    "----------------------------------------");


            return value;


        } catch (Exception e) {

            System.out.println(
                    "******** EXCEL READER ERROR ********");

            e.printStackTrace();

            System.out.println(
                    "**************************************");


            throw new RuntimeException(
                    "ExcelReader failed: "
                    + e.getMessage(),
                    e);
        }
    }
}