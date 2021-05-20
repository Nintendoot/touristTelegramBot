package by.nintendo.touristtelegrambot.service;

import by.nintendo.touristtelegrambot.entity.City;
import by.nintendo.touristtelegrambot.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityServiceImplTest {
    @Autowired
    private CityServiceImpl cityService;

    @Test
    void createCity() {
        List<City> cities = cityService.allCities();
        assertNotNull(cities);
        assertEquals(cities.size(),6);
        City city=new City();
        city.setName("text");
        city.setDescription("text");
        cityService.createCity(city);
        assertNotNull(cities);
        assertEquals(cityService.allCities().size(),7);
        assertEquals(cityService.getCityById(7).getName(),"text");
    }

    @Test
    void deleteCity() {
        assertNotNull(cityService.allCities());
        assertEquals(cityService.allCities().size(),7);
        cityService.deleteCity(1);
        assertEquals(cityService.allCities().size(),6);

    }

    @Test
    void allCities() {
        List<City> cities = cityService.allCities();
        assertNotNull(cities);
        assertEquals(cities.size(),6);
    }

    @Test
    void getCityById() {
        List<City> cities = cityService.allCities();
        assertNotNull(cities);
        City cityById = cityService.getCityById(1);
        assertEquals(cityById.getName(),"Москва");

    }

    @Test
    void getCityByName() {
        List<City> cities = cityService.allCities();
        assertNotNull(cities);
        City cityById = cityService.getCityById(1);
        assertEquals(cityById.getName(),"Москва");
        Optional<City> cityByName = cityService.getCityByName("Москва");
        assertEquals(cityById.getId(),cityByName.get().getId());


    }

    @Test
    void editCity() {
        City cityById = cityService.getCityById(1);
        assertEquals(cityById.getName(),"Москва");
        City  city=new City();
        city.setName("text1");
        city.setDescription("text1");
        cityService.editCity(1,city);
        assertEquals(cityService.getCityById(1).getName(),"text1");

    }
}