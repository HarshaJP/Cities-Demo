package com.hpu.city.controller;


import com.google.gson.Gson;
import com.hpu.city.ApplicationConstants;
import com.hpu.city.model.CityModel;
import com.hpu.city.model.CityResponseModel;
import com.hpu.city.model.PaginationModel;
import com.hpu.city.service.CityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(CityImagesController.class)
public class CityImagesControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CityService cityService;


    private final String citiURI = "/cities";


    @Test
    public void findAllCities() throws Exception {


        Mockito.when(cityService.findAllCities(0,1)).thenReturn(this.getCityResponse());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(this.citiURI +"/list").accept(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(this.getCityResponse())).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());

        Assert.assertNotNull(response.getContentAsString());

    }

    @Test
    public void findAllCitiesWithException() throws Exception {

        Mockito.doThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR)).when(cityService).findAllCities(0,1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(this.citiURI +"/list?pageNumber=0&pageSize=1").accept(MediaType.APPLICATION_JSON)
                .content("").contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

        Assert.assertEquals(ApplicationConstants.SERVER_ERROR_MSG, response.getContentAsString());
    }

    @Test
    public void findCityByName() throws Exception {


        String searchUrl = this.citiURI + "?name=Tokyo";

        Mockito.when(cityService.searchByName("Tokyo")).thenReturn(this.getCityResponse().getCityModelList().get(0));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(searchUrl).accept(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(this.getCityResponse())).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());

        Assert.assertNotNull(response.getContentAsString());

    }

    @Test
    public void findCityByNameWithException() throws Exception {

        String searchUrl = this.citiURI + "?name=Tokyo";

        Mockito.doThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR)).when(cityService).searchByName("Tokyo");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(searchUrl).accept(MediaType.APPLICATION_JSON)
                .content("").contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

        Assert.assertEquals(ApplicationConstants.SERVER_ERROR_MSG, response.getContentAsString());

    }


    @Test
    public void updateCity() throws Exception {

        Mockito.when(cityService.updateCity(Mockito.any(CityModel.class))).thenReturn(this.getCityResponse().getCityModelList().get(0));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(citiURI).accept(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(this.getCityResponse().getCityModelList().get(0))).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

        Assert.assertNotNull(response.getContentAsString());
    }

    @Test
    public void updateCityWithException() throws Exception {

        Mockito.when(cityService.updateCity(Mockito.any(CityModel.class))).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(citiURI).accept(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(this.getCityResponse().getCityModelList().get(0))).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

        Assert.assertEquals(ApplicationConstants.SERVER_ERROR_MSG, response.getContentAsString());
    }


    private CityResponseModel getCityResponse() {
        CityResponseModel cityResponseModel = new CityResponseModel();
        CityModel cityModel1 = new CityModel();
        cityModel1.setId(1);
        cityModel1.setName("Tokyo");
        cityModel1.setImageUrl("testUrl-Tokyo");
        CityModel cityModel2 = new CityModel();
        cityModel2.setId(2);
        cityModel2.setName("Delhi");
        cityModel2.setImageUrl("testUrl-Delhi");
        cityResponseModel.setCityModelList(Arrays.asList(cityModel1, cityModel2));

        PaginationModel paginationModel = new PaginationModel();
        paginationModel.setPageNumber(0);
        paginationModel.setPageSize(1);
        paginationModel.setTotalPages(2);
        paginationModel.setTotalRecords(2);
        cityResponseModel.setPaginationModel(paginationModel);

        return cityResponseModel;
    }


}

