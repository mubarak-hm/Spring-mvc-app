package com.example.awstodoapplication.DynamoDB;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.model.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {
    @Value("${aws.access-key}")
    private String amazonAWSAccessKey;


    @Value("${aws.secret-key}")
    private String amazonAWSSecretKey;


    @Bean
    public AmazonDynamoDB dynamoDBClientBuilder() {

        return AmazonDynamoDBClientBuilder.standard().withRegion("eu-central-1").withCredentials(new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey)
        )).build();

    }
    @Bean
    public  DynamoDBMapper  getDynamoDbMapper(){
        return  new DynamoDBMapper(dynamoDBClientBuilder());
    }

}
