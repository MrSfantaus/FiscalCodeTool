# FiscalCodeTool
FiscalCodeTool is a web application developed using Java Spring Boot for the backend and Angular for the frontend. The main objective of this project is to provide a tool that allows users to validate and generate fiscal codes, while also offering support for homographic cases.

## What is FiscalCodeTool?
FiscalCodeTool is a comprehensive tool designed to handle the complexities of Italian fiscal codes (Codice Fiscale). In Italy, the fiscal code is an alphanumeric code of 16 characters that uniquely identifies individuals for taxation and administrative purposes. It is calculated based on personal data such as name, surname, date, and place of birth.

### Homographic Cases
Homographic cases, also known as "omocodia" in Italian, refer to situations where different individuals have the same fiscal code due to identical personal details. This can occur when individuals share the same name, surname, date, and place of birth. FiscalCodeTool includes algorithms to manage and differentiate individuals in such scenarios, ensuring accuracy in fiscal code generation and validation.

## Features
 - Fiscal Code Generation: Generate valid fiscal codes based on user-provided personal information.
 - Fiscal Code Validation: Validate existing fiscal codes to ensure correctness.
 - Homographic Case Handling: Detect and handle homographic cases to prevent duplication of fiscal codes.
 - User-Friendly Interface: Intuitive web interface built with Angular for easy interaction and SwaggerUI for testing APIs.

## Technologies Used
 - Backend: Java Spring Boot framework for robust and scalable server-side development.
 - Frontend: Angular framework for building dynamic and responsive user interfaces.
 - Database: Integration with a relational database (e.g., PostgreSQL, MySQL) for storing user data and configurations.
 - APIs: Utilize third-party APIs for additional functionalities such as geolocation for place of birth detection.

## Getting Started
To run FiscalCodeTool locally, follow these steps:

1. Clone the Repository: `git clone https://github.com/MrSfantaus/FiscalCodeTool.git`
2. Navigate to Backend Directory: `cd FiscalCodeTool/backend`
3. Run Backend Server: `docker-compose up --build`
4. If you want test the APIs open your web browser and go to [Swagger-UI](http://localhost:8080/swagger-ui/index.html)
5. Navigate to Frontend Directory: `cd ../frontend`
6. Install Dependencies: npm install
7. Run Frontend Server: ng serve
8. Access the Application: Open your web browser and go to http://localhost:4200

## Contributors
Simone Mattei [@MrSfantaus](https://github.com/MrSfantaus)

## License
This project is licensed under the MIT License - see the LICENSE file for details.