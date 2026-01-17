package dev.joseph_free_code_camp.freecodecamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.http.ResponseEntity; 
// import dev.joseph_free_code_camp.freecodecamp.pre_req.MyFirstService;


@SpringBootApplication
public class FreecodecampApplication {

	public static void main(String[] args) {
		// System.out.println(myFirstService.getJavaVersion());
		// System.out.println(myFirstService.getOsName());
		// System.out.println(myFirstService.getOsVersion());
		// System.out.println(myFirstService.getAppProperty());
		SpringApplication.run(FreecodecampApplication.class, args);
		// MyFirstService myFirstService = appContext.getBean(MyFirstService.class);
		// ResponseEntity<String> entity;
		// System.out.println(myFirstService.tellAStory());
		// System.out.println(myFirstService.getDeveloperNameFromCustomProperty());
		// System.out.println(myFirstService.getDeveloperAgeFromCustom2Property());
		// System.out.println(myFirstService.getAppProperties());
		// System.out.println(myFirstService.getAppProperty());
		

	}

}
