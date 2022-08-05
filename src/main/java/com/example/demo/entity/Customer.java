package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private  String name;
    @Column(name = "address")
    private  String address;
    @Column(name = "contactno")
    private  String contactNo;

    public Customer(int id, String name, String address, String contactNo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
    }

    public Customer(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
