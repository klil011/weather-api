# Weather API Application

**Disclaimer**: The project idea for this application were acquired from the [Developer RoadMaps](https://roadmap.sh) website. The project idea can be found at the following link: [Weather API Wrapper Service](https://roadmap.sh/projects/weather-api-wrapper-service).

This is a Spring Boot application that fetches weather data from a third-party API (VisualCrossing) and caches the data in-memory using Redis. The weather data is fetched based on the city provided by the user, and if the data is already in the cache, it will be retrieved from Redis instead of making an API call.

## Features

- Fetches weather data from the VisualCrossing API.
- Caches the weather data in Redis for 12 hours to avoid frequent API calls.
- If data is not available in Redis, it automatically fetches the data from the API and saves it in Redis.
  
## Prerequisites

Before running the application, make sure you have the following installed:

1. **Java 11+**
2. **Maven 3.x+** (for building the project)
3. **Redis** (running locally)

## Getting Started

**Setup the VisualCrossing API Key**

You need to get an API key from VisualCrossing to be able to fetch the weather data.

Once you have your API key, you can add it to your application properties.

Open the src/main/resources/application.properties file and add the following line:

    api.weather.key=your_visual_crossing_api_key

**Setup Redis**

Make sure you have a running Redis instance on your machine (i used a local instance, to keep it simple, but you can use a remote one).

If you're using a local Redis instance, you don't need to modify anything. By default, the application expects Redis to be running at localhost:6379.

If your Redis server is running on a different host/port, update your application.properties with the appropriate Redis configuration:

    spring.redis.host=your_redis_host
    spring.redis.port=your_redis_port

**Build and run the application**

To build the project, run:

    mvn clean install

To start the application, run:

    mvn spring-boot:run

The application will start on http://localhost:8080.

**Test the application**

You can test the weather API endpoint by making a GET request to the following URL (i used PostMan), replacing city_name with the city you want to query:

http://localhost:8080/api/weather/city_name

**Caching Mechanism**

When a weather request is made for a city:

- The application checks if the data for that city is present in Redis.
- If the data is in Redis, it returns the cached data.
- If the data is not in Redis, the application makes an API request to VisualCrossing, fetches the data, and stores it in Redis for 12 hours.
