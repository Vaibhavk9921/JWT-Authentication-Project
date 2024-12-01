JWT Authentication System

Overview
This project is a robust JWT-based Authentication System implemented using Spring Boot. It allows secure access to REST APIs by validating user credentials and generating JWT tokens for authorized users. The system ensures stateless authentication, making it ideal for scalable web applications.

Features
User Authentication: Authenticate users with a username and password.
JWT Token Generation: Issues a secure JSON Web Token upon successful authentication.
Authorization: Protects sensitive routes, allowing access only to authenticated users.
Custom Exception Handling: Provides meaningful error messages for unauthorized access and invalid credentials.
Scalability: Uses stateless authentication, eliminating the need for session management.
Secure Password Storage: Employs BCrypt for encoding user passwords.
Technologies Used
Java: Programming language.
Spring Boot: Backend framework.
Spring Security: For securing APIs.
JWT (JSON Web Token): For token-based authentication.
Maven: Build tool.
Project Structure
Key Packages
config: Contains security configurations.
SecurityConfig: Configures the security filter chain and authorization rules.
JWTAuthenticationEntryPoint: Handles unauthorized access errors.
models: Includes data models for JwtRequest, JwtResponse, and User.
services: Implements business logic, including the UserDetailsService.
security: Core JWT utilities and filters.
JwtHelper: Manages token generation and validation.
JwtAuthenticationFilter: Intercepts requests to validate JWT tokens.
controller: Handles incoming API requests.
AuthController: Handles user login and token issuance.
HomeController: Demonstrates protected routes.
Endpoints
Authentication
Login
URL: /auth/login
Method: POST
Request Body:
json
Copy code
{
    "username": "testuser",
    "password": "password"
}
Response:
json
Copy code
{
    "jwtToken": "<your-jwt-token>",
    "username": "testuser"
}
Protected Routes
Get Current User
URL: /home/current-user
Method: GET
Headers:
makefile
Copy code
Authorization: Bearer <your-jwt-token>

Setup Instructions
Prerequisites
Java 17 or higher
Gradle
Postman (or any API testing tool)
Steps to Run the Application
Clone the repository:
bash
Copy code
git clone https://github.com/yourusername/jwt-authentication.git
Navigate to the project directory:
bash
Copy code
cd jwt-authentication
Build the project:
bash
Copy code
mvn clean install
Run the application:
bash
Copy code
mvn spring-boot:run
Test the APIs using Postman or any API client.
Sample Request Flow
Authenticate User

Send a POST request to /auth/login with valid credentials.
Receive a JWT token in the response.
Access Protected Route

Send a GET request to /home/current-user.
Include the JWT token in the Authorization header as:
makefile
Copy code
Authorization: Bearer <your-jwt-token>
Security Best Practices
Use a strong secret key for signing JWTs.
Implement refresh tokens for better user experience and enhanced security.
Secure sensitive endpoints with additional validations.
