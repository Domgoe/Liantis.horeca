import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  private message: String;
  private navigation = this.router.getCurrentNavigation();
  private state = this.navigation.extras.state as {
    message: string,

  };

  constructor(private router: Router) { }

  ngOnInit() {
    this.message = "Foutboodschap: " + this.state.message;
    console.log(this.message);
  }

}
