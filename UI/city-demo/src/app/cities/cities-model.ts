import { CityModel } from "./city-model";
import { PaginationModel } from "./pagination.model";

export class CitiesModel {

    cityModelList: CityModel[];
    paginationModel: PaginationModel;

    constructor(cityModelList: CityModel[], pagination: PaginationModel) {
        this.cityModelList = cityModelList;
        this.paginationModel = pagination;
    }


}