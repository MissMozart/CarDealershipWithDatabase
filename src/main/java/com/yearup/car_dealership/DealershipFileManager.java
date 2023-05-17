package com.yearup.car_dealership;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    String dealershipFile;
    public DealershipFileManager(String dealershipFile) {
        this.dealershipFile = dealershipFile;
    }

    public Dealership getDealership() {
        Dealership dealership;

        try {
            FileReader fileReader = new FileReader(dealershipFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;

            input = bufferedReader.readLine();
            String[] info = input.split("\\|");
            String name = info[0];
            String address = info[1];
            String phone = info[2];
            dealership = new Dealership(name, address, phone);

            while ((input = bufferedReader.readLine()) != null) {
                info = input.split("\\|");
                String vin = info[0];
                int year = Integer.parseInt(info[1]);
                String make = info[2];
                String model = info[3];
                String vehicleType = info[4];
                String color = info[5];
                int odometer = Integer.parseInt(info[6]);
                double price = Double.parseDouble(info[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dealership;
    }
    public void saveDealership() {

    }
}
