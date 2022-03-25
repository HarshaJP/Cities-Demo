package com.hpu.city.service.impl;

import com.hpu.city.entity.City;
import com.hpu.city.model.CityModel;
import com.hpu.city.model.CityResponseModel;
import com.hpu.city.model.PaginationModel;
import com.hpu.city.repository.CityImagesRepository;
import com.hpu.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class is responsible for all service calls to the persistence layer
 */
@Service
public class CityServiceImpl implements CityService {

    CityImagesRepository cityImagesRepository;

    @Autowired
    public CityServiceImpl(CityImagesRepository cityRepository) {
        this.cityImagesRepository = cityRepository;
    }

    @Override
    public CityResponseModel findAllCities(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<City> pagedResult = cityImagesRepository.findAll(paging);
        List<CityModel> cityModelList = pagedResult.stream().map(city -> buildCityModel(city)).collect(Collectors.toList());
        PaginationModel paginationModel = new PaginationModel(pagedResult.getNumber(), pagedResult.getSize(), pagedResult.getTotalPages(), pagedResult.getTotalElements());
        CityResponseModel cityResponseModel = new CityResponseModel(cityModelList, paginationModel);

        return cityResponseModel;
    }

    @Override
    public CityModel updateCity(CityModel cityModel) {
        City res = cityImagesRepository.save(this.buildCity(cityModel));
        return this.buildCityModel(res);
    }

    @Override
    public CityModel searchByName(String name) {
        return this.buildCityModel(cityImagesRepository.findCityByName(name));
    }

    /**
     * Method will build the city model from its database representation
     * @param city
     * @return
     */
    private CityModel buildCityModel(City city) {
        CityModel cityModel = new CityModel();
        cityModel.setId(city.getId());
        cityModel.setImageUrl(city.getImageUrl());
        cityModel.setName(city.getName());
        return cityModel;
    }

    /**
     * Method will build the database city instance from its Rest layer representation
     * @param cityModel
     * @return
     */
    private City buildCity(CityModel cityModel) {
        City city = new City();
        city.setId(cityModel.getId());
        city.setImageUrl(cityModel.getImageUrl());
        city.setName(cityModel.getName());
        return city;
    }
}
