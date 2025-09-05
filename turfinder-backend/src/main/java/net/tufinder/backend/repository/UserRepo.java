package net.tufinder.backend.repository;

import net.tufinder.backend.dto.UserDto.UserCreateDto;
import net.tufinder.backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByName(String name);
    Optional<Users> findByProviderId(String providerId);
}
