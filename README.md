### Querying the LogEntries table with conditional expressions

You're creating an application that keeps track of log entries. You're storing each entry in a table called `LogEntries`
and you want to be able to query the table in a few different ways.

The `LogEntries` table looks like this:

* `log_level`: partition key—the log level
* `time_stamp`: sort key—in the format of `yyyymmdd-hh:mm:ss`
* `message`: the log message

Write three methods to narrow the scope of your query so that you can retrieve the items of a certain log level that 
occurred between two times, `getLogsBetweenTimes()`, before a certain time, `getLogsBeforeTime()`, and after a 
certain time, `getLogsAfterTime()`.

The `Log` class is already written and annotated for you. The `LogHelperMethods` class includes helper methods that are
used for testing. 

The unit tests in `LogDAOTest` are set-up with a mock database so that you can test your methods offline. The `main()`
method is set-up in `LogApp` so that you can connect to the real `LogEntries` table and test your methods that way.

When you've finished writing your code and testing, make sure rde workflow `dynamodbquery-prework-narrowing` 
is passing and push.

HINTS:
* [The `withKeyConditionExpression()` method isn't recognizing the key values I'm using, help!](./hints/hint-01.md)
* [I'm getting the error 'no HASH key value present' when I attempt to query the table!](./hints/hint-02.md)
