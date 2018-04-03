package com.stachera.justyna.cognifide.utils;

import com.stachera.justyna.cognifide.domain.SBBook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <h1>UTILS CLASS</h1>
 * Class contains extra methods to improve web application.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
public class SBUtils
{
    private static final Logger LOGGER = Logger.getLogger(SBUtils.class.getName());
    
    /**
     * The method is used to convert {@link Long} to {@link Integer}.
     *
     * @param variable {@link Long} variable.
     * @return {@link Long} converted to {@link Integer}
     */
    public static Integer toInteger(Long variable)
    {
        return variable != null ? variable.intValue() : null;
    }
    
    /**
     * The method is used to convert {@link JSONArray} to {@link String[]}.
     *
     * @param jsonArray {@link JSONArray} variable.
     * @return {@link JSONArray} converted to {@link String[]}
     */
    public static String[] toStringArray(JSONArray jsonArray)
    {
        if (jsonArray == null)
        {
            return null;
        }
        
        String[] strings = new String[jsonArray.size()];
        
        for (int i = 0; i < strings.length; ++i)
        {
            strings[i] = (String) jsonArray.get(i);
        }
        
        return strings;
    }
    
    /**
     * The method is used to convert {@link String} to {@link Long}. It is intended for DATE FORMAT {@link String}
     * conversion: yyyy-MM-dd, yyyy.
     *
     * @param stringDateFormat {@link String} variable.
     * @return {@link String} converted to {@link Long}
     */
    public static Long toLong(String stringDateFormat)
    {
        Date date = null;
        SimpleDateFormat formatter;
        
        try
        {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(stringDateFormat);
        }
        catch (ParseException e1)
        {
            // LOGGER.warning("ParseException #1 EXCEPTION!");
        }
        catch (NullPointerException e2)
        {
            LOGGER.warning("NullPointerException #1 EXCEPTION!");
        }
        
        try
        {
            formatter = new SimpleDateFormat("yyyy");
            date = formatter.parse(stringDateFormat);
        }
        catch (ParseException e3)
        {
            // LOGGER.warning("ParseException #2 EXCEPTION!");
        }
        catch (NullPointerException e4)
        {
            LOGGER.warning("NullPointerException #2 EXCEPTION!");
        }
        
        return date != null ? date.getTime() : null;
    }
    
    /**
     * The method is used to convert {@link Long} to {@link String}. It is intended for DATE FORMAT {@link String}
     * conversion: yyyy-MM-dd, yyyy.
     *
     * @param longDateConversion {@link String} variable.
     * @return {@link Long} converted to {@link String}
     */
    public static String toStringDateFormat(Long longDateConversion)
    {
        Date date = new Date(longDateConversion);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        return simpleDateFormat.format(date);
    }
    
    /**
     * The method is used to convert {@link JSONArray} which contains {@link SBBook} objects to {@link List}.
     *
     * @param bookJsonArray This is {@link JSONArray} which contains {@link SBBook} objects.
     * @return {@link JSONArray} converted to {@link List}.
     */
    public static List<SBBook> toSBBookList(JSONArray bookJsonArray)
    {
        List<SBBook> bookList = new LinkedList<>();
        
        for (Object aJsonArray : bookJsonArray)
        {
            JSONObject record = (JSONObject) aJsonArray;
            JSONObject volumeInfo = (JSONObject) record.get("volumeInfo");
            
            JSONObject imageLinks = (JSONObject) volumeInfo.get("imageLinks");
            JSONArray industryIdentifiers = (JSONArray) volumeInfo.get("industryIdentifiers");
    
            SBBook book = new SBBook();
            
            for (Object o : industryIdentifiers)
            {
                JSONObject industryIdentify = (JSONObject) o;
    
                if (industryIdentify.get("type").equals("ISBN_13"))
                {
                    book.setIsbn((String) industryIdentify.get("identifier"));
    
                    break;
                }
            }
    
            book.setTitle((String) volumeInfo.get("title"));
            book.setSubtitle((String) volumeInfo.get("subtitle"));
            book.setPublisher((String) volumeInfo.get("publisher"));
            book.setPublishedDate(SBUtils.toLong((String) volumeInfo.get("publishedDate")));
            book.setDescription((String) volumeInfo.get("description"));
            book.setPageCount(SBUtils.toInteger((Long) volumeInfo.get("pageCount")));
            book.setThumbnailUrl((String) imageLinks.get("thumbnail"));
            book.setLanguage((String) volumeInfo.get("language"));
            book.setPreviewLink((String) volumeInfo.get("previewLink"));
            book.setAverageRating((Double) volumeInfo.get("averageRating"));
            book.setAuthors(SBUtils.toStringArray((JSONArray) volumeInfo.get("authors")));
            book.setCategories(SBUtils.toStringArray((JSONArray) volumeInfo.get("categories")));
            
            bookList.add(book);
        }
        
        return bookList;
    }
}