package com.feliz.memo.service;

import com.feliz.memo.bean.User;

public interface UserService {

    /**
     * 登录
     * @param user
     * @return
     */
    User signIn(User user);

    /**
     * 注册
     * @param user
     * @return
     */
    int signUp(User user);


}
