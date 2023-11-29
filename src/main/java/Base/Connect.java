package Base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connect {
    public static List<Word> volCal = new ArrayList<>();

    private static final String url = "jdbc:sqlite:"+ Connect.class.getResource("/database/dictionary.db");

    public static void insertDataWord(String word, String description, String pronounce, String tableName) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // Kết nối đến cơ sở dữ liệu SQLite
            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();

            // Tạo một lệnh SQL để truy vấn dữ liệu
            String query = "INSERT INTO " + tableName + " (word, description, pronounce) values ('"
                    + word + "', '" + description + "', '" + pronounce + "');";

            //System.out.println(query);
            // results số lượng dòng thay đổi
            int result = statement.executeUpdate(query);
            statement.close();
            connection.close();
        }  catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void deleteDataWord(String word, String tableName) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();

            // Tạo một lệnh SQL để truy vấn dữ liệu
            String query = "DELETE FROM " + tableName + " where word = '"
                    + word + "'";
            //System.out.println(query);
            // results số lượng dòng thay đổi và update data
            int result = statement.executeUpdate(query);
            connection.close();
            statement.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void repairDataWord(String word, String descriptionEdit, String tableName) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();

            // Tạo một lệnh SQL để truy vấn dữ liệu
            String query = "UPDATE " + tableName + " set description = '"
                    + descriptionEdit + "' where word = '" + word + "'";
            //System.out.println(query);

            // results số lượng dòng thay đổi và update data
            int result = statement.executeUpdate(query);
            System.out.println(result);

            connection.close();
            statement.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ObservableList<Word> searchDataWord(String target_word, String tableName) {
        ObservableList<Word> result = FXCollections.observableArrayList();
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();

            // Tạo một lệnh SQL để truy vấn dữ liệu
            String query = "SELECT * FROM " + tableName + " where word like '"
                    + target_word + "' ORDER BY word";

            //System.out.println(query);
            // khi query trả về đối tượng resultSet như 1 cái table có nhiều dòng
            ResultSet resultSet = statement.executeQuery(query);

            // dùng while để lấy nó ra
            while (resultSet.next()) {
                String targetWord = resultSet.getString("word");
                String explainWord = resultSet.getString("description");
                String pronounce = resultSet.getString("pronounce");
                String HTML = resultSet.getString("html");

                Word word = new Word(targetWord, explainWord, pronounce, HTML);
                result.add(word);
            }

            resultSet.close();
            connection.close();
            statement.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static void importDataInTrie(String tableName, TrieNode root) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();

            // Tạo một lệnh SQL để truy vấn dữ liệu
            String query = "SELECT * FROM " + tableName;

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String targetWord = resultSet.getString("word");
                String explainWord = resultSet.getString("description");
                String pronounce = resultSet.getString("pronounce");
                Trie.insertWord(root, targetWord, explainWord, pronounce);
            }
            System.out.println("init thành công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static ObservableList<Word> importDataInObser(String tableName) {
        ObservableList<Word> results = FXCollections.observableArrayList();
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();

            // Tạo một lệnh SQL để truy vấn dữ liệu
            String query = "SELECT * FROM " + tableName;

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String targetWord = resultSet.getString("word");
                String explainWord = resultSet.getString("description");
                String pronounce = resultSet.getString("pronounce");
                results.add(new Word(targetWord, explainWord, pronounce));
            }
            System.out.println("init thành công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
    public static void main(String[] args) {
        ObservableList<Word> list1 = searchDataWord("abstract", "av");
        for (Word word : list1) {
            System.out.println(word.showWord());
        }
    }
}
