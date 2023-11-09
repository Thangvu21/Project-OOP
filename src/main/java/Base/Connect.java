package Base;

import java.sql.*;

/**
 *
 * @author sqlitetutorial.net
 */
public class Connect {
    /**
     * Connect to a sample database
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:D:/App/javafx-sdk-21/demoDB/src/main/resources/database/dictionary.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Kết nối đến cơ sở dữ liệu SQLite
            String url = "jdbc:sqlite:D:/App/javafx-sdk-21/demoDB/src/main/resources/database/dictionary.db";
            connection = DriverManager.getConnection(url);

            System.out.println("Connected to the SQLite database");

            // Tạo một lệnh SQL để truy vấn dữ liệu
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM your_table_name"; // Thay đổi thành tên bảng bạn muốn xem

            // Thực hiện truy vấn và lấy kết quả
            ResultSet resultSet = statement.executeQuery(query);

            // In kết quả
            while (resultSet.next()) {
                String column1 = resultSet.getString("column1"); // Thay đổi "column1" thành tên cột bạn muốn xem
                String column2 = resultSet.getString("column2"); // Thay đổi "column2" thành tên cột khác nếu cần
                System.out.println("Column1: " + column1 + ", Column2: " + column2);
            }

            // Đóng kết nối và các tài nguyên liên quan
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
