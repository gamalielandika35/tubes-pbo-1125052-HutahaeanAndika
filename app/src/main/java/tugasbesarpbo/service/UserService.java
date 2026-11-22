
package tugasbesarpbo.service;

import tugasbesarpbo.model.User;
import tugasbesarpbo.repository.UserRepository;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public void addUser(User user, String password) throws SQLException {
        User newUser = new User(
                user.getUserId(),
                user.getFullName(),
                user.getUsername(),
                password,
                user.getRole(),
                user.getPhone(),
                user.isActive()
        );

        userRepository.save(newUser);
    }

    public List<User> getAllUsers() throws SQLException {
        return userRepository.findAll();
    }

    public User getUserById(long id) throws SQLException {
        return userRepository.findById(id);
    }

    public void updateUser(User user, String password) throws SQLException {
        String newPassword = user.getPassword();

        if (password != null && !password.isEmpty()) {
            newPassword = password;
        }

        User updatedUser = new User(
                user.getUserId(),
                user.getFullName(),
                user.getUsername(),
                newPassword,
                user.getRole(),
                user.getPhone(),
                user.isActive()
        );

        userRepository.update(updatedUser);
    }

    public void deleteUser(long id) throws SQLException {
        userRepository.delete(id);
    }
}
