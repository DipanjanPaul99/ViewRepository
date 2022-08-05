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

    public void save(MultipartFile file) {

        try {
            List<Customer> customers = Helper.convertExcelToListOfCustomer(file.getInputStream());
            this.customerRepository.saveAll(customers);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllProducts() {
        return this.customerRepository.findAll();
    }
}
