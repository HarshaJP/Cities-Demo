package com.hpu.city.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * Class represents the Response instance for list call, which is used for communication over the rest layer
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityResponseModel {
    List<CityModel> cityModelList;
    PaginationModel paginationModel;
}
