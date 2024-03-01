package com.ydsy.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum DirectionEnum {

    /**
     * 设计
     */
    DIRECTION_DESIGN(1),

    /**
     * 全栈
     */
    DIRECTION_FULL_STACK(2),

    /**
     * JAVA
     */
    DIRECTION_JAVA(3),

    /**
     * CPU&OS
     */
    DIRECTION_CPU_OS(4),

    /**
     * 数据科学
     */
    DIRECTION_AI(5);

    private final int directionId;
}
