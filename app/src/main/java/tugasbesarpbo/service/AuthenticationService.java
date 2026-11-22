
package tugasbesarpbo.service;

import tugasbesarpbo.exception.InvalidLoginException;
import tugasbesarpbo.model.User;
import tugasbesarpbo.repository.UserRepository;
import java.sql.SQLException;

public class AuthenticationService {

    private UserRepository userRepository;

    public AuthenticationService() {
        userRepository = new UserRepository();
    }

    public User login(String username, String password)
            throws SQLException, InvalidLoginException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new InvalidLoginException("Username tidak ditemukan!");
        }

        if (!password.equals(user.getPassword())) {
            throw new InvalidLoginException("Password salah!");
        }

        return user;
    }
}
