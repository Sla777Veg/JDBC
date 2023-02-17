package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String USER = "postgres";
    private static String PASSWORD = "klerik87&";
    private static final String URL = "jdbc:postgresql://localhost:5432/skypro";

    public ConnectionManager() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(USER,PASSWORD, URL);
    }
}
