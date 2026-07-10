package org.example.mystarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MyStarterProperties.class)
@ConditionalOnProperty(prefix = "my.starter", name = "enabled", havingValue = "true", matchIfMissing = true)
public class MyStarterAutoConfiguration {

    @Bean
    public MyStarterService myStarterService(MyStarterProperties properties) {
        return new MyStarterService(properties);
    }
}
