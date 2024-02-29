package com.ydsy.service.impl;

import com.ydsy.mapper.MeetingMapper;
import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.Meeting;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MeetingService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 获取所有会议id
     * @return
     */
    public List<Integer> selectAllMeetingId(){
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // MeetingMapper
        MeetingMapper mapper = sqlSession.getMapper(MeetingMapper.class);

        List<Integer> meetingIds = mapper.selectAllMeetingId();

        sqlSession.close();

        return meetingIds;
    }

}
