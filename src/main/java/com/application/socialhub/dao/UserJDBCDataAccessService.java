package com.application.socialhub.dao;


import com.application.socialhub.dtoMappers.UserRowMapper;
import com.application.socialhub.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class UserJDBCDataAccessService implements UserDAO{

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserJDBCDataAccessService(JdbcTemplate jdbcTemplate,
                                         UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public List<User> selectAllUsers() {
        return null;
    }



    @Override
    public Optional<User> findUserByEmail(String email) {
        var sql = """
                SELECT id, name, email, password
                FROM my_user
                WHERE email = ?
                """;
        return jdbcTemplate.query(sql, userRowMapper, email)
                .stream()
                .findFirst();
    }

    @Override
    public boolean existsUserWithEmail(String email) {
        return false;
    }

    @Override
    public void insertUser(User user) {

    }
}
