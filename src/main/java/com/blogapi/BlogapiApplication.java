package com.blogapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // 1.in this configuration file , 2.we can create here @ bean annotation.
public class BlogapiApplication {

	public static void main(String[] args) {

		SpringApplication.run(BlogapiApplication.class, args);
	}

	// for model mapper library , we  can create a bean because of it is external library.

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}
}

//mysql workbench keeps open otherwise we will give error create breakpoint
