# SalonSphere — Stakeholder Service

> A Spring Boot microservice responsible for stakeholder registration, authentication, authorization, profile management, and user administration in the SalonSphere platform.

## 📌 Module 1 — Stakeholder Service

The **Stakeholder Service** is the first core microservice of the SalonSphere application.

## 🚀 Features

### Authentication & Security

* User registration
* User login
* JWT-based authentication
* JWT request filtering
* Role-based authorization
* `@PreAuthorize` method-level security
* Secure password handling using Spring Security

### User Management

* Get user by email
* Get currently authenticated user
* Update user profile
* Change password
* Delete user
* Search users by firstname

### API Features

* RESTful API architecture
* DTO-based request and response handling
* Standardized `ApiResponse<T>` structure
* HTTP status codes using `ResponseEntity`
* Bean Validation using `@Valid`
* Global exception handling
* Pagination
* Sorting
* Search functionality
* Controller-level logging

### Documentation

* Swagger / OpenAPI documentation
* Interactive API testing through Swagger UI

---

## 🛠️ Tech Stack

| Technology         | Usage                          |
| ------------------ | ------------------------------ |
| Java               | Programming Language           |
| Spring Boot        | Application Framework          |
| Spring MVC         | REST API Development           |
| Spring Security    | Authentication & Authorization |
| JWT                | Token-Based Authentication     |
| Spring Data JPA    | Database Access                |
| Hibernate          | ORM                            |
| MySQL              | Database                       |
| Maven              | Build & Dependency Management  |
| Swagger / OpenAPI  | API Documentation              |
| SLF4J              | Application Logging            |
| Jakarta Validation | Request Validation             |

---

## 🏗️ Project Structure

```text
src/main/java/com/project/stakeholders
│
├── controller
│   └── StakeholderController.java
│
├── dto
│   ├── ApiResponse.java
│   ├── ChangePasswordDTO.java
│   ├── LoginDTO.java
│   ├── LoginResponseDTO.java
│   ├── RegisterDTO.java
│   ├── ResponseDTO.java
│   └── UpdateDTO.java
│
├── entity
│   └── Stakeholders.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   └── UserNotFoundException.java
│
├── jwt
│   ├── JwtAuthenticationFilter.java
│   └── JwtUtil.java
│
├── repository
│   └── StakeholderRepository.java
│
├── security
│   └── StakeholderUserDetailsService.java
│
└── service
    ├── StakeholderService.java
    └── StakeholderServiceImpl.java
```

---

## 🔐 Authentication Flow

```text
Client
  │
  │ POST /stakeholders/login
  ▼
Stakeholder Controller
  │
  ▼
Stakeholder Service
  │
  ▼
Spring Security Authentication
  │
  ▼
JWT Token Generated
  │
  ▼
Client
```

For protected APIs:

```text
Client
  │
  │ Authorization: Bearer <JWT>
  ▼
JwtAuthenticationFilter
  │
  ├── Extract JWT
  │
  ├── Validate JWT
  │
  ├── Extract User Email
  │
  ├── Load UserDetails
  │
  └── Set Authentication
          │
          ▼
   Spring Security
          │
          ▼
      Controller
```

---

## 🌐 API Endpoints

### Authentication

| Method | Endpoint                 | Description                        |
| ------ | ------------------------ | ---------------------------------- |
| POST   | `/stakeholders/register` | Register a new user                |
| POST   | `/stakeholders/login`    | Authenticate user and generate JWT |

### User Management

| Method | Endpoint                        | Description       |
| ------ | ------------------------------- | ----------------- |
| GET    | `/stakeholders/email/{email}`   | Get user by email |
| PUT    | `/stakeholders/update/{email}`  | Update user       |
| DELETE | `/stakeholders/{email}`         | Delete user       |
| PUT    | `/stakeholders/change-password` | Change password   |

### Current User

| Method | Endpoint           | Description                         |
| ------ | ------------------ | ----------------------------------- |
| GET    | `/stakeholders/me` | Get authenticated user's profile    |
| PUT    | `/stakeholders/me` | Update authenticated user's profile |

### User Search & Pagination

| Method | Endpoint               | Description                           |
| ------ | ---------------------- | ------------------------------------- |
| GET    | `/stakeholders/all`    | Get users with pagination and sorting |
| GET    | `/stakeholders/search` | Search users by firstname             |

---

## 📄 Standard API Response

Successful responses use a common `ApiResponse<T>` structure.

### Success

```json
{
  "success": true,
  "message": "User retrieved successfully.",
  "data": {
    "id": 1,
    "firstname": "Kunal",
    "lastname": "Patil",
    "email": "kunal@example.com"
  }
}
```

### Error

```json
{
  "success": false,
  "message": "User not found.",
  "data": null
}
```

This keeps the API response format consistent for frontend and service consumers.

---

## ⚠️ Exception Handling

The service uses a centralized `GlobalExceptionHandler`.

Currently handled:

| Exception                         |                 HTTP Status |
| --------------------------------- | --------------------------: |
| `UserNotFoundException`           |             `404 NOT FOUND` |
| `MethodArgumentNotValidException` |           `400 BAD REQUEST` |
| Generic `Exception`               | `500 INTERNAL SERVER ERROR` |

Validation errors are returned in a structured format:

```json
{
  "success": false,
  "message": "Validation Failed",
  "data": {
    "email": "Invalid email format",
    "password": "Password must be at least 8 characters"
  }
}
```

---

## 🔒 Authorization

Administrative operations are protected using Spring Security method-level authorization.

Example:

```java
@PreAuthorize("hasRole('ADMIN')")
```

Therefore, only users with the `ADMIN` role can access administrative operations such as deleting users.

---

## 📑 Pagination & Sorting

The `/all` endpoint supports pagination and sorting.

Example:

```http
GET /stakeholders/all?page=0&size=5&sortBy=firstname
```

Default values:

```text
page   = 0
size   = 5
sortBy = firstname
```

---

## 🔎 Search

Users can be searched by firstname.

Example:

```http
GET /stakeholders/search?firstname=Kunal
```

The search uses case-insensitive partial matching.

Example:

```text
Kunal
kunal
KUNAL
```

can all match the appropriate records.

---

## 📚 Swagger / OpenAPI

Swagger UI is available during local development at:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger provides an interactive interface for:

* Viewing available APIs
* Viewing request parameters
* Sending API requests
* Testing authentication
* Inspecting responses
* Testing HTTP status codes

---

## ⚙️ Configuration

The application currently uses MySQL.

Example configuration:

```properties
spring.application.name=SalonSphere

spring.datasource.url=jdbc:mysql://localhost:3306/stakeholders_database
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> For deployment, database credentials should be supplied through environment variables rather than committed directly to the repository.

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone <YOUR_REPOSITORY_URL>
```

### 2. Open the project

Open the project in:

* IntelliJ IDEA
* Eclipse
* VS Code
* Spring Tool Suite

### 3. Configure MySQL

Create the database:

```sql
CREATE DATABASE stakeholders_database;
```

Update the database credentials in:

```text
application.properties
```

### 4. Build the project

mvn clean install


### 5. Run the application

mvn spring-boot:run


Or run the main Spring Boot application class from your IDE.

### 6. Open Swagger

http://localhost:8080/swagger-ui/index.html

---

## 🧪 Testing

The APIs can be tested using:

* Swagger UI
* Postman
* IntelliJ HTTP Client

For protected endpoints, first authenticate using:

POST /stakeholders/login


Then use the returned JWT token:

Authorization: Bearer <JWT_TOKEN>

---

## 📈 Module Status

### Module 1 — Stakeholder Service

**Status: ✅ Completed**

Implemented:

* [x] User registration
* [x] User login
* [x] JWT authentication
* [x] Role-based authorization
* [x] User profile management
* [x] Password change
* [x] User deletion
* [x] Current user API
* [x] Pagination
* [x] Sorting
* [x] Search
* [x] DTOs
* [x] Validation
* [x] Global exception handling
* [x] Standardized API responses
* [x] Controller logging
* [x] Swagger/OpenAPI

### Upcoming Modules

Module 1 → Stakeholder Service       ✅
Module 2 → Salon Service             🔜
Module 3 → Staff Service
Module 4 → Booking Service
Module 5 → Payment Service
Module 6 → Review Service
Module 7 → Notification Service
Module 8 → API Gateway
Module 9 → Service Discovery
Module 10 → Distributed Tracing
Module 11 → Docker & Deployment

---

## 🔮 Future Improvements

Planned improvements include:

* Refresh token authentication
* Advanced security exception handling
* Unit and integration testing
* Service-to-service communication
* API Gateway
* Eureka Service Discovery
* Redis caching
* Circuit Breaker
* Distributed tracing
* Docker containerization
* CI/CD
* Cloud deployment

---

Technologies:
Java | Spring Boot | Spring Security | JWT
REST APIs | JPA | Hibernate | MySQL
React | Angular | Microservices

---

## 📄 License

This project is developed for learning, portfolio development, and demonstrating practical Spring Boot and microservices development.
