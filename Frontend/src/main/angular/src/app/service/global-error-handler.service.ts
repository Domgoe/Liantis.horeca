import {ErrorHandler, Injectable, Injector} from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class GlobalErrorHandlerService implements ErrorHandler{

  constructor(private injector: Injector) { }

  handleError(error: any): void {
    let router = this.injector.get(Router)
    console.log(error);

    router.navigate(['error']);
    throw error;

  }
}


