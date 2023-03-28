import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import { VaccinationApiService } from 'src/app/services/vaccination-api.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-vaccination-center',
  templateUrl: './add-vaccination-center.component.html',
  styleUrls: ['./add-vaccination-center.component.css']
})
export class AddVaccinationCenterComponent {

  constructor(private api: VaccinationApiService, private alert: AlertService, private authService: AuthService, private router: Router) { }

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
    const vaccinationCenterDetails: any = { ...ngForm.form.value };
    this.api.addVaccinationCenter(vaccinationCenterDetails);
    ngForm.resetForm();
  }

}
