# **Ride Sharing System with Microservices**

This is a **Ride Sharing System** designed using **microservices architecture**. The project demonstrates the implementation of **CQRS (Command Query Responsibility Segregation)**, **Event-Driven Architecture**, and **Kafka-based communication** between services.

## **Project Structure**

The project consists of the following key modules:

### **Modules**

1. **Rider Service**

    * Handles rider-related operations such as submitting ride requests.
    * Publishes `RideRequest` events.

2. **Ride Command Service**

    * Listens to `RideRequest` events and processes the ride request commands.
    * Updates **PostgreSQL** with ride details.

3. **Ride Query Service**

    * Listens to `RideRequest`, `RideAssignment`, and `RideCompletion` events.
    * Updates **MongoDB** with a read-optimized view of ride data.

4. **Driver Service**

    * Handles driver-related operations such as accepting ride assignments and completing rides.
    * Listens for `RideAssignment` events and publishes `RideCompletion` events.

5. **Kafka**

    * Acts as the messaging system for event communication between services using Kafka topics.

---

## **Features & Functionality**

### **Command and Query Responsibility Segregation (CQRS)**

* **CQRS** is implemented to separate **write** operations (commands) from **read** operations (queries), ensuring that the services handling reads and writes are isolated for better scalability, performance, and flexibility.

    * **Command Side**: The services that handle updates to the data (`Ride Assignment Service`, `Driver Service`, `Ride Completion Service`).
    * **Query Side**: The service that handles reads of the data (`Ride Query Service`).

### **Kafka Event-Driven Communication**

* **Kafka** is used to send events between microservices:

    * **Ride Assignment Service** produces ride assignment events that are consumed by the **Driver Service**.
    * **Driver Service** produces events when a ride is completed, consumed by the **Ride Completion Service**.
    * These events are used to update the **Ride Query Service**, ensuring eventual consistency between services.

### **Key Functionalities:**

1. **Rider Service**

    * Accepts ride requests.
    * Publishes `RideRequest` event to Kafka.

2. **Ride Command Service**

    * Listens to `RideRequest` event.
    * Updates **PostgreSQL** with ride details.

3. **Ride Query Service**

    * Listens to `RideRequest`, `RideAssignment`, and `RideCompletion` events.
    * Updates **MongoDB** with the latest ride data (using CQRS principles).

4. **Driver Service**

    * Listens for `RideAssignment` event.
    * Publishes `RideCompletion` event once the ride is completed.

---

## **Steps to Run the Project**

### 1. **Clone the Repository**

Clone the repository from GitHub and navigate to the project directory.

```bash
git clone https://github.com/burakmert/ride-sharing-simulator.git
cd ride-sharing-simulator
```

### 2. **Build the Application**

Ensure you have **Maven** or **Gradle** installed to build the project. You can build the project with the following command:

```bash
mvn clean install
```

### 3. **Set up Docker Containers (Optional for Kafka and MongoDB)**

You can use Docker Compose to bring up the necessary services (Kafka, MongoDB, etc.) for local development. Ensure that your `docker-compose.yml` is correctly set up for Kafka and MongoDB.

```bash
docker-compose up -d
```

### 4. **Run the Services**

Once the Docker containers are set up, run each microservice using Spring Boot’s embedded server. Navigate to each service’s folder and run:

```bash
mvn spring-boot:run
```

Alternatively, you can run the entire application using Docker if the services are Dockerized.

### 6. **Access the Application**

Once all services are running, you can interact with the API endpoints:

* **Rider Service**: `http://localhost:8092/`
* **Ride Command Service**: `http://localhost:8102/`
* **Ride Query Service**: `http://localhost:8112/`
* **Driver Service**: `http://localhost:8122/`

---

## **Technology Stack**

* **Spring Boot**: For building the microservices.
* **Spring Kafka**: For handling event-driven communication between microservices.
* **Apache Kafka**: For message brokering.
* **CQRS**: For separating command and query responsibilities for better scalability and performance.
* **Docker**: For containerizing the services.
* **MongoDB**: For storing and querying ride data in the `RideView` collection.
* **PostgreSQL**: For storing ride data in the command side of the system.

---

## **Event Flow Between Services**

1. **Rider Service** sends a `RideRequest` event.
2. **Ride Command Service** listens to the `RideRequest` event, processes it, and updates **PostgreSQL**.
3. **Ride Query Service** listens for `RideRequest`, `RideAssignment`, and `RideCompletion` events and updates **MongoDB RideView**.
4. **Driver Service** listens for `RideAssignment` events and publishes `RideCompletion` events after the ride is completed.
5. The `RideCompletion` event updates **PostgreSQL** and **MongoDB** as needed.
