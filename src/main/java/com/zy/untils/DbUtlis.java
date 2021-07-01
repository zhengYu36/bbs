package com.zy.untils;
import java.sql.*;

public class DbUtlis {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/bbs?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);
            if (conn!=null){
                System.out.println("数据库连接成功");
            }

        } catch (Exception e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }

        return conn;
    }

    public static void dbClose(Connection connection, PreparedStatement preparedStatement){
        try {
            connection.close();
            preparedStatement.close();
            System.out.println("关闭成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void dbClose(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet){
        try {
            connection.close();
            preparedStatement.close();
            resultSet.close();
            System.out.println("关闭成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


