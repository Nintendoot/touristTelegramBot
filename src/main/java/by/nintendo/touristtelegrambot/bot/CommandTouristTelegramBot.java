package by.nintendo.touristtelegrambot.bot;

import by.nintendo.touristtelegrambot.entity.City;
import by.nintendo.touristtelegrambot.service.CityService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommandTouristTelegramBot {

    private final CityService cityService;

    public CommandTouristTelegramBot(@Qualifier("cityServiceImpl") CityService cityService) {
        this.cityService = cityService;
    }


    public String findBotCommand(String s) {
        switch (s) {
            case "/start":
                return "Привет!)Я помогу тебе узнать о интересных местах в разных городах. Введи город: ";
            case "All city":

                return cityService.allCities().stream()
                        .map(City::toString)
                        .collect(Collectors.joining(", \n"));
            case "Help":
                return "Если у вас есть вопросы и предложения пишите на почту nintendoot@gmail.com";
            default:
                Optional<City> cityByName = cityService.getCityByName(s);
                if (cityByName.isPresent()) {
                    return cityByName.get().getDescription();
                } else {
                    return "Sorry(((Я пока ничего не могу рассказать о городе " + s;
                }
        }
    }

}
