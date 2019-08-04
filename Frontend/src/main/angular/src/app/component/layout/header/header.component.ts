import { Component, OnInit } from '@angular/core';
import {HorecaService} from "../../../service/horeca.service";
import {Horeca} from "../../../model/Horeca";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private horecaService: HorecaService) { }

  horeca: Horeca;

  ngOnInit() {
    this.horeca = new Horeca(0,"", "", null, "", "", "", "", "", "", null, null);
  }

  createError() {
      throw new Error("Error vanuit de headercomponent");
  }

  onSubmit() {
    console.log("From the header:" + this.horeca);
    this.horecaService.getAllBy(this.horeca);
    this.horeca.naam = null;
  }

}
