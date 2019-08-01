import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {HorecaPage} from "../../../model/HorecaPage";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {

  @Input()
  horecaPage: HorecaPage;

  @Output()
  nextPageEvent = new EventEmitter();

  @Output()
  previousPageEvent = new EventEmitter();

  @Output()
  firstPageEvent = new EventEmitter();

  @Output()
  lastPageEvent = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  nextPage(): void {
    this.nextPageEvent.emit(null);
  }

  previousPage(): void {
    this.previousPageEvent.emit(null);
  }

  firstPage(): void {
    this.firstPageEvent.emit(null);
  }

  lastPage(): void {
    this.lastPageEvent.emit(null);
  }


}
