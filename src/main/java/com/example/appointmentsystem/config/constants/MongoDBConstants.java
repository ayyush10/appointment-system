package com.example.appointmentsystem.config.constants;

public final class MongoDBConstants {
    private MongoDBConstants() {} // Prevent instantiation

    public static final String TRUST_STORE_PATH = "/tmp/certs/rds-truststore.jks";
    public static final String SSL_TRUST_STORE_PROPERTY = "javax.net.ssl.trustStore";
    public static final String SSL_TRUST_STORE_PASSWORD_PROPERTY = "javax.net.ssl.trustStorePassword";
}
