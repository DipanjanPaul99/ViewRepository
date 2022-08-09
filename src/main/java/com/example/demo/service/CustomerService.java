package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.helper.Helper;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void save(MultipartFile file) throws IOException {
        if(Helper.checkExcelFormat(file)) {
            List<Customer> customers = Helper.convertExcelToListOfCustomer(file.getInputStream());
            customerRepository.saveAll(customers);
        } else if(Helper.checkCsvFormat(file)) {
            List<Customer> customer = Helper.convertCsvToListOfCustomer(file.getInputStream());
            customerRepository.saveAll(customer);
        }
    }

    public List<Customer> getAllProducts() {
        return customerRepository.findAll();
    }
}
