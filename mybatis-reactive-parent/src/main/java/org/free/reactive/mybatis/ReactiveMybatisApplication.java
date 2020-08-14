package org.free.reactive.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveMybatisApplication {

	public static void main(String[] args) {
		System.out.println("--------------------- start\t-----------------------");
		SpringApplication.run(ReactiveMybatisApplication.class, args);
		System.out.println("--------------------- end\t-----------------------");
	}

}
