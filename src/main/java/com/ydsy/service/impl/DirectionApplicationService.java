package com.ydsy.service.impl;

import com.ydsy.mapper.DirectionApplicationMapper;
import com.ydsy.pojo.DirectionApplication;
import com.ydsy.pojo.User;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DirectionApplicationService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 根据方向请求id获取此申请
     *
     * @param applicationId
     * @return
     */
    public DirectionApplication selectByApplicationId(int applicationId) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // DirectionApplicationMapper
        DirectionApplicationMapper mapper = sqlSession.getMapper(DirectionApplicationMapper.class);

        DirectionApplication directionApplication = mapper.selectByApplicationId(applicationId);

        sqlSession.close();

        return directionApplication;
    }

    /**
     * 处理新的方向申请
     *
     * @param directionApplication
     */
    public void addNeoDirectionApplication(DirectionApplication directionApplication) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // DirectionApplicationMapper
        DirectionApplicationMapper mapper = sqlSession.getMapper(DirectionApplicationMapper.class);

        mapper.insertNeoDirectionApplication(directionApplication);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据总管方向获取该方向的所有方向申请
     *
     * @param manager
     * @return
     */
    public List<DirectionApplication> selectAllByApplicantDirection(User manager) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // LeaveRequestMapper
        DirectionApplicationMapper mapper = sqlSession.getMapper(DirectionApplicationMapper.class);

        List<DirectionApplication> directionApplications = mapper.selectAllByApplicantDirection(manager);

        sqlSession.close();

        return directionApplications;
    }

    /**
     * 更新对方向申请的审批内容
     *
     * @param directionApplication
     */
    public void updateApproverIdAndStatus(DirectionApplication directionApplication) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // LeaveRequestMapper
        DirectionApplicationMapper mapper = sqlSession.getMapper(DirectionApplicationMapper.class);

        mapper.updateApproverIdAndStatus(directionApplication);

        sqlSession.commit();
        sqlSession.close();
    }
}
