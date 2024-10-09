package it.mounir.WeatherAPI.services;

import it.mounir.WeatherAPI.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${api.weather.key}")
    private String apiKey;

    //private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + city + "?key=" + apiKey;

        try {
            return restTemplate.getForObject(url, WeatherResponse.class);
        } catch(RestClientException e) {
            System.err.println("Error, impossible to get API request");
            return null;
        }

    }
}
