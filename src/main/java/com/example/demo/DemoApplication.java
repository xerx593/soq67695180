package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // use "auto- and property configured" default mapper.
    
    //no bean
    private ObjectMapper fooMapper() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, true)
                .configure(SerializationFeature.INDENT_OUTPUT, false)
                .setDateFormat(new SimpleDateFormat("dd.MM.yyyy hh:mm:ss"));
    }

    //no bean
    private ObjectMapper barMapper() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, true)
                .configure(SerializationFeature.INDENT_OUTPUT, false)
                .setDateFormat(new SimpleDateFormat("MM/dd/yyyy K:mm:ss a"));
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter bean = new MappingJackson2HttpMessageConverter();
        bean.setObjectMapper(objectMapper);
        bean.registerObjectMappersForType(Foo.class, m -> { //!!
            m.put(MediaType.APPLICATION_JSON, fooMapper());
        });
        bean.registerObjectMappersForType(Bar.class, m -> {
            m.put(MediaType.APPLICATION_JSON, barMapper());
        });
        return bean;
    }

    @RequestMapping("/foo")
    public Foo foo() {
        return Foo.of(null, "foo", new Date());
    }

    @RequestMapping("/bar")
    public Bar bar() {
        return Bar.of(null, "bar", new Date());
    }

    @RequestMapping("/baz")
    public Baz baz() {
        return Baz.of(null, "baz", new Date());
    }
}
