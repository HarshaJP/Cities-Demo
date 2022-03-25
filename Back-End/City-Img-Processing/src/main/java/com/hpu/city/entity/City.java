package com.hpu.city.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class represents the persistence instance of the City object
 */
@Entity
@Table(name = "CITY")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {

    @Id
    @Column(name="id", nullable=false)
    private int id;

    @Column(name="name", length=100, nullable=false, unique=true)
    private String name;

    @Column(name="imageUrl", length=2000)
    private String imageUrl;

}
