package com.example.awstodoapplication.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.example.awstodoapplication.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public TodoRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public List<Todo> findAll() {
        return dynamoDBMapper.scan(Todo.class, new DynamoDBScanExpression());
    }

    public Todo findById(String id) {
        return dynamoDBMapper.load(Todo.class, id);
    }

    public void save(Todo todo) {
        dynamoDBMapper.save(todo);
    }

    public void delete(Todo todo) {
        dynamoDBMapper.delete(todo);
    }

}
