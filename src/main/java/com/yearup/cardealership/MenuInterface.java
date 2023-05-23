package com.yearup.cardealership;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * com.yearup.car_dealership.MenuInterface is a superclass (base class) that can be used
 * to develop menu-driven user interfaces that run on ANSI
 * standard terminals and emulators such as XTerm, MinTerm,
 * etc.
 * <p>
 * The general appearance of a CLI menu is divided
 * into four sections:
 * <p>
 * heading     - A Heading, that introduces the menu.
 * menu        - A list of options, identified by numbers or letters.
 * status      - A status line that can be used to report input errors.
 * menu_prompt - That asks the user for the appropriate inputThis class is not strongly encapsulated.
 * <p>
 * Subclasses are granted access to the following
 * protected variables to make coding simple.
 * <p>
 * String heading
 * String menu
 * String menu_prompt
 * String status
 * Scanner inputScanner
 * <p>
 * This superclass is not really very versatile.  It is specific to
 * the dealership app and expects to work with
 * the following domain classes:
 * <p>
 * com.yearup.car_dealership.Vehicle    -   it will hold information about a specific vehicle.
 * com.yearup.car_dealership.Dealership -   it will hold information about the dealership and maintain a list of vehicles with methods to search
 * for matching vehicles as well as add/remove vehicles.
 * DealershipFileManage - it will read the dealership csv file, parse, and create a com.yearup.car_dealership.Dealership object full of vehicles
 * the file. I will also save a dealership and the vehicles back into the file with the pipe-delimited format.
 * <p>
 * <p>
 * A subclass must provide its own constructor, and
 * must @Override the following methods:
 * <p>
 * doInputUntilDone()
 */
public class MenuInterface {
    Scanner scanner = new Scanner(System.in);
    Dealership dealership;
    Contract contract;
    private DealershipFileManager dealershipFileManager;




    private void init() {
        dealershipFileManager = new DealershipFileManager("01-DealershipVehicleList");
        dealership = dealershipFileManager.getDealership();
    }

    public void display() {
        init();

        boolean screenDone = false;
        while (!screenDone) {
            String heading = """
                         MENU
                    ---------------
                    Find Car By:
                    1) Price
                    2) Make and Model
                    3) Year
                    4) Color
                    5) Mileage
                    6) Vehicle Type
                    Or:
                    7) Show All Vehicles
                    8) Add Vehicle
                    9) Remove Vehicle
                    10) Buy or Lease a Vehicle
                    X) Exit
                    """;
            System.out.println(heading);

            String input = scanner.nextLine();
            switch (input.toUpperCase()) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "10":
                    buyOrLeaseAVehicle();
                case "X":
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("That didn't work. Please try again.\n");

            }
        }
    }

    private void displayVehicles(ArrayList<Vehicle> inventory) {
        System.out.println("VIN                  YEAR      MAKE           MODEL          TYPE       COLOR      ODOMETER      PRICE");
        System.out.println("--------------------------------------------------------------------------------------------------------------\n");
        for (Vehicle v : inventory) {

            System.out.printf("%-20s %-7d %-15s %-15s %-10s %-10s %-12d %-10.2f\n",
                    v.getVin(),
                    v.getYear(),
                    v.getMake(),
                    v.getModel(),
                    v.getVehicleType(),
                    v.getColor(),
                    v.getOdometer(),
                    v.getPrice());

        }
        System.out.println("\n");
    }


    public void processGetByPriceRequest() {

        System.out.println("What is the least you are willing to pay?");
        String input = scanner.nextLine();
        double min = Double.parseDouble(input);
        System.out.println("What is the most you are willing to pay?");
        input = scanner.nextLine();
        double max = Double.parseDouble(input);
        ArrayList aList = (ArrayList) dealership.getVehiclesByPrice(min, max);
        displayVehicles(aList);

    }

    public void processGetByMakeModelRequest() {

        System.out.println("What brand are you looking for?");
        String make = scanner.nextLine();
        System.out.println("What model are you looking for?");
        String model = scanner.nextLine();
        ArrayList aList = (ArrayList) dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(aList);
    }

    public void processGetYearRequest() {
        System.out.println("From Year:");
        String input = scanner.nextLine();
        int min = 0;
        try {
            min = Integer.parseInt(input);
        } catch (NumberFormatException ignored) {
        }

        System.out.println("To Year:");
        input = scanner.nextLine();
        int max = Integer.parseInt(input);
        ArrayList aList = (ArrayList) dealership.getVehiclesByYear(min, max);
        displayVehicles(aList);
    }

    public void processGetByColorRequest() {
        System.out.println("What color are you looking for?");
        String color = scanner.nextLine();
        ArrayList aList = (ArrayList) dealership.getVehiclesByColor(color);
        displayVehicles(aList);
    }

    public void processGetByMileageRequest() {
        System.out.println("What is the MINIMUM mileage you are looking for?");
        String input = scanner.nextLine();
        int min = 0;
        try {
            min = Integer.parseInt(input);
        } catch (NumberFormatException ignored) {
        }

        System.out.println("What is the MAXIMUM mileage you are looking for?");
        input = scanner.nextLine();
        int max = Integer.parseInt(input);
        ArrayList aList = (ArrayList) dealership.getVehiclesByMileage(min, max);
        displayVehicles(aList);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("What vehicle type are you looking for?");
        String vehicleType = scanner.nextLine();
        ArrayList aList = (ArrayList) dealership.getVehiclesByType(vehicleType);
        displayVehicles(aList);
    }

    public void processGetAllVehiclesRequest() {
        ArrayList aList = (ArrayList) dealership.getAllVehicles();
        displayVehicles(aList);
    }

    public void processAddVehicleRequest() {

        System.out.println("ADDING VEHICLE");
        System.out.println("---------------");
        System.out.println("Enter VIN:");
        String vin = scanner.nextLine();
        System.out.println("Enter year:");
        int year = scanner.nextInt();
        scanner.nextLine(); // flush scanner
        System.out.println("Enter make:");
        String make = scanner.nextLine();
        System.out.println("Enter model:");
        String model = scanner.nextLine();
        System.out.println("Enter vehicle type:");
        String vehicleType = scanner.nextLine();
        System.out.println("Enter color:");
        String color = scanner.nextLine();
        System.out.println("Enter mileage:");
        int odometer = scanner.nextInt();
        System.out.println("Enter price:");
        double price = scanner.nextDouble();
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        System.out.println("The vehicles was added to inventory.");
        dealership.addVehicle(vehicle);
        dealershipFileManager.saveDealership(dealership);

    }

    public void processRemoveVehicleRequest() {
        Vehicle v = null;
        System.out.println("Enter VIN of vehicle to remove:");
        String vin = scanner.nextLine();
        for (Vehicle i : dealership.getAllVehicles()) {
            if (i.getVin().equals(vin)) {
                v = i;
                System.out.println("The vehicle was removed from inventory.");
                break;
            }
        }
        dealership.removeVehicle(v);
        dealershipFileManager.saveDealership(dealership);
    }

    public void buyOrLeaseAVehicle() {

        LocalDate date = LocalDate.now();
        System.out.println("What is your full name?");
        String customerName = scanner.nextLine();
        System.out.println("What is your email address?");
        String customerEmail = scanner.nextLine();
        System.out.println("Which vehicle do you want to buy? (Enter VIN)");
        String chosenVehicle = scanner.nextLine();
        Vehicle v = dealership.getVehicleByVin(chosenVehicle);
        SalesContract salesContract = new SalesContract(date, customerName,
                customerEmail, v);
        String financeOrNot;
        System.out.println("Do you want to finance the car? (y/n)");
        financeOrNot = scanner.nextLine().toLowerCase();
        boolean finance = financeOrNot.equals("y");
        double monthlyPayment = salesContract.getMonthlyPayment();
        if (finance == true) {
            System.out.println("Your monthly payment is " + monthlyPayment);
        }
    }
}
