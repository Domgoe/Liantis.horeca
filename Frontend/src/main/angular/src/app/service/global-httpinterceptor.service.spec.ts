import { TestBed } from '@angular/core/testing';

import { GlobalHttpinterceptorService } from './global-httpinterceptor.service';

describe('GlobalHttpinterceptorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GlobalHttpinterceptorService = TestBed.get(GlobalHttpinterceptorService);
    expect(service).toBeTruthy();
  });
});
