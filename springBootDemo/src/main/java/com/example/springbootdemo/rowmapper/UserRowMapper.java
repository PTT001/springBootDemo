package com.example.springbootdemo.rowmapper;

import com.example.springbootdemo.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreated_date(String.valueOf(rs.getTimestamp("created_date")));
        user.setLast_modified_date(String.valueOf(rs.getTimestamp("last_modified_date")));
        return user;
    }
}
