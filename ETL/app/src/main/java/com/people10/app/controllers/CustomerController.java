package com.people10.app.controllers;

import com.people10.app.config.CustomJobLauncher;


import java.io.*;

import java.util.*;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class CustomerController {



    @Autowired
    private CustomJobLauncher jobLauncher;


    @PostMapping("/upload")
    public String uploadMapOne(MultipartHttpServletRequest request)
            throws IOException, JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException, FileUploadBase.IOFileUploadException {




        //1 get files from web request
        MultipartFile mapFile = request.getFile("uploadMapFile");
        MultipartFile dataFile = request.getFile("uploadDataFile");


        //2 validate (null pointer exception not hundled)
        if(!(mapFile.isEmpty() && dataFile.isEmpty())){

            //3 read data from mapper for data transformation
            BufferedReader reader;
            List<String> result = new ArrayList<>();

            try {
                String line;
                reader = new BufferedReader(new InputStreamReader( mapFile.getInputStream()));
                while ((line = reader.readLine()) != null){
                    result.add(line);
                }

            }catch (IOException e) {
                System.err.println(e.getMessage());
                throw  new FileUploadBase.IOFileUploadException();
            }

            //CSV content not validated for proper coulum and raw size
            //4 Save multipartFile file in a temporary physical folder/resources
            String fileName = dataFile.getOriginalFilename();
            String path = new ClassPathResource("").getURL().getPath();
            File fileToImport = new File(path + fileName);
            OutputStream outputStream = new FileOutputStream(fileToImport);
            IOUtils.copy(dataFile.getInputStream(), outputStream);
            outputStream.flush();
            outputStream.close();






            //5 Launch the Batch Job read---process---write to database
            System.out.println("Job luncher started with: " + fileToImport.getAbsolutePath());
            jobLauncher.run(fileToImport.getAbsolutePath() , result.get(0));

            return "batch job has successfully started";
        }
        else throw new FileNotFoundException();

        //return "success";
    }



}