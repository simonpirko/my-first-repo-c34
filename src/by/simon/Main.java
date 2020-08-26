package by.simon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
            Statement statement = connection.createStatement();
            statement.execute("insert into users values (default, 'NewUser')");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
