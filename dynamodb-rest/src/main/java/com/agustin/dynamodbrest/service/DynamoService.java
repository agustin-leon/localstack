package com.agustin.dynamodbrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamoService {
    @Autowired
    DynamoDbClient dynamoDbClient;

    public String hello(){
        return "Hello!";
    }

    public List<String> listTables(){
        List<String> result = new ArrayList<>();

        dynamoDbClient
                .listTables()
                .tableNames()
                .forEach(result::add);

        return result;
    }

    public String createTable(String tableName, String id){
        CreateTableRequest createTableRequest = CreateTableRequest
                .builder()
                .tableName(tableName)
                .keySchema(KeySchemaElement
                        .builder()
                        .keyType(KeyType.HASH)
                        .attributeName(id)
                        .build())
                .attributeDefinitions(AttributeDefinition
                        .builder()
                        .attributeName(id)
                        .attributeType(ScalarAttributeType.S)
                        .build())
                .provisionedThroughput(ProvisionedThroughput
                        .builder()
                        .writeCapacityUnits(5L)
                        .readCapacityUnits(5L)
                        .build())
                .build();

        return dynamoDbClient.createTable(createTableRequest).tableDescription().tableName();
    }

    public boolean deleteTable(String name){
        DeleteTableRequest deleteTableRequest = DeleteTableRequest
                .builder()
                .tableName(name)
                .build();

        dynamoDbClient.deleteTable(deleteTableRequest);

        return true;
    }
}
