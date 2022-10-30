package com.hackerrank.weather.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherDTO {
    private String date;
    private List<String> city;
    private String sort;
}
