import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HorecaService} from "../../../service/horeca.service";
import {Horeca} from "../../../model/Horeca";
import {HorecaPage} from "../../../model/HorecaPage";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private horecaService: HorecaService) { }

  private horeca: Horeca;
  private serverSideActive: boolean;
  private horecaPage: HorecaPage = new HorecaPage();

  ngOnInit() {
    this.horeca = new Horeca(0,"", "", null, "", "", "", "", "", "", null, null);
    this.serverSideActive = true;
    this.horecaPage = new HorecaPage();
  }

  createError() {
      throw new Error("Error vanuit de headercomponent");
  }

  onSubmit() {
    if (this.serverSideActive) {
      this.horecaService.getHorecaPage(this.horecaPage.pageable, this.horeca);
    } else {
      this.horecaService.getAllBy(this.horeca);
    }

    this.horeca.naam = "";
  }

  changeServerSideToInactive(){
    this.serverSideActive = false;
  }

  changeServerSideToActive() {
    this.serverSideActive = true;
  }


}
