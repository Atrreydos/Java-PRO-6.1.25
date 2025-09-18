package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

  private final RestTemplateClientConfigurationProperties configurationProperties;
  private final RestClientErrorHandler errorHandler;

  @Bean
  public RestTemplate productTemplate() {
    var productClientProperties = configurationProperties.getProductClient();

    return new RestTemplateBuilder()
        .rootUri(productClientProperties.getUrl())
        .setConnectTimeout(productClientProperties.getConnectTimeout())
        .setReadTimeout(productClientProperties.getReadTimeout())
        .errorHandler(errorHandler)
        .build();
  }
}
