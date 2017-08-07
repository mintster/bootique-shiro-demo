package com.nixmash.shiro;

import com.nixmash.shiro.controller.GeneralController;
import io.bootique.BQRuntime;
import io.bootique.test.junit.BQTestFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by daveburke on 6/26/17.
 */
@RunWith(JUnit4.class)
public class ModuleTest {

    @Rule
    public BQTestFactory testFactory = new BQTestFactory();

    @Test
    public void loadModuleTest() {
        BQRuntime runtime = testFactory.app("--server", "--config=classpath:test.yml")
                .autoLoadModules()
                .module(binder -> binder.bind(GeneralController.class))
                .createRuntime();

        GeneralController controller = runtime.getInstance(GeneralController.class);
        Assert.assertTrue(controller.sayHello().equals("hello!"));

    }

}
