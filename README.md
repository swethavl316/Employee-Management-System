# Employee Management System

## Project Overview

This Employee Management System is a simple web application built using **Java Servlets** and **JSP/HTML**. It includes core functionalities such as user registration, login, employee data entry, editing, and deletion.

## Technology Stack

* **Frontend**: HTML, CSS
* **Backend**: Java (Servlets)
* **Database**: MySQL
* **Build Tool**: Not applicable (basic Java web application)
* **Recommended IDE**: Eclipse or IntelliJ IDEA

## Project Structure

```
EmployeeManagementSystem/
├── build/classes
├── src/main/java
│   ├── AddEmployeeServlet.java
│   ├── DeleteEmployeeServlet.java
│   ├── EditEmployeeServlet.java
│   ├── LoginServlet.java
│   ├── RegisterServlet.java
│   ├── UpdateEmployeeServlet.java
│   └── ViewEmployeeServlet.java
├── src/main/webapp
│   ├── META-INF/MANIFEST.MF
│   ├── WEB-INF/lib/mysql-connector-j-9.3.0.jar
│   ├── add.html
│   ├── index.html
│   ├── login.html
│   └── Register.html
```

## Setup Instructions

### 1. Install Prerequisites

* Java JDK
* Apache Tomcat (version 9 or 10)
* MySQL Server
* MySQL JDBC Connector (JAR file)

### 2. Database Configuration

* Create a database named `ems` in your MySQL server.
* Create a table named `employees` with appropriate fields (`id`, `name`, `email`, `department`, etc.) as referenced in the Java code.
* Create a user authentication table if login functionality is implemented.

### 3. IDE Configuration

* Import the project into Eclipse or IntelliJ as a Dynamic Web Project.
* Configure the Tomcat server.
* Add the MySQL connector JAR to the `WEB-INF/lib` directory.
* Deploy and run the project on the local server.

### 4. Access the Application

Open your browser and navigate to: `http://localhost:8080/EmployeeManagementSystem/index.html`

## Features

* Admin Registration and Login
* Add Employee Records
* Edit Employee Details
* Delete Employee Records
* View All Employee Entries

## Configuration Note

Ensure that your MySQL database credentials (username and password) used in the servlet files are correct. Default values are typically:

* Username: `root`
* Password: `your_mysql_password`


## License

This project is licensed under the MIT License, or you may include your own license information.
