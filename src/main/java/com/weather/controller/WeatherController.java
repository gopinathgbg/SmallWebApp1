package com.weather.controller;


import java.lang.annotation.Repeatable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.weather.entity.WeatherRecord;
import com.weather.request.WeatherRequest;
import com.weather.service.WeatherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Weather API", tags = {"Weather"})
@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved temprature,humidity and weather condition"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(responseCode = "500", description = "City value can not be null")
    })
    @PostMapping
    public ResponseEntity<WeatherRecord> getWeather(@RequestBody WeatherRequest request) {
        WeatherRecord record = weatherService.getWeatherAndSave(request);
        return ResponseEntity.ok(record);
    }
    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved All records history for postal code"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(responseCode = "500", description = "Postal Code can not be null")
    })
    @ApiOperation(value = "Get all weather conditions fetched for a USA Postal Code")
    @GetMapping("/{postalCode}")
    public ResponseEntity<List<WeatherRecord>> getAllWeatherByPostalCode(@PathVariable String postalCode) {
        List<WeatherRecord> records = weatherService.getAllWeatherByPostalCode(postalCode);
        return ResponseEntity.ok(records);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved All records history for requested User"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(responseCode = "500", description = "username can not be null")
    })
    @ApiOperation(value = "Get all weather conditions fetched for a User")
    @GetMapping("/user/{user}")
    public ResponseEntity<List<WeatherRecord>> getAllWeatherByUser(@PathVariable String user) {
        List<WeatherRecord> records = weatherService.getAllWeatherByUser(user);
        return ResponseEntity.ok(records);
    }
}
