package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ydsy.mapper.UserMapper;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.UserService;
import com.ydsy.util.BasicResultVO;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收用户数据
        BufferedReader reader = request.getReader();
        String registerUserStr = reader.readLine();

        // 封装用户对象
        if (registerUserStr == null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("用户数据为空")));
            return;
        }
        User user = JSON.parseObject(registerUserStr, User.class);

        // 获取用户输入的验证码（直接从User对象中获取）
        if (user == null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("无法解析用户对象")));
            return;
        }
        String checkCode = user.getCheckCode();
        if (checkCode == null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("验证码为空")));
            return;
        }
        // 程序生成的验证码，从Session获取
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
        if (checkCode != null) {
            // 比对验证码
            if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("验证码错误")));
                return;
            }
            SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                // 获取Mapper
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

                // 检查用户名是否重复
                User existingAccount = userMapper.selectByAccount(user.getAccount());
                if (existingAccount != null) {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("用户名已存在")));
                    response.sendRedirect(request.getRequestURI());
                    return;
                }

                // 检查邮箱是否重复
                User existingEmail = userMapper.selectByEmail(user.getEmail());
                if (existingEmail != null) {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("邮箱已被注册")));
                    response.sendRedirect(request.getRequestURI());
                    return;
                }

                //注册新用户
                userMapper.add(user);
                sqlSession.commit();
                // 返回成功的 JSON 响应
                response.getWriter().write(JSON.toJSONString(BasicResultVO.success("注册成功，请登录")));
                // 重定向到基本 Servlet
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/loginServlet");
            }


        } else {
            // 会话中没有找到生成的验证码，处理错误...
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("请输入验证码")));
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}