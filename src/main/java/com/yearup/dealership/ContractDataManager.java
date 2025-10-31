package com.yearup.dealership;

import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter("contracts.txt", true)) {

            Vehicle vehicle = contract.getVehicle();

            if (contract instanceof SalesContract salescontract) {
                writer.write(String.format(
                        "SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|100|%.2f|%s|%.2f%n",
                        contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(), vehicle.getVin(), vehicle.getYear(),
                        vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice(),
                        salescontract.getTotalPrice(), salescontract.isFinanced() ? "Yes" : "No", salescontract.getMonthlyPayment()
                        ));
            } else if (contract instanceof LeaseContract leasecontract) {
                writer.write(String.format(
                        "LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f%n",
                        contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(), vehicle.getVin(), vehicle.getYear(), vehicle.getMake(),
                        vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice(), leasecontract.getExpectedEndingValue(),
                        leasecontract.getLeaseFee(), leasecontract.getTotalPrice(), leasecontract.getMonthlyPayment()
                        ));
            }
        } catch (IOException error) {
            System.out.println("Error saving contract. Please try again");
        }
    }
}
