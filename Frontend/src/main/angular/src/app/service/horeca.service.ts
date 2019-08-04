import { Injectable } from '@angular/core';
import { environment} from "../../environments/environment";
import { HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import { BehaviorSubject, Observable} from "rxjs";
import { Horeca} from "../model/Horeca";
import { HorecaPage} from "../model/HorecaPage";
import { Pageable} from "../model/Pageable";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class HorecaService {

  private horecaUrl: string = API_URL + "/horeca";
  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  private horecaResults = new BehaviorSubject<Horeca[]>(null);
  private horecaResultsPage = new BehaviorSubject<HorecaPage>(null);

  getResults(){
    return this.horecaResults.asObservable();
  }

  getPageResults() {
    return this.horecaResultsPage.asObservable();
  }

  constructor(private httpClient : HttpClient) { }

  public getAll() {
     this.httpClient.get<Horeca[]>(this.horecaUrl + '/all', {headers: this.headers}).subscribe(
       data => {
          this.horecaResults.next(data);
       },
       error => {
         throw error;
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
        //console.log("From horecaservice:" + data);
        this.horecaResults.next(data);
       },
      error => {
        throw error;
      }
    );
  }

  public getHorecaPage(pageable: Pageable, horeca: Horeca) {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    let params = new HttpParams()
      .set('naam', horeca.naam)
      .set('branche', horeca.branche)
      .set('winkelgebied', horeca.winkelgebied);

    let url = this.horecaUrl + "/getPage?page=" + pageable.pageNumber + "&size=" + pageable.pageSize;
    return this.httpClient.get<HorecaPage>(url, {headers: headers, params: params}).subscribe(
      data => {
        this.horecaResultsPage.next(data);
      },
      error => {
        throw error;
      }
    );
  }

  /** Methods for working without BehaviorSubject as Observable
     public getAll(): Observable<Horeca[]> {
       return this.httpClient.get<Horeca[]>(this.horecaUrl + '/all', httpOptions);
     }

     public getAllBy(horeca: Horeca): Observable<Horeca[]> {
       let params =new HttpParams()
         .set('naam', horeca.naam)
         .set('branche', horeca.branche)
         .set('winkelgebied', horeca.winkelgebied);
       let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
         return this.httpClient.get<Horeca[]>(this.horecaUrl + '/getBy/', {headers: headers, params: params} );
     }

   public getHorecaPage(pageable: Pageable, horeca: Horeca): Observable<HorecaPage> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    let params = new HttpParams()
      .set('naam', horeca.naam)
      .set('branche', horeca.branche)
      .set('winkelgebied', horeca.winkelgebied);

    let url = this.horecaUrl + "/getPage?page=" + pageable.pageNumber + "&size=" + pageable.pageSize;
    return this.httpClient.get<HorecaPage>(url, {headers: headers, params: params});
  }
  */

  public getAllBranches() : Observable<string[]> {
    return this.httpClient.get<string[]>(this.horecaUrl + '/branches', {headers: this.headers});
  }

  public getAllWinkelgebieden(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.horecaUrl + '/winkelgebieden', {headers: this.headers});
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
