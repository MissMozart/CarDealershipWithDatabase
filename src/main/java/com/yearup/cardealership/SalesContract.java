package com.yearup.cardealership;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private int processingFee;
    private double salesTax;
    private boolean finance;

    public SalesContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;
    }

    public SalesContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    @Override
    String getPersistanceString() {
        return String.format("SALE|%s|%s|%s|%s|%.2f|100|%d|%.2f|%s|%.2f", getDate(), getCustomerName(), getCustomerEmail(),
                getVehicleSold().getVin(), getTotalPrice(), getProcessingFee(), getSalesTax(), finance?"y":"n", getMonthlyPayment());

    }


    @Override
    double getTotalPrice() {
        double vehiclePrice = 0;
        double totalPrice = 0;
        vehiclePrice = vehicleSold.getPrice();
        salesTax = vehiclePrice * 0.05; // for getSalesTax method
        // this is the processing fee
        if(vehiclePrice < 10000) {
            totalPrice = vehiclePrice + 295;
            processingFee = 295; // for getProcessingFee method
        } else {
            totalPrice = vehiclePrice + 495;
            processingFee = 495; // for getProcessingFee method
        }
        totalPrice = totalPrice + 100; // recording fee
        totalPrice = totalPrice + (vehiclePrice * 0.05); // sales tax


        return totalPrice;
    }

    int getProcessingFee() {
        return processingFee;
    }
    double getSalesTax() {
        return salesTax;
    }
    @Override
    double getMonthlyPayment() {
        double vehiclePrice = vehicleSold.getPrice();
        double p = vehiclePrice;
        double r;
        double n;
        double t;
        double payment = 0;
        if (finance) {
            if (vehiclePrice >= 10000) {
                r = 0.0425;
                t = 4;
                n = 12;
            } else {
                r = 0.0525;
                t = 2;
                n = 12;
            }

            double rOverN = r / n;
            double numerator = p * rOverN;
            double onePlusROverN = 1 + rOverN;
            double power = -t * n;
            double denominator = 1 - Math.pow(onePlusROverN, power);
            payment = numerator / denominator;

        } else {
            payment = 0.00;
        }
        return payment;
    }
    }
