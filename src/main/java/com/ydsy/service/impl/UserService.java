package com.ydsy.service.impl;

import com.ydsy.mapper.UserMapper;
import com.ydsy.pojo.User;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     *登陆方法
     * @param account
     * @param password
     * @return
     */
    public User login(String account,String password){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(account, password);
        sqlSession.close();
        return user;
    }
    /**
     *查询方法
     * @param account
     * @return
     */
    public User verifyUser(String account){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByAccount(account);
        sqlSession.close();
        return user;
    }
    public boolean updatePassword(String account,String newPassword) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.updatePassword(account, newPassword);
        sqlSession.commit();
        return user != null;
    }

    /**
     * 注册方法
     * @return
     */

    public boolean register(User user){
        //2. 获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4. 判断用户名是否存在
        User u = mapper.selectByAccount(user.getAccount());

        if(u == null){
            // 用户名不存在，注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();

        return u == null;

    }
}
