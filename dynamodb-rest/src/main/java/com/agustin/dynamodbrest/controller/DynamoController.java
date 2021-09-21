package com.agustin.dynamodbrest.controller;

import com.agustin.dynamodbrest.service.DynamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.dynamodb.model.CreateTableResponse;
import software.amazon.awssdk.services.dynamodb.model.TableDescription;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DynamoController {
    private final DynamoService dynamoService;

    @RequestMapping(method = RequestMethod.GET, value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello(){
        return dynamoService.hello();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tables", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> listTables(){
        return dynamoService.listTables();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tables", produces = MediaType.APPLICATION_JSON_VALUE)
    public String listTables(@RequestParam String tableName, @RequestParam String id){
        return dynamoService.createTable(tableName,id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tables", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean listTables(@RequestParam String tableName){
        return dynamoService.deleteTable(tableName);
    }

}
