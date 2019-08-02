import { Component, OnInit } from '@angular/core';
import {Horeca} from "../../../model/Horeca";
import {HorecaService} from "../../../service/horeca.service";
import {HorecaPage} from "../../../model/HorecaPage";
import {PaginationService} from "../../../service/pagination.service";
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-horeca-list',
  templateUrl: './horeca-list.component.html',
  styleUrls: ['./horeca-list.component.css']
})
export class HorecaListComponent implements OnInit {
  horeca: Horeca;
  branches: string[];
  winkelgebieden: string[];
  horecaPage: HorecaPage = new HorecaPage();

  title: String = "Horeca in Brugge";

  constructor(private horecaService: HorecaService, private paginationService: PaginationService) { }

  ngOnInit() {
    this.horeca = new Horeca(0,"", "", null, "", "", "", "", "", "", null);
    this.getHorecaPage();
    this.getBranches();
    this.getWinkelgebieden();
  }

  newSearch() {
    this.ngOnInit();
  }

  private onSubmit(): void {
    this.horecaService.getHorecaPage(this.horecaPage.pageable, this.horeca).subscribe(
      data => {
        this.horecaPage = data
      },
      error =>{
        throw error;
      }
    )
  }

  //Pagina horecazaken
  private getHorecaPage(): void {
    this.horecaService.getHorecaPage(this.horecaPage.pageable, this.horeca).subscribe(
      data => {
        this.horecaPage = data
      }
    )
  }

  //Get branches for dropdown
  private getBranches(): void {
    this.horecaService.getAllBranches().subscribe(
      data => {
        this.branches = data.sort();
      },
      error => {
        throw error;
      }
    )
  }

  //Get winkelgebieden for dropdown
  private getWinkelgebieden() : void {
    this.horecaService.getAllWinkelgebieden().subscribe(
      data => {
        this.winkelgebieden = data.sort();
      },
      error => {
        throw error;
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
