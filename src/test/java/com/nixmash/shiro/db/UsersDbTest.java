package com.nixmash.shiro.db;

import com.nixmash.shiro.config.AppConfig;
import com.nixmash.shiro.models.Role;
import com.nixmash.shiro.models.User;
import com.nixmash.shiro.service.UserService;
import com.nixmash.shiro.service.UserServiceImpl;
import com.nixmash.shiro.utils.DbUtils;
import io.bootique.BQRuntime;
import io.bootique.jdbc.DataSourceFactory;
import io.bootique.test.junit.BQTestFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RunWith(JUnit4.class)
public class UsersDbTest {

    @ClassRule
    public static BQTestFactory TEST_FACTORY = new BQTestFactory();

    private static UserService userService;

    @BeforeClass
    public static void setupDB() throws SQLException {
        BQRuntime runtime = TEST_FACTORY
                .app("--config=classpath:test.yml")
                .module(b -> b.bind(UserService.class).to(UserServiceImpl.class))
                .module(b -> b.bind(UsersDb.class).to(UsersDbImpl.class))
                .autoLoadModules()
                .createRuntime();

        userService = runtime.getInstance(UserService.class);

        DataSourceFactory datasource = runtime.getInstance(DataSourceFactory.class);
        AppConfig appConfig = runtime.getInstance(AppConfig.class);
        Connection connection = datasource.forName(appConfig.datasourceName).getConnection();

        if (appConfig.datasourceName.equals("H2")) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(DbUtils.createSchemaSql);
                statement.execute(DbUtils.dataSql);
            }
        }
    }

    @Test
    public void addUserTest() throws Exception {
        User jammer = new User("jammer", "jammer@aol.com", "password");
        User saved = userService.createUser(jammer);

        User retrieved = userService.getUser("jammer");
        Assert.assertEquals(saved.getUserId(), retrieved.getUserId());
    }

    @Test
    public void addSecondUserTest() throws Exception {
        User reed = new User("reed", "reed@aol.com", "halo");
        User saved = userService.createUser(reed);

        User retrieved = userService.getUser("reed");
        Assert.assertEquals(saved.getUserId(), retrieved.getUserId());
    }

    @Test
    public void permissionsTest() throws Exception {
        List<Role> roles = userService.getRoles("bob");
        Assert.assertEquals(roles.size(), 2);
    }

}
