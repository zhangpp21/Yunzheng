package com.ydsy.mapper;

import com.ydsy.pojo.User;

public interface UserMapper {

    User selectByUserIdUser(int userId);

    void updateAll(User user);
}
