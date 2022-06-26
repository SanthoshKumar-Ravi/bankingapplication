# Banking Application
This Project has one microservice.

### Accountmanagement:
This service containers below list of API's.
 
| API     | Httpmethod     | request  | response  |
| :---:          |   :---:          | :---:       | :---:|
| /create        |  POST        |   {"accountType": "string","age": "string","gender": "string",***"governmentIssuedUniqueId": "string","name": "string"***"monthlyIncome": "string"} | {"accountNo": 0,"accountType": "string","age": "string","balance": 0,"gender": "string","governmentIssuedUniqueId": "string","monthlyIncome": "string""name": "string"}
| /fetchDetails/{accountNumber}       | GET         | /fetchDetails/1   |{"accountNo": 0,"accountType": "string","age": "string","balance": 0,"gender": "string","governmentIssuedUniqueId": "string","monthlyIncome": "string""name": "string","transactionSchemaObjectList": [{"amount": 0,"remarks": "string","transactionDate": "string","type": "string"}]}
| /deposit         | POST         | ***{"accountNo": 16,"amountToBeDeposited":"1"}***  | {"accountNo": 16,"balance": 2.00}
| /transfer         | POST         | ***{"fromAccount":"1","toAccount":"2","notes":"transfer","transferAmount":"1"}***  | {"accountNo": 16,"balance": 2.00}


## Prerequiste tools to run the project:
* Programming language -  Java 8
* Build Tool -  gradle
* database - mysql

## Deployment Steps without Docker:
### Clone the repository

``` git clone https://github.com/SanthoshKumar-Ravi/bankingapplication.git ```

### Go the folder

``` simplebanking ```

### Set Your MySQL username & password in application.yml

[application.yml](https://github.com/SanthoshKumar-Ravi/bankingapplication/blob/master/simplebanking/src/main/resources/application.yml)

### build the application
``` gradlew build ```

### run the application
``` java -jar accountmanagement-0.0.1.jar ```


## Deployment Steps on Docker:
### Download application
``` git clone https://github.com/hendisantika/online-banking.git ```

### Go the folder
``` simplebanking ```

### Start MySQL Docker Container
```
gradlew build
docker-compose build
docker-compose up
```
Access the application by clicking the URL "http://localhost:8086!"

### Commands to create user in mysql
```
CREATE USER 'accountmanagement'@'localhost' IDENTIFIED BY 'password';
GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT, REFERENCES, RELOAD on *.* TO 'accountmanagement'@'localhost' WITH GRANT OPTION;
```

### Commands to create user in mysql
We have added version column for each entity to control the concurrent transactions.

#### Please note:
1) Swagger URL - http://localhost:8086/swagger-ui.html#/ <br />
2) I have added the postman collection with this repository to test the application from postman bankingapplication.postman_collection [bankingapplication.postman_collection](https://github.com/SanthoshKumar-Ravi/bankingapplication/blob/master/bankingapplication.postman_collection.json)
