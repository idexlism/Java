package main.java.example.diary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:diary.db";

    public void initDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String createTableSql = """
                CREATE TABLE IF NOT EXISTS tasks (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    description TEXT NOT NULL
                )
                """;
            conn.createStatement().execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String description) {
        String sql = "INSERT INTO tasks (description) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTasks() {
        List<String> tasks = new ArrayList<>();
        String sql = "SELECT description FROM tasks";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             ResultSet rs = conn.createStatement().executeQuery(sql)) {
            while (rs.next()) {
                tasks.add(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void updateTask(int id, String newDescription) {
        // Логика обновления задачи
    }

    public void deleteTask(int id) {
        // Логика удаления задачи
    }
}