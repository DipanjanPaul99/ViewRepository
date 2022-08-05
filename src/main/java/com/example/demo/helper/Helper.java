package com.example.demo.helper;

import com.example.demo.entity.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;

        } else {
            return false;
        }
    }

    //convert excel to list of customer
    public static List<Customer> convertExcelToListOfCustomer(InputStream is) {
        List<Customer> list = new ArrayList<>();

        try {

            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("file");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                Customer c = new Customer();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            c.setId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            c.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            c.setAddress(cell.getStringCellValue());
                            break;
                        case 3:
                            c.setContactNo(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }
                list.add(c);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }


}


