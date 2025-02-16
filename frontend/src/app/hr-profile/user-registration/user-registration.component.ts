import { Component, inject, signal, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { UserProfileService } from '../../services/user-profile.service'; // assuming your service is here
import { RegisterRequest } from './RegisterRequest'; // RegisterRequest interface
import { Subscription } from 'rxjs';
import { CommonService } from '../../services/common.service';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
@Component({
  selector: 'app-user-registration',
  imports: [
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatCardModule,
    RouterModule,
  ],
  templateUrl: './user-registration.component.html',
  styleUrl: './user-registration.component.scss',
})
export class UserRegistrationComponent implements OnInit {
  hide = signal(true);
  clickEvent(event: MouseEvent) {
    this.hide.set(!this.hide());
    event.stopPropagation();
  }
  registerForm: FormGroup = new FormGroup({});
  error: string = '';
  private userService = inject(UserProfileService);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);
  private commonService = inject(CommonService);
  ngOnInit(): void {
    this.registerForm = this.formBuilder.group(
      {
        name: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        mobile: [
          '',
          [Validators.required, Validators.pattern('^\\+?[0-9]{10,15}$')],
        ],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', Validators.required],
      },
      {
        validators: this.passwordsMatchValidator,
      }
    );

    this.commonService.changeTitle('Attrition Analizer - HR Registration');
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const registerRequest: RegisterRequest = this.registerForm.value;

      this.userService.registerUser(registerRequest).subscribe({
        next: (response) => {
          this.router.navigate(['/login']);
          console.log('Registration successful');
        },
        error: (error: any) => {
          this.error = 'User already exists with this email';
        },
      });
    } else {
      this.makeFormDirty(this.registerForm);
    }
  }

  makeFormDirty(form: FormGroup) {
    Object.keys(form.controls).forEach((key) => {
      form.controls[key].markAsDirty();
      form.controls[key].markAsTouched();
    });
  }

  private passwordsMatchValidator(
    group: FormGroup
  ): { [key: string]: boolean } | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { passwordsMismatch: true };
  }
}
