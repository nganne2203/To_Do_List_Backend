package todo.list.nganmtt.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {

    private final AppProperties appProperties;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Bean
    public OpenAPI openApi() {
        String serverUrl = appProperties.getBackendUrl() != null
                ? appProperties.getBackendUrl()
                : "http://localhost:8080";

        if (contextPath != null && !contextPath.isEmpty() && !contextPath.equals("/")) {
            serverUrl = serverUrl + contextPath;
        }

        return new OpenAPI()
                .addServersItem(new Server()
                        .url(serverUrl)
                        .description("To Do List API Server"))
                .addServersItem(new Server()
                        .url("http://localhost:8080" + contextPath)
                        .description("To Do List API Server")
                )
                .info(new Info()
                        .title("To Do List API")
                        .version("1.0")
                        .description("API documentation for To Do List Application")
                        .contact(new Contact()
                                .name("Mai Thị Thanh Ngân")
                                .email("thanhngan.pt2004@gmail.com")
                                .url("https://www.nganmtt.com")
                        )
                        .license(new License()
                                .name("Apache 2.0 License")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/nganne2203/To_Do_List_Backend")
                );
    }
}
