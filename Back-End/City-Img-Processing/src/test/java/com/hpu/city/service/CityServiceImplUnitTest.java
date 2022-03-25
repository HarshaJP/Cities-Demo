package com.hpu.city.service;

import com.hpu.city.entity.City;
import com.hpu.city.model.CityModel;
import com.hpu.city.model.CityResponseModel;
import com.hpu.city.repository.CityImagesRepository;
import com.hpu.city.service.impl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class CityServiceImplUnitTest {

    final CityImagesRepository cityImagesRepository = Mockito.mock(CityImagesRepository.class);
     City city;
    CityModel cityRequestModel;

    @BeforeEach
    void setUp() {
        city=new City();
        city.setId(1);
        city.setName("Tokyo");
        city.setImageUrl("testUrl-Tokyo");

        cityRequestModel=new CityModel();
        cityRequestModel.setId(1);
        cityRequestModel.setName("Tokyo");
        cityRequestModel.setImageUrl("testUrl-Tokyo");
    }

    @Test
    void findAllCities() {
        Page<City> pagedResult = Mockito.mock(Page.class);

        when(cityImagesRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pagedResult);
        final CityServiceImpl cityService = new CityServiceImpl(cityImagesRepository);

        final CityResponseModel cityResponseModel = cityService.findAllCities(0, 1);
        // assertion
        assertThat(cityResponseModel.getCityModelList()).isNotNull();
        assertThat(cityResponseModel.getPaginationModel()).isNotNull();
    }

    @Test
    void findAllCitiesForException() {
        Page<City> pagedResult = Mockito.mock(Page.class);

        when(cityImagesRepository.findAll(Mockito.any(Pageable.class))).thenThrow(new DataAccessException("test") {
        });
        final CityServiceImpl cityService = new CityServiceImpl(cityImagesRepository);
        try {
            final CityResponseModel cityResponseModel = cityService.findAllCities(0, 1);

        } catch (DataAccessException e) {
            assertThat(e.getMessage()).isEqualTo("test");
        }
    }

    @Test
    void updateCity() {

        when(cityImagesRepository.save(Mockito.any(City.class))).thenReturn(city);
        final CityServiceImpl cityService = new CityServiceImpl(cityImagesRepository);

        final CityModel cityResponseModel = cityService.updateCity(cityRequestModel);
        // assertion
        assertThat(cityResponseModel.getId()).isEqualTo(cityRequestModel.getId());
        assertThat(cityResponseModel.getName()).isEqualTo(cityRequestModel.getName());
        assertThat(cityResponseModel.getImageUrl()).isEqualTo(cityRequestModel.getImageUrl());
    }

    @Test
    void updateCityWithException() {

        when(cityImagesRepository.save(Mockito.any(City.class))).thenThrow(new DataAccessException("test") {
        });
        final CityServiceImpl cityService = new CityServiceImpl(cityImagesRepository);
        CityModel cityResponseModel = null;
        try {
            cityResponseModel = cityService.updateCity(cityRequestModel);

        } catch (DataAccessException e) {
            assertThat(e.getMessage()).isEqualTo("test");

        }

    }

    @Test
    void searchByName() {
        when(cityImagesRepository.findCityByName(Mockito.any(String.class))).thenReturn(city);
        final CityServiceImpl cityService = new CityServiceImpl(cityImagesRepository);
        CityModel cityResponseModel = cityService.searchByName("Tokyo");

        assertThat(cityResponseModel.getId()).isEqualTo(cityRequestModel.getId());
        assertThat(cityResponseModel.getName()).isEqualTo(cityRequestModel.getName());
        assertThat(cityResponseModel.getImageUrl()).isEqualTo(cityRequestModel.getImageUrl());
    }

    @Test
    void searchByNameWithException() {
        when(cityImagesRepository.findCityByName(Mockito.any(String.class))).thenThrow(new DataAccessException("test") {
        });
        final CityServiceImpl cityService = new CityServiceImpl(cityImagesRepository);
        CityModel cityResponseModel = null;
        try {
            cityResponseModel = cityService.searchByName("Tokyo");

        } catch (DataAccessException e) {
            assertThat(e.getMessage()).isEqualTo("test");

        }

    }



}
