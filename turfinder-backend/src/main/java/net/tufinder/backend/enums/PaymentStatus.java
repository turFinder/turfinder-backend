package net.tufinder.backend.enums;

public enum PaymentStatus {
    PENDING("Payment pending"),
    PAID("Payment completed"),
    CANCELLED("Payment cancelled"),
    REFUNDED("Payment refunded"),
    FAILED("Error occurred during payment processing");


    private final String message;
    PaymentStatus(String message){
        this.message = message;
    }

    public String getStatus(){
        return this.message;
    }
}
