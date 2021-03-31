package com.youbao;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;



@ComponentScan("com.youbao")
@SpringBootApplication
//@EntityScan("com.qx.server.dal.model")

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

