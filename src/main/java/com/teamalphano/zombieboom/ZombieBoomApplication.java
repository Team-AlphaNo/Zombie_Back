   package com.teamalphano.zombieboom;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.teamalphano.zombieboom")
public class ZombieBoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZombieBoomApplication.class, args);
	}

}
