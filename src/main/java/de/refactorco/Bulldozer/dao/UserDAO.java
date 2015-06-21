package de.refactorco.Bulldozer.dao;

import com.google.inject.Inject;
import com.netflix.governator.annotations.AutoBindSingleton;
import de.refactorco.Bulldozer.Bulldozer;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
@AutoBindSingleton
public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    @Inject
    private Bulldozer bulldozer;

    public boolean userExists(String userName) {
        try (Connection connection = bulldozer.getDataSource().getConnection()) {
            String query = "select exists(select 1 from users where username=?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userName);

                try (ResultSet result = statement.executeQuery()) {
                    result.first();
                    return result.getBoolean("exists");
                }
            }
        } catch (SQLException e) {
            logger.error("SQL Error", e);
        }

        return false;
    }

    public boolean userExists(Long id) {
        try (Connection connection = bulldozer.getDataSource().getConnection()) {
            String query = "select exists(select 1 from users where id=?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);

                try (ResultSet result = statement.executeQuery()) {
                    return result.getBoolean("exists");
                }
            }
        } catch (SQLException e) {
            logger.error("SQL Error", e);
        }

        return false;
    }

    public boolean createUser(String username, String password, String email) {
        try (Connection connection = bulldozer.getDataSource().getConnection()) {
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

            DefaultPasswordService passwordService = new DefaultPasswordService();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, passwordService.encryptPassword(password));
                statement.setString(3, email);

                return (statement.executeUpdate() > 0);
            }
        } catch (SQLException e) {
            logger.error("SQL Error", e);
        }

        return false;
    }
}
