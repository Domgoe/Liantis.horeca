import {Component, OnInit} from '@angular/core';
import { Horeca} from "../../../model/Horeca";
import { HorecaService} from "../../../service/horeca.service";
import { HorecaPage} from "../../../model/HorecaPage";
import { PaginationService} from "../../../service/pagination.service";
import { MatDialog, MatDialogConfig} from "@angular/material";
import { RatingDialogComponent} from "../../layout/ratingdialog/rating-dialog";

@Component({
  selector: 'app-horeca-list',
  templateUrl: './horeca-list-server-side.component.html',
  styleUrls: ['./horeca-list-server-side.component.css']
})
export class HorecaListServerSideComponent implements OnInit {
  horeca: Horeca;
  branches: string[];
  winkelgebieden: string[];
  horecaPage: HorecaPage = new HorecaPage();

  title: String = "Horeca in Brugge";

  constructor(private horecaService: HorecaService,
              private paginationService: PaginationService,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.horeca = new Horeca(0,"", "", null, "", "", "", "", "", "", null, null);
    this.horecaPage.pageable.pageNumber = 0;
    this.getHorecaPage();
    this.getBranches();
    this.getWinkelgebieden();
   }

  newSearch() {
    this.ngOnInit();
  }

  private onSubmit(): void {
    this.horecaPage.pageable.pageNumber = 0;
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
      },
      error => {
        throw error;
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

  //open dialog for rating horecazaak
  public openDialog(horeca: Horeca): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '400px';
    dialogConfig.data = horeca;

    const dialogRef = this.dialog.open(RatingDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data =>{
        if (data != null) {
          horeca.rating = +data.rating;
          this.horecaService.saveRating(horeca).subscribe(
            // data => console.log(data),
            // error => console.log(error)
          );
        }

      }
    )
  }

}
