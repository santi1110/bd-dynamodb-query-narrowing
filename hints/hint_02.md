Make sure that you're setting a value for the partition key inside the `withKeyConditionExpression()` expression. In the 
previous query activities you used `withHashKeyValues()` to set the partition key value, but since we're using 
condition expressions, we can't use `withHashKeyValues()` and instead need to set the partition key value
equal to something in our condition expression.
