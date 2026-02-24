# yet another spring boot authentication API

A simple Spring Boot project providing **user authentication, registration, and retrieval of current user details**.

## Tech Stack

* **Java 21**
* **Spring Boot 3.5.11**
* **JJWT 0.12.6**
* **Lombok**
* **Jakarta Bean Validation**
* **H2 Database**

## Features

Based on implemented controllers:

* **User Registration** – create new accounts (`/api/auth/register`)
* **User Login** – authenticate and receive JWT token (`/api/auth/login`)
* **Get Current User Details** – retrieve information about the logged-in user (`/api/users/me`)

## Notes

* Uses **JWT** for stateless authentication.
* Passwords should be securely hashed before storage (e.g., BCrypt).
* `@AuthenticationPrincipal` is used to obtain the currently authenticated user's username.
* Easily extendable for roles, permissions, and additional endpoints.
* Generic `ApiResponse<T>` wrapper standardizes API responses.
