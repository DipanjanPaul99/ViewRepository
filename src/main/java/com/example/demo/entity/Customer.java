package com.example.demo.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
public class Customer {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private  String name;

    @Column
    @Embedded
    private  Address address;

    @Column(name = "contactno")
    private  String contactNo;

    public Customer() {
    }

    public Customer(int id, String name, Address address, String contactNo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
