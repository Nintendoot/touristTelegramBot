package by.nintendo.touristtelegrambot.bot;

import by.nintendo.touristtelegrambot.entity.City;
import by.nintendo.touristtelegrambot.repository.CityRepository;
import by.nintendo.touristtelegrambot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
public class CommandTouristTelegramBot {
    @Qualifier("cityServiceImpl")
    @Autowired
    private CityService cityService;

    @Autowired
    private CityRepository cityRepository;


    public String findBotCommand(String s) {
       if(s.toLowerCase().equals(Constants.START.getItem())){
           return Constants.HELLO.getItem();
       }
       else if (s.toLowerCase().equals(Constants.ALL_CITY.getItem())){
           return cityService.allCities().stream()
                   .map(x->x.toString())
                   .collect(Collectors.joining(", \n"));
       }
       else if(cityRepository.existsByName(s)){
           return cityService.getCityByName(s).getDescription();
       }
       else if(s.toLowerCase().equals(Constants.HELP.getItem())){
           return "HELLLLLPPPPP";
       }
       else{
           return "Sorry(((Я пока ничего не могу рассказать о "+s;
       }
    }

}
