package com.ismail.springbootlibrary.Security.auth;

import com.ismail.springbootlibrary.User.User;
import com.ismail.springbootlibrary.dao.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    @PostMapping("/Register")
    public ResponseEntity<String> Register(@RequestBody RegisterRequest request)
    {
        if(userRepository.existsByEmail(request.getEmail()))
        {
            return new ResponseEntity<>("user already exists",HttpStatus.BAD_REQUEST);
        }
        authenticationService.register(request);
return new ResponseEntity <>("registered successfully",HttpStatus.OK);
    }
    @PostMapping("/Authenticate")
    public ResponseEntity<AuthenticationResponse> Authenticate(@RequestBody AuthenticationRequest request)
    {
return  ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/helloworld")
    public String helloWorld()
    {
        return "hello world";
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response)
    {
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return  ResponseEntity.ok("logout successful");
    }

    }


