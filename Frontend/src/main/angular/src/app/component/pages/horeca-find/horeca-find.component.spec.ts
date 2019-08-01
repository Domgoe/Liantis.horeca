import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HorecaFindComponent } from './horeca-find.component';

describe('HorecaFindComponent', () => {
  let component: HorecaFindComponent;
  let fixture: ComponentFixture<HorecaFindComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HorecaFindComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HorecaFindComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
