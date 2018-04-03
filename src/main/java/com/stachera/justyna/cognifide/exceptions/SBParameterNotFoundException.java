package com.stachera.justyna.cognifide.exceptions;

/**
 * <h1>PARAMETER NOT FOUND EXCEPTION</h1>
 * Class is used to handle the exception which extends {@link RuntimeException}. It occurs when -Dparam command line
 * parameter is invalid.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
public class SBParameterNotFoundException extends RuntimeException
{
    private String message;
    
    public SBParameterNotFoundException()
    {
        this.message = "-Dparam command line argument not found!";
    }
    
    public SBParameterNotFoundException(String message)
    {
        this.message = message;
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
}
