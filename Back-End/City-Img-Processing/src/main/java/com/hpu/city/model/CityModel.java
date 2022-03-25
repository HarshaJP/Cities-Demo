package com.hpu.city.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Class represents the CityModel which is used for communication over the rest layer
 */
public class CityModel {
    private int id;

    private String name;

    private String imageUrl;

}
