package net.tufinder.backend.repository;


import net.tufinder.backend.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  OwnerRepo extends JpaRepository<Owner,Long> {
    public Optional<Owner> findByNidNo(String nid);
}
