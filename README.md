# Agenda API

A RESTful API for managing personal appointments and categories, built with Spring Boot.

This project was created to practice back-end development concepts, applying clean architecture principles, business rules, validation, testing, and database persistence.

---

## Features

- Create appointments
- List all appointments
- Retrieve an appointment by ID
- Create categories
- List all categories
- Associate appointments with categories
- Input validation
- Centralized exception handling
- Unit testing

---

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Docker
- Maven
- Jakarta Validation
- JUnit 5
- Mockito

---

## Project Structure

```
src
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
├── service
└── config
```

---

## Business Rules

- The appointment end date must be after the start date.
- Every appointment must belong to a valid category.
- Required fields are validated before persistence.
- Validation and business errors return standardized responses.

---

## Getting Started

### Prerequisites

- Java 21
- Docker
- Maven

### Clone the repository

```bash
git clone https://github.com/rafaelreissant/Agenda.git
cd Agenda
```

### Start PostgreSQL

```bash
docker compose up -d
```

### Run the application

```bash
mvn spring-boot:run
```

---

## API Endpoints

### Appointments

| Method | Endpoint |
|---------|----------|
| POST | `/appointments` |
| GET | `/appointments` |
| GET | `/appointments/{id}` |
| PUT | `/appointments/{appointmentId}/category/{categoryId}` |

### Categories

| Method | Endpoint |
|---------|----------|
| POST | `/categories` |
| GET | `/categories` |

---

## Running Tests

Execute all tests with:

```bash
mvn test
```

---

## Future Improvements

- JWT Authentication
- Pagination
- Update and delete endpoints
- Filtering by date
- Swagger / OpenAPI documentation
- Flyway database migrations
- CI/CD with GitHub Actions

---

## Author

**Rafael Santiago**

GitHub: https://github.com/rafaelreissant
