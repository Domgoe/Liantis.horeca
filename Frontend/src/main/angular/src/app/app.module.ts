import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './component/layout/header/header.component';
import { FooterComponent } from './component/layout/footer/footer.component';
import { HorecaListComponent } from './component/pages/horeca-list/horeca-list.component';
import { HorecaFindComponent, RatingDialogComponent } from './component/pages/horeca-find/horeca-find.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { PaginationComponent } from './component/layout/pagination/pagination.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import {MatFormFieldModule, MatSelectModule} from "@angular/material";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HorecaListComponent,
    HorecaFindComponent,
    PaginationComponent,
    RatingDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  entryComponents:[
    RatingDialogComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
