package com.wxun.rorschach;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhuwx
 */
@SpringBootApplication
@MapperScan("com.wxun.rorschach.dao")
public class RorschachApplication {

	public static void main(String[] args) {
		SpringApplication.run(RorschachApplication.class, args);

	}

}
