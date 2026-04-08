# Java CRUD Performance Analysis

This project analyzes performance of CRUD operations using Java (JDBC) and MySQL.

## Features
- Insert, Read, Update, Delete operations
- Performance measurement using System.nanoTime()

## Technologies
- Java
- MySQL
- JDBC
## How to Run

1. Install MySQL and create database `crud_analysis`
2. Create table `students`
3. Update MySQL username and password in code
4. Add MySQL Connector/J jar
5. Run `CRUDPerformance.java`

## Database Setup

```sql
CREATE DATABASE crud_analysis;

USE crud_analysis;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    department VARCHAR(50)
);
```

## Output
- Insert Time: 5399 ms
- Read Time: 50 ms
- Update Time: 2275 ms
- Delete Time: 2729 ms

```
``` markdown
## Conclusion
Insert operations take more time due to disk writes,
while read operations are faster due to caching.
Performance varies based on number of records.
```
