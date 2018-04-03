package com.stachera.justyna.cognifide.exceptions;

/**
 * <h1>ITEMS FIELD NOT FOUND EXCEPTION</h1>
 * Class is used to handle the exception which extends {@link RuntimeException}. It occurs when 'items' field doesn't
 * exist in JSON file.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
public class SBItemsFieldNotFoundException extends RuntimeException
{
    private String message;
    
    public SBItemsFieldNotFoundException()
    {
        this.message = "The 'items' field not found!";
    }
    
    public SBItemsFieldNotFoundException(String message)
    {
        this.message = message;
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
}
