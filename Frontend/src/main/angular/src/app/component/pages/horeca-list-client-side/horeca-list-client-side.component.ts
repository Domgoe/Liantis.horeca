import { Component, OnInit, ViewChild} from '@angular/core';
import { Horeca} from "../../../model/Horeca";
import { HorecaService} from "../../../service/horeca.service";
import { MatTableDataSource} from '@angular/material/table';
import { MatDialogConfig, MatPaginator} from "@angular/material";
import { MatDialog } from '@angular/material/dialog';
import { RatingDialogComponent} from "../../layout/ratingdialog/rating-dialog";

@Component({
  selector: 'app-horeca-find',
  templateUrl: './horeca-list-client-side.component.html',
  styleUrls: ['./horeca-list-client-side.component.css']
})
export class HorecaListClientSideComponent implements OnInit {

  title: String = "Horeca in Brugge";
  branches: string[];
  winkelgebieden: string[];
  horeca: Horeca;

  displayedColumns: string[] = ['naam', 'straat', 'branche', 'winkelgebied', 'rating', 'update'];
  dataSource: MatTableDataSource<Horeca>;
  dataSourceEmpty: boolean = false;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private horecaService: HorecaService, public dialog: MatDialog) { }

  ngOnInit() {
    this.horeca = new Horeca(0,"", "", null, "", "", "", "", "", "", null);
    this.dataSourceEmpty = false;
    this.getAll();
    this.getBranches();
    this.getWinkelgebieden();
  }

  newSearch() {
    this.ngOnInit();
  }

  //open dialog for rating horecazaak
  openDialog(horeca: Horeca): void {
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

  //Alle horecazaken
  private getAll(): void {
    this.horecaService.getAll().subscribe(
      data => {
        this.dataSource = new MatTableDataSource<Horeca>(data);
        this.dataSource.paginator = this.paginator;
      },
        error => {
        throw error;
      }
    )
  }

  //Get by naam, branche en winkelgebied
  private onSubmit(): void {
    this.horecaService.getAllBy(this.horeca).subscribe(
      data => {
        this.dataSource = new MatTableDataSource<Horeca>(data);
        this.dataSource.paginator = this.paginator;
        this.dataSourceEmpty = this.dataSource.data.length === 0 ? true : false;
      },
      error =>{
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
}




