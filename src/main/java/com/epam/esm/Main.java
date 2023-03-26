package com.epam.esm;

import com.epam.esm.config.AppConfig;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws Exception {

        //LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);
        tomcat.setSilent(false);

        File baseDir = Files.createTempDirectory("embedded-tomcat").toFile();

        Context context= tomcat.addWebapp("/api",baseDir.getAbsolutePath());

        // Create a Spring application context
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppConfig.class);

        // Create a DispatcherServlet and register it with Tomcat
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

       //Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet).setLoadOnStartup(1);
        Tomcat.addServlet(context,"dispatcherServlet",dispatcherServlet).setLoadOnStartup(1);

        context.addServletMappingDecoded("/*", "dispatcherServlet");


        tomcat.start();
        tomcat.getServer().await();
    }
}