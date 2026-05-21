# TechSync - Online Examination System

TechSync is a Java-based Online Examination System built using the MVC architecture, Servlet technology, JDBC, and MySQL. The application automates exam delivery for students, supports answer submission, and generates results instantly. It is designed as a hands-on learning project for Java web application development, database connectivity, session management, CRUD operations, and backend system design.

## Key Features

- Student registration and login
- Admin login and secure dashboard
- Course, subject, question, and exam management
- Student exam scheduling and exam participation
- Answer submission and instant result calculation
- Session-based access control for students and admins
- Profile management and update capabilities
- JSP/HTML front-end with Bootstrap-based UI pages

## Project Architecture

- MVC design pattern
- `Controller` package contains servlet controllers for request processing
- `Model` package contains entity classes for admin, student, course, exam, subjects, and questions
- `Repositry` package contains database access implementations using JDBC
- `Service` package provides business logic layer between controllers and repository
- `webapp` contains HTML views, CSS, and JavaScript assets

## Technology Stack

- Java 21
- Jakarta Servlet API 6.1.0
- JDBC
- MySQL
- Maven WAR packaging
- Bootstrap for UI styling
- Tomcat servlet container for deployment

## Database Configuration

The project is configured to connect to MySQL using the following connection string in `TestSync/src/main/java/com/TestSync/Repositry/DBConfig.java`:

```java
conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineexamdb","root","Pass@12345");
```

Update the URL, database name, username, and password as needed to match your local MySQL environment.

## Setup and Deployment

1. Install Java JDK 21 and Apache Tomcat.
2. Install MySQL and create a database named `onlineexamdb`.
3. Create required tables and seed initial data as expected by the application.
4. Open the project in your IDE (Eclipse, IntelliJ IDEA, or VS Code).
5. Build the project with Maven:

```bash
mvn clean package
```

6. Deploy the generated WAR file from `TestSync/target/TestSync-0.0.1-SNAPSHOT.war` to Tomcat.
7. Start Tomcat and open the application in your browser.

## Application Flow

- `Home.html` provides navigation to student and admin login pages.
- Students can register through `StudentRegisteration.html` and log in through `StudentLogin.html`.
- Admins log in via `AdminLogin.html` and manage courses, subjects, exams, questions, and student data.
- Students can view scheduled exams, start an exam, submit answers, and view exam results.

## Important Files and Directories

- `TestSync/pom.xml` - Maven project configuration
- `TestSync/src/main/java/com/TestSync/Controller` - Servlets handling HTTP requests
- `TestSync/src/main/java/com/TestSync/Model` - Data models
- `TestSync/src/main/java/com/TestSync/Repositry` - JDBC database access
- `TestSync/src/main/java/com/TestSync/Service` - Business logic layer
- `TestSync/src/main/webapp` - HTML pages, CSS, JavaScript and web resources
- `TestSync/src/main/webapp/WEB-INF/web.xml` - Servlet configuration

## Notes

- Ensure MySQL is running before starting the application.
- Verify the database connection details in `DBConfig.java` before deployment.
- Use Tomcat 11+ for compatibility with Jakarta Servlet API 6.

## License

This project is provided for educational use and demonstration of Java web application concepts.
