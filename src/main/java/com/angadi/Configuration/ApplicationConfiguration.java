package com.angadi.Configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfiguration {
	
	@SuppressWarnings("rawtypes")
	public Docket getDocket() {
		Contact contact = new Contact("Raju mb", null, "rajugowda0212@gmail.com");
		List<VendorExtension> extensions = new ArrayList<>();
		ApiInfo apiInfo = new ApiInfo("Shopping App - Angadi API",
				
				"Angadi is an e-commerce project(personal), Usefull for merchants who wants to sell their \r\n"
				+ "products directly to the consumer without any mediators. \r\n"
				+ "Here the merchant will be able to create a virtual shop, and will be able to \r\n"
				+ "sell the products online to the consumer. This platform is for the small-scale \r\n"
				+ "businesses who wants to trade within a particular location.",
				
				"1.0 first", "", contact, "", "", extensions);
		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.angadi")).build()
				.apiInfo(apiInfo).useDefaultResponseMessages(false);
	}
	
}
