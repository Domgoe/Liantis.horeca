import {Horeca} from "./Horeca";
import {Pageable} from "./Pageable";

export class HorecaPage {
  content: Horeca[];
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  first: boolean;
  numberOfElements: number;
  number: number;

  constructor(){
    this.pageable = new Pageable();
  }
}
