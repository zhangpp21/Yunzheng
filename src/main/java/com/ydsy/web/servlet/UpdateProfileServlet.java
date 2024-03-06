package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydsy.pojo.User;
import com.ydsy.util.BasicResultVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从session中获取用户对象
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // 如果用户对象不为null，则可以进行后续操作，比如显示用户信息并允许用户更新班级等
        if (user != null) {
            // 设置响应内容类型为 JSON
            response.setContentType("application/json;charset=utf-8");

            // 使用 BufferedReader 读取请求体中的 JSON 数据
            BufferedReader reader = request.getReader();
            String jsonString = reader.readLine();

            // 使用 JSON 库解析 JSON 数据为 User 对象
            User updatedUser = JSON.parseObject(jsonString, User.class);
        if(updatedUser != null){
            // 返回成功的 JSON 响应
            response.getWriter().write(JSON.toJSONString(BasicResultVO.success("完善信息成功")));
        }else{
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("更新失败，请重试")));
        }

        } else {
            // 处理用户对象为null的情况，可能是session过期或其他错误
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("请重新登录")));
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}