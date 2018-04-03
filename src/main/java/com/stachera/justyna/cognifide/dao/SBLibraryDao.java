package com.stachera.justyna.cognifide.dao;

import com.stachera.justyna.cognifide.exceptions.SBCategoryNameNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * <h1>LIBRARY DAO INTERFACE</h1>
 * Interface includes method declarations to manage JSON files which contain book data.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
public interface SBLibraryDao
{
    /**
     * The method is used to load JSON file due to 'param' command line parameter. It contains a file path. Before
     * running the command line should include the argument: -Dparam which contains the file path. Otherwise
     * {@link com.stachera.justyna.cognifide.exceptions.SBJsonFormatFileNotFoundException} will occur.
     *
     * @return JSON file converted to {@link JSONObject}.
     */
    JSONObject getJSONData();
    
    /**
     * The method is used to get all books as {@link JSONObject}. Before calling the method we have to invoke
     * {@link SBLibraryDao#getJSONData()} from {@link SBLibraryDao}. In case of not found 'items' field in JSON file
     * {@link com.stachera.justyna.cognifide.exceptions.SBItemsFieldNotFoundException} will occur.
     *
     * @param jsonObject JSON file converted to {@link JSONObject}.
     * @return This returns {@link JSONArray} which contains all books.
     */
    JSONArray getBookAll(JSONObject jsonObject);
    
    /**
     * The method is used to get book as {@link JSONObject} by ISBN number. Before calling the method we have to invoke
     * {@link SBLibraryDao#getJSONData()}. In case of not found ISBN number in JSON file
     * {@link com.stachera.justyna.cognifide.exceptions.SBIsbnNotFoundException} will occur.
     *
     * @param jsonObject JSON file converted to {@link JSONObject}.
     * @param isbn       Looking for ISBN number.
     * @return This returns {@link JSONObject} which contains all books by ISBN number.
     */
    JSONObject getBookByIsbn(JSONObject jsonObject, String isbn);
    
    /**
     * The method is used to get book list as {@link JSONArray} by category name. Before calling the method we have
     * to invoke {@link SBLibraryDao#getJSONData()}. In case of not found category name in JSON file
     * {@link SBCategoryNameNotFoundException} will occur.
     *
     * @param jsonObject   JSON file converted to {@link JSONObject}.
     * @param categoryName Looking for category name.
     * @return This returns {@link JSONArray} which contains all books by category name.
     */
    JSONArray getBooksByCategory(JSONObject jsonObject, String categoryName);
    
    /**
     * The method is used to get book list as {@link JSONArray} by average rating in descent order. Before calling the
     * method we have to call {@link SBLibraryDao#getJSONData()}. In case of not found 'items' field in JSON file
     * {@link com.stachera.justyna.cognifide.exceptions.SBItemsFieldNotFoundException} will occur.
     *
     * @param jsonObject JSON file converted to {@link JSONObject}.
     * @return This returns {@link JSONObject} which contains all books .
     */
    JSONArray getBookByRatingDesc(JSONObject jsonObject);
    
    /**
     * The method is used to get all book categories as {@link List}.
     *
     * @param jsonObject JSON file converted to {@link JSONObject}.
     * @return This returns {@link List} which contains all book categories.
     */
    List<String> getBookCategories(JSONObject jsonObject);
}
