package com.srijan.beans;

import java.io.Serializable;

public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mailId;
    private String pWord;
    private String fName;
    private String lName;
    private String addr;
    private long phNo;

    public UserBean() {}

    public UserBean(String mailId, String pWord, String fName, String lName, String addr, long phNo) {
        this.mailId = mailId;
        this.pWord = pWord;
        this.fName = fName;
        this.lName = lName;
        this.addr = addr;
        this.phNo = phNo;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getpWord() {
        return pWord;
    }

    public void setpWord(String pWord) {
        this.pWord = pWord;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public long getPhNo() {
        return phNo;
    }

    public void setPhNo(long phNo) {
        this.phNo = phNo;
    }
}
