package com.ismail.springbootlibrary.dao;

import com.ismail.springbootlibrary.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long>
{

    Optional<User> findByEmail(String name);
    boolean existsByEmail(String username);
}
