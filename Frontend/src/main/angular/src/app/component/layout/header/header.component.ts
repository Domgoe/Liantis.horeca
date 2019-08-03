import { Component, OnInit } from '@angular/core';
import {HorecaService} from "../../../service/horeca.service";
import {HorecaListServerSideComponent} from "../../pages/horeca-list-server-side/horeca-list-server-side.component";
import {Horeca} from "../../../model/Horeca";

@Component({
  providers: [HorecaListServerSideComponent],
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  horeca: Horeca = new Horeca(0,"", "", null, "", "", "", "", "", "", null);

  constructor(private service: HorecaService, private component: HorecaListServerSideComponent) { }

  ngOnInit() {

  }

  createError() {
      throw new Error("Error vanuit de headercomponent");
  }

  //TODO: Get initial data
  refreshHome(){
      this.service.getHorecaPage(this.component.horecaPage.pageable, this.horeca).subscribe(
        data =>{
          this.component.horecaPage = data;
        },
        error => {
          throw  error;
        }
      )
  }
}
