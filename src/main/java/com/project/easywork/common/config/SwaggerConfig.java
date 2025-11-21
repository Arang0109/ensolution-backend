package com.project.easywork.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
  
  @Bean
  public OpenAPI openAPI() {
    Server productionServer = new Server()
        .url("https://easywork-server-870071577371.asia-northeast1.run.app")
        .description("🚀 Cloud Run Production Server");
    
    Server localServer = new Server()
        .url("http://localhost:8080")
        .description("💻 Local Development Server");
    
    return new OpenAPI()
        .info(new Info()
            .title("EasyWork API")
            .description("환경 솔루션 프로젝트 API 문서")
            .version("v1.0"))
        .servers(List.of(productionServer, localServer));
  }
}