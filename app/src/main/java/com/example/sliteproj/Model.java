package com.example.sliteproj;

public class Model {

    String id, image, name, date, time, type,notes,cnumber,cvc,edate,amount,paid,addTimeStamp, updateTimeStamp;

    public Model(String id, String image, String name, String date, String time, String type, String notes, String cnumber, String cvc, String edate, String amount, String paid, String addTimeStamp, String updateTimeStamp) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.date = date;
        this.time = time;
        this.type = type;
        this.notes = notes;
        this.cnumber = cnumber;
        this.cvc = cvc;
        this.edate = edate;
        this.amount = amount;
        this.paid = paid;
        this.addTimeStamp = addTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getCvc() {
        return cvc;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getEdate() {
        return edate;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getPaid() { return paid; }


    public String getAddTimeStamp() {
        return addTimeStamp;
    }

    public void setAddTimeStamp(String addTimeStamp) {
        this.addTimeStamp = addTimeStamp;
    }

    public String getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(String updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }
}
