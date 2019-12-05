package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private Connection connection;

    public Connection connectionToSQLServer() {

       // String url = "jdbc:sqlserver://DESKTOP-IURN3NH\\SQLEXPRESS;databaseName=CONNECTIS;integratedSecurity=true;";
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=CONNECTIS;integratedSecurity=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);
            System.out.println(connection);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public Connection getConnection() {
        return (connection != null) ? connection : connectionToSQLServer();
    }


}
