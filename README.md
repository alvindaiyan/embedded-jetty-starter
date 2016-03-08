# Embedded-jetty-starter [![Build Status](https://travis-ci.org/alvindaiyan/embedded-jetty-starter.svg?branch=master)](https://travis-ci.org/alvindaiyan/embedded-jetty-starter)

Embedded Jetty Starter(ejs) is a simple starter project using 
- [Java8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), 
- [Maven3](https://maven.apache.org/download.cgi),
- [Jetty9](https://eclipse.org/jetty/), 
- [Jersey2](https://jersey.java.net/) with 
- [Swagger 1.5](https://github.com/swagger-api/swagger-core) and, 
- [Swagger UI](https://github.com/swagger-api/swagger-ui). 
EJS provides a simple environment to build, test and run a Java-based web server. 

To know more about Swagger and Swagger annotation, please go to 
[swagger annotation documentation](https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X).

### This is a Java project. The reason github has marked this as a Js project is because a built Swagger UI project is included. EJS will serve the Swagger UI as static resources.

# How to run

If you run from IDE, simply navigate to JettyServerMain and run. 
To adjust the jetty configurations, you can construct you own JettyConfig.class, for example:
```java
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
```

Or you can run ```mvn install``` and run ```java -jar /your/build/file.jar```.

To be noticed, further plan is to allow user to customize Swagger Configuration as well.

According to above setups, the swagger ui will serve at [http://localhost:8080/v1/api-doc/index.html](http://localhost:8080/v1/api-doc/index.html). And the swagger.json is served at [http://localhost:8080/api-doc/swagger.json](http://localhost:8080/api-doc/swagger.json)

# Libraries used and Version

```xml
 <properties>
        <log4j.version>2.4</log4j.version>
        <slf4j.version>1.7.12</slf4j.version>
        <jersey.version>2.22.1</jersey.version>
        <jetty.version>9.3.4.v20151007</jetty.version>
        <swagger.version>1.5.7</swagger.version>
        <junit.version>4.12</junit.version>
    </properties>
```

# Contribution

Welcome to new pull request.

