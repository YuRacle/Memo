package com.feliz.memo.mapper;

import com.feliz.memo.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    public User findUserById(Integer id);

    @Select("select * from user where user_name = #{userName}")
    public User findUserByUserName(String userName);

    @Select("select * from user")
    public User[] findUsers();

    @Delete("select * from user where id = #{id}")
    public int deleteUserById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into user(user_name,password,is_admin,create_time,update_time) values (#{userName},#{password},#{isAdmin},#{createTime},#{updateTime})")
    public int insertUser(User user);

    @Update("update user set user_name = #{userName} where id = #{id}")
    public int updateUser(User user);
}
