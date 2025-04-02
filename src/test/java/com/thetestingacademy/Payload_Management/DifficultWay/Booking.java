package com.thetestingacademy.Payload_Management.DifficultWay;

/*
    {
    "firstname" : "Riya",
    "lastname" : "Dash",
    "totalprice" : 2500,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2025-03-07",
        "checkout" : "2025-03-09"
    },
    "additionalneeds" : "Breakfast"
}
     */
public class Booking {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additioalneeds;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditioalneeds() {
        return additioalneeds;
    }

    public void setAdditioalneeds(String additioalneeds) {
        this.additioalneeds = additioalneeds;
    }
}
