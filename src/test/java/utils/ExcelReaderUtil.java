package utils;

import models.InvalidLoginModel;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReaderUtil {

    public static List<InvalidLoginModel> readInvalidLoginData(String filePath) {
        List<InvalidLoginModel> list = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                String email = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().trim();
                String password = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().trim();
                String expectedError = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().trim();

                list.add(new InvalidLoginModel(email, password, expectedError));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
