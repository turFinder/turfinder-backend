package net.tufinder.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tufinder.backend.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "turf_id",nullable = false)
    private Turf turf;

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String bookingName;
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users user;

    private LocalDateTime created_at;

    @PrePersist
    public void initiate(){
        created_at = LocalDateTime.now();
        status = BookingStatus.PENDING;
    }
}
