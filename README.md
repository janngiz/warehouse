# ClusteredData Warehouse

Welcome to the ClusteredData Warehouse project! This project aims to develop a data warehouse for Bloomberg to analyze FX deals. One of the customer stories is to accept deal details and persist them into a database.

## Table of Contents
- [Requirements](#requirements)
- [Technologies](#technologies)
- [Deployment](#deployment)
- [Building and Running](#building-and-running)
- [Postman Collection](#Postman Collection)
- [Testing](#testing)


## Requirements

- Implement a system to accept deal details and persist them into a database.
- Validate the structure of the request, checking for missing fields and proper type formats.
- Ensure that duplicate requests are not imported into the system.
- All successfully imported rows should be saved in the database.
- Use either Postgres, MySQL, or MongoDB as the database.
- Provide proper error/exception handling.
- Implement logging for tracking and debugging.
- Develop unit tests with sufficient coverage.

## Technologies

This project is built using the following technologies:

- Spring Boot
- gradle
- Java 11
- JPA
- MYSQL database


## Deployment

The project includes a Docker Compose configuration (`docker-compose.yml`) for a workable deployment environment, including the selected database and application services.

## Building and Running

To build and run the application using Docker Compose:

1. Make sure you have Docker and Docker Compose installed.
2. Clone the repository: `git clone  git@github.com:janngiz/warehouse.git`
3. Run: `docker-compose up`


## Postman Collection

A Postman collection containing example API requests is included in this repository. To import the collection into Postman, follow these steps:

1. Open Postman and click on the `Import` button in the top left corner.
2. In the `Import` dialog, select the `File` tab and click on `Choose Files`.
3. Navigate to the `postman` directory in this repository and select the JSON file representing the collection.
4. Click on `Open` to import the collection into Postman.

Once imported, you can use the requests in the collection to test the API endpoints provided by this project.


## Testing

Unit tests have been implemented to ensure the functionality and reliability of the application. To run the tests:

1. Run: `./gradlew test`

Thank you for being part of the ClusteredData Warehouse project. If you have any questions or need further assistance, feel free to contact us.

Project developed by [Pawan Gurung]

GitHub Repository: [https://github.com/janngiz/warehouse](https://github.com/janngiz/warehouse)