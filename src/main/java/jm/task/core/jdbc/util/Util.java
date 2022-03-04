package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String USER = "root";
    public static final String PASSWORD = "1234";
    public static final String URL = "jdbc:mysql://localhost:3306/user";

    public static Connection getMySQLConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
        conn.setAutoCommit(false);
        return conn;
    }
}





