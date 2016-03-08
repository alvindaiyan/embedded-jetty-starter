package com.yan.jetty.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;

/**
 * Created by yan on 3/03/16.
 */
public class JettyServer {

    private final static Logger logger = LoggerFactory.getLogger(JettyServer.class);
    private static JettyServer server;

    private JettyConfig jettyConfig;
    private static String baseUrl;


    // private constructor
    private JettyServer(JettyConfig jettyConfig) {
        this.jettyConfig = jettyConfig;
    }


    public static JettyServer get(JettyConfig jettyConfig) {
        if (server == null) {
            server = new JettyServer(jettyConfig);
        }
        return server;
    }

    public String baseUrl() {
        if (this.baseUrl == null) {
            StringBuilder baseUrl = new StringBuilder();
            baseUrl.append("http://")
                    .append(jettyConfig.getIpAddress())
                    .append(":")
                    .append(jettyConfig.getPort())
                    .append("/")
                    .append(jettyConfig.getApiPrefix())
                    .append("/")
                    .append(jettyConfig.getVersion());
            this.baseUrl = baseUrl.toString();
        }

        return this.baseUrl;
    }

    public void start() throws Exception {
        // todo: validate jettyConfig first

        logger.info("Start embedded jetty server for " + jettyConfig.getProjectName() + " at " +
                baseUrl() + "/*");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");


        // Create a basic jetty server object that will listen on port 8080.
        // Note that if you set this to port 0 then a randomly available port
        // will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(jettyConfig.getPort());
        server.setHandler(context);

        // setup the api/context path
        ServletHolder jerseyServlets = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class,
                "/" + jettyConfig.getApiPrefix() + "/" + jettyConfig.getVersion() + "/*");
        jerseyServlets.setInitOrder(1);


        // the following code is to suggest whether there is container request filter used
        // for jersey servlet. If need, please uncomment the following code and give the
        // right path
//        jerseyServlets.setInitParameter("javax.ws.rs.container.ContainerRequestFilter",
//                jettyConfig.getContainerRequestFilterClassPath());

        // Tells the Jersey Servlet which REST service/class to load
        jerseyServlets.setInitParameter("jersey.config.server.provider.packages",
                jettyConfig.getProviders());

        // configure the swagger-ui static web project, this use default file name called api-doc
        // also this is using the default url to configure the project
        // you can change if needed
        ServletHolder swaggerFiles = new ServletHolder("swagger-ui", DefaultServlet.class);
        swaggerFiles.setInitParameter("resourceBase", getFilePath("api-doc"));
        swaggerFiles.setInitParameter("dirAllowed", "true");
        swaggerFiles.setInitParameter("pathInfoOnly", "true");
        context.addServlet(swaggerFiles, "/" + jettyConfig.getVersion() + "/api-doc/*");


        // Lastly, the default servlet for root content (always needed, to satisfy servlet spec)
        // It is important that this is last.
        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("dirAllowed", "true");
        context.addServlet(holderPwd, "/");

        // swagger.json
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(jettyConfig.getVersion());
        beanConfig.setResourcePackage(jettyConfig.getProviders());
        beanConfig.setScan(true);
        beanConfig.setBasePath("/" + jettyConfig.getApiPrefix() + "/" + jettyConfig.getVersion());
        beanConfig.setDescription("Swagger API Automated Document for "
                + jettyConfig.getProjectName());
        beanConfig.setTitle(jettyConfig.getProjectName());

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages(jettyConfig.getProviders(),
                ApiListingResource.class.getPackage().getName());

        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder entityBrowser = new ServletHolder(servletContainer);
        resourceConfig.register(MultiPartFeature.class); // this is not necessary if there is no upload document api
        context.addServlet(entityBrowser, "/api-doc/*");

        logger.info("Started " + jettyConfig.getProjectName() + " api doc at: "
                + "http://localhost:" + jettyConfig.getPort() +"/v1/api-doc/index.html");
        logger.info("The swagger.json file is served at http://localhost:"
                + jettyConfig.getPort() + "/api-doc/swagger.json");

        // Start things up!
        server.start();
        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See
        // http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
        server.join();

    }


    // get file path from file name
    public String getFilePath(String fname) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fname).getFile());
        return file.getAbsolutePath();
    }

}
