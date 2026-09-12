package com.srijan.beans;

import java.io.Serializable;

public class HistoryBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String transId;
    private String from_stn;
    private String to_stn;
    private String date;
    private String mailId;
    private long seats;
    private double amount;
    private long tr_no;

    public HistoryBean() {}

    public HistoryBean(String transId, String from_stn, String to_stn, String date, String mailId, long seats, double amount, long tr_no) {
        this.transId = transId;
        this.from_stn = from_stn;
        this.to_stn = to_stn;
        this.date = date;
        this.mailId = mailId;
        this.seats = seats;
        this.amount = amount;
        this.tr_no = tr_no;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getFrom_stn() {
        return from_stn;
    }

    public void setFrom_stn(String from_stn) {
        this.from_stn = from_stn;
    }

    public String getTo_stn() {
        return to_stn;
    }

    public void setTo_stn(String to_stn) {
        this.to_stn = to_stn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
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

    public long getTr_no() {
        return tr_no;
    }

    public void setTr_no(long tr_no) {
        this.tr_no = tr_no;
    }
}
