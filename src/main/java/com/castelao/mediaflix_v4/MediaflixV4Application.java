package com.castelao.mediaflix_v4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class MediaflixV4Application {

	public static void main(String[] args) {
		SpringApplication.run(MediaflixV4Application.class, args);

	}

}
