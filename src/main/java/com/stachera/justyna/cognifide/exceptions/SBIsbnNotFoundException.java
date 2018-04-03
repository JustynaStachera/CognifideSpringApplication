package com.stachera.justyna.cognifide.exceptions;

/**
 * <h1>ISBN NOT FOUND EXCEPTION</h1>
 * Class is used to handle the exception which extends {@link RuntimeException}. It occurs when ISBN number doesn't
 * exist.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
public class SBIsbnNotFoundException extends RuntimeException
{
    private String message;
    
    public SBIsbnNotFoundException()
    {
        this.message = "The ISBN inserted not found!";
    }
    
    public SBIsbnNotFoundException(String message)
    {
        this.message = message;
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
}
