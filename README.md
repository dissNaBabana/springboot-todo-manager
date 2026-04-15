# ToDo List - Spring Boot Application

![Java](https://img.shields.io/badge/Java-25-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?logo=thymeleaf&logoColor=white)

A full-featured **ToDo List** web application built with Spring Boot. Users can register,
securely authenticate, and efficiently manage their personal tasks with role-based access control.

## ✨ Key Features

- **User Authentication & Authorization**
    - Registration and login with Spring Security
    - Password hashing 
    - Role-based access: `USER`, `ADMIN`, `SUPER_ADMIN`

- **Task Management**
    - Full CRUD operations (Create, Read, Update, Delete)
    - Mark tasks as completed / pending
    - Task filtering: All, Completed, Not Completed
    - Simple statistics (count of incomplete tasks)

- **Admin Panel**
    - `ADMIN` — can delete users
    - `SUPER_ADMIN` — can delete users and promote them to Admin

- **Modern Architecture**: MVC (Model-View-Controller) with clean separation of concerns

## 🛠 Technologies

- **Java** 25
- **Spring Boot** 4.0.5
- **Spring Security** 
- **Spring Data JPA** & Hibernate
- **PostgreSQL**
- **Thymeleaf** (server-side rendering)
- **Maven**
- **Docker** & **Docker Compose**

## 🚀 How to Run the Project
### With Docker (Recommended)

```bash
# 1. Clone the repository
git clone https://github.com/dissNaBabana/springboot-todo-manager.git
cd springboot-todo-manager

# 2. Start the application and database
docker compose up --build

# 3. Stop the application and containers
docker compose down