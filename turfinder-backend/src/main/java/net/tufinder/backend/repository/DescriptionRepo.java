package net.tufinder.backend.repository;

import net.tufinder.backend.entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepo extends JpaRepository<Description,Long> {

}
