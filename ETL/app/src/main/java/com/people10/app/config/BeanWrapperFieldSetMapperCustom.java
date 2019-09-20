package com.people10.app.config;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.util.StringUtils;
import org.springframework.validation.DataBinder;

//Custom mapper to to hundle datatime data transformation to Object of any type
public class BeanWrapperFieldSetMapperCustom<T> extends BeanWrapperFieldSetMapper<T>{

    @Override
    protected void initBinder(DataBinder binder){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (!StringUtils.isEmpty(text)) {
                    setValue(LocalDateTime.parse(text, formatter));
                } else {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() throws IllegalArgumentException {
                Object date = getValue();
                if (date != null) {
                    return formatter.format((LocalDateTime) date);
                } else {
                    return "";
                }
            }
        });
    }

}