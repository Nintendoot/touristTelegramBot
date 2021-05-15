package by.nintendo.touristtelegrambot.controller;

import by.nintendo.touristtelegrambot.entity.City;
import by.nintendo.touristtelegrambot.repository.CityRepository;
import by.nintendo.touristtelegrambot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/city")
public class RestCityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<Object> createCity(@RequestBody @Valid City city) {
        cityService.createCity(city);
        return new ResponseEntity<>("City create.", HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<City>> allCity(){
        return new ResponseEntity<>(cityService.allCities(),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteCity(@PathVariable("id") long id) {
        cityService.deleteCity(id);
        return new ResponseEntity<>("City deleate.", HttpStatus.OK);
    }

}
