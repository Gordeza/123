package com.hackerrank.weather.service;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.model.WeatherDTO;
import com.hackerrank.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    public Weather addWeather(Weather weather) {
        return this.weatherRepository.save(weather);
    }

    @Override
    public Weather getWeatherById(int id) {
        return this.weatherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Weather> getWeathers(WeatherDTO weatherDTO) throws ParseException {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = (weatherDTO.getDate() != null) ? sdformat.parse(weatherDTO.getDate()) : null;

        Sort sort = null;
        if (weatherDTO.getSort() != null)
            sort = weatherDTO.getSort().charAt(0) == '-' ?
                    Sort.by("date").descending() :
                    Sort.by("date").ascending();
        sort = (sort == null) ?
                Sort.by("id").ascending() :
                sort.and(Sort.by("id").ascending());


        return this.weatherRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (weatherDTO.getCity() != null && weatherDTO.getCity().size() > 0) {
                predicate = cb.and(predicate, root.get("city").in(weatherDTO.getCity()));
            }
            if (weatherDTO.getDate() != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("date"), date));
            }


            return predicate;
        }, sort);
    }
}
