package database;

import java.sql.*;

public class Database {
    private String url = "jdbc:mysql://localhost:3306/new_users";
    private String username = "root";
    private String password = "1234";
    public void Stat(String name, String age){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение с базой данных установлено");

            String sql = "INSERT INTO users (name, age) VALUES (AFfa, 19)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, age);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Новая запись добавлена успешно");
            } else {
                System.out.println("Ошибка при добавлении новой записи");
            }

            connection.close();
            System.out.println("Соединение с базой данных закрыто");

        } catch (SQLException e) {
            System.out.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }
}
