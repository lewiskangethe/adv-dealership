package com.yearup.dealership;

import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);


    public void display() {

        System.out.println("Dealership App");

        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealership();

        boolean running = true


        // Print all vehicles in the inventory
        System.out.println("Inventory for " + dealership.getAllVehicles().size() + " vehicles ");
        System.out.println("Dealership Inventory:\n");

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            System.out.println(vehicle);
            System.out.println("---------------------------");
        }

    }

}
