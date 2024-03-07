package com.ydsy.mapper;

import com.ydsy.pojo.Announcement;
import org.apache.ibatis.annotations.Insert;

public interface AnnouncementMapper {
    @Insert("INSERT INTO announcements (announcement_content, direction, period, user_id, created_at,creator_id) " +
            "VALUES (#{content}, #{direction}, #{period}, #{userId}, #{createdAt},#{creatorId})")
    void insertAnnouncement(Announcement announcement);
}
