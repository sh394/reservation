package com.duke.booking.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.duke.booking.dao", "com.duke.booking.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
