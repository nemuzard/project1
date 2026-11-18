# Student Management System

## Description

A REST API for creating, reading, updating, and deleting student records.

- Java: 21
- Spring Boot: 4.1.2
- Database: PostgreSQL 17
- Build tool: Maven

## Quick Start

1. Start PostgreSQL. On macOS with Homebrew:

   ```bash
   brew install postgresql@17  
   brew services start postgresql@17
   ```

2. Create the database once in DBeaver or a PostgreSQL SQL console:

   ```sql
   CREATE DATABASE sms;
   ```

3. Update `src/main/resources/application.properties` to match your database:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/sms
   spring.datasource.username=your_database_user
   spring.datasource.password=your_database_password
   spring.jpa.hibernate.ddl-auto=update
   ```

   For the current local setup, the username is `ziweigao` and the password is empty because localhost connections use PostgreSQL trust authentication. Use the credentials required by your own database setup.

   Hibernate creates or updates the `students` table at startup. The `sms` database must already exist.

4. Start the application:

   ```bash
   mvn spring-boot:run
   ```

5. Check the API from another terminal:

   ```bash
   curl -i http://localhost:8080/api/students
   ```


## Supported Operations

1. Create a student

   ```http
   POST http://localhost:8080/api/students
   Content-Type: application/json
   ```
   ```json
   {
      "firstName": "firstname",
      "lastName": "lastname",
      "email": "email@xx.com"
   }
   ```

2. Look up a student by ID

   ```http
   GET http://localhost:8080/api/students/{id}
   ```

3. Get all students

   ```http
   GET http://localhost:8080/api/students
   ```

4. Update a student

   ```http
   PUT http://localhost:8080/api/students/{id}
   Content-Type: application/json
   ```
   ```json
   {
      "firstName": "updated firstname",
      "lastName": "updated lastname",
      "email": "updated.email@xx.com"
   }
   ```

5. Delete a student

   ```http
   DELETE http://localhost:8080/api/students/{id}
   ```
-------
Remember close db connection
``
brew services stop yourDatabase
``


## Project Details

Project Dependencies

1. Spring Web MVC (`spring-boot-starter-webmvc`)
2. Spring Data JPA (`spring-boot-starter-data-jpa`) — includes Hibernate and connection pooling
3. PostgreSQL driver
4. Lombok 
   - helps generating `@Getter` and `@Setter`
   - creates constructor - `@NoArgsConstructor` and `@AllArgsConstructor`

Useful Spring annotations

```java
@Entity // tells spring this is an entity class
@Table(name="xx") // specifies the table name
@GeneratedValue(strategy = GenerationType.XXX)
@Column(name = "xx")  // can customize the column name
```
### Project Structure

1. entity
   - maps Java fields to database table columns
2. repository 
   - contains interfaces for database operations on entities
3. dto -- transfer data between client and server 
4. mapper - dto → entity, entity → dto
5. service - business logic 
   - create service layer 
     - Service Interface and Implementation 
   - CRUD operations based on Repository
   - return entity → dto if need pass to controller
   
6. controller
   - based on object service 
   - REST controller
   - return `ResponseEntity<T> object` to the client
7. exception 
   - defines custom exceptions
   - `ResourceNotFoundException` extends `RuntimeException` and maps to HTTP `404`

