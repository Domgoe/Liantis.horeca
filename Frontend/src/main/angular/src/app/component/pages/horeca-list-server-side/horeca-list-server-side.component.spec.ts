import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HorecaListServerSideComponent } from './horeca-list-server-side.component';

describe('HorecaListComponent', () => {
  let component: HorecaListServerSideComponent;
  let fixture: ComponentFixture<HorecaListServerSideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HorecaListServerSideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HorecaListServerSideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
