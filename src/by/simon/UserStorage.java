package by.simon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {

    public static void main(String[] args) {
        UserStorage userStorage = new UserStorage();

//        userStorage.save(new User("Simon", "m", "malina 40"));
//        userStorage.save(new User("Nadya", "f", "malina 45"));

//        System.out.println(userStorage.getAll());

        userStorage.updateName("Nadia", 2);

    }


    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void save(User user){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values ( default, ?, ?, ? )");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSex());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateName(String newName, long id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name = ? where id = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {

                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String sex = resultSet.getString(3);
                String address = resultSet.getString(4);

                User user = new User(id, name, sex, address);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

}
