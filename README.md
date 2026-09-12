# Train Ticket Reservation System

A full-stack, web-based Train Ticket Reservation System developed using **Java Servlets**, **JSP**, **JDBC**, and **MySQL**. The application provides an integrated platform for users to search trains between stations, check real-time seat availability, inspect fares, and book tickets, while offering administrators control over train routes, schedules, and ticket inventories.

---

## Features

### User Module
- **Authentication**: User sign-up, sign-in, session-backed logout, profile view/edit, and password changing.
- **Search & Schedule Enquiry**: Query active trains running between source and destination stations.
- **Seat & Fare Calculation**: Dynamic seat availability checks and fare calculations.
- **Ticket Booking & Cancellation**: Streamlined ticket reservations with unique transaction identifiers and cancellations that restore capacity.

### Admin Module
- **Train Inventory Management**: Add new train routes, configure seat capacities, and update schedules or pricing.
- **Cancellation & Monitoring**: Cancel operational routes and inspect overall booking records.

---

## Tech Stack

- **Backend**: Java 8+, Java Servlets, JDBC
- **Frontend**: JSP, HTML5, CSS3, JavaScript
- **Database**: MySQL 5.7+ / 8.0+
- **Application Server**: Apache Tomcat 8.5+ / 9.0+
- **Build Management**: Apache Maven

---

## Database Setup

1. Open your MySQL client or CLI and execute the setup script:
   ```bash
   mysql -u root -p < database/schema.sql
