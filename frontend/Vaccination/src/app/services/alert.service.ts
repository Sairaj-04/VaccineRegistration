import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor() { }

  success(message: string) {
    return Swal.fire("Success", message, 'success');
  }

  apiSuccessTimer(res: string) {
    Swal.fire(
      {
        text:res,
        icon:"success",
        showConfirmButton:false,
        timer:1500
      }
    )
  }


  apiFail(res: any) {
    Swal.fire(res.error, res || 'Something went wrong', 'error');
  }

  apiFailTimer(res: string) {
    Swal.fire(
      {
        text:res,
        icon:"error",
        showConfirmButton:false,
        timer:1500
      }
    )
  }

  // apiSuccess(res: any) {
  //   Swal.fire(res?.statusCode, res?.status, 'success');
  // }

}
