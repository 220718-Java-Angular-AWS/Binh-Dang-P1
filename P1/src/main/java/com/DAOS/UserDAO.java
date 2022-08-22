package com.DAOS;
import com.Exceptions.AccessDeniedException;
import com.Services.DatasourceService;
import com.pojos.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements DataSourceCRUD<User>{
    Connection connection;

    public UserDAO() {
        this.connection = DatasourceService.getConnection();
    }

    @Override
    public void create(User user) {
        try {
            String sql = "INSERT INTO users (first_name, last_name, user_name, email, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();

            if(results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setUsername(results.getString("user_name"));
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while(results.next()) {
                User user = new User();
                user.setUserId(results.getInt("user_id"));
                user.setUsername(results.getString("user_name"));
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
                user.setAdmin(results.getBoolean("admin"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void update(User user) {
        try {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, user_name = ?, email = ?, password = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setInt(6, user.getUserId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User authenticate(String username, String password) {
        User user = new User();

        try {
            String sql = "SELECT * FROM users WHERE user_name = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet results = pstmt.executeQuery();

            if(results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUsername(results.getString("user_name"));
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
                user.setAdmin(results.getBoolean("admin"));

            } else {
                throw new AccessDeniedException("Access denied!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
