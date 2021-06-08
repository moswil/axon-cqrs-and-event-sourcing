package com.github.moswil.learn.bankacc.query.api;

import com.github.moswil.learn.bankacc.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EntityScan(basePackages = "com.github.moswil.learn.bankacc.core.models")
@Import(AxonConfig.class)
public class BankAccountQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountQueryApplication.class, args);
	}

}
