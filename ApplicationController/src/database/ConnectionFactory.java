package database;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

// Established the connection to the database
public class ConnectionFactory {
    public ConnectionFactory() {
        super();
    }

    protected static Connection conn = null;

    public static Connection getConnection() throws Exception {
        if (conn == null) {
            try {
                String Dir = AdfmfJavaUtilities.getDirectoryPathRoot(AdfmfJavaUtilities.ApplicationDirectory);
                String connStr = "jdbc:sqlite:" + Dir + "/sm.db";
                conn = new SQLite.JDBCDataSource(connStr).getConnection();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
