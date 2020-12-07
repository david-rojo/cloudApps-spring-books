package com.cloudapps.springbooks.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource("classpath:springbooks.properties")
})
public class SpringbooksPropertiesConfig {

}
