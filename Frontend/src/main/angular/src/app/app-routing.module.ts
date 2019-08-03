import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HorecaListServerSideComponent} from "./component/pages/horeca-list-server-side/horeca-list-server-side.component";
import {HorecaListClientSideComponent} from "./component/pages/horeca-list-client-side/horeca-list-client-side.component";
import {ErrorComponent} from "./component/pages/error/error.component";

const routes: Routes = [
  { path: '', component: HorecaListServerSideComponent},
  { path: 'home', redirectTo: ''},
  { path: 'horeca', component:HorecaListClientSideComponent},
  { path: 'error', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
