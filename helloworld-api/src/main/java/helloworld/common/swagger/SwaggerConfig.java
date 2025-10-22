package helloworld.common.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
    public GroupedOpenApi v1Api() {
        return GroupedOpenApi.builder()
                             .group("v1")
                             .pathsToMatch("/**/v1/**")
                             .build();
    }

    @Bean
    public OpenAPI helloWorldOpenAPI() {
    	
        List<Server> serverList = new ArrayList<>();
        
        SecurityScheme securityScheme = new SecurityScheme().type(Type.HTTP)
                                                            .scheme("bearer")
                                                            .in(In.HEADER)
                                                            .name("Authorization");
        
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization");

		return new OpenAPI().info(
								   new Info().title("Hello World Java Development Unit API Swagger")
								    	     .description("자바 단위 개발 내용 WIKI API 입니다.").version("v1")
								    	     .license(
								    	     		   new License().name("Apache 2.0")
								    	     		                .url("http://springdoc.org")) 
							     )
				
                            .components(new Components().addSecuritySchemes("Authorization", securityScheme))
                            .addSecurityItem(securityRequirement)
                            .servers(serverList);
    }
}
