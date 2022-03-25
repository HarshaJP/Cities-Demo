import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CitiesListComponent } from './cities-list/cities-list.component';
import { CitiesService } from './cities-service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {TableModule} from 'primeng/table';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { BrowserModule } from '@angular/platform-browser';



@NgModule({
  declarations: [
    CitiesListComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    BrowserModule,
    TableModule,
    BrowserAnimationsModule,
    MatTableModule, 
    MatPaginatorModule, 
    MatProgressBarModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers : [CitiesService],
  exports: [ CitiesListComponent ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class CitiesModule { }
