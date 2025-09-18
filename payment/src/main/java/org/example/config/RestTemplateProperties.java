package org.example.config;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestTemplateProperties {

  private String url;
  private Duration connectTimeout;
  private Duration readTimeout;
}
