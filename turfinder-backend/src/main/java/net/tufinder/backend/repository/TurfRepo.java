package net.tufinder.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.tufinder.backend.entity.Turf;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurfRepo extends JpaRepository<Turf,Long> {
     public Optional<Turf> findByName(String name);
}
