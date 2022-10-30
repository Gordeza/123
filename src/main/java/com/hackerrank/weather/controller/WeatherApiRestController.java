package com.hackerrank.weather.controller;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.model.WeatherDTO;
import com.hackerrank.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeatherApiRestController {

    private final WeatherService weatherService;

    @PostMapping("/weather")
    public ResponseEntity<Weather> addWeather(@RequestBody Weather weather) {
        return new ResponseEntity(this.weatherService.addWeather(weather), HttpStatus.CREATED);
    }

    @GetMapping("/weather")
    public ResponseEntity<List<Weather>> getWeather(WeatherDTO weatherDTO) throws ParseException {
        return ResponseEntity.ok(this.weatherService.getWeathers(weatherDTO));
    }

    @GetMapping("/weather/{id}")
    public ResponseEntity<Weather> getWeatherById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.weatherService.getWeatherById(id));
    }
}
