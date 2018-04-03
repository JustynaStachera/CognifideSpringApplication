package com.stachera.justyna.cognifide.domain;

import java.util.Objects;

public class SBField
{
    private String field;
    
    public String getField()
    {
        return field;
    }
    
    public void setField(String field)
    {
        this.field = field;
    }
    
    @Override
    public String toString()
    {
        return "SBField{" +
               "field='" + field + '\'' +
               '}';
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        SBField sbField = (SBField) o;
        return Objects.equals(field, sbField.field);
    }
    
    @Override
    public int hashCode()
    {
        
        return Objects.hash(field);
    }
}
