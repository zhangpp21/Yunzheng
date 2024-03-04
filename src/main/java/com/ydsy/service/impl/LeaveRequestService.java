package com.ydsy.service.impl;

import com.ydsy.mapper.LeaveRequestMapper;
import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.User;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class LeaveRequestService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 添加一个新的假条请求
     *
     * @param leaveRequest
     */
    public void addNeoLeaveRequest(LeaveRequest leaveRequest) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取LeaveRequestMapper
        LeaveRequestMapper mapper = sqlSession.getMapper(LeaveRequestMapper.class);

        mapper.InsertNeoLeaveRequest(leaveRequest);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据假条id获取假条
     *
     * @param leaveRequestId
     * @return
     */
    public LeaveRequest selectByLeaveRequestId(int leaveRequestId) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取LeaveRequestMapper
        LeaveRequestMapper mapper = sqlSession.getMapper(LeaveRequestMapper.class);

        LeaveRequest leaveRequest = mapper.selectByLeaveRequestId(leaveRequestId);

        sqlSession.close();

        return leaveRequest;
    }

    /**
     * 根据总管方向获取该方向的所有假条
     *
     * @param manager
     * @return
     */
    public List<LeaveRequest> selectAllByApplicantDirection(User manager) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // LeaveRequestMapper
        LeaveRequestMapper mapper = sqlSession.getMapper(LeaveRequestMapper.class);

        List<LeaveRequest> leaveRequests = mapper.selectAllByApplicantDirection(manager);

        sqlSession.close();

        return leaveRequests;
    }

    /**
     * 总管审批后将审批结果存储在数据库中
     *
     * @param leaveRequest
     */
    public void UpdateLeaveRequestApproval(LeaveRequest leaveRequest) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // LeaveRequestMapper
        LeaveRequestMapper mapper = sqlSession.getMapper(LeaveRequestMapper.class);

        mapper.UpdateLeaveRequestApproval(leaveRequest);

        sqlSession.commit();
        sqlSession.close();
    }
}
