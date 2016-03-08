package yan.server;

import com.yan.jetty.server.JettyConfig;
import com.yan.jetty.server.JettyServer;


/**
 * Created by yan on 3/03/16.
 */
public class JettyServerMain {




    public static void main(String[] arg) {
        JettyConfig jettyConfig = new JettyConfig();

        jettyConfig.setApiPrefix("api");
        jettyConfig.setIpAddress("localhost");
        jettyConfig.setVersion("v1");
        jettyConfig.setProjectName("Embedded Jetty Starter");
        jettyConfig.setPort(8080);
        jettyConfig.setProviders("yan.services");

        try {
            JettyServer.get(jettyConfig).start();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
