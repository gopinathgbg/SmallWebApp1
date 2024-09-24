package com.weather.repo;

import com.weather.entity.WeatherRecord;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherRecord, Long> {
	
	List<WeatherRecord> findByPostalCode(String postalCode);

    List<WeatherRecord> findByUser(String user);
}
