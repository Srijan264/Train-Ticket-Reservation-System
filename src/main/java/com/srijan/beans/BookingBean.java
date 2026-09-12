package com.srijan.beans;

import java.io.Serializable;

public class BookingBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String transactionId;
    private String mailId;
    private String fromStation;
    private String toStation;
    private String date;
    private long seats;
    private double amount;
    private long trainNo;

    public BookingBean() {}

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getSeats() {
        return seats;
    }

    public void setSeats(long seats) {
        this.seats = seats;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(long trainNo) {
        this.trainNo = trainNo;
    }
}
