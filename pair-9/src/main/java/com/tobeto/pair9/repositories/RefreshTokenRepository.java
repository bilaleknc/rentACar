package com.tobeto.pair9.repositories;
import com.tobeto.pair9.entities.concretes.RefreshToken;
import com.tobeto.pair9.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    void deleteByToken(String token);

    void deleteByUser(User user);

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUser(User user);


}

