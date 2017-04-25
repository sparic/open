package cn.muye.mapper;

import cn.muye.model.User;

import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */
public interface UserMapper {
    User getUserById(Integer id);
    void saveUser(User user);
    List<User> listUsers();
}
