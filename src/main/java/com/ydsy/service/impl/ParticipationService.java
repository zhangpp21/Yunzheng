package com.ydsy.service.impl;

import com.ydsy.mapper.ParticipationMapper;
import com.ydsy.pojo.Participation;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ParticipationService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 根据会议id和参会人id锁定参会情况
     *
     * @param meetingId
     * @param participantId
     * @return
     */
    public Participation selectByMeetingIdAndParticipantId(int meetingId, int participantId) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // ParticipationMapper
        ParticipationMapper mapper = sqlSession.getMapper(ParticipationMapper.class);

        Participation participation = mapper.selectByMeetingIdAndParticipantId(meetingId, participantId);

        sqlSession.close();

        return participation;
    }

    /**
     * 更新参会情况
     *
     * @param participation
     */
    public void updateLeaveStatus(Participation participation) {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // ParticipationMapper
        ParticipationMapper mapper = sqlSession.getMapper(ParticipationMapper.class);

        mapper.updateLeaveStatus(participation);

        sqlSession.commit();
        sqlSession.close();
    }
}
