package com.tobeto.pair9.repositories;

import com.tobeto.pair9.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsUserByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String userName);

}
