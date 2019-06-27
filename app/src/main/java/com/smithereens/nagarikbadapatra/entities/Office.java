package com.smithereens.nagarikbadapatra.entities;

import com.google.gson.annotations.SerializedName;

public class Office {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String  name;

    @SerializedName("header1")
    String header1;

    @SerializedName("header2")
    String header2;

    @SerializedName ("header3")
    String header3;

    @SerializedName("header4")
    String header4;

    @SerializedName("location")
    String location;

    @SerializedName("contact")
    String contact;

    @SerializedName("headPersonnel")
    String headPersonnel;

    @SerializedName ("headPersonnelContact")
    String headPersonnelContact;

    @SerializedName("onHour")
    String onHour;

    @SerializedName("informationOfficer")
    String informationOfficer;

    @SerializedName ("informationDeskContact")
    String informationDeskContact;

    public Office() {
    }

    public Office(Integer id, String name, String header1, String header2, String header3, String header4, String location, String contact, String headPersonnel, String headPersonnelContact, String onHour, String informationOfficer, String informationDeskContact) {
        this.id = id;
        this.name = name;
        this.header1 = header1;
        this.header2 = header2;
        this.header3 = header3;
        this.header4 = header4;
        this.location = location;
        this.contact = contact;
        this.headPersonnel = headPersonnel;
        this.headPersonnelContact = headPersonnelContact;
        this.onHour = onHour;
        this.informationOfficer = informationOfficer;
        this.informationDeskContact = informationDeskContact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader1() {
        return header1;
    }

    public void setHeader1(String header1) {
        this.header1 = header1;
    }

    public String getHeader2() {
        return header2;
    }

    public void setHeader2(String header2) {
        this.header2 = header2;
    }

    public String getHeader3() {
        return header3;
    }

    public void setHeader3(String header3) {
        this.header3 = header3;
    }

    public String getHeader4() {
        return header4;
    }

    public void setHeader4(String header4) {
        this.header4 = header4;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHeadPersonnel() {
        return headPersonnel;
    }

    public void setHeadPersonnel(String headPersonnel) {
        this.headPersonnel = headPersonnel;
    }

    public String getHeadPersonnelContact() {
        return headPersonnelContact;
    }

    public void setHeadPersonnelContact(String headPersonnelContact) {
        this.headPersonnelContact = headPersonnelContact;
    }

    public String getOnHour() {
        return onHour;
    }

    public void setOnHour(String onHour) {
        this.onHour = onHour;
    }

    public String getInformationOfficer() {
        return informationOfficer;
    }

    public void setInformationOfficer(String informationOfficer) {
        this.informationOfficer = informationOfficer;
    }

    public String getInformationDeskContact() {
        return informationDeskContact;
    }


    public void setInformationDeskContact(String informationDeskContact) {
        this.informationDeskContact = informationDeskContact;
    }
}
