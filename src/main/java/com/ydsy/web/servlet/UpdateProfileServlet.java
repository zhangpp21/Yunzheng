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

@WebServlet("/UpdateProfileServlet")
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
            // 创建一个JSON对象来存储用户信息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", user.getName());
            jsonObject.put("jobId", user.getJobId());
            jsonObject.put("awards", user.getAwards());
            jsonObject.put("personalSignature", user.getPersonalSignature());
            jsonObject.put("studentId", user.getStudentId());
            jsonObject.put("majorClass", user.getMajorClass());
            jsonObject.put("stage", user.getStage());
            jsonObject.put("directionId", user.getDirectionId());
            // 将JSON对象转换为字符串并写入响应
            PrintWriter out = response.getWriter();
            out.print(jsonObject.toJSONString());
            // 返回成功的 JSON 响应
            response.getWriter().write(JSON.toJSONString(BasicResultVO.success("完善信息成功")));
            out.flush();
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