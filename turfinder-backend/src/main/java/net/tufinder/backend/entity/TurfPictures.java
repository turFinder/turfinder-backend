package net.tufinder.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "turf-pictures")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurfPictures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String turfImageUrl;
    @ManyToOne
    @JoinColumn(name = "turf_id",nullable = false)
    private Turf turf;
}
