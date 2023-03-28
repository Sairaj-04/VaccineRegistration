import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { VaccinationApiService } from 'src/app/services/vaccination-api.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {
  public currentLoggedInUser: any;

  constructor(
    private alert: AlertService,
    private user: UserService,
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

    let userId = sessionStorage.getItem('SESSION_USER_ID') as string;
    this.user.getUserDetails(userId).subscribe(res => {
      this.currentLoggedInUser = res;
    });
  }

  edit(){
    sessionStorage.setItem('editUser', JSON.stringify(this.currentLoggedInUser) );
    this.router.navigate(['/user/edit-profile']);
  }

}
