package dev.joseph_free_code_camp.freecodecamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
// import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
// anonotation that instructs spring to read properties from another file and specifying which file it is to read from
@PropertySources({
    @PropertySource("custom.properties"),
    @PropertySource("custom2.properties")
})
public class MyFirstService {
    
    private MyFirstClass myFirstClass;

    @Value("${appDeveloper}")
    private String developerNameFromCustomProperty;

    @Value("${developerAge}")
    private String developerAgeFromCustom2Property;

    @Value("${spring.application.name}")
    private String appProperties;

    @Value("${application.purpose}")
    private String appProperty;
    // A java special class created and managed by Spring IoC that is used to get details about the application properties, System variables, Operation system variables and active profiles
    // private Environment environment;

    // public MyFirstService(@Qualifier("myThirdClass") MyFirstClass injectedClass){
    //     this.myFirstClass = injectedClass;
    // }
    // public MyFirstService(Environment environment){
    //     this.environment = environment;
    // }
    // public String getJavaVersion(){
    //     return environment.getProperty("java.version");
    // }
    // public String getOsName(){
    //     return environment.getProperty("os.name");
    // }
    // public String getOsVersion(){
    //     return environment.getProperty("os.version");
    // }
    // public String getAppProperty(){
    //     return environment.getProperty("spring.application.name");
    // }



    public String getAppProperty() {
        return appProperty;
    }

    public String getAppProperties() {
        return appProperties;
    }

    public String getDeveloperAgeFromCustom2Property() {
        return developerAgeFromCustom2Property;
    }

    @Autowired
    public void setMyFirstClass(@Qualifier("myFirstClass") MyFirstClass injectedClass){
        this.myFirstClass = injectedClass;
    }

    public String getDeveloperNameFromCustomProperty() {
        return developerNameFromCustomProperty;
    }

    public String tellAStory(){
        return "the class is saying : " + myFirstClass.sayHello();
    }

}
