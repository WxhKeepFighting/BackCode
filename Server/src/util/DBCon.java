package util;

import java.sql.*;

public class DBCon {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static Statement statement = null;

    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            long start = System.currentTimeMillis();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useSSL=false", "root", "123456");
            long end = System.currentTimeMillis();
            System.out.println("建立连接耗时:" + (end - start) + "ms 毫秒");
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static Statement getStatement() {
        Connection connection = DBCon.getConnection();
        if (connection != null) {
            try {
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        Connection connection = DBCon.getConnection();
        if (connection != null) {
            preparedStatement = connection.prepareStatement(sql);
        }
        return preparedStatement;
    }

    public static void close() {

        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
