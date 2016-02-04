package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private static Database instance = new Database();
    private Connection con;

    private Database() {
    }

    public static Database getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

    public void connect() throws Exception {
        if (con != null) {
            return;
        }

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found");
        }

        con = DriverManager.getConnection("jdbc:derby://localhost:1527/projtest");
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Can't close connection");
            }
        }

        con = null;
    }
}
