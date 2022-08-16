package com.example.demo.helper;

import com.example.demo.entity.Address;
import com.example.demo.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    private Helper() {
    }

    /**
     *   Check that file is of Excel type or not
     */
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType != null && contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }

   /**
    *    Check that file is of csv type or not
    */
    public static boolean checkCsvFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType != null && contentType.equals("text/csv")) {
            return true;
        } else {
            return false;
        }
    }

    /**
    * Convert excel to list of customer
    */
    public static List<Customer> convertExcelToListOfCustomer(InputStream is) {
        List<Customer> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
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
                            c.setAddress(new ObjectMapper().readValue(cell.getStringCellValue(), Address.class));
                            break;
                        case 3:
                            c.setContactNo(String.valueOf(cell.getNumericCellValue()));
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

    public static List<Customer> convertCsvToListOfCustomer(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Customer> tutorials = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Customer tutorial = new Customer(
                        Integer.parseInt(csvRecord.get("id")),
                        csvRecord.get("name"),
                        new ObjectMapper().readValue(csvRecord.get("address"), Address.class),
                        csvRecord.get("contactNo")
                );
                tutorials.add(tutorial);
            }
            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}