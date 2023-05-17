package com.yearup.car_dealership;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * com.yearup.car_dealership.MenuInterface is a superclass (base class) that can be used
 * to develop menu-driven user interfaces that run on ANSI
 * standard terminals and emulators such as XTerm, MinTerm,
 * etc.
 *
 * The general appearance of a CLI menu is divided
 * into four sections:
 *
 *    heading     - A Heading, that introduces the menu.
 *    menu        - A list of options, identified by numbers or letters.
 *    status      - A status line that can be used to report input errors.
 *    menu_prompt - That asks the user for the appropriate inputThis class is not strongly encapsulated.
 *
 * Subclasses are granted access to the following
 * protected variables to make coding simple.
 *
 *   String heading
 *   String menu
 *   String menu_prompt
 *   String status
 *   Scanner inputScanner
 *
 *   This superclass is not really very versatile.  It is specific to
 *   the dealership app and expects to work with
 *   the following domain classes:
 *
 *   com.yearup.car_dealership.Vehicle    -   it will hold information about a specific vehicle.
 *   com.yearup.car_dealership.Dealership -   it will hold information about the dealership and maintain a list of vehicles with methods to search
 *                  for matching vehicles as well as add/remove vehicles.
 *   DealershipFileManage - it will read the dealership csv file, parse, and create a com.yearup.car_dealership.Dealership object full of vehicles
 *                          the file. I will also save a dealership and the vehicles back into the file with the pipe-delimited format.
 *
 *
 * A subclass must provide its own constructor, and
 * must @Override the following methods:
 *
 *   doInputUntilDone()
 *
 */
public class MenuInterface {
    Scanner scanner = new Scanner(System.in);
    Dealership dealership;

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager("01-DealershipVehicleList");
        dealership = fileManager.getDealership();
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
                    RemoveVehicleRequest();
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("That didn't work. Please try again.\n");

            }
        }
    }
    private void displayVehicles(ArrayList <Vehicle> inventory) {
        System.out.println("VIN                  YEAR      MAKE           MODEL          TYPE       COLOR      ODOMETER      PRICE");
        System.out.println("--------------------------------------------------------------------------------------------------------------\n");
        for (Vehicle v: inventory) {

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

    }

    public void processGetByMakeModelRequest() {

    }

    public void processGetYearRequest() {

    }

    public void processGetByColorRequest() {

    }

    public void processGetByMileageRequest() {

    }

    public void processGetByVehicleTypeRequest() {

    }

    public void processGetAllVehiclesRequest() {
        ArrayList aList = (ArrayList) dealership.getAllVehicles();
        displayVehicles(aList);
    }

    public void processAddVehicleRequest() {

    }

    public void RemoveVehicleRequest() {

    }
}
