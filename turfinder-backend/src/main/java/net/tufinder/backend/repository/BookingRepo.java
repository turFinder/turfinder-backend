package net.tufinder.backend.repository;

import net.tufinder.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {

}
