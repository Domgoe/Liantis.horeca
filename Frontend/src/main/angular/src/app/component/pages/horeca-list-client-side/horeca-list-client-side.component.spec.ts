import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HorecaListClientSideComponent } from './horeca-list-client-side.component';

describe('HorecaFindComponent', () => {
  let component: HorecaListClientSideComponent;
  let fixture: ComponentFixture<HorecaListClientSideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HorecaListClientSideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HorecaListClientSideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
