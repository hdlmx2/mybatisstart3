package cn.hdlmx.mybatis.dao;

import cn.hdlmx.mybatis.models.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUser {
    //@Select("select * from user where id= #{id}")
    public List<User> getUserList();

    public void insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

    public User getUser(int id);


}
