package com.teamalphano.zombieboom;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
@Slf4j
@MapperScan(basePackages = "com.teamalphano.zombieboom.mapper") // MyBatis Mapper만 스캔
public class ZombieBoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZombieBoomApplication.class, args);
	}

	@PostConstruct
	public void setTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
