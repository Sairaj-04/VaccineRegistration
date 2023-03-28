import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import { VaccinationApiService } from 'src/app/services/vaccination-api.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-vaccine',
  templateUrl: './add-vaccine.component.html',
  styleUrls: ['./add-vaccine.component.css']
})
export class AddVaccineComponent {

  constructor(
    private vaccinationApiService: VaccinationApiService,
    private alert: AlertService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    const token = sessionStorage.getItem('token');
    // if (token != null) {
    //   if (this.authService.tokenExpired(token)) {
    //     sessionStorage.clear();
    //     this.router.navigateByUrl("/login");
    //   }
    // }
    if (token != null) {
      if (this.authService.tokenExpired(token)) {
        Swal.fire({
          icon:'error',
          text:"Session Expired Please Login again",
        }).then(()=>{sessionStorage.clear()
        });
        this.router.navigateByUrl("/login");
      } 
    }
  }

  onSubmit(ngForm: NgForm) {
    // if (ngForm.form.invalid) {
    //   this.alert.error("Please fill required elements");
    //   return;
    // }
    const vaccineDetails = { ...ngForm.form.value, userId: 0 };
    this.vaccinationApiService.addVaccine(vaccineDetails);
    ngForm.resetForm();
  }
}
