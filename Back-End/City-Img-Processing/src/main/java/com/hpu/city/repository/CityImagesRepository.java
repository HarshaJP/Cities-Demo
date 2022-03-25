package com.hpu.city.repository;

import com.hpu.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class represents the repository for City Model
 */
@Repository
public interface CityImagesRepository extends JpaRepository<City, Integer> {

    /**
     * Method is responsible for finding the City by its name
     * @param name
     * @return
     */
    City findCityByName(String name);

}
