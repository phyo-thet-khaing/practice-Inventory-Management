````md
# Cloud Native Inventory Management System
## Step-by-Step Development Plan

> Follow these steps in order. Do not start Frontend, Docker, Kubernetes, or Jenkins before completing the core backend services.

---

# Phase 1: Project Setup

## 1. Create GitHub Repositories

Create repositories:

```text
inventory-management-system
config-repo
```

### Purpose

- inventory-management-system → source code
- config-repo → centralized configuration files

---

## 2. Create Root Project Structure

```text
inventory-management-system/
│
├── backend/
├── frontend/
├── docs/
├── docker/
├── k8s/
├── helm/
└── README.md
```

---

## 3. Create Backend Services

```text
backend/
│
├── config-server/
├── eureka-server/
├── api-gateway/
├── user-service/
├── product-service/
├── supplier-service/
├── location-service/
├── inventory-service/
└── notification-service/
```

---

# Phase 2: Infrastructure Services

Infrastructure must be completed before business services.

---

## 4. Create Config Server

### Add Dependencies

```xml
Spring Cloud Config Server
Spring Boot Actuator
```

### Tasks

- Enable Config Server
- Connect to GitHub config-repo
- Test:

```bash
http://localhost:8888/user-service/default
```

### Goal

Centralized configuration management.

---

## 5. Create Eureka Server

### Add Dependencies

```xml
Spring Cloud Netflix Eureka Server
```

### Tasks

- Enable Eureka Server

### Test

```bash
http://localhost:8761
```

### Goal

Service discovery.

---

## 6. Create API Gateway

### Add Dependencies

```xml
Spring Cloud Gateway
Eureka Client
```

### Tasks

- Register with Eureka
- Configure routes

Example:

```text
/api/users/**
/api/products/**
/api/inventories/**
```

### Goal

Single entry point for frontend.

---

# Phase 3: Authentication & Security

Authentication should be completed before other services.

---

## 7. Create User Service

### Entities

```text
Role
User
```

### Create

```text
Role Entity
User Entity

RoleRepository
UserRepository

RoleService
UserService

RoleController
UserController
```

---

## 8. Implement JWT Authentication

### Create

```text
JwtService
JwtAuthenticationFilter
SecurityConfig
AuthController
AuthService
```

### Features

- Login
- JWT Token
- Password Encryption
- Role Authorization

---

## 9. Create Initial Roles

Insert:

```text
ADMIN
WAREHOUSE_STAFF
SHOWROOM_STAFF
```

---

## 10. Create Setup Admin Endpoint

Example:

```text
POST /api/auth/setup-admin
```

### Purpose

Create first admin user.

---

# Phase 4: Product Management

---

## 11. Create Product Service

### Entities

```text
Category
Product
```

### APIs

```text
Create Category
Update Category
Delete Category
Get Categories

Create Product
Update Product
Delete Product
Get Products
```

---

## 12. Test Product Service

### Verify

```text
CRUD Category
CRUD Product
```

using Swagger and Postman.

---

# Phase 5: Supplier Management

---

## 13. Create Supplier Service

### Entities

```text
Supplier
PurchaseOrder
PurchaseOrderDetail
```

### APIs

```text
Supplier CRUD
Purchase Order CRUD
```

---

## 14. Implement Purchase Order Logic

### Features

```text
Create Purchase Order
Add Items
Approve Order
Complete Order
```

---

# Phase 6: Location Management

---

## 15. Create Location Service

### Entity

```text
Location
```

### Example

```text
Warehouse A
Warehouse B
Showroom A
Showroom B
```

### APIs

```text
CRUD Location
```

---

# Phase 7: Inventory Management

---

## 16. Create Inventory Service

### Entities

```text
Inventory
StockTransaction
```

### APIs

```text
Stock In
Stock Out
Stock Transfer
Inventory Report
```

---

## 17. Implement Stock Logic

### Features

```text
Receive Products
Increase Stock
Decrease Stock
Transfer Stock
```

---

## 18. Implement Low Stock Monitoring

### Example

```text
quantity < minimum_stock
```

Generate alerts.

---

# Phase 8: Notification Service

---

## 19. Create Notification Service

### Features

```text
Low Stock Alert
System Notification
```

---

## 20. Integrate Inventory Service

When stock is low:

```text
Inventory Service
        ↓
Notification Service
```

---

# Phase 9: Frontend

Backend APIs should already be working before starting frontend.

---

## 21. Create React Project

```bash
npm create vite@latest inventory-ui -- --template react-ts
```

Install:

```bash
npm install react-router-dom
npm install axios
npm install zustand
npm install react-hook-form
npm install flowbite flowbite-react
npm install tailwindcss
```

---

## 22. Configure Routing

Create:

```text
Login
Dashboard
Users
Roles
Categories
Products
Suppliers
Locations
Inventory
Reports
```

---

## 23. Create Authentication UI

Pages:

```text
Login
Change Password
```

---

## 24. Create Admin Pages

Pages:

```text
Users
Roles
Categories
Products
Suppliers
Purchase Orders
Locations
Reports
```

---

## 25. Create Warehouse Pages

Pages:

```text
Inventory
Stock In
Stock Out
Transfer
```

---

## 26. Create Showroom Pages

Pages:

```text
Product View
Inventory View
Transfer Request
```

---

# Phase 10: Docker

---

## 27. Dockerize Every Service

Create:

```text
Dockerfile
```

for:

```text
config-server
eureka-server
api-gateway
user-service
product-service
supplier-service
location-service
inventory-service
notification-service
frontend
```

---

## 28. Create Docker Compose

Run:

```text
MySQL
PostgreSQL
Config Server
Eureka
Gateway
All Services
Frontend
```

---

## 29. Test Entire System

Verify:

```text
Frontend
Gateway
Services
Databases
```

all communicate successfully.

---

# Phase 11: CI/CD

---

## 30. Install Jenkins

### Configure

```text
GitHub Webhook
Docker
Maven
Java 21
```

---

## 31. Install SonarQube

### Configure

```text
Code Analysis
Quality Gate
```

---

## 32. Create Jenkins Pipeline

Pipeline:

```text
Git Pull
Build
Test
SonarQube Scan
Docker Build
Docker Push
Deploy
```

---

# Phase 12: Kubernetes

---

## 33. Create Namespace

```bash
kubectl create namespace inventory
```

---

## 34. Create Kubernetes Manifests

For every service:

```text
Deployment
Service
ConfigMap
Secret
```

---

## 35. Deploy to Kubernetes

Order:

```text
Config Server
Eureka Server
Gateway
User Service
Product Service
Supplier Service
Location Service
Inventory Service
Notification Service
Frontend
```

---

# Phase 13: Helm

---

## 36. Create Helm Chart

```text
Chart.yaml
values.yaml
templates/
```

---

## 37. Convert Kubernetes YAML to Helm

Create templates for:

```text
Deployments
Services
Secrets
ConfigMaps
Ingress
```

---

## 38. Install Helm Chart

```bash
helm install inventory .
```

---

# Phase 14: Final Testing

---

## 39. Integration Testing

Verify:

```text
Login
JWT
Gateway Routing
Inventory Flow
Purchase Order Flow
Stock Transfer Flow
Reports
```

---

## 40. Project Completion

Deliverables:

```text
Source Code
Docker Compose
Jenkins Pipeline
SonarQube
Kubernetes YAML
Helm Chart
Documentation
Presentation
```

# Recommended First 10 Tasks

1. Create GitHub repositories
2. Create backend services
3. Create Config Server
4. Create Eureka Server
5. Create API Gateway
6. Create User Service
7. Implement JWT Security
8. Create Roles
9. Create Admin Setup
10. Test Login + JWT

Only after these are working should you move to Product Service.
````
