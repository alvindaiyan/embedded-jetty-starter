package com.yan.server;

import com.yan.jetty.server.JettyConfig;
import com.yan.jetty.server.JettyServer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by yan on 3/03/16.
 */
public class JettyServerTest {



    @Test
    public void starterTest() {
        JettyConfig jettyConfig = new JettyConfig();

        jettyConfig.setApiPrefix("api");
        jettyConfig.setIpAddress("localhost");
        jettyConfig.setVersion("v1");
        jettyConfig.setProjectName("Embedded Jetty Starter");
        jettyConfig.setPort(8080);
        jettyConfig.setProviders("com.yan.services");

        try {
            JettyServer.get(jettyConfig).start();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }
}
