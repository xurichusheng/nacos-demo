package com.wjh;

import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/nacos?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&allowMultiQueries=true&serverTimezone=Asia/Shanghai";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    private Connection conn = null;

    @Before
    public void setUp() throws Exception {
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    @Test
    public void isClosed() {
        try {
            System.out.println(conn.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(conn);
        }
    }
}