package com.resell.hp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/*설정파일 선언하는것*/
@Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
