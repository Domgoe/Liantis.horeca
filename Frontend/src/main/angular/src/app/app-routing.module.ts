import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HorecaListComponent} from "./component/pages/horeca-list/horeca-list.component";
import {HorecaFindComponent} from "./component/pages/horeca-find/horeca-find.component";

const routes: Routes = [
  { path: '', component: HorecaFindComponent},
  {path:'horeca', component:HorecaListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
