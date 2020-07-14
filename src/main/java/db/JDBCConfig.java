package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConfig {
    private final static String DRIVER_CLASS = "org.sqlite.JDBC";
    private final static String DB_URL = "jdbc:sqlite::memory:";
    private static Statement statement;
    private static Connection connection;

    public static Statement config() {
        try {
            Class.forName(DRIVER_CLASS);
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}