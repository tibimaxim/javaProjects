package ro.tibi.csv.setup;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@ComponentScan("ro.tibi.csv")
@EnableSwagger2
@EnableJpaRepositories("ro.tibi.csv.dao")
@EntityScan("ro.tibi.csv.repository")
public class StartUp extends SpringBootServletInitializer {
//
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(StartUp.class);
//    }
//
//    public static void main(String... args) {
//        System.setProperty("spring.profiles.default", System.getProperty("spring.profiles.default", "dev"));
//        final ApplicationContext applicationContext = SpringApplication.run(StartUp.class, args);
//    }

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    

}



