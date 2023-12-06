package com.ismail.springbootlibrary.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name="user_roles" ,joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
    inverseJoinColumns =@JoinColumn(name="role_id",referencedColumnName = "id") )
    private Set<Roles> roles=new HashSet<>();


}
