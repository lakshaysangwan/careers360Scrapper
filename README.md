# careers360Scrapper

A Spring Boot project for scraping data from careers360.com to extract information about various Indian colleges and courses.

## Features

- Scrapes data from careers360.com to retrieve details about colleges and courses.
- Retrieves information such as college name, location, courses offered, fee structure, and more.
- Stores the scraped data in a database for further analysis and usage.
- Provides APIs to query and access the scraped data.

## Technologies Used

- Spring Boot
- Spring Data JPA
- Jsoup (for web scraping)
- MySQL (as the database)

## Installation

1. Clone the repository:

   ```shell
   git clone https://github.com/lakshaysangwan/careers360Scrapper.git
   ```

2. Navigate to the project directory:

   ```shell
   cd careers360Scrapper
   ```

3. Set up the database:

   - Create a MySQL database.
   - Update the database connection configuration in the `application.properties` file with your database credentials.

4. Build and run the application:

   ```shell
   ./mvnw spring-boot:run
   ```

   This will start the Spring Boot application and data collection will be started immediatly.

## Usage

The application will automatically scrape data from careers360.com and store it in the database upon startup.

## Contributing

Contributions to the project are welcome. If you would like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch.
3. Make your changes and commit them.
4. Push your changes to the forked repository.
5. Open a pull request, describing the changes you have made.

## Contact

For any questions or inquiries, please feel free to [contact the project maintainer](mailto:lakshay.ib.hisar@gmail.com).

---
