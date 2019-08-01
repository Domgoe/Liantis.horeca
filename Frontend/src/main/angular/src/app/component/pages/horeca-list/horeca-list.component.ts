import { Component, OnInit } from '@angular/core';
import {Horeca} from "../../../model/Horeca";
import {HorecaService} from "../../../service/horeca.service";
import {HorecaPage} from "../../../model/HorecaPage";
import {PaginationService} from "../../../service/pagination.service";

@Component({
  selector: 'app-horeca-list',
  templateUrl: './horeca-list.component.html',
  styleUrls: ['./horeca-list.component.css']
})
export class HorecaListComponent implements OnInit {
  horecalist: Horeca[];
  horecaPage: HorecaPage = new HorecaPage();

  title: String = "Alle horeca zaken in Brugge";

  constructor(private horecaService: HorecaService, private paginationService: PaginationService) { }

  ngOnInit() {
    //this.getHorecaList();
    this.getHorecaPage();
  }

  //Alle horecazaken
  private getHorecaList(): void {
    this.horecaService.getAll().subscribe(
      data => {
        this.horecalist = data;
      }
    )
  }

  //Pagina horecazaken
  private getHorecaPage(): void {
    this.horecaService.getHorecaPage(this.horecaPage.pageable).subscribe(
      data => {
        this.horecaPage = data
      }
    )
  }

  public getFirstPage() : void {
    this.horecaPage.pageable = this.paginationService.getFirstPage(this.horecaPage);
    this.getHorecaPage();
  }

  public getNextPage() : void {
    this.horecaPage.pageable = this.paginationService.getNextPage(this.horecaPage);
    this.getHorecaPage();
  }

  public getPreviousPage() : void {
    this.horecaPage.pageable = this.paginationService.getPreviousPage(this.horecaPage);
    this.getHorecaPage();
  }

  public getLastPage(): void {
    this.horecaPage.pageable = this.paginationService.getLastPage(this.horecaPage);
    this.getHorecaPage();
  }

}
