# Spring Boot Microservices Template

A production-ready microservices architecture template built with Spring Boot 3.5.7, Spring Cloud 2025.0.0, and Docker. This template demonstrates best practices for building scalable, resilient microservices with service discovery, API gateway, centralized configuration, and database integration.

⭐ **If you find this project helpful, please consider giving it a star!** ⭐

## 🏗️ Architecture Overview

This template implements a complete microservices ecosystem with the following components:

```sh
┌─────────────┐
│   Client    │
└──────┬──────┘
       │
       ▼
┌─────────────────────────────────────┐
│      API Gateway (Port 8080)        │
│   - Request Routing                 │
│   - Load Balancing                  │
└──────┬──────────────────────────────┘
       │
       ▼
┌─────────────────────────────────────┐
│  Eureka Server (Port 8761)          │
│   - Service Discovery               │
│   - Service Registry                │
└──────┬──────────────────────────────┘
       │
       ├──────────────────┬─────────────────┐
       ▼                  ▼                 ▼
┌─────────────┐   ┌──────────────┐  ┌─────────────┐
│Config Server│   │ User Service │  │   Future    │
│ (Port 8888) │   │ (Port 8081)  │  │  Services   │
└─────────────┘   └──────┬───────┘  └─────────────┘
                         │
                         ▼
                  ┌──────────────┐
                  │  PostgreSQL  │
                  │ (Port 2500)  │
                  └──────────────┘
```

## 📦 Components

### 1. **Eureka Server** (Service Discovery)

- **Port:** 8761
- **Purpose:** Service registry for dynamic service discovery
- **Dashboard:** <http://localhost:8761>

### 2. **API Gateway**

- **Port:** 8080
- **Purpose:** Single entry point for all client requests
- **Features:**
  - Routing to microservices
  - Load balancing
  - Eureka integration

### 3. **Config Server**

- **Port:** 8888
- **Purpose:** Centralized configuration management
- **Features:** Externalized configuration for all services

### 4. **User Service** (Example Microservice)

- **Port:** 8081
- **Purpose:** Demonstrates a complete microservice with database integration
- **Features:**
  - RESTful API endpoints
  - PostgreSQL database integration
  - JPA/Hibernate ORM
  - OpenFeign client for inter-service communication
  - Resilience4j for circuit breaker pattern
  - Spring Boot Actuator for monitoring

### 5. **PostgreSQL Database**

- **Port:** 2500 (host) → 5432 (container)
- **Database:** userdb
- **Credentials:** postgres/postgres

## 🚀 Quick Start

### Prerequisites

- **Java 21** or higher
- **Maven 3.9+**
- **Docker** and **Docker Compose**
- **Git**

### Running the Application

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd SPRINGBOOT_MICROSERVICE
   ```

2. **Start all services with Docker Compose**

   ```bash
   docker-compose up --build
   ```

   This command will:
   - Build all Docker images
   - Start PostgreSQL database
   - Start Eureka Server
   - Start Config Server
   - Start API Gateway
   - Start User Service

3. **Wait for services to start** (approximately 2-3 minutes)
   - Monitor logs: `docker-compose logs -f`
   - Check Eureka Dashboard: <http://localhost:8761>

4. **Verify services are registered**
   - Open <http://localhost:8761>
   - You should see `USER-SERVICE`, `API-GATEWAY`, and `CONFIG-SERVER` registered

### Testing the Application

**Access User Service through API Gateway:**

```bash
curl http://localhost:8080/users/1
```

**Direct access to User Service:**

```bash
curl http://localhost:8081/users/1
```

**Check service health:**

```bash
curl http://localhost:8081/actuator/health
```

## 🛠️ Technology Stack

### Core Framework

- **Spring Boot:** 3.5.7
- **Spring Cloud:** 2025.0.0
- **Java:** 21

### Spring Cloud Components

- **Netflix Eureka:** Service discovery
- **Spring Cloud Gateway:** API gateway
- **Spring Cloud Config:** Centralized configuration
- **OpenFeign:** Declarative REST client
- **Resilience4j:** Circuit breaker, retry, rate limiter

### Database & Persistence

- **PostgreSQL:** 15
- **Spring Data JPA:** Data access layer
- **Hibernate:** ORM framework

### Additional Libraries

- **Lombok:** Reduce boilerplate code
- **Spring Boot Actuator:** Production monitoring
- **Spring Boot DevTools:** Development productivity

### Containerization

- **Docker:** Containerization
- **Docker Compose:** Multi-container orchestration

## 📁 Project Structure

```sh
SPRINGBOOT_MICROSERVICE/
├── eureka/                    # Service Discovery Server
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── gateway/                   # API Gateway
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── configServer/              # Config Server
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── userService/               # Example Microservice
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/tutorial/userService/
│   │       │       ├── controller/      # REST controllers
│   │       │       ├── model/           # JPA entities
│   │       │       ├── repositories/    # Data repositories
│   │       │       ├── services/        # Business logic
│   │       │       └── client/          # Feign clients
│   │       └── resources/
│   │           └── application.properties
│   ├── Dockerfile
│   └── pom.xml
└── docker-compose.yml         # Docker orchestration
```

## 🔧 Configuration

### Database Configuration

**Important:** Services running inside Docker must use the internal container port (5432), not the host port (2500).

```properties
# userService/src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://db:5432/userdb
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### Eureka Configuration

All services register with Eureka using the container hostname:

```properties
eureka.client.service-url.defaultZone=http://eureka:8761/eureka
```

### Port Mapping

| Service        | Container Port | Host Port |
|----------------|----------------|-----------|
| API Gateway    | 8080           | 8080      |
| User Service   | 8081           | 8081      |
| Eureka Server  | 8761           | 8761      |
| Config Server  | 8888           | 8888      |
| PostgreSQL     | 5432           | 2500      |

## 🎯 Adding a New Microservice

Follow these steps to add a new microservice to the template:

### 1. Create Spring Boot Project

```bash
# Using Spring Initializr or your IDE
# Dependencies: Web, Eureka Discovery Client, Actuator, Lombok
```

### 2. Add Dependencies to pom.xml

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
</dependencies>
```

### 3. Configure application.properties

```properties
spring.application.name=your-service-name
server.port=8082
eureka.client.service-url.defaultZone=http://eureka:8761/eureka
```

### 4. Create Dockerfile

```dockerfile
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 5. Add to docker-compose.yml

```yaml
your-service:
  build: ./yourService
  container_name: your-service
  restart: always
  ports:
    - "8082:8082"
  depends_on:
    - eureka
  networks:
    - micro-net
```

### 6. Build and Run

```bash
docker-compose up --build
```

## 🔍 Monitoring & Debugging

### View Logs

```bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f user-service
```

### Access Actuator Endpoints

```bash
# Health check
curl http://localhost:8081/actuator/health

# All endpoints
curl http://localhost:8081/actuator

# Metrics
curl http://localhost:8081/actuator/metrics
```

### Database Access

```bash
# Connect to PostgreSQL
docker exec -it db psql -U postgres -d userdb

# List tables
\dt

# Query users
SELECT * FROM users;
```

### Eureka Dashboard

Open <http://localhost:8761> to view:

- Registered services
- Service instances
- Health status

## 🐛 Common Issues & Solutions

### Issue: Can't connect to database

**Problem:** Service can't connect to PostgreSQL

**Solution:** Ensure you're using the correct port:

- Inside Docker network: `db:5432`
- From host machine: `localhost:2500`

### Issue: Service not registering with Eureka

**Problem:** Service doesn't appear in Eureka dashboard

**Solutions:**

1. Check Eureka URL in application.properties
2. Ensure service has `@EnableDiscoveryClient` or `@EnableEurekaClient`
3. Wait 30-60 seconds for registration
4. Check service logs for errors

### Issue: Port already in use

**Problem:** Docker can't start due to port conflict

**Solution:**

```bash
# Find process using port
sudo lsof -i :8080

# Stop existing containers
docker-compose down

# Or change port in docker-compose.yml
```

## 📚 API Documentation

### User Service Endpoints

#### Get User by ID

```http
GET /users/{id}
```

**Response:**

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

## 🧪 Testing

### Run Unit Tests

```bash
# Test specific service
cd userService
mvn test

# Test all services
for dir in eureka gateway configServer userService; do
  cd $dir && mvn test && cd ..
done
```

### Integration Testing

```bash
# Start services
docker-compose up -d

# Wait for services to be ready
sleep 60

# Test endpoints
curl http://localhost:8080/users/1
```

## 🚢 Production Deployment

### Best Practices

1. **Environment Variables:** Externalize sensitive configuration

   ```yaml
   environment:
     - SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}
   ```

2. **Resource Limits:** Set memory and CPU limits

   ```yaml
   deploy:
     resources:
       limits:
         cpus: '1'
         memory: 512M
   ```

3. **Logging:** Configure centralized logging (ELK stack, Splunk)

4. **Security:**
   - Enable Spring Security
   - Use HTTPS
   - Implement OAuth2/JWT authentication

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request


## 🙏 Acknowledgments

- Spring Boot Team
- Spring Cloud Team
- Netflix OSS Team

## 📧 Support

For questions or issues:

- Open an issue on GitHub
- Check existing documentation
- Review Spring Cloud documentation: <https://spring.io/projects/spring-cloud>

---

## Happy Coding! 🚀
