package com.yearup.dealership;

public class SalesContract extends Contract{
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, double salesTaxAmount, boolean isFinanced, double processingFee) {
        super(date, customerName, customerEmail, vehicle);
        this.salesTaxAmount = vehicle.getPrice() * 0.05;
        this.isFinanced = isFinanced;
        if (vehicle.getPrice() < 10000) {
            this.processingFee = 295;
        } else {
            this.processingFee = 495;
        }
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0;
        }

        double price = getTotalPrice();
        double rate;
        int months;

        if (price >= 10000) {
            rate = 0.0425;
            months = 48;
        } else {
            rate = 0.0525;
            months = 24;
        }

        return (price * (rate/12)) / (1 - Math.pow(1 + (rate/12), -months));
    }
}
