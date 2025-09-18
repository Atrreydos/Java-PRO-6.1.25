package org.example.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("integrations.clients")
@RequiredArgsConstructor
@Getter
public class RestTemplateClientConfigurationProperties {

  private final RestTemplateProperties productClient;
}
