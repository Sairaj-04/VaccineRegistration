import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import { VaccinationApiService } from 'src/app/services/vaccination-api.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-vaccine-list-list',
  templateUrl: './admin-vaccine-list.component.html',
  styleUrls: ['./admin-vaccine-list.component.css']
})
export class AdminVaccineListComponent {
  public bookings: any[] = [];
  public vaccineList: any[] = [];

  constructor(
    private vaccinationApiService: VaccinationApiService,
    private alert: AlertService,
    private adminService: AdminService,
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
    this.getVaccineList();
  }

  getVaccineList(){
    this.adminService.getVaccineList().subscribe(res => {
      this.vaccineList = res as any[];
    });
  }

  // Change
  availableVaccine(vcId: number) {
    this.vaccinationApiService.availableVaccine(vcId, () => {
      this.getVaccineList();
    });
  }

  // Change
  unavailableVaccine(vcId: number) {
    this.vaccinationApiService.unavailableVaccine(vcId, () => {
      this.getVaccineList();
    });
  }
}
