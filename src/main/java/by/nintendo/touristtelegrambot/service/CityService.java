package by.nintendo.touristtelegrambot.service;

import by.nintendo.touristtelegrambot.entity.City;
import by.nintendo.touristtelegrambot.exception.CityAlreadyExistsException;
import by.nintendo.touristtelegrambot.exception.CityNotFoundException;
import by.nintendo.touristtelegrambot.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void createCity(City city) {
        log.info("Call method: createCity(name: " + city.getName() + ") ");
        if (cityRepository.existsByName(city.getName())) {
            throw new CityAlreadyExistsException("City already exists.");
        } else {
            cityRepository.save(city);
            log.info("Create city  name: " + city.getName());
        }
    }

    public void deleteCity(long idCity) {
        log.info("Call method: deleteCity(id: " + idCity + ") ");
        Optional<City> city = cityRepository.findById(idCity);
        if (city.isPresent()) {
            cityRepository.delete(city.get());
            log.info("Delete city" + city.get().getName());
        } else {
            throw new CityNotFoundException("City not found.");
        }
    }

    public List<City> allCities() {
        return cityRepository.findAll();
    }

}
