package com.amazon.ata.dynamodbquery.narrowing;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;

public class LogDAO {

    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Log objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public LogDAO(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Uses the query() method to retrieve all the items from the LogEntries table that have a given partition key value
     * and the sort key value is between two given times.
     * @param logLevel the given partition key
     * @param startTime the given start time
     * @param endTime the given end time
     * @return the PaginatedQueryList that is returned from the query
     */
    public List<Log> getLogsBetweenTimes(String logLevel, String startTime, String endTime) {
        // TODO: implement
        HashMap<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":logLevel", new AttributeValue().withS(logLevel));
        expressionValues.put(":startTime", new AttributeValue().withS(startTime));
        expressionValues.put(":endTime", new AttributeValue().withS(endTime));

        DynamoDBQueryExpression<Log> queryExpression = new DynamoDBQueryExpression<Log>()
                .withKeyConditionExpression("log_level = :logLevel AND time_stamp BETWEEN :startTime AND :endTime")
                .withExpressionAttributeValues(expressionValues);

        return mapper.query(Log.class, queryExpression);
    }

    /**
     * Uses the query() method to retrieve all the items from the LogEntries table that have a given partition key value
     * and the sort key value that is before a given time.
     * @param logLevel the given partition key
     * @param endTime the given end time
     * @return the PaginatedQueryList that is returned from the query
     */
    public List<Log> getLogsBeforeTime(String logLevel, String endTime) {
        HashMap<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":logLevel", new AttributeValue().withS(logLevel));
        expressionValues.put(":endTime", new AttributeValue().withS(endTime));
        DynamoDBQueryExpression<Log> queryExpression = new DynamoDBQueryExpression<Log>()
                .withKeyConditionExpression("log_level = :logLevel AND time_stamp <= :endTime")
                .withExpressionAttributeValues(expressionValues);



        //TODO: implement
        return mapper.query(Log.class, queryExpression);
    }

    /**
     * Uses the query() method to retrieve all the items from the LogEntries table that have a given partition key value
     * and the sort key value that is after a given time.
     * @param logLevel the given partition key
     * @param startTime the given start time
     * @return the PaginatedQueryList that is returned from the query
     */
    public List getLogsAfterTime(String logLevel, String startTime) {
        //TODO: implement
        HashMap<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":logLevel", new AttributeValue().withS(logLevel));
        expressionValues.put(":startTime", new AttributeValue().withS(startTime));

        DynamoDBQueryExpression<Log> queryExpression = new DynamoDBQueryExpression<Log>()
                .withKeyConditionExpression("log_level = :logLevel AND time_stamp >= :startTime")
                .withExpressionAttributeValues(expressionValues);

        return mapper.query(Log.class, queryExpression);
    }
}
