package com.d3.d3.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//Falta lo de que todo sea .html

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationContext.class};
    }
    
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
    
    protected String[] getServletMappings() {
        return new String[]{"*.html"};//{ "*.html" };
    }

}
