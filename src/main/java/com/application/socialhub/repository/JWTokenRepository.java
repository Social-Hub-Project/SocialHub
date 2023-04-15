package com.application.socialhub.repository;

import com.application.socialhub.model.BlackListJWToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JWTokenRepository extends JpaRepository<BlackListJWToken, Long> {
    boolean existsByToken(String jwt);
}
