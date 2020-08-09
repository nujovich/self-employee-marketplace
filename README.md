# MarketPlace Spring boot Application

The marketplace allows employers to post jobs, while prospective self-employed can bid for projects. In this system the user can do the following operations.
    - Create a new project
    - Retrieve the project details with the minimum bid sumbitted. In the case that the date to receive new bids is expired, it will return the name and last name of the self-employee.
    - Create a new bid for a specific project
    - Create an auto bid. The system will return the project who's difference between budget and bid is the less. E.g if the bid is 1200 and there are three projects with budgets of 1300, 1400 and 1200 respectively, it will return the last one according to the min difference (zero in this case)

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
java -jar mutantproject-0.0.1-SNAPSHOT.jar
```