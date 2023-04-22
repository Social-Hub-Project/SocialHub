package com.application.socialhub.repository;

import com.application.socialhub.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    @Transactional
    @Query(value = "SELECT u.id, u.bg_photo_source, u.blocked, u.created_at, u.date_of_birth, u.name, u.profile_photo_source, u.residence, u.sex, u.surname" +
            " FROM user_details u " +
            "JOIN my_user on u.id = my_user.user_info_id WHERE my_user.email = :email", nativeQuery = true)
    UserInfo findUserInfoByEmail(@Param("email") String email);
}
