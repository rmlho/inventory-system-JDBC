# Inventory System JDBC
A Java-based inventory management system that uses JDBC to connect to a PostgreSQL database using JDBC. This project allows you to manage product data with basic reporting features.
---
## Features
- **Product Management:** Add, update, and manage product records.
- **Database Integration:** Connects to a PostgreSQL database using the JDBC driver.
- **Reporting:** Includes a `reportMain` method for generating reports on inventory data.
- **Maven Project:** Built with Apache Maven for easy dependency management.
## Technologies Used
- **Java** (Core Java, JDBC)
- **PostgreSQL** (Database)
- **Maven** (Build Tool)
- **IntelliJ IDEA** (IDE)
## Project Structure

inventory-system-JDBC/  
├── .idea/  
├── lib/  
├── src/main/java/com/inventory/product/  
├── pom.xml  
└── README.md

## Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Maven
- PostgreSQL database
- IntelliJ IDEA (recommended)
### Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/rmlho/inventory-system-JDBC.git
   cd inventory-system-JDBC

2. **Set up the PostgreSQL database:**
    
    - Create a database for the inventory system.
        
    - Note the database URL, username, and password.
        
3. **Configure the database connection:**
    
    - Locate the source files in `src/main/java/com/inventory/product`.
        
    - Update the JDBC connection details (URL, username, password) in the appropriate class.
        
4. **Build the project with Maven:**
    
    bash
    
    mvn clean install
    
5. **Run the application:**
    
    - Execute the `main` method from your IDE, or run the compiled JAR file from the `target` directory.
        

## Usage

The system provides two main entry points:

- `main`: The primary method to run the inventory management application.
    
- `reportMain`: A method dedicated to generating reports based on the inventory data.
    

## Contributing

1. Fork the repository.
    
2. Create a new branch (`git checkout -b feature/YourFeature`).
    
3. Commit your changes (`git commit -m 'Add some feature'`).
    
4. Push to the branch (`git push origin feature/YourFeature`).
    
5. Open a Pull Request.
    

## License

This project is open-source. Please check the repository for license details.

## Author

**rmlho**

- GitHub: [@rmlho](https://github.com/rmlho)
