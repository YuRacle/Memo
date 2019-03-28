package com.feliz.memo.service.Impl;

import com.feliz.memo.bean.User;
import com.feliz.memo.mapper.UserMapper;
import com.feliz.memo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 1:登录成功；0:登录失败
     * @param user
     * @return
     */
    @Override
    public User signIn(User user) {
        User _user = userMapper.findUserByUserName(user.getUserName());
        if (_user != null) {
            if (user.getPassword().equals(_user.getPassword())) {
                return _user;
            }
        }
        return null;
    }

    /**
     * 1:注册成功，0:注册失败
     * @param user
     * @return
     */
    @Override
    public int signUp(User user) {
        User _user = userMapper.findUserByUserName(user.getUserName());
        if (_user == null) {
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userMapper.insertUser(user);
            return 1;
        }else {
            return 0;
        }
    }
}
