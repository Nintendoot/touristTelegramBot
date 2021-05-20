package by.nintendo.touristtelegrambot.controller;

import by.nintendo.touristtelegrambot.entity.City;
import by.nintendo.touristtelegrambot.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/city")
public class RestCityController {
    private final CityService cityService;

    public RestCityController(@Qualifier("cityServiceImpl") CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<Object> createCity(@RequestBody @Valid City city) {
        log.info("POST request /city");
        cityService.createCity(city);
        return new ResponseEntity<>("City create.", HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<City>> allCity() {
        log.info("GET request /city/all");
        return new ResponseEntity<>(cityService.allCities(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteCity(@PathVariable("id") long id) {
        log.info("DELETE request /city/" + id);
        cityService.deleteCity(id);
        return new ResponseEntity<>("City deleate.", HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getCityById(@PathVariable("id") long id) {
        log.info("GET request /city/" + id);
        return new ResponseEntity<>(cityService.getCityById(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateCity(@PathVariable("id") long id, @RequestBody @Valid City city) {
        log.info("PUT request /city/" + id);
        cityService.editCity(id, city);
        return new ResponseEntity<>("City " + city.getName() + " update.", HttpStatus.OK);
    }


}
