package com.application.socialhub.repository;

import com.application.socialhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    void enableUser(String email);

    @Transactional
    @Query("SELECT u.enabled FROM User u WHERE u.email = ?1")
    Boolean selectUserEnabled(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM User u " +
            "WHERE u.email = ?1"
    )
    Boolean selectExistsEmail(String email);
}
