package de.pvs.demo;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.Map;

public class DemoTestResource implements QuarkusTestResourceLifecycleManager {

    @Container
    private final PostgreSQLContainer pg = new PostgreSQLContainer<>("postgres:11")
            .withDatabaseName("vintageStoreDB")
            .withUsername("vintage")
            .withPassword("vintage")
            .withExposedPorts(5432);

    @Override
    public Map<String, String> start() {
        pg.start();

        return Map.of("pg_jdbcurl", pg.getJdbcUrl(), "pg_username", pg.getUsername(), "pg_password", pg.getPassword());
    }

    @Override
    public void stop() {
        pg.close();
    }
}
