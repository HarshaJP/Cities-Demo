package com.hpu.city;

import com.hpu.city.entity.City;
import com.hpu.city.repository.CityImagesRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class is responsible for loading initial data into memory
 */
@Component
public class DataLoader implements ApplicationRunner {

    private CityImagesRepository cityImagesRepository;

    @Autowired
    public DataLoader(CityImagesRepository cityImagesRepository) {
        this.cityImagesRepository = cityImagesRepository;
    }

    public void run(ApplicationArguments args) {
        Map<String, City> cityMap = new HashMap<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/cities.csv")).withSkipLines(1).build()) {
            String[] lineInArray;

            while ((lineInArray = reader.readNext()) != null) {
                cityMap.put(lineInArray[1], new City(Integer.parseInt(lineInArray[0]), lineInArray[1], lineInArray[2]));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cityImagesRepository.saveAll(cityMap.values());
    }
}
