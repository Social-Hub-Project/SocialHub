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
        var sql = """
                SELECT id, name, email, password
                FROM my_user
                LIMIT 1000
                """;

        return jdbcTemplate.query(sql, userRowMapper);
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
        var sql = """
                SELECT count(id)
                FROM my_user
                WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public void insertUser(User user) {
        var sql = """
                INSERT INTO my_user(id, role, name, email, password, created_at)
                VALUES (nextval('my_user_id_seq'), ?, ?, ?, ?, ?)
                """;

        int result = jdbcTemplate.update(
                sql,
                user.getRole().toString(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt()
        );

        System.out.println("insertUser result " + result);
    }

    @Override
    public void save(User user) {

    }

    @Override
    public int enableUser(String email) {
        return 0;
    }
}
