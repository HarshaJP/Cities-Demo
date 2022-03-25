import { Component, OnInit } from '@angular/core';
import { CitiesModel } from '../cities-model';
import { CitiesService } from '../cities-service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { CityModel } from '../city-model';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { PaginationModel } from '../pagination.model';
@Component({
  selector: 'app-citi-list',
  templateUrl: './cities-list.component.html',
  styleUrls: ['./cities-list.component.css']
})
export class CitiesListComponent implements OnInit {

  citiesModel: CitiesModel | undefined;

  first = 0;

  rows = 10;

  totalRecords: number = 0;

  cols: any[] = [];

  isLoading: boolean = false;

  selectAll: boolean = false;

  displayedColumns: any[] = [];

  // Form
  cityForm: FormGroup;

  searchedCity: any;
  searchText: string = '';
  errorMsg: string = '';
  successMsg: string = '';


  totalRows = 0;
  pageSize = 5;
  currentPage = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  constructor(private citiesService: CitiesService, private fb: FormBuilder) {
    this.cityForm = this.fb.group({
      id: '',
      name: new FormControl('', Validators.required),
      imageUrl: new FormControl('', [Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?')])

    });
  }

  ngOnInit(): void {
    this.displayedColumns.push("id");
    this.displayedColumns.push("name");
    this.displayedColumns.push("imageUrl");
    this.displayedColumns.push("action");

    this.loadData();
  }


  loadData(pageNumber?: number, pageSize?: number) {
    this.citiesService.listCities(pageNumber, pageSize).subscribe((res: any) => {
      this.citiesModel = res;
    },
      error => {
        this.errorMsg = 'Error while saving the city details';
      },
      () => {
      });
  }

  pageChanged(event: PageEvent) {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadData(event.pageIndex, event.pageSize);
  }

  editCity(rowData: CityModel) {
    this.cityForm.setValue({
      id: rowData.id,
      name: rowData.name,
      imageUrl: rowData.imageUrl
    });
    let element: HTMLElement = document.getElementById('updatemodalbtn') as HTMLElement;
    element.click();
  }

  // form 
  initializeCityForm() {
    this.cityForm = this.fb.group({
      id: '',
      name: '',
      imageUrl: ''
    });
  }

  onSubmit() {
    let errorFields = '';
    this.citiesService.updateCity(this.cityForm.value).subscribe((res: any) => {
      this.successMsg = "City details for '" + this.cityForm.value.name + "' updated successfully.";
      this.initializeCityForm();
      this.loadData();
    },
      error => {
        this.errorMsg = 'Error while updating the city details';
        let element: HTMLElement = document.getElementById('updatemodalbtn') as HTMLElement;
        element.click();
      },
      () => {
        let element: HTMLElement = document.getElementById('updatemodalbtn') as HTMLElement;
        element.click();
      });
  }


  /**
  * Method is responsible for searching the city with given anme
  */
  searchCity() {
    this.clearErrorNotification();
    this.clearSuccessNotification();
    if (this.searchText) {
      this.citiesService.searchCityByName(this.searchText.trim()).subscribe((res: any) => {
        this.searchedCity = res;
        let cityModelList = new Array<CityModel>();
        cityModelList.push(res);
        this.citiesModel = undefined;
        this.citiesModel = new CitiesModel(cityModelList, new PaginationModel(1, 0, 0, 0));
        this.pageSizeOptions = [1];
      },
        error => {
          this.errorMsg = 'Error while fecting data for given city name: ' + this.searchText;
          this.searchedCity = null;
        },
        () => {
        });
    }
  }

  clearSearch() {
    if (this.searchText) {
      this.searchText = "";
      this.searchedCity = undefined;
      this.pageSizeOptions = [5, 10, 25, 100];
      this.loadData();
    }

  }

  /**
   * Clears error notification message
   */
  clearErrorNotification() {
    this.errorMsg = '';
  }

  /**
   * Clears success notification message
   */
  clearSuccessNotification() {
    this.successMsg = '';
  }

  /**
   * 
   * method is responsible for closing the create modal pop-up
   */
  modalClose() {
    this.initializeCityForm();
  }

}
