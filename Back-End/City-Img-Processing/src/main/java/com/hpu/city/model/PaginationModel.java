package com.hpu.city.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class represents the PaginationModel which is used for communication over the rest layer
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationModel {
    int pageNumber;
    int pageSize;
    int totalPages;
    long totalRecords;
}
