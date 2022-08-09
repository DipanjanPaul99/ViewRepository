package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.helper.Helper;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (Helper.checkExcelFormat(file)) {
            customerService.save(file);
            return ResponseEntity.ok(Map.of("message", "Excel File is uploaded and data is saved to db"));
        } else if (Helper.checkCsvFormat(file)) {
            customerService.save(file);
            return ResponseEntity.ok(Map.of("message", "Csv File is uploaded and data is saved to db"));
        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel FIle");
    }

    @GetMapping("/customer")
    public List<Customer> getAllProduct() {
        return customerService.getAllProducts();
    }
}