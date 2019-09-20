package com.people10.app.config;


import com.people10.app.domain.Customer;
import com.people10.app.dto.CustomerDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.persistence.PersistenceContext;

@Configuration
@EnableBatchProcessing
public class ImportJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;





    @Bean
    @Scope(value = "step", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public FlatFileItemReader<CustomerDTO> importReader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile, @Value("#{jobParameters[transformMap]}") String transformMap) {
        FlatFileItemReader<CustomerDTO> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(pathToFile));


        String[] transformTo = transformMap.split(",");


        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<CustomerDTO>() {{

            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(transformTo);
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapperCustom<CustomerDTO>() {{
                setTargetType(CustomerDTO.class);
               
            }});
        }});

        return reader;
    }


   @Bean
   public CustomerItemProcessor processor() {
       return new CustomerItemProcessor();
   }

   @Bean
   @PersistenceContext
   public CustomerItemWriter writer() {

       return new CustomerItemWriter();
   }


      @Bean
     public Job importCustomerJob(ItemReader<CustomerDTO> itemReader) throws Exception {

         return jobBuilderFactory.get("importCustomerJob").incrementer(new RunIdIncrementer())
                 .flow(step1(itemReader)).end().build();
     }

   @Bean
   public Step step1(@Qualifier("importReader") ItemReader<CustomerDTO> importReader) throws Exception {
       DefaultTransactionAttribute txa = new DefaultTransactionAttribute();
       txa.setPropagationBehavior(TransactionDefinition.PROPAGATION_NEVER);

       return stepBuilderFactory.get("step1").<CustomerDTO, Customer>chunk(100).reader(importReader)
               .processor(processor()).writer(writer()).build();
   }

}
