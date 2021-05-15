package by.nintendo.touristtelegrambot.service;

import by.nintendo.touristtelegrambot.entity.City;

import java.util.List;

public interface CityService {
    void createCity(City city);
    void deleteCity(long idCity);
    List<City> allCities();
    City getCityById(long idCity);
    City getCityByName(String nameCity);
    void editCity(long idCityToUpdate, City city);

}
