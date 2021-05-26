package com.example.demo;

import java.util.Date;
import lombok.Value;


/**
 *
 * @author xerx
 */
@Value(staticConstructor = "of")
public class Bar {
    private final String left, right;
    private final Date updated; 
}