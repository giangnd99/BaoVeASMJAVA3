package com.poly.utils;

import java.sql.*;

public class XJdbc {

    static Connection connection;
    static String databaseName = "asmdb";
    static String JDBC_URL = "jdbc:mysql://localhost:3306/" + databaseName;
    static String USERNAME = "root";
    static String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
     * Nạp driver và lấy kết nối
     */
    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                connection.setAutoCommit(false); // Bắt đầu transaction, tắt auto-commit
            }
            return connection;
        } catch (SQLException e) {
            throw new SQLException("Cannot connect to the database", e);
        }
    }

    /*
     * Xây dựng PreparedStatement với cơ chế hỗ trợ transaction
     */
    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        connection = getConnection(); // Sử dụng kết nối với transaction
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);
        } else {
            pstmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    /*
     * Thực hiện các câu lệnh SQL thao tác (INSERT, UPDATE, DELETE) với rollback
     */
    public static void update(String sql, Object... args) {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            try {
                stmt.executeUpdate();
                connection.commit(); // Commit nếu thành công
            } catch (SQLException e) {
                connection.rollback(); // Rollback nếu có lỗi
                throw new SQLException("Transaction failed, rollback executed", e);
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Thực hiện các câu lệnh SQL truy vấn (SELECT)
     */
    public static ResultSet query(String sql, Object... args) {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Lấy giá trị đầu tiên trong kết quả truy vấn
     */
    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = XJdbc.query(sql, args);
            if (rs.next()) {
                return rs.getObject(1); // Lấy cột đầu tiên
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
