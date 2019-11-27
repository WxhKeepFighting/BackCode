package util;

import java.sql.*;

public class DBCon {
    private static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            long start = System.currentTimeMillis();
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
            long end = System.currentTimeMillis();
            System.out.println("建立连接耗时:" + (end - start) + "ms 毫秒");
        } catch (SQLException|ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static Statement getStatement(){
        Connection connection = DBCon.getConnection();
        Statement statement = null;
        if (connection != null) {
            try {
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }
}
