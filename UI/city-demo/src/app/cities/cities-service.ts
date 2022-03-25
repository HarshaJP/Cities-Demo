import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CityModel } from './city-model';

@Injectable({
  providedIn: 'root'
})
export class CitiesService {

  baseUrl: string = "http://localhost:8180/cities";

  constructor(private httpClient: HttpClient) {

  }

  /**
   * Method will list the cities according to the pagination parameter
   * @param pageNumber 
   * @param pageSize 
   * @returns 
   */
  listCities(pageNumber?: number, pageSize?: number) {
    let listUrl = this.baseUrl + '/list';
    if (pageNumber !== undefined && pageSize != undefined) {
      listUrl = listUrl.concat('?pageNumber=').concat(pageNumber.toString()).concat('&pageSize=').concat(pageSize.toString());
    }
    return this.httpClient.get(listUrl);

  }

  /**
   * Method will search city by its name
   * @param name
   * @returns 
   */
  searchCityByName(name: string) {
    return this.httpClient.get(this.baseUrl + '?name=' + name);
  }


  /**
   * Method will update the city 
   * @param cityModel 
   * @returns 
   */
  updateCity(cityModel: CityModel) {
    return this.httpClient.put(this.baseUrl, cityModel);
  }

}
