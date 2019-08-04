import {ErrorHandler, Injectable, Injector} from '@angular/core';
import {Router, NavigationExtras} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class GlobalErrorHandlerService implements ErrorHandler{

  constructor(private injector: Injector) { }

  handleError(error: any): void {
    let router = this.injector.get(Router);
    let receivedError: Error;

    console.log(error);

    if(error instanceof Error){
      receivedError = error as Error;
    }

    const navigationExtras: NavigationExtras = {
      state: {
       message: receivedError.message
      }
    };

    router.navigate(['error'], navigationExtras);
  }
}


