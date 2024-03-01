package com.ydsy.service.impl;

import com.ydsy.mapper.UserMapper;
import com.ydsy.pojo.User;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 根据用户id获取用户对象
     *
     * @param userId
     * @return
     */
    public User selectUserByUserId(int userId) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.selectByUserIdUser(userId);

        sqlSession.close();

        return user;
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateAll(User user) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.updateAll(user);

        sqlSession.commit();
        sqlSession.close();
    }
}
