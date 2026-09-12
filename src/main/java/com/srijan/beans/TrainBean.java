package com.srijan.beans;

import java.io.Serializable;

public class TrainBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private long tr_no;
    private String tr_name;
    private String from_stn;
    private String to_stn;
    private long seats;
    private double fare;

    public TrainBean() {}

    public TrainBean(long tr_no, String tr_name, String from_stn, String to_stn, long seats, double fare) {
        this.tr_no = tr_no;
        this.tr_name = tr_name;
        this.from_stn = from_stn;
        this.to_stn = to_stn;
        this.seats = seats;
        this.fare = fare;
    }

    public long getTr_no() {
        return tr_no;
    }

    public void setTr_no(long tr_no) {
        this.tr_no = tr_no;
    }

    public String getTr_name() {
        return tr_name;
    }

    public void setTr_name(String tr_name) {
        this.tr_name = tr_name;
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

    public long getSeats() {
        return seats;
    }

    public void setSeats(long seats) {
        this.seats = seats;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}
