// com/example/appointmentsystem/config/DocumentDBConfig.java
package com.example.appointmentsystem.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


//@Configuration
//public class DocumentDBConf {
//    private final MongoProperties mongoProperties;
//    private final MongoDBProperties dbProperties;
//
//    public DocumentDBConf(MongoProperties mongoProperties, MongoDBProperties dbProperties) {
//        this.mongoProperties = mongoProperties;
//        this.dbProperties = dbProperties;
//    }
//
//    @Bean
//    public MongoClientSettings mongoClientSettings() {
//        SSLConfigurationUtil.configureSslProperties(dbProperties.getTrustStorePassword());
//
//        return MongoClientSettings.builder()
//                .applyToSslSettings(builder -> builder
//                        .enabled(true)
//                        .invalidHostNameAllowed(true))
//                .applyToConnectionPoolSettings(builder -> builder
//                        .maxSize(50)
//                        .minSize(10))
//                .build();
//    }
//}
//@Configuration
//public class DocumentDBConf {
//
//    private MongoProperties properties;
//
//    public static final String KEY_STORE_TYPE = "/tmp/certs/rds-truststore.jks";
//    public static final String DEFAULT_KEY_STORE_PASSWORD = "yourSecurePassword";
//
//    public DocumentDBConf(final MongoProperties properties) {
//        super();
//        this.properties = properties;
//    }
//
//    @Bean
//    public MongoClientSettings mongoClientSettings() {
//        setSslProperties();
//
//
//        return MongoClientSettings.builder()
//                .applyToSslSettings(builder -> builder.enabled(true))
//                .applyConnectionString(new com.mongodb.ConnectionString(mongoUri))
//                .build();
//    }
//
//    private static void setSslProperties() {
//        System.setProperty("javax.net.ssl.trustStore", KEY_STORE_TYPE);
//        System.setProperty("javax.net.ssl.trustStorePassword",
//                DEFAULT_KEY_STORE_PASSWORD);
//    }
//    @Bean
//    public MongoPropertiesClientSettingsBuilderCustomizer mongoPropertiesCustomizer(final MongoProperties properties) {
//        return new MongoPropertiesClientSettingsBuilderCustomizer(properties);
//    }
//}




//@Configuration
//public class DocumentDBConf {
//
//    private final MongoProperties properties;
//
//    public DocumentDBConf(final MongoProperties properties) {
//        this.properties = properties;
//    }
//
//    @Bean
//    public MongoClientSettings mongoClientSettings() {
//        setSslProperties();
//
//        String mongoUri = properties.getUri(); // Retrieve from application properties
//        return MongoClientSettings.builder()
//                .applyToSslSettings(builder -> builder.enabled(true))
//                .applyConnectionString(new ConnectionString(mongoUri))
//                .build();
//    }
//
//    private static void setSslProperties() {
//        String keyStoreType = System.getenv("TRUST_STORE_PATH"); // Use environment variable
//        String keyStorePassword = System.getenv("TRUST_STORE_PASSWORD"); // Use environment variable
//
//        System.setProperty("javax.net.ssl.trustStore", keyStoreType);
//        System.setProperty("javax.net.ssl.trustStorePassword", keyStorePassword);
//    }
//}
//@Configuration
//public class DocumentDBConf {
//
//    private final MongoProperties properties;
//
//    public DocumentDBConf(final MongoProperties properties) {
//        this.properties = properties;
//    }
//
//    @Bean
//    public MongoClientSettings mongoClientSettings() {
//        setSslProperties();
//
//        String mongoUri = properties.getUri();
//        if (mongoUri == null) {
//            throw new IllegalStateException("MongoDB URI is not configured");
//        }
//
//        return MongoClientSettings.builder()
//                .applyToSslSettings(builder -> builder.enabled(true))
//                .applyConnectionString(new ConnectionString(mongoUri))
//                .build();
//    }
//
//    private static void setSslProperties() {
//        String trustStorePath = System.getenv("TRUST_STORE_PATH");
//        String trustStorePassword = System.getenv("TRUST_STORE_PASSWORD");
//
//        if (trustStorePath == null || trustStorePassword == null) {
//            throw new IllegalStateException("TRUST_STORE_PATH or TRUST_STORE_PASSWORD is not set");
//        }
//
//        System.setProperty("javax.net.ssl.trustStore", trustStorePath);
//        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
//    }
//}
@Configuration
public class DocumentDBConf {

    private MongoProperties properties;

    public static final String KEY_STORE_TYPE = "C:\\tmp\\certs\\rds-truststore.jks";

    public static final String DEFAULT_KEY_STORE_PASSWORD = "12345678";

    public DocumentDBConf(final MongoProperties properties) {
        super();
        this.properties = properties;
    }

    @Bean
    public MongoClientSettings mongoClientSettings() {
        setSslProperties();
        return MongoClientSettings.builder()
                .applyToSslSettings(builder -> builder.enabled(true))
                .build();
    }

    private static void setSslProperties() {
        System.setProperty("javax.net.ssl.trustStore", KEY_STORE_TYPE);
        System.setProperty("javax.net.ssl.trustStorePassword",
                DEFAULT_KEY_STORE_PASSWORD);
    }
    @Bean
    public MongoPropertiesClientSettingsBuilderCustomizer mongoPropertiesCustomizer(final MongoProperties properties) {
        return new MongoPropertiesClientSettingsBuilderCustomizer(properties);
    }







}
