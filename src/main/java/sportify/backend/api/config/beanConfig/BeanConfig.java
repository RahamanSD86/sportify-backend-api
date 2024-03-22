package sportify.backend.api.config.beanConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import sportify.backend.api.config.Constants;

@Configuration
public class BeanConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public WebClient cricketApiClient() {
        return WebClient.builder()
                .baseUrl(Constants.IPL_BASE_URL) // Replace with your API's base URL
                .build();
    }
}
