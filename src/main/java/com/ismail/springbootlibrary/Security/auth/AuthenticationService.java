package com.ismail.springbootlibrary.Security.auth;

import com.ismail.springbootlibrary.Security.JwtService;
import com.ismail.springbootlibrary.User.CustomUserDetailsService;
import com.ismail.springbootlibrary.User.Roles;
import com.ismail.springbootlibrary.User.User;
import com.ismail.springbootlibrary.dao.RolesRepository;
import com.ismail.springbootlibrary.dao.UserRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final RolesRepository rolesRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private  final  CustomUserDetailsService customUserDetailsService;

    public String register( RegisterRequest request)
    {



        var user= User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        Optional<Roles> rolesOptional=rolesRepository.findByName("user1");
        Roles role = rolesOptional.get();
        Set<Roles> roles=new HashSet<>();
                roles.add(role);
       user.setRoles(roles);
        userRepository.save(user);

        return "Authentication successful";
    }
    public AuthenticationResponse authenticate( AuthenticationRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        User user1 = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new UsernameNotFoundException("user does not exist"));
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(user1.getEmail());
        String jwtToken=jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

