package com.application.socialhub.dtoMappers;

import com.application.socialhub.model.Role;
import com.application.socialhub.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.time.Month.MARCH;

@Component
public class UserRowMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                Role.USER,
                rs.getString("email"),
                rs.getString("password"),
                rs.getBoolean("active"),
                LocalDate.of(2001, MARCH, 14)
        );
    }
}
