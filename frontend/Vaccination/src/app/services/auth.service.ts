import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginDTO } from '../interfaces';
import { AlertService } from './alert.service';
import { ApiService } from './api.service';
import Swal from 'sweetalert2';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiFail(res: any) {
    Swal.fire(res.error, res || 'Something went wrong', 'error');
  }

  constructor(private api: ApiService, private alert: AlertService, private router: Router) { }

  registerUser(data: any) {
    this.api.post(`/user/register/${data.adrNumber}`, data).subscribe((res: any) => {
      this.alert.success('Registration successful.');
      this.router.navigate(['/login'])
    }, err=>{
      
      this.alert.apiFail(JSON.stringify(err.error))
    });
  }

    editRegisterUser(id:any,data: any) {
      this.api.put(`/user/user/${id}`, data).subscribe((res: any) => {
        this.alert.success('Profile Updated successful.');
        this.router.navigate(['/user/profile'])
      }, err=>{
        this.alert.apiFail(err)
      });
    }

  loginUser(data: LoginDTO) {
    this.api.postForLogin('/user/login', data).subscribe((res: any) => {
      let header=res.headers.get('token');
      sessionStorage.setItem("token",header);

      sessionStorage.setItem('SESSION_USERNAME', res?.body.name);
      sessionStorage.setItem('SESSION_ROLE', 'USER');
      sessionStorage.setItem('SESSION_USER_ID', res?.body.id);
      // this.alert.success("Login successful");
      this.alert.apiSuccessTimer("Login successful");
      this.router.navigate(["/user"]);
    }, err=>{
      this.alert.apiFail(err);
    });
  }

  loginAdmin(data: LoginDTO) {
    this.api.postForLogin('/admin/login', data).subscribe((res: any) => {
      let header=res.headers.get('token');
      sessionStorage.setItem("token",header);
      
      sessionStorage.setItem('SESSION_USERNAME', res.body.name);
      sessionStorage.setItem('SESSION_ROLE', 'ADMIN');
      sessionStorage.setItem('SESSION_USER_ID', res?.body.adminId);
      // this.alert.success("Login successful");
      this.alert.apiSuccessTimer("Login successful");
      this.router.navigateByUrl("/admin");
    }, err=>{
      this.alert.apiFail(err);
    });
  }

  isLoggedIn() {
    if (sessionStorage.getItem('SESSION_ROLE') && sessionStorage.getItem('SESSION_USERNAME'))
      return true;
    else {
      Swal.fire({
        title: "Login required!",
        text: "Sorry you need to log in to view this page",
        icon: 'warning'
      })
      this.router.navigateByUrl("/login");
      return false;
    }

  }

  isAdmin() {
    if (sessionStorage.getItem('SESSION_ROLE') && sessionStorage.getItem('SESSION_ROLE') === 'ADMIN')
      return true;
    return false;
  }

  isUser() {
    if (sessionStorage.getItem('SESSION_ROLE') && sessionStorage.getItem('SESSION_ROLE') === 'USER')
      return true;
    return false;
  }


  public tokenExpired(token: string) {
    const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
    return (Math.floor((new Date).getTime() / 1000)) >= expiry;
  }
 
}
