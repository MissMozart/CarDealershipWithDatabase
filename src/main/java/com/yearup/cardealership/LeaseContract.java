package com.yearup.cardealership;

import java.time.LocalDate;

public class LeaseContract extends Contract {

    public LeaseContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    @Override
    String getPersistanceString() {
        return String.format("LEASE|%s|%s|%s|%s|%.2f|%.2f|%.2f|%.2f|%.2f\n", getDate(), getCustomerName(), getCustomerEmail(),
                getVehicleSold().getVin(), getTotalPrice(), expectedEndingValue(), getLeaseFee(), endingValueAndLeaseFee(), getMonthlyPayment());
    }

    double endingValueAndLeaseFee() {
        double vehiclePrice = vehicleSold.getPrice();
        double endingValue = vehiclePrice / 2;
        double leaseFee = vehiclePrice * 0.07;
        return endingValue + leaseFee;
    }
    double expectedEndingValue() {
        double vehiclePrice = vehicleSold.getPrice();
        double endingValue = vehiclePrice / 2;
        return endingValue;
    }
    double getLeaseFee() {
        double vehiclePrice = vehicleSold.getPrice();
        double leaseFee = vehiclePrice * 0.07;
        return leaseFee;
    }
    @Override
    double getTotalPrice() {
        double vehiclePrice = vehicleSold.getPrice();
        return vehiclePrice;
    }

    @Override
    double getMonthlyPayment() {
        double vehiclePrice = vehicleSold.getPrice();
        double p = vehiclePrice;
        double r = 0.04;
        double t = 3;
        double n = 12;
        double payment = 0;

        double rOverN = r / n;
        double numerator = p * rOverN;
        double onePlusROverN = 1 + rOverN;
        double power = -t * n;
        double denominator = 1 - Math.pow(onePlusROverN, power);
        payment = numerator / denominator;

        return payment;
    }
}

