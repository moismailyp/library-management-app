package com.ismail.springbootlibrary.User;

import com.ismail.springbootlibrary.User.SecurityUser;
import com.ismail.springbootlibrary.User.User;
import com.ismail.springbootlibrary.dao.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Component
@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user name not found exception"));
        return new SecurityUser(user);

    }
}
