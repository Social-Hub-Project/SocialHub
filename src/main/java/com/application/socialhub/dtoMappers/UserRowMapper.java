package com.application.socialhub.dtoMappers;

import com.application.socialhub.model.Role;
import com.application.socialhub.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class UserRowMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getLong("id"),
                Role.valueOf(rs.getString("role")),
                rs.getString("email"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getString("createdAt")
        );
    }
}
