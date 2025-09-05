package net.tufinder.backend.service;

import net.tufinder.backend.entity.UserPrincipal;
import net.tufinder.backend.entity.Users;
import net.tufinder.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<Users> opt = userRepo.findByName(username);
        if(opt.isEmpty()) throw new UsernameNotFoundException("user not found");

        Users user = opt.get();
        return new UserPrincipal(user);
    }
}
