package com.stachera.justyna.cognifide.dao;

import com.stachera.justyna.cognifide.domain.SBBook;
import com.stachera.justyna.cognifide.exceptions.SBCategoryNameNotFoundException;
import com.stachera.justyna.cognifide.exceptions.SBIsbnNotFoundException;
import com.stachera.justyna.cognifide.exceptions.SBItemsFieldNotFoundException;
import com.stachera.justyna.cognifide.exceptions.SBJsonFormatFileNotFoundException;
import com.stachera.justyna.cognifide.utils.SBParameter;
import com.stachera.justyna.cognifide.utils.SBUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <h1>LIBRARY DAO INTERFACE IMPLEMENTATION</h1>
 * Class implements {@link SBLibraryDao} interface. It enables you to have access to JSON files.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
@Repository
public class SBLibraryImpl implements SBLibraryDao
{
    @Override
    public JSONObject getJSONData()
    {
        SBParameter.setParam(System.getProperty("param"));
        
        JSONObject jsonObject;
        
        try
        {
            JSONParser parser = new JSONParser();
            
            jsonObject = (JSONObject) parser.parse(new FileReader(SBParameter.setParam()));
            
            return jsonObject;
        }
        catch (ParseException | IOException e)
        {
            throw new SBJsonFormatFileNotFoundException();
        }
    }
    
    @Override
    public JSONArray getBookAll(JSONObject jsonObject)
    {
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");
        
        if (jsonArray == null)
        {
            throw new SBItemsFieldNotFoundException();
        }
        
        return jsonArray;
    }
    
    @Override
    public JSONObject getBookByIsbn(JSONObject jsonObject, String isbn)
    {
        JSONObject bookJsonObject = null;
        
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");
        
        if (jsonArray == null)
        {
            throw new SBItemsFieldNotFoundException();
        }
        
        List<SBBook> bookList = SBUtils.toSBBookList(jsonArray);
        
        Optional<SBBook> book = bookList.stream().filter(sbBook -> {
            
            boolean isTrue = false;
            String result = sbBook.getIsbn();
            
            if (result != null && result.equals(isbn))
            {
                isTrue = true;
            }
            
            return isTrue;
        }).findFirst();
        
        if (book.isPresent())
        {
            bookJsonObject = book.get().toJsonObject();
        }
        
        if (bookJsonObject == null)
        {
            throw new SBIsbnNotFoundException();
        }
        
        return bookJsonObject;
    }
    
    @Override
    public JSONArray getBooksByCategory(JSONObject jsonObject, String categoryName)
    {
        JSONArray bookJsonArray = new JSONArray();
        
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");
        
        if (jsonArray == null)
        {
            throw new SBItemsFieldNotFoundException();
        }
        
        List<SBBook> bookList = SBUtils.toSBBookList(jsonArray);
        
        bookList = bookList.stream().filter(sbBook -> {
            
            boolean isTrue = false;
            String[] categories = sbBook.getCategories();
            
            if (categories != null)
            {
                for (String s : categories)
                {
                    if (s.equals(categoryName))
                    {
                        isTrue = true;
                    }
                }
            }
            return isTrue;
        }).collect(Collectors.toList());
        
        bookJsonArray.addAll(bookList);
        
        if (bookJsonArray.isEmpty())
        {
            throw new SBCategoryNameNotFoundException();
        }
        
        return bookJsonArray;
    }
    
    @Override
    public JSONArray getBookByRatingDesc(JSONObject jsonObject)
    {
        JSONArray bookJsonArray = new JSONArray();
        
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");
        
        if (jsonArray == null)
        {
            throw new SBItemsFieldNotFoundException();
        }
        
        for (Object aJsonArray : jsonArray)
        {
            JSONObject record = (JSONObject) aJsonArray;
            JSONObject volumeInfo = (JSONObject) record.get("volumeInfo");
            JSONArray authors = (JSONArray) volumeInfo.get("authors");
            Double averageRating = (Double) volumeInfo.get("averageRating");
            
            if (authors == null || averageRating == null)
            {
                continue;
            }
            
            SBBook book = new SBBook();
            
            book.setAuthors(SBUtils.toStringArray(authors));
            book.setAverageRating(averageRating);
            
            JSONObject bookJsonObject = book.toJsonObject();
            
            bookJsonArray.add(bookJsonObject);
        }
        
        if (bookJsonArray.isEmpty())
        {
            throw new SBCategoryNameNotFoundException();
        }
        
        bookJsonArray.sort((o1, o2) -> {
            Double v1 = (Double) ((JSONObject) o1).get("averageRating");
            Double v2 = (Double) ((JSONObject) o2).get("averageRating");
            
            return v2.compareTo(v1);
        });
        
        return bookJsonArray;
    }
    
    @Override
    public List<String> getBookCategories(JSONObject jsonObject)
    {
        JSONArray bookArray = (JSONArray) jsonObject.get("items");
        List<String> categoryList = new ArrayList<>();
        
        for (Object o : bookArray)
        {
            SBBook book = new SBBook();
            JSONObject object = (JSONObject) o;
            JSONObject volumeInfo = (JSONObject) object.get("volumeInfo");
            
            if (volumeInfo != null)
            {
                book.setTitle((String) volumeInfo.get("title"));
                book.setCategories(SBUtils.toStringArray((JSONArray) volumeInfo.get("categories")));
                book.setAverageRating((Double) volumeInfo.get("averageRating"));
                book.setPublishedDate(SBUtils.toLong((String) volumeInfo.get("publishedDate")));
            }
            
            if (book.getCategories() != null)
            {
                categoryList.addAll(Arrays.asList(book.getCategories()));
            }
        }
        
        return categoryList.stream().distinct().collect(Collectors.toList());
    }
}