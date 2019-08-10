package com.example.shop.dao;

import com.example.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User getUser(String username){
        User user = jdbcTemplate.queryForObject("select * from t_user where username = ?", new String[]{"zhangsan"},
                (rs, rowNum) -> {
                    User user1 = new User();
                    user1.setUsername(rs.getString("username"));
                    user1.setAge(rs.getInt("age"));
                    user1.setEmail(rs.getString("email"));
                    user1.setRealname(rs.getString("realname"));
                    return user1;
                });
        return user;
    }
}
