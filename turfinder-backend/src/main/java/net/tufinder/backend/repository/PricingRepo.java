package net.tufinder.backend.repository;

import net.tufinder.backend.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PricingRepo extends JpaRepository<Pricing,Long> {

}
