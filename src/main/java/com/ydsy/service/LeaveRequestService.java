package com.ydsy.service;

import com.ydsy.mapper.LeaveRequestMapper;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Timestamp;

public class LeaveRequestService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public void addNeoLeaveRequest(int applicationId, Timestamp leaveRequestTime, String leaveRequestReason) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取LeaveRequestMapper
        LeaveRequestMapper mapper = sqlSession.getMapper(LeaveRequestMapper.class);

        mapper.addNeoLeaveRequest(applicationId,leaveRequestTime,leaveRequestReason);
    }
}
