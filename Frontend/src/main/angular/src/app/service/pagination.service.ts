import { Injectable } from '@angular/core';
import {HorecaPage} from "../model/HorecaPage";
import {Pageable} from "../model/Pageable";

@Injectable({
  providedIn: 'root'
})
export class PaginationService {

  constructor() { }

  public getNextPage(horecaPage: HorecaPage): Pageable {
      if(!horecaPage.last) {
        horecaPage.pageable.pageNumber = horecaPage.pageable.pageNumber + 1;
      }

      return horecaPage.pageable;
  }

  public getPreviousPage(horecaPage: HorecaPage): Pageable {
      if(!horecaPage.first) {
        horecaPage.pageable.pageNumber = horecaPage.pageable.pageNumber -1;
      }

      return horecaPage.pageable;
  }

  public getFirstPage(horecaPage: HorecaPage): Pageable {
    horecaPage.pageable.pageNumber = Pageable.FIRST_PAGE_NUMBER;
    return horecaPage.pageable;
  }

  public getLastPage(horecaPage: HorecaPage): Pageable {
    horecaPage.pageable.pageNumber = horecaPage.totalPages-1;
    return horecaPage.pageable;
  }

}
