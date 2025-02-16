# Attrition Analyzer

## Overview
Attrition Analyzer is a microservices-based application that helps HR professionals analyze employee attrition trends and take proactive measures to retain employees. The system allows HR users to visualize attrition trends, mark employees at risk of leaving, and send notifications to relevant stakeholders.

## Tech Stack
### Frontend:
- Angular
- Angular Material
- Chart.js (for data visualization)

### Backend:
- Spring Boot
- Microservices Architecture
- MySQL (for structured data)
- MongoDB (for storing notifications)
- Kafka (for inter-service communication)
- Feign Client (for service-to-service calls)
- JWT Authentication

## Microservices Overview
### 1. **User Details Service**
   - Manages HR user registration and details.
   - MySQL as the database.

### 2. **Auth Service**
   - Handles authentication using JWT.
   - Retrieves HR credentials from the User Details Service via Feign Client.

### 3. **Employee Service**
   - Fetches employee data from an external source hosted in a Docker container.
   - Uses RestTemplate for data retrieval.

### 4. **Notification Service**
   - Stores HR notifications related to employee attrition in MongoDB.
   - Uses Kafka for event-driven communication with the Employee Service.

### 5. **API Gateway**
   - Manages routing and security for microservices.
   - Implements JWT validation for secured endpoints.
   - **JWT filter is not added, and CORS issue is not fixed. Due to CORS issues, the frontend cannot interact with services via the API Gateway, so the client directly interacts with individual services for a working condition.**

## Features
- **Employee Attrition Visualization**: Displays employee attrition trends using Chart.js.
- **Mark Employees at Risk**: Allows HR to flag employees who may leave.
- **Send Notifications**: HR can send notifications to relevant stakeholders.
- **Secure Authentication**: JWT-based authentication and role-based access control.
- **Microservices Communication**: Uses Kafka and Feign Client for inter-service communication.
- **CORS Handling**: Configured for secure cross-origin requests (not yet fixed).

## Setup & Installation
### Prerequisites:
- Java 17+
- Node.js & Angular CLI
- MySQL & MongoDB
- Docker (for employee data source)

### Steps to Run:
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/attrition-analyzer.git
   cd attrition-analyzer
   ```
2. Set up MySQL and MongoDB databases.
3. Configure database connections in `application.properties` for each microservice.
4. Start Kafka broker for event-driven communication.
   
   **Kafka Commands (Windows):**
   ```sh
   C:\kafka_2.13-3.9.0\bin\windows>
   zookeeper-server-start.bat ..\..\config\zookeeper.properties
   kafka-server-start.bat ..\..\config\server.properties
   ```

5. Build and run the backend microservices:
   ```sh
   mvn clean install
   java -jar target/{microservice-name}.jar
   ```
6. Start the frontend Angular application:
   ```sh
   cd frontend
   npm install
   ng serve
   ```
7. Access the application at `http://localhost:4200`.

## API Endpoints
| Service | Endpoint | Method | Description |
|---------|---------|--------|-------------|
| Auth Service | `/auth/login` | POST | Authenticates users and returns JWT |
| User Details Service | `/users/register` | POST | Registers a new HR user |
| Employee Service | `/employees` | GET | Retrieves all employee data |
| Notification Service | `/notifications` | POST | Saves a notification in MongoDB |

## Future Enhancements
- Implement machine learning to predict employee attrition.
- Add role-based access control for different user levels.
- Integrate an email/SMS notification system.
- Fix CORS issues to allow frontend to interact with API Gateway.
- Implement JWT filtering for better security.

## Contributing
Feel free to fork this repository and submit pull requests to improve the project.

## License
This project is licensed under the MIT License.

---

🚀 **Attrition Analyzer** helps HR teams make data-driven decisions to reduce employee attrition! Let’s build a better workplace together. 😊