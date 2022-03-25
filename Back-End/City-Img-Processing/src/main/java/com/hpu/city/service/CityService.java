package com.hpu.city.service;

import com.hpu.city.model.CityModel;
import com.hpu.city.model.CityResponseModel;

/**
 * Abstraction layer for City Services
 */
public interface CityService {
    /**
     * Method finds all the cities for given pagination controls
     * @param pageNumber
     * @param pageSize
     * @return
     */
    CityResponseModel findAllCities(int pageNumber, int pageSize);

    /**
     * Method updates the existing city
     * @param city
     * @return
     */
    CityModel updateCity(CityModel city);

    /**
     * Method will find the city by its name
     * @param name
     * @return
     */
    CityModel searchByName(String name);

}
