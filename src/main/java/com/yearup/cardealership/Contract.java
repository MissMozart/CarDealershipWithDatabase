package com.yearup.cardealership;


import java.time.LocalDate;

abstract class Contract {
    LocalDate Date;
    String customerName;
    String customerEmail;
    Vehicle vehicleSold;


    public Contract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        Date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public LocalDate getDate() {
        return Date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    abstract String getPersistanceString();

    abstract double getTotalPrice();

    abstract double getMonthlyPayment();

}