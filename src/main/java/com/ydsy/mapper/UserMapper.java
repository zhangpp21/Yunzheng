package com.ydsy.mapper;

import com.ydsy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     * 根据用户名和密码查询用户对象
     *
     * @param account
     * @param password
     * @return
     */
    @Select("select * from management.users where account = #{account} and password = #{password}")
    User select(@Param("account") String account, @Param("password") String password);

    /**
     * 根据用户名查询用户对象
     *
     * @param account
     * @return
     */
    @Select("select * from management.users where account = #{account}")
    User selectByAccount(String account);

    /**
     * 根据邮箱查询用户对象
     *
     * @param email
     * @return
     */
    @Select("select * from management.users where email= #{email}")
    User selectByEmail(String email);

    /**
     * 添加用户
     *
     * @param user
     */
    @Insert("insert into management.users values(null,#{account},#{password},#{email},null,null,null,null,null,null,null,null)")
    void add(User user);

    @Select("select * from management.users where user_id = #{userId}")
    User selectByUserId(int userId);


    void updateAll(User user);
}
