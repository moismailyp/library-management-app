package com.ismail.springbootlibrary.dao;

import com.ismail.springbootlibrary.User.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Long>
{

    Optional<Roles> findByName(String name);
}
