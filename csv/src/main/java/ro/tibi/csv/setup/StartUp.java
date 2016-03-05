package ro.tibi.csv.setup;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.google.common.base.Predicates;

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

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("15MB");
		factory.setMaxRequestSize("15MB");
		return factory.createMultipartConfig();
	}

}
