import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HorecaListComponent } from './horeca-list.component';

describe('HorecaListComponent', () => {
  let component: HorecaListComponent;
  let fixture: ComponentFixture<HorecaListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HorecaListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HorecaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
