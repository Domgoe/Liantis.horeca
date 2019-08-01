export class Pageable {

  static readonly DEFAULT_PAGE_SIZE = 15;
  static readonly FIRST_PAGE_NUMBER = 0;

  pageSize: number;
  pageNumber: number;
  offset:number;
  unpaged:boolean;
  paged:boolean;

  constructor( ) {
    this.pageSize = Pageable.DEFAULT_PAGE_SIZE,
    this.pageNumber = Pageable.FIRST_PAGE_NUMBER
  }

}
