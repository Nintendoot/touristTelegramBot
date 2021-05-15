package by.nintendo.touristtelegrambot.service;

import by.nintendo.touristtelegrambot.entity.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityServiceImplTest {
    @Autowired
    private CityServiceImpl cityService;

    @Test
    void createCity() {
        List<City> cities = cityService.allCities();
        assertNotNull(cities);
        assertEquals(cities.size(),1);
        City city=new City();
        city.setName("text");
        city.setDescription("text");
        cityService.createCity(city);
        assertNotNull(cities);
        assertEquals(cityService.allCities().size(),2);
        assertEquals(cityService.getCityById(3).getName(),"text");
    }

    @Test
    void deleteCity() {
    }

    @Test
    void allCities() {
        List<City> cities = cityService.allCities();
        assertNotNull(cities);
        assertEquals(cities.size(),2);
        cityService.deleteCity(1);
        assertEquals(cityService.allCities().size(),1);
    }

    @Test
    void getCityById() {
        List<City> cities = cityService.allCities();
        assertNotNull(cities);

    }

    @Test
    void getCityByName() {
    }

    @Test
    void editCity() {
    }
}