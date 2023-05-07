package dev.tobhai.modulith.customer;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CustomerNotificationServiceTestConfig {

  @Bean
  public Clock clock() {
    var fixedTime = Instant.parse("2023-01-02T10:00:00Z");
    return Clock.fixed(fixedTime, ZoneId.of("UTC"));
  }
}
