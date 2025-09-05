package net.tufinder.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tufinder.backend.entity.Booking;
import net.tufinder.backend.entity.Turf;
import net.tufinder.backend.entity.Users;
import net.tufinder.backend.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalTime;


public class BookingDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RetrieveDto{
        private LocalTime startTime;
        private LocalTime endTime;
        private LocalDate date;
        private BookingStatus status;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDto{
        private LocalTime startTime;
        private LocalTime endTime;
        private LocalDate date;
        private Long turfId;
        private Long userId;
    }



    public static RetrieveDto map(Booking booking){
        return new RetrieveDto(
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getDate(),
                booking.getStatus()
        );
    }
}
