package de.pvs.demo;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@Testcontainers
@QuarkusTestResource(DemoTestResource.class)
@TestProfile(DemoTestProfile.class)
public class DemoTest {

    @ConfigProperty(name = "pg_jdbcurl")
    String jdbcurl;

    @ConfigProperty(name = "pg_username")
    String username;

    @ConfigProperty(name = "pg_password")
    String password;

    @Test
    public void shouldPingPostgreSQL() throws Exception {
        try (Connection con = DriverManager.getConnection(jdbcurl, username, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT VERSION()")) {

            if (rs.next()) {
                assertTrue(rs.getString(1).contains("PostgreSQL 11"));
            } else {
                throw new Exception();
            }
        }
    }
}
