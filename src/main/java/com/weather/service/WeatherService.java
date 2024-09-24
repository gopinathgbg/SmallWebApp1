package com.weather.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.weather.repo.WeatherRepository;
import com.weather.request.WeatherRequest;
import com.weather.entity.WeatherRecord;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private final String API_KEY = "e68341e3c43472557b7b9b68f6e7ec05"; // Replace with your actual API key

    public WeatherRecord getWeatherAndSave(WeatherRequest request) {
        WebClient webClient = webClientBuilder.build();

        // Build the OpenWeather API URL
        String url = API_URL + "?zip=" + request.getPostalCode() + ",us&appid=" + API_KEY + "&units=metric";

        // Call the API and parse the response
        Mono<Map> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class);

        Map<String, Object> responseBody = response.block();

        double temperature = (double) ((Map<String, Object>) responseBody.get("main")).get("temp");
        int humidity = (int) ((Map<String, Object>) responseBody.get("main")).get("humidity");
        String weatherCondition = (((java.util.List) responseBody.get("weather")).get(0)).toString();

        // Save to database
        WeatherRecord record = new WeatherRecord();
        record.setUser(request.getUser());
        record.setPostalCode(request.getPostalCode());
        record.setTemperature(temperature);
        record.setHumidity(humidity);
        record.setWeatherCondition(weatherCondition);
        record.setTimestamp(LocalDateTime.now());

        return weatherRepository.save(record);
    }
    
    public List<WeatherRecord> getAllWeatherByPostalCode(String postalCode) {
        return weatherRepository.findByPostalCode(postalCode);
    }
    public List<WeatherRecord> getAllWeatherByUser(String user) {
        return weatherRepository.findByUser(user);
    }
    
}
