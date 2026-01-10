package dev.joseph_free_code_camp.freecodecamp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

// The @Configuration annotation allows spring to scan this class for any bean to be added to the IoC container
@Configuration
// Profile can be set at the class level , look at the commented example below
// @Profile("dev")
public class ApplicationConfig {
    // declaring a bean with its name and making it specific to dev environment
    @Bean
    @Qualifier("Bean1")
    @Profile("dev")
    MyFirstClass myFirstClass(){
        return new MyFirstClass("Joseph");
    }

    
    // declaring a bean with its name and making it specific to test environment
    @Bean
    @Qualifier("Bean2")
    @Profile("test")
    MyFirstClass mySecondClass(){
        return new MyFirstClass("Aniekan");
    }

    
    // declaring a bean with its name and making it available to all environment
    @Bean
    // @Primary
    @Qualifier("Bean3")
    MyFirstClass myThirdClass(){
        return new MyFirstClass("Ukpong");
    }
}
