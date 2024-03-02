package com.ydsy.mapper;


import com.ydsy.pojo.Meeting;

import java.util.List;

public interface MeetingMapper {

    /**
     * 获取所有会议信息
     *
     * @return
     */
    List<Meeting> selectAll();

    /**
     * 获取所有会议id
     *
     * @return
     */
    List<Integer> selectAllMeetingId();

    /**
     * 根据会议id获取该会议
     *
     * @param meetingId
     * @return
     */
    Meeting selectByMeetingId(int meetingId);
}
