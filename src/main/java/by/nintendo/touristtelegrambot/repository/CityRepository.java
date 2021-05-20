package by.nintendo.touristtelegrambot.repository;

import by.nintendo.touristtelegrambot.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByName(String name);

    Optional<City> getCityByName(String name);

    boolean existsByNameAndIdIsNot(String name, long id);
}
