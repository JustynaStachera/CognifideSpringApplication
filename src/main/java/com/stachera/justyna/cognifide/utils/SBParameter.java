package com.stachera.justyna.cognifide.utils;

import com.stachera.justyna.cognifide.exceptions.SBParameterNotFoundException;

/**
 * <h1>PARAMETER CLASS</h1>
 * Class contains a parameter from the command line e.g.
 * <p>
 * java -Dpath=PATH_TO_FILE -jar target\APPLICATION_NAME.jar
 * <p>
 * If parameter is NULL, {@link SBParameterNotFoundException} will occur.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
public class SBParameter
{
    /**
     * Command line parameter.
     */
    private static String param;
    
    public static String setParam()
    {
        return param;
    }
    
    public static void setParam(String param)
    {
        if (param == null)
        {
            throw new SBParameterNotFoundException();
        }
        
        SBParameter.param = param;
    }
}
