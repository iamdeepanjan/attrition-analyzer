import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotifiedEmployeeDialogComponent } from './notified-employee-dialog.component';

describe('NotifiedEmployeeDialogComponent', () => {
  let component: NotifiedEmployeeDialogComponent;
  let fixture: ComponentFixture<NotifiedEmployeeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotifiedEmployeeDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotifiedEmployeeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
