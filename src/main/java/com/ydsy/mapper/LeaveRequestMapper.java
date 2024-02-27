package com.ydsy.mapper;

import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

public interface LeaveRequestMapper {

    void addNeoLeaveRequest(@Param("applicationId") int applicationId, @Param("leaveRequestTime") Timestamp leaveRequestTime,
                            @Param("leaveRequestReason") String leaveRequestReason);

}
