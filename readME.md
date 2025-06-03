# ForecastForTheDay

ForecastForTheDay is a Java application that retrieves daily weather information for a specified location using the [weather.gov API](https://www.weather.gov/documentation/services-web-api) and logs the data to a file every 24 hours.

## Features

- Fetches weather data based on grid points from the weather.gov API
- Logs daily weather information to a local file
- Automated to run every 24 hours

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Internet connection to access the weather.gov API



### Usage

- Upon execution, the application will:
- Retrieve the current weather data for the specified location

- Write the weather information to a local file

- Repeat this process every 24 hours

Contributing
Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

License
This project is licensed under the MIT License.

### Installation

```bash
# Clone the repository
git clone https://github.com/MurphyAmos/ForecastForTheDay.git

# Navigate to the project directory
cd ForecastForTheDay

# Compile the Java files
javac writeToDates.java weatherApiManager.java

# Run the application
java writeToDates
