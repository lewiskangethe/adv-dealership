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
}
