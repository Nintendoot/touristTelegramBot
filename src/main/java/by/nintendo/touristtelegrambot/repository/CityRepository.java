package by.nintendo.touristtelegrambot.repository;

import by.nintendo.touristtelegrambot.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    boolean existsByName(String name);
}
