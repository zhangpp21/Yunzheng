-- 用于重载sql
DROP DATABASE
    IF
        EXISTS management;
CREATE DATABASE management;
USE management;
--
--
--
-- 用户表 用于存储用户数据
CREATE TABLE users
(
    user_id            INT PRIMARY KEY AUTO_INCREMENT, -- 用户ID，作为主键并自动递增
    account            VARCHAR(255)        NOT NULL,   -- 账号，不能为空
    password           VARCHAR(255)        NOT NULL,   -- 密码，不能为空
    email              VARCHAR(255) UNIQUE NOT NULL,   -- 邮箱，唯一且不能为空
    name               VARCHAR(255)        ,   -- 姓名，不能为空
    job_id             INT,                            -- 职位
    awards             TEXT,                           -- 获奖经历
    personal_signature TEXT,                           -- 个人签名
    student_id         VARCHAR(255),                   -- 学号
    major_class        VARCHAR(255),                   -- 专业班级
    stage              VARCHAR(255),                   -- 期数
    direction_id       INT                             -- 方向
);
ALTER TABLE users
    CONVERT TO CHARACTER
        SET utf8;
-- 转换数据格式
-- user 实例
INSERT INTO users (account, password, email,name, job_id, awards, personal_signature, student_id, major_class, stage,
                   direction_id)
VALUES ('2023004333', '123456', '22778845@qq.com', '张三', 3, '青鸥奖学金', '好好学习', '2023004333', '软件2333班',
        '八期', 2),
       ('2023002222', '13579', '2464181818@qq.com', '李四', 1, '无', '天天向上', '2023002222', '数科2302', '七期', 2);

SET SESSION sql_mode = (SELECT REPLACE
                               (@@sql_mode, 'ONLY_FULL_GROUP_BY', ''));
-- 临时更改MySQL的SQL模式
--
--
--
-- 方向表 用于存储方向本身信息
CREATE TABLE directions
(
    direction_id    INT AUTO_INCREMENT PRIMARY KEY, -- 方向id
    direction_name  VARCHAR(255) NOT NULL,          -- 方向名称
    direction_count INT DEFAULT 0                   -- 方向人数
);
ALTER TABLE directions
    CONVERT TO CHARACTER
        SET utf8;
-- 转换数据格式
-- 实例
INSERT INTO directions (direction_name, direction_count)
VALUES ('设计', 1),
       ('全栈', 2),
       ('Java', 3),
       ('CPU&OS', 4),
       ('数据科学', 5);
ALTER TABLE users -- 将用户表中的方向与方向表建立外键联系
    ADD CONSTRAINT fk_users_directions FOREIGN KEY (direction_id) REFERENCES directions (direction_id) ON DELETE
        SET NULL;
--
--
--
--
-- 职务表 用于存储职位本身信息
CREATE TABLE jobs
(
    job_id    INT AUTO_INCREMENT PRIMARY KEY, -- 职位id
    job_name  VARCHAR(255) NOT NULL,          -- 职位名称
    job_count INT DEFAULT 0                   -- 职位人数
);
ALTER TABLE jobs
    CONVERT TO CHARACTER
        SET utf8;
-- 转换数据格式
-- 实例
INSERT INTO jobs (job_name, job_count)
VALUES ('学员', 1),
       ('总管', 2),
       ('大总管', 3);
ALTER TABLE users -- 将用户表中的职位与职位表建立外键联系
    ADD CONSTRAINT fk_users_jobs FOREIGN KEY (job_id) REFERENCES jobs (job_id) ON DELETE SET NULL;
--
--
--
--
-- 会议表 用于存储会议本身信息
CREATE TABLE meetings
(
    meeting_id      INT AUTO_INCREMENT PRIMARY KEY, --  会议id
    meeting_name    VARCHAR(255) NOT NULL,          -- 会议名称
    creator_id      INT          NOT NULL,          -- 创建人id
    meeting_content TEXT                            -- 会议内容
);
ALTER TABLE meetings
    CONVERT TO CHARACTER
        SET utf8;
ALTER TABLE meetings
    ADD CONSTRAINT fk_meetings_users -- 将用户表中的用户与会议表建立外键联系
        FOREIGN KEY (creator_id) REFERENCES users (user_id) ON DELETE CASCADE;
-- 实例
INSERT INTO meetings (meeting_name, creator_id, meeting_content)
VALUES ('八期成员见面会', 1, '振翅云顶之上，极目星辰大海');
--
--
--
--
-- 参会情况表 用于存储参会的情况 记录签到签退请假情况
-- 管理员创建会议时应该创建每个参会人的创建情况表
CREATE TABLE participations
(
    participation_id INT AUTO_INCREMENT PRIMARY KEY,                               -- 会议情况主键id
    participant_id   INT NOT NULL,                                                 -- 参会人员id
    meeting_id       INT NOT NULL,                                                 -- 会议id
    approve_id       INT                                        default NULL,      -- 审批人id
    sign_in_status   ENUM ( 'Yes', 'No', 'Pending' )            default 'Pending', -- 签到状态
    sign_out_status  ENUM ( 'Yes', 'No', 'Pending' )            default 'Pending', -- 签退状态
    leave_status     ENUM ( 'Approved', 'Pending', 'Rejected' ) default 'Pending'  -- 请假状态

);
ALTER TABLE participations
    CONVERT TO CHARACTER
        SET utf8;
ALTER TABLE participations -- 将会议表中的会议与参会人员表建立外键联系
    ADD CONSTRAINT fk_participants_meetings FOREIGN KEY (meeting_id) REFERENCES meetings (meeting_id) ON DELETE CASCADE;
ALTER TABLE participations -- -- 将用户表中的用户与参会人员表的申请人建立外键联系
    ADD CONSTRAINT fk_participations_participant_users FOREIGN KEY (participant_id) REFERENCES users (user_id) ON DELETE CASCADE;
ALTER TABLE participations -- -- 将用户表中的用户与参会人员表的审批人建立外键联系
    ADD CONSTRAINT fk_participations_approve_users FOREIGN KEY (approve_id) REFERENCES users (user_id) ON DELETE CASCADE;
INSERT INTO participations (participant_id, meeting_id, sign_in_status, sign_out_status, leave_status)
VALUES (1, 1, 'Yes', 'No', 'Pending');
--
--
--
--
-- 公告表 记录公告信息 公告将给创建人所属方向的所有成员所见
CREATE TABLE announcements
(
    announcement_id      INT AUTO_INCREMENT PRIMARY KEY,      -- 公告id
    creator_id           INT  NOT NULL,                       -- 创建人id
    announcement_content TEXT NOT NULL,                       -- 公告内容
    created_at           TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    FOREIGN KEY (creator_id) REFERENCES users (user_id) ON DELETE CASCADE
);
ALTER TABLE announcements
    CONVERT TO CHARACTER
        SET utf8;
ALTER TABLE announcements -- 将公告表中的创建人与参会人员表建立外键联系
    ADD CONSTRAINT fk_announcements_users FOREIGN KEY (creator_id) REFERENCES users (user_id) ON DELETE CASCADE;
-- 实例
INSERT INTO announcements (creator_id, announcement_content)
VALUES (1, '这是一条公告内容');
--
--
--
--
-- 请假情况表 用于存储请假申请和结果 提交申请创建此表内信息,审批后填补审批信息
CREATE TABLE leave_requests
(-- 假条表
    leave_request_id     INT AUTO_INCREMENT PRIMARY KEY, -- 假条id
    applicant_id         INT       NOT NULL, -- 申请人id
    leave_request_meeting   INT  NOT NULL, -- 请假时间
    leave_request_reason VARCHAR(255), -- 请假理由
    approve_id           INT DEFAULT NULL, -- 批准人id
    approve_status       INT DEFAULT -1 -- 批准情况 1:批准 0:不批准

);
ALTER TABLE leave_requests
    CONVERT TO CHARACTER
        SET utf8;
ALTER TABLE leave_requests -- 将用户表中的用户与假条表中的请假人建立外键联系
    ADD CONSTRAINT fk_leave_requests_users FOREIGN KEY (applicant_id) REFERENCES users (user_id) ON DELETE CASCADE;
ALTER TABLE leave_requests -- 将用户表中的用户与假条表中的批准人建立外键联系
    ADD CONSTRAINT fk_leave_requests_approve_users FOREIGN KEY (approve_id) REFERENCES users (user_id) ON DELETE CASCADE;
ALTER TABLE leave_requests -- 将用户表中的用户与假条表中的请假人建立外键联系
    ADD CONSTRAINT fk_leave_requests_meetings FOREIGN KEY (leave_request_meeting) REFERENCES meetings (meeting_id) ON DELETE CASCADE;
-- 实例
INSERT INTO leave_requests (applicant_id, leave_request_meeting, leave_request_reason, approve_id, approve_status)
VALUES (1, 1, '我需要请一天的假去看医生', 2, 1);
--
--
--
--
-- 方向申请情况表 记录方向的申请情况
CREATE TABLE direction_applications
(
    application_id        INT AUTO_INCREMENT PRIMARY KEY,      -- 申请ID
    applicant_id          INT NOT NULL,                        -- 申请人ID
    application_direction INT,                                 -- 申请方向
    approver_id           INT       DEFAULT NULL,              -- 批准人ID
    approval_status       INT       DEFAULT - 1,               -- 批准情况：-1（未审核），1（批准），0（不批准）
    created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 申请时间
    FOREIGN KEY (applicant_id) REFERENCES users (user_id),     -- 外键约束，引用users表的user_id
    FOREIGN KEY (approver_id) REFERENCES users (user_id)       -- 外键约束，引用users表的user_id
);
ALTER TABLE direction_applications
    CONVERT TO CHARACTER
        SET utf8;
-- 转换数据格式
ALTER TABLE direction_applications -- 将用户表中的用户与方向申请情况表中的申请人建立外键联系
    ADD CONSTRAINT fk_direction_applications_applicant_id_users FOREIGN KEY (applicant_id) REFERENCES users (user_id) ON DELETE CASCADE;
ALTER TABLE direction_applications -- 将用户表中的用户与方向申请情况表中的审批人建立外键联系
    ADD CONSTRAINT fk_direction_applications_approver_id_users FOREIGN KEY (approver_id) REFERENCES users (user_id) ON DELETE CASCADE;
-- 实例
INSERT INTO direction_applications (applicant_id, application_direction, approver_id, approval_status)
VALUES (1, 2, 2, 1);