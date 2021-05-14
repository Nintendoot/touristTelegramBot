package by.nintendo.touristtelegrambot.repository;

import by.nintendo.touristtelegrambot.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
    boolean existsByName(String name);
}
