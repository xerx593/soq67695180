package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean//() -> default
    public ObjectMapper objectMapper() {// -> name = "objectMapper", overrides default ObjectMapper
        return new ObjectMapper();
    }
    
    @Bean
    public ObjectMapper objectMapper2 () {
        ObjectMapper bean = new ObjectMapper();
        bean.setDateFormat(new SimpleDateFormat("dd.MM.yyyy hh:mm:ss"));
        return bean;
    }
    
    @RequestMapping("/foo")
    public Foo foo() {
        return Foo.of("I am", "foo", new Date());
    }
    
    @RequestMapping("/bar")
    public Bar bar() {
        return Bar.of("I am", "bar", new Date());
    }
}
