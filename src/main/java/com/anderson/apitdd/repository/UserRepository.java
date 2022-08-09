package com.anderson.apitdd.repository;

import com.anderson.apitdd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);
}
