package org.example.mystarter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class MyStarterAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(MyStarterAutoConfiguration.class));

    @Test
    void autoConfigurationRegistersServiceWithDefaultMessage() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(MyStarterService.class);
            assertThat(context.getBean(MyStarterService.class).sayHello())
                    .isEqualTo("Hello from MyStarter!");
        });
    }

    @Test
    void autoConfigurationUsesCustomMessage() {
        contextRunner
                .withPropertyValues("my.starter.message=Custom message")
                .run(context -> {
                    assertThat(context).hasSingleBean(MyStarterService.class);
                    assertThat(context.getBean(MyStarterService.class).sayHello())
                            .isEqualTo("Custom message");
                });
    }

    @Test
    void autoConfigurationDisabledWhenEnabledIsFalse() {
        contextRunner
                .withPropertyValues("my.starter.enabled=false")
                .run(context -> assertThat(context).doesNotHaveBean(MyStarterService.class));
    }
}
