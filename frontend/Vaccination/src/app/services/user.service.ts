import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from './alert.service';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private api: ApiService, private alert: AlertService, private router: Router) { }

  getUserDetails(id:string){
    return this.api.get(`/user/user/${id}`);
  }


}
