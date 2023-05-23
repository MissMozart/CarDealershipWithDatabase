package com.yearup.cardealership;

import java.time.LocalDate;

public class LeaseContract extends Contract {
    public LeaseContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    @Override
    String getPersistanceString() {
        return null;
    }

    @Override
    double getTotalPrice() {
        double vehiclePrice = 0;
        double totalPrice = 0;
        vehiclePrice = vehicleSold.getPrice();
        totalPrice = vehiclePrice + (vehiclePrice * 0.07);
        return totalPrice;
    }

    @Override
    double getMonthlyPayment() {
        return monthlyPayment;
    }
}
