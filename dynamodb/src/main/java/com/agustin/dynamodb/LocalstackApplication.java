package com.agustin.dynamodb;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;
import software.amazon.awssdk.services.dynamodb.model.*;

@SpringBootApplication
@EnableConfigurationProperties(DynamoProperties.class)
public class LocalstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalstackApplication.class, args);
	}

	@Bean
	DynamoDbClient dynamoDbClient(DynamoProperties dynamoProperties){
		DynamoDbClientBuilder builder = DynamoDbClient.builder();

		if (dynamoProperties.getUri() != null){
			builder.endpointOverride(dynamoProperties.getUri());
		}

		return builder.build();
	}

	@Bean
	ApplicationRunner applicationRunner(DynamoDbClient dynamoDbClient){
		System.out.println("Tables in DynamoDB: ");

		return args -> {
			CreateTableRequest createTableRequest = CreateTableRequest
					.builder()
					.tableName("users")
					.keySchema(KeySchemaElement
							.builder()
							.keyType(KeyType.HASH)
							.attributeName("id")
							.build())
					.attributeDefinitions(AttributeDefinition
							.builder()
							.attributeName("id")
							.attributeType(ScalarAttributeType.S)
							.build())
					.provisionedThroughput(ProvisionedThroughput
							.builder()
							.writeCapacityUnits(5L)
							.readCapacityUnits(5L)
							.build())
					.build();

			dynamoDbClient.createTable(createTableRequest);

			dynamoDbClient
					.listTables()
					.tableNames()
					.forEach(System.out::println);
		};
	}

}
