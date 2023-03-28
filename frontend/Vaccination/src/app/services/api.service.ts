import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private API_BASE_URL: string = 'http://localhost:8090';
  constructor(private http: HttpClient) { }

  postForLogin(uri: string, payload: any) {
    return this.http.post(this.API_BASE_URL + uri, payload, { observe: 'response' });
  }
  post(uri: string, payload: any) {
    let headers = this.getAuthorizationHeader();
    return this.http.post(this.API_BASE_URL + uri, payload, { headers });
  }
  put(uri: string, payload: any) {
    let headers = this.getAuthorizationHeader();
    return this.http.put(this.API_BASE_URL + uri, payload, { headers });
  }
  get(uri: string) {
    let headers = this.getAuthorizationHeader();
    return this.http.get(this.API_BASE_URL + uri, { headers });
  }
  delete(uri: string) {
    let headers = this.getAuthorizationHeader();
    return this.http.delete(this.API_BASE_URL + uri, { headers });
  }

  getAuthorizationHeader(): HttpHeaders {
    const tokenStr = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set("Authorization", '' + tokenStr);
    return headers;
  }

}
