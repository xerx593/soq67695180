package com.example.demo;

import java.util.Date;
import lombok.Value;


/**
 *
 * @author xerx
 */
@Value(staticConstructor = "of")
public class Foo {
    private final String first, last;
    private final Date created; 
}
