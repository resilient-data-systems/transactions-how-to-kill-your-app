package uk.co.resilientdatasystems.thtkya.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "uk.co.resilientdatasystems" }, excludeFilters = { @Filter(Configuration.class) })
public class MainConfig {

}
