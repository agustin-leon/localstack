package com.agustin.dynamodb;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

@ConfigurationProperties("dynamodb")
@Data
public class DynamoProperties {
    private URI uri;
}
