package empapp;

import com.github.dockerjava.api.model.Bind;
import com.redis.testcontainers.RedisContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {
        var container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                .withExposedPorts(5432)
                .withDatabaseName("employees")
                .withUsername("employees")
                .withPassword("employees")
                .withReuse(true);
        container.setPortBindings(List.of("5432:5432"));
        return container;
    }

    @Bean
    @ServiceConnection("redis")
    RedisContainer redisContainer() {
        return new RedisContainer(DockerImageName.parse("redis:latest"))
                .withExposedPorts(6379)
                .withReuse(true);
    }

}
