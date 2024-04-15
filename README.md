# Electric Scooter Rental Web Application

This is a web application developed for renting electric scooters in Novi Sad. It allows users to browse available scooters, make reservations, and manage scooter rentals.

---

## Features

- View all scooters with pagination
- Add, update, and delete scooters (Admin only)
- Rent scooters
- Return rented scooters
- Fill scooter battery to 100% (Admin only)
- Search scooters by address and battery level

---

## Technologies Used

- Java
- Spring Boot
- React
- Bootstrap
- MySQL

---

## Getting Started

To run the application locally, follow these steps:

1. Clone the repository:

`git clone https://github.com/CecaDj/ftn_informatika_final_project.git`

2. Navigate to the project directory:
   
`cd ftn_informatika_final_project`

4. Install dependencies:

Install backend dependencies

`cd back`

`mvn install`

Install frontend dependencies

`cd ../front`

`npm install`

4. Configure the database by updating `application.properties` with your database credentials.

5. Run the application:

Start the backend server

`cd ../back`

`mvn spring-boot:run`

Start the frontend server

`cd ../front`

`npm start`

6. Access the application at `http://localhost:3000`.

---

## API Endpoints

- `GET /api/addresses` - Get all addresses
- `GET /api/scooters` - Get all scooters (paginated)
- `GET /api/scooters/{id}` - Get scooter by ID
- `POST /api/scooters` - Add a new scooter
- `PUT /api/scooters/{id}` - Update existing scooter
- `DELETE /api/scooters/{id}` - Delete scooter by ID
- `PUT /api/scooters/{id}/return` - Return rented scooter
- `POST /api/reservations` - Rent a scooter

---

## Functionality

- Users can browse, rent, and return scooters.
- Administrators can add, update, and delete scooters.
- Pagination and search functionality are available for easier navigation.

---

## Authentication

The application requires users to log in before accessing any functionality. Only logged-in users can rent and return scooters.
Authentication is implemented using JWT (JSON Web Token) authentication.

### Admin Credentials

- Username: miroslav
- Password: miroslav

### User Credentials

- Username: tamara
- Password: tamara

---

