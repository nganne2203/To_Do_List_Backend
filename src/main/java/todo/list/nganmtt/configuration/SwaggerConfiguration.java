package todo.list.nganmtt.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class SwaggerConfiguration {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("To Do List API")
                        .version("1.0")
                        .description("API documentation for To Do List Application")
                        .contact(new Contact()
                                .name("nganmtt")
                                .email("thanhngan.pt2004@gmail.com")
                                .url("https://www.nganmtt.com")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/nganne2203/To_Do_List_Backend")
                );
    }
}
