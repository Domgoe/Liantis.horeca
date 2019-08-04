import { Injectable } from '@angular/core';
import { environment} from "../../environments/environment";
import { HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import { Horeca} from "../model/Horeca";
import { HorecaPage} from "../model/HorecaPage";
import { Pageable} from "../model/Pageable";

const API_URL = environment.apiUrl;
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class HorecaService {

  private horecaUrl: string = API_URL + "/horeca";

  horecaResults = new BehaviorSubject<Horeca[]>(null);

  getResults(){
    return this.horecaResults.asObservable();
  }

  constructor(private httpClient : HttpClient) { }

  public getAll() {
     this.httpClient.get<Horeca[]>(this.horecaUrl + '/all', httpOptions).subscribe(
       data => {
          this.horecaResults.next(data);
       }
     );
  }

  public getAllBy(horeca: Horeca) {
    let params =new HttpParams()
      .set('naam', horeca.naam)
      .set('branche', horeca.branche)
      .set('winkelgebied', horeca.winkelgebied);
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    this.httpClient.get<Horeca[]>(this.horecaUrl + '/getBy/', {headers: headers, params: params} ).subscribe(
      data => {
        console.log("From horecaservice:" + data);
        this.horecaResults.next(data);
       }
    );

  }

  // public getAll(): Observable<Horeca[]> {
  //   return this.httpClient.get<Horeca[]>(this.horecaUrl + '/all', httpOptions);
  // }

  public getHorecaPage(pageable: Pageable, horeca: Horeca): Observable<HorecaPage> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    let params = new HttpParams()
      .set('naam', horeca.naam)
      .set('branche', horeca.branche)
      .set('winkelgebied', horeca.winkelgebied);

    let url = this.horecaUrl + "/getPage?page=" + pageable.pageNumber + "&size=" + pageable.pageSize;
    return this.httpClient.get<HorecaPage>(url, {headers: headers, params: params});
  }

  // public getAllBy(horeca: Horeca): Observable<Horeca[]> {
  //   let params =new HttpParams()
  //     .set('naam', horeca.naam)
  //     .set('branche', horeca.branche)
  //     .set('winkelgebied', horeca.winkelgebied);
  //   let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  //   return this.httpClient.get<Horeca[]>(this.horecaUrl + '/getBy/', {headers: headers, params: params} );
  // }

  public getAllBranches() : Observable<string[]> {
    return this.httpClient.get<string[]>(this.horecaUrl + '/branches', httpOptions);
  }

  public getAllWinkelgebieden(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.horecaUrl + '/winkelgebieden', httpOptions);
  }

  public saveRating(horeca: Horeca): Observable<Horeca> {
     // console.log("In the service");
     // console.log(horeca);
     let params =new HttpParams()
      .set('id', horeca.id.toString())
      .set('rating', horeca.rating.toString())
     let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
     return this.httpClient.put<Horeca>(this.horecaUrl + "/saveRating/" + horeca.id, null, {headers: headers, params: params} );
  }
}
