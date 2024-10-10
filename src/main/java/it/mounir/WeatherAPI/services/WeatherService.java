package it.mounir.WeatherAPI.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.mounir.WeatherAPI.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/* Handles weather API calls and Redis cache */
@Service
public class WeatherService {

    @Value("${api.weather.key}")
    private String apiKey;

    //private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<String, WeatherResponse> redisTemplate;

    public WeatherResponse getWeather(String city) {
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + city.toLowerCase() + "?key=" + apiKey;
        String cacheKey = "weather:" + city.toLowerCase();

        /*if data is in the cache */
        if(redisTemplate.hasKey(cacheKey)) {
            WeatherResponse inMemoryWeather = redisTemplate.opsForValue().get(cacheKey);

            System.out.println("*** In-Memory ***");
            return inMemoryWeather;
        }

        /* if data is not in the cache -> call API */
        try {
            WeatherResponse apiWeather = restTemplate.getForObject(url, WeatherResponse.class);

            /* save in-memory if data recived from API is not null */
            if(apiWeather != null){
                redisTemplate.opsForValue().set(cacheKey, apiWeather, 12, TimeUnit.HOURS);
            }
            System.out.println("*** API data ***");
            return apiWeather;
        } catch(RestClientException e) {
            System.err.println("<Error> impossible to get API request" + e.getMessage());

            return null;
        }

    }
}
