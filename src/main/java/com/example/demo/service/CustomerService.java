package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.NewCustomerClass;
import com.example.demo.helper.Helper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.NewCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private NewCustomerRepository newCustomerRepository;

    /**
     * To check the file  format for xlsx
     */
    public void saveExcelFormat(MultipartFile file) throws IOException {
        try {
            if (Helper.checkExcelFormat(file)) {
                List<Customer> customers = Helper.convertExcelToListOfCustomer(file.getInputStream());
                customerRepository.saveAll(customers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To check  file format for csv format
     */
    public void saveCsvFormat(MultipartFile file) {
        try {
            if (Helper.checkCsvFormat(file)) {
                List<Customer> customer = Helper.convertCsvToListOfCustomer(file.getInputStream());
                customerRepository.saveAll(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To get the list of customer imported from excel to database
     */
    public List<NewCustomerClass> getAllCustomer() {
        return newCustomerRepository.findAll();
    }
}
