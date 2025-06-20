# Final Project API

Overview
This project is a RESTful web service for managing users, achievements, and player statistics in a gaming or gamified application.
It is built with Spring Boot 3.4.3, uses Spring Security with JWT for authentication, and provides full interactive API documentation via Swagger/OpenAPI.

# Main features:

User registration, authentication (JWT), and management

Achievement CRUD operations

Player statistics tracking and updates

Exception handling with informative error responses

Logging and performance monitoring using Spring AOP

Comprehensive API documentation with Swagger UI

# Technologies

Java 21

Spring Boot 3.4.3

Spring Data JPA (Hibernate)

Spring Security + JWT

PostgreSQL (or H2/MySQL, configurable)

Lombok

Swagger (springdoc-openapi)

Flyway (for DB migrations)

Maven

# Getting Started
Prerequisites
Java 21

Maven 3.8+

PostgreSQL (or H2/MySQL, set in application.properties)

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
The application will start at http://localhost:8083.

# API Documentation
After starting the application, open:

Swagger UI: http://localhost:8083/swagger-ui.html

OpenAPI JSON: http://localhost:8083/v3/api-docs

You can view and test all endpoints, models, and request/response formats directly from your browser.

# Main Endpoints
Authentication
POST /authenticate
Authenticate with username and password. Returns a JWT token.

Users
GET /user/
Get all users.

GET /user/{username}
Get user by username.

POST /user
Create a new user.

PUT /user/{id}
Update user by ID.

DELETE /user/{id}
Delete user by ID.

Achievements
GET /achievements/
Get all achievements.

POST /achievements
Create a new achievement.

PUT /achievements/{id}
Update achievement by ID.

DELETE /achievements/{id}
Delete achievement by ID.

Player Stats
GET /user/stats/{userId}
Get player stats by user ID.

PUT /user/stats/{userId}
Update player stats by user ID.

POST /user/{id}/assign-admin
Assign ADMIN role to the user by ID.

POST /user/{userId}/achievements/{achievementId}
Assign achievement to the user by ID.


# Exception Handling
The API returns clear error responses for runtime errors and validation issues.

Example error response:

json
{
  "error": "Runtime error",
  "message": "Some error occurred",
  "status": 400
}
For validation errors:

json
{
  "error": "Data validation error",
  "fieldErrors": {
    "username": "Username cannot be empty"
  },
  "status": 400
}

For not found errors:
json
{
"error": "Not Found",
"message": "Player with ID 42 not found",
"path": "/user/42"
}

# Security
JWT Authentication:
Use /authenticate to get a token.
Add header Authorization: Bearer <your_token> to access protected endpoints.

Swagger UI access:
Swagger endpoints are open for exploration and testing.

# Logging & Monitoring
All service-layer method calls and performance are logged using Spring AOP.

Logs include method names, arguments, execution time, and error traces.

Project Structure
controller/ — REST controllers for all entities

model/ — JPA entities and enums

model/dto/ — DTOs for authentication and responses

service/ — Business logic

exception/ — Global exception handling and error DTOs

aop/ — Logging and performance aspects

config/ — Security and OpenAPI configuration

# Contribution
Feel free to fork and contribute!

For questions or issues, open an Issue or Pull Request.
