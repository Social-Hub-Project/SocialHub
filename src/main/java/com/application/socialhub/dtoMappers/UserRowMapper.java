package com.application.socialhub.dtoMappers;

import com.application.socialhub.model.Role;
import com.application.socialhub.model.Sex;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.time.Month.MARCH;

@Component
public class UserRowMapper implements RowMapper<UserEntity> {


    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserEntity(
                Role.USER,
                rs.getString("email"),
                rs.getString("password"),
                rs.getBoolean("active"),
                LocalDate.of(2001, MARCH, 14),
                true,
                new UserInfo(rs.getString("name"),
                        rs.getString("surname"),
                        LocalDate.now(),
                        rs.getString("residence"),
                        rs.getBoolean("blocked"),
                        rs.getString("profilePhotoSource"),
                        rs.getString("bgPhotoSource"),
                        Sex.FEMALE,
                        LocalDate.now()
                )
        );
    }
}
