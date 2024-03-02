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

        User user = mapper.selectByUserId(userId);

        sqlSession.close();

        return user;
    }

    /**
     * 更新用户信息
     *
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

    /**
     * 登陆方法
     *
     * @param account
     * @param password
     * @return
     */
    public User login(String account, String password) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(account, password);
        sqlSession.close();
        return user;
    }
    /**
     * 注册方法
     *
     * @return
     */

    public boolean register(User user) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4. 判断用户名是否存在
        User u = mapper.selectByAccount(user.getAccount());

        if (u == null) {
            // 用户名不存在，注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();

        return u == null;
    }
}
