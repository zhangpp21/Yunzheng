package com.ydsy.service;

import com.ydsy.mapper.LeaveRequestMapper;
import com.ydsy.mapper.UserMapper;
import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.User;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Timestamp;

public class LeaveRequestService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public void addNeoLeaveRequest(LeaveRequest leaveRequest) {
        // 获取SqlSession`\
        SqlSession sqlSession = factory.openSession();
        // 获取LeaveRequestMapper
        LeaveRequestMapper mapper = sqlSession.getMapper(LeaveRequestMapper.class);

        mapper.addNeoLeaveRequest(leaveRequest);

        sqlSession.commit();
        sqlSession.close();
    }

    public User selectUserByUserId(int userId) {
        // 获取SqlSession`\
        SqlSession sqlSession = factory.openSession();
        // UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user =  mapper.selectByUserId(userId);

        sqlSession.close();

        return user;
    }
}
