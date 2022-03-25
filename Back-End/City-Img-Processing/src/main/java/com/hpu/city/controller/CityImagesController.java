package com.hpu.city.controller;

import com.hpu.city.model.CityModel;
import com.hpu.city.model.CityResponseModel;
import com.hpu.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for the city demo application
 */
@RestController()
@RequestMapping("cities")
public class CityImagesController {

    @Autowired
    CityService cityService;

    /**
     * Method is responsible for listing all the cities with pagination option
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    @CrossOrigin
    public ResponseEntity<CityResponseModel> findAllCities(@RequestParam(defaultValue = "0") int pageNumber,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(cityService.findAllCities(pageNumber, pageSize), HttpStatus.OK);

    }

    /**
     * Method is responsible for finding the city by its name
     *
     * @param name
     * @return
     */
    @GetMapping()
    @CrossOrigin
    public ResponseEntity<CityModel> findCityByName(@RequestParam String name) {
        return new ResponseEntity<>(cityService.searchByName(name), HttpStatus.OK);

    }

    /**
     * Method is responsible for updating the city
     *
     * @param cityModel
     * @return
     */
    @PutMapping()
    @CrossOrigin
    public ResponseEntity updateCity(@RequestBody CityModel cityModel) {
        cityService.updateCity(cityModel);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
