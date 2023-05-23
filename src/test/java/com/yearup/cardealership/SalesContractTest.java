package com.yearup.car_dealership;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SalesContractTest {


    @Test
    void getTotalPrice() {
        Vehicle vehicle = new Vehicle("dfgd", 1993, "Honda", "Civic", "Sedan", "White", 67956, 10000);
        SalesContract salesContract = new SalesContract(LocalDate.now(), "Jane Doe", "janedoe@gmail.com", vehicle);

        Assertions.assertEquals(11095.0, salesContract.getTotalPrice());
    }

    @Test
    void getMonthlyPayment() {
        Vehicle vehicle = new Vehicle("dfgd", 1993, "Honda", "Civic", "Sedan", "White", 67956, 10000);
        SalesContract salesContract = new SalesContract(LocalDate.now(), "Jane Doe", "janedoe@gmail.com", vehicle);

        Assertions.assertEquals(226.91, salesContract.getMonthlyPayment(), 0.01);


    }
@Test
    void getMonthlyPaymentCheapCar() {
        Vehicle vehicle = new Vehicle("dfgd", 1993, "Honda", "Civic", "Sedan", "White", 67956, 7000);
        SalesContract salesContract = new SalesContract(LocalDate.now(), "Jane Doe", "janedoe@gmail.com", vehicle);

        Assertions.assertEquals(307.88, salesContract.getMonthlyPayment(), 0.01);

    }
}