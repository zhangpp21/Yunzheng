package com.ydsy.mapper;

import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.User;

import java.util.List;

public interface LeaveRequestMapper {

    void InsertNeoLeaveRequest(LeaveRequest leaveRequest);

    LeaveRequest selectByLeaveRequestId(int leaveRequestId);

    List<LeaveRequest> selectAllByApplicantDirection(User manager);

    void UpdateLeaveRequestApproval(LeaveRequest leaveRequest);
}
