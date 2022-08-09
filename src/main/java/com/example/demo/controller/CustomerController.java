package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.helper.Helper;
import com.example.demo.message.ResponseMessage;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/upload")
    public ResponseEntity<ResponseMessage> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";
        if (Helper.checkExcelFormat(file)) {
            try {
                customerService.saveExcelFormat(file);
                message = "Xlxs File is uploaded and data is saved to db: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a xlxs file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (Helper.checkCsvFormat(file)) {
            try {
                customerService.saveCsvFormat(file);
                message = "Csv File is uploaded and data is saved to db: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @GetMapping("/customer")
    public List<Customer> getAllProduct() {
        return customerService.getAllCustomer();
    }
}