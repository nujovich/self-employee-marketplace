# MarketPlace Spring boot Application

The marketplace allows employers to post jobs, while prospective self-employed can bid for projects. In this system the user can do the following operations:

    - Create a new project(POST /project)
    - Retrieve the project details with the minimum bid sumbitted. In the case that the date to receive new bids is expired, it will return the name and last name of the self-employee. (GET /project/{id})
    - Create a new bid for a specific project (POST /bid)
    - Create an auto bid. The system will return the project who's difference between budget and bid is the less. E.g if the bid is 1200 and there are three projects with budgets of 1300, 1400 and 1200 respectively, it will return the last one according to the min difference (zero in this case) (POST /autobid)

## Tech stack used

    - Java 1.8
    - Maven 3.6.3
    - Spring Boot 2.3.1
    - MongoDB Java Driver 3.12.2
    - JUnit 5 with Mockito
    - Jacoco Reports Lib

## Usage

### Run application locally

Proceed to clone [GIT](https://github.com/nujovich/self-employee-marketplace.git) into a local directory

```bash
git clone https://github.com/nujovich/self-employee-marketplace.git
```

Generate target folder:

```bash
mvn clean package
```

If you want to see the JaCoCo reports:
```bash
mvn install
mvn clean jacoco:prepare-agent install jacoco:report
```
This will generate a site\jacoco folder under target. To see generate reports go to index.html file, right click on it, copy path and paste it in your browser. Coverage %50. I did not increased at a specific top, as the excercise was not requesting it. I only covered specific functionality.


Run the spring boot app using the mvn wrapper command:
```bash
mvnw spring-boot:run
```
Or Run the app using the conventional java way:
```bash
java -jar market-0.0.1-SNAPSHOT.jar
```

### Rest Services Exposed

#### Get project details - Project with no bids submitted and therefore no winner

##### Request GET /project/{id}

```bash
curl -v GET http://localhost:8080/marketplace/v1/project/5f230bdceec559210107f1cc -H 'Content-Type: application/json' 
```

```bash
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 09 Aug 2020 22:49:07 GMT
<
{"id":"5f230bdceec559210107f1cc","description":"Another cool project to work on with very awesome people","budget":1370.0,"endDateForBids":"2020-07-29T00:00:00.000+00:00","minBid":0.0,"name":"N/A","lastName":"N/A"}* Connection #1 to host localhost left intact
```

#### Get project details - Project with min bid and name for the winner

```bash
curl -v GET http://localhost:8080/marketplace/v1/project/5f20ce37c0f8a6440ae395fe -H 'Content-Type: application/json' 
```

```bash
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 09 Aug 2020 22:53:11 GMT
<
{"id":"5f20ce37c0f8a6440ae395fe","description":"This is a project really cool with so many people to work with","budget":2500.0,"endDateForBids":"2020-07-23T03:00:00.000+00:00","minBid":1200.0,"name":"Lucas","lastName":"Johnson"}* Connection #1 to host localhost left intact
```

##### Request POST /project
```bash
curl -v POST http://localhost:8080/marketplace/v1/project -H 'Content-Type: application/json' -d '{
    "description": "Cool microservices project, come and check this out",
    "budget": "1560",
    "endDateForBids": "2020-08-30"
}'
```

```bash
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 09 Aug 2020 22:56:46 GMT
<
{"id":"5f307f2e52e01d7f1044ef05","description":"Cool microservices project, come and check this out","budget":1560.0,"endDateForBids":"2020-08-30T00:00:00.000+00:00","minBid":0.0,"name":"N/A","lastName":"N/A"}* Connection #1 to host localhost left intact
```

```bash
curl -v POST http://localhost:8080/marketplace/v1/project -H 'Content-Type: application/json' -d '{
    "description": "Cool microservices project, come and check this out",
    "budget": "1560",
    "endDateForBids": "2020-08-01"
}'
```

```bash
< HTTP/1.1 400
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 09 Aug 2020 22:58:01 GMT
< Connection: close
<
{"errorMessage":"The date selected is a past date.","errorCode":400}* Closing connection 1
```

#### Request POST /bid
```bash
curl -v POST http://localhost:8080/marketplace/v1/bid -H 'Content-Type: application/json' -d '{
    "projectId": "5f307f2e52e01d7f1044ef05",
    "sellerId": "5f29382d80937d966ec9090f",
    "bid": "1560"
}'
```

```bash
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 09 Aug 2020 23:03:25 GMT
<
{"id":"5f3080bd52e01d7f1044ef06","projectId":"5f307f2e52e01d7f1044ef05","sellerId":"5f29382d80937d966ec9090f","bid":1560.0}* Connection #1 to host localhost left intact
```

```bash
curl -v POST http://localhost:8080/marketplace/v1/bid -H 'Content-Type: application/json' -d '{
    "projectId": "5f307f2e52e01d7f1044ef05",
    "sellerId": "5f29382d80937d966ec9090f",
    "bid": "2000"
}'
```

```bash
< HTTP/1.1 400
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 09 Aug 2020 23:03:39 GMT
< Connection: close
<
{"errorMessage":"Your bid exceeds the project's budget","errorCode":400}* Closing connection 1
```

#### Request POST /autobid

```bash
curl -v POST http://localhost:8080/marketplace/v1/autobid -H 'Content-Type: application/json' -d '{
    "sellerId": "5f29382d80937d966ec9090f",
    "bid": "1560"
}'
```

```bash
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 09 Aug 2020 23:06:28 GMT
<
{"id":"5f30817452e01d7f1044ef07","projectId":"5f293cdf59e3326e306f6dd5","sellerId":"5f29382d80937d966ec9090f","bid":1560.0}* Connection #1 to host localhost left intact
```

```bash
curl -v POST http://localhost:8080/marketplace/v1/autobid -H 'Content-Type: application/json' -d '{
    "sellerId": "5f29382d80937d966ec9090f",
    "bid": "200000"
}'
```

```bash
< HTTP/1.1 400
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 09 Aug 2020 23:15:18 GMT
< Connection: close
<
{"errorMessage":"No matching project found for your bid. Please check budgets and retry submit a new bid","errorCode":400}* Closing connection 1
```

### Feedback

#### Exercise difficulty
Moderate

#### How did I feel abou it ?
8

#### How do you feel about coding an exercise as a step in the interview process?
9

#### What would you change in the exercise and/or process?
I would include host the app in a free tier like AWS or such
