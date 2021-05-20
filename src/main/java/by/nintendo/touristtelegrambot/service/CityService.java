package by.nintendo.touristtelegrambot.service;

import by.nintendo.touristtelegrambot.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    void createCity(City city);

    void deleteCity(long idCity);

    List<City> allCities();

    City getCityById(long idCity);

    Optional<City> getCityByName(String nameCity);

    void editCity(long idCityToUpdate, City city);

}
