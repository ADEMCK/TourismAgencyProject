# Patika Turizm Acentesi Turizm Acente Sistemi

This project is a software solution developed to digitalize the daily operations of Patika Turizm Acentesi. The software encompasses various features such as hotel management, room management, period management, pricing management, room search, and reservation operations.

## Technologies Used

- **Java**
- **Java Swing (GUI)**
- **PostgreSQL**

## Installation

1. Clone this project to your local machine.
2. Create a database named `turizmacentesistemi` in PostgreSQL.
3. Use the `turizmacentesistemi.sql` file to create the database tables.
4. Configure the database connection by editing the `DatabaseConnection.java` file.

## Project Structure

- **business**: Contains service classes that implement the business logic.
- **core**: Contains the script for creating the database.
- **dao**: Contains DAO (Data Access Object) classes that handle database operations.
- **entity**: Contains model classes that represent database tables.
- **views**: Contains Swing GUI classes that build the user interface.

## Additional Features

### Hotel Addition and Removal

The tourism agency system allows employees to add new hotels or remove existing ones from the system. This feature ensures that the agency can keep its hotel portfolio up to date, offering a wide range of options to its customers. The addition and removal of hotels are carried out through an easy-to-use interface, allowing employees to perform operations quickly and accurately.

### Reservation Booking

The system enables employees to book hotel rooms based on customer requests. With its user-friendly interface, employees can easily search for and reserve rooms that meet the customers' date range and criteria. The reservation process is completed by entering customer details and reservation specifics, which are then saved to the database.

### Room Management

Employees can manage rooms in existing hotels, update room information, and track room statuses.

### Period Management

Employees can adjust pricing and room availability on a periodic basis.

### Pricing Management

The system allows for the setting and updating of hotel room prices.

### Room Search

Employees can conduct room searches based on customer demands.
