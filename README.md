# 🚀 Customer Management System (Backend)

A Spring Boot-based backend application for managing customers, family relationships, and bulk operations using Excel uploads.

---

## 🛠️ Tech Stack

- **Java Version**: 1.8
- **Spring Boot Version**: 2.7.18
- **Build Tool**: Maven
- **Database**: MariaDB 
- **ORM**: Spring Data JPA (Hibernate)
- **File Processing**: Apache POI (Excel)

---

## 📦 Features

### 👤 Customer Management
- Create customer
- Get customer by ID
- Get all customers
- Update customer

### 👨‍👩‍👧 Family Management
- Add family member
- Get family members of a customer

### 📂 Bulk Operations (Excel)
- Bulk customer creation via Excel upload
- Bulk customer update via Excel
- Handles large datasets (up to 1,000,000 records)
- Batch processing for better performance

### 🌍 Master Data
- Cities and Countries stored in master tables
- Referenced using `cityId` and `countryId`
- No manual user input required

---

## 🗄️ Database Tables

- `customer`
- `mobile`
- `address`
- `city`
- `country`
- `family`

---

## 🔗 API Endpoints

### 📌 Base URL

- http://localhost:8080

---

### 👤 Customer APIs

Base Path: `/api/v1/customers`

| Method | Endpoint | Description |
|--------|---------|------------|
| POST | `/` | Create customer |
| GET | `/{id}` | Get customer by ID |
| GET | `/` | Get all customers |
| PUT | `/{id}` | Update customer |

---

### 👨‍👩‍👧 Family APIs

Base Path: `/api/v1/family`

| Method | Endpoint | Description |
|--------|---------|------------|
| POST | `/` | Add family member |
| GET | `/{customerId}` | Get family members |

---

### 📂 Bulk APIs

Base Path: `/api/bulk`

| Method | Endpoint | Description |
|--------|---------|------------|
| POST | `/upload` | Bulk create customers |
| PUT | `/bulk-update` | Bulk update customers |

---

## 📊 Excel Format (Bulk Upload)

| name | dob | nic | mobile | line1 | line2 | cityId | countryId |
|------|-----|-----|--------|-------|-------|--------|-----------|

---

## ⚡ Performance Optimizations

- Batch processing (1000 records per batch)
- Hibernate batch insert enabled
- Memory-efficient Excel reading
- Async processing to prevent timeout

---

## 🔒 CORS Configuration

```java
@CrossOrigin(origins = "http://localhost:5173")

```

## ▶️ Run the Project 

### Clone repository
git clone https://github.com/dinelkalakshan40/customer-management-system-backend.git

### Navigate to project
cd customer-management-system

### Run application
mvn spring-boot:run
