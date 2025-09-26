package net.tufinder.backend.controller;


import net.tufinder.backend.dto.UserDto;
import net.tufinder.backend.dto.UserDto.UserCreateDto;
import net.tufinder.backend.entity.Users;
import net.tufinder.backend.repository.UserRepo;
import net.tufinder.backend.service.CaptchaService;
import net.tufinder.backend.service.JwtService;
import net.tufinder.backend.util.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CaptchaService captchaService;

    @GetMapping()
    public ResponseEntity<List<Users>> allUsers(){
        List<Users> users = userRepo.findAll();
        return ResponseEntity.ok(users);
    }


    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody UserCreateDto dto){
        if(!captchaService.verifyToken(dto.getToken())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Optional<Users> optUser = userRepo.findByEmail(dto.getEmail());
        if(optUser.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Users user = new Users();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        String hashedPassword = PasswordHasher.hash(dto.getPassword());
        user.setPassword(hashedPassword);
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/update")
    public ResponseEntity<Users> updateUser(@RequestBody UserDto.UserUpdateDto dto){
        Optional<Users> optUser = userRepo.findById(dto.getId());
        Users user;
        if (optUser.isPresent()){
            user = optUser.get();
            user.setAddress(dto.getAddress());
            user.setNidNo(dto.getNidNo());
            user.setPhoneNo(dto.getPhoneNo());
            user.setProfileImageUrl(dto.getProfileImageLink());
            userRepo.save(user);
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto.UserRetrieveDto> loginUser(@RequestBody UserDto.UserLoginDto dto){
        Optional<Users> optUser = userRepo.findByEmail(dto.getEmail());
        if(optUser.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        Users user = optUser.get();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword())
        );
        if(authentication.isAuthenticated()){
            return ResponseEntity.ok(
                    new UserDto.UserRetrieveDto(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            jwtService.generateToken(user.getEmail())
                    )
            );
        }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/google/login")
    public ResponseEntity<UserDto.UserRetrieveDto> googleLogin(@RequestBody UserDto.GoogleUserCreateDto dto){
        Optional<Users> optUser = userRepo.findByProviderId(dto.getProviderId());
        Users user = null;
        if(optUser.isPresent()){
            user = optUser.get();
            return ResponseEntity.ok(
                    new UserDto.UserRetrieveDto(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            jwtService.generateToken(user.getName())
                    )
            );
        }

        user = new Users();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setProviderId(dto.getProviderId());
        user.setProvider("Google");
        userRepo.save(user);

        return ResponseEntity.ok(
                new UserDto.UserRetrieveDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        jwtService.generateToken(user.getName())
                )
        );
    }
}
