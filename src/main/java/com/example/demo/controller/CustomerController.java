package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.NewCustomerClass;
import com.example.demo.helper.Helper;
import com.example.demo.message.ResponseMessage;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * This is a Main controller
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/upload")
    public ResponseEntity<ResponseMessage> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String data = "";
        if (Helper.checkExcelFormat(file)) {
            try {
                customerService.saveExcelFormat(file);
                data = "Xlsx File is uploaded and data is saved to db: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(data));
            } catch (Exception e) {
                data = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(data));
            }
        }
        data = "Please upload a Xlsx file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(data));
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String data = "";
        if (Helper.checkCsvFormat(file)) {
            try {
                customerService.saveCsvFormat(file);
                data = "Csv File is uploaded and data is saved to db: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(data));
            } catch (Exception e) {
                data = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(data));
            }
        }
        data = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(data));
    }

    @GetMapping("/customer")
    public List<NewCustomerClass> getAllProduct() {
        return customerService.getAllCustomer();
    }
}