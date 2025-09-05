package net.tufinder.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true,nullable = false)
    @Email
    private String email;
    private String password; //hash
    @Column(unique = true)
    private String nidNo;
    @Column(unique = true)
    private String phoneNo;
    private String profileImageUrl;
    private Boolean isAdmin;
    private Boolean isActive;
    private String address;
    private LocalDateTime created_at;
    private String providerId;
    private String provider;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @PrePersist
    public void preprocess(){
        created_at = LocalDateTime.now();
        isAdmin = false;
        isActive = true;
    }
}
