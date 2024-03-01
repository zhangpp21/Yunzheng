package com.ydsy.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum JobEnum {
    /**
     * 学员
     */
    JOB_STUDENT(1),

    /**
     * 总管
     */
    JOB_MANAGER(2),

    /**
     * 大总管
     */
    JOB_BIG_MANAGER(3);

    private final int jobId;
}
