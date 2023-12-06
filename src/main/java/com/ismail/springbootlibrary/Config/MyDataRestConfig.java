package com.ismail.springbootlibrary.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.awt.print.Book;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer
{
    private String theAllowedOrigins="http://localhost:3000";
@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configuration, CorsRegistry cors)
    {
        HttpMethod[] theUnsupportedActions={
                HttpMethod.POST,
                HttpMethod.PUT,
                HttpMethod.DELETE,
                HttpMethod.PUT};
        configuration.exposeIdsFor(com.ismail.springbootlibrary.entity.Book.class);
        configuration.exposeIdsFor(com.ismail.springbootlibrary.entity.Review.class);
        disableHttpMethods(com.ismail.springbootlibrary.entity.Book.class,configuration,theUnsupportedActions);
        disableHttpMethods(com.ismail.springbootlibrary.entity.Review.class,configuration,theUnsupportedActions);

        cors.addMapping(configuration.getBasePath()+"/**")
                .allowedOrigins(theAllowedOrigins);
     }
     private void disableHttpMethods(Class theClass,RepositoryRestConfiguration configuration,HttpMethod[] theUnsupportedActions)
     {
         configuration.getExposureConfiguration()
                 .forDomainType(theClass)
                 .withItemExposure(((metdata, httpMethods) ->
                         httpMethods.disable(theUnsupportedActions)))
                 .withCollectionExposure((metdata, httpMethods) ->
                         httpMethods.disable(theUnsupportedActions));
     }
}
