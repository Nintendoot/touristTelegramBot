package by.nintendo.touristtelegrambot.service;

import by.nintendo.touristtelegrambot.entity.City;
import by.nintendo.touristtelegrambot.exception.CityAlreadyExistsException;
import by.nintendo.touristtelegrambot.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void createCity(City city){
    if(cityRepository.existsByName(city.getName())){
        throw new CityAlreadyExistsException("City already exists.");
    }else{
        city.setCreateTime(LocalDateTime.now().toString());
        cityRepository.save(city);
    }

    

}

}
