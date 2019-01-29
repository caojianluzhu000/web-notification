package yixuan.project.webnotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebNotificationApplication.class, args);
	}

}

