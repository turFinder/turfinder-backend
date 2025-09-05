package net.tufinder.backend.enums;

public enum BookingStatus {
    PENDING("Booking pending"),
    CONFIRMED("Booking confirmed"),
    COMPLETED("Booking completed"),
    REJECTED("Booking rejected"),
    CANCELLED("Booking cancelled");

    private final String message;
    BookingStatus(String message){
        this.message = message;
    }

    public String getStatus(){
        return this.message;
    }
}
