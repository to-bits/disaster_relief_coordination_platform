# Disaster Relief Coordination Platform (DRCP)

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![React](https://img.shields.io/badge/React-19-blue)
![TypeScript](https://img.shields.io/badge/TypeScript-5.x-3178C6)
![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1)
![License](https://img.shields.io/badge/License-MIT-green)

**A modern full-stack disaster relief coordination platform designed to improve emergency response, resource management, volunteer coordination, and communication during natural and man-made disasters.**

</div>

---

# Table of Contents

- [Overview](#-overview)
- [Project Objective](#-project-objective)
- [Problem Statement](#-problem-statement)
- [Key Features](#-key-features)
- [Target Users](#-target-users)
- [Technology Stack](#-technology-stack)
- [System Architecture](#-system-architecture)
- [Project Structure](#-project-structure)
- [Core Modules](#-core-modules)
- [Database Design](#-database-design)
- [REST API Modules](#-rest-api-modules)
- [Application Workflow](#-application-workflow)
- [Future Enhancements](#-future-enhancements)
- [Installation](#-installation)
- [Development Roadmap](#-development-roadmap)
- [Contributing](#-contributing)
- [License](#-license)

---

# Overview

The **Disaster Relief Coordination Platform (DRCP)** is an enterprise-level web application that connects disaster victims, volunteers, NGOs, donors, and government agencies in one centralized ecosystem.

The platform aims to eliminate communication gaps during emergencies by providing real-time coordination, request management, shelter information, donation tracking, and resource allocation.

Instead of relying on social media posts or phone calls, every request is stored, tracked, prioritized, and assigned digitally.

The platform is designed specifically for disaster-prone countries like **Bangladesh**, but can easily be adapted for any country.

---

# Project Objective

Natural disasters often create chaos because information is scattered across multiple sources.

People don't know:

- Where shelters are available.
- Which roads are blocked.
- Where volunteers are needed.
- Which NGOs are already working.
- Where food is running out.
- Who needs immediate rescue.

Our objective is to create a **single digital platform** that enables:

- Real-time disaster reporting
- Faster emergency response
- Better communication
- Efficient resource management
- Transparent donation tracking
- Volunteer coordination
- Government monitoring

Ultimately, the platform helps save lives by reducing response time.

---

# ❗ Problem Statement

During disasters, common issues include:

- Delayed rescue operations
- Duplicate relief distribution
- Poor communication
- Lack of centralized information
- Missing people
- Untracked donations
- Inefficient volunteer deployment
- Limited transparency

Our platform addresses these problems through automation and centralized coordination.

---

# Key Features

## Authentication

- JWT Authentication
- Role-Based Authorization
- Email Verification
- Password Reset
- OTP Verification

---

## Disaster Management

- Create Disaster
- Update Disaster Status
- Disaster Severity
- Disaster Timeline
- Disaster Location

---

## SOS Requests

Victims can instantly request:

- Rescue
- Food
- Water
- Medicine
- Shelter
- Blood
- Baby Food

Each request contains:

- GPS Location
- Disaster Type
- Priority
- Status
- Images

---

## Volunteer Management

- Nearby Requests
- Accept Mission
- Live Status
- Navigation
- Task History
- Availability Status

---

## NGO Management

NGOs can:

- Manage Relief Camps
- Manage Inventory
- Assign Volunteers
- Monitor Deliveries
- Publish Reports

---

## Shelter Management

Each shelter includes:

- Capacity
- Available Beds
- Water
- Food
- Medicine
- Electricity
- Washrooms
- Contact Person

---

## Donation Management

Support:

- Money
- Food
- Medicine
- Clothes
- Blankets
- Medical Equipment

Track:

- Donation Status
- Distribution
- Inventory Usage

---

## Inventory Management

Track:

- Rice
- Water
- ORS
- Medicine
- Blankets
- Tents
- Baby Food
- Emergency Kits

---

## Live Map

Display:

- Disaster Areas
- Victims
- Volunteers
- NGOs
- Shelters
- Relief Requests
- Distribution Routes

---

## Notifications

Real-time notifications using WebSocket.

Examples:

- Volunteer Assigned
- Donation Received
- Shelter Full
- Request Completed
- New SOS Alert

---

## Chat System

Communication between:

- Victim ↔ Volunteer
- Volunteer ↔ NGO
- NGO ↔ Government
- Admin ↔ Everyone

---

## Missing Person Module

Store:

- Photo
- Last Seen
- Description
- Contact
- Current Status

---

## Analytics Dashboard

Charts:

- Disaster Statistics
- Active Volunteers
- Donation Trends
- Shelter Occupancy
- Relief Requests
- Resource Distribution

---

# Target Users

## Victim

- Register
- Request Help
- Track Requests
- Find Shelter
- Report Missing Person

---

## Volunteer

- View Nearby Requests
- Accept Missions
- Update Status
- Chat
- View Assigned Tasks

---

## NGO

- Create Relief Camps
- Manage Volunteers
- Manage Inventory
- Allocate Resources
- Generate Reports

---

## Government

- National Dashboard
- Disaster Monitoring
- Shelter Monitoring
- Resource Allocation
- Reports

---

##  Administrator

Full System Control

- Users
- Roles
- NGOs
- Volunteers
- Donations
- Reports
- Settings

---

# Technology Stack

## Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- REST API
- WebSocket
- Redis
- Docker

---

## Frontend

- React
- TypeScript
- Vite
- Tailwind CSS
- ShadCN UI
- React Router
- React Query
- Axios
- Leaflet Maps

---

## Database

- MySQL

---

## DevOps

- Docker
- Docker Compose
- GitHub Actions

---

## Cloud (Future)

- AWS
- Firebase
- Cloudinary

---

# System Architecture

```
                 Users
                    │
     ┌──────────────┼──────────────┐
     │              │              │
 Victim        Volunteer        NGO
     │              │              │
     └──────────────┼──────────────┘
                    │
          Spring Boot REST API
                    │
      Authentication (JWT)
                    │
 Business Logic & Services
                    │
          MySQL Database
                    │
     Notifications / WebSocket
                    │
          React Frontend
```

---

# Project Structure

```
disaster-relief-coordination-platform
│
├── backend
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── drcp
│   │   │   │           ├── auth
│   │   │   │           ├── config
│   │   │   │           ├── controller
│   │   │   │           ├── dto
│   │   │   │           ├── entity
│   │   │   │           ├── enums
│   │   │   │           ├── exception
│   │   │   │           ├── mapper
│   │   │   │           ├── repository
│   │   │   │           ├── security
│   │   │   │           ├── service
│   │   │   │           ├── websocket
│   │   │   │           ├── notification
│   │   │   │           ├── disaster
│   │   │   │           ├── shelter
│   │   │   │           ├── inventory
│   │   │   │           ├── donation
│   │   │   │           ├── volunteer
│   │   │   │           ├── victim
│   │   │   │           ├── report
│   │   │   │           └── util
│   │   │   │
│   │   │   └── resources
│   │   │       ├── application.yml
│   │   │       └── db
│   │   │
│   │   └── test
│   │
│   ├── pom.xml
│   └── Dockerfile
│
├── frontend
│   ├── public
│   ├── src
│   │   ├── api
│   │   ├── assets
│   │   ├── components
│   │   ├── context
│   │   ├── hooks
│   │   ├── layouts
│   │   ├── pages
│   │   ├── routes
│   │   ├── services
│   │   ├── store
│   │   ├── styles
│   │   ├── types
│   │   └── utils
│   │
│   ├── package.json
│   └── vite.config.ts
│
├── database
│   ├── schema.sql
│   ├── seed.sql
│   └── migrations
│
├── docs
│   ├── ERD
│   ├── API
│   ├── Architecture
│   ├── Screenshots
│   └── Diagrams
│
├── postman
│
├── docker
│
├── .github
│   └── workflows
│
├── docker-compose.yml
├── README.md
├── LICENSE
└── .gitignore
```

---

# Core Modules

- Authentication
- User Management
- Disaster Management
- SOS Requests
- Volunteer Management
- NGO Management
- Shelter Management
- Inventory Management
- Donation Management
- Delivery Tracking
- Chat
- Notifications
- Reports
- Dashboard
- Missing Persons

---

# 🗄 Database Design

Major Tables

```
Users
Roles
Permissions

Disasters

Victims

Volunteers

NGOs

ReliefRequests

Shelters

Inventory

InventoryItems

Donations

DonationItems

Deliveries

Reports

Notifications

Messages

Locations

AuditLogs

MissingPersons

Attachments
```

---

# REST API Modules

Approximate API Count

| Module | APIs |
|---------|------|
| Authentication | 15 |
| Users | 20 |
| Disaster | 20 |
| Relief Request | 30 |
| Shelter | 20 |
| Inventory | 20 |
| Donation | 25 |
| Volunteer | 20 |
| Reports | 15 |
| Dashboard | 20 |
| Notifications | 10 |
| Chat | 15 |

**Total ≈ 210+ REST APIs**

---

# Application Workflow

```
Disaster Occurs
        │
Victim Sends SOS
        │
Request Stored
        │
Priority Generated
        │
Nearby Volunteers Notified
        │
Volunteer Accepts
        │
NGO Allocates Resources
        │
Relief Delivered
        │
Victim Confirms
        │
Request Completed
```

---

# Future Enhancements

- AI-based Request Prioritization
- Machine Learning Prediction
- Weather API Integration
- SMS Notifications
- Push Notifications
- Mobile App
- QR Code Relief Distribution
- Drone Monitoring
- Satellite Data
- Offline Mode (PWA)
- IoT Sensors
- Blockchain Donation Tracking
- Multi-language Support
- Voice Assistance

---

# Installation

## Clone Repository

```bash
git clone https://github.com/your-username/disaster-relief-coordination-platform.git
```

Backend

```bash
cd backend
mvn spring-boot:run
```

Frontend

```bash
cd frontend
npm install
npm run dev
```

Database

```sql
Create MySQL Database

Import schema.sql

Run application
```

---

# 🗺 Development Roadmap

- [ ] Authentication
- [ ] User Management
- [ ] Disaster Module
- [ ] Relief Request Module
- [ ] Shelter Module
- [ ] Volunteer Module
- [ ] NGO Module
- [ ] Inventory Module
- [ ] Donation Module
- [ ] Dashboard
- [ ] Live Map
- [ ] Chat
- [ ] Notifications
- [ ] Reports
- [ ] Testing
- [ ] Deployment

---

# 🤝 Contributing

Contributions are welcome.

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to your branch
5. Open a Pull Request

---

# 📄 License

This project is licensed under the **MIT License**.

---

# ❤️ Acknowledgements

This project is inspired by the need for better disaster response systems in disaster-prone countries such as Bangladesh.

Our goal is to build technology that saves lives by improving communication, coordination, and transparency during emergencies.

---

<div align="center">



</div>