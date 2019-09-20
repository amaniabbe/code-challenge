package com.people10.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomJobLauncher {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job  importCustomerJob;

    public void run(String path , String transformMap ) {

        try { //job launcher considers all csv files are valid
            JobExecution jobExecution = jobLauncher.run(importCustomerJob, new JobParametersBuilder()
                    .addString("fullPathFileName", path)
                    .addString("transformMap", transformMap)
                    .addString("timestamp", LocalDateTime.now().toString())
                    .toJobParameters());

            System.out.println("Exit Status : " + jobExecution.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
