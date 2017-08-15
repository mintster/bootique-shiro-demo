package com.nixmash.shiro.db;

import com.google.inject.Inject;
import com.nixmash.shiro.config.AppConfig;
import com.nixmash.shiro.models.Role;
import com.nixmash.shiro.models.User;
import io.bootique.jdbc.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDbImpl implements UsersDb {

    private static final Logger logger = LoggerFactory.getLogger(UsersDbImpl.class);

    private DataSourceFactory dataSource;
    private AppConfig appConfig;

    @Inject
    public UsersDbImpl(DataSourceFactory dataSource, AppConfig appConfig) {
        this.dataSource = dataSource;
        this.appConfig = appConfig;
    }

    @Override
    public List<Role> getRoles(Long userId) {
        List<Role> roles = new ArrayList<>();
        try (Connection cn = dataSource.forName(appConfig.datasourceName).getConnection()) {
            try (Statement statement = cn.createStatement()) {
                String getRolesSql = "SELECT user_roles.user_id, roles.permission, roles.role_name FROM  user_roles " +
                        "INNER JOIN roles ON user_roles.role_id = roles.role_id WHERE user_roles.user_id ='" + userId + "'";
                ResultSet rs = statement.executeQuery(getRolesSql);
                rs.beforeFirst();
                while (rs.next()) {
                    roles.add(new Role(rs.getString("permission"), rs.getString("role_name")));
                }
                rs.close();
                cn.close();
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("Error retrieving roles: " + e.getMessage());
        }
        return roles;
    }

    @Override
    public User getUser(String username) {
        User user = new User();
        try (Connection cn = dataSource.forName(appConfig.datasourceName).getConnection()) {

            try (Statement statement = cn.createStatement()) {
                String getUserSql = "SELECT * FROM users WHERE username = '" + username + "'";
                ResultSet rs = statement.executeQuery(getUserSql);
                rs.beforeFirst();

                while (rs.next()) {
                    user.setUserId(rs.getLong("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                }
                rs.close();
                cn.close();
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("Error retrieving user: " + e.getMessage());
        }
        return user;
    }


    @Override
    public User addUser(User user) {
        try (Connection connection = dataSource.forName(appConfig.datasourceName).getConnection()) {

            try (Statement statement = connection.createStatement()) {
                String createUserSql = "INSERT INTO users (username, email, first_name, last_name, password) VALUES " +
                        "('" + user.getUsername() + "', " +
                        "'" + user.getEmail() + "', " +
                        "'" + user.getFirstName() + "', " +
                        "'" + user.getLastName() + "', " +
                        "'" + user.getPassword() + "'" +
                        ")";
                int result = statement.executeUpdate(createUserSql, Statement.RETURN_GENERATED_KEYS);
                if (result == 1) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        user.setUserId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            logger.info("Error creating new user: " + e.getMessage());
        }
        return user;
    }
}
