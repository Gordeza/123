package com.hackerrank.weather.service;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.model.WeatherDTO;

import java.text.ParseException;
import java.util.List;

public interface WeatherService {

    Weather addWeather(Weather weather);

    Weather getWeatherById(int id);

    List<Weather> getWeathers(WeatherDTO weatherDTO) throws ParseException;
}
