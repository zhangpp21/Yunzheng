package com.ydsy.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class PojoReceiveRequestDataUtil<T> {

    public static <T> T pojoReceiveRequestDataUtil(HttpServletRequest request, Class<T> pojoClass) throws IOException {

        /**
         * 接收所有数据为字符串
         */
        InputStreamReader insr = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
        StringBuilder body = new StringBuilder();
        int respInt = insr.read();
        while (respInt != -1) {
            body.append((char) respInt);
            respInt = insr.read();
        }

        /**
         * pojo接收数据
         */
        T pojo = JSON.parseObject(String.valueOf(body), pojoClass);
        return pojo;
    }
}
