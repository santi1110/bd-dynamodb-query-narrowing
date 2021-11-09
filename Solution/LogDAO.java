package com.amazon.ata.dynamodbquery.prework.narrowing;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":logLevel", new AttributeValue().withS(logLevel));
        valueMap.put(":startTime", new AttributeValue().withS(startTime));
        valueMap.put(":endTime", new AttributeValue().withS(endTime));

        DynamoDBQueryExpression<Log> queryExpression = new DynamoDBQueryExpression<Log>()
            .withKeyConditionExpression("log_level = :logLevel and time_stamp between :startTime and :endTime")
            .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<Log> logList = mapper.query(Log.class, queryExpression);
        return logList;
    }

    /**
     * Uses the query() method to retrieve all the items from the LogEntries table that have a given partition key value
     * and the sort key value that is before a given time.
     * @param logLevel the given partition key
     * @param endTime the given end time
     * @return the PaginatedQueryList that is returned from the query
     */
    public List<Log> getLogsBeforeTime(String logLevel, String endTime) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":logLevel", new AttributeValue().withS(logLevel));
        valueMap.put(":endTime", new AttributeValue().withS(endTime));
        DynamoDBQueryExpression<Log> queryExpression = new DynamoDBQueryExpression<Log>()
            .withKeyConditionExpression("log_level = :logLevel and time_stamp < :endTime")
            .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<Log> logList = mapper.query(Log.class, queryExpression);
        return logList;
    }

    /**
     * Uses the query() method to retrieve all the items from the LogEntries table that have a given partition key value
     * and the sort key value that is after a given time.
     * @param logLevel the given partition key
     * @param startTime the given start time
     * @return the PaginatedQueryList that is returned from the query
     */
    public List getLogsAfterTime(String logLevel, String startTime) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":logLevel", new AttributeValue().withS(logLevel));
        valueMap.put(":startTime", new AttributeValue().withS(startTime));
        DynamoDBQueryExpression<Log> queryExpression = new DynamoDBQueryExpression<Log>()
            .withKeyConditionExpression("log_level = :logLevel and time_stamp > :startTime")
            .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<Log> logList = mapper.query(Log.class, queryExpression);
        return logList;
    }
}
