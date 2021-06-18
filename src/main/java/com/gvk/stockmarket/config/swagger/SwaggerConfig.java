package com.gvk.stockmarket.config.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/****
	 * Use http://localhost:6060/SM/swagger-ui.html to launch swagger application
	 */
	
	@Bean
	public Docket createDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gvk.stockmarket"))
				.paths(PathSelectors.regex("/apis.*"))
				.build()
				.apiInfo(apiInfo())
				;
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Stock Market - Restful",
				"it is shows restful services of this application", 
				"1.0", 
				"http://localhost:8080/StockMarket", 
				"+91-XXXXXXXXXX", 
				"License - NA", 
				"License Url - NA");
	}

}
