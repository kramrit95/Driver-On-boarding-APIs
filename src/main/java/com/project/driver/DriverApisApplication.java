package com.project.driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.project.driver.config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class DriverApisApplication {

	private static final Logger logger = LoggerFactory.getLogger(DriverApisApplication.class);

	public static void main(String[] args) {
		logger.info("Driver onboarding service is starting");
		SpringApplication.run(DriverApisApplication.class);
		logger.info("Service started");
	}
}