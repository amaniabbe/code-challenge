import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UploadFileComponent} from './component/upload-file/upload-file.component'

const routes: Routes = [
  { path: '', redirectTo: '/uploadCSV', pathMatch: 'full' },
  {path:'uploadCSV', component:UploadFileComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
