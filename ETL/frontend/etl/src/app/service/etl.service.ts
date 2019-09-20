import { Injectable } from '@angular/core';
import { HttpClient, } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EtlService {

  constructor(private http:HttpClient) { }
  private url = "http://localhost:8080";

  public uploadCSV(formData:FormData):void{
    this.http.post(this.url + '/upload', formData).subscribe(
      data => console.log('success') , error => console.log(error)      
    )
  }
}
