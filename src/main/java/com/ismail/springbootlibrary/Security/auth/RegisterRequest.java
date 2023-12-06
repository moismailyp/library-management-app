package com.ismail.springbootlibrary.Security.auth;

import com.ismail.springbootlibrary.User.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest
{

    private String firstName;
    private String lastName;
    private String email;

    private String password;
    private Roles roles;
}
