import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { AlertService } from 'src/app/services/alert.service';
import { AppointmentService } from 'src/app/services/appointment.service';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent {
  public bookings: any[] = [];
  public appointmentList: any[] = [];

  constructor(
    private appointmentService: AppointmentService,
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
    let userId = sessionStorage.getItem('SESSION_USER_ID')
    this.getAppointments(userId);
  }

  getAppointments(id:any){
    this.appointmentService.getAllAppointments(id).subscribe(res => {
      this.appointmentList = res as any[];
    });
  }

  onDelete(Id: number) {
    this.appointmentService.deleteAppointment(Id, () => {
      this.ngOnInit();
     
    });
   
  }

  onUpdate(item:any){
    sessionStorage.setItem('slot',JSON.stringify(item));
    this.router.navigate(['/user/update-appointment'])
  }
}
