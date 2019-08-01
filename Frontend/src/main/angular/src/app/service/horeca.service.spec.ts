import { TestBed } from '@angular/core/testing';

import { HorecaService } from './horeca.service';

describe('HorecaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HorecaService = TestBed.get(HorecaService);
    expect(service).toBeTruthy();
  });
});
