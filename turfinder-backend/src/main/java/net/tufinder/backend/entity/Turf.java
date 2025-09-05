package net.tufinder.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turf")
public class Turf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalTime opening;
    private LocalTime closing;
    private Double averageRating;
    private Integer slotDuration;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="description_id")
    private Description description;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id",nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "owner_id",nullable = false)
    private Owner owner;

    @OneToMany(mappedBy = "turf",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Pricing> pricingList = new ArrayList<>();

    @OneToMany(mappedBy = "turf")
    private List<Booking> bookingList = new ArrayList<>();

    @OneToMany(mappedBy = "turf",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "turf",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TurfPictures> images = new ArrayList<>();

    private LocalDateTime created_at;

    @PrePersist
    public void initialRating(){
        averageRating = 0.0;
    }

}
