import { Component, OnInit } from '@angular/core';
import {error} from "util";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  createError() {
      throw new Error("Error vanuit de headercomponent");
  }

}
