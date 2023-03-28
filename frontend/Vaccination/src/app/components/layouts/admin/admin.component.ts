import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  constructor(private router: Router, private authService: AuthService, private alert: AlertService) { }

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
  }

  onLogout() {
    sessionStorage.clear();
    this.router.navigateByUrl("/login");
    this.alert.apiSuccessTimer("Logout successfully");

  }
}
