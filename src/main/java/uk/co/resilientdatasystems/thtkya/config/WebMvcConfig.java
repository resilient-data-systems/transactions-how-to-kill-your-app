package uk.co.resilientdatasystems.thtkya.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configurable
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 14 d cache control
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico").setCachePeriod(1_209_600);
    }

}
