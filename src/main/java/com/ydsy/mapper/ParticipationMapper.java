package com.ydsy.mapper;

import com.ydsy.pojo.Participation;
import org.apache.ibatis.annotations.Param;

public interface ParticipationMapper {

    /**
     * 根据会议id和参会人id锁定参会情况
     *
     * @param meetingId
     * @param participantId
     * @return
     */
    Participation selectByMeetingIdAndParticipantId(@Param("meetingId") int meetingId, @Param("participantId") int participantId);

    /**
     * 更新参会情况
     *
     * @param participation
     */
    void updateLeaveStatus(Participation participation);
}
