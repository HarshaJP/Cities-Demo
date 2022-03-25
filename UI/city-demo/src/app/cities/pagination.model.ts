export class PaginationModel {
    pageNumber : number;
    pageSize : number;
    totalPages : number;
    totalRecords : number;

    constructor(pageNumber: number, pageSize: number, totalPages: number, totalRecords: number) {
        this.pageNumber=pageNumber;
        this.pageSize=pageSize;
        this.totalPages=totalPages;
        this.totalRecords=totalRecords;
    }


}