import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Horeca} from "../model/Horeca";
import {HorecaPage} from "../model/HorecaPage";
import {Pageable} from "../model/Pageable";

const API_URL = environment.apiUrl;
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class HorecaService {

  private horecaUrl: string = API_URL + "/horeca";

  constructor(private httpClient : HttpClient) { }

  public getAll(): Observable<Horeca[]> {
    return this.httpClient.get<Horeca[]>(this.horecaUrl + '/all', httpOptions);
  }

  public getHorecaPage(pageable: Pageable): Observable<HorecaPage> {
    let url = this.horecaUrl + "/get?page=" + pageable.pageNumber + "&size=" + pageable.pageSize;
    return this.httpClient.get<HorecaPage>(url, httpOptions)
  }

  public getAllBy(horeca: Horeca): Observable<Horeca[]> {
    return this.httpClient.post<Horeca[]>(this.horecaUrl + '/find/', horeca, httpOptions);
  }

  public getAllBranches() : Observable<string[]> {
    return this.httpClient.get<string[]>(this.horecaUrl + '/branches', httpOptions);
  }

  public getAllWinkelgebieden(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.horecaUrl + '/winkelgebieden', httpOptions);
  }

  public saveAndUpdateRating(horeca: Horeca): Observable<Horeca> {
     // console.log("In the service");
     // console.log(horeca);
     return this.httpClient.post<Horeca>(this.horecaUrl + "/saveRating", horeca, httpOptions);
  }
}
