# Visa Tracking Service

## Project Purpose
The **Visa Tracking Service** is responsible for monitoring the lifecycle of a tourist's visa. It handles visa issuance, extensions, physical location tracking, and travel logs. This service ensures tourists do not overstay and their movements comply with visa regulations.

## Core Components
### Entities (Data Models)
- **`Visa`**: Represents the actual visa granted to the tourist (validity, type).
- **`VisaExtension`**: Records requests and approvals for extending a visa.
- **`VisaHistory`**: Maintains a historical log of visa status changes.
- **`LocationHistory`**: Tracks the physical coordinates/locations of the tourist over time.
- **`TravelLog`**: Logs broader travel itineraries and movements within the country.

### Controllers (API Endpoints)
- **`VisaController`**: Endpoints for issuing and checking visa status.
- **`VisaExtensionController` & `VisaHistoryController`**: Endpoints for managing extensions and historical logs.
- **`LocationHistoryController`**: Endpoints for updating and retrieving GPS/location pings.
- **`TravelLogController`**: Endpoints for managing travel records.

## Security Overview
Secured via **Spring Security** and **JWT**.
- The `SecurityConfig` ensures location data and visa statuses are protected.
- It prevents unauthorized extensions or tampering with travel logs by requiring authenticated tokens.

## Technologies Used
- Java 21
- Spring Boot 4.1.0
- Spring Data JDBC & JPA
- Spring WebMVC
- Spring Security & JWT
- MySQL Connector
- SpringDoc OpenAPI (Swagger UI)

## Getting Started
1. Clone the repository.
2. Update `application.properties` with your MySQL database credentials.
3. Build the project using Maven: `./mvnw clean install`
4. Run the application: `./mvnw spring-boot:run`

## API Documentation
Once the application is running, access the Swagger UI at:
`http://localhost:<port>/swagger-ui.html`
