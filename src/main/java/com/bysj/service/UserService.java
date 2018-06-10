package com.bysj.service;

import com.bysj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cz1996 on 2018/4/15.
 */

@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUserByNameAndPsd(String name,String psd){
        String sql = "select USER_ID,USER_NAME,USER_PASSWORD from user where user.USER_NAME = ? and user.USER_PASSWORD = ?";
        List<User> user= jdbcTemplate.query(sql,new Object[]{name,psd},new MyRowMapper());
        return user;
    }

    public boolean setUserByNameAndPsd(String name,String psd){
        String sql = "insert into user (USER_NAME, USER_PASSWORD) values (?,?)";
        int insert = jdbcTemplate.update(sql,new Object[]{name,psd});
        if(insert > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean changeUserPassword(int userId,String userPasswordNew){
        String sql = "update user set USER_PASSWORD = ? where USER_ID = ?";
        int update = jdbcTemplate.update(sql,new Object[]{userPasswordNew,userId});
        if(update > 0){
            return true;
        }else{
            return false;
        }
    }

    class MyRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int num) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("USER_ID"));
            user.setUserName(rs.getString("USER_NAME"));
            user.setUserPassword(rs.getString("USER_PASSWORD"));
            return user;
        }
    }
}
