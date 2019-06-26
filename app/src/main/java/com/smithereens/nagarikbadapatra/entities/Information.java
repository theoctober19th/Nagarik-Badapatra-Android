package com.smithereens.nagarikbadapatra.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Information {
    @SerializedName("id")
    Integer id;
    @SerializedName("code")
    String code;
    @SerializedName("description")
    String description;
    @SerializedName("reqDocList")
    List<String> reqDocList;
    @SerializedName("time")
    Integer time;
    @SerializedName("timeUnit")
    String timeUnit;
    @SerializedName("cost")
    Long cost;
    @SerializedName("referDepartment")
    String referDepartment;
    @SerializedName("referPerson")
    String referPerson;
    @SerializedName("referPersonContact")
    String referPersonContact;
    @SerializedName("complaintOfficer")
    String complaintOfficer;
    @SerializedName("remarks")
    String remarks;

    public Information() {
    }

    public Information(int id, String code, String description, List<String> reqDocList, int time, String timeUnit, long cost, String referDepartment, String referPerson, String referPersonContact, String complaintOfficer, String remarks) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.reqDocList = reqDocList;
        this.time = time;
        this.timeUnit = timeUnit;
        this.cost = cost;
        this.referDepartment = referDepartment;
        this.referPerson = referPerson;
        this.referPersonContact = referPersonContact;
        this.complaintOfficer = complaintOfficer;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getReqDocList() {
        return reqDocList;
    }

    public void setReqDocList(List<String> reqDocList) {
        this.reqDocList = reqDocList;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getReferDepartment() {
        return referDepartment;
    }

    public void setReferDepartment(String referDepartment) {
        this.referDepartment = referDepartment;
    }

    public String getReferPerson() {
        return referPerson;
    }

    public void setReferPerson(String referPerson) {
        this.referPerson = referPerson;
    }

    public String getReferPersonContact() {
        return referPersonContact;
    }

    public void setReferPersonContact(String referPersonContact) {
        this.referPersonContact = referPersonContact;
    }

    public String getComplaintOfficer() {
        return complaintOfficer;
    }

    public void setComplaintOfficer(String complaintOfficer) {
        this.complaintOfficer = complaintOfficer;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
