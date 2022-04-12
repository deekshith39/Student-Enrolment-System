package com.example.StudentEnrolmentSystem;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.management.QueryExp;

import java.util.function.Predicate;

import static javax.management.Query.or;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class StudentEnrolmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentEnrolmentSystemApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(Predicates.not(PathSelectors.regex("/error.*?")))
				.apis(RequestHandlerSelectors.basePackage("com.example"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Student Enrolment System")
				.description("Student Enrolment system can be used by students to enter and view their enrolment information, it can be used by members (Admins) of admissions department to onboard and manage new students and for course and fee maintenance.")
				.termsOfServiceUrl(null)
				.license(null)
				.licenseUrl(null).version("1.0").build();
	}


}
