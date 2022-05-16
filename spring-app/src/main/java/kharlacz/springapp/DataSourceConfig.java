package kharlacz.springapp;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.sql.DataSource;
import java.io.File;

@Configuration
@AllArgsConstructor
public class DataSourceConfig {

//    @Bean
//    public static DataSource getDataSource() throws ConfigurationException {
//        // Manual bean creation in order to get env variables
//        final var envVars = System.getenv();
//        final var dataSourceBuilder = DataSourceBuilder.create();
//        final var url = "jdbc:mysql://" + envVars.get("CAKEHOUSE_DATABASE_URL") +
//                "/cake_house?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC" +
//                "&createDatabaseIfNotExist=true";
//        dataSourceBuilder.url(url);
//        dataSourceBuilder.username(envVars.get("CAKEHOUSE_DATABASE_USERNAME"));
//        dataSourceBuilder.password(envVars.get("CAKEHOUSE_DATABASE_PASSWORD"));
//        return dataSourceBuilder.build();
//    }
}