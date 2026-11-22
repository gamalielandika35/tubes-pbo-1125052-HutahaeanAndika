
package tugasbesarpbo.repository;

import tugasbesarpbo.Database.databaseConnection;
import tugasbesarpbo.model.Role;
import tugasbesarpbo.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements GenericRepository<User> {

    public User findByUsername(String username) throws SQLException {

        String sql = "SELECT * FROM users WHERE username = ? AND is_active = TRUE";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return getUserFromResult(result);
                }
            }
        }

        return null;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql = "INSERT INTO users "
                + "(full_name, username, password, role, phone, is_active) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getFullName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getPhone());
            statement.setBoolean(6, user.isActive());

            statement.executeUpdate();
        }
    }

    @Override
    public User findById(long id) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return getUserFromResult(result);
                }
            }
        }

        return null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users ORDER BY user_id";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                users.add(getUserFromResult(result));
            }
        }

        return users;
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET full_name = ?, username = ?, "
                + "password = ?, role = ?, phone = ?, is_active = ? "
                + "WHERE user_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getFullName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getPhone());
            statement.setBoolean(6, user.isActive());
            statement.setLong(7, user.getUserId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "UPDATE users SET is_active = FALSE WHERE user_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private User getUserFromResult(ResultSet result) throws SQLException {
        return new User(
                result.getLong("user_id"),
                result.getString("full_name"),
                result.getString("username"),
                result.getString("password"),
                Role.valueOf(result.getString("role")),
                result.getString("phone"),
                result.getBoolean("is_active")
        );
    }
}
