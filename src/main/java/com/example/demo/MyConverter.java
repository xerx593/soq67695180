package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author xerx
 */
@Component
public class MyConverter extends MappingJackson2HttpMessageConverter {

    public MyConverter(@Qualifier("objectMapper2") ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(Foo.class)
                && super.canWrite(clazz, mediaType);
    }

    /*
	 * (non-Javadoc)
	 * @see org.springframework.http.converter.AbstractGenericHttpMessageConverter#canWrite(java.lang.reflect.Type, java.lang.Class, org.springframework.http.MediaType)
     */
    @Override
    public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
        return canWrite(clazz, mediaType);
    }

    /*
	 * (non-Javadoc)
	 * @see org.springframework.http.converter.json.MappingJackson2HttpMessageConverter#canRead(java.lang.reflect.Type, java.lang.Class, org.springframework.http.MediaType)
     */
    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        return false;
    }
}
