package com.application.socialhub.repository;

import com.application.socialhub.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u " +
            "SET u.enabled = TRUE WHERE u.email = ?1")
    void enableUser(String email);

    @Transactional
    @Query("SELECT u.enabled FROM UserEntity u WHERE u.email = ?1")
    Boolean selectUserEnabled(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u " +
            "SET u.active = TRUE WHERE u.email = ?1")
    void activateUser(String email);

    @Transactional
    @Query("SELECT u.active FROM UserEntity u WHERE u.email = ?1")
    Boolean selectUserActive(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM UserEntity u " +
            "WHERE u.email = ?1"
    )
    Boolean selectExistsEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity findUserByEmail(String email);

}
