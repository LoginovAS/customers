/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sbx.customers.web.config;

import com.sun.faces.config.FacesInitializer;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.sbx.customers.app.config.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class PortalWebAppIntializer extends FacesInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(DatabaseConfig.class, PortalWebMVCConfig.class);
        rootContext.setServletContext(servletContext);

        servletContext.addListener(new ContextLoaderListener(rootContext));

        ServletRegistration.Dynamic facesServlet = servletContext.addServlet("faces-servlet", new FacesServlet());
        facesServlet.setLoadOnStartup(1);
        facesServlet.addMapping("*.xhtml", "*.jsf");
    }
    
}
