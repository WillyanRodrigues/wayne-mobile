package br.com.vivo.waynemobile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket productAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.vivo.waynemobile.controller"))
				.paths(PathSelectors.regex(".*"))
				.build()
				.apiInfo(metaInfo());
	}
	
	public ApiInfo metaInfo() {
		return new ApiInfoBuilder()
				.title("Wayne Mobile API")
				.description("API responsible for registering and deleting CDR records and it's User and Lines")
				.version("1.0.0")
				.build();
	}
}
