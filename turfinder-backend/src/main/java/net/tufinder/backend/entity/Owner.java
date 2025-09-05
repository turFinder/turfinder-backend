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
@Table(name="owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true,nullable = false)
    @Email
    private String email;
    @Column(unique = true,nullable = false)
    private String  phoneNo;
    private String password; //hash
    private String profileImageUrl;
    @Column(unique = true,nullable = false)
    private String nidNo;
    private String address;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "owner")
    private List<Turf> turfs = new ArrayList<>();

    private LocalDateTime created_at;

    @PrePersist
    public void initiate(){
        created_at = LocalDateTime.now();
    }
}
