package com.example.Learn.WaterDeliveryApp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Ecommerce {
	private static final Logger logger = LoggerFactory.getLogger(Ecommerce.class);

	public static void main(String[] args) {
		SpringApplication.run(Ecommerce.class, args);
	}

	/*
	@Bean
	public CommandLineRunner sendStartupEmail(EmailService emailService) {
		return args -> {
			String to = "bampohansahjeffrey@gmail.com"; // Replace with the recipient's email
			String subject = "Application Startup Notification";
			String text = "The scholarship application has started successfully!";
			emailService.sendEmail(to, subject, text);
			logger.info("Startup email sent successfully!");
		};
	}

	 */
}