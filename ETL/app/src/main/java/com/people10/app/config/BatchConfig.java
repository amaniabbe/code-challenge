package com.people10.app.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
//@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer{

   @Bean
   @Primary
   public ResourcelessTransactionManager batchTransactionManager(){
       return new ResourcelessTransactionManager();
   }



   @Bean
   protected JobRepository jobRepository(ResourcelessTransactionManager batchTransactionManager) throws Exception{
       MapJobRepositoryFactoryBean jobRepository = new MapJobRepositoryFactoryBean();
       jobRepository.setTransactionManager(batchTransactionManager);
       return jobRepository.getObject();
   }

   @Bean
   public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception{
       SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
       jobLauncher.setJobRepository(jobRepository);
       return jobLauncher;
   }

    
}
