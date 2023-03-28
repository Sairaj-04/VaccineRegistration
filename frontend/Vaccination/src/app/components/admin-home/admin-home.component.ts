import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  public totalUsers?: number;
  public totalVaccines?: number;
  public totalVaccineCenters?: number;

  public vaccineList: any[] = [];
  public vaccineCenterList: any[] = [];
  public users:any[] = [];

  constructor(
    private adminService: AdminService,
    private authService: AuthService,
    private router: Router,
    private alertService: AlertService
  ) {}



  ngOnInit(): void {
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
    
    this.getVaccineList()
    this.getAllcenters()
    this.getUsers()
  }

  getVaccineList(){
    this.adminService.getVaccineList().subscribe(res => {
      this.vaccineList = res as any[];
    });
  }

  getAllcenters(){
    this.adminService.getAllCenters().subscribe(res => {
      this.vaccineCenterList = res as any[];
    });
  }

  getUsers(){
    this.adminService.getAllUsers().subscribe(res => {
      this.users = res as any[];
    });
  }

}
