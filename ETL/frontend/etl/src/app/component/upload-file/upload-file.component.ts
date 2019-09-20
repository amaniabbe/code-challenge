import { Component, OnInit } from '@angular/core';
import { EtlService } from 'src/app/service/etl.service';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  constructor(private etlService : EtlService) { }

  ngOnInit() {
  }

  private formData:FormData = new FormData();

  dataFileChange(event):void{
    const fileslist:FileList = event.target.files; 
    if(fileslist.length > 0){
      let file = fileslist[0];
      let formData:FormData = new FormData();
      this.formData.append("uploadDataFile",file,file.name);

      

    }
}

mapFileChange(event):void{
  const fileslist:FileList = event.target.files; 
  if(fileslist.length > 0){
    let file = fileslist[0];
    
    this.formData.append("uploadMapFile",file,file.name);
  }
}

uploadFiles(){
  if(this.formData.has("uploadDataFile") && this.formData.has("uploadMapFile")){
    //in case authorization if needed
    const headers= new Headers();
    
    this.etlService.uploadCSV(this.formData);

  }
}

}
