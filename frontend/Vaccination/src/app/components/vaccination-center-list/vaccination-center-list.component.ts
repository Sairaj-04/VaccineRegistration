import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import { VaccinationApiService } from 'src/app/services/vaccination-api.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-vaccination-center-list',
  templateUrl: './vaccination-center-list.component.html',
  styleUrls: ['./vaccination-center-list.component.css']
})
export class VaccinationCenterListComponent {
  public bookings: any[] = [];
  public vaccineCenter: any[] = [];

  constructor(
    private vaccinationApiService: VaccinationApiService,
    private alert: AlertService,
    private adminService: AdminService,
    private authService: AuthService,
    private router: Router

  ) { }

  ngOnInit() {
    const token = sessionStorage.getItem('token');
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

    this.getAllcenters();
  }

  getAllcenters(){
    this.adminService.getAllCenters().subscribe(res => {
      this.vaccineCenter = res as any[];
    });
  }

  // Change
  availableCenter(vcId: number) {
    this.vaccinationApiService.availableVaccineCenter(vcId, () => {
      this.getAllcenters();
    });
  }

  // Change
  unavailableCenter(vcId: number) {
    this.vaccinationApiService.unavailableVaccineCenter(vcId, () => {
      this.getAllcenters();
    });
  }

}
