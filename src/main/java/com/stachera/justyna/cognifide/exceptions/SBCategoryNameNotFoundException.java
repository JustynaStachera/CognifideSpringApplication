package com.stachera.justyna.cognifide.exceptions;

/**
 * <h1>CATEGORY NAME NOT FOUND EXCEPTION</h1>
 * Class is used to handle the exception which extends {@link RuntimeException}. It occurs when book category name
 * doesn't exist.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
public class SBCategoryNameNotFoundException extends RuntimeException
{
    private String message;
    
    public SBCategoryNameNotFoundException()
    {
        this.message = "The category not found!";
    }
    
    public SBCategoryNameNotFoundException(String message)
    {
        this.message = message;
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
}
