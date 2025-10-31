package com.yearup.dealership;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle, double expectedEndingValue, double leaseFee) {
        super(date, customerName, customerEmail, vehicle);
        this.expectedEndingValue = vehicle.getPrice() * 0.50;
        this.leaseFee = vehicle.getPrice() * 0.07;
    }

    @Override
    public double getTotalPrice() {
        return leaseFee + getVehicle().getPrice();
    }

    @Override
    public double getMonthlyPayment() {
        double rate = 0.04;
        int months = 36;
        double price = getTotalPrice();

        return (price * (rate/12)) / (1 - Math.pow(1 + (rate / 12), -months));
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }
}
