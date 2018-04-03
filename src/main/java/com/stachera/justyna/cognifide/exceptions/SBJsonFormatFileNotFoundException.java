package com.stachera.justyna.cognifide.exceptions;

/**
 * <h1>JSON FORMAT FILE NOT FOUND EXCEPTION</h1>
 * Class is used to handle the exception which extends {@link RuntimeException}. It occurs when path to JSON file is
 * invalid or file has invalid format.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
public class SBJsonFormatFileNotFoundException extends RuntimeException
{
    private String message;
    
    public SBJsonFormatFileNotFoundException()
    {
        this.message = "The JSON format file not found!";
    }
    
    public SBJsonFormatFileNotFoundException(String message)
    {
        this.message = message;
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
}
