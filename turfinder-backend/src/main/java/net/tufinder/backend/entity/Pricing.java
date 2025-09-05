package net.tufinder.backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pricing")
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Positive
    @Column(nullable = false)
    private Double pricePerHour;
    @Column(nullable = false)
    private LocalTime startHour;
    private LocalTime endHour;
    private Short startDay;
    private Short endDay;

    @ManyToOne
    @JoinColumn(name="turf_id",nullable = false)
    private Turf turf;
}
