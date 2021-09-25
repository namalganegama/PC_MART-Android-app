package com.pcmart.deliverytest;

public class Delivery_Model {

    private int id,phone,zipcode,quentity;
    private String name,  email, address;


    public Delivery_Model(){

    }

    public Delivery_Model(int id, String name, int phone, String email, String address, int zipcode, int quentity) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.zipcode = zipcode;
        this.quentity = quentity;

    }

    public Delivery_Model(String name, int phone, String email, String address, int zipcode, int quentity) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.zipcode = zipcode;
        this.quentity = quentity;

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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getQuentity() { return quentity; }

    public void setQuentity(int quentity) {
        this.quentity = quentity;
    }


}
