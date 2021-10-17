package com.otuschat.dao;

import com.otuschat.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(User user) {
        return jdbcTemplate.update(
                "insert into profile (password, name, surname, age, sex, hobby, city, email) values(?,?,?,?,?,?,?,?)",
                user.getPassword(), user.getName(), user.getSurname(), user.getAge(), user.getSex(), user.getHobby(), user.getCity(), user.getEmail());
    }

    public User findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from profile where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new User(
                                rs.getLong("id"),
                                rs.getString("email"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("age"),
                                rs.getString("sex"),
                                rs.getString("hobby"),
                                rs.getString("city")
                        )).get()
        );
    }

    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "select * from profile where email = ?",
                new Object[]{email},
                (rs, rowNum) ->
                        Optional.of(new User(
                                rs.getLong("id"),
                                rs.getString("password"),
                                rs.getString("name")
                        )).get()
        );
    }

    public List<User> findAll() {

        return jdbcTemplate.query(
                "select * from profile",
                (rs, rowNum) ->
                        new User(
                                rs.getLong("id"),
                                rs.getString("password"),
                                rs.getString("name")
                        )
        );
    }

}