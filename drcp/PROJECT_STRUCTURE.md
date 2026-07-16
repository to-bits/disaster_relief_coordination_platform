# DRCP (Disaster Relief Coordination Platform)

## Current Fixed Project Structure

```
drcp/
│
├── src/
│   ├── main/
│   │
│   ├── java/
│   │   └── com/
│   │       └── drcp/
│   │
│   │           ├── DrcpApplication.java
│   │           │
│   │           ├── config/
│   │           │   └── DataInitializer.java
│   │           │
│   │           ├── controller/
│   │           │
│   │           │   ├── auth/
│   │           │   │     └── AuthController.java
│   │           │   │
│   │           │   ├── admin/
│   │           │   │     └── AdminDashboardController.java
│   │           │   │
│   │           │   ├── citizen/
│   │           │   │     └── CitizenDashboardController.java
│   │           │   │
│   │           │   ├── volunteer/
│   │           │   │     └── VolunteerDashboardController.java
│   │           │   │
│   │           │   └── donor/
│   │           │         └── DonorDashboardController.java
│   │           │
│   │           ├── dto/
│   │           │
│   │           │   ├── request/
│   │           │   │     ├── LoginRequest.java
│   │           │   │     └── RegisterRequest.java
│   │           │   │
│   │           │   └── response/
│   │           │         └── UserResponse.java
│   │           │
│   │           ├── entity/
│   │           │     ├── User.java
│   │           │     ├── Role.java
│   │           │     └── UserRole.java
│   │           │
│   │           ├── repository/
│   │           │     ├── UserRepository.java
│   │           │     └── RoleRepository.java
│   │           │
│   │           ├── security/
│   │           │
│   │           │     ├── SecurityConfig.java
│   │           │     ├── CustomUserDetails.java
│   │           │     ├── CustomUserDetailsService.java
│   │           │     └── CustomAuthenticationSuccessHandler.java
│   │           │
│   │           ├── service/
│   │           │
│   │           │   ├── interfaces/
│   │           │   │      └── UserService.java
│   │           │   │
│   │           │   └── impl/
│   │           │          └── UserServiceImpl.java
│   │           │
│   │           └── exception/
│   │
│   └── resources/
│       │
│       ├── static/
│       │
│       ├── templates/
│       │
│       │   ├── admin/
│       │   ├── citizen/
│       │   ├── volunteer/
│       │   ├── donor/
│       │   └── index.html
│       │
│       └── application.properties
│
├── docs/
│   ├── PROJECT_STRUCTURE.md
│   └── DEVELOPMENT_REVIEW.md
│
├── pom.xml
│
└── README.md
```

---

# Project Architecture

```
Browser

↓

Controller

↓

DTO

↓

Service

↓

Repository

↓

Database
```

---

# Authentication Flow

```
Login Request

↓

Spring Security

↓

CustomUserDetailsService

↓

Database User

↓

Authentication

↓

Role Based Redirect
```

---

# Current Database

```
users

roles

user_roles
```

---

# Design Principles

- Layered Architecture
- DTO Pattern
- Repository Pattern
- Service Pattern
- Spring Security
- BCrypt Password Encoding
- Role Based Authorization
- MySQL
- JPA/Hibernate
- Thymeleaf MVC

---

# Future Modules

- Relief Management
- Disaster Management
- Donation Management
- Volunteer Management
- Resource Tracking
- GIS Integration
- Emergency Request System
- Notification Service
- Dashboard & Analytics
- Admin Panel