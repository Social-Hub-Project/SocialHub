package com.application.socialhub.repository;

import com.application.socialhub.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    @Transactional
    @Query(value = "SELECT u.id, u.bg_photo_source, u.blocked, u.created_at, u.date_of_birth, u.name, u.profile_photo_source, u.residence, u.sex, u.surname" +
            " FROM user_details u " +
            "JOIN my_user on u.id = my_user.user_info_id WHERE my_user.email = :email", nativeQuery = true)
    UserInfo findUserInfoByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_details" +
            " SET profile_photo_source = :src" +
            " WHERE user_details.id = :id", nativeQuery = true)
    void changeProfilePhoto(long id, String src);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_details" +
            " SET bg_photo_source = :src" +
            " WHERE user_details.id = :id", nativeQuery = true)
    void changeBackgroundPhoto(long id, String src);

    @Query(value = "SELECT *" +
            " FROM user_details" +
            " WHERE LOWER(name) LIKE CONCAT('%', LOWER(:word), '%') " +
            "OR LOWER(surname) LIKE CONCAT('%', LOWER(:word), '%')", nativeQuery = true)
    List<UserInfo> findUser(@Param("word") String word);

    @Transactional
    @Query(value = "SELECT * " +
            "FROM user_details u " +
            "WHERE u.id = :id", nativeQuery = true)
    UserInfo findUserInfoById(long id);
}
