package com.example.harshil.hosteldata;

class Details {
    private String roomNo,enrollNo,fullName,branch,year,email,contactNo,eContactNo,address;

    Details(){

    }

    public Details(String roomNo, String enrollNo, String fullName, String branch, String year, String email, String contactNo, String eContactNo, String address) {
        this.roomNo = roomNo;
        this.enrollNo = enrollNo;
        this.fullName = fullName;
        this.branch = branch;
        this.year = year;
        this.email = email;
        this.contactNo = contactNo;
        this.eContactNo = eContactNo;
        this.address = address;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getEnrollNo() {
        return enrollNo;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBranch() {
        return branch;
    }

    public String getYear() {
        return year;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String geteContactNo() {
        return eContactNo;
    }

    public String getAddress() {
        return address;
    }
}
