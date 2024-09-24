package com.weather.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.weather.entity.WeatherRecord;
import com.weather.repo.WeatherRepository;
import com.weather.request.WeatherRequest;

import reactor.core.publisher.Mono;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private WeatherService weatherService;

    private WebClient webClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a mock WebClient and mock its behavior
        RequestHeadersUriSpec<?> uriSpecMock = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec<?> headersSpecMock = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpecMock = mock(ResponseSpec.class);

        // Mock the response body
        when(webClientBuilder.build()).thenReturn(webClient);
  //      when(webClient.get()).thenReturn(uriSpecMock);
   //     when(uriSpecMock.uri(anyString())).thenReturn(headersSpecMock);
        when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(any(Class.class))).thenReturn(Mono.just(
                Map.of(
                        "main", Map.of("temp", 25.5, "humidity", 60),
                        "weather", java.util.List.of(Map.of("description", "clear sky"))
                )
        ));
    }

    @Test
    public void testGetWeatherAndSave() {
        // Mock the repository save method
        WeatherRecord savedRecord = new WeatherRecord();
        savedRecord.setId(1L);
        savedRecord.setUser("John Doe");
        savedRecord.setPostalCode("94040");
        savedRecord.setTemperature(25.5);
        savedRecord.setHumidity(60);
        savedRecord.setWeatherCondition("clear sky");

        when(weatherRepository.save(any(WeatherRecord.class))).thenReturn(savedRecord);

        // Create a request object
        WeatherRequest request = new WeatherRequest();
        request.setUser("John Doe");
        request.setPostalCode("94040");

        // Call the method under test
        WeatherRecord result = weatherService.getWeatherAndSave(request);

        // Verify the save method was called
        ArgumentCaptor<WeatherRecord> recordCaptor = ArgumentCaptor.forClass(WeatherRecord.class);
        verify(weatherRepository).save(recordCaptor.capture());

        // Assert the saved values
        WeatherRecord capturedRecord = recordCaptor.getValue();
        assertEquals("John Doe", capturedRecord.getUser());
        assertEquals("94040", capturedRecord.getPostalCode());
        assertEquals(25.5, capturedRecord.getTemperature());
        assertEquals(60, capturedRecord.getHumidity());
        assertEquals("clear sky", capturedRecord.getWeatherCondition());

        // Assert the returned value
        assertEquals(savedRecord, result);
    }
}
