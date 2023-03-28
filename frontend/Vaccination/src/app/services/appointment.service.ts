import { Injectable } from '@angular/core';
import { AlertService } from './alert.service';
import { ApiService } from './api.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(
    private api: ApiService,
    private alert: AlertService,
    private router: Router
  ) { }


  bookAppointment(payload:any,id:number,vid:number,vcid:number,dose:number, callback?: () => void) {
    delete payload.id;
    delete payload.vid;
    delete payload.vcid;
    delete payload.dose;

    return this.api.post(`/user/vaccination/${id}/${vid}/${vcid}/${dose}`, payload).subscribe((res: any) => {
      if (callback) callback();
      this.alert.success('Vaccination slot booked successful.');
      this.router.navigate(['/user/appointment']);
    }, err=>{
      this.alert.apiFail(err)
    });
  }

  updateAppointment(payload:any, callback?: () => void) {
    return this.api.put(`/user/vaccination`, payload).subscribe((res: any) => {
      if (callback) callback();
      this.alert.success('Vaccination appointment updated successful.');
      this.router.navigate(['/user/appointment']);
      sessionStorage.removeItem('slot');
    }, err=>{
      this.alert.apiFail(err)
    });
  }

  getAllAppointments(id:string) {
    return this.api.get(`/user/doses${id}`);
  }

  deleteAppointment(Id: number, payload: any, callback?: () => void) {
    return this.api.put(`/user/vaccination/${Id}`, payload).subscribe((res: any) => {
      if (callback) callback();
      this.alert.success('Appoinment cancelled successful.').then(()=>{
        window.location.reload()
      })
    }, this.alert.apiFail);
  }
}

