package com.weather.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.weather.entity.WeatherRecord;
import com.weather.service.WeatherServiceTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

public class WeatherControllerTest {

    @Mock
    private WeatherServiceTest weatherService;

    @InjectMocks
    private WeatherController weatherController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    public void testGetWeather() throws Exception {
        WeatherRecord mockRecord = new WeatherRecord();
        mockRecord.setId(1L);
        mockRecord.setUser("John Doe");
        mockRecord.setPostalCode("94040");
        mockRecord.setTemperature(25.5);
        mockRecord.setHumidity(60);
        mockRecord.setWeatherCondition("clear sky");
        mockRecord.setTimestamp(LocalDateTime.now());

     //   when(weatherService.getWeatherAndSave(any(WeatherRequest.class))).thenReturn(mockRecord);

        String requestJson = "{\"user\":\"John Doe\",\"postalCode\":\"94040\"}";

        mockMvc.perform(post("/api/weather")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user", is("John Doe")))
                .andExpect(jsonPath("$.postalCode", is("94040")))
                .andExpect(jsonPath("$.temperature", is(25.5)))
                .andExpect(jsonPath("$.humidity", is(60)))
                .andExpect(jsonPath("$.weatherCondition", is("clear sky")));
    }

    @Test
    public void testGetAllWeatherByUser() throws Exception {
        WeatherRecord mockRecord1 = new WeatherRecord();
        mockRecord1.setId(1L);
        mockRecord1.setUser("John Doe");
        mockRecord1.setPostalCode("94040");
        mockRecord1.setTemperature(25.5);
        mockRecord1.setHumidity(60);
        mockRecord1.setWeatherCondition("clear sky");
        mockRecord1.setTimestamp(LocalDateTime.now());

        WeatherRecord mockRecord2 = new WeatherRecord();
        mockRecord2.setId(2L);
        mockRecord2.setUser("John Doe");
        mockRecord2.setPostalCode("12345");
        mockRecord2.setTemperature(20.0);
        mockRecord2.setHumidity(70);
        mockRecord2.setWeatherCondition("cloudy");
        mockRecord2.setTimestamp(LocalDateTime.now());

        List<WeatherRecord> mockRecords = List.of(mockRecord1, mockRecord2);

      //  when(weatherService.getAllWeatherByUser("John Doe")).thenReturn(mockRecords);

        mockMvc.perform(get("/api/weather/user/John Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].user", is("John Doe")))
                .andExpect(jsonPath("$[0].postalCode", is("94040")))
                .andExpect(jsonPath("$[1].user", is("John Doe")))
                .andExpect(jsonPath("$[1].postalCode", is("12345")));
    }
}
