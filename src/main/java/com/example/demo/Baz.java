package com.example.demo;

import java.util.Date;
import lombok.Value;


/**
 *
 * @author xerx
 */
@Value(staticConstructor = "of")
public class Baz {
    private final String a, b;
    private final Date deleted; 
}