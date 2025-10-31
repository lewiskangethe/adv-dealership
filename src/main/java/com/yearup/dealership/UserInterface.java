package com.yearup.dealership;

import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);


    public void display() {

        System.out.println("Dealership App");

        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealership();

        boolean running = true;

        while (running) {
            System.out.println("""
                    *********************
                    ***DEALERSHIP MENU***
                    1.) VIEW ALL VEHICLES
                    2.) SELL/LEASE A VEHICLE
                    0.) EXIT
                    *********************
                    ENTER YOUR CHOICE: 
                    """);

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 : viewAllVehicles();
                    break;
                case 2 : sellOrLeaseVehicle();
                    break;
                case 0 : running = false;
                    break;
                default :
                    System.out.println("Invalid input, please select (1, 2 or 0)");
                    break;
            }
        }
        System.out.println("Thank you! Goodbye!");

    }

    private void viewAllVehicles() {
        System.out.println("Inventory for " + dealership.getAllVehicles().size() + " vehicles ");
        System.out.println("Dealership Inventory: \n");

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            System.out.println(vehicle);
            System.out.println("*********************");
        }
    }

    private void sellOrLeaseVehicle() {
        System.out.println("Enter the vehicle's VIN number: ");
        int vin = Integer.parseInt(scanner.nextLine());

        Vehicle chosen = null;

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                chosen = vehicle;
                break;
            }
        }
        if (chosen == null) {
            System.out.println("No vehicles found with that VIN. Please try again.");
            return;
        }

        System.out.println("Enter date (YYYYMMDD): ");
        String date = scanner.nextLine();

        System.out.println("Customer name: ");
        String name = scanner.nextLine();

        System.out.println("Customer email: ");
        String email = scanner.nextLine();

        System.out.println("Sale or Lease? (S or L): ");
        String type = scanner.nextLine().trim().toUpperCase();

        Contract contract;

        if (type.equals("S")) {
            System.out.println("Would you like to finance? (yes or no): ");
            boolean isFinanced = scanner.nextLine().equalsIgnoreCase("yes");

            double salesTaxAmount = chosen.getPrice() * 0.05;
            double processingFee;
            if (chosen.getPrice() < 10000) {
                processingFee = 295;
            } else {
                processingFee = 495;
            }
            contract = new SalesContract(date, name, email, chosen, salesTaxAmount, isFinanced, processingFee);
        } else {
            int age = 2025 - chosen.getYear();
            if (age > 3) {
                System.out.println("This vehicle is too old to lease. Please try again.");
                return;
            }

            double expectedEndingValue = chosen.getPrice() * 0.50;
            double leaseFee = chosen.getPrice() * 0.07;
            contract = new LeaseContract(date, name, email, chosen, expectedEndingValue, leaseFee);
        }

        ContractDataManager manager = new ContractDataManager();
        manager.saveContract(contract);

        dealership.removeVehicle(chosen);

        System.out.println("Contract is done. Thank you!");
        System.out.println();
    }
}
