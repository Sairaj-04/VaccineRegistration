import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register-user-list',
  templateUrl: './register-user-list.component.html',
  styleUrls: ['./register-user-list.component.css']
})
export class RegisterUserListComponent {
  public users:any[] = [];
  constructor(
    private adminService: AdminService,
    private user: UserService,
    private alert: AlertService,
    private authService: AuthService,
    private router: Router
  ) { }

  query: string="";
  sortField: string = "id";
  changeSortFiled(filed: string) {
    this.sortField = filed;
  }
  
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
    this.getUsers();
  }

  getUsers(){
    this.adminService.getAllUsers().subscribe(res => {
      this.users = res as any[];
    });
  }

  

  getValue(item:any){
    if(item.length){
      return item[item.length-1]
    }
    return {};
  }

  onComplete(item:any){
    let dose = item[item.length-1];
    console.log(item)
    this.adminService.completeDose(dose.doseId).subscribe((res: any) => {
      this.alert.success('Dose Complete successful.'); 
      this.getUsers();
    }, this.alert.apiFail);
    
  }
}
