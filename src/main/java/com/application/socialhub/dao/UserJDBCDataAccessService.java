package com.application.socialhub.dao;

import com.application.socialhub.dtoMappers.UserRowMapper;
import com.application.socialhub.model.UserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public UserEntity findUserByEmail(String email) {
        var sql = """
                SELECT id, email, password
                FROM my_user
                WHERE email = ?
                """;
        return null;
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

    @Override
    public void updateUserState(boolean state, String email) {
        var sql = """
                UPDATE my_user
                SET active = ?
                WHERE email = ?
                """;
        jdbcTemplate.update(sql, state, email);
    }

    @Override
    public UserEntity findUserById(long id) {
        return null;
    }

    @Override
    public void changePassword(long userId, String newPassword) {
        System.out.println("dupa");
    }
}
