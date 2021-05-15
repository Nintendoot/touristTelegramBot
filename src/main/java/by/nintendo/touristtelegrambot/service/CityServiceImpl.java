package by.nintendo.touristtelegrambot.service;

import by.nintendo.touristtelegrambot.entity.City;
import by.nintendo.touristtelegrambot.exception.CityAlreadyExistsException;
import by.nintendo.touristtelegrambot.exception.CityNotFoundException;
import by.nintendo.touristtelegrambot.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void createCity(City city) {
        log.info("Call method: createCity(name: " + city.getName() + ") ");
        if (cityRepository.existsByName(city.getName())) {
            throw new CityAlreadyExistsException("City " + city.getName() + " already exists.");
        } else {
            cityRepository.save(city);
            log.info("Create city " + city.getName());
        }
    }

    public void deleteCity(long idCity) {
        log.info("Call method: deleteCity(id: " + idCity + ") ");
        Optional<City> city = cityRepository.findById(idCity);
        if (city.isPresent()) {
            cityRepository.delete(city.get());
            log.info("Delete city" + city.get().getName());
        } else {
            throw new CityNotFoundException("City with id " + idCity + " not found.");
        }
    }

    public List<City> allCities() {
        log.info("Call method: allCities()");
        return cityRepository.findAll();
    }

    public City getCityById(long idCity) {
        log.info("Call method: getCityById(id: " + idCity + ") ");
        Optional<City> city = cityRepository.findById(idCity);
        if (city.isPresent()) {
            log.info("Method: getCityById return city " + city.get().getName());
            return city.get();
        } else {
            throw new CityNotFoundException("City with id " + idCity + " not found.");
        }
    }

    public City getCityByName(String nameCity) {
        log.info("Call method: getCityByName(name: " + nameCity + ") ");
        Optional<City> city = cityRepository.getCityByName(nameCity);
        if (city.isPresent()) {
            log.info("Method: getCityByName return city " + city.get().getName());
            return city.get();
        } else {
            throw new CityNotFoundException("City " + nameCity + " not found.");
        }
    }

    public void editCity(long idCityToUpdate, City city) {
        log.info("Call method: editCity(id: " + idCityToUpdate + ",city(name " + city.getName() + ",description " + city.getDescription() + ")" + ") ");
        Optional<City> cityToUpdate = cityRepository.findById(idCityToUpdate);
        if (cityToUpdate.isPresent()) {
            if (!cityRepository.existsByNameAndIdIsNot(city.getName(), idCityToUpdate)) {
                cityToUpdate.get().setName(city.getName());
                cityToUpdate.get().setDescription(city.getDescription());
            } else {
                throw new CityAlreadyExistsException("City " + city.getName() + " already exists.");
            }
            cityRepository.save(cityToUpdate.get());
            log.info("Сity " + cityRepository.findById(idCityToUpdate).get().getName() + " update.");
        } else {
            throw new CityNotFoundException("City id " + idCityToUpdate + " not found.");
        }
    }

}
