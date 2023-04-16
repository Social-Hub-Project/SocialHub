package com.application.socialhub.dao;


import com.application.socialhub.dtoMappers.UserRowMapper;
import com.application.socialhub.model.UserEntity;
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
    public List<UserEntity> selectAllUsers() {
        var sql = """
                SELECT id, email, password, active
                FROM my_user
                LIMIT 1000
                """;

        return jdbcTemplate.query(sql, userRowMapper);
    }



    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        var sql = """
                SELECT id, email, password
                FROM my_user
                WHERE email = ?
                """;
        return jdbcTemplate.query(sql, userRowMapper, email)
                .stream()
                .findFirst();
    }

    @Override
    public boolean existsUserWithEmail(String email) {
        var sql = """
                SELECT count(id)
                FROM my_user
                WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public void save(UserEntity userEntity) {

    }

    @Override
    public void enableUser(String email) {
    }

    @Override
    public boolean selectUserEnabled(String email) {
        return false;
    }

    @Override
    public boolean selectExistsEmail(String email) {
        return false;
    }
}
