# localstack - dynamodb

Steps:

1. Run Localstack

    SERVICES=dynamodb localstack start


2. Review port in localhost where mock dynamodb is running

    http://localhost:4566


## endpoints

1. GET: /hello - healthcheck

   http://localhost:8080/hello


2. GET: /tables - list tables

   http://localhost:8080/tables


3. POST: /tables - create a table with a "name" and "id"

   http://localhost:8080/tables?tableName=users&id=id


4. DELETE: /tables - delete a table with "name"

   http://localhost:8080/tables?tableName=users




###Useful AWS CLI Commands:

1. **Create table in dynamodb**
   
   aws --endpoint-url=http://localhost:4566 dynamodb create-table --table-name test_table  --attribute-definitions AttributeName=first,AttributeType=S AttributeName=second,AttributeType=N --key-schema AttributeName=first,KeyType=HASH AttributeName=second,KeyType=RANGE --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5


2. **List tables**

   aws --endpoint-url=http://localhost:4566 dynamodb list-tables


3. **Describe table**

   aws --endpoint-url=http://localhost:4566 dynamodb describe-table --table-name test_table


4. **Put item**

   aws --endpoint-url=http://localhost:4566 dynamodb put-item --table-name test_table  --item '{"first":{"S":"Jack"},"second":{"N":"42"}}'


5. **Scan table**

   aws --endpoint-url=http://localhost:4566 dynamodb scan --table-name test_table


6. **Get item**

   aws --endpoint-url=http://localhost:4566 dynamodb get-item --table-name test_table  --key '{"first":{"S":"Manish"},"second":{"N":"40"}}'


7. **Delete table**

   aws --endpoint-url=http://localhost:4566 dynamodb delete-table --table-name test_table


   


