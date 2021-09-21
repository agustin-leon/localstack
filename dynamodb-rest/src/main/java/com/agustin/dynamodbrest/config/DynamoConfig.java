package com.agustin.dynamodbrest.config;

import com.agustin.dynamodbrest.DynamoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

@Configuration
@EnableConfigurationProperties(DynamoProperties.class)
public class DynamoConfig {

    @Bean
    DynamoDbClient dynamoDbClient(DynamoProperties dynamoProperties){
        DynamoDbClientBuilder builder = DynamoDbClient.builder();

        if (dynamoProperties.getUri() != null){
            builder.endpointOverride(dynamoProperties.getUri());
        }

        return builder.build();
    }

}
